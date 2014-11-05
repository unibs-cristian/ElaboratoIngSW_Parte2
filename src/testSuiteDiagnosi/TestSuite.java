package testSuiteDiagnosi;
import java.io.Serializable;
import gestioneModello.Modello;
import utilita.GUI;
import java.util.Vector;

public class TestSuite implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String MSG_INTESTAZIONE_TS = "\n\nTEST SUITE PER IL MODELLO %s\n\n";
	
	private Vector <ClasseEquivalenza> elencoClassi;
	private Vector <Diagnosi> elencoDiagnosi;
	private Modello mod;
	
	private static TestSuite instance = null;
	
	private TestSuite() {
		elencoClassi = new Vector <ClasseEquivalenza>();
	}
	
	public static TestSuite getInstance() {
		if(instance==null)
			instance = new TestSuite();
		return instance;
	}
	 
	public static void cambiaTestSuite(TestSuite nuovo) {
		instance = nuovo;
	}
	
	public static boolean isNull() {
		if(instance==null)
			return true;
		return false;
	}
	
	public void addClasseEquivalenza(ClasseEquivalenza clEq) {
		elencoClassi.add(clEq);
	}
	
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
	
	public boolean giaInserita(ClasseEquivalenza ce) {
		for(int i=0; i<getNumeroCE(); i++)
			if(getClasseAt(i).isEqual(ce))
				return true;
		return false;
	}
		
	// Le classi di equivalenza verranno inserite dall'utente come le entita' nel modello.
	public Vector <ClasseEquivalenza> getElencoClassi() {
		return elencoClassi;
	}
	
	public ClasseEquivalenza getClasseAt(int indice) {
		return elencoClassi.elementAt(indice);
	}
	
	public Modello getModello() {
		return mod;
	}
	
	public int getNumeroCE() {
		return elencoClassi.size();
	}
	
	public void setModello(Modello m) {
		mod = m;
	}
	
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
