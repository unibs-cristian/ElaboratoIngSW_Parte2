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

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(STATO_NON_OK))
			camminoDaGestire.setStatoCammino(new StatoNonOk());
		else if(nuovoStatoCammino.equals(ENTRATO_RAMO))
			camminoDaGestire.setStatoCammino(new EntratoRamo());
		else if(nuovoStatoCammino.equals(STATO_OK))
			camminoDaGestire.setStatoCammino(new StatoOk());
		else if(nuovoStatoCammino.equals(SALTATO_BLOCCO))
			camminoDaGestire.setStatoCammino(new SaltatoBlocco());
		else if(nuovoStatoCammino.equals(PERCORSO_TUTTO))
			camminoDaGestire.setStatoCammino(new PercorsoTutto());
		else if(nuovoStatoCammino.equals(FERMATO))
			camminoDaGestire.setStatoCammino(new Fermato());
		else if(nuovoStatoCammino.equals(FERMATO_DENTRO))
			camminoDaGestire.setStatoCammino(new FermatoDentro());
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {
		if(nuovoStatoRamo.equals(STATO_NON_OK))
			ramoDaGestire.setStato(new StatoNonOk());
	}
	
	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return NON_PERCORSO;
	}
}
