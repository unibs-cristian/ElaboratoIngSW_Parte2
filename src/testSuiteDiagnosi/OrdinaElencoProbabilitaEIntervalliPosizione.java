package testSuiteDiagnosi;

import java.util.Vector;

public class OrdinaElencoProbabilitaEIntervalliPosizione 
{

	public Vector<Tupla> OrdinaElencoProbabilita (Vector<Float> probabilitaTestSuite)
	{
		Vector<Tupla> elencoProbabilitaOrdinato = new Vector<Tupla>();
		
		Vector<Tupla> elencoProbabilita = new Vector<Tupla>();
		for (int i = 0; i < probabilitaTestSuite.size(); i++)
		{
				Tupla tuplaDaInserire = new Tupla(probabilitaTestSuite.get(i), null );
				Vector<String> listaAzioni = new Vector<String>();
				listaAzioni.add("A" + (i + 1) );
				tuplaDaInserire.setListaAzioni(listaAzioni);
				elencoProbabilita.set(i, tuplaDaInserire);
		}
		
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
		
		
		
		return elencoProbabilitaOrdinato;
	}
}