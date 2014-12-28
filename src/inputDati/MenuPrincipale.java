package inputDati;

import gestioneDati.Report;
import gestioneModello.Modello;
import gestioneModello.NodoIniziale;

import java.io.File;
import java.util.Vector;

import testSuiteDiagnosi.CamminoAzioni;
import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.Diagnosi;
import testSuiteDiagnosi.OrdinaElencoProbabilitaEIntervalliPosizione;
import testSuiteDiagnosi.ProbabilitaMetodo1;
import testSuiteDiagnosi.ProbabilitaMetodo2;
import testSuiteDiagnosi.TestSuite;
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
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	public final static String MSG_CARD_CE = "Inserire la cardinalita' relativa alla classe di equivalenza : ";
	public final static String MSG_INS_COP = "INSERIMENTO INSIEME DI COPERTURA";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	public final static String MSG_ERRORE_CE = "Errore! E' gia' presente nel Test Suite una classe di equivalenza uguale. Ripetere l'inserimento.";
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
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
	public final static String MSG_SALVATAGGIO_REPORT = "Si desidera salvare il Report in un file di testo?";
	public final static String MSG_VISUALIZZA_REPORT = "Si desidera visualizzare il report inserito?";
	public final static String MSG_REPORT_SALVATO = "Il Report e' stato salvato correttamente ed e' stato creato il file di testo %s";
	
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
	public static final String MSG_MODELLO_TS_NON_OK = "Attenzione, il Test Suite caricato si riferisce ad un modello diverso da quello presente nel sistema.\nVerra' caricato il modello corretto per poter eseguire correttamente diagnosi e probabilita'";
	public final static String MSG_NOME_REPORT_PREESISTENTE = "Nome Report da caricare: ";
	public final static String MSG_REPORT_CARICATO = "Il report %s e' stato caricato con successo.";
	public final static String MSG_SEGNALAZIONE_REPORT = "Attenzione! Il Report caricato si riferisce ad un Test Suite o ad un Modello diverso da quelli\nattualmente caricati.";
	public final static String MSG_VISUALIZZA_REPORT_CARICATO = "Si desidera visualizzare il report caricato?";

	/** Costanti stringa per il salvataggio */
	public final static String MSG_TITOLO_MENU_SALVATAGGIO = "MENU' GESTIONE SALVATAGGIO\n\nCosa si desidera fare?";
	public final static String MSG_SALVATAGGIO_OK = "Il file e' stato salvato con successo";
	public static final String MSG_SALVA_MODELLO = "1 - Salva Modello";
	public static final String MSG_SALVA_TS = "2 - Salva Test Suite";
	public static final String MSG_SALVA_REPORT = "3 - Salva dati in un report completo";
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
		Menu menuPrincipale = new Menu(titolo, vociMenuPrincipale);
		this.gestisci(menuPrincipale);
	}
	
	private void gestisci(Menu m) {
		boolean finito = false;
		do {
			switch(m.scegliVoce()) {
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
	private static void inserimentoNuovoModello() {
		boolean sovrascrivi = false;
		if(Modello.isNull() == false)
			sovrascrivi = Util.yesOrNo(MSG_MODELLO_ESISTENTE);
		else {
			String nome_modello = Util.leggiString(MSG_NOME_MODELLO);
			String descrizione_modello = Util.leggiString(MSG_DESCRIZIONE_MODELLO);
			Modello m = Modello.getInstance();
			m.setNome(nome_modello);
			m.setDescrizione(descrizione_modello);
			NodoIniziale ni = new NodoIniziale();
			m.addEntita(ni);
			m.getGm().menuInserimentoPrimario();
		}
		if(sovrascrivi) {
			Modello.cambiaModello(null);
			Modello nuovo = Modello.getInstance();
			String nome_modello = Util.leggiString(MSG_NOME_MODELLO);
			String descrizione_modello = Util.leggiString(MSG_DESCRIZIONE_MODELLO);
			nuovo.setNome(nome_modello);
			nuovo.setDescrizione(descrizione_modello);
			NodoIniziale ni = new NodoIniziale();
			nuovo.addEntita(ni);
			nuovo.getGm().menuInserimentoPrimario();
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
			Modello daVisualizzare = Modello.getInstance();
			System.out.println(MSG_STAMPA_MOD_CORRENTE);
			System.out.println(daVisualizzare.toString());
		}
	}
	
	/**
	 * Inserimento Test Suit (opzione 3).
	 */
	private static void inserimentoTS() { 
		TestSuite ts;
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
			ts = TestSuite.getInstance();
			if(Modello.isNull()==false) {
				Modello modelloCorrente = Modello.getInstance();
				ts.setModello(modelloCorrente);
				continua = true;
			}
			else 
				System.out.println(MSG_NO_MODELLO);			
			if(continua) {
				Modello modelloCorrente = Modello.getInstance();
				System.out.println(String.format(MSG_TS, modelloCorrente.getNome()));		
				int i=1;
				boolean giaPresente = false;
				//Inserimento classi di equivalenza
				do {
					do {
						System.out.println(String.format(MSG_INS_CLASSE_EQ, i));
						//Inserimento cardinalitÃ  e creazione classe di equivalenza.
						int cardinalita = Util.leggiIntConMinimo(MSG_CARD_CE, 1);
						ClasseEquivalenza ce = new ClasseEquivalenza(cardinalita);
						//Inserimento Cammino Globale
						CamminoAzioni cammGlob = new CamminoAzioni(true);
						InserimentoCammino inserimento = new InserimentoCammino(ce, cammGlob);
						inserimento.inserisciCamm();
						
						System.out.println(MSG_INS_COP);
						i++;
						//Inserimento insieme di copertura (insiemi di coppie insieme cammino - val rilev)
						do {
							CamminoAzioni insCamm = new CamminoAzioni(false);
							InserimentoCammino inserimentoInsCamm = new InserimentoCammino(ce, insCamm);
							inserimentoInsCamm.inserisciCamm();
							System.out.println(String.format(MSG_COPPIA_AGGIUNTA,i));					
						} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_COPPIA));
						//Se la classe e' gia' presente nel TS non la aggiunge e fa ripetere l'inserimento.
						if(ts.giaInserita(ce)) {
							System.out.println(MSG_ERRORE_CE);
							giaPresente = true;
						}
						//Se non e' gia' presente, aggiunge la nuova classe al TS
						else {
							ts.addClasseEquivalenza(ce);
							giaPresente = false;
						}
					} while(giaPresente == true);					
				} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_CE));	
				
				boolean salvataggioSiNo = Util.yesOrNo(MSG_SALVATAGGIO_TS);
				if(salvataggioSiNo) {
					File nomeFile = new File(Util.leggiString(MSG_NOME_TS));
					Stream.salvaFile(nomeFile, ts, true);
				}
					
				boolean visualizzaSiNo = Util.yesOrNo(MSG_SINTESI_TS);
				if(visualizzaSiNo)
					System.out.println(ts.toString());
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
				TestSuite ts = TestSuite.getInstance();
				if(!ts.hasDiagnosi()) {
					Diagnosi d = new Diagnosi(1,ts);				
					ts.setDiagnosi(d);
				}
				//TODO inserire qui il metodo per stampare diagnosi.				
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
				System.out.println("TS nullo!");
			else
			{
				System.out.println("TS rilevato!");
				
				TestSuite ts = TestSuite.getInstance();
				Diagnosi d1 = new Diagnosi(1, ts);
				Diagnosi d2 = new Diagnosi(2, ts);
				ProbabilitaMetodo1.stampaRisultati(d1.eseguiDiagnosiMetodo1(false));
				ProbabilitaMetodo2.stampaRisultati(d2.eseguiDiagnosiMetodo2(false));
				OrdinaElencoProbabilitaEIntervalliPosizione.ElencoProbabilitaOrdinatoSenzaDoppioni(d1.eseguiDiagnosiMetodo1(false) );
				OrdinaElencoProbabilitaEIntervalliPosizione.ElencoProbabilitaOrdinatoSenzaDoppioni(d2.eseguiDiagnosiMetodo2(false) );
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
			Modello modCorrente = Modello.getInstance();
			TestSuite tsCorrente = TestSuite.getInstance();
			// Se il Test Suite non ha almeno una diagnosi associata, viene stampato un messaggio d'errore ed il metodo si arresta.
			// Viene inoltre impedita la creazione del report se il Test Suite attuale non Ã¨ corrispondente al modello attuale.
			if(!tsCorrente.hasDiagnosi() || !(tsCorrente.getModello().isEqual(modCorrente)))
				System.out.println(MSG_ERRORE_REPORT_2);
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
					String nomeReport = Util.leggiString(MSG_NOME_REPORT) + ".txt";
					nuovo = Report.getInstance(modCorrente,tsCorrente);
					nuovo.setNome(nomeReport);
					boolean salvataggioSiNo = Util.yesOrNo(MSG_SALVATAGGIO_REPORT);
					if(salvataggioSiNo) {
						Stream.scriviSuFile(nomeReport, nuovo.toString());
						System.out.println(String.format(MSG_REPORT_SALVATO,nomeReport));
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
			Report reportCorrente = Report.getInstance(Modello.getInstance(),TestSuite.getInstance());
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
	
	/**
	 * Caricamento ts.
	 */
	private static void caricamentoTS() {
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
					if(modTS.isEqual(modCorrente) ) {   //Se i modelli coincidono effettua con successo il caricamento.
						TestSuite.cambiaTestSuite(tsCaricato);
						System.out.println(MSG_CARICAMENTO_OK);
					}
					else {    //Se il modello non e' coerente con quello del TS stampa a video un warning e carica il modello del TS
						System.out.println(MSG_MODELLO_TS_NON_OK);
						Modello.cambiaModello(modTS);    
						System.out.println(String.format(MSG_MODELLO_CARICATO,modTS.getNome()));   
					}
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
	
	/**
	 * Caricamento report
	 */
	private static void caricamentoReport() {
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
		Modello modCorrente = Modello.getInstance();
		TestSuite tsCorrente = TestSuite.getInstance();
		if(sovrascriviReport || repCorrente == null)
		{
			Report.cambiaReport(repCaricato);
			if(repCaricato!=null) {
				System.out.println(String.format(MSG_REPORT_CARICATO,repCaricato.getNome()));
				if(!modCorrente.getNome().equals(repCaricato.getModello().getNome()) || !tsCorrente.isEqual(repCaricato.getTS()))
					System.out.println(MSG_SEGNALAZIONE_REPORT);
			}
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
			Modello mod = Modello.getInstance();
			File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_SALVA));
			Stream.salvaFile(nomeFile, mod, true);
		}
	}
	
	/**
	 * Salvataggio ts.
	 */
	private static void salvataggioTS() {
		if(TestSuite.isNull())
			System.out.println(MSG_NO_TS);
		else {
			TestSuite ts = TestSuite.getInstance();
			File nomeFile = new File(Util.leggiString(MSG_NOME_TS));
			Stream.salvaFile(nomeFile, ts, true);
		}
	}
	
	/**
	 * Salvataggio report.
	 */
	private static void salvataggioReport() {
		if(Report.isNull())
			System.out.println(MSG_NO_REPORT);
		else {
			Report r = Report.getInstance(Modello.getInstance(), TestSuite.getInstance());
			File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT));
			Stream.salvaFile(nomeFile, r, true);
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
