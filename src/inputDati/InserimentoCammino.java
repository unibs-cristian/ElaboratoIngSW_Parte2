/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package inputDati;

import java.util.Vector;

import controlloCammino.EntratoRamo;
import controlloCammino.Fermato;
import controlloCammino.FermatoDentro;
import controlloCammino.PercorsoTutto;
import controlloCammino.SaltatoBlocco;
import controlloCammino.StatoCammino;
import controlloCammino.StatoNonOk;
import controlloCammino.StatoOk;
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
public class InserimentoCammino {
	
	/** The Constant MSG_CAMM_GLOBALE_1. */
	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	
	/** The Constant MSG_AGGIUNTA_CAMM_GLOBALE. */
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	
	/** The Constant MSG_ERRORE_CAMMINO. */
	public final static String MSG_ERRORE_CAMMINO = "Errore! Il cammino e' vuoto. Inserire nuovamente.";
	
	/** The Constant MSG_CAMMINO_NON_VALIDO. */
	public final static String MSG_CAMMINO_NON_VALIDO = "Errore! Cammino non valido perche' %s .Inserire nuovamente.";
	
	public final static String MSG_CAMMINO_INCORRETTO = "non corretto in base alla struttura del modello";
	public final static String MSG_NON_SOTTOINSIEME = "non e' un sottoinsieme del cammino globale";
	
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
		boolean camminoValido;
		boolean sottoinsieme; 
		do {
			camminoValido = false;
			sottoinsieme = true;
			//Toglie le eventuali azioni presenti nel cammino (utile per resettare il Vector di Azioni quando si è inserito un cammino errato.
			if(!(camm.isEmpty()))
				camm.azzeraAzioni();
			
			//Ripristina lo stato iniziale a vuoto se non lo e' (puo' accadere per esempio dopo che ho inserito un cammino non valido)
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO) == false)
				camm.setStatoCammino(new StatoVuoto());
			
			for(int i=1; i<entitaMod.size()-1; i++) {
				Entita e = entitaMod.elementAt(i);
				String tipo = e.getIdTipo();
				if(tipo.equals(Entita.ID_TIPO_AZIONE) || tipo.equals(Entita.ID_TIPO_AZIONE_COMPOSTA))
					gestisciStatoAzione(e, null, -1, -1);
				else if(tipo.equals(Entita.ID_TIPO_BRANCH) || tipo.equals(Entita.ID_TIPO_CICLO) || tipo.equals(Entita.ID_TIPO_FORK))
					gestisciStatoComplessa(e,null,-1,-1);
			}
			//Controllo se al termine dell'inserimento il cammino si trova in uno stato valido. Se non lo e', lo faccio reinserire, stampando a video un opportuno messaggio di errore.
			if(camm.isEmpty())
				camminoValido = false;
			else
				camminoValido = camm.getStato().isValid();
			
			//Controllo se l'insieme del cammino e' un sottoinsieme del cammino globale
			if(!camm.isGlobale()) {
				CamminoAzioni cammGlob = ce.getCamminoGlobale();
				if(camm.inclusoIn(cammGlob) == false)
					sottoinsieme = false;
			}
			
			System.out.println("Stato finale : "+camm.getStato().getStringaStato());
			if(camminoValido  == false)
				System.out.println(String.format(MSG_CAMMINO_NON_VALIDO,MSG_CAMMINO_INCORRETTO));
			else if(sottoinsieme == false)
				System.out.println(String.format(MSG_CAMMINO_NON_VALIDO, MSG_NON_SOTTOINSIEME));
		} while(!camminoValido || !sottoinsieme);
		
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
	private void gestisciStatoAzione(Entita e, Entita esterna, int numRamo, int posizioneRamo) {
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
			else if(camm.getStato().getStringaStato().equalsIgnoreCase(StatoCammino.PERCORSO_PARZ) && esterna != null && posizioneRamo == esterna.getRami()[numRamo].getNumeroEntita()-1) {
				camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_TUTTO);
				Ramo daSettare = esterna.getRami()[numRamo];
				daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.PERCORSO_TUTTO);				
			}
			//Se sono appena entrato in un ramo e inserisco la prima azione, vado in PERCORSO_PARZ e setto anche il relativo ramo a quello stato
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO) && esterna!=null) {
				if(posizioneRamo == esterna.getRami()[numRamo].getNumeroEntita()-1) {
					camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_TUTTO);
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.PERCORSO_TUTTO);
				}
				else if(posizioneRamo == 0) {
					camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_PARZ);
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.PERCORSO_PARZ);
				}
			}			
			//Se mi sono fermato in un ramo e inserisco un'alra entita', allora questo diventa non valido.
			else if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO)) {
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
				if(esterna!=null) {
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.STATO_NON_OK);
				}
			}
			//Se sono in stato NON_PERCORSO (relativamente ad un ramo) ed inserisco un'azione di quel ramo, finisco in stato NON_OK
			else if(camm.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) && esterna != null) {
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
				if(esterna!=null) {
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.STATO_NON_OK);
				}
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.SALTATO_BLOCCO)) {
				camm.getStato().gestisciStato(camm, StatoCammino.STATO_NON_OK);
				if(esterna!=null) {
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.STATO_NON_OK);
				}
			}
			//TODO forse si puo' mettere altrove
		}	
		//Calcolo del nuovo stato nel caso in cui un'azione non viene inserita
		else {
			//Se sono negli stati OK e VUOTO, vado nello stato FERMATO
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_OK))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
			//Se sono nel ramo in posizione 0 e non viene inserita l'azione, allora lo stato del ramo diventa NON_PERCORSO
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO) && esterna!=null && posizioneRamo==0) {
				camm.getStato().gestisciStato(camm, StatoCammino.NON_PERCORSO);
				Ramo daSettare = esterna.getRami()[numRamo];
				daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.NON_PERCORSO);
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ)) {
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO_DENTRO);
				if(esterna!=null) {
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.FERMATO_DENTRO);
				}
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO))
				camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);	
			//Se tutta l'entita' complessa precedente e' stata saltata e non viene aggiunta una nuova azione al di fuori di essa, il cammino diventa FERMATO
			else if(camm.getStato().getStringaStato().equals(StatoCammino.SALTATO_BLOCCO)) {
				if(esterna!=null) {
					camm.getStato().gestisciStato(camm, StatoCammino.FERMATO_DENTRO);
					Ramo daSettare = esterna.getRami()[numRamo];
					daSettare.getStato().gestisciStatoRamo(daSettare, StatoCammino.FERMATO_DENTRO);
				}
				else
					camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
			}
		}
		System.out.println("Stato --> " + camm.getStato().getStringaStato());
	}
	
	/**
	 * Gestisci stato complessa.
	 *
	 * @param e the e
	 */
	private void gestisciStatoComplessa(Entita e,Entita esterna,int numRamoEsterna,int posRamoEsterna) {
		for(int i=0; i<e.getRami().length; i++) {
			if(e.getRami()[i].isEmpty()) {
				e.getRami()[i].setStato(new PercorsoTutto()); 
				camm.getStato().gestisciStato(camm, StatoCammino.PERCORSO_TUTTO);
			}
			else {
				camm.getStato().gestisciStato(camm, StatoCammino.ENTRATO_RAMO);
				e.getRami()[i].setStato(new EntratoRamo());
			}
			//TODO si puo' aggiungere lo stato al costruttore per evitare tutte queste chiamate di metodi
			Ramo ramoCorrente = e.getRami()[i];
			for(int j=0; j<ramoCorrente.getNumeroEntita(); j++) {
				Entita ent = ramoCorrente.getEntitaAt(j);
				if(ent.getIdTipo().equals(Entita.ID_TIPO_AZIONE) || ent.getIdTipo().equals(Entita.ID_TIPO_AZIONE_COMPOSTA)) {
					gestisciStatoAzione(ent, e, i, j);			
				}
				else if(ent.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || ent.getIdTipo().equals(Entita.ID_TIPO_CICLO) || ent.getIdTipo().equals(Entita.ID_TIPO_FORK))					
					gestisciStatoComplessa(ent,e,i,j);     //Gestisco l'entita' complessa ent, con entita' esterna e
			}
		}
		//Controlli effettuati una volta giunti al termine dell'entita
		if(!(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK)))
			if(esterna==null) {
				
				if(!camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK) && (!(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO)) && !(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))))
					if(esterna == null && getRamiPercorsi(e, StatoCammino.NON_PERCORSO) == e.getRami().length)
						camm.getStato().gestisciStato(camm, StatoCammino.FERMATO);
				
				if(e.getIdTipo().equals(Entita.ID_TIPO_FORK)) {
					String state = controllaFork(e).getStringaStato();
					camm.getStato().gestisciStato(camm, state);
				}
				else if(e.getIdTipo().equals(Entita.ID_TIPO_BRANCH))
					camm.getStato().gestisciStato(camm, controllaBranch(e).getStringaStato());
				else if(e.getIdTipo().equals(Entita.ID_TIPO_CICLO))
					camm.getStato().gestisciStato(camm, controllaCiclo(e).getStringaStato());
			}
			else {
				gestisciStatoRami(esterna, e, numRamoEsterna, posRamoEsterna);
				if(!camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK)) {
					camm.getStato().gestisciStato(camm, esterna.getRami()[numRamoEsterna].getStato().getStringaStato());
					System.out.println("Ramo di " + esterna.getNome() + "settato a "+esterna.getRami()[numRamoEsterna].getStato().getStringaStato());
				}
			}
	}
	
	//TODO precondizione (idtipo = fork) e f non nulla
	//Se l'entita' complessa e' un fork, controllo che l'intero blocco di esecuzione sia stato inserito. Se non lo e', il cammino diventa SALTATO_BLOCCO
	private StatoCammino controllaFork(Entita f) {
		if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK))
			return new StatoNonOk();
		else {
			assert f != null && f.getIdTipo().equalsIgnoreCase(Entita.ID_TIPO_FORK);
			
			int ramiPercorsiTutti = getRamiPercorsi(f, StatoCammino.PERCORSO_TUTTO);
			int ramiFermatoDentro = getRamiPercorsi(f, StatoCammino.FERMATO_DENTRO);
			int ramiFork = f.getRami().length;
			System.out.println("Rami percorsi tutti del fork = "+ramiPercorsiTutti);
			//Verifico che ci sia almeno un ramo percorso completamente.
			//Se c'e' almeno un ramo percorso completamente, allora tutti i rami devono essere percorsi completamente.
			if(ramiPercorsiTutti == ramiFork)   //Se tutti i rami sono percorsi completamente allora viene mantenuto lo stato attuale
				return new StatoOk();
			//Se c'e' almeno un ramo percorso tutto ma non tutti i rami sono percorsi tutti, allora lo stato diventa SALTATO_BLOCCO
			else if(ramiPercorsiTutti >= 1 && ramiPercorsiTutti < ramiFork)
				return new SaltatoBlocco();
			//Se il cammino e' globale e c'e' almeno un ramo fermato dentro, allora il nuovo stato e' fermato dentro.
			else if(camm.isGlobale() && ramiFermatoDentro >= 1)
				return new FermatoDentro();
			else if(!camm.isGlobale() && ramiFermatoDentro == 1)
				return new FermatoDentro();
			else if(!camm.isGlobale() && ramiFermatoDentro > 1)
				return new StatoNonOk();
			else
				return new FermatoDentro();
		}
	}
	
	//Se l'entita' complessa e' un branch o un ciclo e il cammino e' globale, controllo che nel caso in cui ci si fermi dentro l'entita' complessa, non vengano inserite azioni successive.
	//Si controlla inoltre che un insieme del cammino non abbia piu' rami di un Branch percorsi completamente.
	private StatoCammino controllaBranch(Entita b) {
		if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK))
			return new StatoNonOk();
		else {
			int ramiPercorsiTutti = getRamiPercorsi(b, StatoCammino.PERCORSO_TUTTO);
			int ramiFermatiDentro = getRamiPercorsi(b, StatoCammino.FERMATO_DENTRO);
			int ramiNonPercorsi = getRamiPercorsi(b, StatoCammino.NON_PERCORSO); 
			boolean fermato = false;
			if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO) || camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
				fermato = true;
				
			if(ramiPercorsiTutti == 0 && ramiFermatiDentro > 0)
				if(!fermato)
					return new FermatoDentro();
				else 
					return new StatoNonOk();
			else if(ramiNonPercorsi == b.getRami().length)
				return new Fermato();
			else if(!camm.isGlobale() && (ramiPercorsiTutti > 1 || ramiFermatiDentro > 1))
				return new StatoNonOk();
		}
		return new StatoOk();
	}
	
	private StatoCammino controllaCiclo(Entita c) {
		Ramo attivitaIniziali = c.getRami()[0];
		Ramo condPermanenza = c.getRami()[1];
		//Se il ramo delle attivita' iniziali non e' inserito o e' inserito solo parzialmente, allora il cammino diventa non valido se l'altro ramo e' stato percorso tutto o in parte
		if(attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) || attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
			if(condPermanenza.getStato().equals(StatoCammino.PERCORSO_TUTTO) || condPermanenza.getStato().equals(StatoCammino.FERMATO_DENTRO))
				return new StatoNonOk();
			else if(attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
				return new Fermato();
			else if(attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
				return new FermatoDentro(); 
		return new StatoOk();	
	}
	
	private void gestisciStatoRami(Entita esterna, Entita interna, int numRamoEsterna, int posRamoEsterna) { 
		Ramo ramoEsterna = esterna.getRami()[numRamoEsterna];
		int ramiPercorsiTutti = getRamiPercorsi(interna, StatoCammino.PERCORSO_TUTTO);
		int ramiFermatoDentro = getRamiPercorsi(interna,StatoCammino.FERMATO_DENTRO);
		int ramiNonPercorsi = getRamiPercorsi(interna,StatoCammino.NON_PERCORSO);
		int ramiVuoti = 0;
		for(int i=0; i<interna.getRami().length; i++)
			if(interna.getRami()[i].isEmpty())
				ramiVuoti++;
		
		String tipoInterna = interna.getIdTipo();
		//Gestisco il caso in cui l'entita' interna sia un Branch
		if(tipoInterna.equals(Entita.ID_TIPO_BRANCH) && camm.isGlobale()) {
			//Se il cammino e' globale e sono stati percorsi totalmente almeno un ramo, allora il ramo esterno viene settato di conseguenza, a seconda della posizione di interna nel ramo.
			if(ramiPercorsiTutti >= 1) {
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO)) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
					System.out.println("Ramo non valido per " + esterna.getNome());
				}
				else if(posRamoEsterna == ramoEsterna.getNumeroEntita()-1) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_TUTTO);
					System.out.println("Percorso tutto il ramo di " + esterna.getNome());
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_PARZ);
					System.out.println("Percorso parz il ramo di " + esterna.getNome());
				}
			}
			else if(ramiPercorsiTutti < 1 && ramiFermatoDentro >= 1) {
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO)) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
					System.out.println("Ramo non valido per " + esterna.getNome());
				}
				else if(posRamoEsterna == 0) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
					System.out.println("Ramo non percorso per "+ esterna.getNome());
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
					System.out.println("Ramo fermato dentro per "+ esterna.getNome());
				}
			}
			else if(ramiNonPercorsi == interna.getRami().length) {
				if(posRamoEsterna == 0) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
					System.out.println("Ramo non percorso per "+ esterna.getNome());
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
					System.out.println("Ramo fermato dentro per "+ esterna.getNome());
				}
			}
		}
		else if(tipoInterna.equalsIgnoreCase(Entita.ID_TIPO_FORK)) {
			int ramiFork = interna.getRami().length;
			//Verifico che ci sia almeno un ramo percorso completamente.
			//Se c'e' almeno un ramo percorso completamente, allora tutti i rami devono essere percorsi completamente.
			if(ramiPercorsiTutti == ramiFork) { 
				if(posRamoEsterna == ramoEsterna.getNumeroEntita()-1) {
					//Se tutti i rami del Fork sono stati percorsi tutti e il ramo dell'entita' esterna e' percorso in parte o si trova in stato Entrato Ramo (in questo caso il Fork e' la prima ma anche l'ultima entita' di quel ramo - ovvero e' l'unica), allora il ramo esterno diviene percorso tutto.
					if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_TUTTO);
					//Se tutti i rami del Fork sono stati percorsi tutti ma il ramo esterno e' in stato Fermato Dentro o Non percorso, allora il ramo esterno diventa non valido.
					else if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
				}
				else if(posRamoEsterna == 0 && ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_PARZ);

				else {
					if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);				
				}
			}
			//Se c'e' almeno un ramo percorso tutto ma non tutti i rami sono percorsi tutti, allora lo stato e' non ok
			else if(ramiPercorsiTutti >= 1 && ramiPercorsiTutti < ramiFork)
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			//Se il cammino e' globale e c'e' almeno un ramo fermato dentro, allora il nuovo stato e' fermato dentro.
			else if(camm.isGlobale() && ramiFermatoDentro >= 1) {
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				else if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			}
			else if(!camm.isGlobale() && ramiFermatoDentro == 1) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				else if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			}
			else if(!camm.isGlobale() && ramiFermatoDentro > 1)
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			else if(ramiNonPercorsi == interna.getRami().length)
				if(posRamoEsterna == 0)
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
				else
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
		}
		else if(tipoInterna.equalsIgnoreCase(Entita.ID_TIPO_BRANCH) && !camm.isGlobale()) {
			System.out.println("breakpoint");
			//Se in un insieme del cammino un branch ha piu' rami percorsi tutti o fermati dentro, allora il cammino diventa non valido.
			if((ramiPercorsiTutti > 1 && ramiVuoti == 0) || ramiFermatoDentro > 1) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
				System.out.println("Ramo non valido per " + esterna.getNome());
			}
			else if((ramiPercorsiTutti == 1 && ramiVuoti == 0) && ramiFermatoDentro > 0) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
				System.out.println("Ramo non valido per " + esterna.getNome());
			}
			else if(ramiFermatoDentro == 1) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				System.out.println("Ramo fermato dentro per " + esterna.getNome());
			}
			else if(ramiPercorsiTutti >=1) {
				if(posRamoEsterna == esterna.getRami()[numRamoEsterna].getNumeroEntita()-1) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_TUTTO);
					System.out.println("Percorso tutto il ramo di " + esterna.getNome());
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_PARZ);
					System.out.println("Percorso tutto il ramo di " + esterna.getNome());
				}
			}
			else if(ramiNonPercorsi == interna.getRami().length && ramiPercorsiTutti == 0 && ramiFermatoDentro == 0){
				if(posRamoEsterna == 0) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
					System.out.println("Ramo non percorso per " + esterna.getNome());
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
					System.out.println("Ramo Fermato dentro per " + esterna.getNome());
				}
			}
		}
		else if(interna.getIdTipo().equals(Entita.ID_TIPO_CICLO)) {	
			Ramo attivitaIniziali = interna.getRami()[0];
			Ramo condPermanenza = interna.getRami()[1];
			//Se il ramo delle attivita' iniziali non e' inserito o e' inserito solo parzialmente, allora il cammino diventa non valido se l'altro ramo e' stato percorso tutto o in parte
			if(attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) || attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO)) {
				//Se non ho percorso completamente il ramo delle attivita' iniziali, non posso aver percorso la condizione di permanenza.
				if(condPermanenza.getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO) || condPermanenza.getStato().equals(StatoCammino.FERMATO_DENTRO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
				else if(attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) || attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
					if(posRamoEsterna == 0)
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
					else
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
			}
			else if(attivitaIniziali.getStato().getStringaStato().equals(StatoCammino.PERCORSO_TUTTO)) {
				if(posRamoEsterna == ramoEsterna.getNumeroEntita()-1) {
					if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
					else if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_TUTTO);
				}
				else if(posRamoEsterna == 0 && ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_PARZ);
				else 
					if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
						ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);					
			}			
		}
	}
	
	private int getRamiPercorsi(Entita e, String stringaStato) {
		int counter = 0;
		for(int i=0; i<e.getRami().length; i++) 
			if(e.getRami()[i].getStato().getStringaStato().equals(stringaStato) || (e.getRami()[i].isEmpty() && stringaStato.equals(StatoCammino.PERCORSO_TUTTO)))
				counter++;
		return counter;
	}
}