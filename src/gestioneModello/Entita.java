/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import java.util.*;
import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Interface Entita.
 */
public interface Entita extends Serializable {
	
	/** The Constant SPAZIO. */
	public final static String SPAZIO = " ";
	
	/** The Constant MSG_CONFERMA_CANCELLAZIONE */
	public final static String MSG_CONFERMA_CANCELLAZIONE = "Si desidera procedere con l'eliminazione dell'entita' %s?";
	
	/** The Constant MSG_ENTITA_RIMOSSA. */
	public final static String MSG_ENTITA_RIMOSSA = "E' stata rimossa l'entita' %s (id = %d)";
	
	/** The Constant MSG_RAMO_VUOTO. */
	public final static String MSG_RAMO_VUOTO = "Nessuna\n";
	
	/** The Constant ID_TIPO_AZIONE. */
	public final static String ID_TIPO_AZIONE = "AZ";
	
	/** The Constant ID_TIPO_BRANCH. */
	public final static String ID_TIPO_BRANCH = "BR";
	
	/** The Constant ID_TIPO_CICLO. */
	public final static String ID_TIPO_CICLO = "CI";
	
	/** The Constant ID_TIPO_FORK. */
	public final static String ID_TIPO_FORK = "FO";
	
	/** The Constant ID_TIPO_MODELLO. */
	public final static String ID_TIPO_MODELLO = "MO";
	
	/** The Constant ID_TIPO_NODO_FINALE. */
	public final static String ID_TIPO_NODO_FINALE = "NF";
	
	/** The Constant ID_TIPO_NODO_INIZIALE. */
	public final static String ID_TIPO_NODO_INIZIALE = "NI";
	
	/**
	 * Adds the entita.
	 *
	 * @param e the e
	 * @param qualeRamo the quale ramo
	 */
	public void addEntita(Entita e, int qualeRamo);
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId();
	
	/**
	 * Gets the entita.
	 *
	 * @return the entita
	 */
	public Vector <Entita> getEntita();
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome();
	 
	/**
	 * Gets the rami.
	 *
	 * @return the rami
	 */
	public Ramo[] getRami();
	
	/**
	 * Gets the indentazione.
	 *
	 * @return the indentazione
	 */
	public int getIndentazione();
	
	/**
	 * Gia presente.
	 *
	 * @param nome the nome
	 * @return true, if successful
	 */
	public boolean giaPresente(String nome);
	
	/**
	 * Rimuovi entita at.
	 *
	 * @param id the id
	 */
	public void rimuoviEntitaAt(int id);
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();
	
	/**
	 * Gets the id tipo.
	 *
	 * @return the id tipo
	 */
	public String getIdTipo();
}

