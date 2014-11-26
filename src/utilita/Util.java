/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package utilita;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util
{
	
	/** The Constant ERRORE_NUMERO. */
	private static final String ERRORE_NUMERO = "Errore, il carattere inserito non è un numero.";
	
	/** The Constant ERRORE_STRINGA_VUOTA. */
	private static final String ERRORE_STRINGA_VUOTA = "Errore, non è possibile inserire una stringa vuota. \nReinserire la stringa: ";
	
	/** The Constant ERRORE_SESSO. */
	private static final String ERRORE_SESSO = "Errore, reinserire il sesso: ";
	
	/** The Constant ERRORE_INTERO_COMPRESO. */
	private static final String ERRORE_INTERO_COMPRESO = "Errore. Insere un numero compreso tra %s e %s: ";
	
	/** The Constant ERRORE_INTERO. */
	private static final String ERRORE_INTERO = "Errore, il carattere inserito non è un intero.";
	
	/** The Constant ERRORE_STRINGA_VUOTA_O_SPAZI. */
	private static final String ERRORE_STRINGA_VUOTA_O_SPAZI = "Errore, non è possibile inserire una stringa vuota o con spazi. \nReinserire la stringa:";
	
	/** The Constant RILEV_OK. */
	public final static String RILEV_OK = "OK";
	
	/** The Constant RILEV_KO. */
	public final static String RILEV_KO = "KO";
	
	/** The Constant RISPOSTA_SI. */
	private final static String RISPOSTA_SI="S";
	
	/** The Constant RISPOSTA_NO. */
	private final static String RISPOSTA_NO="N";
	
	/** The Constant MSG_AMMISSIBILI. */
	private final static String MSG_AMMISSIBILI = "Errore! I valori ammissibili sono " + "'" + RILEV_OK + "'" + " e '" + RILEV_KO + "'";
	
	/** The Constant MSG_AMMISSIBILI. */
	private final static String MSG_AMMISSIBILI1 = "Errore! I valori ammissibili sono " + "'" +  RISPOSTA_SI + " e '" + RISPOSTA_NO + "'";
	
	/** The Constant ERRORE_INTERO_MAGGIORE. */
	private static final String ERRORE_INTERO_MAGGIORE = "Errore. Insere un numero maggiore di %s: ";
	 
	/** The lettore. */
	private static Scanner lettore = creaScanner();
	 
 	/**
 	 * Crea scanner.
 	 *
 	 * @return the scanner
 	 */
	 private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	
	 /**
 	 * Leggi string.
 	 *
 	 * @param messaggio the messaggio
 	 * @return the string
 	 */
	public static String leggiString(String messaggio)
	{
		System.out.print(messaggio);
		return lettore.next();
	}
	
	/**
	 * Leggi double.
	 *
	 * @param messaggio the messaggio
	 * @return the double
	 */
	 public static double leggiDouble (String messaggio)
	 {
	 boolean finito = false;
	 double valoreLetto = 0;

	 do
	 	{
		 System.out.print(messaggio); 
		 if (lettore.hasNextDouble())
		 	{
		 		valoreLetto = lettore.nextDouble();
		 		finito = true;
		 	}
		 else
		 	{
		 		System.out.println(ERRORE_NUMERO);
		 		@SuppressWarnings("unused")
		 		String daButtare = lettore.next();
		 	}
	 	} 
	 while (!finito);
	 
	 return valoreLetto;
	 }
	
	 /**
 	 * Leggi string piena.
 	 *
 	 * @param messaggio the messaggio
 	 * @return the string
 	 */
	public static String leggiStringPiena (String messaggio)
	{
		System.out.print(messaggio);
		boolean esci = false;
		String nome = null;
		while (!esci)
		{
		nome = lettore.next();
		if (nome.trim().length() == 0)
		{
			System.out.print(ERRORE_STRINGA_VUOTA);
		}
		else
		{
			esci = true;
		}		
		}
		return nome.trim();
	}
	
	/**
	 * Leggi string piena no spazi.
	 *
	 * @param messaggio the messaggio
	 * @return the string
	 */
	public static String leggiStringPienaNoSpazi (String messaggio)
	{
		System.out.print(messaggio);
		boolean esci = false;
		String nome = null;
		while (!esci)
		{
		nome = lettore.next();
		if (nome.trim().length() != 0 && nome.trim().length() == nome.replaceAll("\\u0020","").length() )
		{
			esci = true;
		}
		else
		{
			System.out.print(ERRORE_STRINGA_VUOTA_O_SPAZI);
		}
		}
		return nome.trim();
	}
	 
	 
	/**
	 * Leggi char.
	 *
	 * @param messaggio the messaggio
	 * @return the char
	 */
	public static char leggiChar(String messaggio)
	{
		String letto = leggiStringPiena(messaggio);
		return letto.charAt(0);
	}
	
	/**
	 * Leggi int compreso.
	 *
	 * @param minimo the minimo
	 * @param massimo the massimo
	 * @param messaggio the messaggio
	 * @return the int
	 */
	public static int leggiIntCompreso(int minimo, int massimo, String messaggio)
	{
		boolean fine= false;
		int numero = 0;
		while(!fine)
		{
			numero = leggiInt(messaggio);
			if (numero >=minimo && numero <=massimo)
			{
				fine = true;
			}
			else
			{
				System.out.printf(ERRORE_INTERO_COMPRESO, minimo, massimo);
			}
		}
		return numero;
	}
	
	/**
	 * Leggi int.
	 *
	 * @param messaggio the messaggio
	 * @return the int
	 */
	public static int leggiInt(String messaggio)
	{		
		boolean fine = false;
		int numero = 0;		
		while(!fine)
		{
			System.out.print(messaggio);

			if(lettore.hasNextInt())
			{
				numero = lettore.nextInt();
				fine =true;
			}
			else
			{
				System.out.println(ERRORE_INTERO);
				@SuppressWarnings("unused")
				String daButtare = lettore.next();
			}
		}
		return numero;
	}	
	
	
	/**
	 * Leggi sesso.
	 *
	 * @param messaggio the messaggio
	 * @return the char
	 */
	public static char leggiSesso (String messaggio)
	{
		String toReturn = null;
		while (toReturn == null)
		{
			System.out.print(messaggio + "(M/m/f/F): ");
			String sesso = lettore.next();
			if ( sesso.toLowerCase().equals("m") || sesso.toLowerCase().equals("f"))
			{
				toReturn = sesso.toLowerCase();
			}
			else
			{
			System.out.print(ERRORE_SESSO);
			}
		}
	return toReturn.charAt(0);
	}
	
	/**
	 * Leggi array intero.
	 *
	 * @param lunghezza the lunghezza
	 * @param numerazione the numerazione
	 * @param messaggio the messaggio
	 * @return the int[]
	 */
    public static int[] leggiArrayIntero(int lunghezza, String numerazione, String messaggio)
    {
            System.out.println(messaggio);
            int array[] = new int [lunghezza];
            for(int i = 0; i < lunghezza ; i++)
                    array[i] = leggiInt(numerazione + (i + 1) + ":");
            return array;
    }
	
    /**
     * Leggi doppio arrayintero.
     *
     * @param lunghezza the lunghezza
     * @param messaggio the messaggio
     * @return the int[][]
     */
    public static int[][] leggiDoppioArrayintero (int lunghezza, String messaggio)
    {
    	System.out.println(messaggio);
    	int array[][] = new int [lunghezza][lunghezza];
    	for(int i=0;i<lunghezza; i++)
    		for(int j=0; j<lunghezza; j++)
    			array[i][j] = leggiInt("Riga " + (i + 1) +" e colonna "+ (j + 1) + ":");
    	return array;
    }
    
    /**
     * Yes or no.
     *
     * @param messaggio the messaggio
     * @return true, if successful
     */
    public static boolean yesOrNo(String messaggio)
	{
		  String mioMessaggio = messaggio + "(" + RISPOSTA_SI + "/" + RISPOSTA_NO + ")";
		  String inputDati = "";
		  boolean valoreCorretto = false;
		  do {
			  inputDati = leggiString(mioMessaggio);
			  if(inputDati.equalsIgnoreCase(RISPOSTA_SI)) 
				  valoreCorretto = true;
			  else if(inputDati.equalsIgnoreCase(RISPOSTA_NO))
				  return false;
			  else
				  System.out.println(MSG_AMMISSIBILI1);
		  } while(valoreCorretto==false);
		  return valoreCorretto;
	}
    
    /**
     * Ok or ko.
     *
     * @param messaggio the messaggio
     * @return the string
     */
    public static String okOrKo(String messaggio) {
    	String mioMessaggio = messaggio + "(" + RILEV_OK + "/" + RILEV_KO + ")";
    	String inputDati = "";
    	boolean valoreCorretto = false;
    	do {
    		inputDati = leggiString(mioMessaggio);
    		if(inputDati.equalsIgnoreCase(RILEV_OK) || inputDati.equalsIgnoreCase(RILEV_KO))
    			valoreCorretto = true;
    		else
    			System.out.println(MSG_AMMISSIBILI);
    	} while(valoreCorretto==false);
    	return inputDati;
    }
    
    /**
     * Leggi vector stringhe.
     *
     * @param messaggio the messaggio
     * @param domanda the domanda
     * @return the vector
     */
    public static Vector<String> leggiVectorStringhe(String messaggio, String domanda)
    {
    	System.out.println(messaggio);
    	Vector<String> vettore = new Vector<String>();
    	while(yesOrNo(domanda) )
    		vettore.add(Util.leggiStringPiena(messaggio) );
    	return vettore;
    }

    /**
     * Leggi int con minimo.
     *
     * @param messaggio the messaggio
     * @param minimo the minimo
     * @return the int
     */
	public static int leggiIntConMinimo(String messaggio, int minimo)
	{
		boolean fine= false;
		int numero = 0;
		while(!fine)
		{
			numero = leggiInt(messaggio);
			if (numero >=minimo)
			{
				fine = true;
			}
			else
			{
				System.out.printf(ERRORE_INTERO_MAGGIORE, minimo);
				System.out.println();
			}
		}
		return numero;
	}
}
