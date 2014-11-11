package controlloCammino;

import java.io.Serializable;

import testSuiteDiagnosi.CamminoAzioni;

public class StatoOk implements StatoCammino, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}

	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return STATO_OK;
	}	
}