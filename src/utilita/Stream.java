/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package utilita;

import java.io.*;

/**
 * The Class Stream.
 */
public class Stream implements Serializable
{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;
			
			/** The Constant MSG_SALVA. */
			private static final String MSG_SALVA = "File salvato correttamente";
			
			/** The Constant DOMANDA_SOVRASCRIVERE. */
			private static final String DOMANDA_SOVRASCRIVERE = "File gia' presente. Sovrascrivere? ";
			
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

			private static final String ERRORE_PRINTSTREAM = "ERRORE. PRINTSTREAM NON APERTO";
			
			/**
			 * Carica file.
			 *
			 * @param f the f
			 * @param oggetto the oggetto
			 * @return the object
			 */
			public static Object caricaFile (File daCaricare, Object oggetto)
		    {
		            if (daCaricare.exists() )
		            {
		                    try
		                    {
		                    	oggetto = (Object) caricaSingoloOggetto(daCaricare);
		                    }
		                    catch (ClassCastException excCast)
		                    {
		                            System.out.println(NO_CAST);
		                    }
		                    finally
		                    {
		                            if (oggetto != null)
		                            {
		                                    return oggetto;
		                            }
		                    }
		            }
		            else
		            	System.out.println(NO_FILE);
					return null;    
		    }
		   
			/**
			 * Salva file.
			 *
			 * @param f the f
			 * @param oggetto the oggetto
			 * @param controlloSovrascrittura the controllo sovrascrittura
			 */
		    public static void salvaFile(File daSalvare, Object oggetto, Boolean controlloSovrascrittura)
		    {
		    	if (daSalvare.exists() )
		    		{
		    		if (controlloSovrascrittura)
		    			{
		    			if (Util.yesOrNo(DOMANDA_SOVRASCRIVERE) )
		    				salvaSingoloOggetto(daSalvare, oggetto);
		    			}
		    		else
		    			salvaSingoloOggetto(daSalvare, oggetto);		    			
		    		}		    	
		    	else
		    		salvaSingoloOggetto(daSalvare, oggetto);
		    }
			
			/**
			 * Carica singolo oggetto.
			 *
			 * @param f the f
			 * @return the object
			 */
			public static Object caricaSingoloOggetto(File daCaricare) 
			{
				Object letto = null;
				ObjectInputStream ingresso = null;
				
				try
				{
					ingresso = new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(daCaricare) ) );
					
					letto = ingresso.readObject();
				}
				catch (FileNotFoundException excNotFound)
				{
					System.out.println(NO_FILE + daCaricare.getName() );
				} 
				//Contiene anche FileNotFoundException, ma meglio tenerle entrambe.
				catch (IOException excLettura) 
				{
					System.out.println(NO_READ + daCaricare.getName() );
				}
				catch (ClassNotFoundException excCast)
				{
					System.out.printf(NO_CAST, daCaricare.getName() );
					System.out.println();
				}
				catch (ClassCastException erroreCast)
				{
					System.out.printf(NO_CAST, daCaricare.getName());
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
							System.out.println(NO_CLOSE + daCaricare.getName() );
						}
					}
				}				
				return letto;
			}
			
			/**
			 * Salva singolo oggetto.
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
			
			public static void scriviSuFile(String nomeFile, String daScrivere) {
				PrintStream scrivi = null;
				try {
					FileOutputStream reportStampabile = new FileOutputStream(nomeFile);
					scrivi = new PrintStream(reportStampabile);
					scrivi.println(daScrivere);
				}
				catch (IOException e)
				{
					System.out.println("Errore: " + e);
					System.exit(1);
				}
				finally {
					if (scrivi != null)
						scrivi.close();
				    else
				       System.out.println(ERRORE_PRINTSTREAM);
				}
			}
}
