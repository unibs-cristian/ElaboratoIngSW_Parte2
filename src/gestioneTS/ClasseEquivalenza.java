/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;
import inputDati.InserimentoCammino;

import java.util.Vector;
import java.io.Serializable;

import utilita.Util;

/**
 * La Classe ClasseEquivalenza.
 * Una classe di equivalenza di un Test Suite e' costituita un insieme di coppie 
 * insieme del cammino - valore della rilevazione.
 * 
 * INVARIANTE DI CLASSE : 
 * Non contiene coppie uguali tra loro. 
 */
public class ClasseEquivalenza implements Serializable {

	/** Costanti stringa per la stampa a video di messaggi */
	public final static String MSG_INS_COP = "INSERIMENTO INSIEME DI COPERTURA";
	public final static String MSG_COPPIA_AGGIUNTA = "La coppia (Insieme del Cammino ; Valore della Rilevazione) e' stata aggiunta alla classe di equivalenza n.%d";
	public final static String MSG_CONTINUA_SI_NO_COPPIA = "Si desidera inserire un'altra coppia (insieme del cammino ; valore della rilevazione)?";
	
	/** Costante per il salvataggio. */
	private static final long serialVersionUID = 1L;

	/** La cardinalita' della classe. */
	private int cardinalita;
	
	/** Il cammino globale della classe di equivalenza. */
	private CamminoAzioni camminoGlobale;
	
	/** L'insieme di coppie insieme del cammino - valore della rilevazione. */
	private Vector<Coppia> elencoCoppie;

	/**
	 * Costruttore della classe ClasseEquivalenza
	 *
	 * @param _cardinalita : la cardinalita' da assegnare alla classe.
	 */
	public ClasseEquivalenza(int _cardinalita) {
		cardinalita = _cardinalita;
		elencoCoppie = new Vector<Coppia>(); 
	}
	 
	/**
	 * Aggiunge la coppia alla classe.
	 *
	 * @param daAggiungere : la coppia da aggiungere
	 */
	public void addCoppia(Coppia daAggiungere) {
		// PRECONDIZIONE
		assert daAggiungere!=null : "Violata precondizione metodo addCoppia. Passata coppia null.";
		
		int sizeVecchia = elencoCoppie.size();
		elencoCoppie.add(daAggiungere);
		
		// POSTCONDIZIONE
		assert elencoCoppie.size() == sizeVecchia+1 : "Violata postcondizione metodo addCoppia. La coppia non e' stata inserita.";
	}
	
	/**
	 * Fornisce la cardinalita' della classe.
	 *
	 * @return il valore dell'attributo cardinalita'.
	 */
	public int getCardinalita() {
		return cardinalita;
	}
	
	/**
	 * Fornisce le coppie della classe
	 *
	 * @return la struttura dati contenente le coppie
	 */
	public Vector <Coppia> getElencoCoppie() {
		return elencoCoppie;
	}
	
	/**
	 * Fornisce il cammino globale
	 *
	 * @return il cammino globale
	 */
	public CamminoAzioni getCamminoGlobale() {
		return camminoGlobale;
	}
	
	/** 
	 * Controlla se una coppia e' gia' stata inserita come parte 
	 * dell'insieme di copertura di una classe di equivalenza
	 * 
	 * @param daVerificare : la coppia di cui verificare la presenza
	 * @return true, se la coppia e' presente, false altrimenti.
	 * @throws NullPointerException se c == null
	 */
	public boolean coppiaGiaPresente(Coppia daVerificare) {
		//PRECONDIZIONE
		if(daVerificare==null)
			throw new NullPointerException("Violata precondizione metodo giaPresente. Inserita coppia nulla.");
		
		for(int i=0; i<elencoCoppie.size(); i++)
			if(elencoCoppie.elementAt(i).isEqual(daVerificare))
				return true;
		return false;
	}
	
	/** 
	 * @param attuale : la classe di equivalenza per cui viene inserito l'insieme 
	 * di copertura
	 * @param numClasseEq : il numero della classe nel test suite
 	 */
	public void inserisciInsiemeCopertura(int numClasseEq) {
		System.out.println(MSG_INS_COP);
		//Inserimento insieme di copertura (insiemi di coppie insieme cammino - val rilev)
	    do {
			InserimentoCammino inserimentoInsCamm = new InserimentoCammino(this, new CamminoAzioni(false));
			/*
			 * L'inserimento del cammino è compito dei metodi di una classe apposita
			 * che ne gestisce anche lo stato
			 */
			inserimentoInsCamm.inserisciCamm();
			System.out.println(String.format(MSG_COPPIA_AGGIUNTA,numClasseEq));
		} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_COPPIA));
	}
	
	/**
	 * Controlla se due classi di equivalenza sono uguali.
	 *
	 * @param altra : la classe da confrontare
	 * @return true, se le due classi sono uguali, false altrimenti.
	 */
	public boolean isEqual(ClasseEquivalenza altra) {
		// Se le due classi hanno diverso cammino globale o diverso numero di coppie, allora sono diverse.
		if(camminoGlobale.isEqual(altra.getCamminoGlobale()) == false || elencoCoppie.size() != altra.getElencoCoppie().size())
			return false;
		
		else
		{
			for(int i=0; i<elencoCoppie.size(); i++) 
				if(elencoCoppie.elementAt(i).isEqual(altra.getElencoCoppie().elementAt(i)) == false)
					return false;
		}
		return true;
	}
	
	/**
	 * Setta il cammino globale.
	 *
	 * @param cammGlob : il cammino globale da assegnare alla classe di equivalenza
	 */
	public void setCamminoGlobale(CamminoAzioni cammGlob) {
		camminoGlobale = cammGlob;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("Cardinalita' = " + cardinalita + "\n");
		risultato.append("Cammino Globale --> " + camminoGlobale.toString() + "\n");
		for(int i=0; i<elencoCoppie.size(); i++) {
			risultato.append(String.format("Coppia n.%d\n" + elencoCoppie.elementAt(i).toString(),i+1));
		}		
		return risultato.toString();
	}
}
