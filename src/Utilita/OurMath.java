package Utilita;

import java.util.*;

/**
 * Svariati metodi matematici.
 * @author Lorenzo Rubagotti.
 */
public class OurMath
{
	private final static int CENTO = 100;
	
	/**
	 * Metodo che si occupa di calcolare la percentuale di un numero.
	 * @param numero Numero in ingresso.
	 * @param percentuale Percentuale del numero in ingresso.
	 * @return Restituisce la percentuale del numero in ingresso.
	 */
	public static double percentualeDi(double numero, double percentuale)
	{
		return (numero * percentuale) / CENTO;
	}
	
	/**
	 * Metodo che si occupa di calcolare la media dei valori di un array di interi.
	 * @param array Array di interi che riceve in ingresso.
	 * @return Restituisce il valore intero della media.
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
	 * Metodo che si occupa di trovare il valore massimo di un array di interi.
	 */
	public static int valoreMassimoArray(int array[])
	{
		return array[indiceValoreMassimoArray(array)];
	}
	
	/**
	 * Metodo che si occupa di trovare in che posizione dell'array si trova il valore massimo di un array di interi.
	 * @param array Riceve in ingresso un array di interi.
	 * @return Restituisce la posizione dell'array nel quale si trova il valore massimo intero dell'array di interi.
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
	
	public static Random estrattore = new Random();
	/**
	 * Metodo che genera un numero double casuale dato un minimo e un massimo.
	 * @param min Minimo.
	 * @param max Massimo.
	 * @return Restituisce il numero casuale generato.
	 */
	public static double numeroCasualeDouble(int min, int max)
	{
		 int range = max + 1 - min;
		 double casual = estrattore.nextInt(range);
		 return casual + min;
	}

	/**
	 * Metodo che genera un numero intero casuale dato un minimo e un massimo.
	 * @param min Minimo.
	 * @param max Massimo.
	 * @return Restituisce il numero casuale generato.
	 */
	public static int numeroCasualeIntero(int min, int max)
	{
		int range = max + 1 - min;
		int casual = estrattore.nextInt(range);
		return casual + min;
	}
	
	/**
	 * Restituisce la lunghezza massima, dei caratteri, di un vector di stringhe. Utile per la formattazione.
	 * @param vettore Vettore che riceve in ingresso.
	 * @return Restituisce la lunghezza massima calcolata.
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
	 * Restituisce la lunghezza massima, delle cifre, di un array di interi. Utile per la formattazione.
	 * @param array Array che riceve in ingresso.
	 * @return Restituisce la lunghezza massima calcolata.
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
