package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class SuperatoFork implements StatoCammino{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return SUPERATO_FORK;
	}
}