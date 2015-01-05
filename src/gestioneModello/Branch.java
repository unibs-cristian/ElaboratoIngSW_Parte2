/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import inputDati.GestoreModello;

import java.util.Vector;

import utilita.*;

/**
 * Classe Branch.
 * Un'istanza della classe Branch rappresenta un costrutto avente un solo flusso d'ingresso e due o 
 * piu' flussi d'uscita. Solo uno dei flussi d'uscita puo' essere eseguito. Sono analoghi ai 
 * costrutti if-else if-...-else.
 * 
 * INVARIANTE DI CLASSE : Tra le entita' che costituiscono il Branch, non ce ne devono essere due con
 * lo stesso nome.
 */
public class Branch implements Entita{
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_BRANCH = "[ INIZIO BRANCH %s (ID = %d)";
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_ENTITA_RAMO_BRANCH = "%s - ENTITA' RAMO N. %d\n";
	
	/** Costante stringa per la stampa a video */
	public final static String MSG_MERGE = "FINE BRANCH %s (ID = %d) ]\n";
	
	/** L'id numerico dell'entita'. */
	private int id;
	
	/** Il nome dell'entita' */
	private String titolo;
	
	/** Il numero di flussi alternativi che costituiscono l'entita' */
	private int numeroRami;
	
	/** Array contenente le istanze dei rami che costituiscono l'entita' */
	private Ramo[] elencoRami;
	
	/** Vector contenente tutte le entita' interne al branch. */
	private Vector <Entita> elencoEntita;
	
	/** L'indentazione per la stampa a video. */
	private int valoreIndentazione;
	
	/** La stringa che rappresenta sinteticamente il tipo dell'entita' */
	private String idTipo;
	
	/**
	 * Costruttore della classe Branch
	 *
	 * @param _titolo : il nome dell'entita' che e' stato inserito dall'utente.
	 * @param _numeroRami : il numero di rami che e' stato deciso dall'utente
	 */
	public Branch (String _titolo, int _numeroRami) {
		titolo = _titolo;
		numeroRami = _numeroRami;
		elencoRami = new Ramo [numeroRami];
		idTipo = ID_TIPO_BRANCH;
		elencoEntita = new Vector<Entita>();
		valoreIndentazione = GestoreModello.getRientro();
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
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
	
	/**
	 * Restituisce il numero di rami.
	 *
	 * @return numero rami
	 */
	public int getNumeroRami() {
		return numeroRami;
	}
	
	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++)
			for(int j=0; j<elencoRami[i].getEntitaRamo().size(); j++)
				addToElencoEntita(elencoRami[i].getEntitaRamo().elementAt(j));
		return elencoEntita;
	}
	 
	public boolean giaInseritaSiNo(String nomeEntitaDaInserire) {
		if(titolo.equalsIgnoreCase(nomeEntitaDaInserire))
			return true;
		boolean trovata = false;
		int i=0;
		// Cerca l'entita' nei vari rami.
		while(trovata == false && i<numeroRami) {
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
	
	/**
	 * Aggiunge l'entita' e all'elenco delle entita' interne del Branch.
	 *
	 * @param daAggiungere : l'entita' da aggiungere
	 */
	public void addToElencoEntita(Entita daAggiungere) {
		//PRECONDIZIONI 
		assert daAggiungere!=null : "Chiamato addEntita con entita' nulla o con numero di ramo errato";
		
		int sizeVecchio = elencoEntita.size();
		elencoEntita.add(daAggiungere);
		
		//POSTCONDIZIONE
		assert elencoEntita.size() == sizeVecchio+1 : "Postcondizione violata nel metodo addEntita";
	}		
	
	/**
	 * Aggiunge l'entita' e all'elenco delle entita' che costituiscono il ramo r del Branch.
	 * 
	 * @param daAggiungere : l'entita' da aggiungere
	 * @param qualeRamo : il ramo al cui elenco deve essere aggiunta l'entita' e
	 */
	public void aggiungiAlRamo(Entita daAggiungere, int qualeRamo) {
		//PRECONDIZIONI 
		assert daAggiungere!=null && (qualeRamo>=0 && qualeRamo<=daAggiungere.getRami().length-1) : "Chiamato addEntita con entita' nulla o con numero di ramo errato";

		int sizeVecchio = elencoEntita.size();
		elencoRami[qualeRamo].aggiungiEntitaRamo(daAggiungere);
		
		//POSTCONDIZIONE
		assert elencoEntita.size() == sizeVecchio+1 : "Postcondizione violata nel metodo addEntita";
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public boolean isComplessa() {
		return true;
	}

	public boolean isAzione() {
		return false;
	}
	
	public boolean isBranch() {
		return true;
	}
	
	public boolean isCiclo() {
		return false;
	}
	
	public boolean isFork() {
		return false;
	}
	
	public Entita ottieniEntita(int numeroRamo, int posizioneRamo) {
		return elencoRami[numeroRamo].getEntitaAt(posizioneRamo);
	}
	
	public void rimuoviEntita(int idEntitaDaRimuovere) {
		for (int i=0; i<numeroRami; i++) {
			for(int j=0; j<elencoRami[i].getNumeroEntita(); j++) {
				//Se trova l'entita' la elimina dalle entita' del ramo i-esimo di this e restituisce true
				if(ottieniEntita(i, j).getId()==idEntitaDaRimuovere)
				{
					if(Util.yesOrNo(String.format(MSG_CONFERMA_CANCELLAZIONE,ottieniEntita(i, j).getNome()))) {
						Modello.getInstance().decrementaContatore();
						if(ottieniEntita(i, j).isAzione())
							Modello.getInstance().rimuoviAzione(ottieniEntita(i, j).getNome());					
						elencoRami[i].eliminaEntitaRamo(j);
						System.out.println(String.format(MSG_ENTITA_RIMOSSA, ottieniEntita(i, j).getNome(),ottieniEntita(i, j).getId()));
					}
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
        risultato.append(UtilitaStringhe.indenta(String.format(MSG_BRANCH, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_ENTITA_RAMO_BRANCH, titolo.toUpperCase(),i+1), SPAZIO, valoreIndentazione));
			if(elencoRami[i].isEmpty())
				risultato.append(UtilitaStringhe.indenta(MSG_RAMO_VUOTO,SPAZIO,valoreIndentazione));
			else
				risultato.append(elencoRami[i].toString());
		}
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(UtilitaStringhe.indenta(String.format(MSG_MERGE, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_MERGE, titolo.toUpperCase(),id));
		return risultato.toString();
	}
}
