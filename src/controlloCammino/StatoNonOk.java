/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;


import gestioneModello.Ramo;
import gestioneTS.CamminoAzioni;

/**
 * La classe StatoNonOk.
 * Ci si trova in questo stato quando il cammino inserito e' invalido e va dunque reinserito.
 * Un cammino puo' essere invalido perche' strutturalmente non compatibile con il modello, oppure
 * perche' un insieme del cammino non e' sottoinsieme del cammino globale.
 */
public class StatoNonOk implements StatoCammino{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;

	public void gestisciStato(CamminoAzioni camm, String stato) {}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return STATO_NON_OK;
	}

	public void gestisciStatoRamo(Ramo r, String stato) {}
}