package testSuiteDiagnosi;

import java.util.Vector;
import gestioneModello.Azione;

public class Diagnosi {

	private int id;
	private TestSuite testSuite;
	private Vector<ClasseEquivalenza> elencoClassi;
	private Vector<Azione> elencoAzioni;
	private Vector<Integer> risultatoAzioni;
	private Vector<Vector<Integer>> risultatoClassiPerProbabilita;
	private Vector<Float> risultatoFinaleProbabilita1;
	
	public Diagnosi (int id, TestSuite testSuite) {
		
		this.id = id;
		this.testSuite = testSuite;
		risultatoAzioni = new Vector<Integer>();
		risultatoClassiPerProbabilita = new Vector<Vector<Integer>>();
	}
	
	public void eseguiDiagnosi () {
		
		elencoClassi = testSuite.getElencoClassi();
		elencoAzioni = testSuite.getAzioniClassi();
		
		/** Seleziono una classe per volta. */
		for(int i=0; i<elencoClassi.size(); i++) {
			ClasseEquivalenza classe = elencoClassi.get(i);
			Vector<Coppia> insiemeDiCopertura = classe.getElencoCoppie();
			
			/** Creo la matrice che ha Azioni sulle COLONNE e ogni elenco Azioni della Coppia sulle RIGHE. */
			int[][] matrice = new int[insiemeDiCopertura.size()][elencoAzioni.size()];

			/** Seleziono l'elenco di azioni di un elemento dell'insieme di copertura. */
			for(int c=0; c<insiemeDiCopertura.size(); c++) {
				
				/** Ottengo una coppia elencoAzioniCoppia-valoreRilevazione. */
				Coppia coppia = insiemeDiCopertura.get(c);
				/** Ottengo il vector di Azioni della Coppia e il valore. */
				Vector<Azione> azioni = coppia.getInsiemeCammino();
				String valRil = coppia.getValoreRilevazione();
				
				/** Faccio passare l'elencoAzioni e controllo se l'azione è presente nella Coppia. */
				for(int y=0; y<elencoAzioni.size(); y++) {
					Azione azioneElenco = elencoAzioni.get(y);
					
					/** Se viene trovata, metto 0 se il valore della coppia è OK, 1 se il valore della coppia è KO. Metto -1 se non viene trovata. */
					for(int k=0; k<azioni.size(); k++) {
						if(azioneElenco.getNome() == azioni.get(k).getNome()) {
							if(valRil == "OK")
								matrice[c][y] = 0;
							else if(valRil == "KO") 
								matrice[c][y] = 1;
						}
						else {
							matrice[y][c] = -1;							
						}
					}					
				}
			}
			
			/** Elaboro la matrice (metto a 0 le colonne dove è presente uno 0. */
			for (int s=0; s<elencoAzioni.size(); s++) {
				
				boolean okTrovato = false;
				
				for(int f=0; f<insiemeDiCopertura.size(); f++) {
					int valore = matrice[f][s];
					
					if(!okTrovato) {
						if(valore == 0) {
							/** Inserisco -2 negli spazi vuoti di elementi OK */
							for(int k=0; k<elencoAzioni.size(); k++) {
								if(matrice[f][k] != 1 && matrice[f][k] != 0)
									matrice[f][k] = -2;								
							}
							okTrovato = true;
							f=-1;
						}
					}
					else {
						if(valore == 1) {
							matrice[f][s] = 0;
						}						
					}
				}
			}
			
			/** Calcolo risultati ed inserimento in vettore. */
			for(int s=0; s<elencoAzioni.size(); s++) {
				
				/** Calcolo risultati */
				boolean fineCalcoloAzione = false;
				int valoreAzione = -1;
				int f=0;
				
				do {
					if(matrice[f][s] == 0 || matrice[f][s] == -1) {
						valoreAzione = 0;
						fineCalcoloAzione = true;
					}
					
					if(matrice[f][s] == 1) {
						valoreAzione = 1;
					}
					
					f++;
				} while(f<insiemeDiCopertura.size() || fineCalcoloAzione);
				
				/** Inserimento valore Azione in vettore risultatoAzioni specifico di una Classe */
				risultatoAzioni.add(valoreAzione);
			}
			
			/** Inserimento deli risultati delle Azioni singole della Classe nel vettore risultatoClassiPerProbabilità */
			risultatoClassiPerProbabilita.add(risultatoAzioni);
		}
		
		ProbabilitaMetodo1 metodo1 = new ProbabilitaMetodo1();
		risultatoFinaleProbabilita1 = metodo1.calcolaProbabilita(testSuite, risultatoClassiPerProbabilita);
	}	
	
	public int getId() {
		
		return id;
	}
}
