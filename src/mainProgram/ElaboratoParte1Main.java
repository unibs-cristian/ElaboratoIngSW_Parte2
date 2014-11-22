/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package mainProgram;

import inputDati.InserimentoCammino;

import java.io.File;
import java.util.Vector;

import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.TestSuite;
import utilita.*;
import gestioneDati.Report;
import gestioneModello.Modello;
import gestioneModello.NodoIniziale;
import testSuiteDiagnosi.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ElaboratoParte1Main.
 */
public class ElaboratoParte1Main {

	/** The Constant MSG_TITOLO_MENU_PRINCIPALE. */
	public final static String MSG_TITOLO_MENU_PRINCIPALE = "BENVENUTO NEL MENU' PRINCIPALE\n\nCosa si desidera fare?"; 
	
	/** The Constant MSG_TITOLO_MENU_CARICAMENTO. */
	public final static String MSG_TITOLO_MENU_CARICAMENTO = "MENU' GESTIONE CARICAMENTO\n\nCosa si desidera fare?";
	
	/** The Constant MSG_TITOLO_MENU_SALVATAGGIO. */
	public final static String MSG_TITOLO_MENU_SALVATAGGIO = "MENU' GESTIONE SALVATAGGIO\n\nCosa si desidera fare?";
	
	/** The Constant MSG_NUOVO_MODELLO. */
	public final static String MSG_NUOVO_MODELLO = " 1 - Crea Nuovo Modello";
	
	/** The Constant MSG_VISUALIZZAZIONE_MODELLO. */
	public final static String MSG_VISUALIZZAZIONE_MODELLO = " 2 - Visualizza Modello";
	
	/** The Constant MSG_INS_TEST_SUITE. */
	public final static String MSG_INS_TEST_SUITE = " 3 - Inserimento Test Suite";
	
	/** The Constant MSG_VISUALIZZAZIONE_DIAGNOSI. */
	public final static String MSG_VISUALIZZAZIONE_DIAGNOSI = " 4 - Visualizza Insiemi delle Diagnosi";
	
	/** The Constant MSG_VISUALIZZAZIONE_PROBABILITA. */
	public final static String MSG_VISUALIZZAZIONE_PROBABILITA = " 5 - Visualizza Probabilita' e Distanze";
	
	/** The Constant MSG_CREAZIONE_REPORT. */
	public final static String MSG_CREAZIONE_REPORT = " 6 - Creazione Report Completo";
	
	/** The Constant MSG_VISUALIZZAZIONE_REPORT. */
	public final static String MSG_VISUALIZZAZIONE_REPORT = " 7 - Visualizza Report";
	
	/** The Constant MSG_CARICAMENTO. */
	public final static String MSG_CARICAMENTO = " 8 - Carica";
	
	/** The Constant MSG_SALVATAGGIO. */
	public final static String MSG_SALVATAGGIO = " 9 - Salva";
	
	/** The Constant MSG_USCITA_PROGRAMMA. */
	public final static String MSG_USCITA_PROGRAMMA = "10 - Esci dal programma.";
	
	/** The Constant MSG_ERRORE. */
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	
	/** The Constant MSG_NO_MODELLO. */
	public final static String MSG_NO_MODELLO = "Errore! Nessun modello inserito.";
	
	/** The Constant MSG_NO_TS. */
	public final static String MSG_NO_TS = "Errore! Nessun Test Suite inserito.";
	
	/** The Constant MSG_NO_REPORT. */
	public final static String MSG_NO_REPORT = "Errore! Nessun Report inserito.";
	
	/** The Constant MSG_STAMPA_MOD_CORRENTE. */
	public final static String MSG_STAMPA_MOD_CORRENTE = "STAMPA DEL MODELLO CORRENTE\n";
	
	/** The Constant MSG_TS. */
	public final static String MSG_TS = "\n\nCREAZIONE DEL TEST SUITE RELATIVO AL MODELLO %s\n\n";
	
