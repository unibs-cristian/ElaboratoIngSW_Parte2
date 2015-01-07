package inputDati;

import gestioneModello.Modello;
import gestioneModello.NodoIniziale;
import gestioneReport.Report;
import gestioneTS.Diagnosi;
import gestioneTS.TestSuite;

import java.io.File;
import java.util.Vector;

import statisticheGuasti.OrdinaElencoProbabilitaEIntervalliPosizione;
import statisticheGuasti.ProbabilitaMetodo1;
import statisticheGuasti.ProbabilitaMetodo2;
import utilita.Menu;
import utilita.Stream;
import utilita.Util;

public class MenuPrincipale { 

	/** Costanti stringa che rappresentano le opzioni del menu' principale */
	public final static String MSG_NUOVO_MODELLO = " 1 - Crea Nuovo Modello";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = " 2 - Visualizza Modello";
	public final static String MSG_INS_TEST_SUITE = " 3 - Inserimento Test Suite";
	public final static String MSG_VISUALIZZAZIONE_DIAGNOSI = " 4 - Visualizza Insiemi delle Diagnosi";
	public final static String MSG_VISUALIZZAZIONE_PROBABILITA = " 5 - Visualizza Probabilita' e Distanze";
	public final static String MSG_CREAZIONE_REPORT = " 6 - Creazione Report Completo";
	public final static String MSG_VISUALIZZAZIONE_REPORT = " 7 - Visualizza Report";
	public final static String MSG_CARICAMENTO = " 8 - Carica";
	public final static String MSG_SALVATAGGIO = " 9 - Salva";
	public final static String MSG_USCITA_PROGRAMMA = "10 - Esci dal programma.";
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	
	/** Costanti stringa per l'opzione crea nuovo modello. */
	public final static String MSG_MODELLO_ESISTENTE = "Esiste gia' un modello. Si desidera inserire comunque un nuovo modello?";
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";

	/** Costanti stringa per l'opzione visualizza modello corrente. */
	public final static String MSG_NO_MODELLO = "Errore! Nessun modello inserito.";
	public final static String MSG_STAMPA_MOD_CORRENTE = "STAMPA DEL MODELLO CORRENTE\n";
		
	/** Costanti stringa per l'opzione inserisci ts. */
	public final static String MSG_SOVRASCRIVI_TS = "Attenzione, esiste gia' un Test Suite inserito. Si desidera abbandonare tale Test Suite e lavorare su quello nuovo?";
	public final static String MSG_INSERIMENTO_TS_ANNULLATO = "Inserimento Test Suite annulato.";
	public final static String MSG_TS = "\n\nCREAZIONE DEL TEST SUITE RELATIVO AL MODELLO %s\n\n";
	public final static String MSG_SALVATAGGIO_TS = "Si desidera salvare il Test Suite inserito?";
	public final static String MSG_NOME_TS = "Che nome si desidera dare al Test Suite?";
	public final static String MSG_SINTESI_TS = "Si desidera vedere una sintesi delle classi di equivalenza e degli insiemi di copertura inseriti\nper il TS corrente?";
	
	/** Costante stringa per l'opzione visualizza diagnosi. */
	public final static String MSG_NO_TS = "Errore! Nessun Test Suite inserito.";

	/** Costante stringa per i metodi che operano sui report. */
	public final static String MSG_ERRORE_REPORT_1 = "Attenzione! Per creare un report deve essere inserito un modello, un test suite e un insieme delle diagnosi minimali.";
	public final static String MSG_ERRORE_REPORT_2 = "Attenzione! Per creare un report e' necessario associare al Test Suite un insieme delle diagnosi minimali e aver caricato il modello corrispondente\na quello del Test Suite.";
	public final static String MSG_SOVRASCRIVI_REPORT = "Attenzione, esiste gia' un Report inserito. Si desidera sostituirlo?";
	public final static String MSG_REPORT_INESISTENTE = "Errore. Nessun Report inserito";
	public final static String MSG_INSERIMENTO_REPORT_ANNULLATO = "Inserimento Report annulato.";
	public final static String MSG_SALVATAGGIO_REPORT = "Si desidera salvare il Report?";
	public final static String MSG_SALVATAGGIO_TESTO_REPORT = "Si desidera salvare il Report in un file di testo?";
	public final static String MSG_VISUALIZZA_REPORT = "Si desidera visualizzare il report inserito?";
	public final static String MSG_REPORT_SALVATO_TESTO = "Il Report e' stato salvato correttamente nel file di testo %s";
	public final static String MSG_REPORT_SALVATO = "Il Report e' stato salvato correttamente nel file %s";
	
