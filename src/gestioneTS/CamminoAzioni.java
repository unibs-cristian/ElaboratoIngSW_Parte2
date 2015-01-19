/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;
import gestioneModello.Azione;

import java.io.Serializable;
import java.util.Vector;

import controlloCammino.StatoCammino;
import controlloCammino.StatoVuoto;

/**
 * Classe CamminoAzioni.
 * Rappresenta un insieme di azioni. Puo' essere un cammino globale o un insieme del cammino. 
 * Il suo stato evolve in base a cio' che fa l'utente in fase di inserimento.
 */
public class CamminoAzioni implements Serializable {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costanti stringa per messaggi d'errore */
	public final static String MSG_CAMMINO_VUOTO = "Errore! Il cammino inserito e' vuoto.";
	public final static String MSG_CAMMINO_NON_VALIDO = "Errore! Cammino non valido perche' %s .Inserire nuovamente.";
	public final static String MSG_CAMMINO_INCORRETTO = "non corretto in base alla struttura del modello";
	public final static String MSG_NON_SOTTOINSIEME = "non e' un sottoinsieme del cammino globale";
	
	/** Il Vector contenente le azioni del cammino */
	private Vector <Azione> insiemeCammino;
	
	/** Dice il tipo di cammino. Se e' vero, il cammino e' globale, altrimenti e' un insieme del cammino. */
	private boolean globaleSiNo;  
	
	/** Lo stato del cammino. */
	private StatoCammino statoCorrente;
	
	/**
	 * Costruttore della classe CamminoAzioni
	 *
	 * @param _globaleSiNo : se true, crea un cammino globale, altrimenti un insieme del cammino.
	 */
	public CamminoAzioni(boolean _globaleSiNo) {
		globaleSiNo = _globaleSiNo;
		insiemeCammino = new Vector<Azione>();
		statoCorrente = new StatoVuoto();
	}
	
	/**
	 * Aggiungi un'azione al cammino
	 *
	 * @param daAggiungere : l'azione da aggiungere al cammino
	 */
	public void aggiungiAzione(Azione daAggiungere) {
		//PRECONDIZIONE
		assert daAggiungere!=null : "Violata precondizione metodo aggiungiAzione. Passata azione nulla";		
		
		int sizeVecchio = insiemeCammino.size();
		insiemeCammino.add(daAggiungere);
		
		//POSTCONDIZIONE
		assert insiemeCammino.size() == sizeVecchio+1 : "Violata postcondizione metodo aggiungiAzione. L'azione non e' stata aggiunta al cammino.";
	}
	
	/**
	 * Controlla se un'azione e' presente nel cammino.
	 *
	 * @param daVerificare : l'azione di cui verificare la presenza nel cammino
	 * @return true, se presente, false altrimenti.
	 */
	public boolean azioneGiaPresente(Azione daVerificare) {
		for(int i=0; i<insiemeCammino.size(); i++) {
			if(daVerificare.getNome().equalsIgnoreCase(getAzioneAt(i).getNome()))
				return true;
		}
		return false;
	}
	
	public void azzeraAzioni() {
		while(!(insiemeCammino.isEmpty()))
			insiemeCammino.remove(getAzioneAt(0));
		
		//POSTOCONDIZIONE
		assert insiemeCammino.isEmpty() : "Postcondizione violata per il metodo azzeraAzioni. insiemeCamminon non e' vuoto.";
	}
	
	/**
	 * Fornisce le azioni facenti parte del cammino.
	 *
	 * @return il Vector contenente le azioni
	 */
	public Vector<Azione> getInsiemeCammino() {
		return insiemeCammino;
	}
	
	/**
	 * Fornisce l'azione all'indice specificato
	 *
	 * @param index : la posizione dell'azione nel Vector
	 * @return l'elemento del Vector alla posizione 'index'
	 */
	public Azione getAzioneAt(int index) {
		return insiemeCammino.elementAt(index);
	}
	
	/**
	 * Fornisce lo stato del cammino.
	 *
	 * @return lo stato del cammino
	 */
	public StatoCammino getStato() {
		return statoCorrente;
	}
	
	/**
	 * Metodo che verifica se un cammino e' sottoinsieme di un altro.
	 *
	 * @param altro : il cammino con cui confrontare.
	 * @return true se questo cammino e' sottoinsieme del cammino 'altro'.
	 */
	public boolean inclusoIn(CamminoAzioni altro) {
		if(this.insiemeCammino.size() > altro.getInsiemeCammino().size())
			return false;
		for(int i=0; i<insiemeCammino.size(); i++)
			if(altro.azioneGiaPresente(getAzioneAt(i)) == false)
				return false;
		return true;
	}
	
	/**
	 * Controlla se il cammino e' vuoto
	 *
	 * @return true, se il cammino e' vuoto, false altrimenti.
	 */
	public boolean isEmpty() {
		return insiemeCammino.isEmpty();
	}
	
	/**
	 * Verifica se due cammini sono uguali.
	 *
	 * @param altro : il cammino con cui confrontare
	 * @return true se i due cammini contengono le stesse azioni, false altrimenti
	 */
	public boolean isEqual(CamminoAzioni altro) {
		if(altro.getInsiemeCammino().size() != insiemeCammino.size())
			return false;
		else {
			for(int i=0; i<insiemeCammino.size(); i++) {
				if(getAzioneAt(i).getNome().equalsIgnoreCase(altro.getAzioneAt(i).getNome())==false)
					return false;
			}
		}
		return true;
	}
	
	public boolean isValid(ClasseEquivalenza classe) {
		if(isEmpty()) {
			System.out.println(MSG_CAMMINO_VUOTO);
			return false;
		}
		if(!getStato().isValid()) {
			System.out.println(String.format(MSG_CAMMINO_NON_VALIDO,MSG_CAMMINO_INCORRETTO));
			return false;
		}
		//Se un insieme del cammino non e' sottoinsieme del cammino globale, viene considerato non valido
		else if(!globaleSiNo() && !inclusoIn(classe.getCamminoGlobale())) {
			System.out.println(String.format(MSG_CAMMINO_NON_VALIDO, MSG_NON_SOTTOINSIEME));
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Controlla se un cammino e' globale o e' un insieme del cammino.
	 *
	 * @return true, se il cammino e' globale, false se e' un insieme del cammino.
	 */
	public boolean globaleSiNo() {
		return globaleSiNo;
	}
	
	public void setInsiemeCammino(Vector <Azione> nuovo) {
		insiemeCammino = nuovo;
	}
	
	/**
	 * Setta lo stato del cammino.
	 *
	 * @param state : lo stato da assegnare al cammino.
	 */
	public void setStatoCammino(StatoCammino state) {
		statoCorrente = state;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(getAzioneAt(0).getNome());
		for(int i=1; i<insiemeCammino.size(); i++)
			risultato.append("," + getAzioneAt(i).getNome());
			return risultato.toString();
	}
}
