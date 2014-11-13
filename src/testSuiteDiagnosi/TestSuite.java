/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package testSuiteDiagnosi;
import java.io.Serializable;
import gestioneModello.Modello;
import utilita.GUI;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class TestSuite.
 */
public class TestSuite implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_INTESTAZIONE_TS. */
	public final static String MSG_INTESTAZIONE_TS = "\n\nTEST SUITE PER IL MODELLO %s\n\n";
	
	/** The elenco classi. */
	private Vector <ClasseEquivalenza> elencoClassi;
	
	/** The elenco diagnosi. */
	private Vector <Diagnosi> elencoDiagnosi;
	
	/** The mod. */
	private Modello mod;
	
	/** The instance. */
	private static TestSuite instance = null;
	
	/**
	 * Instantiates a new test suite.
	 */
	private TestSuite() {
		elencoClassi = new Vector <ClasseEquivalenza>();
	}
	
	/**
	 * Gets the single instance of TestSuite.
	 *
	 * @return single instance of TestSuite
	 */
	public static TestSuite getInstance() {
		if(instance==null)
			instance = new TestSuite();
		return instance;
	}
	 
	/**
	 * Cambia test suite.
	 *
	 * @param nuovo the nuovo
	 */
	public static void cambiaTestSuite(TestSuite nuovo) {
		instance = nuovo;
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
	 * Adds the classe equivalenza.
	 *
	 * @param clEq the cl eq
	 */
	public void addClasseEquivalenza(ClasseEquivalenza clEq) {
		elencoClassi.add(clEq);
	}
	
	/**
	 * Adds the diagnosi.
	 *
	 * @param diag the diag
	 */
	public void addDiagnosi(Diagnosi diag) {
		elencoDiagnosi.add(diag);
	}
	
	//TODO Chiedere se serve!!
	 
//	public Vector <Azione> getAzioniTS() {
		
	/*	Vector <Azione> azioniTS = new Vector<Azione>();
		Vector <ClasseEquivalenza> classi = getElencoClassi();
		//Per ogni classe di equivalenza mi faccio i cammini di esecuzione globali 
		for(int i=0; i<classi.size(); i++) {
			CamminoAzioni cammGlob = classi.elementAt(i).getCamminoGlobale();
			//Per quel cammino globale ottengo le azioni
			Vector <Azione> azCammGlob = cammGlob.getInsiemeCammino();
			/* 
			 * Le azioni del cammino globale vengono aggiunte a quelle del TS solo se non sono 
			 * gia' presenti nel secondo vettore. 
			 */
			/*	for(int j=0; j<azCammGlob.size(); j++) {
					Azione a = azCammGlob.elementAt(j);
					boolean giaPresente = false;
					for(int k=0; k<azioniTS.size(); k++) {
						while(giaPresente == false) {
							if(azioniTS.elementAt(k).getNome().equalsIgnoreCase(a.getNome()))
								giaPresente = true;
						}
					}
					// Se non ha trovato a in azioniTS, la aggiunge
					if(giaPresente == false)
						azioniTS.addElement(a);
				}						
			}  
		return azioniTS;*/
//	}
	
	/**
	 * Gia inserita.
	 *
	 * @param ce the ce
	 * @return true, if successful
	 */
	public boolean giaInserita(ClasseEquivalenza ce) {
		for(int i=0; i<getNumeroCE(); i++)
			if(getClasseAt(i).isEqual(ce))
				return true;
		return false;
	}
		
	// Le classi di equivalenza verranno inserite dall'utente come le entita' nel modello.
	/**
	 * Gets the elenco classi.
	 *
	 * @return the elenco classi
	 */
	public Vector <ClasseEquivalenza> getElencoClassi() {
		return elencoClassi;
	}
	
	/**
	 * Gets the classe at.
	 *
	 * @param indice the indice
	 * @return the classe at
	 */
	public ClasseEquivalenza getClasseAt(int indice) {
		return elencoClassi.elementAt(indice);
	}
	
	/**
	 * Gets the elenco diagnosi.
	 *
	 * @return the elenco diagnosi
	 */
	public Vector <Diagnosi> getElencoDiagnosi() {
		return elencoDiagnosi;
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
	 * Gets the numero ce.
	 *
	 * @return the numero ce
	 */
	public int getNumeroCE() {
		return elencoClassi.size();
	}
	
	/**
	 * Sets the modello.
	 *
	 * @param m the new modello
	 */
	public void setModello(Modello m) {
		mod = m;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(GUI.incorniciaStringa(MSG_INTESTAZIONE_TS), mod.getNome()));
		for(int i=0; i<getNumeroCE(); i++) {
			risultato.append("\n\n");
			risultato.append(String.format("- CLASSE DI EQUIVALENZA N.%d\n"+getClasseAt(i).toString(),i+1));
		}
		return risultato.toString();
	}
}
