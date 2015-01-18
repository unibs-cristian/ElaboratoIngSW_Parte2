/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package inputDati;

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
import utilita.Util;
import gestioneModello.Azione;
import gestioneModello.Modello;
import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;
import gestioneTS.ClasseEquivalenza;
import gestioneTS.Coppia;

/**
 * Classe InserimentoCammino.
 * Questa classe gestisce l'interazione con l'utente quando questo deve inserire un cammino
 * globale oppure un insieme del cammino. Vengono inoltre  inoltre gestito lo stato in base a 
 * quanto è stato inserito dall'utente.
 * 
 */
public class InserimentoCammino {
	
	/** Costanti stringa per il cammino globale */
	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	public final static String MSG_CAMM_GLOB = "Cammino Globale --> ";
	
	/** Costanti stringa per l'insieme del cammino */
	public final static String MSG_INS_CAMMINO = "Scegliere le azioni da aggiungere all'insieme del cammino";
	public final static String MSG_INS_CAMM = "Insieme del Cammino --> ";
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza.";
	
	/** Messaggi di errore vari */
	public final static String MSG_ERRORE_CAMMINO = "Errore! Il cammino e' vuoto. Inserire nuovamente.";
	public final static String MSG_ERRORE_CO = "Errore! E' gia' presente nella classe di equivalenza una coppia uguale. Ripetere l'inserimento.";
	
	/** Classe di equivalenza per la quale si vuole inserire il cammino globale o l'insieme del cammino */
	private ClasseEquivalenza ce;
	
	/** Il cammino contenente le azioni */
	private CamminoAzioni camm;
	
	/**
	 * Costruttore della classe InserimentoCammino
	 *
	 * @param _ce : la classe di equivalenza per la quale si vuole fare l'inserimento.
	 * @param _camm : il cammino contenente le azioni.
	 */
	public InserimentoCammino(ClasseEquivalenza _ce,CamminoAzioni _camm) {
		ce = _ce;
		camm = _camm;
	}
	
	/**
	 * Gestisce l'inserimento del cammino
	 */
	public void inserisciCamm() {
		if(camm.globaleSiNo())
			System.out.println(MSG_CAMM_GLOBALE_1);
		else
			System.out.println(MSG_INS_CAMMINO);
		do {
			//Toglie le eventuali azioni presenti nel cammino (utile per resettare il Vector di Azioni quando si è inserito un cammino errato.
			if(!(camm.isEmpty()))
				camm.azzeraAzioni();		
			//Ripristina lo stato iniziale a vuoto se non lo e' (puo' accadere per esempio dopo che ho inserito un cammino non valido)
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO) == false)
				camm.setStatoCammino(new StatoVuoto());
			