	/** Costanti stringa per l'opzione caricamento */
	public final static String MSG_TITOLO_MENU_CARICAMENTO = "MENU' GESTIONE CARICAMENTO\n\nCosa si desidera fare?";
	public static final String MSG_CARICA_MODELLO = "1 - Carica un modello esistente";
	public static final String MSG_CARICA_TS = "2 - Carica un test suite esistente";
	public static final String MSG_CARICA_REPORT = "3 - Carica un report esistente";
	public static final String MSG_ESCI = "4 - Ritorna al menu' principale";
	public static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare: ";
	public static final String MSG_SOVRASCRIVI_MODELLO = "Attenzione, esiste gia' un modello inserito. Si desidera abbandonare tale modello e lavorare su quello caricato?";
	public static final String MSG_CARICAMENTO_ANNULLATO = "Caricamento annullato.";
	public final static String MSG_MODELLO_CARICATO = "Il modello %s e' stato caricato con successo.";
	public static final String MSG_NOME_TS_PREESISTENTE = "Nome Test Suite da caricare: ";
	public final static String MSG_CARICAMENTO_OK = "Caricamento completato correttamente.";
	public final static String MSG_ERRORE_MODELLO_TS = "Attenzione, ci sono problemi con il file caricato, che potrebbe essere corrotto.";
	public static final String MSG_MODELLO_TS_NON_OK = "Attenzione, il Test Suite caricato si riferisce ad un modello diverso da quello presente nel sistema.\nVerra' caricato il modello corretto per poter eseguire correttamente diagnosi e probabilita'";
	public final static String MSG_NOME_REPORT_PREESISTENTE = "Nome Report da caricare: ";
	public final static String MSG_REPORT_CARICATO = "Il report %s e' stato caricato con successo.";
	public final static String MSG_SEGNALAZIONE_REPORT = "Attenzione! Il Report caricato si riferisce ad un Test Suite o ad un Modello diverso da quelli\nattualmente caricati.";
	public final static String MSG_VISUALIZZA_REPORT_CARICATO = "Si desidera visualizzare il report caricato?";
	public final static String MSG_ERRORE_CAST = "ERRORE! FILE CON CAST NON CORRISPONDENTE.";
	
	/** Costanti stringa per il salvataggio */
	public final static String MSG_TITOLO_MENU_SALVATAGGIO = "MENU' GESTIONE SALVATAGGIO\n\nCosa si desidera fare?";
	public final static String MSG_SALVATAGGIO_OK = "Il file e' stato salvato con successo";
	public static final String MSG_SALVA_MODELLO = "1 - Salva Modello";
	public static final String MSG_SALVA_TS = "2 - Salva Test Suite";
	public static final String MSG_SALVA_REPORT = "3 - Salva report completo";
	public static final String MSG_ESCI_1 = "4 - Ritorna al menu' principale";
	public static final String MSG_NOME_MODELLO_SALVA = "Che nome si vuole dare al modello da salvare?";
	public final static String MSG_NO_REPORT = "Errore! Nessun Report inserito.";
	public final static String MSG_NOME_REPORT = "Che nome si desidera dare al Report?";

	/** Saluto finale. */
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! Arrivederci.\n";

	
	private String titolo;
	
	public MenuPrincipale(String _titolo) {
		titolo = _titolo;
	}
	
	public void avvia() {
		this.crea();
	}
	
	private void crea() {
		this.gestisci(new Menu(titolo, ottieniVoci()));
	}
	
