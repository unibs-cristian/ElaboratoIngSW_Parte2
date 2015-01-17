/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package statisticheGuasti;

import java.util.Vector;

import gestioneModello.Azione;
import gestioneModello.Modello;

// TODO: Auto-generated Javadoc
/**
 * The Class Distanze.
 */
public class Distanze {

	/** The vettore metodo1. */
	private Vector<int[]> vettoreMetodo1;
	
	/** The vettore metodo2. */
	private Vector<int[]> vettoreMetodo2;
	
	/** The risultato distanze. */
	private Vector<Integer> risultatoDistanze;
	
	/** The elenco azioni. */
	private Vector<Azione> elencoAzioni;
	
	/**
	 * Instantiates a new distanze.
	 *
	 * @param vettoreMetodo1 the vettore metodo1
	 * @param vettoreMetodo2 the vettore metodo2
	 */
	public Distanze(Vector<int[]> vettoreMetodo1, Vector<int[]> vettoreMetodo2) {
		this.vettoreMetodo1 = vettoreMetodo1;
		this.vettoreMetodo2 = vettoreMetodo2;
		risultatoDistanze = new Vector<Integer>();
		
		calcoloDistanze();
	}
	
	/**
	 * Calcolo distanze.
	 *
	 * @return the vector
	 */
	public Vector<Integer> calcoloDistanze() {
		
		/** Ottengo vettori di classi e azioni da test suite. */	
		Modello mod = Modello.getInstance();
		elencoAzioni = mod.getElencoAzioni();
		
		/** Seleziono la stessa azione per entrambi i metodi contemporaneamente. */
		for(int a=0; a<elencoAzioni.size(); a++) {
			
			int distanza = 0;
			
			/** Inizializza i valori dell'azione. */
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
		
		System.out.println("\nRISULTATO DISTANZE");
		System.out.print(toString());
		
		return risultatoDistanze;
	}
	
	/**
	 * Stampa distanza totale.
	 */
	private StringBuffer stampaDistanzaTotale() {
		StringBuffer risultato = new StringBuffer();
		int distanzaTotale = 0;
		for(int i=0; i<risultatoDistanze.size(); i++) {
			distanzaTotale += risultatoDistanze.get(i);
		}
		
		risultato.append("\n\nDistanza totale: " + distanzaTotale);
		
		return risultato;
	}
	
	/**
	 * Stampa distanza media.
	 */
	private StringBuffer stampaDistanzaMedia() {
		StringBuffer risultato = new StringBuffer();
		float distanzaMedia = 0;
		for(int i=0; i<risultatoDistanze.size(); i++) {
			distanzaMedia += risultatoDistanze.get(i);
		}
		distanzaMedia = distanzaMedia/risultatoDistanze.size();
		
		risultato.append("\nDistanza media: " + distanzaMedia);
		
		return risultato;
	}
	
	/**
	 * Stampa risultato distanze.
	 */
	private StringBuffer stampaRisultatoDistanze() {
		StringBuffer risultato = new StringBuffer();
		Modello mod = Modello.getInstance();
		elencoAzioni = mod.getElencoAzioni();
		
		for(int i=0; i<risultatoDistanze.size(); i++) {
			risultato.append("\nDis(" + elencoAzioni.get(i).getNome() + ") = " + risultatoDistanze.get(i));
		}
		
		return risultato;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(stampaRisultatoDistanze());
		risultato.append(stampaDistanzaTotale());
		risultato.append(stampaDistanzaMedia());
		risultato.append("\n");
		
		return risultato.toString();
	}
}
