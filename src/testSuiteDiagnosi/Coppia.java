/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package testSuiteDiagnosi;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Coppia.
 */
public class Coppia implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The insieme cammino. */
	private CamminoAzioni insiemeCammino;
	
	/** The valore rilevazione. */
	private String valoreRilevazione;
	
	/**
	 * Instantiates a new coppia.
	 *
	 * @param _insiemeCammino the _insieme cammino
	 * @param _valoreRilevazione the _valore rilevazione
	 */
	public Coppia(CamminoAzioni _insiemeCammino, String _valoreRilevazione) {
		insiemeCammino = _insiemeCammino;
		valoreRilevazione = _valoreRilevazione;
	}
	
	/**
	 * Gets the valore rilevazione.
	 *
	 * @return the valore rilevazione
	 */
	public String getValoreRilevazione() {
		return valoreRilevazione;
	}
	 
	/**
	 * Gets the insieme cammino.
	 *
	 * @return the insieme cammino
	 */
	public CamminoAzioni getInsiemeCammino() {
		return insiemeCammino;
	}
	
	/**
	 * Checks if is equal.
	 *
	 * @param altra the altra
	 * @return true, if is equal
	 */
	public boolean isEqual(Coppia altra) {
		// Confronta i valori della rilevazione. Se non coincidono le coppie sono diverse.
		if(altra.getValoreRilevazione().equalsIgnoreCase(getValoreRilevazione()) == false)
			return false;
		// Se i valori della rilevazione sono uguali, viene effettuato il confronto degli insiemi dei cammini.
		else
			return getInsiemeCammino().isEqual(altra.getInsiemeCammino());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("Insieme del cammino ---> " + insiemeCammino.toString() + "\n");
		risultato.append("Valore della rilevazione ---> "+valoreRilevazione + "\n");
		return risultato.toString();
	}
}