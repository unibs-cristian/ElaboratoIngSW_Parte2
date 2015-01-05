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

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(STATO_NON_OK))
			camminoDaGestire.setStatoCammino(new StatoNonOk());
		if(nuovoStatoCammino.equals(ENTRATO_RAMO) && camminoDaGestire.globaleSiNo())
			camminoDaGestire.setStatoCammino(new EntratoRamo());
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {
		if(nuovoStatoRamo.equals(STATO_NON_OK))
			ramoDaGestire.setStato(new StatoNonOk());
	}
	
	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return FERMATO_DENTRO;
	}
}
