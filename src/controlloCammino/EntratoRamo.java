/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * Classe EntratoRamo.
 * Un'istanza di questa classe rappresenta lo stato di un ramo o di un cammino. Si e' in questo stato
 * quando si e' all'inizio di un ramo di una qualsiasi entita' complessa, ovvero in corrispondenza
 * del flusso entrante alla prima entita' del ramo.
 */
public class EntratoRamo implements StatoCammino {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {	
		if(nuovoStatoCammino.equals(NON_PERCORSO))
			camminoDaGestire.setStatoCammino(new RamoNonPercorso());
		else if(nuovoStatoCammino.equals(PERCORSO_PARZ))
			camminoDaGestire.setStatoCammino(new RamoPercorsoParz());
		else if(nuovoStatoCammino.equals(PERCORSO_TUTTO))
			camminoDaGestire.setStatoCammino(new PercorsoTutto());
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {
		if(nuovoStatoRamo.equals(NON_PERCORSO))
			ramoDaGestire.setStato(new RamoNonPercorso());
		else if(nuovoStatoRamo.equals(PERCORSO_PARZ))
			ramoDaGestire.setStato(new RamoPercorsoParz());	
		else if(nuovoStatoRamo.equals(PERCORSO_TUTTO))
			ramoDaGestire.setStato(new PercorsoTutto());
		else if(nuovoStatoRamo.equals(STATO_NON_OK))
			ramoDaGestire.setStato(new StatoNonOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return(ENTRATO_RAMO);
	}		
}
