/*
* @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
*/
package utilita;

import java.util.Vector;
import javax.swing.JOptionPane;

/**
 * The Class UtilitaStringhe.
 */
public class UtilitaStringhe
{

	/** The Constant SPAZIO. */
	public final static String SPAZIO = " ";
	
	/** The Constant ACAPO. */
	public final static String ACAPO = "\n";
	
	/** The Constant RIGASOTTO. */
	public final static String RIGASOTTO = "=";
	
	/** The Constant RIGALATO. */
	public final static String RIGALATO = "|";
	
	/** The Constant MARGINE. */
	public final static int MARGINE = 2;
	
	/** The Constant RIGA_ASTERISCHI. */
	public final static String RIGA_ASTERISCHI = "********************" +"********************" +"********************" +"********************"; 
	
	/** The Constant RIGA_BIANCA. */
	public final static String RIGA_BIANCA =    "                    " + "                    " +"                    " +"                    "; 
	
	/**
	 * A capo dopo.
	 *
	 * @param testo the testo
	 * @return the string
	 */
	public static String aCapoDopo(String testo)
	{
		String str = testo+ACAPO;
		return str;
	}
	
	/**
	 * Incornicia titolo.
	 *
	 * @param daIncorniciare titolo
	 */
	public static void incorniciaTitolo(String daIncorniciare)
	{
		rigaSotto(daIncorniciare.length());
		System.out.println();
		rigaLato();
		System.out.print(daIncorniciare);
		rigaLato();
		rigaSotto(daIncorniciare.length());
	}
	
	/**
	 * Incornicia menu.
	 *
	 * @param titoloMenu the titolo menu
	 * @param menu the menu
	 * @param spuntaDivisioneRighe the spunta divisione righe
	 */
	public static void incorniciaMenu(String titoloMenu, Vector<String> menu, int spuntaDivisioneRighe)
	{
		//titolo centrato del menu'
		System.out.print(titoloMenu);
		//
		//Cerco la stringa con lunghezza massima.
		int lunghezzaMassima = OurMath.lunghezzaMassimaVectorString(menu);
		//
		
		rigaSotto(lunghezzaMassima);
		System.out.println();
		for (int i = 0; i < menu.size(); i++)
			{
			rigaLato();
			System.out.print(incolonna(menu.get(i), lunghezzaMassima));
			rigaLato();
			
			//Divisione per ogni riga.
			if(spuntaDivisioneRighe == 1)
				rigaSotto(lunghezzaMassima);
			
			if (i != menu.size() - 1)
				System.out.println();
			}
		//toglie l'ultima riga (che sarebbe altrimenti ripetuta), se attivata la divisione per ogni riga.
		if(spuntaDivisioneRighe == 0)
			rigaSotto(lunghezzaMassima);
		
		System.out.println();
	}
	
	/**
	 * Indenta.
	 *
	 * @param daIndentare testo
	 * @param carattereIndentazione the carattere
	 * @param profonditaIndentazione the profondita
	 * @return the string
	 */
	public static String indenta(String daIndentare, String carattereIndentazione, int profonditaIndentazione)
	{
		StringBuffer daRestituire = new StringBuffer();
		for(int i = 0; i<profonditaIndentazione; i++)
			daRestituire.append(carattereIndentazione);
		daRestituire.append(daIndentare);
		return daRestituire.toString();
	}
	
	//Chiamata rigaSotto perche' pronta per quello scopo, se la uso sopra devo sistemarla con un a capo.
	/**
	 * Riga sotto.
	 *
	 * @param lunghezzaTitolo the lunghezza titolo
	 */
	public static void rigaSotto(int lunghezzaTitolo)
	{	
		System.out.println();
		for (int i = 0; i < lunghezzaTitolo + MARGINE; i++)
		System.out.print(RIGASOTTO);
	}

	/**
	 * Riga lato.
	 */
	public static void rigaLato()
	{
		System.out.print(RIGALATO);
	}
	
