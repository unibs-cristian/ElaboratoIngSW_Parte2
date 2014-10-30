package testSuiteDiagnosi;

import java.util.Vector;

public class Tupla 
{
	private float probabilita;
	private Vector<String> listaAzioni;

	public Tupla (float _probabilita, Vector<String> _listaAzioni)
	{
		probabilita = _probabilita;
		listaAzioni = _listaAzioni;
	}
	
	public float getProbabilita()
	{
		return probabilita;
	}

	public void setListaAzioni(Vector<String> listaAzioniDaSettare) 
	{
		listaAzioni = listaAzioniDaSettare;
	}
}
