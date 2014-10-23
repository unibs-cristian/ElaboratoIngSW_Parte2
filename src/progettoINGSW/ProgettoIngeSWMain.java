package progettoINGSW;

import java.io.File;
import java.util.Vector;
import Utilita.*;

public class ProgettoIngeSWMain {
	public final static String TITOLO_MENU_PRINCIPALE = "Menu Principale"; 
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	public final static String MSG_USCITA_PROGRAMMA = "3 - Esci dal programma.";
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	public final static String MSG_NUOVO_MODELLO = "1 - Crea nuovo modello";
	public final static String MSG_CARICAMENTO_MODELLO = "2 - Carica modello";
	private static final String MSG_NOME_MODELLO_PREESISTENTE = "Nome modello da caricare:";
	
	@SuppressWarnings({ "unused" })
	public static void main(String[] args) {
		benvenuto();
		Vector<String> vociMenuPrincipale = new Vector<String>();
		
		vociMenuPrincipale.add(MSG_NUOVO_MODELLO);
		vociMenuPrincipale.add(MSG_CARICAMENTO_MODELLO);
		vociMenuPrincipale.add(MSG_USCITA_PROGRAMMA);
		Menu menuPrincipale = new Menu(TITOLO_MENU_PRINCIPALE, vociMenuPrincipale);
		
		boolean finito = false;
		do {
			switch(menuPrincipale.scegliVoce()) {
				case 1:
					String nome_modello = Util.leggiString(MSG_NOME_MODELLO);
					String descrizione_modello = Util.leggiString(MSG_DESCRIZIONE_MODELLO);
					Modello m = new Modello(nome_modello, descrizione_modello);
					m.creaNodoIniziale();
					m.menuInserimentoEntita(null);
					break;
				case 2: 
					{
						File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO_PREESISTENTE));
						Modello modelloCaricato = null;
						Stream.caricaFile(nomeFile, modelloCaricato);
						if(modelloCaricato != null)
						{
							modelloCaricato.creaNodoIniziale();
							modelloCaricato.menuInserimentoEntita(null);						
						}
					}
					break;
				case 3:
						finito = true; break;
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);
		saluta();
	}   
	
//	public static void caricamentoModello() {};
	
	public static void benvenuto()
	{
		System.out.println(MSG_BENVENUTO);
	}
	
	public static void saluta()
	{
		System.out.print(MSG_SALUTO);
	}
}