	private Vector <String> ottieniVoci() {
		Vector<String> vociMenuPrincipale = new Vector<String>();
		vociMenuPrincipale.add(MSG_NUOVO_MODELLO);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_MODELLO);
		vociMenuPrincipale.add(MSG_INS_TEST_SUITE);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_DIAGNOSI);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_PROBABILITA);
		vociMenuPrincipale.add(MSG_CREAZIONE_REPORT);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_REPORT);
		vociMenuPrincipale.add(MSG_CARICAMENTO);
		vociMenuPrincipale.add(MSG_SALVATAGGIO);
		vociMenuPrincipale.add(MSG_USCITA_PROGRAMMA);
		return vociMenuPrincipale;
	}
	
	private void gestisci(Menu principale) {
		//Precondizione
		assert principale!=null : "Violata precondizione metodo gestisci. Passato menu' nullo.";
		
		boolean finito = false;
		do {
			switch(principale.scegliVoce()) {
				case 1:
					inserimentoNuovoModello();
					break;
				
				case 2: 
					visualizzaModelloCorrente();
					break;  
				
				case 3:
					inserimentoTS();
					break;
				
				case 4:
					visualizzaDiagnosi();
					break;
					
				case 5:
					visualizzaProbabilitaDistanze();
					break;
				
				case 6:
					creazioneReport();
					break;
					
				case 7:
					visualizzaReport();
					break;
					
				case 8:
					caricamento();
					break;
				
				case 9:
					salvataggio();
					break;
					
				case 10 : 
					saluta(); 
					finito = true; 
					break;
					
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);
	}
	
	/**
	 * Inserimento nuovo modello (ozpione 1)
	 */
	//TODO eseguito intervento di manutenzione.
	private static void inserimentoNuovoModello() {		
		boolean esci = false;
		//Se c'e' gia' un modello chiede di sovrascriverlo.
		if(Modello.isNull() == false)
			if(Util.yesOrNo(MSG_MODELLO_ESISTENTE))
				Modello.cambiaModello(null);
			else 
				esci = true;
				
		//Se l'utente non ha rifiutato di sovrascrivere il vecchio modello, ne crea uno nuovo.
		if(!esci) {
			Modello.getInstance().setNome(Util.leggiStringPiena(MSG_NOME_MODELLO));
			Modello.getInstance().setDescrizione(Util.leggiStringPiena(MSG_DESCRIZIONE_MODELLO));
			Modello.getInstance().addToElencoEntita(new NodoIniziale());
			Modello.getInstance().getGm().gestisciMenuInserimentoPrimario();
		}
	}
	
	/**
	 * Visualizza modello corrente (opzione 2)
	 */
	private static void visualizzaModelloCorrente() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else 
		{
			System.out.println(MSG_STAMPA_MOD_CORRENTE);
			System.out.println(Modello.getInstance().toString());
		}
	}
	
	/**
	 * Inserimento Test Suit (opzione 3).
	 */
	private static void inserimentoTS() { 
		TestSuite nuovo;
		boolean continua = false;
		boolean esci = false;
		if(TestSuite.isNull() == false)       //Se c'e' gia' un TS inserito chiede all'utente se vuole sovrascriverlo.
			if(Util.yesOrNo(MSG_SOVRASCRIVI_TS))
				TestSuite.cambiaTestSuite(null);
			else {
				esci = true;
				System.out.println(MSG_INSERIMENTO_TS_ANNULLATO);
			}
		
		if(esci == false) {
			nuovo = TestSuite.getInstance();
			if(Modello.isNull()==false) {
				nuovo.setModello(Modello.getInstance());
				continua = true;
			}
			else 
				System.out.println(MSG_NO_MODELLO);			
			if(continua) {
				System.out.println(String.format(MSG_TS, Modello.getInstance().getNome()));		
				nuovo.inserisciClassiEquivalenza();	
				boolean salvataggioSiNo = Util.yesOrNo(MSG_SALVATAGGIO_TS);
				if(salvataggioSiNo) {
					File nomeFile = new File(Util.leggiString(MSG_NOME_TS));
					Stream.salvaFile(nomeFile, nuovo, true);
				}
				if(Util.yesOrNo(MSG_SINTESI_TS))
					System.out.println(nuovo.toString());
			}	
		}
	}
	
	/**
	 * Visualizzazione diagnosi (opzione 4).
	 */
	private static void visualizzaDiagnosi() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else 
		{							
			if(TestSuite.isNull())
				System.out.println(MSG_NO_TS);
			else
			{
				if(!TestSuite.getInstance().hasDiagnosi()) {				
					TestSuite.getInstance().setDiagnosi(new Diagnosi(TestSuite.getInstance(),false));
				}
				System.out.println(TestSuite.getInstance().getDiagnosi().toString());			
			}
		}
	}
	
	/**
	 * Visualizzazione probabilita' e distanze (opzione 5).
	 */
	private static void visualizzaProbabilitaDistanze() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else 
		{							
			if(TestSuite.isNull())
				System.out.println(MSG_NO_TS);
			else
			{	
				TestSuite attuale = TestSuite.getInstance();
				Diagnosi diagMin = new Diagnosi(attuale, true);
				ProbabilitaMetodo1.stampaRisultati(diagMin.eseguiDiagnosiMetodo1());
				ProbabilitaMetodo2.stampaRisultati(diagMin.eseguiDiagnosiMetodo2());
				OrdinaElencoProbabilitaEIntervalliPosizione.elencoProbabilitaOrdinatoSenzaDoppioni(diagMin.eseguiDiagnosiMetodo1() );
				OrdinaElencoProbabilitaEIntervalliPosizione.elencoProbabilitaOrdinatoSenzaDoppioni(diagMin.eseguiDiagnosiMetodo2() );
			}
		}
	}
	
	/**
	 * Creazione report (opzione 6)
	 */
	private static void creazioneReport() {
		
		// Se non e' inserito un modello o un test suite, viene stampato un messaggio d'errore ed il metodo si arresta.
		if(Modello.isNull() || TestSuite.isNull())
			System.out.println(MSG_ERRORE_REPORT_1);
		else {
			// Se il Test Suite non ha una diagnosi associata, viene stampato un messaggio d'errore ed il metodo si arresta.
			// Viene inoltre impedita la creazione del report se il Test Suite attuale non e' corrispondente al modello attuale.
			if(!TestSuite.getInstance().hasDiagnosi() || !(TestSuite.getInstance().getModello().isEqual(Modello.getInstance())))
				System.out.println(MSG_ERRORE_REPORT_2);
			//Caso corretto (modello, ts e diagnosi presenti)
			else {
				Report nuovo;
				boolean esci = false;
				if(Report.isNull() == false)       //Se c'e' gia' un report inserito chiede all'utente se vuole sovrascriverlo.
					if(Util.yesOrNo(MSG_SOVRASCRIVI_REPORT))
						Report.cambiaReport(null);
					else {
						esci = true;
						System.out.println(MSG_INSERIMENTO_REPORT_ANNULLATO);
					}	
				// Si prosegue se l'utente non ha rifiutato di sovrascrivere un eventuale report gia' presente.
				if(esci == false) {
					String nomeReport = Util.leggiString(MSG_NOME_REPORT);
					nuovo = Report.getInstance(Modello.getInstance(),TestSuite.getInstance());
					nuovo.setNome(nomeReport);
					boolean salvataggioSiNo = Util.yesOrNo(MSG_SALVATAGGIO_REPORT);
					if(salvataggioSiNo) {
						File nomeFile = new File(nomeReport);
						Stream.salvaFile(nomeFile, nuovo, true);
						System.out.println(String.format(MSG_REPORT_SALVATO,nomeReport));
					}
					boolean salvataggioFileTestoSiNo = Util.yesOrNo(MSG_SALVATAGGIO_TESTO_REPORT);
					if(salvataggioFileTestoSiNo) {
						String nomeFileTesto = nomeReport + ".txt";
						Stream.scriviSuFile(nomeFileTesto, nuovo.toString());
						System.out.println(String.format(MSG_REPORT_SALVATO_TESTO,nomeReport));
					}	
					boolean visualizzaSiNo = Util.yesOrNo(MSG_VISUALIZZA_REPORT);
					if(visualizzaSiNo)
						System.out.println(nuovo.toString());
				}
			}
		}
	}

	/**
	 * Visualizza report (opzione 7).
	 */
	private static void visualizzaReport() {
		if(Report.isNull())
			System.out.println(MSG_REPORT_INESISTENTE);
		else {
			Report reportCorrente = Report.getInstance(null,null);
			System.out.println(reportCorrente.toString());
		}
	}
	
	/**
	 * Caricamento (opzione 8).
	 */
	private static void caricamento() {	
		Vector <String> vociMenuCaricamento = new Vector<String>();
		vociMenuCaricamento.add(MSG_CARICA_MODELLO);
		vociMenuCaricamento.add(MSG_CARICA_TS);
		vociMenuCaricamento.add(MSG_CARICA_REPORT);
		vociMenuCaricamento.add(MSG_ESCI);
		Menu menuCaricamento = new Menu(MSG_TITOLO_MENU_CARICAMENTO, vociMenuCaricamento);
		boolean finito = false;
		do {
			switch(menuCaricamento.scegliVoce()) {
				case 1: caricamentoModello(); break;
				case 2: caricamentoTS(); break;
				case 3: caricamentoReport(); break;
				case 4: finito = true; break;
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);	
	}
				
	/**
	 * Caricamento modello.
	 */
	private static void caricamentoModello() {
		File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_PREESISTENTE));
		Modello modelloCaricato = null;
		try {
			modelloCaricato = (Modello) Stream.caricaFile(nomeFile, modelloCaricato);		
			Modello modCorrente;
			boolean sovrascriviModello = false;
			if(Modello.isNull())
				modCorrente = null;
			else {
				modCorrente = Modello.getInstance();
				if(Util.yesOrNo(MSG_SOVRASCRIVI_MODELLO))
					sovrascriviModello = true;
				else
					System.out.println(MSG_CARICAMENTO_ANNULLATO);
			}
			if(sovrascriviModello || modCorrente == null)
			{
				Modello.cambiaModello(modelloCaricato);
				if(modelloCaricato!=null)
					System.out.println(String.format(MSG_MODELLO_CARICATO,modelloCaricato.getNome()));
			}
		} 
		catch(ClassCastException eccezioneCasting) {
			System.out.println(MSG_ERRORE_CAST);
		}
	}
	
	/**
	 * Caricamento ts.
	 */
	private static void caricamentoTS() {
		try {
			Modello modCorrente;
			if(Modello.isNull())
				modCorrente = null;
			else
				modCorrente = Modello.getInstance();
			
			//Se non c'e' alcun modello inserito, viene impedito l'inserimento del TS
			if(modCorrente == null) {
				System.out.println(MSG_NO_MODELLO);
				System.out.println(MSG_CARICAMENTO_ANNULLATO);
			}
			else { //Eseguito se c'e' un modello inserito
				TestSuite tsCaricato = null;	
				File nomeFile = new File(Util.leggiString(MSG_NOME_TS_PREESISTENTE));
				tsCaricato = (TestSuite) Stream.caricaFile(nomeFile, tsCaricato);
				if(tsCaricato != null) {
					Modello modTS = tsCaricato.getModello();			
					if(TestSuite.isNull()) {     //Se non c'e' inserito alcun ts carica quello specificato nel nome del file, sse e' concorde col modello 
						if(modTS!=null) {
							if(modTS.isEqual(modCorrente)) {   //Se i modelli coincidono effettua con successo il caricamento.
								TestSuite.cambiaTestSuite(tsCaricato);     
								System.out.println(MSG_CARICAMENTO_OK);
							}
							else {    //Se il modello non e' coerente con quello del TS stampa a video un warning e carica il modello del TS
								System.out.println(MSG_MODELLO_TS_NON_OK);
								Modello.cambiaModello(modTS);    
								System.out.println(String.format(MSG_MODELLO_CARICATO,modTS.getNome()));   
							}
						}
						else
							System.out.println(MSG_ERRORE_MODELLO_TS);
					}
					else {   //Se c'e' gia' un TS inserito lo fa sovrascrivere, ma controlla che sia coerente il suo modello col modello inserito
						if(Util.yesOrNo(MSG_SOVRASCRIVI_TS)) {
							if(modTS.isEqual(modCorrente)) {   //Se i modelli coincidono effettua con successo il caricamento.  
								TestSuite.cambiaTestSuite(tsCaricato);
								System.out.println(MSG_CARICAMENTO_OK);
							}
							else {    //Se il modello non e' coerente con quello del TS stampa a video un warning e carica il modello del TS
								System.out.println(MSG_MODELLO_TS_NON_OK);
								Modello.cambiaModello(modTS);   
								System.out.println(String.format(MSG_MODELLO_CARICATO,modTS.getNome()));   
							}
						}	
						else    //Se l'utente rifiuta di sovrascrivere il TS, il caricamento viene annullato
							System.out.println(MSG_CARICAMENTO_ANNULLATO);	
					}
				}
			}
		}
		catch(ClassCastException erroreCast) {
			System.out.println(MSG_ERRORE_CAST);
		}
	}
	
	/**
	 * Caricamento report
	 */
	private static void caricamentoReport() {
		try {
			File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT_PREESISTENTE));
			Report repCaricato = null;
			repCaricato = (Report) Stream.caricaFile(nomeFile, repCaricato);
			Report repCorrente;
			boolean sovrascriviReport = false;
			if(Report.isNull())
				repCorrente = null;
			else {
				//Se c'e' gia' un report inserito, chiede se lo si vuole sostituire 
				repCorrente = Report.getInstance(null,null);
				if(Util.yesOrNo(MSG_SOVRASCRIVI_REPORT))
					sovrascriviReport = true;
				else
					System.out.println(MSG_CARICAMENTO_ANNULLATO);
			}
			if(sovrascriviReport || repCorrente == null)
			{
				Report.cambiaReport(repCaricato);
				if(repCaricato!=null) {
					System.out.println(String.format(MSG_REPORT_CARICATO,repCaricato.getNomeReport()));
					if(!(Modello.isNull()) && !(TestSuite.isNull())) {
						if(!Modello.getInstance().getNome().equals(repCaricato.getModello().getNome()) || !TestSuite.getInstance().isEqual(repCaricato.getTS())) 
							System.out.println(MSG_SEGNALAZIONE_REPORT);
					}
					else if(!Modello.isNull() && !Modello.getInstance().getNome().equals(repCaricato.getModello().getNome()))
						System.out.println(MSG_SEGNALAZIONE_REPORT);
				}
			}
		}
		catch(ClassCastException erroreCast) {
			System.out.println(MSG_ERRORE_CAST);
		}
	}
	
	/**
	 * Salvataggio (opzione 9).
	 */
	private static void salvataggio() {
		Vector <String> vociMenuSalvataggio = new Vector<String>();
		vociMenuSalvataggio.add(MSG_SALVA_MODELLO);
		vociMenuSalvataggio.add(MSG_SALVA_TS);
		vociMenuSalvataggio.add(MSG_SALVA_REPORT);
		vociMenuSalvataggio.add(MSG_ESCI_1);
		Menu menuSalvataggio = new Menu(MSG_TITOLO_MENU_SALVATAGGIO, vociMenuSalvataggio);
		boolean finito = false;
		do {
			switch(menuSalvataggio.scegliVoce()) {
				case 1: salvataggioModello(); break;
				case 2: salvataggioTS(); break;
				case 3: salvataggioReport(); break;
				case 4: finito = true; break;
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);	
	}
	
	/**
	 * Salvataggio modello.
	 */
	private static void salvataggioModello() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else {
			File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_SALVA));
			Stream.salvaFile(nomeFile, Modello.getInstance(), true);
		}
	}
	
	/**
	 * Salvataggio ts.
	 */
	private static void salvataggioTS() {
		if(TestSuite.isNull())
			System.out.println(MSG_NO_TS);
		else {
			File nomeFile = new File(Util.leggiString(MSG_NOME_TS));
			Stream.salvaFile(nomeFile, TestSuite.getInstance(), true);
		}
	}
	
	/**
	 * Salvataggio report.
	 */
	private static void salvataggioReport() {
		if(Report.isNull())
			System.out.println(MSG_NO_REPORT);
		else {
			Report attuale = Report.getInstance(Modello.getInstance(), TestSuite.getInstance());
			File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT));
			Stream.salvaFile(nomeFile, attuale, true);
		}
	}
	
	/**
	 * Saluto uscita programma (opzione 10).
	 */
	private static void saluta()
	{
		System.out.print(MSG_SALUTO);
	}
}
