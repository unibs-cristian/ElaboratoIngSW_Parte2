package testSuiteDiagnosi;

import java.util.Vector;
import gestioneModello.Azione;

public class Coppia {
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
}