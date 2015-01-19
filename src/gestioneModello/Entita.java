/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import java.util.*;
import java.io.Serializable;

/**
 * L'Interface Entita.
 * Standardizza l'accesso ad ogni tipo di Entita', semplici o composte.
 * 
 * @author Sampietri Cristian
 */
public interface Entita extends Serializable {
	
	/** Costanti stringa per la stampa a video */
	public final static String SPAZIO = " ";
	public final static String MSG_CONFERMA_CANCELLAZIONE = "Si desidera procedere con l'eliminazione dell'entita' %s?";
	public final static String MSG_ENTITA_RIMOSSA = "E' stata rimossa l'entita' %s (id = %d)";
	public final static String MSG_RAMO_VUOTO = "Nessuna\n";
	
	/** Costanti stringa contenenti gli identificatori dei vari tipi di entita' */
	public final static String ID_TIPO_AZIONE = "AZ";
	public final static String ID_TIPO_AZIONE_COMPOSTA = "AZC";
	public final static String ID_TIPO_BRANCH = "BR";
	public final static String ID_TIPO_CICLO = "CI";
	public final static String ID_TIPO_FORK = "FO";
	public final static String ID_TIPO_MODELLO = "MO";
	public final static String ID_TIPO_NODO_FINALE = "NF";
	public final static String ID_TIPO_NODO_INIZIALE = "NI";
	
	/**
	 * Aggiunge l'entita' al ramo speficato dell'entita' complessa
	 *
	 * @param daAggiungere : l'entita' da aggiungere
	 * @param qualeRamo : il ramo a cui aggiungere l'entita' 
	 */
	public void aggiungiAlRamo(Entita daAggiungere, int qualeRamo);
	
	/**
	 * Ritorna l'id numerico dell'entita'.
	 *
	 * @return l'id
	 */
	public int getId();
	
	/**
	 * Ritorna le varie sotto entita' di un'entita' complessa
	 *
	 * @return Vector di entita'
	 */
	public Vector <Entita> getEntita();
	
	/**
	 * Ritorna la stringa che codifica il tipo dell'entita'
	 *
	 * @return id tipo
	 */
	public String getIdTipo();
	
	/**
	 * Ritorna il nome dell'entita'
	 *
	 * @return il nome
	 */
	public String getNome();
	 
	/**
	 * Ritorna il numero di rami.
	 *
	 * @return array contenente i vari rami
	 */
	public Ramo[] getRami();
	
	/**
	 * Ritorna l'indentazione per la corretta stampa a video della struttura del modello.
	 *
	 * @return il valore numerico dell'indentazione
	 */
	public int getIndentazione();
	
	/**
	 * Metodo che serve a garantire l'invariante di classe di Modello e delle altre entita' complesse.
	 *
	 * @param nomeEntitaDaInserire : il nome di cui verificare l'eventuale presenza nel modello o nell'entita' complessa.
	 * @return true se e' gia' presente nel modello o nell'entita' complessa un'altra entita' con lo 
	 * stesso nome.
	 */
	public boolean giaInseritaSiNo(String nomeEntitaDaInserire);
	
	/**
	 * Dice se l'entita' e e' complessa
	 * 
	 * @return true se complessa
	 */
	public boolean isComplessa();
	
	/**
	 * Dice se l'entita' e e' un'azione
	 * 
	 * @return true se azione (semplice o composta)
	 */
	public boolean isAzione();
	
	/**
	 * Dice se l'entita' e e' un branch
	 * 
	 * @return true se branch
	 */
	public boolean isBranch();
	
	/**
	 * Dice se l'entita' e e' un ciclo
	 * 
	 * @return true se ciclo
	 */
	public boolean isCiclo();
	
	/**
	 * Dice se l'entita' e e' un fork
	 * 
	 * @return true se fork
	 */
	public boolean isFork();
	
	/**
	 * Rimuove l'entita' avente l'identificatore numerico passato come parametro.
	 *
	 * @param idEntitaDaRimuovere : l'id dell'entita' da eliminare
	 */
	public void rimuoviEntita(int idEntitaDaRimuovere);
	
	/**
	 * Metodo to string.
	 *
	 * @return stringa per la stampa a video di una sintesi dell'entita'.
	 */
	public String toString();
}
