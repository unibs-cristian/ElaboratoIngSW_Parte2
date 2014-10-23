package Utilita;

import java.util.Vector;
/**
 * Metodi per il menu'.
 * @author Lorenzo Rubagotti.
 */
public class Menu
{
	private static Vector<String> listaOpzioni;

	private final static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";
	
	public Menu(Vector<String> _listaOpzioni)
	{
		listaOpzioni = _listaOpzioni;
	}

	/**
	 * Metodo per aggiungere un'opzione al menu'.
	 * @param opzione Opzione da aggiungere.
	 */
	public static void aggiungiOpzione(String opzione)
	{
		listaOpzioni.add(opzione);
	}
	
	/**
	 * Metodo per visualizzare e scegliere la voce del menu'.
	 * @return Restituisce il numero scelto.
	 */
	public static int scegliVoce()
	{
		System.out.println();
		
		GUI.incorniciaMenu("MENU'", listaOpzioni, 0);
		
		System.out.println();
		return Util.leggiIntCompreso(1, listaOpzioni.size(), RICHIESTA_INSERIMENTO);
	}
}
