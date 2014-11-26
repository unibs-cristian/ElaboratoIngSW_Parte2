/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package controlloCammino;

import gestioneModello.Ramo;

import java.io.Serializable;

import testSuiteDiagnosi.CamminoAzioni;

// TODO: Auto-generated Javadoc
/**
 * The Interface StatoCammino.
 */
public interface StatoCammino extends Serializable{
	
	/** The Constant ENTRATO_RAMO. */
	public final static String ENTRATO_RAMO = "ENTRATO RAMO";
	
	/** The Constant FERMATO. */
	public final static String FERMATO = "FERMATO";
	
	/** The Constant FERMATO_DENTRO. */
	public final static String FERMATO_DENTRO = "FERMATO DENTRO";
	
	/** The Constant NON_PERCORSO. */
	public final static String NON_PERCORSO = "RAMO NON PERCORSO";
	
	/** The Constant PERCORSO_PARZ. */
	public final static String PERCORSO_PARZ = "RAMO PERCORSO PARZIALMENTE";
	
	/** The Constant PERCORSO_TUTTO. */
	public final static String PERCORSO_TUTTO = "RAMO PERCORSO TUTTO";
	
	/** The Constant STATO_OK. */
	public final static String STATO_OK = "OK";
	
	/** The Constant STATO_NON_OK. */
	public final static String STATO_NON_OK = "NON VALIDO";
	
	/** The Constant STATO_VUOTO. */
	public final static String STATO_VUOTO = "VUOTO";
	
	/** The Constant SALTATO_BLOCCO. */
	public final static String SALTATO_BLOCCO = "SALTATO BLOCCO";
	
	/**
	 * Gestisci stato.
	 *
	 * @param camm the camm
	 * @param stato the stato
	 */
	public void gestisciStato(CamminoAzioni camm, String stato);
	
	/**
	 * Gestisci stato ramo.
	 *
	 * @param r the ramo
	 * @param stato the stato
	 */
	public void gestisciStatoRamo(Ramo r, String stato);
	
	/**
	 * Gets the stringa stato.
	 *
	 * @return the stringa stato
	 */
	public String getStringaStato();
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid();

}
