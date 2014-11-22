/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package utilita;

import java.util.Vector;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class GUI.
 */
public class GUI
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
	 * @param titolo the titolo
	 */
	public static void incorniciaTitolo(String titolo)
	{
		rigaSotto(titolo.length());
		System.out.println();
		rigaLato();
		System.out.print(titolo);
		rigaLato();
		rigaSotto(titolo.length());
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
	 * @param testo the testo
	 * @param carattere the carattere
	 * @param profondita the profondita
	 * @return the string
	 */
	public static String indenta(String testo, String carattere, int profondita)
	{
		StringBuffer daRestituire = new StringBuffer();
		for(int i = 0; i<profondita; i++)
			daRestituire.append(carattere);
		daRestituire.append(testo);
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
	 * @param s the s
	 * @param larghezza the larghezza
	 * @return the string
	 */
	public static String incolonna (String s, int larghezza)
		{
		StringBuffer res = new StringBuffer(larghezza);
		int numCharDaStampare = Math.min(larghezza,s.length());
		res.append(s.substring(0, numCharDaStampare));
		for (int i=s.length()+1; i<=larghezza; i++)
			res.append(SPAZIO);
		return res.toString();
		}
	
	 /**
 	 * Centrata.
 	 *
 	 * @param s the s
 	 * @param larghezza the larghezza
 	 * @return the string
 	 */
	 public static String centrata (String s, int larghezza)
		{
		 StringBuffer res = new StringBuffer(larghezza);
		 if (larghezza <= s.length())
			res.append(s.substring(larghezza));
		 else
			{
			 int spaziPrima = (larghezza - s.length())/2;
			 int spaziDopo = larghezza - spaziPrima - s.length();
			 for (int i=1; i<=spaziPrima; i++)
				res.append(SPAZIO);
				
			 res.append(s);
			
			 for (int i=1; i<=spaziDopo; i++)
				res.append(SPAZIO);
			}
		 	 return res.toString();

		}
	 
	 /**
 	 * Ripeti char.
 	 *
 	 * @param carattere the carattere
 	 * @param lunghezza the lunghezza
 	 * @return the string
 	 */
	 public static String ripetiChar (char carattere, int lunghezza)
		{
			StringBuffer result = new StringBuffer(lunghezza);
			for (int i = 0; i < lunghezza; i++)
				{
				result.append(carattere);
				}
			return result.toString();
		}	 

		/**
		 * Ripeti stringa.
		 *
		 * @param stringa the stringa
		 * @param lunghezza the lunghezza
		 * @return the string
		 */
	 public static String ripetiStringa(String stringa, int lunghezza)
		{
			StringBuffer result = new StringBuffer(lunghezza);
			for (int i = 0; i < lunghezza; i++)
			{
				result.append(stringa);
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
