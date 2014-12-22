/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;

/**
 * La classe StatoVuoto.
 * Ci si trova in questo stato finche' non viene inserita un'azione.
 */
public class StatoVuoto implements StatoCammino{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
		else if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return STATO_VUOTO;
	}

	public void gestisciStatoRamo(Ramo r, String stato) {}	
}