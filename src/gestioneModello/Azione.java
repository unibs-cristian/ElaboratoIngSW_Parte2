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
	private String modelloComposta;
	
	/** Dice se l'azione e' composta o semplice */
	private boolean composta;
	
	/**
	 * Costruttore per la classe Azione
	 *
	 * @param _titolo : il nome da assegnare all'azione
	 */
	public Azione(String _titolo, boolean _composta) {
		titolo = _titolo;
		composta = _composta;
		valoreIndentazione=GestoreModello.getRientro();
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		setIdTipo();
	}
	
	public void addEntita(Entita e, int qualeRamo) {}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> elencoEntita = new Vector<Entita>();
		elencoEntita.add(this);
		return elencoEntita;
	}
	
	public boolean isComposta() {
		return composta;
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
	
	public boolean giaPresente(String nome) {
		return titolo.equalsIgnoreCase(nome);
	}

	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public void rimuoviEntitaAt(int id) {}
	
	public void setIdTipo() {
		if(composta)
			idTipo=ID_TIPO_AZIONE_COMPOSTA;
		else 
			idTipo=ID_TIPO_AZIONE;
	}
	
	public void setModelloComposta(String m) {
		modelloComposta=m;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		if(composta)
			risultato.append(String.format(MSG_AZIONE_COMPOSTA,titolo,modelloComposta));
		else
			risultato.append(String.format(MSG_AZIONE,titolo));
		return risultato.toString();
	}

	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIdTipo()
	 */
	public String getIdTipo() {
		return idTipo;
	}
	
	/**
	 * Gets the azioni.
	 *
	 * @return the azioni
	 */
	public Vector <Entita> getAzioni() {
		return null;
	}
}