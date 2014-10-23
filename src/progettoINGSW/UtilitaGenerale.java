package progettoINGSW;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UtilitaGenerale 
{
	private static Scanner lettore = creaScanner();
	private static final String ERRORE_STRINGA = "Attenzione! Inserire una stringa valida. ";
	private static final String MEX_NUM_NON_VALIDO = "Valore non valido. Inserire nuovamente ";
	private static final String ERRORE = "ERRORE! Il valore fornito ha un formato non compatibile";
	private static final String MEX_ERRORE = "\n\nAttenzione! Opzione non consentita. Digitare 'Y' per Si, 'N' per No";
	private static final String MEX_RICHIESTA = "\nCosa desideri fare? Digita 'Y' se vuoi inserire un altro CD, 'N' per uscire: ";
	private final static char RISPOSTA_SI='S';
	private final static char RISPOSTA_NO='N';
	private final static String ERRORE_AMMISSIBILI= "Attenzione: i caratteri ammissibili sono: ";
	private static String _String;
    private static char _char;
    private static BufferedReader br;
		
	
    private static Scanner creaScanner()
    {
   	 Scanner nuovoScanner = new Scanner(System.in);
   	 nuovoScanner.useDelimiter(System.getProperty("line.separator"));
   	 return nuovoScanner;
    }
	
	public static String leggiString(String messaggio)
    {   	
   		
   		boolean finito = false;
   		String str = null;
   		do
   		{
   			System.out.print(messaggio);
   			str = lettore.next();
   			if(str == "" || str.trim().equals(""))
   			{
   				System.out.println(ERRORE_STRINGA);
   			}  			
   			else
   			{
   				finito = true;
   			}
   		} while(!finito);
   		return str;
    }
	
	public static int leggiInteroConLimite1(String messaggio, int min)
	{
		boolean finito = false;
    	int valoreLetto = 0;
	    do
	    {
	    	System.out.print(messaggio);
	    	if(lettore.hasNextInt())
	    	{
	    		valoreLetto = lettore.nextInt();
	    		if(valoreLetto >= min)
	    		{
	    			finito = true;
	    		}
	    		else
	    			{
	    				System.out.println(MEX_NUM_NON_VALIDO);
	    			}
	    	}
	    	else
	    	{
	    		System.out.println(ERRORE);
	    		@SuppressWarnings("unused")
				String daButtare = lettore.next();
	    	}
	    } while(!finito);
	   	return valoreLetto;
	}
	
	public static int leggiInteroConLimite2(String messaggio, int min, int max)
	{
    	boolean finito = false;
    	int valoreLetto = 0;
	    do
	    {
	    	System.out.print(messaggio);
	    	if(lettore.hasNextInt())
	    	{
	    		valoreLetto = lettore.nextInt();
	    		if(valoreLetto >= min && valoreLetto <= max)
	    		{
	    			finito = true;
	    		}
	    		else
	    			{
	    				System.out.println(MEX_NUM_NON_VALIDO);
	    			}
	    	}
	    	else
	    	{
	    		System.out.println(ERRORE);
	    		@SuppressWarnings("unused")
				String daButtare = lettore.next();
	    	}
	    } while(!finito);
	   	return valoreLetto;
	}
	
	public static char leggiChar(String messaggio)
    {
    	boolean finito = false;
    	char valoreLetto = '\0';
    	do
    	{
    		System.out.print(messaggio);
    		String str = lettore.next();
    		if(str.length() > 0)
    		{
    			finito = true;
    			valoreLetto = str.charAt(0);
    		}
    		else
    		{
    			System.out.println(ERRORE_STRINGA);
    			@SuppressWarnings("unused")
				String daButtare = lettore.next();
    		}
    	} while(!finito);
    	return valoreLetto;	
    }
    
    public static char leggiCharUscita(String messaggio)
    {
    	System.out.println(MEX_RICHIESTA);
    	boolean finito = false;
    	do
    	{
    		System.out.print(messaggio);
    		br = new BufferedReader(new InputStreamReader(System.in)); 
    		try
    		{
    			_String = br.readLine();
    			if (_String.length() > 1)
    				throw new NumberFormatException();
    			_char = _String.charAt(0);
    		}
    		catch (IOException e1)
    		{
    			System.out.println ("errore di flusso");
    		}
    		catch (NumberFormatException e2)
    		{
    			System.out.println ("errore di input da tastiera");
    			return(0);
    		}
    		   	
    		if(_char == 'y' || _char == 'f' || _char == 'M' || _char == 'S')
    		{
    			finito = true;
    		}
    		else
    		{
    			System.out.println(MEX_ERRORE);
    		}
    	} while(!finito);
    	 
    	return(_char);
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
}
