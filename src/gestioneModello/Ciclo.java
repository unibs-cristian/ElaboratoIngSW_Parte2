/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.Util;
import utilita.UtilitaStringhe;

/**
 * Classe Ciclo.
 * Un'istanza della classe Ciclo rappresenta un costrutto a due rami, analogo ai costrutti di tipo
 * while o do-while. 
 * 
 * INVARIANTE DI CLASSE : Tra le entita' che costituiscono il Ciclo, non ce ne devono essere due con
 * lo stesso nome.
 */
public class Ciclo implements Entita {

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
	 * @param daAggiungere l'entita' da aggiungere.
	 */
	public void addToElencoEntita(Entita daAggiungere) {
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
	
	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) {
			for(int j=0; j<elencoRami[i].getEntitaRamo().size(); j++) {
				addToElencoEntita(elencoRami[i].getEntitaRamo().elementAt(j));
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
	
	public boolean giaInseritaSiNo(String nomeEntitaDaInserire) {
		if(titolo.equalsIgnoreCase(nomeEntitaDaInserire))
			return true;
		boolean trovata = false;
		int i=0;
		while(trovata == false && i<NUM_RAMI_CICLO) {
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
		return true;
	}

	public boolean isFork() {
		return false;
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
						elencoRami[i].eliminaEntitaRamo(j);
					}
				}
				else 
					ottieniEntita(i, j).rimuoviEntita(idEntitaDaRimuovere);
			}
		}
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("\n");
        risultato.append(UtilitaStringhe.indenta(String.format(MSG_CICLO, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		if(elencoRami[0].getEntitaRamo().isEmpty())
		{
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			risultato.append(UtilitaStringhe.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		}
		else
		{
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			for(int j=0; j<elencoRami[0].getEntitaRamo().size(); j++) {
				risultato.append(elencoRami[0].toString());
			}
		}
		risultato.append(UtilitaStringhe.indenta(String.format(MSG_COND_PERMANENZA_CICLO,titolo), SPAZIO, valoreIndentazione));
		if(elencoRami[1].getEntitaRamo().isEmpty())
			risultato.append(UtilitaStringhe.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		else
			risultato.append(elencoRami[1].toString());
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(),id));
		return risultato.toString();
	}
}
