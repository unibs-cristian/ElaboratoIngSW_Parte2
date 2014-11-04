package gestioneDati;
import testSuiteDiagnosi.Diagnosi;
import testSuiteDiagnosi.TestSuite;
import utilita.GUI;
import gestioneModello.Modello;

import java.io.Serializable;

public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String MSG_TITOLO_REPORT = "\n\nREPORT %s\n\n";
	
	private String nome;
	private Modello mod;
	private TestSuite ts;
	private Diagnosi diag;
	
private static Report instance = null;
	
	private Report() {
		mod = null;
		ts = null;
		diag = null;
	}

	private Report(Modello _mod, TestSuite _ts, Diagnosi _diag) {
		mod = _mod;
		ts = _ts;
		diag = _diag;
	}
	
	public static Report getInstance() {
		if(instance==null)
			instance = new Report();
		return instance;
	}
	 
	public static void cambiaReport(Report altro) {
		instance = altro;
	}
	
	public static boolean isNull() {
		if(instance==null)
			return true;
		return false;
	}
	
	public Modello getModello() {
		return mod;
	}
	
	public TestSuite getTS() {
		return ts;
	}
	
	public Diagnosi getDiagnosi() {
		return diag;
	}
	
	public void setModello(Modello m) {
		mod = m;
	}
	
	public void setTestSuite(TestSuite aTS) {
		ts = aTS;
	}
	
	public void setDiagnosi(Diagnosi d) {
		diag = d;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.incorniciaStringa(String.format(MSG_TITOLO_REPORT, nome)));
		risultato.append("MODELLO" + "\n" + "-----------------" + "\n");
		risultato.append(mod.toString()+"\n\n");
		risultato.append("TEST SUITE" + "\n" + "-----------------" + "\n");
		risultato.append(ts.toString()+"\n\n");
		risultato.append("DIAGNOSI" + "\n" + "-------------------" + "\n");
		risultato.append(diag.toString());     //TODO serve metodo per toString diagnosi
		return risultato.toString();
	}
}