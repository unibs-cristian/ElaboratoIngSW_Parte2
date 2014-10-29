package testSuiteDiagnosi;

import java.util.Vector;

public class ProbabilitaMetodo1 
{
	public static Vector<Integer> calcolaProbabilita (TestSuite testSuite, Vector<Vector<Integer>> vettoreRisultatiTestSuite)
	{
		Vector<Integer> risultato = new Vector<Integer>(); 
		Vector<Vector<Integer>> vettoreRisultatiProbabilitaClassi = new Vector<Vector<Integer>>();
		for ( int i = 0; i < testSuite.getElencoClassi().size() -1; i++)
		{
			Vector<Integer> risultatoProbabilitaClasse = new Vector<Integer>();
			int cardinalita = testSuite.getElencoClassi().get(i).getCardinalita();
			
			if (cardinalita > 1)
				risultatoProbabilitaClasse = calcolaProbabilitaClasse(cardinalita, vettoreRisultatiTestSuite.get(i) );
			else //Semplice ottimizzazione, evita di chiamare un metodo in piu'
				risultatoProbabilitaClasse = vettoreRisultatiTestSuite.get(i);
			
			vettoreRisultatiProbabilitaClassi.add(risultatoProbabilitaClasse);
		}
		
		Vector<Integer> numeratoreClassi = new Vector<Integer>();
		Vector<Integer> denominatoreClassi = new Vector<Integer>();
		
		for (int i = 0; i < vettoreRisultatiProbabilitaClassi.size() - 1; i++)
		{
			int cardinalita = testSuite.getElencoClassi().get(i).getCardinalita();
			
			for (int j = 0; j < vettoreRisultatiProbabilitaClassi.get(i).size() - 1; j++)
			{
				if (vettoreRisultatiProbabilitaClassi.get(j).get(i) != null)
					{
						numeratoreClassi.set(i, numeratoreClassi.get(i) + vettoreRisultatiProbabilitaClassi.get(j).get(i) );
						denominatoreClassi.set(i, denominatoreClassi.get(i) + cardinalita );
					}
			}
			
			risultato.set(i, numeratoreClassi.get(i) / denominatoreClassi.get(i));
				
		}
		return risultato;
	}
	
	public static Vector<Integer> calcolaProbabilitaClasse (int cardinalita, Vector<Integer> vettoreRisultatiClasse)
	{
		Vector<Integer> risultatoProbabilitaClasse = new Vector<Integer>();
		for (int i = 0; i < vettoreRisultatiClasse.size() - 1; i++)
		{
			risultatoProbabilitaClasse.set(i, cardinalita * vettoreRisultatiClasse.get(i) );
		}
		return risultatoProbabilitaClasse;
	}

}
