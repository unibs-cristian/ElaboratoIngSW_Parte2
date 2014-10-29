package testSuiteDiagnosi;
import gestioneModello.Azione;
import gestioneModello.Modello;

import java.util.Vector;

public class TestSuite {
	private Vector <ClasseEquivalenza> elencoClassi;
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
	
	//Metodo per restituire le azioni "coinvolte" nel TS
	public Vector <Azione> getAzioniClassi() {
		Vector <Azione> azioniTS = new Vector<Azione>();
		Vector <ClasseEquivalenza> classi = getElencoClassi();
		//Per ogni classe di equivalenza mi faccio restituire le coppie 
		for(int i=0; i<classi.size(); i++) {
			Vector <Coppia> coppieClasse = classi.elementAt(i).getElencoCoppie();
			//Per ogni coppia mi faccio restituire le azioni
			for(int j=0; j<coppieClasse.size(); j++) {
				Vector<Azione> azioniCoppia = coppieClasse.elementAt(j).getInsiemeCammino();
				//Aggiungo a alle azioni del TS solo se il suo id non coincide con quello di una delle entita' gia' 
				//gia' presenti
				for(int k=0; k<azioniCoppia.size(); k++) {
					Azione a = azioniCoppia.elementAt(k);
					for(int l=0; l<azioniTS.size(); l++) {
						boolean giaPresente = false;
						while(giaPresente == false) {
							if(azioniTS.elementAt(l).getId()==a.getId())
								giaPresente = true;
						}
						if(!giaPresente) {
							azioniTS.addElement(a);
						}
					}
				}
			}
		}
		return azioniTS;
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
	
	public void setModello(Modello m) {
		mod = m;
	}
}
