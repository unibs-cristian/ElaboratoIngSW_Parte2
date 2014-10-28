package testSuiteDiagnosi;

import java.util.Vector;
import gestioneModello.Azione;

public class Diagnosi {

	private int id;
	private TestSuite testSuite;
	private Vector<ClasseEquivalenza> elencoClassi;
	private Vector<Azione> elencoAzioni;
	private Vector<String[][]> elencoMatrici;
	
	public Diagnosi (int id, TestSuite testSuite) {
		
		this.id = id;
		this.testSuite = testSuite;
		elencoMatrici = new Vector<String[][]>();
	}
	
	public void eseguiDiagnosi () {
		
		elencoClassi = testSuite.getClassi();
		elencoAzioni = testSuite.getElencoAzioni();
		
		/** Seleziono una classe per volta. */
		for(int i=0; i<elencoClassi.size(); i++) {
			ClasseEquivalenza classe = elencoClassi.get(i);
			Vector<Coppia> idc = classe.getInsiemeDiCopertura();
			
			String[][] matrice = creaMatrice(idc);

			/** Seleziono l'elenco di azioni di un elemento dell'insieme di copertura. */
			for(int c=1; c<idc.size()+1; c++) {
				
				Vector<Coppia> insiemeDiCopertura = idc.get(i);
				Vector<Azione> azioni = insiemeDiCopertura.getAzioni();
				matrice[0][c] = insiemeDiCopertura.getValoreRilevazione();
				
				boolean valRil;
				if(insiemeDiCopertura.getValoreRIlevazione() == "OK")
					valRil = true;
				else
					valRil = false;
				
				for(int y=1; y<elencoAzioni.size(); y++) {
					String nomeAzioneTabella = matrice[y][0];
					
					for(int j=0; j<elencoAzioni.size(); j++) {
						Azione action = elencoAzioni.get(j);
						String nomeAzione = action.getNome();
						
						if(nomeAzione == nomeAzioneTabella && valRil == true)
							matrice[y][c] = "0";
						else if(nomeAzione == nomeAzioneTabella && valRil == false)
							matrice[y][c] = "1";
						else
							matrice[y][c] = null;
					}
					
				}
			}
			
			
				
			// ProbabilitaMetodo1 metodo = new ProbabilitaMetodo1(testSuite, risultato);
		}
	}	
	
	private String[][] creaMatrice (Vector<Coppia> insiemeDiCopertura) {
		
		String[][] matrice = new String[elencoAzioni.size()+1][insiemeDiCopertura.size()+1];
		
		for(int i=0; i<elencoAzioni.size(); i++) {
			matrice[i+1][0] = elencoAzioni.get(i).getNome();			
		}
		
		return matrice;
	}
	
	public int getId() {
		
		return id;
	}
}
