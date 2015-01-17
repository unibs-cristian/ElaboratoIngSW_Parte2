/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;

import java.io.Serializable;
import java.util.Vector;
import statisticheGuasti.ProbabilitaMetodo1;
import statisticheGuasti.ProbabilitaMetodo2;
import gestioneModello.Azione;
import gestioneModello.Modello;

public class Diagnosi implements Serializable {

	private static final long serialVersionUID = 1L;

	private TestSuite testSuite;
	private Vector<ClasseEquivalenza> elencoClassi;
	private Vector<Azione> elencoAzioni;
	private Vector<Integer> risultatoAzioni;
	private Vector<Vector<Integer>> risultatoClassiPerProbabilita;
	private Vector<Vector<String>> elencoDiagnosiMinimali;
	
	
	/* 	 2: OK
		 1: KO
		-1: !KO
		-2: !OK 	*/

	public Diagnosi (TestSuite testSuite, boolean eseguiProbabilita) {
		this.testSuite = testSuite;
		
		eseguiDiagnosi(eseguiProbabilita);
		
	}
	
	public void eseguiDiagnosi(boolean eseguiProbabilita) {
		elencoDiagnosiMinimali = new Vector<Vector<String>>();
		if(!eseguiProbabilita) {
			calcoloDiagnosiMinimali();
		}
		else {
			eseguiDiagnosiMetodo1();
			eseguiDiagnosiMetodo2();
		}
	}
	
	public Vector<Float> eseguiDiagnosiMetodo1 () {
		
		calcoloDiagnosiMinimali();
		
		return ProbabilitaMetodo1.calcolaProbabilita(testSuite, risultatoClassiPerProbabilita);
	}
	
	private void calcoloDiagnosiMinimali() {
		/** Inizializza i vettori che servono per i risultati da passare a calcolo probabilita'. */
		risultatoClassiPerProbabilita = new Vector<Vector<Integer>>();
		
		/** Ottengo vettori di classi e azioni da test suite. */
		elencoClassi = testSuite.getElencoClassi();		
		Modello mod = Modello.getInstance();
		elencoAzioni = mod.getElencoAzioni();
		
		/** Seleziono una classe per volta. */
		for(int i=0; i<elencoClassi.size(); i++) {
			risultatoAzioni = new Vector<Integer>();
			ClasseEquivalenza classe = elencoClassi.get(i);
			Vector<Coppia> insiemeDiCopertura = classe.getElencoCoppie();
			
			/** Creo la matrice che ha Azioni sulle COLONNE e ogni elenco Azioni della Coppia sulle RIGHE. */
			int[][] matrice = new int[insiemeDiCopertura.size()][elencoAzioni.size()];

			/** Seleziono l'elenco di azioni di un elemento dell'insieme di copertura. */
			for(int c=0; c<insiemeDiCopertura.size(); c++) {
				
				/** Ottengo una coppia elencoAzioniCoppia-valoreRilevazione. */
				Coppia coppia = insiemeDiCopertura.get(c);
				
				/** Ottengo il vector di Azioni della Coppia e il valore. */
				CamminoAzioni camm = coppia.getInsiemeCammino();
				Vector <Azione> azioni = camm.getInsiemeCammino();
				String valRil = coppia.getValoreRilevazione();
				
				for(int a=0; a<elencoAzioni.size(); a++) {
					Azione azioneSingolaElenco = elencoAzioni.get(a);
					
					boolean azioneInCoppia = false;
					int cop=0;
					do {
						if(azioneSingolaElenco.getNome().equalsIgnoreCase(azioni.get(cop).getNome()))
							azioneInCoppia = true;
						cop++;
					}while (cop<azioni.size() && !azioneInCoppia);
					
					if(azioneInCoppia) {
						if(valRil.equalsIgnoreCase("OK")) {
							matrice[c][a] = 2;
						}
						else if(valRil.equalsIgnoreCase("KO")) 
							matrice[c][a] = 1;
					}
					else {
						matrice[c][a] = -1;							
					}
					
				}
			}
			
			metto0AColonneCon0(insiemeDiCopertura, matrice);
			
			calcoloRisultato(insiemeDiCopertura, matrice);
			
			caricamentoVettoreDiagnosiMinimali();
			
			/** Inserimento dei risultati delle Azioni singole della Classe nel vettore risultatoClassiPerProbabilita  */
			risultatoClassiPerProbabilita.add(risultatoAzioni);
		}		
	}

	private void metto0AColonneCon0(Vector<Coppia> insiemeDiCopertura, int[][] matrice) {
		
		/** Elaboro la matrice (metto a 0 le colonne dove e' presente uno 0. */
		for (int col=0; col<elencoAzioni.size(); col++) {
			
			boolean dueTrovato = false;
			
			for(int riga=0; riga<insiemeDiCopertura.size(); riga++) {
				int valore = matrice[riga][col];
				
				if(!dueTrovato) {
					if(valore == 2) {
						/** Inserisco -2 negli spazi vuoti di elementi OK */
						for(int k=0; k<elencoAzioni.size(); k++) {
							if(matrice[riga][k] == -1)
								matrice[riga][k] = -2;								
						}
						dueTrovato = true;
						riga=-1;
					}
				}
				else {
					if(valore == 1) {
						matrice[riga][col] = 2;
					}						
				}
			}
		}		
	}
	
