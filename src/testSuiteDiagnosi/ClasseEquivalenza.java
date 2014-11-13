/*
 * 
 */
package testSuiteDiagnosi;
import java.util.Vector;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ClasseEquivalenza.
 */
public class ClasseEquivalenza implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  La cardinalita' della classe di equivalenza. */
	private int cardinalita;
	
	/**  Cammino di esecuzione globale relativo alla classe di equivalenza. */
	private CamminoAzioni camminoGlobale;
	
	/**  Attributo che contiene le coppie (Insieme del cammino, valore della rilevazione). */
	private Vector<Coppia> elencoCoppie;

	/**
	 * Instantiates a new classe equivalenza.
	 *
	 * @param _cardinalita the _cardinalita
	 */
	public ClasseEquivalenza(int _cardinalita) {
		cardinalita = _cardinalita;
		elencoCoppie = new Vector<Coppia>(); 
	}
	 
	/**
	 * Adds the coppia.
	 *
	 * @param daAggiungere the da aggiungere
	 */
	public void addCoppia(Coppia daAggiungere) {
		elencoCoppie.add(daAggiungere);
	}
	
	/**
	 * Gets the cardinalita.
	 *
	 * @return the cardinalita
	 */
	public int getCardinalita() {
		return cardinalita;
	}
	
	/**
	 * Gets the elenco coppie.
	 *
	 * @return the elenco coppie
	 */
	public Vector <Coppia> getElencoCoppie() {
		return elencoCoppie;
	}
	
	/**
	 * Gets the coppia at.
	 *
	 * @param index the index
	 * @return the coppia at
	 */
	public Coppia getCoppiaAt(int index) {
		return elencoCoppie.elementAt(index);
	}
	
	/**
	 * Gets the numero coppie.
	 *
	 * @return the numero coppie
	 */
	public int getNumeroCoppie() {
		return elencoCoppie.size();
	}
	
	/**
	 * Gets the cammino globale.
	 *
	 * @return the cammino globale
	 */
	public CamminoAzioni getCamminoGlobale() {
		return camminoGlobale;
	}
	
	/**
	 * Checks if is equal.
	 *
	 * @param altra the altra
	 * @return true, if is equal
	 */
	public boolean isEqual(ClasseEquivalenza altra) {
		// Se le due classi hanno diverso cammino globale o diverso numero di coppie, allora sono diverse.
		if(camminoGlobale.isEqual(altra.getCamminoGlobale()) == false || getNumeroCoppie() != altra.getNumeroCoppie())
			return false;
		// 
		else
		{
			for(int i=0; i<getNumeroCoppie(); i++) 
				if(getCoppiaAt(i).isEqual(altra.getCoppiaAt(i)) == false)
					return false;
		}
		return true;
	}
	
	/**
	 * Sets the cammino globale.
	 *
	 * @param cammGlob the new cammino globale
	 */
	public void setCamminoGlobale(CamminoAzioni cammGlob) {
		camminoGlobale = cammGlob;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
