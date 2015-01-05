package controlloCammino;

import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
* La classe SaltatoBlocco.
* Questo particolare stato si raggiunge quando in un cammino globale un Fork ha un ramo settato
* a percorso tutto e almeno un ramo con stato FermatoDentro. 
* Tale stato serve per tenere conto del fatto che il blocco di esecuzione relativo al Fork non e' stato 
* completamente inserito. Dunque in caso di inserimento di entita' successive, lo stato diventera' non
* valido.
*/
public class SaltatoBlocco implements StatoCammino{


	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(STATO_NON_OK))
			camminoDaGestire.setStatoCammino(new StatoNonOk());
		else if(nuovoStatoCammino.equals(FERMATO))
			camminoDaGestire.setStatoCammino(new Fermato());
		else if(nuovoStatoCammino.equals(ENTRATO_RAMO))
			camminoDaGestire.setStatoCammino(new EntratoRamo());
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {}

	public String getStringaStato() {
		return SALTATO_BLOCCO;
	}
	
	public boolean isValid() {
		return true;
	}
}
