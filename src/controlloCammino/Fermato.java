/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;

/**
 * Classe Fermato.
 * Questo stato si raggiunge dopo che l'entita' presa in considerazione non e' stata inserita. In 
 * questo modo, quando l'utente inserisce altre entita' successive, lo stato diventa non valido.
 */
public class Fermato implements StatoCammino {

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {
		if(stato.equals(STATO_NON_OK))
			camm.setStatoCammino(new StatoNonOk());
	}

	public String getStringaStato() {
		return FERMATO;
	}

	public boolean isValid() {
		return true;
	}

	public void gestisciStatoRamo(Ramo r, String stato) {}
}