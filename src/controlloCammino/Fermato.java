package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class Fermato implements StatoCammino {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
	}

	public String getStringaStato() {
		return FERMATO;
	}

	public boolean isValid() {
		return true;
	}
}
