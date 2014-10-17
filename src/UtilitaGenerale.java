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
	
	public static int leggiInteroConLimite(String messaggio, int min, int max)
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
}
