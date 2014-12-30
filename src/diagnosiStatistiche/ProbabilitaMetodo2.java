/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package diagnosiStatistiche;

import gestioneTS.TestSuite;

import java.util.Vector;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProbabilitaMetodo2.
 */
public class ProbabilitaMetodo2 implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Calcola probabilita.
	 *
	 * @param testSuite the test suite
	 * @param matriceRisultatiTestSuite the matrice risultati test suite
	 * @return the vector
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
	
	public static void stampaRisultati(Vector<Float> risultato) {
		System.out.println("RISULTATO PROBABILITA' CON METODO 2");
		for(int i=0; i<risultato.size(); i++) {
			String action = "A" + 1;
			Float result = risultato.get(i);
		
			System.out.println("" + i + ") P(" + action + ") = " + result);
		}
	}

}
