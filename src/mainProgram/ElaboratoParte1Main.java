package mainProgram;

import java.io.File;
import java.util.Vector;

import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.TestSuite;
import utilita.*;
import gestioneModello.Azione;
import gestioneModello.Modello;
import gestioneModello.NodoIniziale;
import testSuiteDiagnosi.*;

public class ElaboratoParte1Main {

	public final static String MSG_TITOLO_MENU_PRINCIPALE = "BENVENUTO NEL MENU' PRINCIPALE\n\nCosa si desidera fare?"; 
	public final static String MSG_NUOVO_MODELLO = "1 - Crea Nuovo Modello";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = "2 - Visualizza Modello";
	public final static String MSG_INS_TEST_SUITE = "3 - Inserimento Test Suite";
	public final static String MSG_VISUALIZZAZIONE_DIAGNOSI = "4 - Visualizza Insiemi delle Diagnosi";
	public final static String MSG_VISUALIZZAZIONE_PROBABILITA = "5 - Visualizza Probabilita' e Distanze";
	public final static String MSG_VISUALIZZAZIONE_REPORT = "6 - Visualizza Report Completo";
	public final static String MSG_CARICAMENTO_MODELLO = "7 - Carica";
	public final static String MSG_USCITA_PROGRAMMA = "8 - Esci dal programma.";
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	
	public final static String MSG_NO_MODELLO = "Errore! Nessun modello inserito.";
	public final static String MSG_STAMPA_MOD_CORRENTE = "STAMPA DEL MODELLO CORRENTE\n";
	public final static String MSG_MODELLO_CARICATO = "Il modello %s e' stato caricato con successo.";
	public final static String MSG_TS = "\n\nCREAZIONE DEL TEST SUITE RELATIVO AL MODELLO %s\n\n";
	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	public final static String MSG_AGGIUNTA_INS_CAMM = "Si desidera aggiungere l'azione %s all'insieme del cammino?";
	public final static String MSG_INS_COP = "INSERIMENTO INSIEME DI COPERTURA";
	public final static String MSG_INS_CAMMINO = "Scegliere le azioni da aggiungere all'insieme del cammino";
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	public final static String MSG_CARD_CE = "Inserire la cardinalita' relativa alla classe di equivalenza : ";
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	public final static String MSG_VAL_RILEV = "Inserire il valore della rilevazione relativa all'insieme del cammino";
	public final static String MSG_SALVATAGGIO_TS = "Si desidera salvare il Test Suite inserito?";
	public final static String MSG_NOME_TS = "Che nome si desidera dare al Test Suite?";
	public final static String MSG_SINTESI_TS = "Si desidera vedere una sintesi delle classi di equivalenza e degli insiemi di copertura inseriti\nper il TS corrente?";
	
	
	public final static String MSG_MODELLO_ESISTENTE = "Esiste gia' un modello. Si desidera inserire comunque un nuovo modello?";
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	
	private static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare:";
	private static final String MSG_CARICA_TS = "Caricare un test suite preesistente?";
	private static final String MSG_NOME_TS_DA_CARICARE = "Nome Test Suite da caricare:";
	
