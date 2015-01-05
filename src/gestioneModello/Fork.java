/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.Util;
import utilita.UtilitaStringhe;

/**
 * Classe Fork.
 * Un'istanza della classe Fork rappresenta un costrutto a due o piu' flussi. Ciascun flusso 
 * rappresenta una sequenza di istruzioni che possono essere svolte in parallelo. 
 * 
 * INVARIANTE DI CLASSE : Tra le entita' che costituiscono il Fork, tutte devono avere nomi diversi 
 * tra di loro.
 */
public class Fork implements Entita{
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costanti stringa per la stampa a video */
	public final static String MSG_FORK = "{ INIZIO FORK %s (ID = %d)";
	public final static String MSG_ENTITA_RAMO_FORK = "%s - ENTITA' RAMO PARALLELO N. %d\n";
	public final static String MSG_JOIN = "FINE FORK %s (ID = %d) }\n";
	
	/** L'id numerico che identifica l'entita' */
	private int id;
	
	/** Il nome dell'entita' */
	private String titolo;
	
	/** Il numero di flussi paralleli che costituiscono il Fork */
	private int numRami;
	
	/** Vettore contenente i flussi paralleli */
	private Ramo[] elencoRami;
	
	/** Elenco di entita', semplici o complesse, che costituiscono il Fork */
	private Vector<Entita> elencoEntita;
	
	/** Valore numerico per la corretta stampa a video del modello */
	private int valoreIndentazione;
	
	/** The id tipo. */
	private String idTipo;
	
	/**
	 * Costruttore della classe Fork
	 *
	 * @param _titolo : il nome da assegnare all'entita'
	 * @param _numRami : il numero di flussi paralleli che avra' il Fork.
	 */
	public Fork(String _titolo, int _numRami) {
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		titolo = _titolo;
		numRami = _numRami;
		elencoRami = new Ramo[numRami];
		elencoEntita = new Vector<Entita>();
		idTipo = ID_TIPO_FORK;
		valoreIndentazione = GestoreModello.getRientro();
	}
	
	public int getId() {
		return id;
	}
	
	public String getIdTipo() {
		return idTipo;
	}
	
	public String getNome() {
		return titolo;
	}

	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) 
			for(int j=0; j<elencoRami[i].getEntitaRamo().size(); j++) 
				addEntita(elencoRami[i].getEntitaRamo().elementAt(j));
		return elencoEntita;
	}
	
	/**
	 * Aggiunge l'entita' e all'elenco di entita' che costituiscono il Fork.
	 *
	 * @param daAggiungere : l'entita' da aggiungere
	 */
	public void addEntita(Entita daAggiungere) {
		//PRECONDIZIONI 
		assert daAggiungere!=null : "Chiamato addEntita con entita' nulla o con numero di ramo errato";
						
		int sizeVecchio = elencoEntita.size();
		elencoEntita.add(daAggiungere);
		
		//POSTCONDIZIONE
		assert elencoEntita.size() == sizeVecchio+1 : "Postcondizione violata nel metodo addEntita";
	}
	
	public void aggiungiAlRamo(Entita daAggiungere, int qualeRamo) {
		//PRECONDIZIONI 
		assert daAggiungere!=null && (qualeRamo>=0 && qualeRamo<=daAggiungere.getRami().length-1) : "Chiamato addEntita con entita' nulla o con numero di ramo errato";
				
		int sizeVecchio = elencoEntita.size();
		elencoRami[qualeRamo].aggiungiEntitaRamo(daAggiungere);
		
		//POSTCONDIZIONE
		assert elencoEntita.size() == sizeVecchio+1 : "Postcondizione violata nel metodo addEntita";

	}
	
	/**
	 * Restituisce il numero di flussi paralleli
	 *
	 * @return numRami
	 */
	public int getNumeroRami() {
		return numRami;
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}

	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public boolean giaInseritaSiNo(String nomeEntitaDaInserire) {
		if(titolo.equalsIgnoreCase(nomeEntitaDaInserire))
			return true;
		boolean trovata = false;
		int i=0;
		while(trovata == false && i<numRami) {
			int j=0;
			while(trovata == false && j<getRami()[i].getNumeroEntita()) {
				if(getRami()[i].getEntitaAt(j).getNome().equalsIgnoreCase(nomeEntitaDaInserire))
					return true;
				else {
					trovata = getRami()[i].getEntitaAt(j).giaInseritaSiNo(nomeEntitaDaInserire);
					j++;
				}
			}
			i++;
		}
		return trovata;	
	}
	
	public boolean isComplessa() {
		return true;
	}

	public boolean isAzione() {
		return false;
	}

	public boolean isBranch() {
		return false;
	}

	public boolean isCiclo() {
		return false;
	}
	
	public boolean isFork() {
		return true;
	}
	 
	public Entita ottieniEntita(int numeroRamo, int posizioneRamo) {
		return elencoRami[numeroRamo].getEntitaAt(posizioneRamo);
	}
	
	public void rimuoviEntita(int idEntitaDaRimuovere) {
		for (int i=0; i<numRami; i++) {
			for(int j=0; j<elencoRami[i].getNumeroEntita(); j++) {
				//Se trova l'entita' la elimina dalle entita' del ramo i-esimo di this e restituisce true			
				if(ottieniEntita(i, j).getId()==idEntitaDaRimuovere)
				{
					if(Util.yesOrNo(String.format(MSG_CONFERMA_CANCELLAZIONE,ottieniEntita(i, j).getNome()))) {
						Modello.getInstance().decrementaContatore();
						if(ottieniEntita(i, j).isAzione())
							Modello.getInstance().rimuoviAzione(ottieniEntita(i, j).getNome());
						System.out.println(String.format(MSG_ENTITA_RIMOSSA, ottieniEntita(i, j).getNome(),ottieniEntita(i, j).getId()));
						elencoRami[i].eliminaEntitaRamo(j);					}
				}
				else 
					ottieniEntita(i, j).rimuoviEntita(idEntitaDaRimuovere);
			}
		}
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append("\n");
        risultato.append(UtilitaStringhe.indenta(String.format(MSG_FORK, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_ENTITA_RAMO_FORK, titolo.toUpperCase(),i+1), SPAZIO, valoreIndentazione));
			if(elencoRami[i].isEmpty())
				risultato.append(UtilitaStringhe.indenta(MSG_RAMO_VUOTO,SPAZIO,valoreIndentazione));
			else
				risultato.append(elencoRami[i].toString());
		}
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_JOIN, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_JOIN, titolo.toUpperCase(),id));
		return risultato.toString();
	}
}
