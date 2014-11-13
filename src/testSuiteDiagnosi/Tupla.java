/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package testSuiteDiagnosi;
import java.io.Serializable;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Tupla.
 */
public class Tupla implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The probabilita. */
	private float probabilita;
	
	/** The lista azioni. */
	private Vector<Integer> listaAzioni;

	/**
	 * Instantiates a new tupla.
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
	 * Gets the probabilita.
	 *
	 * @return the probabilita
	 */
	public float getProbabilita()
	{
		return probabilita;
	}

	/**
	 * Gets the lista azioni.
	 *
	 * @return the lista azioni
	 */
	public Vector<Integer> getListaAzioni() 
	{
		return listaAzioni;
	}

	/**
	 * Sets the lista azioni.
	 *
	 * @param listaAzioniDaSettare the new lista azioni
	 */
	public void setListaAzioni(Vector<Integer> listaAzioniDaSettare) 
	{
		listaAzioni = listaAzioniDaSettare;
	}
	
}
