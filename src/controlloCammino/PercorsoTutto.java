package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class PercorsoTutto implements StatoCammino {
	
	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(SUPERATO_FORK))
			camm.setStatoCammino(new SuperatoFork());
		else if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(ENTRATO_RAMO) && camm.isGlobale())
			camm.setStatoCammino(new EntratoRamo());
		else if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return PERCORSO_TUTTO;
	}
}