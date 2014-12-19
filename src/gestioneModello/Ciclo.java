/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.GUI;
import utilita.Util;

/**
 * Classe Ciclo.
 * Un'istanza della classe Ciclo rappresenta un costrutto a due rami, analogo ai costrutti di tipo
 * while o do-while. 
 */
public class Ciclo implements Entita{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_CICLO = "< INIZIO CICLO %s (ID = %d) - MERGE";
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_ATTIVITA_INIZIALI = "%s - ENTITA' RAMO N.1 (ATTIVITA' INIZIALI)\n";
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_COND_PERMANENZA_CICLO = "%s - ENTITA RAMO N.2 (CONDIZIONE DI PERMANENZA NEL CICLO)\n";
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_BRANCH_CICLO = "FINE CICLO %s (ID = %d) - BRANCH >\n";
	
	/** Il numero di rami del ciclo. */
	public final static int NUM_RAMI_CICLO = 2;
	
	/** Id numerico dell'entita' */
	private int id;
	
	/** Il titolo dell'entita' */
	private String titolo;
	
	/** Il numero di rami che costituiscono l'entita'. */
	private int numRami;
	
	/** Vector contenente le istanze dei rami che costituiscono il Ciclo. */
	private Ramo [] elencoRami;
	
	/** Vector contenente tutte le entita' interne (semplici o complesse) che costituiscono il ciclo. */
	private Vector<Entita> elencoEntita;
	
	/** L'indentazione per stampare a video correttamente il modello. */
	private int valoreIndentazione;
	
	/** Stringa che codifica il tipo dell'entita'. */
	private String idTipo;
	
	/**
	 * Costruttore della classe Ciclo
	 *
	 * @param _titolo : il nome dell'entita' che e' stato inserito dall'utente.
	 */
	public Ciclo(String _titolo)
	{
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		titolo = _titolo;
		numRami = NUM_RAMI_CICLO;
		elencoRami = new Ramo [NUM_RAMI_CICLO]; 
		elencoEntita = new Vector<Entita>();
		idTipo = ID_TIPO_CICLO;
		valoreIndentazione = GestoreModello.getRientro();
	}
	
	/**
	 * Aggiunge l'entita' e all'elenco di entita' interne del Ciclo
	 *
	 * @param e l'entita' da aggiungere.
	 */
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
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
	
	public int getId() {
		return id;
	}
	
	public String getIdTipo() {
		return idTipo;
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public String getNome() {
		return titolo;
	}
	
	/**
	 * Ritorna il numero rami.
	 *
	 * @return numero rami
	 */
	public int getNumeroRami() {
		return numRami;
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}
	
	public boolean giaPresente(String nome) {
		if(titolo.equalsIgnoreCase(nome))
			return true;
		boolean trovata = false;
		int i=0;
		while(trovata == false && i<NUM_RAMI_CICLO) {
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
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("\n");
        risultato.append(GUI.indenta(String.format(MSG_CICLO, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		if(elencoRami[0].getEntitaRamo().isEmpty())
		{
			risultato.append(GUI.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		}
		else
		{
			risultato.append(GUI.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			for(int j=0; j<elencoRami[0].getEntitaRamo().size(); j++) {
				risultato.append(elencoRami[0].toString());
			}
		}
		risultato.append(GUI.indenta(String.format(MSG_COND_PERMANENZA_CICLO,titolo), SPAZIO, valoreIndentazione));
		if(elencoRami[1].getEntitaRamo().isEmpty())
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		else
			risultato.append(elencoRami[1].toString());
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(GUI.indenta(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(),id));
		return risultato.toString();
	}
}