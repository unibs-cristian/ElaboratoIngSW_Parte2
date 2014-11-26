/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;


// TODO: Auto-generated Javadoc
/*
 * Si e' in questo stato quando si e' all'inizio di un ramo, ovvero in corrispondenza del flusso entrante
 * alla prima entita' del ramo. 
 */
/**
 * The Class EntratoRamo.
 */
public class EntratoRamo implements StatoCammino {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#gestisciStato(testSuiteDiagnosi.CamminoAzioni, java.lang.String)
	 */
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
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#isValid()
	 */
	public boolean isValid() {
		return false;
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#getStringaStato()
	 */
	public String getStringaStato() {
		return(ENTRATO_RAMO);
	}		
}