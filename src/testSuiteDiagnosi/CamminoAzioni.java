package testSuiteDiagnosi;
import gestioneModello.Azione;
import java.util.Vector;

public class CamminoAzioni {
	
	private Vector <Azione> insiemeCammino;
	
	public CamminoAzioni() {
		insiemeCammino = new Vector<Azione>();
	}
	
	public void aggiungiAzione(Azione a) {
		insiemeCammino.add(a);
	}
	
	public Vector<Azione> getInsiemeCammino() {
		return insiemeCammino;
	}
	
	public Azione getAzioneAt(int index) {
		return insiemeCammino.elementAt(index);
	}
	
	public String toString() {
		return null;
	}
	

}
