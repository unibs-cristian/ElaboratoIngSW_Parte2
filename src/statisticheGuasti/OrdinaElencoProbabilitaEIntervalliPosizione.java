/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;
import java.io.Serializable;
import java.util.Vector;

/**
 * The Class OrdinaElencoProbabilitaEIntervalliPosizione.
 */
public class OrdinaElencoProbabilitaEIntervalliPosizione implements Serializable
{
	
	/** La costante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private Vector<Float> probabilitaTestSuite;
	private int metodoUtilizzato;
	private Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni;
	
	/**
	 * Fornisce l'istanza di OrdinaElencoProbabilitaEIntervalliPosizione.
	 * 
	 * @param probabilitaTestSuite : probabilita
	 * @param metodoUtilizzato : metodo utilizzato
	 */
	public OrdinaElencoProbabilitaEIntervalliPosizione (Vector<Float> probabilitaTestSuite, int metodoUtilizzato)
	{
		this.probabilitaTestSuite = probabilitaTestSuite;
		this.metodoUtilizzato = metodoUtilizzato;
		
		elencoProbabilitaOrdinatoSenzaDoppioni();
	}
	
	/**
	 * Elenco probabilita ordinato senza doppioni.
	 *
	 * @return il vettore elencoProbabilita' senza doppioni
	 */
	public Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni()
	{
		elencoProbabilitaOrdinatoSenzaDoppioni = rimuoviDoppioniElencoProbabilitaOrdinato(ordinaElencoProbabilita(elencoProbabilita(probabilitaTestSuite) ) );
		return elencoProbabilitaOrdinatoSenzaDoppioni;
	}
	
	/**
	 * Elenco probabilita.
	 *
	 * @return il vettore elencoProbabilita di probabilitaTestSuite
	 */
	private Vector<Tupla> elencoProbabilita (Vector<Float> probabilitaTestSuite)
	{
		//Inserisco l'elenco probabilita' in un vettore di tuple
		Vector<Tupla> elencoProbabilita = new Vector<Tupla>();
		for (int i = 0; i < probabilitaTestSuite.size(); i++)
		{
				Tupla tuplaDaInserire = new Tupla(probabilitaTestSuite.get(i), null );
				Vector<Integer> listaAzioni = new Vector<Integer>();
				listaAzioni.add(i + 1);
				tuplaDaInserire.setListaAzioni(listaAzioni);
				elencoProbabilita.add(tuplaDaInserire);
		}
		
		return elencoProbabilita;
	}
	
	/**
	 * Ordina elenco probabilita.
	 *
	 * @param elencoProbabilita l'elenco probabilita
	 * @return il vettore elencoProbabilitaT, ovvero il vettore elencoProbabilita ordinato
	 */
	private Vector<Tupla> ordinaElencoProbabilita(Vector<Tupla> elencoProbabilita)
	{
		Vector<Tupla> elencoProbabilitaOrdinato = elencoProbabilita;
				//Ordino le tuple
				Tupla temp;
				for (int i = 0; i < elencoProbabilitaOrdinato.size()-1; i++)
				{
					int iMax = i;
					Tupla tuplaConProbabilitaMassima = elencoProbabilitaOrdinato.get(i);
					for (int j = i; j < elencoProbabilitaOrdinato.size(); j++)
					{
						if (tuplaConProbabilitaMassima.getProbabilita() < elencoProbabilitaOrdinato.get(j).getProbabilita() )
							{
							tuplaConProbabilitaMassima = elencoProbabilitaOrdinato.get(j);
							iMax = j;
							}
					}
				temp = elencoProbabilitaOrdinato.get(i);
				elencoProbabilitaOrdinato.set(i, tuplaConProbabilitaMassima);
				elencoProbabilitaOrdinato.set(iMax, temp);
				
				
				}
		return elencoProbabilitaOrdinato;
	}

