/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package testSuiteDiagnosi;
import java.io.Serializable;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class OrdinaElencoProbabilitaEIntervalliPosizione.
 */
public class OrdinaElencoProbabilitaEIntervalliPosizione implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The probabilita test suite. */
	private Vector<Float> probabilitaTestSuite;

	/**
	 * Instantiates a new ordina elenco probabilita e intervalli posizione.
	 *
	 * @param _probabilitaTestSuite the _probabilita test suite
	 */
	public OrdinaElencoProbabilitaEIntervalliPosizione(Vector<Float> _probabilitaTestSuite)
	{
		probabilitaTestSuite = _probabilitaTestSuite;
	}
	
	/**
	 * Elenco probabilita ordinato senza doppioni.
	 *
	 * @return the vector
	 */
	public Vector<Tupla> ElencoProbabilitaOrdinatoSenzaDoppioni()
	{
		return RimuoviDoppioniElencoProbabilitaOrdinato(OrdinaElencoProbabilita(ElencoProbabilita() ) );
	}
	
	/**
	 * Elenco probabilita.
	 *
	 * @return the vector
	 */
	private Vector<Tupla> ElencoProbabilita ()
	{
		//Inserisco l'elenco probabilita' in un vettore di tuple
		Vector<Tupla> elencoProbabilita = new Vector<Tupla>();
		for (int i = 0; i < probabilitaTestSuite.size(); i++)
		{
				Tupla tuplaDaInserire = new Tupla(probabilitaTestSuite.get(i), null );
				Vector<Integer> listaAzioni = new Vector<Integer>();
				listaAzioni.add(i + 1);
				tuplaDaInserire.setListaAzioni(listaAzioni);
				elencoProbabilita.set(i, tuplaDaInserire);
		}
		
		return elencoProbabilita;
	}
	
	/**
	 * Ordina elenco probabilita.
	 *
	 * @param elencoProbabilita the elenco probabilita
	 * @return the vector
	 */
	private Vector<Tupla> OrdinaElencoProbabilita(Vector<Tupla> elencoProbabilita)
	{
		Vector<Tupla> elencoProbabilitaOrdinato = new Vector<Tupla>();
		//Ordino le tuple
				for (int i = 0; i < elencoProbabilita.size(); i++)
				{
					Tupla tuplaConProbabilitaMassima = elencoProbabilita.get(i);
					for (int j = i + 1; j < elencoProbabilita.size(); j++)
					{
						if (tuplaConProbabilitaMassima.getProbabilita() < elencoProbabilita.get(j).getProbabilita() )
							tuplaConProbabilitaMassima = elencoProbabilita.get(j);
					}
					elencoProbabilitaOrdinato.add(tuplaConProbabilitaMassima);
				}
		return elencoProbabilitaOrdinato;
	}
	
	/**
	 * Rimuovi doppioni elenco probabilita ordinato.
	 *
	 * @param elencoProbabilita the elenco probabilita
	 * @return the vector
	 */
	private Vector<Tupla> RimuoviDoppioniElencoProbabilitaOrdinato(Vector<Tupla> elencoProbabilita)
	{
		//Rimuovo i doppioni tenendo traccia di quali erano
		for (int i = 0; i < elencoProbabilita.size(); i++)
		{
			for (int j = i + 1; j < elencoProbabilita.size(); j++)
			{
				if (elencoProbabilita.get(i).getProbabilita() == elencoProbabilita.get(j).getProbabilita() )
				{
					elencoProbabilita.get(i).getListaAzioni().add(elencoProbabilita.get(j).getListaAzioni().get(0) );
					elencoProbabilita.remove(j);
				}
			}
		}
		return elencoProbabilita;
	}
	
	
	/**
	 * Intervallii posizione.
	 *
	 * @return the vector
	 */
	public Vector<int[]> IntervalliiPosizione()
	{
		Vector<int[]> intervalliPosizione = new Vector<int[]>();
		Vector<Integer> azioniOrdinatePerProbabilita = new Vector<Integer>();
		
		Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni = ElencoProbabilitaOrdinatoSenzaDoppioni();
		
		int posizione = 1;
		for (int i = 0; i < elencoProbabilitaOrdinatoSenzaDoppioni.size(); i++)
		{
			int[] posizioni = new int[2];
			posizione += i;
			posizioni[0]= posizione;
			posizione += elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size() - 1;
			posizioni[1]= posizione;
			for (int j = 0; j < elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size(); j++)
				{
				intervalliPosizione.add(posizioni);
				azioniOrdinatePerProbabilita.add(elencoProbabilitaOrdinatoSenzaDoppioni.get(j).getListaAzioni().get(j) );
				}
		}
		
		Vector<int[]> intervalliPosizioneOrdinatiPerAzione = new Vector<int[]>();
		for (int i = 0; i < azioniOrdinatePerProbabilita.size(); i++)
		{
			int azioneMinima = azioniOrdinatePerProbabilita.get(i);
			for (int j = i + 1; j < azioniOrdinatePerProbabilita.size(); j++)
			{
				if (azioneMinima > azioniOrdinatePerProbabilita.get(j) )
					azioneMinima = azioniOrdinatePerProbabilita.get(j);
			}
			intervalliPosizioneOrdinatiPerAzione.add(intervalliPosizione.get(azioneMinima) );
		}
		
		return intervalliPosizioneOrdinatiPerAzione;
	}
	
	
}