	private void calcoloRisultato(Vector<Coppia> insiemeDiCopertura, int[][] matrice) {
		/** Calcolo risultati ed inserimento in vettore. */
		for(int s=0; s<elencoAzioni.size(); s++) {
			
			/** Calcolo risultati */
			boolean valoreDue = false;
			boolean valoreMenoUno = false;
			boolean valoreUno = false;
			
			for(int f=0; f<insiemeDiCopertura.size(); f++) {
				if(matrice[f][s] == 2) {
					valoreDue = true;
				} 
				else {
					if(matrice[f][s] == -1)
						valoreMenoUno = true;
					if(matrice[f][s] == 1)
						valoreUno = true;
				}
			}
			
			int valoreAzione = -1;
			if(valoreDue)
				valoreAzione = 0;
			else if(valoreUno && valoreMenoUno)
				valoreAzione = 0;
			else if(valoreUno && !valoreMenoUno)
				valoreAzione = 1;
			
			/** Inserimento valore Azione in vettore risultatoAzioni specifico di una Classe */
			risultatoAzioni.add(valoreAzione);
		}
	}
	
	public void caricamentoVettoreDiagnosiMinimali() {
		Vector<String> diagnosiMinimali = new Vector<String>();
		for(int dm=0; dm<risultatoAzioni.size(); dm++) {
			if(risultatoAzioni.get(dm) == 1)
				diagnosiMinimali.add(elencoAzioni.get(dm).getNome());
		}
		elencoDiagnosiMinimali.add(diagnosiMinimali);		
	}

	public Vector<Float> eseguiDiagnosiMetodo2 () {
		/** Ottengo vettori di classi e azioni da test suite. */
		elencoClassi = testSuite.getElencoClassi();		
		Modello mod = Modello.getInstance();
		elencoAzioni = mod.getElencoAzioni();
		
		/** Conto le righe per la matrice finale. */
		int righeMatriceFinale = 0;
		for(int c=0; c<elencoClassi.size(); c++) {
			ClasseEquivalenza classe = elencoClassi.get(c);
			righeMatriceFinale += classe.getElencoCoppie().size()*classe.getCardinalita();
		}
		
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
				
				/** Ottengo una coppia elencoAzioniCoppia-valoreRilevazione. */
				Coppia coppia = insiemeDiCopertura.get(c);
				
				/** Ottengo il vector di Azioni della Coppia e il valore. */
				CamminoAzioni camm = coppia.getInsiemeCammino();
				Vector <Azione> azioni = camm.getInsiemeCammino();
				String valRil = coppia.getValoreRilevazione();
				
				for(int a=0; a<elencoAzioni.size(); a++) {
					Azione azioneSingolaElenco = elencoAzioni.get(a);
					
					boolean azioneInCoppia = false;
					int cop=0;
					do {
						if(azioneSingolaElenco.getNome().equalsIgnoreCase(azioni.get(cop).getNome()))
							azioneInCoppia = true;
						cop++;
					}while (cop<azioni.size() && !azioneInCoppia);
					
					if(azioneInCoppia) {
						if(valRil.equalsIgnoreCase("OK")) {
							matrice[c][a] = 2;
						}
						else if(valRil.equalsIgnoreCase("KO")) 
							matrice[c][a] = 1;
					}
					else {
						matrice[c][a] = -1;							
					}
					
				}
			}
			
			for(int c=0; c<insiemeDiCopertura.size(); c++) {
				for(int a=0; a<elencoAzioni.size(); a++) {
					if(matrice[c][a] == 2) {
						/** Inserisco -2 negli spazi vuoti di elementi OK */
						for(int k=0; k<elencoAzioni.size(); k++) {
							if(matrice[c][k] == -1)
								matrice[c][k] = -2;								
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
		int r;
		
		/** Faccio passare il vettore delle Matrici  e recupero ogni Matrice rappresentante ogni Classe. */
		for(int v=0; v<vettoreMatriciRisultato.size(); v++) {
			int lengthMatrice = vettoreMatriciRisultato.get(v).length;
			int cardinalita = elencoClassi.get(v).getCardinalita();
			int[][] matriceTemp = new int[lengthMatrice][elencoAzioni.size()];
			
			/** Inserisco una matrice di Classe in una temporanea di dimensione uguale. */
			matriceTemp = vettoreMatriciRisultato.get(v);
			
			do {
				for(r=0; r<matriceTemp.length; r++) {
					for(int c=0; c<elencoAzioni.size(); c++) {
						/** Copio la matrice temporanea nella grossa matrice finale. */
						matriceClassiPerProbabilita2[r+ultimaRiga][c] = matriceTemp[r][c];
					}
				}
				/** Tengo in memoria la prima riga libera per poter partire dalla posizione corretta nell'inserimento della prossima matrice. */
				ultimaRiga += r;
				
				cardinalita--;
			} while (cardinalita!=0);
		}

		/** Invio risultati. */
		return ProbabilitaMetodo2.calcolaProbabilita(testSuite, matriceClassiPerProbabilita2);
	}
	

	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("\nELENCO DIAGNOSI MINIMALI\n\n");
		
		for(int i=0; i<elencoClassi.size(); i++) {
			ClasseEquivalenza classe = elencoClassi.get(i);
			Vector<String> diagnosiMinimali = elencoDiagnosiMinimali.get(i);
			risultato.append(String.format("\tCLASSE DI EQUIVALENZA N. %d\n", i+1));
			risultato.append(String.format("\tCardinalita': %d\n", classe.getCardinalita()));
			risultato.append("\tDiagnosi Minimali = {");
			
			for(int j=0; j<diagnosiMinimali.size(); j++) {
				risultato.append(String.format("{%s}", diagnosiMinimali.get(j)));
			}
			
			risultato.append("}\n\n");
		}
		
		return risultato.toString();
	}
}
