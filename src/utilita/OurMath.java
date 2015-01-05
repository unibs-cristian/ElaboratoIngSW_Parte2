/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package utilita;

import java.util.*;

/**
 * The Class OurMath.
 */
public class OurMath
{
	
	/** The Constant CENTO. */
	private final static int CENTO = 100;
	
	/**
	 * Percentuale di.
	 *
	 * @param numero the numero
	 * @param percentuale the percentuale
	 * @return the double
	 */
	public static double percentualeDi(double numero, double percentuale)
	{
		return (numero * percentuale) / CENTO;
	}
	
	/**
	 * Media array.
	 *
	 * @param array the array
	 * @return the int
	 */
	public static int mediaArray(int array[])
	{
		int somma = 0;
		for (int i = 0; i < array.length; i++)
		{
			somma += array[i];
		}
		return  somma / (array.length);
	}
	
	/**
	 * Valore massimo array.
	 *
	 * @param array the array
	 * @return the int
	 */
	public static int valoreMassimoArray(int array[])
	{
		return array[indiceValoreMassimoArray(array)];
	}
	
	/**
	 * Indice valore massimo array.
	 *
	 * @param array the array
	 * @return the int
	 */
	public static int indiceValoreMassimoArray(int array[])
	{
		int valoreMassimo = array[0];
		int i = 0;
		int indice = 0;
		for(i = 1; i < array.length; i++)
		{
			if (array[i] > valoreMassimo)
			{
				valoreMassimo = array[i];
				indice = i;
			}
		}
		return indice;
	}
	
	/** The estrattore. */
	public static Random estrattore = new Random();
	
	/**
	 * Numero casuale double.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the double
	 */
	public static double numeroCasualeDouble(int min, int max)
	{
		 int range = max + 1 - min;
		 double casual = estrattore.nextInt(range);
		 return casual + min;
	}

	/**
	 * Numero casuale intero.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int numeroCasualeIntero(int min, int max)
	{
		int range = max + 1 - min;
		int casual = estrattore.nextInt(range);
		return casual + min;
	}
	
	/**
	 * Lunghezza massima vector string.
	 *
	 * @param vettore the vettore
	 * @return the int
	 */
	public static int lunghezzaMassimaVectorString(Vector<String> vettore)
	{
		int lunghezzaMassima = 0;
		for ( int i = 0; i < vettore.size(); i++)
			if(lunghezzaMassima < vettore.get(i).length())
				lunghezzaMassima = vettore.get(i).length();
		return lunghezzaMassima;	
	}
	
	/**
	 * Lunghezza massima arrayint.
	 *
	 * @param array the array
	 * @return the int
	 */
	public static int lunghezzaMassimaArrayint(int [] array)
	{
		int lunghezzaMassima = 0;
		for ( int i = 0; i < array.length; i++)
			if(lunghezzaMassima < Integer.toString(array[i]).length())
				lunghezzaMassima = Integer.toString(array[i]).length();
		return lunghezzaMassima;
	}	
}
