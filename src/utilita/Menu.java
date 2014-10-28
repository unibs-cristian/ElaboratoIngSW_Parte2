package utilita;

import java.util.Vector;
/**
 * Metodi per il menu'.
 * @author Lorenzo Rubagotti.
 */
public class Menu
{
	private final static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

	private String nomeMenu;
	private Vector<String> listaOpzioni;
	
	public Menu(String _nomeMenu, Vector<String> _listaOpzioni)
	{
		nomeMenu = _nomeMenu;
		listaOpzioni = _listaOpzioni;
	}

	/**
	 * Metodo per aggiungere un'opzione al menu'.
	 * @param opzione Opzione da aggiungere.
	 */
	public void aggiungiOpzione(String opzione)
	{
		listaOpzioni.add(opzione);
	}
	
	/**
	 * Metodo per visualizzare e scegliere la voce del menu'.
	 * @return Restituisce il numero scelto.
	 */
	public int scegliVoce()
	{
		System.out.println();
		
		GUI.incorniciaMenu(nomeMenu, listaOpzioni, 0);
		
		System.out.println();
		return Util.leggiIntCompreso(1, listaOpzioni.size(), RICHIESTA_INSERIMENTO);
	}
}
