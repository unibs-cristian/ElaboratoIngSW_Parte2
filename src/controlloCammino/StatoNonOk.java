package controlloCammino;


import testSuiteDiagnosi.CamminoAzioni;

public class StatoNonOk implements StatoCammino{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return STATO_NON_OK;
	}

}
