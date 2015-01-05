/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * La classe StatoVuoto.
 * Ci si trova in questo stato finche' non viene inserita un'azione.
 */
public class StatoVuoto implements StatoCammino{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(STATO_OK))
			camminoDaGestire.setStatoCammino(new StatoOk());
		else if(nuovoStatoCammino.equals(FERMATO))
			camminoDaGestire.setStatoCammino(new Fermato());
		else if(nuovoStatoCammino.equals(ENTRATO_RAMO))
			camminoDaGestire.setStatoCammino(new EntratoRamo());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return STATO_VUOTO;
	}

	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {}	
}
