/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;

/**
 * Classe PercorsoTutto.
 * Si e' in questo stato quando tutte le entita' di un ramo sono state inserite
 */
public class PercorsoTutto implements StatoCammino {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
		else if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
		else if(stato.equals(PERCORSO_PARZ))
			camm.setStatoCammino(new RamoPercorsoParz());
	}
	
	public void gestisciStatoRamo(Ramo r, String stato) {}
	
	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return PERCORSO_TUTTO;
	}
}