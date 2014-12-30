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

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
		else if(stato.equals(FERMATO))
			camm.setStatoCammino(new Fermato());
		else if(stato.equals(ENTRATO_RAMO))
			camm.setStatoCammino(new EntratoRamo());
	}

	public boolean isValid() {
		return true;
	}

	public String getStringaStato() {
		return SALTATO_BLOCCO;
	}

	public void gestisciStatoRamo(Ramo r, String stato) {}
}