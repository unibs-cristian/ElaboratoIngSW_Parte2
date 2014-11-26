/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;
import java.util.Vector;

// TODO: Auto-generated Javadoc
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
	public final static String ID_TIPO = "Azione %s";
	
	/** L'id all'interno del Modello a cui appartiene l'azione. */
	private int id;
	
	/** Il titolo dell'azione (univoco all'interno del modello) */
	private String titolo;
	
	/** L'indentazione usata per stampare il modello in modo piu' intuitivo possibile. */
	private int valoreIndentazione;
	
	/** Stringa per tenere traccia del tipo di entita' */
	private String idTipo;
	
	//TODO Azioni composte
	
	/**
	 * Costruttore per la classe Azione
	 *
	 * @param _titolo : il nome da assegnare all'azione
	 */
	public Azione(String _titolo) {
		titolo = _titolo;
		valoreIndentazione=GestoreModello.getRientro();
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		idTipo = ID_TIPO_AZIONE;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#addEntita(gestioneModello.Entita, int)
	 */
	public void addEntita(Entita e, int qualeRamo) {}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getEntita()
	 */
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
	
	/* 	
	 * Fornisce l'
	 */
	public int getId() {
		return id;
	}
	 
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getNome()
	 */
	public String getNome() {
		return titolo;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#giaPresente(java.lang.String)
	 */
	public boolean giaPresente(String nome) {
		return titolo.equalsIgnoreCase(nome);
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getRami()
	 */
	public Ramo[] getRami() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public void rimuoviEntitaAt(int id) {}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(ID_TIPO,titolo));
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