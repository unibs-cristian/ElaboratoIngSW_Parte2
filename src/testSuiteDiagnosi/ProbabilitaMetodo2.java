package testSuiteDiagnosi;

import java.util.Vector;
import java.io.Serializable;

public class ProbabilitaMetodo2 implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Vector<Float> calcolaProbabilita (TestSuite testSuite, int[][] matriceRisultatiTestSuite)
	{
		Vector<Float> risultato = new Vector<Float>();
		
		for ( int i = 0; i < matriceRisultatiTestSuite.length; i++)
		{
			float nKO = 0, nOK = 0, nKOv = 0;
			for (int j = 0; j < matriceRisultatiTestSuite.length; j++)
				switch (matriceRisultatiTestSuite[j][i])
				{
				case 2: nOK += 1;
					break;
					
				case 1: nKO += 1;
					break;
					
				case -1: nKOv += 1;
					break;
					
				default: break;
				}
			
			risultato.add(nKO / ( (nKO + nOK) * (nKO + nKOv) ) );
				
		}
		return risultato;
	}

}
