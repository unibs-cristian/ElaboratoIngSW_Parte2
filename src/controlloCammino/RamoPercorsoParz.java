/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;


import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * La classe RamoPercorsoParz.
 * Si e' in questo stato quando sono state inserite tutte le azioni di un ramo, 
 * ma questo contiene ancora altre azioni da inserire. Quando verra' inserita l'ultima azione del 
 * ramo si passera' allo stato PercorsoTutto.
 */
public class RamoPercorsoParz implements StatoCammino{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(FERMATO_DENTRO))
			camm.setStatoCammino(new FermatoDentro());
		else if(stato.equals(PERCORSO_TUTTO))
			camm.setStatoCammino(new PercorsoTutto());   //ci vado se l'azione inserita e' l'ultima del ramo
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}
	
	public void gestisciStatoRamo(Ramo r, String stato) {
		if(stato.equals(FERMATO_DENTRO))
			r.setStato(new FermatoDentro());
		else if(stato.equals(PERCORSO_TUTTO))
			r.setStato(new PercorsoTutto());
		else if(stato.equals(STATO_NON_OK))
			r.setStato(new StatoNonOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return PERCORSO_PARZ;
	}
}