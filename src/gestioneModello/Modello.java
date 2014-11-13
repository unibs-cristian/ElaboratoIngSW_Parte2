/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import inputDati.GestoreModello;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Modello.
 */
public class Modello implements Entita{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_NOME_MODELLO. */
	public final static String MSG_NOME_MODELLO = "NOME MODELLO : %s\n";
	
	/** The Constant MSG_DESCRIZIONE_MODELLO. */
	public final static String MSG_DESCRIZIONE_MODELLO = "DESCRIZIONE MODELLO : %s\n\n";
	
	/** The Constant MSG_ERRORE_MODIFICA. */
	public final static String MSG_ERRORE_MODIFICA = "Errore. Non e' presente alcuna entita' da eliminare.";
	
	/** The nome. */
	private String nome;
	
	/** The descrizione. */
	private String descrizione;
	
	/** The gm. */
	private GestoreModello gm;
	
	/** The elenco entita. */
	private Vector <Entita> elencoEntita;
	
	/** The elenco azioni. */
	private Vector <Azione> elencoAzioni;
	
	/** The contatore modello. */
	private static int contatoreModello = 1;
	
	/** The id modello. */
	private int idModello;
	
	/** The id tipo. */
	private String idTipo;
	
	/** The instance. */
	private static Modello instance = null;
	
	/**
	 * Instantiates a new modello.
	 */
	private Modello() {
		elencoEntita = new Vector<Entita>();
		elencoAzioni = new Vector<Azione>();
		gm = new GestoreModello(this);
		idTipo = ID_TIPO_MODELLO;
		idModello = contatoreModello;
		contatoreModello++;
	}
	
