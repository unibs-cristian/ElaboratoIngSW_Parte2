/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

import java.io.Serializable;

/**
 * Interface StatoCammino.
 * Offre un'interfaccia comune per le varie classi che rappresentano tutti i possibili stati che puo'
 * avere un cammino globale, un insieme del cammino o un ramo di un'entita' complessa.
 */
public interface StatoCammino extends Serializable{
	
	/** Costanti stringa che rappresentano tutti i possibili stati */
	public final static String ENTRATO_RAMO = "ENTRATO RAMO";
	public final static String FERMATO = "FERMATO";
	public final static String FERMATO_DENTRO = "FERMATO DENTRO";
	public final static String NON_PERCORSO = "RAMO NON PERCORSO";
	public final static String PERCORSO_PARZ = "RAMO PERCORSO PARZIALMENTE";
	public final static String PERCORSO_TUTTO = "RAMO PERCORSO TUTTO";
	public final static String STATO_OK = "OK";
	public final static String STATO_NON_OK = "NON VALIDO";
	public final static String STATO_VUOTO = "VUOTO";
	public final static String SALTATO_BLOCCO = "SALTATO BLOCCO";
	
	/**
	 * Gestisce le transizioni di stato del cammino.
	 *
	 * @param camm : il cammino per il quale gestire le transizioni di stato.
	 * @param stato : il nuovo stato da assegnare al cammino, se la transizione stato attuale - 
	 * nuovo stato e' possibile
	 */
	public void gestisciStato(CamminoAzioni camm, String stato);
	
	/**
	 * Gestisce le transizioni di stato di un ramo.
	 *
	 * @param r : il ramo per il quale gestire le transizioni di stato.
	 * @param stato : il nuovo stato da assegnare 
	 */
	public void gestisciStatoRamo(Ramo r, String stato);
	
	/**
	 * Fornisce la stringa rappresentante lo stato attuale del cammino o del ramo.
	 * La stringa restituita e' una delle costanti che rappresentano i possibili stati.
	 *
	 * @return la stringa dello stato
	 */
	public String getStringaStato();
	
	/**
	 * Dice se lo stato e' valido.
	 *
	 * @return true, se lo stato del cammino o del ramo e' uno stato valido, false altrimenti.
	 */
	public boolean isValid();
}
