package gestioneModello;

import java.util.Vector;

public class Diagnosi {

	private int id;
	private Vector<ClasseEquivalenza> elencoClassi;
	private Vector<String[][]> elencoMatrici;
	
	public Diagnosi (int id, TestSuite test) {
		
		this.id = id;
		this.elencoClassi = elencoClassi;
		elencoMatrici = new Vector<String[][]>();
	}
	
	
	
	private void creaArray() {
		
		
		String array = new String[][];
	}	
	
	public int getId() {
		
		return id;
	}
}
