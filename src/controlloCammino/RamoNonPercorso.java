package controlloCammino;


import testSuiteDiagnosi.CamminoAzioni;

public class RamoNonPercorso implements StatoCammino{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Si e' in questo stato quando la prima azione del ramo non e' stata inserita. Se viene inserita 
	 * qualsiasi altra azione del ramo corrente, si passera' allo stato non valido. Si ritorna invece 
	 * nello stato EntratoRamo quando si passa al ramo successivo.
	 */
	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
		else if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return NON_PERCORSO;
	}
}