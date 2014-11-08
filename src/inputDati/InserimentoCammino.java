package inputDati;

import java.util.Vector;

import testSuiteDiagnosi.CamminoAzioni;
import testSuiteDiagnosi.ClasseEquivalenza;
import testSuiteDiagnosi.Coppia;
import testSuiteDiagnosi.TestSuite;
import utilita.Util;
import gestioneModello.Azione;
import gestioneModello.Entita;
import gestioneModello.Modello;

public class InserimentoCammino {

	public final static String MSG_CAMM_GLOBALE_1 = "Scegliere le azioni facenti parte del cammino globale relativo alla classe di equivalenza.";
	public final static String MSG_AGGIUNTA_CAMM_GLOBALE = "Si desidera aggiungere l'azione %s al cammino globale?";
	public final static String MSG_ERRORE_CAMMINO = "Errore! Il cammino e' vuoto. Inserire nuovamente.";
	public final static String MSG_CAMMINO_NON_VALIDO = "Errore! Questa azione va introdotta per avere un cammino valido.";
	
	private Modello mod;
	private TestSuite ts;
	private ClasseEquivalenza ce;
	private Coppia c;
	
	public InserimentoCammino() {
		ts = TestSuite.getInstance();
		mod = ts.getModello();
	}
	
	public InserimentoCammino(ClasseEquivalenza _ce) {
		ce = _ce;
		ts = TestSuite.getInstance();
		mod = ts.getModello();
	}
	
	public InserimentoCammino(ClasseEquivalenza _ce, Coppia _c) {
		ce = _ce;
		c = _c;
		ts = TestSuite.getInstance();
		mod = ts.getModello();
	}
	
	public CamminoAzioni inserisciCammGlob() {
		Vector <Azione> azioniModello = mod.getElencoAzioni();
		CamminoAzioni cammGlob = new CamminoAzioni(true);
		System.out.println(MSG_CAMM_GLOBALE_1);
		boolean camminoValido = false;
		do {
			for(int j=0; j<azioniModello.size(); j++) {
				Azione a = azioniModello.elementAt(j);							
				if(Util.yesOrNo(String.format(MSG_AGGIUNTA_CAMM_GLOBALE,azioniModello.elementAt(j).getNome())))
					cammGlob.aggiungiAzione(a);
			}
			// Si controlla che il cammino globale inserito non sia vuoto.
			if(cammGlob.isEmpty()==false)
				camminoValido = true;
			else
				System.out.println(MSG_ERRORE_CAMMINO);
			//Si controlla che il cammino inserito sia concorde col modello
			camminoValido = verificaCammino(cammGlob);
			
		} while(!camminoValido);
		System.out.println("Cammino Globale ---> " + cammGlob.toString());
		return cammGlob;
	}
	
	//TODO vedere se si può aaplicare il pattern state
	private boolean verificaCammino(CamminoAzioni daVerificare) {  
		boolean esito;
		//Verifica validita' di un cammino globale
		if(daVerificare.isGlobale()) {
			//Verifica sulla prima entita'
			Entita prima = mod.getEntita().elementAt(1);
			/*
			 * Se la prima entita' nel modello e' un'azione e questa non e' presente nel cammino, il cammino
			 * e' considerato non valido.
			 */
			if(prima.getIdTipo().equalsIgnoreCase(Entita.ID_TIPO_AZIONE)) {
				if(daVerificare.presente(mod.getAzioneAt(0)) == false)
					return false;
			}
			else if(prima.getIdTipo().equalsIgnoreCase(Entita.ID_TIPO_FORK)) {
				esito = verificaFork(daVerificare);
				if(esito == false)
					return false;
			}
			
		}
			
	}
	
	public boolean verificaFork(CamminoAzioni daVerificare) {
		
	}
}
