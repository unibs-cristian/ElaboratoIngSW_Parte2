package inputDati;

import java.io.Serializable;
import java.util.Vector;

import controlloCammino.EntratoRamo;
import controlloCammino.StatoCammino;
import controlloCammino.StatoVuoto;
import gestioneModello.Entita;
import testSuiteDiagnosi.CamminoAzioni;
import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.Coppia;
import utilita.Util;
import gestioneModello.Azione;
import gestioneModello.Modello;
import gestioneModello.Ramo;

public class InserimentoCammino implements Serializable {

	private static final long serialVersionUID = 1L;
	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	public final static String MSG_ERRORE_CAMMINO = "Errore! Il cammino e' vuoto. Inserire nuovamente.";
	public final static String MSG_CAMMINO_NON_VALIDO = "Errore! Cammino non valido. Inserire nuovamente.";
	public final static String MSG_INS_CAMMINO = "Scegliere le azioni da aggiungere all'insieme del cammino";
	public final static String MSG_CAMM_GLOB = "Cammino Globale --> ";
	public final static String MSG_INS_CAMM = "Insieme del Cammino --> ";
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	
	private ClasseEquivalenza ce;
	private CamminoAzioni camm;
	
	public InserimentoCammino(ClasseEquivalenza _ce,CamminoAzioni _camm) {
		ce = _ce;
		camm = _camm;
	}
	
	public void inserisciCamm() {
		System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
		if(camm.isGlobale())
			System.out.println(MSG_CAMM_GLOBALE_1);
		else
			System.out.println(MSG_INS_CAMMINO);
		Vector <Entita> entitaMod = Modello.getInstance().getEntita();
	    boolean camminoValido = false;
		do {
			//Ripristina lo stato iniziale a vuoto se non lo e' (puo' accadere per esempio dopo che ho inserito un cammino non valido)
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO) == false)
				camm.setStatoCammino(new StatoVuoto());
			
			for(int i=1; i<entitaMod.size()-1; i++) {
				Entita e = entitaMod.elementAt(i);
				String tipo = e.getIdTipo();
				if(tipo.equals(Entita.ID_TIPO_AZIONE))
					gestisciStatoAzione(e, null, 0);
				else if(tipo.equals(Entita.ID_TIPO_BRANCH) || tipo.equals(Entita.ID_TIPO_CICLO) || tipo.equals(Entita.ID_TIPO_FORK))
					gestisciStatoComplessa(e);
			}
			System.out.println("STATO FINALE --> " + camm.getStato().getStringaStato());
			//Controllo se al termine dell'inserimento il cammino si trova in uno stato valido. Se non lo e', lo faccio reinserire, stampando a video un opportuno messaggio di errore.
			camminoValido = camm.getStato().isValid();
			//Controllo se l'insieme del cammino e' un sottoinsieme del cammino globale
			if(camm.isGlobale()==false) {
				CamminoAzioni cammGlob = ce.getCamminoGlobale();
				if(camm.inclusoIn(cammGlob) == false)
					camminoValido = false;
			}
			
