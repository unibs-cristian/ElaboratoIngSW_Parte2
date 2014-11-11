package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;


/*
 * Si e' in questo stato quando si e' all'inizio di un ramo, ovvero in corrispondenza del flusso entrante
 * alla prima entita' del ramo. 
 */
public class EntratoRamo implements StatoCammino {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {	
		if(stato.equals(NON_PERCORSO))
			camm.setStatoCammino(new RamoNonPercorso());
		else if(stato.equals(PERCORSO_PARZ))
			camm.setStatoCammino(new RamoPercorsoParz());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return(ENTRATO_RAMO);
	}		
}