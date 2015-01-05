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

	public void gestisciStatoCammino(CamminoAzioni camminoDaGestire, String nuovoStatoCammino) {
		if(nuovoStatoCammino.equals(FERMATO_DENTRO))
			camminoDaGestire.setStatoCammino(new FermatoDentro());
		else if(nuovoStatoCammino.equals(PERCORSO_TUTTO))
			camminoDaGestire.setStatoCammino(new PercorsoTutto());   //ci vado se l'azione inserita e' l'ultima del ramo
		else if(nuovoStatoCammino.equals(ENTRATO_RAMO))
			camminoDaGestire.setStatoCammino(new EntratoRamo());
	}
	
	public void gestisciStatoRamo(Ramo ramoDaGestire, String nuovoStatoRamo) {
		if(nuovoStatoRamo.equals(FERMATO_DENTRO))
			ramoDaGestire.setStato(new FermatoDentro());
		else if(nuovoStatoRamo.equals(PERCORSO_TUTTO))
			ramoDaGestire.setStato(new PercorsoTutto());
		else if(nuovoStatoRamo.equals(STATO_NON_OK))
			ramoDaGestire.setStato(new StatoNonOk());
	}

	public boolean isValid() {
		return false;
	}

	public String getStringaStato() {
		return PERCORSO_PARZ;
	}
}