			if(camminoValido  == false)
				System.out.println(MSG_CAMMINO_NON_VALIDO);
		} while(camminoValido == false);
		
		if(camm.isGlobale()) {
			System.out.println(MSG_CAMM_GLOB + camm.toString());
			ce.setCamminoGlobale(camm);
		}
		else {
			System.out.println(MSG_INS_CAMM + camm.toString());
			String valoreRilevazione = Util.okOrKo(MSG_VAL_RILEV);
			Coppia c = new Coppia(camm, valoreRilevazione);
			ce.addCoppia(c);
		}
	}
	
	public void gestisciStatoAzione(Entita e, Entita esterna, int posizioneRamo) {
		String richiestaInserimento;
		if(camm.isGlobale())
			richiestaInserimento = MSG_AGGIUNTA_CAMM_GLOBALE;
		else
			richiestaInserimento = MSG_AGGIUNTA_INS_CAMM;
		
		//Calcolo del nuovo stato nel caso in cui un'azione viene inserita		
		if(Util.yesOrNo(String.format(richiestaInserimento,e.getNome()))) {
			camm.aggiungiAzione((Azione)e);
			//Se lo stato era VUOTO, il nuovo stato sara' OK
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_OK);
			//Se lo stato era FERMATO DENTRO, il nuovo stato sara' NON_OK
			else if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			//Se sono appena entrato in un ramo e inserisco la prima azione, vado in PERCORSO_PARZ
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
				camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_PARZ);
			//Se mi sono fermato in un ramo e inserisco un'alra entita' e se il cammino e' parziale, allora questo diventa non valido.
			else if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) && camm.isGlobale() == false)
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			//Se in un cammino parziale un ramo e' stato percorso interamente e viene inserita nel cammino un'altra azione di un'altro ramo di quell'entita', allora il cammino diventa non valido 
			else if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			//Se sono in stato NON_PERCORSO (relativamente ad un ramo) ed inserisco un'azione di quel ramo, finisco in stato NON_OK
			else if(camm.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			
			
			System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
		}	
		//Calcolo del nuovo stato nel caso in cui un'azione non viene inserita
		else {
			//Se sono negli stati OK e VUOTO, vado nello stato FERMATO
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_OK))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
				camm.getStato().gestisciStato(camm, StatoCammino.NON_PERCORSO);
			else if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO_DENTRO);
			else if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
			
			System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());			
		}
	}
	
	public void gestisciStatoComplessa(Entita e) {
		camm.getStato().gestisciStato(camm, StatoCammino.ENTRATO_RAMO);
		System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
		for(int i=0; i<e.getRami().length; i++) {
			camm.getStato().gestisciStato(camm, StatoCammino.ENTRATO_RAMO);
			System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
			//Se sto inserendo il cammino globale, posso percorrere anche piu' rami completamente o parzialmente
			/*if(i>0 && camm.isGlobale()) {
				camm.setStatoCammino(new EntratoRamo());
				System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
			}
			else if(camm.isGlobale()==false) {
				//TODO si puo' aggiungere lo stato al costruttore per evitare tutte queste chiamate di metodi
				camm.getStato().gestisciStato(camm, StatoCammino.ENTRATO_RAMO);
				System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
			}*/
			Ramo ramoCorrente = e.getRami()[i];
			for(int j=0; j<ramoCorrente.getNumeroEntita(); j++) {
				Entita ent = ramoCorrente.getEntitaAt(j);
				String tipo = ent.getIdTipo();
				if(tipo.equals(Entita.ID_TIPO_AZIONE))
					gestisciStatoAzione(ent, e, j);
				else if(tipo.equals(Entita.ID_TIPO_BRANCH) || tipo.equals(Entita.ID_TIPO_CICLO) || tipo.equals(Entita.ID_TIPO_FORK))
					gestisciStatoComplessa(ent);
				}
			//Se stavo percorrendo il ramo, allora quando viene inserita l'ultima entita' il ramo diventa percorso completamente
			if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ)) {
				camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_TUTTO);
			}
			System.out.println("Stato Cammino --> " + camm.getStato().getStringaStato());
		}
		
		boolean nonVuoto = false;
		for(int i=0; i<e.getRami().length; i++) {
			if(e.getRami()[i].isEmpty() == false)
				nonVuoto = true;
		}
		//Se tutti i rami sono stati saltati, il cammino diventa NON OK
		if(nonVuoto == false)
			camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
		//Se l'entita' complessa e' un fork, controllo che l'intero blocco di esecuzione sia stato inserito. Se non lo e', il cammino diventa NON_OK
		if(e.getIdTipo().equals(Entita.ID_TIPO_FORK)) {
			camm.getStato().gestisciStato(camm, StatoCammino.SUPERATO_FORK);
			Vector <Entita> azioniFork = new Vector<Entita>();
			//TODO alleggerire in fase di refactoring
			for(int i=0; i<e.getEntita().size(); i++)
				if(e.getEntita().elementAt(i).getIdTipo().equals(Entita.ID_TIPO_AZIONE))
					azioniFork.add(e.getEntita().elementAt(i));
			
			//Dice se il blocco di esecuzione e' stato inserito
			boolean nonInserito = false;
			for(int i=0; i<azioniFork.size(); i++)
				if(camm.presente((Azione)azioniFork.elementAt(i)) == false)
					nonInserito = true;
			if(nonInserito)
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
		}
		camm.getStato().gestisciStato(camm, StatoCammino.STATO_OK);
	}
}