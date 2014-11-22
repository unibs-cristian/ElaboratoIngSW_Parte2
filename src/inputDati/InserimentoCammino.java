/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package inputDati;

import java.io.Serializable;
import java.util.Vector;

import controlloCammino.FermatoDentro;
import controlloCammino.PercorsoTutto;
import controlloCammino.StatoCammino;
import controlloCammino.StatoNonOk;
import controlloCammino.StatoVuoto;
import gestioneModello.Entita;
import testSuiteDiagnosi.CamminoAzioni;
import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.Coppia;
import utilita.Util;
import gestioneModello.Azione;
import gestioneModello.Modello;
import gestioneModello.Ramo;

// TODO: Auto-generated Javadoc
/**
 * The Class InserimentoCammino.
 */
public class InserimentoCammino implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_CAMM_GLOBALE_1. */
	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	
	/** The Constant MSG_AGGIUNTA_CAMM_GLOBALE. */
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	
	/** The Constant MSG_ERRORE_CAMMINO. */
	public final static String MSG_ERRORE_CAMMINO = "Errore! Il cammino e' vuoto. Inserire nuovamente.";
	
	/** The Constant MSG_CAMMINO_NON_VALIDO. */
	public final static String MSG_CAMMINO_NON_VALIDO = "Errore! Cammino non valido. Inserire nuovamente.";
	
	/** The Constant MSG_INS_CAMMINO. */
	public final static String MSG_INS_CAMMINO = "Scegliere le azioni da aggiungere all'insieme del cammino";
	
	/** The Constant MSG_CAMM_GLOB. */
	public final static String MSG_CAMM_GLOB = "Cammino Globale --> ";
	
	/** The Constant MSG_INS_CAMM. */
	public final static String MSG_INS_CAMM = "Insieme del Cammino --> ";
	
	/** The Constant MSG_AGGIUNTA_INS_CAMM. */
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	
	/** The Constant MSG_VAL_RILEV. */
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	
	/** The Constant MSG_COPPIA_AGGIUNTA. */
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	
	/** The ce. */
	private ClasseEquivalenza ce;
	
	/** The camm. */
	private CamminoAzioni camm;
	
	/**
	 * Instantiates a new inserimento cammino.
	 *
	 * @param _ce the _ce
	 * @param _camm the _camm
	 */
	public InserimentoCammino(ClasseEquivalenza _ce,CamminoAzioni _camm) {
		ce = _ce;
		camm = _camm;
	}
	
	/**
	 * Inserisci camm.
	 */
	public void inserisciCamm() {
		if(camm.isGlobale())
			System.out.println(MSG_CAMM_GLOBALE_1);
		else
			System.out.println(MSG_INS_CAMMINO);
		Vector <Entita> entitaMod = Modello.getInstance().getEntita();
	    boolean camminoValido = false;
		do {
			//Toglie le eventuali azioni presenti nel cammino
			if(!equals(camm.isEmpty()))
				camm.setInsiemeCammino(new Vector <Azione>());
			
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
			//Controllo se al termine dell'inserimento il cammino si trova in uno stato valido. Se non lo e', lo faccio reinserire, stampando a video un opportuno messaggio di errore.
			if(camm.isEmpty())
				camminoValido = false;
			else
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
		
		if(!(camm.isEmpty())) {
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
	}
	
	/**
	 * Gestisci stato azione.
	 *
	 * @param e the e
	 * @param esterna the esterna
	 * @param posizioneRamo the posizione ramo
	 */                                       //Sembra che esteran e poRamo non servano.
	public void gestisciStatoAzione(Entita e, Entita esterna, int numRamo) {
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
			//Se mi sono fermato in un ramo e inserisco un'alra entita', allora questo diventa non valido.
			else if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			//Se in un cammino parziale un ramo e' stato percorso interamente e viene inserita nel cammino un'altra azione di un'altro ramo di quell'entita', allora il cammino diventa non valido 
			else if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO) && !(camm.isGlobale()))
					camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			//Se sono in stato NON_PERCORSO (relativamente ad un ramo) ed inserisco un'azione di quel ramo, finisco in stato NON_OK
			else if(camm.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
			//Se tutta l'entita' complessa precedente e' stata saltata e viene aggiunta una nuova azione, il cammino diventa non valido
			else if(camm.getStato().getStringaStato().equals(StatoCammino.SALTATO_BLOCCO))
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
		}	
		//Calcolo del nuovo stato nel caso in cui un'azione non viene inserita
		else {
			//Se sono negli stati OK e VUOTO, vado nello stato FERMATO
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_OK))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
				camm.getStato().gestisciStato(camm, StatoCammino.NON_PERCORSO);
			else if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ)) {
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO_DENTRO);
				if(esterna!=null)
					esterna.getRami()[numRamo].setStato(new FermatoDentro());
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);	
			//Se tutta l'entita' complessa precedente e' stata saltata e non viene aggiunta una nuova azione al di fuori di essa, il cammino diventa FERMATO
			else if(camm.getStato().getStringaStato().equals(StatoCammino.SALTATO_BLOCCO))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
			
		}
		System.out.println("Stato --> " + camm.getStato().getStringaStato());
	}
	
	/**
	 * Gestisci stato complessa.
	 *
	 * @param e the e
	 */
	public void gestisciStatoComplessa(Entita e) {
		camm.getStato().gestisciStato(camm, StatoCammino.ENTRATO_RAMO);
		for(int i=0; i<e.getRami().length; i++) {
			camm.getStato().gestisciStato(camm, StatoCammino.ENTRATO_RAMO);
			//TODO si puo' aggiungere lo stato al costruttore per evitare tutte queste chiamate di metodi
			Ramo ramoCorrente = e.getRami()[i];
			for(int j=0; j<ramoCorrente.getNumeroEntita(); j++) {
				Entita ent = ramoCorrente.getEntitaAt(j);
				String tipo = ent.getIdTipo();
				if(tipo.equals(Entita.ID_TIPO_AZIONE))
					gestisciStatoAzione(ent, e, i);
				else if(tipo.equals(Entita.ID_TIPO_BRANCH) || tipo.equals(Entita.ID_TIPO_CICLO) || tipo.equals(Entita.ID_TIPO_FORK))
					gestisciStatoComplessa(ent);
				}
			//Se stavo percorrendo il ramo, allora quando viene inserita l'ultima entita' il ramo diventa percorso completamente
			if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO) || camm.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO)) {
				camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_TUTTO);
				e.getRami()[i].setStato(new PercorsoTutto());
				System.out.println("Troooooooooooooooooooooooooooooooooooooooooooooooooool");
			}
		}
		
		//Verifico se l'entita' complessa e' stata saltata completamente (nessuna entita' di nessun ramo e' stata inserita).
		//In tal caso lo stato del cammino diventera' SALTATO_BLOCCO. Questo controllo non va fatto se sono in stato FERMATO perche' 
		//e' ovvio che in quello stato non potro' piu' inserire nuove azioni nel cammino.
		
		if(!(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO)) && !(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO)) && !(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK))) { 
			boolean saltata = true;
			int i=0;
			while(saltata && i<e.getRami().length) {
				Ramo current = e.getRami()[i];
				if(current.isEmpty()) {
					saltata = false;
					current.setStato(new PercorsoTutto());
				}
				int j=0;
				while(saltata && j<current.getNumeroEntita()) {
					if(camm.presente(current.getAzioniRamo().elementAt(j)))
						saltata = false;
					j++;
				}
				i++;
			}
			//Se tutti i rami sono stati saltati, il cammino va in stato SALTATO_BLOCCO
			if(saltata == true)
				camm.getStato().gestisciStato(camm, StatoCammino.SALTATO_BLOCCO);
		}
		
		//Se l'entita' complessa e' un branch o un ciclo e il cammino e' globale, controllo che nel caso in cui ci si fermi dentro l'entita' complessa, non vengano inserite azioni successive.
		if(!(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK)) && camm.isGlobale() && ((e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO)))) {
			boolean fermatoDentro = true;
			int i=0;
			while(fermatoDentro == true && i<e.getRami().length) {
				Ramo r = e.getRami()[i];
				if(r.getStato()!=null && r.getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO))
					fermatoDentro = false;
				i++;
			}
			if(fermatoDentro)
				camm.setStatoCammino(new FermatoDentro());
	//		else 
	//			camm.setStatoCammino(new StatoOk());
		}
						
		//Se l'entita' complessa e' un fork, controllo che l'intero blocco di esecuzione sia stato inserito. Se non lo e', il cammino diventa NON_OK
		if(e.getIdTipo().equals(Entita.ID_TIPO_FORK) && !(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK))) {
			boolean ramoPercorsoTutto = false;
			int i=0;
			//Verifico che ci sia almeno un ramo percorso completamente.
			while(ramoPercorsoTutto == false && i<e.getRami().length) {
				if(e.getRami()[i].getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO))
					ramoPercorsoTutto = true;
				i++;
			}
			//Se c'e' almeno un ramo percorso completamente, allora tutti i rami devono essere percorsi completamente, altrimenti il cammino inserito diventa non valido
			if(ramoPercorsoTutto) {
				boolean bloccoInserito = true;
				i=0;
				while(bloccoInserito && i<e.getRami().length) {
					if(!(e.getRami()[i].getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO)))
						bloccoInserito = false;	
					i++;	
				}
				if(!bloccoInserito) {
					camm.setStatoCammino(new StatoNonOk());
					System.out.println("Wiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
				}
			}
			//Se non c'e' alcun ramo percorso completamente, significa che mi sono fermato dentro al fork.
			else
				camm.setStatoCammino(new FermatoDentro());
		}
	
		camm.getStato().gestisciStato(camm, StatoCammino.STATO_OK);		
		System.out.println("Stato --> " + camm.getStato().getStringaStato());
	}
}