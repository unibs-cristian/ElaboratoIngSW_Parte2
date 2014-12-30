/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;
import java.util.Vector;
import java.io.Serializable;

/**
 * La Classe ClasseEquivalenza.
 * Una classe di equivalenza di un Test Suite e' costituita un insieme di coppie 
 * insieme del cammino - valore della rilevazione.
 * 
 * INVARIANTE DI CLASSE : 
 * Non contiene coppie uguali tra loro. 
 */
public class ClasseEquivalenza implements Serializable {

	/** Costante per il salvataggio. */
	private static final long serialVersionUID = 1L;

	/** La cardinalita' della classe. */
	private int cardinalita;
	
	/** Il cammino globale della classe di equivalenza. */
	private CamminoAzioni camminoGlobale;
	
	/** L'insieme di coppie insieme del cammino - valore della rilevazione. */
	private Vector<Coppia> elencoCoppie;

	/**
	 * Costruttore della classe ClasseEquivalenza
	 *
	 * @param _cardinalita : la cardinalita' da assegnare alla classe.
	 */
	public ClasseEquivalenza(int _cardinalita) {
		cardinalita = _cardinalita;
		elencoCoppie = new Vector<Coppia>(); 
	}
	 
	/**
	 * Aggiunge la coppia alla classe.
	 *
	 * @param daAggiungere : la coppia da aggiungere
	 */
	public void addCoppia(Coppia daAggiungere) {
		// PRECONDIZIONE
		assert daAggiungere!=null : "Violata precondizione metodo addCoppia. Passata coppia null.";
		
		int sizeVecchia = elencoCoppie.size();
		elencoCoppie.add(daAggiungere);
		
		// POSTCONDIZIONE
		assert elencoCoppie.size() == sizeVecchia+1 : "Violata postcondizione metodo addCoppia. La coppia non e' stata inserita.";
	}
	
	/**
	 * Fornisce la cardinalita' della classe.
	 *
	 * @return il valore dell'attributo cardinalita'.
	 */
	public int getCardinalita() {
		return cardinalita;
	}
	
	/**
	 * Fornisce le coppie della classe
	 *
	 * @return la struttura dati contenente le coppie
	 */
	public Vector <Coppia> getElencoCoppie() {
		return elencoCoppie;
	}
	
	/**
	 * Fornisce una coppia ad uno specifico indice.
	 *
	 * @param index : la posizione della coppia nel Vector
	 * @return la coppia avente quell'indice nel Vector
	 */
	public Coppia getCoppiaAt(int index) {
		return elencoCoppie.elementAt(index);
	}
	
	/**
	 * Fornisce il numero di coppie della classe di equivalenza
	 *
	 * @return la dimensione della struttura dati contenente le coppie
	 */
	public int getNumeroCoppie() {
		return elencoCoppie.size();
	}
	
	/**
	 * Fornisce il cammino globale
	 *
	 * @return il cammino globale
	 */
	public CamminoAzioni getCamminoGlobale() {
		return camminoGlobale;
	}
	
	/** 
	 * Controlla se una coppia e' gia' stata inserita come parte 
	 * dell'insieme di copertura di una classe di equivalenza
	 * 
	 * @param c : la coppia di cui verificare la presenza
	 * @return true, se la coppia e' presente, false altrimenti.
	 * @throws NullPointerException se c == null
	 */
	public boolean giaPresente(Coppia c) {
		//PRECONDIZIONE
		if(c==null)
			throw new NullPointerException("Violata precondizione metodo giaPresente. Inserita coppia nulla.");
		
		for(int i=0; i<getNumeroCoppie(); i++)
			if(getCoppiaAt(i).isEqual(c))
				return true;
		return false;
	}
	
	/**
	 * Controlla se due classi di equivalenza sono uguali.
	 *
	 * @param altra : la classe da confrontare
	 * @return true, se le due classi sono uguali, false altrimenti.
	 */
	public boolean isEqual(ClasseEquivalenza altra) {
		// Se le due classi hanno diverso cammino globale o diverso numero di coppie, allora sono diverse.
		if(camminoGlobale.isEqual(altra.getCamminoGlobale()) == false || getNumeroCoppie() != altra.getNumeroCoppie())
			return false;
		
		else
		{
			for(int i=0; i<getNumeroCoppie(); i++) 
				if(getCoppiaAt(i).isEqual(altra.getCoppiaAt(i)) == false)
					return false;
		}
		return true;
	}
	
	/**
	 * Setta il cammino globale.
	 *
	 * @param cammGlob : il cammino globale da assegnare alla classe di equivalenza
	 */
	public void setCamminoGlobale(CamminoAzioni cammGlob) {
		camminoGlobale = cammGlob;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("Cardinalita' = " + cardinalita + "\n");
		risultato.append("Cammino Globale --> " + camminoGlobale.toString() + "\n");
		for(int i=0; i<getNumeroCoppie(); i++) {
			risultato.append(String.format("Coppia n.%d\n" + getCoppiaAt(i).toString(),i+1));
		}
		
		return risultato.toString();
	}
}
