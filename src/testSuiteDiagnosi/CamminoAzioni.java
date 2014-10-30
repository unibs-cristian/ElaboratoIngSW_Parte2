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
	
	public int getNumeroAzioni() {
		return insiemeCammino.size();
	}
	
	public Azione getAzioneAt(int index) {
		return insiemeCammino.elementAt(index);
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(getAzioneAt(0).getNome());
		for(int i=1; i<insiemeCammino.size(); i++)
			risultato.append("," + getAzioneAt(i).getNome());
			return risultato.toString();
	}
}