/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneDati;
import testSuiteDiagnosi.Diagnosi;
import testSuiteDiagnosi.TestSuite;
import gestioneModello.Modello;

import java.io.Serializable;
import java.util.Vector;

/**
 * Classe Report.
 * Un'istanza della classe Report è una struttura contenente tutti i dati 
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
	private String nome;
	
	/** Il modello per il quale viene generato il Report */
	private Modello mod;
	
	/** Il Test Suite in base al quale viene generato il Report */
	private TestSuite ts;
	
	/** Il Vector contenente le diagnosi */
	private Diagnosi diagnosi;
	
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
		diagnosi = ts.getDiagnosi();
	}
	
	/**
	 * Fornisce l'istanza di Report.
	 *
	 * @return l'unica istanza Report
	 */
	public static Report getInstance(Modello mod, TestSuite ts) {
		if(instance==null)
			instance = new Report(mod,ts);
		return instance;
	}
	 
	/**
	 * Cambia report.
	 *
	 * @param altro : il nuovo Report su cui operare
	 */
	public static void cambiaReport(Report altro) {
		instance = altro;
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
	 * Restituisce un elemento del Vector contenente le diagnosi
	 *
	 * @param index : l'indice relativo alla posizione dell'elemento da restituire.
	 * @return la diagnosi corrispondente alla posizione specificata per il Vector
	 */
/*	public Diagnosi getDiagnosiAt(int index) {
		//PRECONDIZIONI 
		assert index>=0 && index<=elencoDiag.size()-1 : "Chiamato getDiagnosiAt con indice errato";
				

		return elencoDiag.elementAt(index);
	}
*/	
	// TODO verificare se servono. La diagnosi non è unica per un certo TS?
	/**
	 * Restituisce il modello relativo al Report
	 *
	 * @return il modello per il quale e' stato generato il Report
	 */
	public Modello getModello() {
		return mod;
	}
	
	public String getNome() {
		return nome;
	}
	
	/**
	 * Restituisce il numero di diagnosi
	 *
	 * @return la dimensione del Vector contenente le diagnosi
	 */
/*	public int getNumeroDiag() {
		return elencoDiag.size();
	}
*/	
	/**
	 * Restituisce il Test Suite
	 *
	 * @return il TS per cui e' stato creato il Report
	 */
	public TestSuite getTS() {
		return ts;
	}
	
	/**
	 * Assegna un insieme di diagnosi al report
	 *
	 * @param diagnosiTS : il Vector da associare
	 */
/*	public void setDiagnosi(Vector <Diagnosi> diagnosiTS) {
		for(int i=0; i<diagnosiTS.size(); i++) {
			elencoDiag.addElement(diagnosiTS.elementAt(i));
		}
	}
*/
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
		nome = unNome;
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
//		for(int i=0; i<getNumeroDiag(); i++)
//			risultato.append(elencoDiag.elementAt(i).toString() + "\n");
		return risultato.toString();
	}
}