	/**
	 * Input dialog.
	 *
	 * @param messaggio the messaggio
	 * @return the int
	 */
	public static int inputDialog(String messaggio)
	{
		String input = JOptionPane.showInputDialog(messaggio);
		Integer numero = Integer.parseInt(input);
		return numero;
	}
	
	/**
	 * Incolonna.
	 *
	 * @param daIncolonnare da incolonnare
	 * @param larghezza the larghezza
	 * @return the string
	 */
	public static String incolonna (String daIncolonnare, int larghezza)
		{
		StringBuffer res = new StringBuffer(larghezza);
		int numCharDaStampare = Math.min(larghezza,daIncolonnare.length());
		res.append(daIncolonnare.substring(0, numCharDaStampare));
		for (int i=daIncolonnare.length()+1; i<=larghezza; i++)
			res.append(SPAZIO);
		return res.toString();
		}
	
	 /**
 	 * Centrata.
 	 *
 	 * @param daCentrare da centrare
 	 * @param larghezza the larghezza
 	 * @return the string
 	 */
	 public static String centrata (String daCentrare, int larghezza)
		{
		 StringBuffer res = new StringBuffer(larghezza);
		 if (larghezza <= daCentrare.length())
			res.append(daCentrare.substring(larghezza));
		 else
			{
			 int spaziPrima = (larghezza - daCentrare.length())/2;
			 int spaziDopo = larghezza - spaziPrima - daCentrare.length();
			 for (int i=1; i<=spaziPrima; i++)
				res.append(SPAZIO);
				
			 res.append(daCentrare);
			
			 for (int i=1; i<=spaziDopo; i++)
				res.append(SPAZIO);
			}
		 	 return res.toString();

		}
	 
	 /**
 	 * Ripeti char.
 	 *
 	 * @param charDaRipetere carattere da ripetere
 	 * @param ripetizioni  lunghezza
 	 * @return the string
 	 */
	 public static String ripetiChar (char charDaRipetere, int ripetizioni)
		{
			StringBuffer result = new StringBuffer(ripetizioni);
			for (int i = 0; i < ripetizioni; i++)
				{
				result.append(charDaRipetere);
				}
			return result.toString();
		}	 

		/**
		 * Ripeti stringa.
		 *
		 * @param daRipetere stringa da ripetere
		 * @param ripetizioni quante volte
		 * @return the string
		 */
	 public static String ripetiStringa(String daRipetere, int ripetizioni)
		{
			StringBuffer result = new StringBuffer(ripetizioni);
			for (int i = 0; i < ripetizioni; i++)
			{
				result.append(daRipetere);
			}
			return result.toString();
		}
	 
	 /**
 	 * Incornicia stringa.
 	 *
 	 * @param daIncorniciare the da incorniciare
 	 * @return the string
 	 */
 	public static String incorniciaStringa(String daIncorniciare) {
		 int lunghezza = daIncorniciare.length();
		 //Costruzione delle righe che costituiranno la cornice
		 String rigaCornice, rigaIntermedia, rigaTesto, spazi;
		 rigaCornice = RIGA_ASTERISCHI.substring(0, lunghezza + 6);
		 spazi = RIGA_BIANCA.substring(0, lunghezza + 4);
		 rigaIntermedia = "*" + spazi + "*";
		 rigaTesto = "*  " + daIncorniciare + "  *";
		    
		 //Creazione StringBuffer con la cornice
		 StringBuffer daRestituire = new StringBuffer();
		 daRestituire.append(rigaCornice+ACAPO);
		 daRestituire.append(rigaIntermedia+ACAPO);
		 daRestituire.append(rigaTesto+ACAPO);
		 daRestituire.append(rigaIntermedia+ACAPO);
		 daRestituire.append(rigaCornice+ACAPO);
		 
		 //Restituzione del risultato
		 return daRestituire.toString();
	 }
}

