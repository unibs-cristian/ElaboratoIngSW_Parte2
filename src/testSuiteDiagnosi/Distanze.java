package testSuiteDiagnosi;

import java.util.Vector;

import sun.security.jca.GetInstance;
import gestioneModello.Azione;
import gestioneModello.Modello;

public class Distanze {

	private Vector<int[]> vettoreMetodo1;
	private Vector<int[]> vettoreMetodo2;
	private Vector<Integer> risultatoDistanze;
	private Vector<Azione> elencoAzioni;
	
	public Distanze(Vector<int[]> vettoreMetodo1, Vector<int[]> vettoreMetodo2) {
		this.vettoreMetodo1 = vettoreMetodo1;
		this.vettoreMetodo2 = vettoreMetodo2;
		risultatoDistanze = new Vector<Integer>();
	}
	
	private Vector<Integer> calcoloDistanze() {
		
		/** Ottengo vettori di classi e azioni da test suite. */	
		Modello mod = Modello.getInstance();
		elencoAzioni = mod.getElencoAzioni();
		
		/** Seleziono la stessa azione per entrambi i metodi contemporaneamente. */
		for(int a=0; a<elencoAzioni.size(); a++) {
			
			int distanza = 0;
			
			/** Inizializzo i valori dell'azione. */
			int minM1 = vettoreMetodo1.get(a)[0];
			int minM2 = vettoreMetodo2.get(a)[0];
			int maxM1 = vettoreMetodo1.get(a)[1];
			int maxM2 = vettoreMetodo2.get(a)[1];
			
			if(minM1 <= minM2 && maxM1 >= maxM2) {
				risultatoDistanze.add(distanza);
			}
			else if(minM2 <= minM1 && maxM2 >= maxM1) {
				risultatoDistanze.add(distanza);
			}
			else if(minM2-maxM1 >= 0) {
				distanza = minM2-maxM1;
				risultatoDistanze.add(distanza);
			}
			else if(minM1-maxM2 >=0) {
				distanza = minM1-maxM2;
				risultatoDistanze.add(distanza);
			}
		}
		
		stampaRisultatoDistanze();
		stampaDistanzaTotale();
		stampaDistanzaMedia();
		
		return risultatoDistanze;
	}
	
	private void stampaDistanzaTotale() {
		int distanzaTotale = 0;
		for(int i=0; i<risultatoDistanze.size(); i++) {
			distanzaTotale += risultatoDistanze.get(i);
		}
		
		System.out.println("Distanza totale: " + distanzaTotale);
	}
	
	private void stampaDistanzaMedia() {
		float distanzaMedia = 0;
		for(int i=0; i<risultatoDistanze.size(); i++) {
			distanzaMedia += risultatoDistanze.get(i);
		}
		distanzaMedia = distanzaMedia/risultatoDistanze.size();
		
		System.out.println("Distanza media: " + distanzaMedia);
	}
	
	private void stampaRisultatoDistanze() {
		Modello mod = Modello.getInstance();
		elencoAzioni = mod.getElencoAzioni();
		
		for(int i=0; i<risultatoDistanze.size(); i++) {
			System.out.println("dis(" + elencoAzioni.get(i).getNome() + ") = " + risultatoDistanze.get(i));
		}
	}
}
