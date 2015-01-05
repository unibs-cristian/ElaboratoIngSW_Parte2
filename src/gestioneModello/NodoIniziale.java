/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import java.util.Vector;

/**
 * Classe NodoIniziale.
 * Un'istanza di questa classe rappresenta il nodo inizile di un modello, ovvero la prima entita' di 
 * questo. 
 * Tale entita' e' inserita automaticamente quando viene creato il modello.
 */
public class NodoIniziale implements Entita{

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costante per stampa a video */
	public final static String MSG_TITOLO_NI = "NODO_INIZIALE";	
	
	/** id numerico */
	private int id;
	
	/** Tipo dell'entita' */
	private static String idTipo;
	
	/**
	 * Costruttore della classe NodoIniziale
	 */
	public NodoIniziale() {
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		idTipo = ID_TIPO_NODO_INIZIALE;
	}
	
	public void aggiungiAlRamo(Entita daAggiungere, int qualeRamo) {}
	
	public int getId() {
		return id;
	}
	
	public int getIndentazione() {
		return 0;
	}

	public String getNome() {
		return MSG_TITOLO_NI;
	}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> daRestituire = new Vector<Entita>();
		daRestituire.add(this);
		return daRestituire;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public boolean giaInseritaSiNo(String nomeEntitaDaInserire) {
		return false;
	}
	
	public boolean isComplessa() {
		return false;
	}

	public boolean isAzione() {
		return false;
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
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(MSG_TITOLO_NI);
		return risultato.toString();
	}
	
	public String getIdTipo() {
		return idTipo;
	}
	
	public Vector <Entita> getAzioni() {
		return null;
	}
}
