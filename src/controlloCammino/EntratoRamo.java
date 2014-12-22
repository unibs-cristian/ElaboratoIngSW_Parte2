/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;

/**
 * Classe EntratoRamo.
 * Un'istanza di questa classe rappresenta lo stato di un ramo o di un cammino. Si e' in questo stato
 * quando si e' all'inizio di un ramo di una qualsiasi entita' complessa, ovvero in corrispondenza
 * del flusso entrante alla prima entita' del ramo.
 */
public class EntratoRamo implements StatoCammino {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {	
		if(stato.equals(NON_PERCORSO))
			camm.setStatoCammino(new RamoNonPercorso());
		else if(stato.equals(PERCORSO_PARZ))
			camm.setStatoCammino(new RamoPercorsoParz());
		else if(stato.equals(PERCORSO_TUTTO))
			camm.setStatoCammino(new PercorsoTutto());
	}
	
	public void gestisciStatoRamo(Ramo r, String stato) {
		if(stato.equals(NON_PERCORSO))
			r.setStato(new RamoNonPercorso());
		else if(stato.equals(PERCORSO_PARZ))
			r.setStato(new RamoPercorsoParz());	
		else if(stato.equals(PERCORSO_TUTTO))
			r.setStato(new PercorsoTutto());
		else if(stato.equals(STATO_NON_OK))
			r.setStato(new StatoNonOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return(ENTRATO_RAMO);
	}		
}