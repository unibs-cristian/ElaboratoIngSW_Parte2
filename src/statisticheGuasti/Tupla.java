/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;
import java.io.Serializable;
import java.util.Vector;

/**
 * La classe Tupla.
 */
public class Tupla implements Serializable
{
	
	/** La costanteserialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La probabilita. */
	private float probabilita;
	
	/** La lista azioni. */
	private Vector<Integer> listaAzioni;

	/**
	 * Istanza di una nuova tupla.
	 *
	 * @param _probabilita the _probabilita
	 * @param _listaAzioni the _lista azioni
	 */
	public Tupla (float _probabilita, Vector<Integer> _listaAzioni)
	{
		probabilita = _probabilita;
		listaAzioni = _listaAzioni;
	}
	
	/**
	 * Get della probabilita.
	 *
	 * @return la probabilita
	 */
	public float getProbabilita()
	{
		return probabilita;
	}

	/**
	 * Get della lista azioni.
	 *
	 * @return la lista azioni
	 */
	public Vector<Integer> getListaAzioni() 
	{
		return listaAzioni;
	}

	/**
	 * Set della lista azioni.
	 *
	 * @param listaAzioniDaSettare la nuova lista azioni da "settare"
	 */
	public void setListaAzioni(Vector<Integer> listaAzioniDaSettare) 
	{
		listaAzioni = listaAzioniDaSettare;
	}
	
}
