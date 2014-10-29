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
	private Vector<Float> risultatoFinaleProbabilita;
	
	/* 	 2: OK
		 1: KO
		-1: !KO
		-2: !OK 	*/
	
	public Diagnosi (int id, TestSuite testSuite) {
		
		this.id = id;
		this.testSuite = testSuite;
		risultatoAzioni = new Vector<Integer>();
		risultatoClassiPerProbabilita = new Vector<Vector<Integer>>();
	}
	
	public Vector<Float> eseguiDiagnosiMetodo1 () {
		
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
				
				/** Faccio passare l'elencoAzioni e controllo se l'azione e' presente nella Coppia. */
				for(int y=0; y<elencoAzioni.size(); y++) {
					Azione azioneElenco = elencoAzioni.get(y);
					
					/** Se viene trovata, metto 0 se il valore della coppia e' OK, 1 se il valore della coppia e' KO. Metto -1 se non viene trovata. */
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
			
			/** Elaboro la matrice (metto a 0 le colonne dove e' presente uno 0. */
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
			
			/** Inserimento deli risultati delle Azioni singole della Classe nel vettore risultatoClassiPerProbabilita  */
			risultatoClassiPerProbabilita.add(risultatoAzioni);
		}
		
		ProbabilitaMetodo1 metodo1 = new ProbabilitaMetodo1();
		risultatoFinaleProbabilita = metodo1.calcolaProbabilita(testSuite, risultatoClassiPerProbabilita);
		return risultatoFinaleProbabilita;
	}
	
public Vector<Float> eseguiDiagnosiMetodo2 () {
		
		int righeMatriceFinale = 0;
		elencoClassi = testSuite.getElencoClassi();
		elencoAzioni = testSuite.getAzioniClassi();
		
		Vector<int[][]> vettoreMatriciRisultato = new Vector<int[][]>();
		int[][] matriceClassiPerProbabilita2;
		
		/** Seleziono una classe per volta. */
		for(int i=0; i<elencoClassi.size(); i++) {
			ClasseEquivalenza classe = elencoClassi.get(i);
			Vector<Coppia> insiemeDiCopertura = classe.getElencoCoppie();
			
			/** Creo la matrice che ha Azioni sulle COLONNE e ogni elenco Azioni della Coppia sulle RIGHE. */
			int[][] matrice = new int[insiemeDiCopertura.size()][elencoAzioni.size()];

			/** Seleziono l'elenco di azioni di un elemento dell'insieme di copertura. */
			for(int c=0; c<insiemeDiCopertura.size(); c++) {
				
				righeMatriceFinale++;
				
				/** Ottengo una coppia elencoAzioniCoppia-valoreRilevazione. */
				Coppia coppia = insiemeDiCopertura.get(c);
				/** Ottengo il vector di Azioni della Coppia e il valore. */
				Vector<Azione> azioni = coppia.getInsiemeCammino();
				String valRil = coppia.getValoreRilevazione();
				
				/** Faccio passare l'elencoAzioni e controllo se l'azione e' presente nella Coppia. */
				for(int y=0; y<elencoAzioni.size(); y++) {
					Azione azioneElenco = elencoAzioni.get(y);
					
					/** Se viene trovata, metto 1 se il valore della coppia e' OK, 2 se il valore della coppia e' KO. Metto -1 se non e' OK e -2 se non e' KO. */
					for(int k=0; k<azioni.size(); k++) {
						if(azioneElenco.getNome() == azioni.get(k).getNome()) {
							if(valRil == "OK")
								matrice[c][y] = 1;
							else if(valRil == "KO") 
								matrice[c][y] = 2;
						}
						else {
							if(valRil == "OK")
								matrice[y][c] = -1;	
							else if(valRil == "KO") 
								matrice[c][y] = -2;
						}
					}					
				}
			}
			
			/** Inserimento deli risultati delle Azioni singole della Classe nel vettore risultatoClassiPerProbabilita. */
			vettoreMatriciRisultato.add(matrice);
		}
		
		/** Creazione matrice finale per passaggio risultati a ProbabilitaMetodo2. */
		matriceClassiPerProbabilita2 = new int[righeMatriceFinale][elencoAzioni.size()];
		
		int ultimaRiga = 0;	
		
		/** Faccio passare il vettore delle Matrici  e recupero ogni Matrice rappresentante ogni Classe. */
		for(int v=0; v<vettoreMatriciRisultato.size(); v++) {
			int lengthMatrice = vettoreMatriciRisultato.get(v)[0].length;
			int[][] matriceTemp = new int[lengthMatrice][elencoAzioni.size()];
			
			/** Inserisco una matrice di Classe in una temporanea di dimensione uguale. */
			matriceTemp = vettoreMatriciRisultato.get(v);
			
			for(int r=ultimaRiga; r<righeMatriceFinale; r++) {
				for(int c=0; c<elencoAzioni.size(); c++) {
					/** Copio la matrice temporanea nella grossa matrice finale. */
					matriceClassiPerProbabilita2[r][c] = matriceTemp[r][c];
				}
				/** Tengo in memoria la prima riga libera per poter partire dalla posizione corretta nell'inserimento della prossima matrice. */
				ultimaRiga = r+1;
			}
		}
		
		/** Invio risultati. */
		ProbabilitaMetodo2 metodo2 = new ProbabilitaMetodo2();
		risultatoFinaleProbabilita = metodo2.calcolaProbabilita(testSuite, matriceClassiPerProbabilita2);
		return risultatoFinaleProbabilita;
	}
	
	public int getId() {
		return id;
	}
}
