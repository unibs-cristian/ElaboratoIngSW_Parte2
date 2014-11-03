package testSuiteDiagnosi;

import java.io.Serializable;

public class Coppia implements Serializable {
	private static final long serialVersionUID = 1L;
	private CamminoAzioni insiemeCammino;
	private String valoreRilevazione;
	
	public Coppia(CamminoAzioni _insiemeCammino, String _valoreRilevazione) {
		insiemeCammino = _insiemeCammino;
		valoreRilevazione = _valoreRilevazione;
	}
	
	public String getValoreRilevazione() {
		return valoreRilevazione;
	}
	 
	public CamminoAzioni getInsiemeCammino() {
		return insiemeCammino;
	}
	
	public boolean isEqual(Coppia altra) {
		// Confronta i valori della rilevazione. Se non coincidono le coppie sono diverse.
		if(altra.getValoreRilevazione().equalsIgnoreCase(getValoreRilevazione()) == false)
			return false;
		// Se i valori della rilevazione sono uguali, viene effettuato il confronto degli insiemi dei cammini.
		else
			return getInsiemeCammino().isEqual(altra.getInsiemeCammino());
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("Insieme del cammino ---> " + insiemeCammino.toString() + "\n");
		risultato.append("Valore della rilevazione ---> "+valoreRilevazione + "\n");
		return risultato.toString();
	}
}