	/**
	 * Rimuovi doppioni elenco probabilita ordinato.
	 *
	 * @param elencoProbabilita l'elenco probabilita da gestire
	 * @return il vettore elencoProbabilita ordinato senza doppioni
	 */
	private Vector<Tupla> rimuoviDoppioniElencoProbabilitaOrdinato(Vector<Tupla> elencoProbabilitaOrdinato)
	{
		//Rimuovo i doppioni tenendo traccia di quali erano
		Vector<Integer> tupleDaEliminare = new Vector<Integer>();
			for (int j = 0; j < elencoProbabilitaOrdinato.size() - 1; j++)
			{
				for (int k = j+1; k < elencoProbabilitaOrdinato.size(); k++)
					if (elencoProbabilitaOrdinato.get(j).getProbabilita() == elencoProbabilitaOrdinato.get(k).getProbabilita() )
					{
						elencoProbabilitaOrdinato.get(j).getListaAzioni().add(elencoProbabilitaOrdinato.get(k).getListaAzioni().get(0) );
						boolean tuplaDaEliminarePresente = false;
						for (int t = 0; t < tupleDaEliminare.size(); t++)
							if (k == tupleDaEliminare.get(t) )
								tuplaDaEliminarePresente = true;
						if(!tuplaDaEliminarePresente)
						tupleDaEliminare.add(k);
					}
			}
			
			for ( int i = tupleDaEliminare.size() - 1; i >= 0; i--)
			{
				int daEliminare = tupleDaEliminare.get(i);
				elencoProbabilitaOrdinato.remove(daEliminare);
			}			
			
		return elencoProbabilitaOrdinato;
	}
	
	
	/**
	 * Intervalli posizione.
	 *
	 * @return il vettore degli intervalli posizione
	 */
	public Vector<int[]> intervalliiPosizione()
	{
		Vector<int[]> intervalliPosizione = new Vector<int[]>();
		Vector<Integer> azioniOrdinatePerProbabilita = new Vector<Integer>();
		
		Vector<Tupla> elencoProbabilitaOrdinatoSenzaDoppioni = elencoProbabilitaOrdinatoSenzaDoppioni();
		
		int posizione = 1;
		for (int i = 0; i < elencoProbabilitaOrdinatoSenzaDoppioni.size(); i++)
		{
			int[] posizioni = new int[2];
			if (i > 0)
				posizione++;
			posizioni[0]= posizione;
			posizione += elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size() - 1;
			posizioni[1]= posizione;
			
			for (int j = 0; j < elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size(); j++)
				{
				intervalliPosizione.add(posizioni);
				azioniOrdinatePerProbabilita.add(elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().get(j) );
				}
		}
		
		Vector<int[]> intervalliPosizioneOrdinatiPerAzione = new Vector<int[]>();
		for (int i = 0; i < azioniOrdinatePerProbabilita.size(); i++)
		{
			int posizioneAzioneMinima = i;
			int azioneMinima = azioniOrdinatePerProbabilita.get(i);
			for (int j = i + 1; j < azioniOrdinatePerProbabilita.size(); j++)
			{
				if (azioneMinima > azioniOrdinatePerProbabilita.get(j) )
					{
					azioneMinima = azioniOrdinatePerProbabilita.get(j);
					posizioneAzioneMinima = j;
					}
			}
			intervalliPosizioneOrdinatiPerAzione.add(intervalliPosizione.get(posizioneAzioneMinima) );
			azioniOrdinatePerProbabilita.remove(posizioneAzioneMinima);
			intervalliPosizione.remove(posizioneAzioneMinima);
			i = -1
					;
		}
		
		return intervalliPosizioneOrdinatiPerAzione;
	}
	
	/**
	 * Stampa i risultati.
	 *
	 * @return ritorna il risultato da stampare via stringbuffer
	 */
	public StringBuffer stampaOrdinaElencoProbabilita() 
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append("ELENCO PROBABILITA' METODO " + metodoUtilizzato + " ORDINATO \n");
		
		for(int i=0; i<elencoProbabilitaOrdinatoSenzaDoppioni.size(); i++) {
			risultato.append("\nAzioni: ");
			for (int j = 0; j < elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().size(); j++)
			{
				risultato.append(elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getListaAzioni().get(j) + " ");
			}
		
			risultato.append("| Probabilita': " + elencoProbabilitaOrdinatoSenzaDoppioni.get(i).getProbabilita() );
		}
		return risultato;
	}
	
	/**
	 * Stampa i risultati.
	 *
	 * @return ritorna il risultato da stampare
	 */
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(stampaOrdinaElencoProbabilita() );
		risultato.append("\n");
		
		return risultato.toString();
	}
	
}
