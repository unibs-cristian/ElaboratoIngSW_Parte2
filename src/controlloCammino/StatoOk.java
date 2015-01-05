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

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(FERMATO))
			camminoDaGestire.setStatoCammino(new Fermato());
		else if(nuovoStatoCammino.equals(ENTRATO_RAMO))
			camminoDaGestire.setStatoCammino(new EntratoRamo());
		else if(nuovoStatoCammino.equals(SALTATO_BLOCCO))
			camminoDaGestire.setStatoCammino(new SaltatoBlocco());
	}

	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return STATO_OK;
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {}	
}
