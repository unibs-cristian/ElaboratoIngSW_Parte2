package testSuiteDiagnosi;
import java.io.Serializable;
import java.util.Vector;

public class OrdinaElencoProbabilitaEIntervalliPosizione implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Vector<Tupla> OrdinaElencoProbabilita (Vector<Float> probabilitaTestSuite)
	{
		Vector<Tupla> elencoProbabilitaOrdinato = new Vector<Tupla>();
		
		//Inserisco l'elenco probabilità in un vettore di tuple
		Vector<Tupla> elencoProbabilita = new Vector<Tupla>();
		for (int i = 0; i < probabilitaTestSuite.size(); i++)
		{
				Tupla tuplaDaInserire = new Tupla(probabilitaTestSuite.get(i), null );
				Vector<String> listaAzioni = new Vector<String>();
				listaAzioni.add("A" + (i + 1) );
				tuplaDaInserire.setListaAzioni(listaAzioni);
				elencoProbabilita.set(i, tuplaDaInserire);
		}
		
		//Ordino le tuple
		Tupla tuplaConProbabilitaMassima = null;
		for (int i = 0; i < elencoProbabilita.size(); i++)
		{
			for (int j = i + 1; j < elencoProbabilita.size(); j++)
			{
				if (elencoProbabilita.get(i).getProbabilita() >= elencoProbabilita.get(j).getProbabilita() )
				{
					tuplaConProbabilitaMassima = elencoProbabilita.get(i);
				}
				else
					tuplaConProbabilitaMassima = elencoProbabilita.get(j);
			}
			elencoProbabilitaOrdinato.add(tuplaConProbabilitaMassima);
		}
		
		//Rimuovo i doppioni tenendo traccia di quali erano
		for (int i = 0; i < elencoProbabilitaOrdinato.size(); i++)
		{
			for (int j = i + 1; j < elencoProbabilitaOrdinato.size(); j++)
			{
				if (elencoProbabilitaOrdinato.get(i).getProbabilita() == elencoProbabilitaOrdinato.get(j).getProbabilita() )
				{
					elencoProbabilitaOrdinato.get(i).getListaAzioni().add(elencoProbabilitaOrdinato.get(j).getListaAzioni().get(0) );
					elencoProbabilitaOrdinato.remove(j);
				}
			}
		}
		
		return elencoProbabilitaOrdinato;
	}
	
	
}