/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import java.io.Serializable;
import java.util.Vector;

import controlloCammino.EntratoRamo;
import controlloCammino.StatoCammino;
import utilita.UtilitaStringhe;

/**
 * Classe Ramo.
 * Un'istanza di questa classe rappresenta un singolo flusso alternativo di un'entita' complessa come
 * Fork, Branch o Cicli.
 */
public class Ramo implements Serializable {
	
	/** Costante numerica per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Vector di entita' che costituiscono il ramo */
	private Vector <Entita> entitaRamo; 
	
	/** Stato del ramo, necessario per controllare la validita' dei cammini inseriti. */
	private StatoCammino statoRamo;
	
	/**
	 * Costruttore della classe Ramo
	 */
	public Ramo()
	{
		entitaRamo = new Vector<Entita>();  
		statoRamo = new EntratoRamo();
	}
	
	/**
	 * Fornisce le entita' che costituiscono il ramo.
	 *
	 * @return il Vector contenente le entita'
	 */
	public Vector<Entita> getEntitaRamo() {
		return entitaRamo;
	}
	 
	/**
	 * Aggiunge un'entita' al ramo.
	 *
	 * @param daAggiungere : l'entita da aggiungere
	 */
	public void aggiungiEntitaRamo(Entita daAggiungere) {
		entitaRamo.addElement(daAggiungere);
	}
	
	/**
	 * Controlla se il ramo e' privo di entita'
	 *
	 * @return true se non e' contenuta nessuna entita', false altrimenti.
	 */
	public boolean isEmpty() {
		return entitaRamo.isEmpty();
	}
	
	/**
	 * Elimina entita'
	 *
	 * @param posizione : la posizione dell'entita' da rimuovere 
	 */
	public void eliminaEntitaRamo(int posizione) {
		entitaRamo.remove(posizione);
	}
	
	/**
	 * Fornisce le azioni che costituiscono il ramo
	 *
	 * @return Vector contenente le azioni.
	 */
	public Vector <Azione> getAzioniRamo() {
		Vector <Azione> risultato = new Vector<Azione>();
		for(int i=0; i<entitaRamo.size(); i++) 
			if(entitaRamo.elementAt(i).getIdTipo().equals(Entita.ID_TIPO_AZIONE) || entitaRamo.elementAt(i).getIdTipo().equals(Entita.ID_TIPO_AZIONE_COMPOSTA))
				risultato.add((Azione)entitaRamo.elementAt(i));
		return risultato;
	}
	
	/**
	 * Fornisce il numero di entita' inserite nel ramo
	 *
	 * @return la dimensione del Vector contenente le entita'
	 */
	public int getNumeroEntita() {
		return entitaRamo.size();
	}
	
	/**
	 * Fornisce l'entita' in una certa posizione
	 *
	 * @param index : la posizione nel Vector dell'entita' da restituire
	 * @return L'entita' avente quella posizione all'interno del Vector di entita' 
	 */
	public Entita getEntitaAt(int index) {
		return entitaRamo.elementAt(index);
	}
	
	public StatoCammino getStato() {
		return statoRamo;
	}

	public void setStato(StatoCammino nuovo) {
		statoRamo = nuovo;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		for(int i=0; i<entitaRamo.size(); i++) {
			risultato.append(UtilitaStringhe.indenta(entitaRamo.elementAt(i).toString(),Entita.SPAZIO,entitaRamo.elementAt(i).getIndentazione()));
			risultato.append("\n");
		}
		return risultato.toString();
	}
}
