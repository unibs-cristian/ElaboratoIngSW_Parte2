package testSuiteDiagnosi;
import gestioneModello.Modello;

import java.util.Vector;

public class TestSuite {
	private Vector <ClasseEquivalenza> elencoClassi;
	private Modello mod;
	
	public TestSuite(Modello _mod) {
		mod = _mod;
		elencoClassi = new Vector <ClasseEquivalenza>();
	}
	
	// Le classi di equivalenza verranno inserite dall'utente come le entita' nel modello.
	public Vector <ClasseEquivalenza> getElencoClassi() {
		return elencoClassi;
	}
	
	public Modello getModello() {
		return mod;
	}
}
