package progettoINGSW;

import java.io.*;

/**
 * Classe di utilita'. Contiene metodi per lo stream di un oggetto.
 * @author Lorenzo Rubagotti
 */
public class SalvaCarica implements Serializable
{
			private static final long serialVersionUID = 1L;
			
			private static final String MSG_OK_FILE = "File caricato correttamente.";
			private static final String MSG_SALVA = "File salvato correttamente";
			private static final String DOMANDA_SOVRASCRIVERE = "File gia' presente. Sovrascrivere? ";
			private static final String NO_CLOSE = "ATTENZIONE: PROBLEMI CON LA CHIUSURA DEL FILE ";
			private static final String NO_FILE = "ATTENZIONE: NON TROVO IL FILE ";
			private static final String NO_READ = "ATTENZIONE: PROBLEMI CON LA LETTURA DEL FILE ";
			private static final String NO_CAST = "ATTENZIONE FILE CON CAST NON CORRISPONDENTE: LETTURA DEL FILE FALLITA";
			private static final String NO_WRITE = "ATTENZIONE: PROBLEMI CON LA SCRITTURA DEL FILE ";

			/**
			 * Metodo automatico per caricare un file.
			 * @param f File in questione.
			 * @param oggetto Oggetto da caricare.
			 * @return Restituisce l'oggetto caricato.
			 */
			public static Object caricaFile (File f, Object oggetto)
		    {
		            if (f.exists() )
		            {
		                    try
		                    {
		                    	oggetto = (Object) caricaSingoloOggetto(f);
		                    }
		                    catch (ClassCastException excCast)
		                    {
		                            System.out.println(NO_CAST);
		                    }
		                    finally
		                    {
		                            if (oggetto != null)
		                            {
		                                    System.out.println(MSG_OK_FILE);
		                                    return oggetto;
		                            }
		                    }
		            }
		            else
		            	System.out.println(NO_FILE);
					return null;    
		    }
		   
			/**
			 * Metodo per salvare un file automatizzando tutto.
			 * @param f File in questione.
			 * @param oggetto Oggetto da salvare.
			 * @param controlloSovrascrittura Attiva, true, o disattiva, false, il controllo della sovra-scrittura del file.
			 */
		    public static void salvaFile(File f, Object oggetto, Boolean controlloSovrascrittura)
		    {
		    	if (f.exists() )
		    		{
		    		if (controlloSovrascrittura)
		    			{
		    			if (UtilitaGenerale.yesOrNo(DOMANDA_SOVRASCRIVERE) )
		    				salvaSingoloOggetto(f, oggetto);
		    			}
		    		else
		    			salvaSingoloOggetto(f, oggetto);		    			
		    		}		    	
		    	else
		    		salvaSingoloOggetto(f, oggetto);
		    }
			
			/**
			 * Metodo necessario per caricare un Oggetto da un file. Riceve il file e restituisce l'oggetto salvato nel file.
			 * @param File f File in questione.
			 * @return Object Restituisce l'oggetto letto dal file.
			 */
			public static Object caricaSingoloOggetto (File f) 
			{
				Object letto = null;
				ObjectInputStream ingresso = null;
				
				try
				{
					ingresso = new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(f) ) );
					
					letto = ingresso.readObject();
				}
				catch (FileNotFoundException excNotFound)
				{
					System.out.println(NO_FILE + f.getName() );
				} 
				//Contiene anche FileNotFoundException, ma meglio tenerle entrambe.
				catch (IOException excLettura) 
				{
					System.out.println(NO_READ + f.getName() );
				}
				catch (ClassNotFoundException excCast)
				{
					System.out.println(NO_CAST + f.getName() );
				}
				
				finally
				{
					if (ingresso != null)
					{
						try
						{
							ingresso.close();
						}
						catch (IOException excChiusura)
						{
							System.out.println(NO_CLOSE + f.getName() );
						}
					}
				}				
				return letto;
			}
			
			/**
			 * Metodo necessario per salvare un Oggetto su un File f. Riceve il file sul quale salvare e l'oggetto da salvare e procede al salvataggio.
			 * @param File f File in questione.
			 * @param Object daSalvare L'oggetto che si va a salvare nel file.
			 */
			public static void salvaSingoloOggetto (File f, Object daSalvare)
			{
				ObjectOutputStream uscita = null;
				
				try
				{
					uscita = new ObjectOutputStream(
							new BufferedOutputStream(
									new FileOutputStream(f) ) );
					uscita.writeObject(daSalvare);
					System.out.println(MSG_SALVA);
				}
				catch (IOException excScrittura)
				{
					System.out.println(NO_WRITE + f.getName() );
				}
				
				finally
				{
					if (uscita != null)
					{
						try
						{
							uscita.close();				            
						}
						catch (IOException excChiusura)
						{
							System.out.println(NO_CLOSE + f.getName() );
						}
					}
				}
			}
}
