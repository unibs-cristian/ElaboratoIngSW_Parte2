package testSuiteDiagnosi;
import gestioneModello.Azione;
import java.io.Serializable;
import java.util.Vector;

public class CamminoAzioni implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
	
	public boolean isEmpty() {
		return insiemeCammino.isEmpty();
	}
	
	public boolean isEqual(CamminoAzioni altro) {
		if(altro.getNumeroAzioni() != getNumeroAzioni())
			return false;
		else {
			for(int i=0; i<getNumeroAzioni(); i++) {
				if(getAzioneAt(i).getNome().equalsIgnoreCase(altro.getAzioneAt(i).getNome())==false)
					return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(getAzioneAt(0).getNome());
		for(int i=1; i<insiemeCammino.size(); i++)
			risultato.append("," + getAzioneAt(i).getNome());
			return risultato.toString();
	}
}