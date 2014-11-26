/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;


import gestioneModello.Ramo;
import testSuiteDiagnosi.CamminoAzioni;

// TODO: Auto-generated Javadoc
/**
 * The Class RamoPercorsoParz.
 */
public class RamoPercorsoParz implements StatoCammino{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	//Si e' in questo stato quando sono state inserite tutte le azioni di un ramo, ma questo contiene ancora altre azioni da inserire
	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#gestisciStato(testSuiteDiagnosi.CamminoAzioni, java.lang.String)
	 */
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
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#isValid()
	 */
	public boolean isValid() {
		return false;
	}

	/* (non-Javadoc)
	 * @see controlloCammino.StatoCammino#getStringaStato()
	 */
	public String getStringaStato() {
		return PERCORSO_PARZ;
	}
}