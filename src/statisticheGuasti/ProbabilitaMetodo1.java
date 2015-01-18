/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;

import gestioneTS.ClasseEquivalenza;
import gestioneTS.TestSuite;

import java.util.Vector;
import java.io.Serializable;

/**
 * La classe ProbabilitaMetodo1.
 */
public class ProbabilitaMetodo1 implements Serializable 
{
	
	/** La costante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Calcola la probabilita.
	 *
	 * @param testSuite il test suite
	 * @param vettoreRisultatiTestSuite il vettore risultati test suite
	 * @return il vettore delle probabilita'
	 */
	public static Vector<Float> calcolaProbabilita (TestSuite testSuite, Vector<Vector<Integer>> vettoreRisultatiTestSuite)
	{
		Vector<ClasseEquivalenza> elencoClassiTestSuite = testSuite.getElencoClassi();
		Vector<Float> risultatoProbabilitaTestSuite = new Vector<Float>();
		
		Vector<Vector<Integer>> vettoreRisultatiProbabilitaClassi = new Vector<Vector<Integer>>();
		for ( int i = 0; i < elencoClassiTestSuite.size(); i++)
		{
			Vector<Integer> risultatoProbabilitaClasse = new Vector<Integer>();
			int cardinalita = elencoClassiTestSuite.get(i).getCardinalita();
						
			if (cardinalita > 1)
				risultatoProbabilitaClasse = calcolaProbabilitaClasse(cardinalita, vettoreRisultatiTestSuite.get(i) );
			else //Semplice ottimizzazione, evita di chiamare un metodo in piu'
				risultatoProbabilitaClasse = vettoreRisultatiTestSuite.get(i);
			
			vettoreRisultatiProbabilitaClassi.add(risultatoProbabilitaClasse);
		}
		
		risultatoProbabilitaTestSuite = calcolaProbabilitaTestSuite(elencoClassiTestSuite, vettoreRisultatiProbabilitaClassi);
		
		return risultatoProbabilitaTestSuite;
	}
	
	/**
	 * Calcola la probabilita del test suite.
	 *
	 * @param elencoClassiTestSuite l'elenco classi del test suite
	 * @param vettoreRisultatiProbabilitaClassi il vettore dei risultati delle probabilita delle classi
	 * @return il vettore delle probabilita' del test suite
	 */
	private static Vector<Float> calcolaProbabilitaTestSuite(Vector<ClasseEquivalenza> elencoClassiTestSuite, Vector<Vector<Integer>> vettoreRisultatiProbabilitaClassi) 
	{
		Vector<Float> risultatoProbabilitaTestSuite = new Vector<Float>();
		Vector<Float> numeratoreClassi = new Vector<Float>();
		Vector<Float> denominatoreClassi = new Vector<Float>();
		
		for (int i = 0; i < vettoreRisultatiProbabilitaClassi.get(0).size(); i++)
		{
			numeratoreClassi.add(i, (float) 0);
			denominatoreClassi.add(i, (float) 0);
			for (int j = 0; j < vettoreRisultatiProbabilitaClassi.size(); j++)
			{
				int cardinalita = elencoClassiTestSuite.get(j).getCardinalita();			
				if (vettoreRisultatiProbabilitaClassi.get(j).get(i) >= 0)
					{
						numeratoreClassi.set(i, numeratoreClassi.get(i) + vettoreRisultatiProbabilitaClassi.get(j).get(i) );
						denominatoreClassi.set(i, denominatoreClassi.get(i) + cardinalita );
					}
			}
			
			risultatoProbabilitaTestSuite.add(i, numeratoreClassi.get(i) / denominatoreClassi.get(i));
		}
		return risultatoProbabilitaTestSuite;
	}

	/**
	 * Calcola probabilita classe.
	 *
	 * @param cardinalita la cardinalita'
	 * @param vettoreRisultatiClasse il vettore risultati classe
	 * @return il vettore delle probabilita' di una classe
	 */
	private static Vector<Integer> calcolaProbabilitaClasse (int cardinalita, Vector<Integer> vettoreRisultatiClasse)
	{
		Vector<Integer> risultatoProbabilitaClasse = new Vector<Integer>();
		for (int i = 0; i < vettoreRisultatiClasse.size(); i++)
		{
			risultatoProbabilitaClasse.add(i, cardinalita * vettoreRisultatiClasse.get(i) );
		}
		return risultatoProbabilitaClasse;
	}
	
	/**
	 * Stampa i risultati.
	 *
	 * @param risultato il risultato da stampare
	 */
	public static void stampaRisultati(Vector<Float> risultato) {
		System.out.println("RISULTATO PROBABILITA' CON METODO 1");
		for(int i=0; i<risultato.size(); i++) {
			String action = "A" + (i+1);
			Float result = risultato.get(i);
		
			System.out.println("" + (i+1) + ") P(" + action + ") = " + result);
		}
	}
}