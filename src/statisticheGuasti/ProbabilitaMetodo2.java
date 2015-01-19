/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;

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
	 * @param matriceRisultatiTestSuite la matrice risultati test suite
	 * @return il  vettore delle probabilita'
	 */
	public static Vector<Float> calcolaProbabilita (int[][] matriceRisultatiTestSuite)
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
			
			float coefficienteOchiai = coefficienteOchiai(nKO, nOK, nKOv);
			
			risultato.add(coefficienteOchiai);
				
		}
		return risultato;
	}
	
	/**
	 * Calcola il coefficiente di Ochiai.
	 *
	 * @param nKO numero di KO
	 * @param nOK numero di OK
	 * @param nKOv numero di KO vuoti
	 * @return il coefficiente di Ochiai
	 */
	public static float coefficienteOchiai(float nKO, float nOK,  float nKOv)
	{
		float numeratore = nKO;
		float denominatore = (float) Math.sqrt( ( (nKO+nOK)*(nKO+nKOv) ) );
		float risultato;
		if (denominatore != 0)
			risultato =  numeratore / denominatore;
		else risultato = 0;
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
