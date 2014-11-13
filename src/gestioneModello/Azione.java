/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Azione.
 */
public class Azione implements Entita{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ID_TIPO. */
	public final static String ID_TIPO = "Azione n. %d : %s";
	
	/** The id. */
	private int id;
	
	/** The id azione. */
	private int idAzione;
	
	/** The titolo. */
	private String titolo;
	
	/** The valore indentazione. */
	private int valoreIndentazione;
	
	/** The contatore azioni. */
	private static int contatoreAzioni = 1;
	
	/** The id tipo. */
	private String idTipo;
	
	//TODO Azioni composte
	
	/**
	 * Instantiates a new azione.
	 *
	 * @param _titolo the _titolo
	 */
	public Azione(String _titolo) {
		id = GestoreModello.contatoreEntita;
		idAzione = contatoreAzioni;
		titolo = _titolo;
		contatoreAzioni++;
		valoreIndentazione=GestoreModello.getRientro();
		GestoreModello.contatoreEntita++;
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
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#cercaPerNome(java.lang.String)
	 */
	public Entita cercaPerNome(String nomeDaCercare) {
		if(titolo.equalsIgnoreCase(nomeDaCercare))
			return this;
		else 
			return null;
	}
	
	/**
	 * Decrementa contatore.
	 */
	public void decrementaContatore() {
		contatoreAzioni--;
	}
	
	/**
	 * Gets the titolo.
	 *
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getId()
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
	public boolean rimuoviEntitaAt(int id) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(ID_TIPO, idAzione, titolo));
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