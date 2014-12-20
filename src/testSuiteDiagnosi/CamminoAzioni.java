/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package testSuiteDiagnosi;
import gestioneModello.Azione;

import java.io.Serializable;
import java.util.Vector;

import controlloCammino.StatoCammino;
import controlloCammino.StatoVuoto;

// TODO: Auto-generated Javadoc
/**
 * The Class CamminoAzioni.
 */
public class CamminoAzioni implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The insieme cammino. */
	private Vector <Azione> insiemeCammino;
	
	/** The globale si no. */
	private boolean globaleSiNo;   //Se vero e' globale, se falso e' parziale
	
	/** The stato corrente. */
	private StatoCammino statoCorrente;
	
	/**
	 * Instantiates a new cammino azioni.
	 *
	 * @param tipo the tipo
	 */
	public CamminoAzioni(boolean tipo) {
		globaleSiNo = tipo;
		insiemeCammino = new Vector<Azione>();
		statoCorrente = new StatoVuoto();
	}
	
	/**
	 * Aggiungi azione.
	 *
	 * @param a the a
	 */
	public void aggiungiAzione(Azione a) {
		insiemeCammino.add(a);
	}
	
	public void azzeraAzioni() {
		while(!(insiemeCammino.isEmpty()))
			insiemeCammino.remove(getAzioneAt(0));
	}
	
	/**
	 * Gets the insieme cammino.
	 *
	 * @return the insieme cammino
	 */
	public Vector<Azione> getInsiemeCammino() {
		return insiemeCammino;
	}
	
	/**
	 * Gets the numero azioni.
	 *
	 * @return the numero azioni
	 */
	public int getNumeroAzioni() {
		return insiemeCammino.size();
	}
	
	/**
	 * Gets the azione at.
	 *
	 * @param index the index
	 * @return the azione at
	 */
	public Azione getAzioneAt(int index) {
		return insiemeCammino.elementAt(index);
	}
	
	/**
	 * Gets the stato.
	 *
	 * @return the stato
	 */
	public StatoCammino getStato() {
		return statoCorrente;
	}
	
	//Ritorna true se questo cammino azioni e' un sottoinsieme di 'altro'.
	/**
	 * Incluso in.
	 *
	 * @param altro the altro
	 * @return true, if successful
	 */
	public boolean inclusoIn(CamminoAzioni altro) {
		if(this.getNumeroAzioni() > altro.getNumeroAzioni())
			return false;
		for(int i=0; i<getNumeroAzioni(); i++)
			if(altro.presente(getAzioneAt(i)) == false)
				return false;
		return true;
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return insiemeCammino.isEmpty();
	}
	
	/**
	 * Checks if is equal.
	 *
	 * @param altro the altro
	 * @return true, if is equal
	 */
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
	
	/**
	 * Checks if is globale.
	 *
	 * @return true, if is globale
	 */
	public boolean isGlobale() {
		return globaleSiNo;
	}
	
	/**
	 * Presente.
	 *
	 * @param a the a
	 * @return true, if successful
	 */
	public boolean presente(Azione a) {
		for(int i=0; i<getNumeroAzioni(); i++) {
			if(a.getNome().equalsIgnoreCase(getAzioneAt(i).getNome()))
				return true;
		}
		return false;
	}
	
	public void setInsiemeCammino(Vector <Azione> nuovo) {
		insiemeCammino = nuovo;
	}
	
	/**
	 * Sets the stato cammino.
	 *
	 * @param state the new stato cammino
	 */
	public void setStatoCammino(StatoCammino state) {
		statoCorrente = state;
		System.out.println("Cambiato stato in " + state.getStringaStato());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(getAzioneAt(0).getNome());
		for(int i=1; i<insiemeCammino.size(); i++)
			risultato.append("," + getAzioneAt(i).getNome());
			return risultato.toString();
	}
}