/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneReport;
import gestioneModello.Modello;
import gestioneTS.Diagnosi;
import gestioneTS.TestSuite;

import java.io.Serializable;

import statisticheGuasti.Distanze;
import statisticheGuasti.OrdinaElencoProbabilitaEIntervalliPosizione;

/**
 * Classe Report.
 * Un'istanza della classe Report e' una struttura contenente tutti i dati 
 * forniti in input dall'utente e tutti i risultati ottenuti come output 
 * dell'elaborazione. Il Report viene generato in base al Modello, al Test 
 * Suite e alle diagnosi correntemente inserite.
 */
public class Report implements Serializable{
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costanti stringa per stampa a video */
	private static final String MSG_TITOLO_REPORT = "REPORT %s";
	private static final String CORNICE_TO_STRING = "---------------------------------------------------\n";
	
	/** Il nome del report */
	private String nomeReport;
	
	/** Il modello per il quale viene generato il Report */
	private Modello mod;
	
	/** Il Test Suite in base al quale viene generato il Report */
	private TestSuite ts;
	
	/** La diagnosi */
	private Diagnosi diag;
	
	/** Gli elenchi delle probabilita' */
	private OrdinaElencoProbabilitaEIntervalliPosizione elencoProb1;
	private OrdinaElencoProbabilitaEIntervalliPosizione elencoProb2;
	
	/** Le distanze */
	private Distanze dist;
	
/** L'istanza unica di Report */
private static Report instance = null;
	
	/**
	 * Costruttore della classe Report
	 *
	 * @param _mod : il modello per cui generare il Report
	 * @param _ts : il test suite per cui generare il Report
	 */
	private Report(Modello _mod, TestSuite _ts) {
		mod = _mod;
		ts = _ts;
		diag = ts.getDiagnosi();
		elencoProb1 = ts.getElencoProb1();
		elencoProb2 = ts.getElencoProb2();
		dist = ts.getDistanze();
	}
	
	/**
	 * Fornisce l'istanza di Report. 
	 *
	 * @param unModello : il modello da associare.
	 * @param unTestSuite : il test suite da associare
	 * @return l'unica istanza Report
	 */
	public static Report getInstance(Modello unModello, TestSuite unTestSuite) {
		if(instance==null)
			instance = new Report(unModello,unTestSuite);
		return instance;
	}
	 
	/**
	 * Cambia report.
	 *
	 * @param nuovoReport : il nuovo Report su cui operare
	 */
	public static void cambiaReport(Report nuovoReport) {
		instance = nuovoReport;
	}
	
	/**
	 * Controlla se l'istanza unica della classe Report e' nulla.
	 *
	 * @return true, se non e' ancora stato creato un report
	 */
	public static boolean isNull() {
		if(instance==null)
			return true;
		return false;
	}
	
	/**
	 * Restituisce il modello relativo al Report
	 *
	 * @return il modello per il quale e' stato generato il Report
	 */
	public Modello getModello() {
		return mod;
	}
	
	/** 
	 * Restituisce il nome del Report
	 *  
	 * @return il valore dell'attributo nome dell'istanza.
	 */
	public String getNomeReport() {
		return nomeReport;
	}
	
	/**
	 * Restituisce il Test Suite
	 *
	 * @return il TS per cui e' stato creato il Report
	 */
	public TestSuite getTS() {
		return ts;
	}

	/**
	 * Assegna il modello al Report
	 *
	 * @param m : il modello da assegnare
	 */
	public void setModello(Modello m) {
		//PRECONDIZIONE
		assert m!=null : "Chiamato setModello con m null";
		mod = m;
	}
	
	/**
	 * Assegna il nome al Report
	 * 
	 * @param unNome : il nome da dare al Report
	 */
	public void setNome(String unNome) {
		nomeReport = unNome;
	}
	
	/**
	 * Assegna il Test Suite
	 *
	 * @param aTS : il TS da assegnare al Report
	 */
	public void setTestSuite(TestSuite aTS) {
		//PRECONDIZIONE
		assert aTS!=null : "Chiamato setTestSuite con un TS nullo";
		ts = aTS;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(CORNICE_TO_STRING);
		risultato.append(CORNICE_TO_STRING+"\n");
		risultato.append(String.format(MSG_TITOLO_REPORT, nome)+"\n");
		risultato.append(CORNICE_TO_STRING);
		risultato.append(CORNICE_TO_STRING+"\n");
		risultato.append("MODELLO" + "\n" + "-----------------" + "\n");
		risultato.append(mod.toString()+"\n\n");
		risultato.append("TEST SUITE" + "\n" + "-----------------" + "\n");
		risultato.append(ts.toString()+"\n\n");
		risultato.append(diag.toString() + "\n");
		risultato.append("ELENCHI DELLE PROBABILITA'" + "\n");
		risultato.append(elencoProb1.toString() + "\n");
		risultato.append(elencoProb2.toString() + "\n");
		risultato.append("CALCOLO DELLE DISTANZE" + "\n");
		risultato.append(dist.toString() + "\n");
		return risultato.toString();
	}
}
