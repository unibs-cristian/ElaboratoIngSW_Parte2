package testSuiteDiagnosi;
import gestioneModello.Azione;

import java.io.Serializable;
import java.util.Vector;

import controlloCammino.StatoCammino;
import controlloCammino.StatoVuoto;

public class CamminoAzioni implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Vector <Azione> insiemeCammino;
	private boolean globaleSiNo;   //Se vero e' globale, se falso e' parziale
	private StatoCammino statoCorrente;
	
	public CamminoAzioni(boolean tipo) {
		globaleSiNo = tipo;
		insiemeCammino = new Vector<Azione>();
		statoCorrente = new StatoVuoto();
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
	
	public StatoCammino getStato() {
		return statoCorrente;
	}
	
	//Ritorna true se questo cammino azioni e' un sottoinsieme di 'altro'.
	public boolean inclusoIn(CamminoAzioni altro) {
		if(this.getNumeroAzioni() > altro.getNumeroAzioni())
			return false;
		for(int i=0; i<getNumeroAzioni(); i++)
			if(altro.presente(getAzioneAt(i)) == false)
				return false;
		return true;
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
	
	public boolean isGlobale() {
		return globaleSiNo;
	}
	
	public boolean presente(Azione a) {
		for(int i=0; i<getNumeroAzioni(); i++) {
			if(a.getNome().equalsIgnoreCase(getAzioneAt(i).getNome()))
				return true;
		}
		return false;
	}
	
	public void setStatoCammino(StatoCammino state) {
		statoCorrente = state;
		System.out.println("Cambiato stato in >> "+statoCorrente.getStringaStato());
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(getAzioneAt(0).getNome());
		for(int i=1; i<insiemeCammino.size(); i++)
			risultato.append("," + getAzioneAt(i).getNome());
			return risultato.toString();
	}
}