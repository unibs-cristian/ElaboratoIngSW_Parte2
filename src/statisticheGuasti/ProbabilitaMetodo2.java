/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;

import gestioneTS.TestSuite;

import java.util.Vector;
import java.io.Serializable;

/**
 * La classe ProbabilitaMetodo2.
 */
public class ProbabilitaMetodo2 implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Calcola probabilita.
	 *
	 * @param testSuite il test suite
	 * @param matriceRisultatiTestSuite la matrice risultati test suite
	 * @return il  vettore delle probabilita'
	 */
	public static Vector<Float> calcolaProbabilita (TestSuite testSuite, int[][] matriceRisultatiTestSuite)
	{
		Vector<Float> risultato = new Vector<Float>();
		
		for ( int i = 0; i < matriceRisultatiTestSuite[0].length; i++)
		{
			float nKO = 0, nOK = 0, nKOv = 0;
			for (int j = 0; j < matriceRisultatiTestSuite.length; j++)
				switch (matriceRisultatiTestSuite[j][i])
				{
				case 2: ++nOK;
					break;
					
				case 1: ++nKO;
					break;
					
				case -1: ++nKOv;
					break;
					
				default: break;
				}
			
			float result = (float) (nKO/Math.sqrt(((nKO+nOK)*(nKO+nKOv))));
			risultato.add(result);
				
		}
		return risultato;
	}
	
	/**
	 * Stampa i risultati.
	 *
	 * @param risultato il risultato da stampare
	 */
	public static void stampaRisultati(Vector<Float> risultato) {
		System.out.println("RISULTATO PROBABILITA' CON METODO 2");
		for(int i=0; i<risultato.size(); i++) {
			String action = "A" + (i+1);
			Float result = risultato.get(i);
		
			System.out.println("" + (i+1) + ") P(" + action + ") = " + result);
		}
	}

}
