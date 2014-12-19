/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import java.io.Serializable;
import java.util.Vector;

import controlloCammino.EntratoRamo;
import controlloCammino.StatoCammino;
import utilita.GUI;

// TODO: Auto-generated Javadoc
/**
 * The Class Ramo.
 */
public class Ramo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The entita ramo. */
	private Vector <Entita> entitaRamo; 
	
	/** Lo stato del ramo quando l'utente inserisce un cammino */
	private StatoCammino statoRamo;
	
	/**
	 * Instantiates a new ramo.
	 */
	public Ramo()
	{
		entitaRamo = new Vector<Entita>();  
		statoRamo = new EntratoRamo();
	}
	
	/**
	 * Gets the entita ramo.
	 *
	 * @return the entita ramo
	 */
	public Vector<Entita> getEntitaRamo() {
		return entitaRamo;
	}
	 
	/**
	 * Aggiungi entita ramo.
	 *
	 * @param daAggiungere the da aggiungere
	 */
	public void aggiungiEntitaRamo(Entita daAggiungere) {
		entitaRamo.addElement(daAggiungere);
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return entitaRamo.isEmpty();
	}
	
	/**
	 * Elimina entita ramo.
	 *
	 * @param i the i
	 */
	public void eliminaEntitaRamo(int i) {
		entitaRamo.remove(i);
	}
	
	/**
	 * Gets the azioni ramo.
	 *
	 * @return the azioni ramo
	 */
	public Vector <Azione> getAzioniRamo() {
		Vector <Azione> risultato = new Vector<Azione>();
		for(int i=0; i<entitaRamo.size(); i++) 
			if(entitaRamo.elementAt(i).getIdTipo().equals(Entita.ID_TIPO_AZIONE) || entitaRamo.elementAt(i).getIdTipo().equals(Entita.ID_TIPO_AZIONE_COMPOSTA))
				risultato.add((Azione)entitaRamo.elementAt(i));
		return risultato;
	}
	
	/**
	 * Gets the numero entita.
	 *
	 * @return the numero entita
	 */
	public int getNumeroEntita() {
		return entitaRamo.size();
	}
	
	/**
	 * Gets the entita at.
	 *
	 * @param index the index
	 * @return the entita at
	 */
	public Entita getEntitaAt(int index) {
		return entitaRamo.elementAt(index);
	}
	
	public StatoCammino getStato() {
		return statoRamo;
	}
	
	//TODO togliere se nn serve
	/**
	 * Presente.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean presente(Entita e) {
		for(int i=0; i<getNumeroEntita(); i++)
			if(getEntitaAt(i).getNome().equals(e.getNome()))
				return true;
		return false;
	}
	
	public void setStato(StatoCammino nuovo) {
		statoRamo = nuovo;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		for(int i=0; i<entitaRamo.size(); i++) {
			risultato.append(GUI.indenta(entitaRamo.elementAt(i).toString(),Entita.SPAZIO,entitaRamo.elementAt(i).getIndentazione()));
			risultato.append("\n");
		}
		return risultato.toString();
	}
}