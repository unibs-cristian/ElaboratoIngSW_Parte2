/*
 * 
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class NodoFinale.
 */
public class NodoFinale implements Entita
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_TITOLO_NF. */
	public final static String MSG_TITOLO_NF = "NODO_FINALE";
	
	/** The id. */
	private int id;
	
	/**  Identificatore del tipo. */
	private static String idTipo;
	
	/**
	 * Instantiates a new nodo finale.
	 */
	public NodoFinale() {
		id = GestoreModello.contatoreEntita;
		GestoreModello.contatoreEntita++;
		idTipo = ID_TIPO_NODO_FINALE;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#cercaPerNome(java.lang.String)
	 */
	public Entita cercaPerNome(String nomeDaCercare) {
		if(idTipo == nomeDaCercare)
			return this;
		else 
			return null;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getId()
	 */
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getNome()
	 */
	public String getNome() {
		return MSG_TITOLO_NF;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getEntita()
	 */
	public Vector<Entita> getEntita() {
		Vector <Entita> daRestituire = new Vector<Entita>();
		daRestituire.add(this);
		return daRestituire;
	}
	 
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#giaPresente(java.lang.String)
	 */
	public boolean giaPresente(String nome) {
		return nome==ID_TIPO_NODO_FINALE;	
	}	
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#addEntita(gestioneModello.Entita, int)
	 */
	public void addEntita(Entita e, int qualeRamo) {}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public boolean rimuoviEntitaAt(int id) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getRami()
	 */
	public Ramo[] getRami() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(MSG_TITOLO_NF);
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
