/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package mainProgram;

import inputDati.MenuPrincipale;

/**
 * Classe contenente il metodo main
 */
public class ElaboratoParte1Main {
	
	/** Il messaggio di benvenuto stampato a video all'avvio del programma. */
	public final static String MSG_BENVENUTO = "Benvenuto. Questo programma consente di inserire e caricare modelli e test suite. Inoltre e' possibile\nvisualizzare i relativi insiemi delle diagnosi minimali e gli elenchi ordinati delle probabilita' con\nle relative distanze. Infine e' possibile creare e visualizzare report completi contenenti tutti i dati\nforniti in input e quelli ottenuti come output. Per maggiori informazioni, consultare il manuale d'uso.";

	/** La stringa stampata a video quando compare il menu' principale. */
	public final static String MSG_TITOLO_MENU_PRINCIPALE = "BENVENUTO NEL MENU' PRINCIPALE\n\nCosa si desidera fare?"; 
		
	/**
	 * Il metodo main.
	 *
	 * @param args : gli eventuali argomenti con i quali viene chiamato il main
	 */
	public static void main(String[] args) {
		benvenuto();
		MenuPrincipale principale = new MenuPrincipale(MSG_TITOLO_MENU_PRINCIPALE);
		principale.avvia();
	}   
	
	/**
	 * Benvenuto.
	 */
	private static void benvenuto()
	{
		System.out.println(MSG_BENVENUTO);
	}
}