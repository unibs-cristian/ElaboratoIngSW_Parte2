/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;
import java.util.Vector;

/**
 * Classe Azione.
 * Un'istanza di questa classe rappresenta un'azione di un diagramma di attivita'. Si tratta 
 * di un'entita' semplice che puo' essere aggiunta ad altre entita' complesse, come Branch, Cicli e 
 * Fork.
 * 
 * @author Cristian Sampietri
 */
public class Azione implements Entita{
	
	/** La costante serialVersionUID per il salvataggio. */
	private static final long serialVersionUID = 1L;

	/** Stringa usata in fase di stampa a video. */
	public final static String MSG_AZIONE = "Azione %s";
	
	public final static String MSG_AZIONE_COMPOSTA = "Azione Composta %s             Titolo modello di riferimento : %s";
	
	/** L'id all'interno del Modello a cui appartiene l'azione. */
	private int id;
	
	/** Il titolo dell'azione (univoco all'interno del modello) */
	private String titolo;
	
	/** L'indentazione usata per stampare il modello in modo piu' intuitivo possibile. */
	private int valoreIndentazione;
	
	/** Stringa per tenere traccia del tipo di entita' */
	private String idTipo;
	
	/** Il nome del modello a cui si riferisce l'entita' composta */
	private String titoloModelloComposta;
	
	/** Dice se l'azione e' composta o semplice */
	private boolean compostaSiNo;
	
	/**
	 * Costruttore per la classe Azione
	 *
	 * @param _titolo : il nome da assegnare all'azione
	 * @param _compostaSiNo : se e' composta
	 */
	public Azione(String _titolo, boolean _compostaSiNo) {
		titolo = _titolo;
		compostaSiNo = _compostaSiNo;
		valoreIndentazione=GestoreModello.getRientro();
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		setIdTipo();
	}
	
	public void aggiungiAlRamo(Entita daAggiungere, int qualeRamo) {}
	
	public boolean compostaSiNo() {
		return compostaSiNo;
	}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> elencoEntita = new Vector<Entita>();
		elencoEntita.add(this);
		return elencoEntita;
	}
	
	/**
	 * Fornisce il nome dell'entita'
	 *
	 * @return titolo : il nome dell'entita'
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/** 	
	 * Fornisce l'identificatore numerico dell'entita'
	 */
	public int getId() {
		return id;
	}
	 
	/**
	 * Fornisce il nome dell'azione.
	 */
	public String getNome() {
		return titolo;
	}
	
	public boolean giaInseritaSiNo(String nomeEntitaDaInserire) {
		return titolo.equalsIgnoreCase(nomeEntitaDaInserire);
	}

	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public boolean isComplessa() {
		return false;
	}

	public boolean isAzione() {
		return true;
	}

	public boolean isBranch() {
		return false;
	}

	public boolean isCiclo() {
		return false;
	}

	public boolean isFork() {
		return false;
	}
	
	public void rimuoviEntita(int idEntitaDaRimuovere) {}
	
	public void setIdTipo() {
		if(compostaSiNo)
			idTipo=ID_TIPO_AZIONE_COMPOSTA;
		else 
			idTipo=ID_TIPO_AZIONE;
	}
	
	public void setModelloComposta(String m) {
		titoloModelloComposta=m;
	}
	
	public String toString()
	{
		StringBuffer stringaDaStampare = new StringBuffer();
		if(compostaSiNo)
			stringaDaStampare.append(String.format(MSG_AZIONE_COMPOSTA,titolo,titoloModelloComposta));
		else
			stringaDaStampare.append(String.format(MSG_AZIONE,titolo));
		return stringaDaStampare.toString();
	}

	public String getIdTipo() {
		return idTipo;
	}

	public Vector <Entita> getAzioni() {
		return null;
	}
}
