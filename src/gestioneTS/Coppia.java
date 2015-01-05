/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;

import java.io.Serializable;

/**
 * La Classe Coppia.
 * Una coppia si riferisce ad una data classe di equivalenza ed e' costituita da un insieme 
 * del cammino e dal relativo valore della rilevazione effettuata.
 */
public class Coppia implements Serializable {
	
	/** Costante per il salvataggio. */
	private static final long serialVersionUID = 1L;
	
	/** L'insieme del cammino della coppia. */
	private CamminoAzioni insiemeCammino;
	
	/** Il valore della rilevazione. */
	private String valoreRilevazione;
	
	/**
	 * Costruttore della classe coppia.
	 *
	 * @param _insiemeCammino : l'insieme del cammino da assegnare alla coppia.
	 * @param _valoreRilevazione : il valore della rilevazione da assegnare alla coppia.
	 */
	public Coppia(CamminoAzioni _insiemeCammino, String _valoreRilevazione) {
		insiemeCammino = _insiemeCammino;
		valoreRilevazione = _valoreRilevazione;
	}
	
	/**
	 * Fornisce il valore rilevazione.
	 *
	 * @return il valore della rilevazione
	 */
	public String getValoreRilevazione() {
		return valoreRilevazione;
	}
	 
	/**
	 * Fornisce l'insieme del cammino.
	 *
	 * @return l'insieme del cammino
	 */
	public CamminoAzioni getInsiemeCammino() {
		return insiemeCammino;
	}
	
	/**
	 * Controlla se due coppie sono uguali, ovvero hanno stesso insieme del cammino e stesso 
	 * valore della rilevazione.
	 *
	 * @param altra : la coppia con cui confrontare
	 * @return true, se le due coppie sono uguali, false altrimenti.
	 */
	public boolean isEqual(Coppia altra) {
		// Confronta i valori della rilevazione. Se non coincidono le coppie sono diverse.
		if(altra.getValoreRilevazione().equalsIgnoreCase(getValoreRilevazione()) == false)
			return false;
		// Se i valori della rilevazione sono uguali, viene effettuato il confronto degli insiemi dei cammini.
		else
			return insiemeCammino.isEqual(altra.getInsiemeCammino());
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("Insieme del cammino ---> " + insiemeCammino.toString() + "\n");
		risultato.append("Valore della rilevazione ---> "+valoreRilevazione + "\n");
		return risultato.toString();
	}
}
