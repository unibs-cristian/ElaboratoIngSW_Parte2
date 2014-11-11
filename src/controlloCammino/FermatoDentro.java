package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class FermatoDentro implements StatoCammino {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		if(stato.equals(ENTRATO_RAMO) && camm.isGlobale())
			camm.setStatoCammino(new EntratoRamo());
	}
	
	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return FERMATO_DENTRO;
	}
}