			//Inserimento azioni e gestione stato del cammino. 
			for(int i=1; i<Modello.getInstance().getEntita().size()-1; i++) {
				if(Modello.getInstance().getEntita().get(i).isAzione())
					gestisciStatoAzione(Modello.getInstance().getEntita().get(i), null, -1, -1);
				else if(Modello.getInstance().getEntita().get(i).isComplessa())
					gestisciStatoComplessa(Modello.getInstance().getEntita().get(i),null,-1,-1);
			}
		} while(!camm.isValid(ce));
		
		visualizzaCammino();
		
		if(camm.globaleSiNo())
			ce.setCamminoGlobale(camm);
		// Se e' un insieme del cammino, viene richiesto anche il valore della rilevazione
		else {
			String valoreRilevazione = Util.okOrKo(MSG_VAL_RILEV);
			Coppia nuova = new Coppia(camm, valoreRilevazione);
			if(!ce.coppiaGiaPresente(nuova)) {
				ce.addCoppia(nuova);
				System.out.println(String.format(MSG_COPPIA_AGGIUNTA));
			}
			else
				System.out.println(MSG_ERRORE_CO);
			}
	}
	
	private void visualizzaCammino() {
		if(camm.globaleSiNo())
			System.out.println(MSG_CAMM_GLOB + camm.toString());
		else
			System.out.println(MSG_INS_CAMM + camm.toString());	
	}
	
	private String getRichiestaInserimento() {
		if(camm.globaleSiNo())
			return MSG_AGGIUNTA_CAMM_GLOBALE;
		else
			return MSG_AGGIUNTA_INS_CAMM;
	}
	
	/**
	 * Calcola il nuovo stato in base allo stato attuale, quando l'utente decide di inserire
	 * o meno un'azione.
	 *
	 * @param azione : l'azione in questione
	 * @param esterna : l'eventuale entita' complessa di cui fa parte l'azione da inserire.
	 * Se nulla indica che l'azione e' direttamente inserita nel modello.
	 * @param numRamoEsterna : il numero del ramo dell'entita' esterna
	 * @param posizioneRamo : l'eventuale numero del ramo dell'entita' complessa in cui si trova l'azione
	 */                                       
	private void gestisciStatoAzione(Entita azione, Entita esterna, int numRamoEsterna, int posizioneRamo) {
		verificaPrecondizioni6(azione, esterna, numRamoEsterna, posizioneRamo);
		
		//Calcolo del nuovo stato nel caso in cui un'azione viene inserita		
		if(Util.yesOrNo(String.format(getRichiestaInserimento(),azione.getNome()))) {
			camm.aggiungiAzione((Azione)azione);
			//Se lo stato era VUOTO, il nuovo stato sara' OK
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO))
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.STATO_OK);
			//Se lo stato era FERMATO DENTRO, il nuovo stato sara' NON_OK
			else if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO))
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.STATO_NON_OK);
			else if(camm.getStato().getStringaStato().equalsIgnoreCase(StatoCammino.PERCORSO_PARZ) && esterna != null && posizioneRamo == esterna.getRami()[numRamoEsterna].getNumeroEntita()-1) {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.PERCORSO_TUTTO);
				esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.PERCORSO_TUTTO);				
			}
			//Se sono appena entrato in un ramo e inserisco la prima azione, vado in PERCORSO_PARZ e setto anche il relativo ramo a quello stato
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO) && esterna!=null) {
				if(posizioneRamo == esterna.getRami()[numRamoEsterna].getNumeroEntita()-1) {
					camm.getStato().gestisciStatoCammino(camm, StatoCammino.PERCORSO_TUTTO);
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.PERCORSO_TUTTO);
				}
				else if(posizioneRamo == 0) {
					camm.getStato().gestisciStatoCammino(camm, StatoCammino.PERCORSO_PARZ);
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.PERCORSO_PARZ);
				}
			}			
			//Se mi sono fermato in un ramo e inserisco un'alra entita', allora questo diventa non valido.
			else if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO)) {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.STATO_NON_OK);
				if(esterna!=null)
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.STATO_NON_OK);
			}
			//Se sono in stato NON_PERCORSO (relativamente ad un ramo) ed inserisco un'azione di quel ramo, finisco in stato NON_OK
			else if(camm.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) && esterna != null) {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.STATO_NON_OK);
				if(esterna!=null) {
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.STATO_NON_OK);
				}
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.SALTATO_BLOCCO)) {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.STATO_NON_OK);
				if(esterna!=null)
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.STATO_NON_OK);
			}
		}	
		//Calcolo del nuovo stato nel caso in cui un'azione non viene inserita
		else {
			//Se sono negli stati OK e VUOTO, vado nello stato FERMATO
			if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_OK))
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.FERMATO);
			//Se sono nel ramo in posizione 0 e non viene inserita l'azione, allora lo stato del ramo diventa NON_PERCORSO
			else if(camm.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO) && esterna!=null && posizioneRamo==0) {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.NON_PERCORSO);
				esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.NON_PERCORSO);
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ)) {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.FERMATO_DENTRO);
				if(esterna!=null)
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.FERMATO_DENTRO);
			}
			else if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_VUOTO))
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.FERMATO);	
			//Se tutta l'entita' complessa precedente e' stata saltata e non viene aggiunta una nuova azione al di fuori di essa, il cammino diventa FERMATO
			else if(camm.getStato().getStringaStato().equals(StatoCammino.SALTATO_BLOCCO)) {
				if(esterna!=null) {
					camm.getStato().gestisciStatoCammino(camm, StatoCammino.FERMATO_DENTRO);
					esterna.getRami()[numRamoEsterna].getStato().gestisciStatoRamo(esterna.getRami()[numRamoEsterna], StatoCammino.FERMATO_DENTRO);
				}
				else
					camm.getStato().gestisciStatoCammino(camm, StatoCammino.FERMATO);
			}
		}
	}
	
	/**
	 * Gestisce lo stato per le entita' complesse.
	 *
	 * @param attuale : l'entita' complessa di cui gestire lo stato.
	 * @param esterna : l'eventuale entita' complessa di cui fa a sua volta parte l'entita' complessa (livelli di annidamento multipli)
	 * @param numRamoEsterna : Il numero del ramo dell'entita' complessa di cui e fa parte.
	 * @param posRamoEsterna : La posizione di e nel ramo dell'entita' complessa di cui fa parte.
	 */
	private void gestisciStatoComplessa(Entita attuale,Entita esterna,int numRamoEsterna,int posRamoEsterna) {
		verificaPrecondizioni2(attuale, esterna, numRamoEsterna, posRamoEsterna);
		
		for(int i=0; i<attuale.getRami().length; i++) {
			if(attuale.getRami()[i].isEmpty()) {
				attuale.getRami()[i].setStato(new PercorsoTutto()); 
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.PERCORSO_TUTTO);
			}
			else {
				camm.getStato().gestisciStatoCammino(camm, StatoCammino.ENTRATO_RAMO);
				attuale.getRami()[i].setStato(new EntratoRamo());
			}
			for(int j=0; j<attuale.getRami()[i].getNumeroEntita(); j++) {
				if(attuale.getRami()[i].getEntitaAt(j).isAzione()) {
					gestisciStatoAzione(attuale.getRami()[i].getEntitaAt(j), attuale, i, j);			
				}
				else if(attuale.getRami()[i].getEntitaAt(j).isComplessa())					
					gestisciStatoComplessa(attuale.getRami()[i].getEntitaAt(j),attuale,i,j);     //Gestisco l'entita' complessa ent, con entita' esterna e
			}
		}
		//Controlli effettuati una volta giunti al termine dell'entita
		if(!(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK)))
			if(esterna==null) {
				
				if(!camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK) && (!(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO)) && !(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))))
					if(esterna == null && getRamiPercorsi(attuale, StatoCammino.NON_PERCORSO) == attuale.getRami().length)
						camm.getStato().gestisciStatoCammino(camm, StatoCammino.FERMATO);
				
				if(attuale.getIdTipo().equals(Entita.ID_TIPO_FORK)) {
					camm.getStato().gestisciStatoCammino(camm, controllaFork(attuale).getStringaStato());
				}
				else if(attuale.getIdTipo().equals(Entita.ID_TIPO_BRANCH))
					camm.getStato().gestisciStatoCammino(camm, controllaBranch(attuale).getStringaStato());
				else if(attuale.getIdTipo().equals(Entita.ID_TIPO_CICLO))
					camm.getStato().gestisciStatoCammino(camm, controllaCiclo(attuale).getStringaStato());
			}
			else {
				gestisciStatoRami(esterna, attuale, numRamoEsterna, posRamoEsterna);
				if(!camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK)) {
					camm.getStato().gestisciStatoCammino(camm, esterna.getRami()[numRamoEsterna].getStato().getStringaStato());
				}
			}
	}
	
	/**
	 * Controlla se le azioni inserite nel cammino sono compatibili con la struttura del Fork
	 * Gestisce lo stato dopo che e' stato completato l'inserimento relativamente ad un 
	 * costrutto Fork-Join.	
	 * @param daControllare : il fork per il quale deve essere gestito lo stato.
	 * @return : il nuovo stato calcolato al termine di un costrutto fork. 
	 */
	private StatoCammino controllaFork(Entita daControllare) {
		verificaPrecondizioni5(daControllare);
				
		if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK))
			return new StatoNonOk();
		else {
			assert daControllare != null && daControllare.getIdTipo().equalsIgnoreCase(Entita.ID_TIPO_FORK);
			
			int ramiPercorsiTutti = getRamiPercorsi(daControllare, StatoCammino.PERCORSO_TUTTO);
			int ramiFermatoDentro = getRamiPercorsi(daControllare, StatoCammino.FERMATO_DENTRO);
			int ramiFork = daControllare.getRami().length;
			//Verifico che ci sia almeno un ramo percorso completamente.
			//Se c'e' almeno un ramo percorso completamente, allora tutti i rami devono essere percorsi completamente.
			if(ramiPercorsiTutti == ramiFork)   //Se tutti i rami sono percorsi completamente allora viene mantenuto lo stato attuale
				return new StatoOk();
			//Se c'e' almeno un ramo percorso tutto ma non tutti i rami sono percorsi tutti, allora lo stato diventa SALTATO_BLOCCO
			else if(ramiPercorsiTutti >= 1 && ramiPercorsiTutti < ramiFork)
				return new SaltatoBlocco();
			//Se il cammino e' globale e c'e' almeno un ramo fermato dentro, allora il nuovo stato e' fermato dentro.
			else if(camm.globaleSiNo() && ramiFermatoDentro >= 1)
				return new FermatoDentro();
			else if(!camm.globaleSiNo() && ramiFermatoDentro == 1)
				return new FermatoDentro();
			//Un insieme del cammino non puo' prevedere piu' rami del Fork percorsi parzialmente
			else if(!camm.globaleSiNo() && ramiFermatoDentro > 1)
				return new StatoNonOk();
			else
				return new FermatoDentro();
		}
	}
	
	/**
	 * Metodo che controlla se le azioni inserite nel Branch sono compatibili con la struttura del 
	 * branch e gestisce di conseguenza lo stato al termine di quest'ultimo.
	 * @param daControllare : il branch per cui controllare e gestire lo stato 
	 * @return : il nuovo stato calcolato in base all'analisi del Branch
	 */
	private StatoCammino controllaBranch(Entita daControllare) {
		verificaPrecondizioni4(daControllare);
				
		if(camm.getStato().getStringaStato().equals(StatoCammino.STATO_NON_OK))
			return new StatoNonOk();
		else {
			int ramiPercorsiTutti = getRamiPercorsi(daControllare, StatoCammino.PERCORSO_TUTTO);
			int ramiFermatiDentro = getRamiPercorsi(daControllare, StatoCammino.FERMATO_DENTRO);
			int ramiNonPercorsi = getRamiPercorsi(daControllare, StatoCammino.NON_PERCORSO); 
			boolean fermato = false;
			if(camm.getStato().getStringaStato().equals(StatoCammino.FERMATO) || camm.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
				fermato = true;
				
			if(ramiPercorsiTutti == 0 && ramiFermatiDentro > 0)
				if(!fermato)
					return new FermatoDentro();
				else 
					return new StatoNonOk();
			else if(ramiNonPercorsi == daControllare.getRami().length)
				return new Fermato();
			else if(!camm.globaleSiNo() && (ramiPercorsiTutti > 1 || ramiFermatiDentro > 1))
				return new StatoNonOk();
		}
		return new StatoOk();
	}
	
	/**
	 * Metodo che controlla se le azioni inserite nel Ciclo sono compatibili con la struttura del 
	 * Ciclo stesso. Inoltre si gestisce di conseguenza lo stato del cammino al termine di 
	 * quest'ultimo.
	 * @param daControllare : il ciclo per cui controllare e gestire lo stato 
	 * @return : il nuovo stato calcolato in base all'analisi del Ciclo
	 */
	private StatoCammino controllaCiclo(Entita daControllare) {
		verificaPrecondizioni3(daControllare);

		//Se il ramo delle attivita' iniziali non e' inserito o e' inserito solo parzialmente, allora il cammino diventa non valido se l'altro ramo e' stato percorso tutto o in parte
		if(daControllare.getRami()[0].getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO) || daControllare.getRami()[0].getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
			if(daControllare.getRami()[1].getStato().equals(StatoCammino.PERCORSO_TUTTO) || daControllare.getRami()[1].getStato().equals(StatoCammino.FERMATO_DENTRO))
				return new StatoNonOk();
			else if(daControllare.getRami()[0].getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
				return new Fermato();
			else if(daControllare.getRami()[0].getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO))
				return new FermatoDentro(); 
		return new StatoOk();	
	}
	
	/**
	 * Metodo che gestisce lo stato dei singoli rami delle entita' complesse nel caso di livelli di
	 * annidamento multipli. Al termine dell'inserimento di un'entita' complessa, viene calcolato e
	 * gestito lo stato del cammino e viene settato di conseguenza lo stato del ramo dell'entita' 
	 * complessa di cui l'entita' fa parte.
	 *  
	 * @param esterna : l'entita' complessa di cui l'entita' fa parte
	 * @param interna : l'entita' complessa in base alla quale calcolare il nuovo stato.
	 * @param numRamoEsterna : il numero di ramo dell'entita' complessa esterna in cui si trova
	 * l'entita' interna.
	 * @param posRamoEsterna : la posizione di interna nel ramo dell'entita' esterna
	 */
	private void gestisciStatoRami(Entita esterna, Entita interna, int numRamoEsterna, int posRamoEsterna) { 
		verificaPrecondizioni2(interna, esterna, numRamoEsterna, posRamoEsterna);
			
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
		if(tipoInterna.equals(Entita.ID_TIPO_BRANCH) && camm.globaleSiNo()) {
			//Se il cammino e' globale e sono stati percorsi totalmente almeno un ramo, allora il ramo esterno viene settato di conseguenza, a seconda della posizione di interna nel ramo.
			if(ramiPercorsiTutti >= 1) {
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO)) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
				}
				else if(posRamoEsterna == ramoEsterna.getNumeroEntita()-1) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_TUTTO);
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_PARZ);
				}
			}
			else if(ramiPercorsiTutti < 1 && ramiFermatoDentro >= 1) {
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO)) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
				}
				else if(posRamoEsterna == 0) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				}
			}
			else if(ramiNonPercorsi == interna.getRami().length) {
				if(posRamoEsterna == 0) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
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
			else if(camm.globaleSiNo() && ramiFermatoDentro >= 1) {
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				else if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			}
			else if(!camm.globaleSiNo() && ramiFermatoDentro == 1) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.PERCORSO_PARZ) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.ENTRATO_RAMO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
				else if(ramoEsterna.getStato().getStringaStato().equals(StatoCammino.FERMATO_DENTRO) || ramoEsterna.getStato().getStringaStato().equals(StatoCammino.NON_PERCORSO))
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			}
			else if(!camm.globaleSiNo() && ramiFermatoDentro > 1)
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			else if(ramiNonPercorsi == interna.getRami().length)
				if(posRamoEsterna == 0)
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
				else
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
		}
		else if(tipoInterna.equalsIgnoreCase(Entita.ID_TIPO_BRANCH) && !camm.globaleSiNo()) {
			//Se in un insieme del cammino un branch ha piu' rami percorsi tutti o fermati dentro, allora il cammino diventa non valido.
			if((ramiPercorsiTutti > 1 && ramiVuoti == 0) || ramiFermatoDentro > 1) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			}
			else if((ramiPercorsiTutti == 1 && ramiVuoti == 0) && ramiFermatoDentro > 0) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.STATO_NON_OK);
			}
			else if(ramiFermatoDentro == 1) {
				ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
			}
			else if(ramiPercorsiTutti >=1) {
				if(posRamoEsterna == esterna.getRami()[numRamoEsterna].getNumeroEntita()-1) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_TUTTO);
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.PERCORSO_PARZ);
				}
			}
			else if(ramiNonPercorsi == interna.getRami().length && ramiPercorsiTutti == 0 && ramiFermatoDentro == 0){
				if(posRamoEsterna == 0) {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.NON_PERCORSO);
				}
				else {
					ramoEsterna.getStato().gestisciStatoRamo(ramoEsterna, StatoCammino.FERMATO_DENTRO);
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
	
	/**
	 * Metodo ausiliario che serve per farsi restituire il numero di rami di un'entita' complessa
	 * aventi un certo stato. Puo' essere utile per verificare con facilita' se, ad esempio, e' 
	 * stato inserito interamente un blocco di esecuzione di un Fork.
	 * 
	 * @param e : l'entita' complessa per cui calcolare il numero di rami aventi un certo stato
	 * @param stringaStato : lo stato dei rami che interessa
	 * @return : il numero di rami aventi come stato stringaStato
	 */
	private int getRamiPercorsi(Entita e, String stringaStato) {
		verificaPrecondizioni1(e,stringaStato);
		
		int counter = 0;
		for(int i=0; i<e.getRami().length; i++) 
			if(e.getRami()[i].getStato().getStringaStato().equals(stringaStato) || (e.getRami()[i].isEmpty() && stringaStato.equals(StatoCammino.PERCORSO_TUTTO)))
				counter++;
		return counter;
	}
	
	private boolean isComplessa(Entita daVerificare) {
		return daVerificare.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || daVerificare.getIdTipo().equals(Entita.ID_TIPO_CICLO) || daVerificare.getIdTipo().equals(Entita.ID_TIPO_FORK);
	}
	
	private boolean isCiclo(Entita daControllare) {
		return daControllare.getIdTipo().equals(Entita.ID_TIPO_CICLO);
	}
	
	private boolean isBranch(Entita daControllare) {
		return daControllare.getIdTipo().equals(Entita.ID_TIPO_BRANCH);
	}
	
	private boolean isFork(Entita daControllare) {
		return daControllare.getIdTipo().equals(Entita.ID_TIPO_FORK);
	}
	
	private boolean isAzione(Entita daControllare) {
		return daControllare.getIdTipo().equals(Entita.ID_TIPO_AZIONE) || daControllare.getIdTipo().equals(Entita.ID_TIPO_AZIONE_COMPOSTA);
	}
	
	private void verificaPrecondizioni1(Entita e, String stringaStato) {
		/*
		 * PRECONDIZIONI
		 * 1) e non deve essere nulla
		 * 2) e deve essere una entita' complessa (fork,ciclo o branch)
		 * 3) la stringa dello stato non deve essere nullo
		 */
		
		assert e!=null : "Violata precondizione metodo getRamiPercorsi. Passata entita' nulla";
		assert isComplessa(e) : "Violata precondizione metodo getRamiPercorsi. Passata entita' diversa da fork, branch o ciclo.";
		assert stringaStato!=null : "Violata precondizione metodo getRamiPercorsi. Passata stringa stato nulla";
	}
	
	private void verificaPrecondizioni2(Entita interna, Entita esterna, int numRamoEsterna, int posRamoEsterna) {
		/*
		 * PRECONDIZIONI 
		 * 1) esterna e interna non devono essere nulle
		 * 2) il numero di ramo e la posizione nel ramo devono essere corretti.
		 */
		boolean numRamoOk = false; 
		boolean posRamoOk = false;
		if(numRamoEsterna>=0 && numRamoEsterna<=esterna.getRami().length-1)
			numRamoOk = true;
		if(numRamoOk && posRamoEsterna>=0 && posRamoEsterna<=esterna.getRami()[numRamoEsterna].getNumeroEntita()-1)
			posRamoOk = true;
	
		assert interna != null : "Violata precondizione metodo gestisciStatoRami. Passata entita' interna null";
		assert esterna != null : "Violata precondizione metodo gestisciStatoRami. Passata entita' esterna null";
		assert numRamoOk : "Violata precondizione metodo gestisciStatoRami. Numero ramo esterna non corretto";
		assert posRamoOk : "Violata precondizione metodo gestisciStatoRami. Posizione ramo esterna non corretta";
	}
	
	private void verificaPrecondizioni3(Entita daControllare) {
		//Precondizioni
		assert daControllare!=null : "Violata precondizione metodo controllaCiclo. Passato ciclo nullo.";
		assert isCiclo(daControllare) : "Violata precondizione metodo controllaCiclo. Passata entita' diversa da Ciclo";

	}
	
	private void verificaPrecondizioni4(Entita daControllare) {
		//Precondizioni
		assert daControllare!=null : "Violata precondizione metodo controllaBranch. Passato branch nullo.";
		assert isBranch(daControllare) : "Violata precondizione metodo controllaBranch. Passata entita' diversa da Branch";
	}
	
	private void verificaPrecondizioni5(Entita daControllare) {
		//Precondizioni
		assert daControllare!=null : "Violata precondizione metodo controllaFork. Passato fork nullo.";
		assert isFork(daControllare) : "Violata precondizione metodo controllaFork. Passata entita' diversa da Fork.";
	}
	
	private void verificaPrecondizioni6(Entita interna, Entita esterna, int numRamoEsterna, int posRamoEsterna) {
		// Variabili per le precondizioni
		boolean eNull = false;
		boolean esternaNull = false;
		boolean numRamoOk = false; 
		boolean posRamoOk = false;
		if(interna == null)
			eNull = true;
		if(esterna == null)
			esternaNull = true;
		if(!esternaNull && numRamoEsterna>=0 && numRamoEsterna<=esterna.getRami().length-1)
			numRamoOk = true;
		if(!esternaNull && numRamoOk && posRamoEsterna>=0 && posRamoEsterna<=esterna.getRami()[numRamoEsterna].getNumeroEntita()-1)
			posRamoOk = true;
				
		/* 
		 * PRECONDIZIONI
		 * 1) L'entita' e deve essere non nulla 
		 * 2) L'entita' e deve essere un'azione
		 * 3) Se l'entita' esterna non e' nulla, allora gli indici relativi al ramo e alla posizione nel ramo devono essere corretti
		 */
		assert eNull : "Violata precondizione metodo gestisciStatoAzione. Passata azione nulla.";
		assert isAzione(interna) : "Violata precondizione metodo gestisciStatoAzione. Chiamato metodo con entita' diversa da azione.";
		assert esternaNull || (!esternaNull && numRamoOk && posRamoOk) : "Violata precondizione sul metodo gestisciStatoAzione. Il numero del ramo o la posizione nel ramo non sono corretti.";
	}
}