	public static void main(String[] args) {
		benvenuto();
		Vector<String> vociMenuPrincipale = new Vector<String>();
		
		vociMenuPrincipale.add(MSG_NUOVO_MODELLO);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_MODELLO);
		vociMenuPrincipale.add(MSG_INS_TEST_SUITE);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_DIAGNOSI);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_PROBABILITA);
		vociMenuPrincipale.add(MSG_VISUALIZZAZIONE_REPORT);
		vociMenuPrincipale.add(MSG_CARICAMENTO_MODELLO);
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
					boolean continua = false;
					if(TestSuite.isNull())
						System.out.println("TS nullo!");
					else
					{
						System.out.println("TS rilevato!");
						continua = true;
					}
					if(continua) {
						TestSuite ts = TestSuite.getInstance();
						Diagnosi d1 = new Diagnosi(1, ts);
						Diagnosi d2 = new Diagnosi(2, ts);
						d1.eseguiDiagnosiMetodo1();
						d2.eseguiDiagnosiMetodo2();
						break;	
					}
					
				case 5:
					System.out.println("Visualizzazione probabilita' e distanze da implementare...");
					break;
				
				case 6:
					System.out.println("Visualizzazione report completo da implementare...");
					break;
					
				case 7:
					caricamentoModello();
					break;
				
				case 8 : 
					saluta(); 
					finito = true; 
					break;
					
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);
	}   
	
	public static void inserimentoNuovoModello() {
		String nome_modello = Util.leggiString(MSG_NOME_MODELLO);
		String descrizione_modello = Util.leggiString(MSG_DESCRIZIONE_MODELLO);
		boolean sovrascrivi = false;
		if(Modello.isNull() == false)
			sovrascrivi = Util.yesOrNo(MSG_MODELLO_ESISTENTE);
		
		if(sovrascrivi || Modello.isNull() == true) {
			Modello m = Modello.getInstance();
			m = null;
			m = Modello.getInstance();
			m.setNome(nome_modello);
			m.setDescrizione(descrizione_modello);
			NodoIniziale ni = new NodoIniziale();
			m.addEntita(ni);
			m.getGm().menuInserimentoPrimario();
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
		TestSuite ts = TestSuite.getInstance();
		boolean continua = false;
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
			Vector <Azione> azioniModello = modelloCorrente.getElencoAzioni();
			
			if (!Util.yesOrNo(MSG_CARICA_TS ) )
			{
				int i=1;
				//Inserimento classi di equivalenza
				do {
					System.out.println(String.format(MSG_INS_CLASSE_EQ, i));
					//Inserimento cardinalita', creazione classe di equivalenza e aggiunta al TS
					int cardinalita = Util.leggiIntConMinimo(MSG_CARD_CE, 1);
					
					CamminoAzioni cammGlob = new CamminoAzioni();
					System.out.println(MSG_CAMM_GLOBALE_1);
					//Inserimento cammino globale
					for(int j=0; j<azioniModello.size(); j++) {
						Azione a = azioniModello.elementAt(j);
						if(Util.yesOrNo(String.format(MSG_AGGIUNTA_CAMM_GLOBALE,azioniModello.elementAt(j).getNome())))
							cammGlob.aggiungiAzione(a);
					}
					System.out.println("Cammino Globale ---> " + cammGlob.toString());
					ClasseEquivalenza ce = new ClasseEquivalenza(cardinalita,cammGlob);
					ts.addClasseEquivalenza(ce);
					System.out.println(MSG_INS_COP);
					i++;
					//Inserimento insieme di copertura (insiemi di coppie insieme cammino - val rilev)
					do {
						CamminoAzioni insCamm = new CamminoAzioni();
						System.out.println(MSG_INS_CAMMINO);
						/*
						 * Le azioni che l'utente può inserire nell'insieme del cammino sono quelle del
						 * cammino globale, quindi e' garantito che ciascun insieme del cammino sia un 
						 * sottoinsieme del cammino globale.
						 */
						 
						for(int j=0; j<cammGlob.getNumeroAzioni(); j++) {
							Azione a = cammGlob.getAzioneAt(j);
							if(Util.yesOrNo(String.format(MSG_AGGIUNTA_INS_CAMM,cammGlob.getAzioneAt(j).getNome())))
								insCamm.aggiungiAzione(a);
						}
						System.out.println("Insieme del Cammino ---> " + insCamm.toString());
						String valoreRilevazione = Util.okOrKo(MSG_VAL_RILEV);
						Coppia c = new Coppia(insCamm, valoreRilevazione);
						ce.addCoppia(c);
						System.out.println(String.format(MSG_COPPIA_AGGIUNTA,i));					
					} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_COPPIA));  			
				} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_CE));	
			}
			else
			{
				File fileDaCaricare = new File(Util.leggiString(MSG_NOME_TS_DA_CARICARE) );
				ts = (TestSuite) Stream.caricaFile(fileDaCaricare, ts);
				/*
				if (ts != null)
				{
					Modello.cambiaModello(modelloCaricato);
					System.out.printf(MSG_MODELLO_CARICATO, nomeFile);
					System.out.println();
				}
				*/
			}
			
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
		
	public static void caricamentoModello() {
		File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_PREESISTENTE));
		Modello modelloCaricato = null;
		modelloCaricato = (Modello) Stream.caricaFile(nomeFile, modelloCaricato);
		if (modelloCaricato != null)
		{
			Modello.cambiaModello(modelloCaricato);
			System.out.printf(MSG_MODELLO_CARICATO, nomeFile);
			System.out.println();
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
