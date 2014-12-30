/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;


import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * Classe RamoNonPercorso.
 * Si va in questo stato quando la prima entita' di un ramo risulta non inserita nel cammino.
 * Se viene inserita 
 * qualsiasi altra azione del ramo corrente, si passera' allo stato non valido. Si ritorna invece 
 * nello stato EntratoRamo quando si passa al ramo successivo.
 */
public class RamoNonPercorso implements StatoCammino{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
		else if(stato.equals(STATO_OK))
			camm.setStatoCammino(new StatoOk());
		else if(stato.equals(SALTATO_BLOCCO))
			camm.setStatoCammino(new SaltatoBlocco());
		else if(stato.equals(PERCORSO_TUTTO))
			camm.setStatoCammino(new PercorsoTutto());
		else if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(FERMATO_DENTRO))
			camm.setStatoCammino(new FermatoDentro());
	}
	
	public void gestisciStatoRamo(Ramo r, String stato) {
		if(stato.equals(STATO_NON_OK))
			r.setStato(new StatoNonOk());
	}
	
	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return NON_PERCORSO;
	}
}