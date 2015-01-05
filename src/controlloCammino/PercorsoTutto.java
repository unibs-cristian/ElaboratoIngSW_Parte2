/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * Classe PercorsoTutto.
 * Si e' in questo stato quando tutte le entita' di un ramo sono state inserite
 */
public class PercorsoTutto implements StatoCammino {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(STATO_NON_OK))
			camminoDaGestire.setStatoCammino(new StatoNonOk());
		else if(nuovoStatoCammino.equals(ENTRATO_RAMO))
			camminoDaGestire.setStatoCammino(new EntratoRamo());
		else if(nuovoStatoCammino.equals(STATO_OK))
			camminoDaGestire.setStatoCammino(new StatoOk());
		else if(nuovoStatoCammino.equals(PERCORSO_PARZ))
			camminoDaGestire.setStatoCammino(new RamoPercorsoParz());
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {}
	
	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return PERCORSO_TUTTO;
	}
}
