package gestioneDati;
import testSuiteDiagnosi.Diagnosi;
import testSuiteDiagnosi.TestSuite;
import utilita.GUI;
import gestioneModello.Modello;

import java.io.Serializable;
import java.util.Vector;

public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String MSG_TITOLO_REPORT = "\n\nREPORT %s\n\n";
	
	private String nome;
	private Modello mod;
	private TestSuite ts;
	private Vector <Diagnosi> elencoDiag;
	
private static Report instance = null;
	
	private Report() {
		mod = null;
		ts = null;
		elencoDiag = ts.getElencoDiagnosi();
	}

	private Report(Modello _mod, TestSuite _ts, Diagnosi _diag) {
		mod = _mod;
		ts = _ts;
		elencoDiag = ts.getElencoDiagnosi();
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
	
	public Diagnosi getDiagnosiAt(int index) {
		return elencoDiag.elementAt(index);
	}
	
	public Modello getModello() {
		return mod;
	}
	
	public int getNumeroDiag() {
		return elencoDiag.size();
	}
	
	public TestSuite getTS() {
		return ts;
	}
	
	public void setModello(Modello m) {
		mod = m;
	}
	
	public void setTestSuite(TestSuite aTS) {
		ts = aTS;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.incorniciaStringa(String.format(MSG_TITOLO_REPORT, nome)));
		risultato.append("MODELLO" + "\n" + "-----------------" + "\n");
		risultato.append(mod.toString()+"\n\n");
		risultato.append("TEST SUITE" + "\n" + "-----------------" + "\n");
		risultato.append(ts.toString()+"\n\n");
		for(int i=0; i<getNumeroDiag(); i++) {
			risultato.append("DIAGNOSI N." + i + "\n" + "-------------------" + "\n");
			risultato.append(getDiagnosiAt(i).toString() + "\n");    //TODO serve metodo per toString diagnosi
		}
		return risultato.toString();
	}
}