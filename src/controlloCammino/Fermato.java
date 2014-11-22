/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

// TODO: Auto-generated Javadoc
/**
 * The Class Fermato.
 */
public class Fermato implements StatoCammino {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#gestisciStato(testSuiteDiagnosi.CamminoAzioni, java.lang.String)
	 */
	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#getStringaStato()
	 */
	public String getStringaStato() {
		return FERMATO;
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#isValid()
	 */
	public boolean isValid() {
		return true;
	}
}