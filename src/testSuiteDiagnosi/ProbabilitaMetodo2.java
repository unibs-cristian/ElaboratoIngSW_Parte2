package testSuiteDiagnosi;

import java.util.Vector;

public class ProbabilitaMetodo2 
{
	public static Vector<Float> calcolaProbabilita (TestSuite testSuite, Vector<Vector<Integer>> vettoreRisultatiTestSuite)
	{
		Vector<Integer> risultato = new Vector<Integer>(); 
		Vector<Vector<Integer>> vettoreRisultatiProbabilitaAzioni = new Vector<Vector<Integer>>();
		
		Vector<String> vettoreValoriRilevazione = new Vector<String>();
		for (int i = 0; i < testSuite.getElencoClassi().size() - 1; i++)
			for (int j = 0; j < testSuite.getElencoClassi().get(i).getElencoCoppie().size() - 1; j++)
				vettoreValoriRilevazione.add(testSuite.getElencoClassi().get(i).getElencoCoppie().get(j).getValoreRilevazione() );
		
		
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
				if (vettoreRisultatiProbabilitaClassi.get(j).get(i) != -1)
					{
						numeratoreClassi.set(i, numeratoreClassi.get(i) + vettoreRisultatiProbabilitaClassi.get(j).get(i) );
						denominatoreClassi.set(i, denominatoreClassi.get(i) + cardinalita );
					}
			}
			
			risultato.set(i, numeratoreClassi.get(i) / denominatoreClassi.get(i));
				
		}
		return risultato;
	}

}
