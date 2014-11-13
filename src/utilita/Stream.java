/*
 * 
 */
package utilita;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * Classe di utilita'. Contiene metodi per lo stream di un oggetto.
 * @author Lorenzo Rubagotti
 */
public class Stream implements Serializable
{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;
			
			/** The Constant MSG_OK_FILE. */
			private static final String MSG_OK_FILE = "File caricato correttamente.";
			
			/** The Constant MSG_SALVA. */
			private static final String MSG_SALVA = "File salvato correttamente";
			
			/** The Constant DOMANDA_SOVRASCRIVERE. */
			private static final String DOMANDA_SOVRASCRIVERE = "File gi� presente. Sovrascrivere? ";
			
			/** The Constant NO_CLOSE. */
			private static final String NO_CLOSE = "ATTENZIONE: PROBLEMI CON LA CHIUSURA DEL FILE ";
			
			/** The Constant NO_FILE. */
			private static final String NO_FILE = "ATTENZIONE: NON TROVO IL FILE ";
			
			/** The Constant NO_READ. */
			private static final String NO_READ = "ATTENZIONE: PROBLEMI CON LA LETTURA DEL FILE ";
			
			/** The Constant NO_CAST. */
			private static final String NO_CAST = "ATTENZIONE FILE CON CAST NON CORRISPONDENTE: LETTURA DEL FILE %s FALLITA";
			
			/** The Constant NO_WRITE. */
			private static final String NO_WRITE = "ATTENZIONE: PROBLEMI CON LA SCRITTURA DEL FILE ";

			/**
			 * Metodo per caricare un file automatizzando tutto.
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
		    			if (Util.yesOrNo(DOMANDA_SOVRASCRIVERE) )
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
			 *
			 * @param f the f
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
					System.out.printf(NO_CAST, f.getName() );
					System.out.println();
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
			 *
			 * @param f the f
			 * @param daSalvare the da salvare
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
