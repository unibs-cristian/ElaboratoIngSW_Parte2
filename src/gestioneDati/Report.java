/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneDati;
import testSuiteDiagnosi.Diagnosi;
import testSuiteDiagnosi.TestSuite;
import utilita.GUI;
import gestioneModello.Modello;

import java.io.Serializable;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Report.
 */
public class Report implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_TITOLO_REPORT. */
	private static final String MSG_TITOLO_REPORT = "\n\nREPORT %s\n\n";
	
	/** The nome. */
	private String nome;
	
	/** The mod. */
	private Modello mod;
	
	/** The ts. */
	private TestSuite ts;
	
	/** The elenco diag. */
	private Vector <Diagnosi> elencoDiag;
	
/** The instance. */
private static Report instance = null;
	
	/**
	 * Instantiates a new report.
	 */
	private Report() {
		mod = null;
		ts = null;
	}

	/**
	 * Instantiates a new report.
	 *
	 * @param _mod the _mod
	 * @param _ts the _ts
	 * @param _diag the _diag
	 */
	private Report(Modello _mod, TestSuite _ts, Diagnosi _diag) {
		mod = _mod;
		ts = _ts;
		elencoDiag = ts.getElencoDiagnosi();
	}
	
	/**
	 * Gets the single instance of Report.
	 *
	 * @return single instance of Report
	 */
	public static Report getInstance() {
		if(instance==null)
			instance = new Report();
		return instance;
	}
	 
	/**
	 * Cambia report.
	 *
	 * @param altro the altro
	 */
	public static void cambiaReport(Report altro) {
		instance = altro;
	}
	
	/**
	 * Checks if is null.
	 *
	 * @return true, if is null
	 */
	public static boolean isNull() {
		if(instance==null)
			return true;
		return false;
	}
	
	/**
	 * Gets the diagnosi at.
	 *
	 * @param index the index
	 * @return the diagnosi at
	 */
	public Diagnosi getDiagnosiAt(int index) {
		return elencoDiag.elementAt(index);
	}
	
	/**
	 * Gets the modello.
	 *
	 * @return the modello
	 */
	public Modello getModello() {
		return mod;
	}
	
	/**
	 * Gets the numero diag.
	 *
	 * @return the numero diag
	 */
	public int getNumeroDiag() {
		return elencoDiag.size();
	}
	
	/**
	 * Gets the ts.
	 *
	 * @return the ts
	 */
	public TestSuite getTS() {
		return ts;
	}
	
	/**
	 * Sets the diagnosi.
	 *
	 * @param diagnosiTS the new diagnosi
	 */
	public void setDiagnosi(Vector <Diagnosi> diagnosiTS) {
		for(int i=0; i<diagnosiTS.size(); i++) {
			elencoDiag.addElement(diagnosiTS.elementAt(i));
		}
	}

	/**
	 * Sets the modello.
	 *
	 * @param m the new modello
	 */
	public void setModello(Modello m) {
		mod = m;
	}
	
	/**
	 * Sets the test suite.
	 *
	 * @param aTS the new test suite
	 */
	public void setTestSuite(TestSuite aTS) {
		ts = aTS;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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