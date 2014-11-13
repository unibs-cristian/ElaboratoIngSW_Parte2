package mainProgram;

import inputDati.InserimentoCammino;

import java.io.File;
import java.util.Vector;

import com.sun.org.apache.xpath.internal.operations.Equals;

import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.TestSuite;
import utilita.*;
import gestioneDati.Report;
import gestioneModello.Modello;
import gestioneModello.NodoIniziale;
import testSuiteDiagnosi.*;

public class ElaboratoParte1Main {

	public final static String MSG_TITOLO_MENU_PRINCIPALE = "BENVENUTO NEL MENU' PRINCIPALE\n\nCosa si desidera fare?"; 
	public final static String MSG_TITOLO_MENU_CARICAMENTO = "MENU' GESTIONE CARICAMENTO\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_SALVATAGGIO = "MENU' GESTIONE SALVATAGGIO\n\nCosa si desidera fare?";
	
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
	
	public final static String MSG_NO_MODELLO = "Errore! Nessun modello inserito.";
	public final static String MSG_NO_TS = "Errore! Nessun Test Suite inserito.";
	public final static String MSG_NO_REPORT = "Errore! Nessun Report inserito.";
	public final static String MSG_STAMPA_MOD_CORRENTE = "STAMPA DEL MODELLO CORRENTE\n";
	public final static String MSG_TS = "\n\nCREAZIONE DEL TEST SUITE RELATIVO AL MODELLO %s\n\n";
	
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	public final static String MSG_INS_COP = "INSERIMENTO INSIEME DI COPERTURA";
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	public final static String MSG_CARD_CE = "Inserire la cardinalita' relativa alla classe di equivalenza : ";
	
	public final static String MSG_ERRORE_CE = "Errore! E' gia' presente nel Test Suite una classe di equivalenza uguale. Ripetere l'inserimento.";
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	public final static String MSG_SALVATAGGIO_TS = "Si desidera salvare il Test Suite inserito?";
	public final static String MSG_NOME_TS = "Che nome si desidera dare al Test Suite?";
	public final static String MSG_SINTESI_TS = "Si desidera vedere una sintesi delle classi di equivalenza e degli insiemi di copertura inseriti\nper il TS corrente?";
	public final static String MSG_NOME_REPORT = "Che nome si desidera dare al Report?";
	
	public final static String MSG_MODELLO_ESISTENTE = "Esiste gia' un modello. Si desidera inserire comunque un nuovo modello?";
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	
	public static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare: ";
	public static final String MSG_NOME_TS_PREESISTENTE = "Nome Test Suite da caricare: ";
	public final static String MSG_NOME_REPORT_PREESISTENTE = "Nome Report da caricare: ";
	public static final String MSG_CARICA_MODELLO = "1 - Carica un modello esistente";
	public static final String MSG_CARICA_TS = "2 - Carica un test suite esistente";
	public static final String MSG_CARICA_REPORT = "3 - Carica un report completo";
	public static final String MSG_ESCI = "4 - Ritorna al menu' principale";
	public static final String MSG_NOME_TS_DA_CARICARE = "Nome Test Suite da caricare: ";
	public static final String MSG_SOVRASCRIVI_MODELLO = "Attenzione, esiste gia' un modello inserito. Si desidera abbandonare tale modello e lavorare su quello caricato?";
	public static final String MSG_MODELLO_TS_NON_OK = "Attenzione, il Test Suite caricato si riferisce ad un modello diverso da quello presente nel sistema. Verra' caricato il modello corretto per poter eseguire correttamente diagnosi e probabilita'";
	public static final String MSG_SOVRASCRIVI_TS = "Attenzione, esiste gia' un Test Suite inserito. Si desidera abbandonare tale Test Suite e lavorare su quello nuovo?";
	public final static String MSG_MODELLO_CARICATO = "Il modello %s e' stato caricato con successo.";
	public final static String MSG_REPORT_CARICATO = "Il report %s e' stato caricato con successo.";
	public final static String MSG_VISUALIZZA_REPORT_CARICATO = "Si desidera visualizzare il report caricato?";
	public final static String MSG_CARICAMENTO_OK = "Caricamento completato correttamente.";
	public final static String MSG_SALVATAGGIO_OK = "Il file e' stato salvato con successo";
	public static final String MSG_CARICAMENTO_ANNULLATO = "Caricamento annullato.";
	public static final String MSG_SALVA_MODELLO = "1 - Salva Modello";
	public static final String MSG_SALVA_TS = "2 - Salva Test Suite";
	public static final String MSG_SALVA_REPORT = "3 - Salva dati in un report completo";
	public static final String MSG_ERRORE_REPORT = "Errore. Nessun Report inserito";
	public static final String MSG_NOME_MODELLO_SALVA = "Che nome si vuole dare al modello da salvare?";
	public static final String MSG_INSERIMENTO_TS_ANNULLATO = "Inserimento Test Suite annulato.";
	
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
							d1.eseguiDiagnosiMetodo1();
							d2.eseguiDiagnosiMetodo2();							
						}
					}
					break;
					
				case 5:
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
							d1.eseguiDiagnosiMetodo1();
							d2.eseguiDiagnosiMetodo2();							
						}
					}
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
						//Inserimento cardinalita' e creazione classe di equivalenza.
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
	
	public static void visualizzaReport() {
		if(Report.isNull())
			System.out.println(MSG_ERRORE_REPORT);
		else {
			Report reportCorrente = Report.getInstance();
			System.out.println(reportCorrente.toString());
		}
	}
		
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
				
	public static void caricamentoReport() {
		File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT_PREESISTENTE));
		Report reportCaricato = null;
		reportCaricato = (Report) Stream.caricaFile(nomeFile, reportCaricato);
		Report.cambiaReport(reportCaricato);
		System.out.println(MSG_REPORT_CARICATO);
		if(Util.yesOrNo(MSG_VISUALIZZA_REPORT_CARICATO))
			reportCaricato.toString();
	}
	
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
	
	public static void salvataggioModello() {
		if(Modello.isNull())
			System.out.println(MSG_NO_MODELLO);
		else {
			Modello mod = Modello.getInstance();
			File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_SALVA));
			Stream.salvaFile(nomeFile, mod, true);
		}
	}
	
	public static void salvataggioTS() {
		if(TestSuite.isNull())
			System.out.println(MSG_NO_TS);
		else {
			TestSuite ts = TestSuite.getInstance();
			File nomeFile = new File(Util.leggiString(MSG_NOME_TS));
			Stream.salvaFile(nomeFile, ts, true);
		}
	}
	
	public static void salvataggioReport() {
		if(Report.isNull())
			System.out.println(MSG_NO_REPORT);
		else {
			Report r = Report.getInstance();
			File nomeFile = new File(Util.leggiString(MSG_NOME_REPORT));
			Stream.salvaFile(nomeFile, r, true);
		}
	}
	
	public static void benvenuto()
	{
		System.out.println(MSG_BENVENUTO);
	}
	
	public static void saluta()
	{
		System.out.print(MSG_SALUTO);
	}
}
