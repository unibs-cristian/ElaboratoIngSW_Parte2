/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * La classe StatoOk.
 * Stato che indica che il cammino inserito fino all'entita' attuale e' corretto.
 */
public class StatoOk implements StatoCammino{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
		else if(stato.equals(SALTATO_BLOCCO))
			camm.setStatoCammino(new SaltatoBlocco());
	}

	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return STATO_OK;
	}
	
	public void gestisciStatoRamo(Ramo r, String stato) {}	
}