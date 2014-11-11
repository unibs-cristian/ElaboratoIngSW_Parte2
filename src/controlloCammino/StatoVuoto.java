package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class StatoVuoto implements StatoCammino{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
		else if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return STATO_VUOTO;
	}	
}