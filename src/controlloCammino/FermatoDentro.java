/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * Classe FermatoDentro.
 * Si e' in questo stato quando un'entita' interna di un'entita' complessa non e' stata inserita.
 * Lo scopo e' analogo a quello dello stato Fermato, ma riguarda la singola entita' complessa.
 */
public class FermatoDentro implements StatoCammino {

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		if(stato.equals(ENTRATO_RAMO) && camm.isGlobale())
			camm.setStatoCammino(new EntratoRamo());
	}
	
	public void gestisciStatoRamo(Ramo r, String stato) {
		if(stato.equals(STATO_NON_OK))
			r.setStato(new StatoNonOk());
	}
	
	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return FERMATO_DENTRO;
	}
}