	/** The Constant MSG_AGGIUNTA_CAMM_GLOBALE. */
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	
	/** The Constant MSG_AGGIUNTA_INS_CAMM. */
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	
	/** The Constant MSG_INS_COP. */
	public final static String MSG_INS_COP = "INSERIMENTO INSIEME DI COPERTURA";
	
	/** The Constant MSG_INS_CLASSE_EQ. */
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	
	/** The Constant MSG_CARD_CE. */
	public final static String MSG_CARD_CE = "Inserire la cardinalita' relativa alla classe di equivalenza : ";
	
	/** The Constant MSG_ERRORE_CE. */
	public final static String MSG_ERRORE_CE = "Errore! E' gia' presente nel Test Suite una classe di equivalenza uguale. Ripetere l'inserimento.";
	
	/** The Constant MSG_CONTINUA_SI_NO_CE. */
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
	
	/** The Constant MSG_COPPIA_AGGIUNTA. */
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	
	/** The Constant MSG_CONTINUA_SI_NO_COPPIA. */
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	
	/** The Constant MSG_VAL_RILEV. */
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	
	/** The Constant MSG_SALVATAGGIO_TS. */
	public final static String MSG_SALVATAGGIO_TS = "Si desidera salvare il Test Suite inserito?";
	
	/** The Constant MSG_NOME_TS. */
	public final static String MSG_NOME_TS = "Che nome si desidera dare al Test Suite?";
	
	/** The Constant MSG_SINTESI_TS. */
	public final static String MSG_SINTESI_TS = "Si desidera vedere una sintesi delle classi di equivalenza e degli insiemi di copertura inseriti\nper il TS corrente?";
	
	/** The Constant MSG_NOME_REPORT. */
	public final static String MSG_NOME_REPORT = "Che nome si desidera dare al Report?";
	
	/** The Constant MSG_MODELLO_ESISTENTE. */
	public final static String MSG_MODELLO_ESISTENTE = "Esiste gia' un modello. Si desidera inserire comunque un nuovo modello?";
	
	/** The Constant MSG_NOME_MODELLO. */
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	
	/** The Constant MSG_DESCRIZIONE_MODELLO. */
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	
	/** The Constant MSG_BENVENUTO. */
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	
	/** The Constant MSG_SALUTO. */
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	
	/** The Constant MSG_NOME_MODELLO_PREESISTENTE. */
	public static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare: ";
	
	/** The Constant MSG_NOME_TS_PREESISTENTE. */
	public static final String MSG_NOME_TS_PREESISTENTE = "Nome Test Suite da caricare: ";
	
	/** The Constant MSG_NOME_REPORT_PREESISTENTE. */
	public final static String MSG_NOME_REPORT_PREESISTENTE = "Nome Report da caricare: ";
	
	/** The Constant MSG_CARICA_MODELLO. */
	public static final String MSG_CARICA_MODELLO = "1 - Carica un modello esistente";
	
	/** The Constant MSG_CARICA_TS. */
	public static final String MSG_CARICA_TS = "2 - Carica un test suite esistente";
	
	/** The Constant MSG_CARICA_REPORT. */
	public static final String MSG_CARICA_REPORT = "3 - Carica un report completo";
	
	/** The Constant MSG_ESCI. */
	public static final String MSG_ESCI = "4 - Ritorna al menu' principale";
	
	/** The Constant MSG_NOME_TS_DA_CARICARE. */
	public static final String MSG_NOME_TS_DA_CARICARE = "Nome Test Suite da caricare: ";
	
	/** The Constant MSG_SOVRASCRIVI_MODELLO. */
	public static final String MSG_SOVRASCRIVI_MODELLO = "Attenzione, esiste gia' un modello inserito. Si desidera abbandonare tale modello e lavorare su quello caricato?";
	
	/** The Constant MSG_MODELLO_TS_NON_OK. */
	public static final String MSG_MODELLO_TS_NON_OK = "Attenzione, il Test Suite caricato si riferisce ad un modello diverso da quello presente nel sistema. Verra' caricato il modello corretto per poter eseguire correttamente diagnosi e probabilita'";
	
