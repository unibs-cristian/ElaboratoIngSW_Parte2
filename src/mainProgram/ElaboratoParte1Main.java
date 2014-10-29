package mainProgram;

import java.io.File;
import java.util.Vector;

import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.TestSuite;
import utilita.*;
import gestioneModello.Azione;
import gestioneModello.Entita;
import gestioneModello.Modello;
import gestioneModello.NodoIniziale;
import testSuiteDiagnosi.*;

public class ElaboratoParte1Main {

	public final static String TITOLO_MENU_PRINCIPALE = "BENVENUTO NEL MENU' PRINCIPALE\n\nCosa si desidera fare?"; 
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
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	
	private static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare:";
	
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
		Menu menuPrincipale = new Menu(TITOLO_MENU_PRINCIPALE, vociMenuPrincipale);
		
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
					System.out.println("Visualizzazione insiemi delle diagnosi minimali da implementare...");
					break;	
					
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
		Modello m = Modello.getInstance();
		m.setNome(nome_modello);
		m.setDescrizione(descrizione_modello);
		NodoIniziale ni = new NodoIniziale();
		m.addEntita(ni);
		m.getGm().menuInserimentoPrimario();
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
		Modello modelloCorrente = Modello.getInstance();
		ts.setModello(modelloCorrente);
		
		ClasseEquivalenza c1 = new ClasseEquivalenza(2);
		Vector <Azione> insCamm1 = new Vector<Azione>();
		
		Coppia coppia1 = new Coppia(insCamm1, "KO");
		//Inserimento delle classi di equivalenza per il TS considerato
	/*	int i=1;
		do {
			System.out.println(String.format(MSG_INS_CLASSE_EQ, i));
			do {
				
					
			} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_COPPIA));  
			
		} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_CE));	   */
	}
	
	public static void caricamentoModello() {
		File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_PREESISTENTE));
		Modello modelloCaricato = null;
		Stream.caricaFile(nomeFile, modelloCaricato);
		Modello.cambiaModello(modelloCaricato);
		System.out.println(MSG_MODELLO_CARICATO);
	};
	
	public static void benvenuto()
	{
		System.out.println(MSG_BENVENUTO);
	}
	
	public static void saluta()
	{
		Vector <Entita> elencoAzioni = Modello.getInstance().getAzioni();
		for(int i=0; i<elencoAzioni.size(); i++)
		{
			System.out.println(elencoAzioni.elementAt(i).getNome());
			System.out.println("Entrato");
		}
		System.out.print(MSG_SALUTO);
	}
}
