/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package utilita;

import java.util.Vector;
// TODO: Auto-generated Javadoc
/**
 * The Class Menu.
 */
public class Menu
{
	
	/** The Constant RICHIESTA_INSERIMENTO. */
	private final static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

	/** The nome menu. */
	private String nomeMenu;
	
	/** The lista opzioni. */
	private Vector<String> listaOpzioni;
	
	/**
	 * Instantiates a new menu.
	 *
	 * @param _nomeMenu the _nome menu
	 * @param _listaOpzioni the _lista opzioni
	 */
	public Menu(String _nomeMenu, Vector<String> _listaOpzioni)
	{
		nomeMenu = _nomeMenu;
		listaOpzioni = _listaOpzioni;
	}

	/**
	 * Aggiungi opzione.
	 *
	 * @param opzione the opzione
	 */
	public void aggiungiOpzione(String opzione)
	{
		listaOpzioni.add(opzione);
	}
	
	/**
	 * Scegli voce.
	 *
	 * @return the int
	 */
	public int scegliVoce()
	{
		System.out.println();
		
		GUI.incorniciaMenu(nomeMenu, listaOpzioni, 0);
		
		System.out.println();
		return Util.leggiIntCompreso(1, listaOpzioni.size(), RICHIESTA_INSERIMENTO);
	}
}