	/** The Constant MSG_SOVRASCRIVI_TS. */
	public static final String MSG_SOVRASCRIVI_TS = "Attenzione, esiste gia' un Test Suite inserito. Si desidera abbandonare tale Test Suite e lavorare su quello nuovo?";
	
	/** The Constant MSG_MODELLO_CARICATO. */
	public final static String MSG_MODELLO_CARICATO = "Il modello %s e' stato caricato con successo.";
	
	/** The Constant MSG_REPORT_CARICATO. */
	public final static String MSG_REPORT_CARICATO = "Il report %s e' stato caricato con successo.";
	
	/** The Constant MSG_VISUALIZZA_REPORT_CARICATO. */
	public final static String MSG_VISUALIZZA_REPORT_CARICATO = "Si desidera visualizzare il report caricato?";
	
	/** The Constant MSG_CARICAMENTO_OK. */
	public final static String MSG_CARICAMENTO_OK = "Caricamento completato correttamente.";
	
	/** The Constant MSG_SALVATAGGIO_OK. */
	public final static String MSG_SALVATAGGIO_OK = "Il file e' stato salvato con successo";
	
	/** The Constant MSG_CARICAMENTO_ANNULLATO. */
	public static final String MSG_CARICAMENTO_ANNULLATO = "Caricamento annullato.";
	
	/** The Constant MSG_SALVA_MODELLO. */
	public static final String MSG_SALVA_MODELLO = "1 - Salva Modello";
	
	/** The Constant MSG_SALVA_TS. */
	public static final String MSG_SALVA_TS = "2 - Salva Test Suite";
	
	/** The Constant MSG_SALVA_REPORT. */
	public static final String MSG_SALVA_REPORT = "3 - Salva dati in un report completo";
	
	/** The Constant MSG_ERRORE_REPORT. */
	public static final String MSG_ERRORE_REPORT = "Errore. Nessun Report inserito";
	
	/** The Constant MSG_NOME_MODELLO_SALVA. */
	public static final String MSG_NOME_MODELLO_SALVA = "Che nome si vuole dare al modello da salvare?";
	
	/** The Constant MSG_INSERIMENTO_TS_ANNULLATO. */
	public static final String MSG_INSERIMENTO_TS_ANNULLATO = "Inserimento Test Suite annulato.";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		benvenuto();
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
		Menu menuPrincipale = new Menu(MSG_TITOLO_MENU_PRINCIPALE, vociMenuPrincipale);
		
		boolean finito = false;
		do {
			switch(menuPrincipale.scegliVoce()) {
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
					visualizzazioneDiagnosi();
					break;
					
				case 5:
					probabilitaDistanze();
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
	
	private static void probabilitaDistanze() {
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
				ProbabilitaMetodo1.stampaRisultati(d1.eseguiDiagnosiMetodo1(false) );
				ProbabilitaMetodo2.stampaRisultati(d2.eseguiDiagnosiMetodo2(false) );
				OrdinaElencoProbabilitaEIntervalliPosizione.ElencoProbabilitaOrdinatoSenzaDoppioni(d1.eseguiDiagnosiMetodo1(false) );
				OrdinaElencoProbabilitaEIntervalliPosizione.ElencoProbabilitaOrdinatoSenzaDoppioni(d2.eseguiDiagnosiMetodo2(false) );
			}
		}
	}

