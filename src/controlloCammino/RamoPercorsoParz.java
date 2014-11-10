package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public class RamoPercorsoParz implements StatoCammino {

	//Si e' in questo stato quando sono state inserite tutte le azioni di un ramo, ma questo contiene ancora altre azioni da inserire
	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(FERMATO_DENTRO))
			camm.setStatoCammino(new FermatoDentro());
		else if(stato.equals(PERCORSO_TUTTO))
			camm.setStatoCammino(new PercorsoTutto());   //ci vado se l'azione inserita e' l'ultima del ramo
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return PERCORSO_PARZ;
	}
	
}
