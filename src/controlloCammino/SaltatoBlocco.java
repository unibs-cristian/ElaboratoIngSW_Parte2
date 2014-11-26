package controlloCammino;

import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;

//TODO: Auto-generated Javadoc
/**
* The Class SaltatoBlocco.
*/
public class SaltatoBlocco implements StatoCammino{


	/** The constant SerialVersionUID */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#gestisciStato(testSuiteDiagnosi.CamminoAzioni, java.lang.String)
	 */
	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#isValid()
	 */
	public boolean isValid() {
		return true;
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#getStringaStato()
	 */
	public String getStringaStato() {
		return SALTATO_BLOCCO;
	}

	public void gestisciStatoRamo(Ramo r, String stato) {}
}