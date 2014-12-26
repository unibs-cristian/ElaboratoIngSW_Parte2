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
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	
	/** Messaggi di errore vari */
	public final static String MSG_ERRORE_CAMMINO = "Errore! Il cammino e' vuoto. Inserire nuovamente.";
	public final static String MSG_CAMMINO_NON_VALIDO = "Errore! Cammino non valido perche' %s .Inserire nuovamente.";
	public final static String MSG_CAMMINO_INCORRETTO = "non corretto in base alla struttura del modello";
	public final static String MSG_NON_SOTTOINSIEME = "non e' un sottoinsieme del cammino globale";
	
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
	 * Calcola il nuovo stato in base allo stato attuale, quando l'utente decide di inserire
	 * o meno un'azione.
	 *
	 * @param e : l'azione in questione
	 * @param esterna : l'eventuale entita' complessa di cui fa parte l'azione da inserire.
	 * Se nulla indica che l'azione e' direttamente inserita nel modello.
	 * @param posizioneRamo : l'eventuale numero del ramo dell'entita' complessa in cui si trova l'azione
	 */                                       
	private void gestisciStatoAzione(Entita e, Entita esterna, int numRamo, int posizioneRamo) {
		//Variabili per le precondizioni
		boolean eNull = false;
		boolean esternaNull = false;
		boolean numRamoOk = false; 
		boolean posRamoOk = false;
		if(e == null)
			eNull = true;
		if(esterna == null)
			esternaNull = true;
		if(!esternaNull && numRamo>=0 && numRamo<=esterna.getRami().length-1)
			numRamoOk = true;
		if(!esternaNull && numRamoOk && posizioneRamo>=0 && posizioneRamo<=esterna.getRami()[numRamo].getNumeroEntita()-1)
			posRamoOk = true;
		
		/* 
		 * PRECONDIZIONI
		 * 1) L'entita' e deve essere non nulla 
		 * 2) L'entita' e deve essere un'azione
		 * 3) Se l'entita' esterna non e' nulla, allora gli indici relativi al ramo e alla posizione nel ramo devono essere corretti
		*/
		assert eNull : "Violata precondizione metodo gestisciStatoAzione. Passata azione nulla.";
		assert e.getIdTipo().equals(Entita.ID_TIPO_AZIONE) || e.getIdTipo().equals(Entita.ID_TIPO_AZIONE_COMPOSTA) : "Violata precondizione metodo gestisciStatoAzione. Chiamato metodo con entita' diversa da azione.";
		assert esternaNull || (!esternaNull && numRamoOk && posRamoOk) : "Violata precondizione sul metodo gestisciStatoAzione. Il numero del ramo o la posizione nel ramo non sono corretti.";
		
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
	 * Gestisce lo stato per le entita' complesse.
	 *
	 * @param e : l'entita' complessa di cui gestire lo stato.
	 * @param esterna : l'eventuale entita' complessa di cui fa a sua volta parte l'entita' complessa (livelli di annidamento multipli)
	 * @param numRamoEsterna : Il numero del ramo dell'entita' complessa di cui e fa parte.
	 * @param posRamoEsterna : La posizione di e nel ramo dell'entita' complessa di cui fa parte.
	 */
	private void gestisciStatoComplessa(Entita e,Entita esterna,int numRamoEsterna,int posRamoEsterna) {
		//Variabili per le precondizioni
				boolean eNull = false;
				boolean esternaNull = false;
				boolean numRamoOk = false; 
				boolean posRamoOk = false;
				if(e == null)
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
				 * 2) L'entita' e deve essere un branch, un fork o un ciclo.
				 * 3) Se l'entita' esterna non e' nulla, allora gli indici relativi al ramo e alla posizione nel ramo devono essere corretti
				*/
				assert eNull : "Violata precondizione metodo gestisciStatoComplessa. Passata entita' complessa nulla.";
				assert e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO) || e.getIdTipo().equals(Entita.ID_TIPO_FORK): "Violata precondizione metodo gestisciStatoComplessa. Chiamato metodo con entita' diversa da branch, fork o ciclo.";
				assert esternaNull || (!esternaNull && numRamoOk && posRamoOk) : "Violata precondizione sul metodo gestisciStatoAzione. Il numero del ramo o la posizione nel ramo non sono corretti.";

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
	
	/**
	 * Controlla se le azioni inserite nel cammino sono compatibili con la struttura del Fork
	 * Gestisce lo stato dopo che e' stato completato l'inserimento relativamente ad un 
	 * costrutto Fork-Join.	
	 * @param f : il fork per il quale deve essere gestito lo stato.
	 * @return : il nuovo stato calcolato al termine di un costrutto fork. 
	 */
	private StatoCammino controllaFork(Entita f) {
		//Precondizioni
		assert f!=null : "Violata precondizione metodo controllaFork. Passato fork nullo.";
		assert f.getIdTipo().equals(Entita.ID_TIPO_FORK) : "Violata precondizione metodo controllaFork. Passata entita' diversa da Fork.";
		
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
			//Un insieme del cammino non puo' prevedere piu' rami del Fork percorsi parzialmente
			else if(!camm.isGlobale() && ramiFermatoDentro > 1)
				return new StatoNonOk();
			else
				return new FermatoDentro();
		}
	}
	
	/**
	 * Metodo che controlla se le azioni inserite nel Branch sono compatibili con la struttura del 
	 * branch e gestisce di conseguenza lo stato al termine di quest'ultimo.
	 * @param b : il branch per cui controllare e gestire lo stato 
	 * @return : il nuovo stato calcolato in base all'analisi del Branch
	 */
	private StatoCammino controllaBranch(Entita b) {
		//Precondizioni
		assert b!=null : "Violata precondizione metodo controllaBranch. Passato branch nullo.";
		assert b.getIdTipo().equals(Entita.ID_TIPO_BRANCH) : "Violata precondizione metodo controllaBranch. Passata entita' diversa da Branch";
		
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
	
	/**
	 * Metodo che controlla se le azioni inserite nel Ciclo sono compatibili con la struttura del 
	 * Ciclo stesso. Inoltre si gestisce di conseguenza lo stato del cammino al termine di 
	 * quest'ultimo.
	 * @param c : il ciclo per cui controllare e gestire lo stato 
	 * @return : il nuovo stato calcolato in base all'analisi del Ciclo
	 */
	private StatoCammino controllaCiclo(Entita c) {
		//Precondizioni
		assert c!=null : "Violata precondizione metodo controllaBranch. Passato branch nullo.";
		assert c.getIdTipo().equals(Entita.ID_TIPO_BRANCH) : "Violata precondizione metodo controllaBranch. Passata entita' diversa da Branch";

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
		/*
		 * PRECONDIZIONI
		 * 1) e non deve essere nulla
		 * 2) e deve essere una entita' complessa (fork,ciclo o branch)
		 * 3) la stringa dello stato non deve essere nullo
		 */
		assert e!=null : "Violata precondizione metodo getRamiPercorsi. Passata entita' nulla";
		assert e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO) || e.getIdTipo().equals(Entita.ID_TIPO_FORK) : "Violata precondizione metodo getRamiPercorsi. Passata entita' diversa da fork, branch o ciclo.";
		assert stringaStato!=null : "Violata precondizione metodo getRamiPercorsi. Passata stringa stato nulla";
		
		int counter = 0;
		for(int i=0; i<e.getRami().length; i++) 
			if(e.getRami()[i].getStato().getStringaStato().equals(stringaStato) || (e.getRami()[i].isEmpty() && stringaStato.equals(StatoCammino.PERCORSO_TUTTO)))
				counter++;
		return counter;
	}
}