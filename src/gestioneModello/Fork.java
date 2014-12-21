/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.GUI;
import utilita.Util;

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
		for(int i=0; i<elencoRami.length; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			for(int j=0; j<entitaRamo.size(); j++) {
				addEntita(entitaRamo.elementAt(j));
			}
		}
		return elencoEntita;
	}
	
	/**
	 * Aggiunge l'entita' e all'elenco di entita' che costituiscono il Fork.
	 *
	 * @param e : l'entita' da aggiungere
	 */
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
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
	
	public boolean giaPresente(String nome) {
		if(titolo.equalsIgnoreCase(nome))
			return true;
		boolean trovata = false;
		int i=0;
		while(trovata == false && i<numRami) {
			int j=0;
			while(trovata == false && j<getRami()[i].getNumeroEntita()) {
				Entita e = getRami()[i].getEntitaAt(j);
				if(e.getNome().equalsIgnoreCase(nome))
					return true;
				else {
					trovata = e.giaPresente(nome);
					j++;
				}
			}
			i++;
		}
		return trovata;	
	}
	 
	public void rimuoviEntitaAt(int id) {
		//Per ogni ramo metto le entita' in un vector. Se una di quelle soddisfa la condizione, la tolgo dal ramo
		for (int i=0; i<numRami; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			//Ricerca l'entita' da eliminare tra le entita' interne del ramo i-esimo
			for(int j=0; j<entitaRamo.size(); j++) {
				Entita e = entitaRamo.elementAt(j);
				//Se la trova la elimina dalle entita' del ramo i-esimo di this e restituisce true
				if(e.getId()==id)
				{
					if(Util.yesOrNo(String.format(MSG_CONFERMA_CANCELLAZIONE,e.getNome()))) {
						elencoRami[i].eliminaEntitaRamo(j);
						Modello.getInstance().decrementaContatore();
						if(e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE) || e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE_COMPOSTA))
							Modello.getInstance().rimuoviAzione(e.getNome());
						System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
					}
				}
				else 
					e.rimuoviEntitaAt(id);
			}
		}
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append("\n");
        risultato.append(GUI.indenta(String.format(MSG_FORK, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_FORK, titolo.toUpperCase(),i+1), SPAZIO, valoreIndentazione));
			if(elencoRami[i].isEmpty())
				risultato.append(GUI.indenta(MSG_RAMO_VUOTO,SPAZIO,valoreIndentazione));
			else
				risultato.append(elencoRami[i].toString());
		}
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(GUI.indenta(String.format(MSG_JOIN, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_JOIN, titolo.toUpperCase(),id));
		return risultato.toString();
	}
}