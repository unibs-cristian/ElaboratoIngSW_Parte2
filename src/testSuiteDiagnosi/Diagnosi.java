package testSuiteDiagnosi;

import java.util.Vector;

public class Diagnosi {

	private int id;
	private TestSuite testSuite;
	private Vector<ClasseEquivalenza> elencoClassi;
	private Vector<String[][]> elencoMatrici;
	
	public Diagnosi (int id, TestSuite testSuite) {
		
		this.id = id;
		this.testSuite = testSuite;
		elencoMatrici = new Vector<String[][]>();
	}
	
	public void eseguiDiagnosi () {
		
		elencoClassi = testSuite.getClassi();
		for(int i=0; i<elencoClassi.size(); i++) {
			ClasseEquivalenza classe = elencoClassi.get(i);
			Vector<String> elementi = classe.getElementi();
			
			for(int j=0; j<elementi.size(); j++) {
								
			}
		}
		creaArray();
	}
	
	private void creaArray() {
		
		
		String array = new String[][];
	}	
	
	public int getId() {
		
		return id;
	}
}
