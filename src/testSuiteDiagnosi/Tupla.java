package testSuiteDiagnosi;
import java.io.Serializable;
import java.util.Vector;

public class Tupla implements Serializable
{
	private static final long serialVersionUID = 1L;
	private float probabilita;
	private Vector<Integer> listaAzioni;

	public Tupla (float _probabilita, Vector<Integer> _listaAzioni)
	{
		probabilita = _probabilita;
		listaAzioni = _listaAzioni;
	}
	
	public float getProbabilita()
	{
		return probabilita;
	}

	public Vector<Integer> getListaAzioni() 
	{
		return listaAzioni;
	}

	public void setListaAzioni(Vector<Integer> listaAzioniDaSettare) 
	{
		listaAzioni = listaAzioniDaSettare;
	}
	
}
