/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

// TODO: Auto-generated Javadoc
/**
 * The Class StatoVuoto.
 */
public class StatoVuoto implements StatoCammino{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#gestisciStato(testSuiteDiagnosi.CamminoAzioni, java.lang.String)
	 */
	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
		else if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
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
		return STATO_VUOTO;
	}	
}