	/**
	 * Instantiates a new modello.
	 *
	 * @param _nome the _nome
	 * @param _descrizione the _descrizione
	 */
	private Modello (String _nome, String _descrizione) {
		nome = _nome;
		descrizione = _descrizione;
		elencoEntita = new Vector<Entita>();
		gm = new GestoreModello(this);
		NodoIniziale ni = new NodoIniziale();
		elencoEntita.add(ni);
		idModello = contatoreModello;
		contatoreModello++;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getId()
	 */
	public int getId() {
		return idModello;
	}
	
	/**
	 * Checks if is null.
	 *
	 * @return true, if is null
	 */
	public static boolean isNull() {
		if(instance == null)
			return true;
		return false;
	}
	
	/**
	 * Gets the single instance of Modello.
	 *
	 * @return single instance of Modello
	 */
	public static Modello getInstance() {
		if(instance == null)
			instance = new Modello();
		return instance;
	}
	
	/**
	 * Cambia modello.
	 *
	 * @param nuovo the nuovo
	 */
	public static void cambiaModello(Modello nuovo) {
		instance = nuovo;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getEntita()
	 */
	public Vector <Entita> getEntita() {
		return elencoEntita;
	}
	
	/**
	 * Gets the azione at.
	 *
	 * @param index the index
	 * @return the azione at
	 */
	public Azione getAzioneAt(int index) {
		return elencoAzioni.elementAt(index);
	}
	
	/**
	 * Gets the numero azioni.
	 *
	 * @return the numero azioni
	 */
	public int getNumeroAzioni() {
		return elencoAzioni.size();
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getNome()
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Gets the gm.
	 *
	 * @return the gm
	 */
	public GestoreModello getGm() {
		return gm;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#addEntita(gestioneModello.Entita, int)
	 */
	public void addEntita (Entita e, int qualeRamo) {
		elencoEntita.add(e);
	}
	
	/**
	 * Adds the entita.
	 *
	 * @param e the e
	 */
	public void addEntita (Entita e) {
		elencoEntita.add(e);
	}
	
	/**
	 * Adds the azione.
	 *
	 * @param a the a
	 */
	public void addAzione(Azione a) {
		elencoAzioni.add(a);
	}
	
	/**
	 * Elimina ultima entita.
	 */
	public void eliminaUltimaEntita() {
		if(elencoEntita.size()<=1)     
			System.out.println(MSG_ERRORE_MODIFICA);
		else
		{
			int i = GestoreModello.contatoreEntita-1;
			boolean finito = false;
			while(finito == false)
			{
				if(rimuoviEntitaAt(i) == false)
					i--;
				else
					finito = true;
			}
		}
	}
	
	/**
	 * Gets the elenco azioni.
	 *
	 * @return the elenco azioni
	 */
	public Vector<Azione> getElencoAzioni() {
		return elencoAzioni;
	}
	
	/**
	 * Nodo finale presente.
	 *
	 * @return true, if successful
	 */
	public boolean nodoFinalePresente() {
		if(giaPresente(ID_TIPO_NODO_FINALE))
			return true;
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#cercaPerNome(java.lang.String)
	 */
	//TODO Forse non e' mai utilizzato. Verificare
	public Entita cercaPerNome(String nomeDaCercare) {
		for (int i = 0; i < elencoEntita.size(); i++) 
		{
			Entita e = elencoEntita.get(i);
			// Chiama il metodo di ricerca per nome di ciascuna entita' inserita nel modello.
			// Se la trova la restituisce
			e = e.cercaPerNome(nomeDaCercare);
			if(e!=null)   
				return e;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#giaPresente(java.lang.String)
	 */
	public boolean giaPresente(String nome) {
		/* 
		 * Controlla anche se il nome del modello o dell'entita' da inserire in esso  e' gia' 
		 * presente, per impedire l'inserimento di entita' aventi lo stesso nome.
		 */
		Boolean trovato = false;
		if(this.nome.equalsIgnoreCase(nome))
			return true;
		else
			for(int i=0; i<elencoEntita.size(); i++) {
				if(elencoEntita.elementAt(i).getNome().equalsIgnoreCase(nome)) 
					return true;
				else 
					trovato = elencoEntita.elementAt(i).giaPresente(nome);
			}
		return trovato;
	}
	
	/**
	 * Checks if is equal.
	 *
	 * @param altro the altro
	 * @return true, if is equal
	 */
	public boolean isEqual(Modello altro) {
		if((nome.equals(altro.getNome()) == false) || getNumeroAzioni() != altro.getNumeroAzioni())
			return false;
		else 
			for(int i=0; i<elencoEntita.size(); i++)
				if(getEntita().elementAt(i).getNome().equals(altro.getEntita().elementAt(i).getNome()) == false)
					return false;
		return true;
	}
	
	/**
	 * Nessuna azione.
	 *
	 * @return true, if successful
	 */
	public boolean nessunaAzione() {
		return elencoAzioni.isEmpty();
	}
	
	// Rimuove l'entita' con tale id, se la trova
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public boolean rimuoviEntitaAt(int id) {
		Entita e = null;
		for (int i = 0; i < elencoEntita.size(); i++) 
		{
			e = elencoEntita.get(i);
			if(e.getId()==id)
			{
				elencoEntita.remove(i);
				if(e.getIdTipo()==ID_TIPO_AZIONE)
					rimuoviAzione(e.getNome());
				System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
				return true; 
			}
			else
				if(e.rimuoviEntitaAt(id))
					return true;
		}
		return false;
	}
	
	/**
	 * Rimuovi azione.
	 *
	 * @param nomeAzione the nome azione
	 */
	public void rimuoviAzione(String nomeAzione) {
		for(int i=0; i<elencoAzioni.size(); i++)
			if(elencoAzioni.elementAt(i).getNome().equalsIgnoreCase(nomeAzione))
				elencoAzioni.remove(i);
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param unNome the new nome
	 */
	public void setNome(String unNome) {
		nome = unNome;
	}
	
	/**
	 * Sets the descrizione.
	 *
	 * @param unaDescrizione the new descrizione
	 */
	public void setDescrizione(String unaDescrizione) {
		descrizione = unaDescrizione;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(MSG_NOME_MODELLO, nome));
		risultato.append(String.format(MSG_DESCRIZIONE_MODELLO, descrizione));
		
		for(int i=0; i<elencoEntita.size(); i++) {
			Entita e = elencoEntita.get(i);
			risultato.append(e.toString());
			risultato.append("\n");
		}  
		return risultato.toString();
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getRami()
	 */
	public Ramo[] getRami() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIdTipo()
	 */
	public String getIdTipo() {
		return idTipo;
	}
}