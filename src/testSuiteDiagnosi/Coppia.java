package testSuiteDiagnosi;

import java.util.Vector;
import gestioneModello.Azione;

public class Coppia {
	private Vector<Azione> insiemeCammino;
	private String valoreRilevazione;
	
	public Coppia(Vector <Azione> _insiemeCammino, String _valoreRilevazione) {
		insiemeCammino = _insiemeCammino;
		valoreRilevazione = _valoreRilevazione;
	}
	
	public String getValoreRilevazione() {
		return valoreRilevazione;
	}
	 
	public Vector <Azione> getInsiemeCammino() {
		return insiemeCammino;
	}
}