	private static void visualizzazioneDiagnosi() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else 
		{							
			if(TestSuite.isNull())
				System.out.println(MSG_NO_TS);
			else
			{
				TestSuite ts = TestSuite.getInstance();
				Diagnosi d1 = new Diagnosi(1, ts);
				Diagnosi d2 = new Diagnosi(2, ts);
				d1.eseguiDiagnosiMetodo1(true);
				d2.eseguiDiagnosiMetodo2(true);
			}
		}
	}

	/**
	 * Inserimento nuovo modello.
	 */
	public static void inserimentoNuovoModello() {
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
	 * Visualizza modello corrente.
	 */
	public static void visualizzaModelloCorrente() {
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
	 * Inserimento ts.
	 */
	public static void inserimentoTS() { 
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
				
			//TODO : Metodo troppo lungo e non di competenza di questa classe. Da rivedere in fase di refactoring !!
				
			if(continua) {
				Modello modelloCorrente = Modello.getInstance();
				System.out.println(String.format(MSG_TS, modelloCorrente.getNome()));		
				int i=1;
				boolean giaPresente = false;
				//Inserimento classi di equivalenza
				do {
					do {
						System.out.println(String.format(MSG_INS_CLASSE_EQ, i));
						//Inserimento cardinalità e creazione classe di equivalenza.
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
	 * Creazione report.
	 */
	public static void creazioneReport() {
		Report nuovo = Report.getInstance();
		Modello modCorrente = Modello.getInstance();
		TestSuite tsCorrente = TestSuite.getInstance();
		if(Modello.isNull() == false)
			nuovo.setModello(modCorrente);
		else 
			System.out.println(MSG_NO_MODELLO);
		if(TestSuite.isNull() == false) {
			nuovo.setTestSuite(tsCorrente);
			Vector <Diagnosi> diagnosiTS = tsCorrente.getElencoDiagnosi();
			if(!(diagnosiTS.isEmpty()))        //Le diagnosi devono essere inserite nel TS
				nuovo.setDiagnosi(diagnosiTS);
		}
		else 
			System.out.println(MSG_NO_TS);
	}
	
	/**
	 * Visualizza report.
	 */
	public static void visualizzaReport() {
		if(Report.isNull())
			System.out.println(MSG_ERRORE_REPORT);
		else {
			Report reportCorrente = Report.getInstance();
			System.out.println(reportCorrente.toString());
		}
	}
		
	/**
	 * Caricamento.
	 */
	public static void caricamento() {	
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
	public static void caricamentoModello() {
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
	public static void caricamentoTS() {
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
			File nomeFile = new File(Util.leggiString(MSG_NOME_TS_PREESISTENTE));
			TestSuite tsCaricato = null;
			tsCaricato = (TestSuite) Stream.caricaFile(nomeFile, tsCaricato);
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
		}  //Chiude il primo else (quello che viene eseguito se c'e' un modello caricato
	}
				
	/**
	 * Caricamento report.
	 */
	public static void caricamentoReport() {
		File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT_PREESISTENTE));
		Report reportCaricato = null;
		reportCaricato = (Report) Stream.caricaFile(nomeFile, reportCaricato);
		Report.cambiaReport(reportCaricato);
		System.out.println(MSG_REPORT_CARICATO);
		if(Util.yesOrNo(MSG_VISUALIZZA_REPORT_CARICATO))
			reportCaricato.toString();
	}
	
	/**
	 * Salvataggio.
	 */
	public static void salvataggio() {
		Vector <String> vociMenuSalvataggio = new Vector<String>();
		vociMenuSalvataggio.add(MSG_SALVA_MODELLO);
		vociMenuSalvataggio.add(MSG_SALVA_TS);
		vociMenuSalvataggio.add(MSG_SALVA_REPORT);
		vociMenuSalvataggio.add(MSG_ESCI);
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
	public static void salvataggioModello() {
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
	public static void salvataggioTS() {
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
	public static void salvataggioReport() {
		if(Report.isNull())
			System.out.println(MSG_NO_REPORT);
		else {
			Report r = Report.getInstance();
			File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT));
			Stream.salvaFile(nomeFile, r, true);
		}
	}
	
	/**
	 * Benvenuto.
	 */
	public static void benvenuto()
	{
		System.out.println(MSG_BENVENUTO);
	}
	
	/**
	 * Saluta.
	 */
	public static void saluta()
	{
		System.out.print(MSG_SALUTO);
	}
}