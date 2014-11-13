/*
 * 
 */
package utilita;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * Svariate utilita' di lettura.
 * @author Lorenzo Rubagotti.
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
	
	/** The Constant ERRORE_AMMISSIBILI. */
	private final static String ERRORE_AMMISSIBILI= "Attenzione: i caratteri ammissibili sono: ";
	
	/** The Constant RILEV_OK. */
	public final static String RILEV_OK = "OK";
	
	/** The Constant RILEV_KO. */
	public final static String RILEV_KO = "KO";
	
	/** The Constant MSG_AMMISSIBILI. */
	private final static String MSG_AMMISSIBILI = "Errore! I valori ammissibili sono " + "'" + RILEV_OK + "'" + " e '" + RILEV_KO + "'";
	
	/** The Constant RISPOSTA_SI. */
	private final static char RISPOSTA_SI='S';
	
	/** The Constant RISPOSTA_NO. */
	private final static char RISPOSTA_NO='N';
	
	/** The Constant ERRORE_INTERO_MAGGIORE. */
	private static final String ERRORE_INTERO_MAGGIORE = "Errore. Insere un numero maggiore di %s: ";
	 
	/** The lettore. */
	private static Scanner lettore = creaScanner();
	 /**
	  * Metodo che delimita lo scanner cosi' che non prende piu' l'invio.
	  * @return Restituisce lo scanner delimitato.
	  */
	 private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	
	 /**
	  * Metodo che si occupa di leggere una stringa.
	  * @param messaggio Testo a video prima dell'acquisizione di una stringa.
	  * @return Restituisce la stringa acquisita.
	  */
	public static String leggiString(String messaggio)
	{
		System.out.print(messaggio);
		return lettore.next();
	}
	
	/**
	 * Metodo che si occupa di leggere un numero double da tastiera.
	 * @param messaggio Messaggio a video che precede l'acquisizione del numero double.
	 * @return Restituisce il numero double acquisito da tastiera.
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
	  * Metodo che si occupa di leggere una stringa. Evita le stringhe vuote. Accetta gli spazi.
	  * @param messaggio Testo a video prima dell'acquisizione della stringa.
	  * @return Restituisce la stringa acquisita.
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
	 * Metodo che si occupa di leggere una stringa. Evita le stringhe vuote e gli spazi.
	 * @param messaggio Testo a video prima dell'acquisizione della stringa.
	 * @return Restituisce la stringa acquisita.
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
	 * Metodo che si occupa di acquisire il primo carattere di una stringa.
	 * @param messaggio Testo a video prima di acquisire il carattere iniziale.
	 * @return restituisce il carattere iniziare della stringa inserita.
	 */
	public static char leggiChar(String messaggio)
	{
		String letto = leggiStringPiena(messaggio);
		return letto.charAt(0);
	}
	
	/**
	 * Metodo che si occupa di leggere un intero da tastiera e accettarlo solo se compreso tra i numeri a e b.
	 * @param minimo Estremo sinistro dell'intervallo di numeri accettabili. E' compreso.
	 * @param massimo Estremo destro dell'intervallo di numeri accettabili. E' compreso.
	 * @param messaggio Testo a video prima dell'acquisizione del numero.
	 * @return Restituisce il numero inserito da tastiera. Lo accetta solo se nell'intervallo compreso tra a e b.
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
	 * Metodo che si occupa di leggere un intero da tastiera. Chiede il reinserimento se non è un numero intero.
	 * @param messaggio Testo a video prima dell'inserimento del numero da tastiera.
	 * @return Restituisce il numero inserito da tastiera.
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
	 * Metodo che si occupa di leggere il sesso restituendo il primo carattere della stringa, la quale deve iniziare con m, M, f o F. Altrimenti vi è il reinserimento.
	 * @param messaggio Testo a video prima dell'acquisizione del sesso.
	 * @return Restituisce il carattere m o f.
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
	 * Metodo che si occupa di leggere un array di interi, data la sua lunghezza.
	 * @param lunghezza Lunghezza dell'array di interi.
	 * @param numerazione Testo ad ogni blocco dell'array.
	 * @param messaggio Testo a video prima della lettura dell'array di interi.
	 * @return Restituisce un array di interi.
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
     * Metodo che si occupa di leggere una matrice quadrata di interi, data la dimensione.
     * @param lunghezza Dimensione della matrice
     * @param messaggio Testo a video prima della lettura della matrice.
     * @return Restituisce una matrice quadrata di interi.
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
     * Metodo che si occupa di chiedere all'utente di rispondere si o no ad un messaggio.
     * @param messaggio Testo a video della domanda alla quale rispondere.
     * @return Restituisce true in caso di risposta affermativa e false in caso di risposta negativa.
     */
    public static boolean yesOrNo(String messaggio)
	{
		  String mioMessaggio = messaggio + "(" + RISPOSTA_SI + "/" + RISPOSTA_NO + ")";
		  char valoreLetto = leggiUpperChar(mioMessaggio,String.valueOf(RISPOSTA_SI) + "//" + String.valueOf(RISPOSTA_NO));
		  
		  if (valoreLetto == RISPOSTA_SI)
			return true;
		  else
			return false;
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
     * Metodo che si occupa di far scegliere tramite un carattere.
     * @param messaggio Testo a video che ti chiede la scelta.
     * @param ammissibili Caratteri ammissibili.
     * @return Restituisce il primo carattere di una Stringa date le scelte ammissibili.
     */
    public static char leggiUpperChar (String messaggio, String ammissibili)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	   {
	   valoreLetto = leggiChar(messaggio);
	   valoreLetto = Character.toUpperCase(valoreLetto);
	   if (ammissibili.indexOf(valoreLetto) != -1)
		   finito  = true;
	   else
		   System.out.println(ERRORE_AMMISSIBILI + ammissibili);
	   } 
	   while (!finito);
	   
	   return valoreLetto;
	  }
    
    /**
     * Metodo che legge un vector di stringhe.
     * @param messaggio Testo a video prima di acquisire il vettore.
     * @param domanda Domanda che ti chiede se proseguire o no.
     * @return Restituisce il vettore.
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
     * Metodo che legge un intero dato un minimo. Accetta solo quelli maggiori e uguali a quel numero.
     * @param messaggio Messaggio che precede l'acquisizione.
     * @param minimo Minimo.
     * @return Restituisce il numero letto. Se non è accettabile chiede il reinserimento.
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
