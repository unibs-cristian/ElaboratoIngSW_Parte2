/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;
import inputDati.GestoreModello;

import java.util.Vector;

import utilita.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Branch.
 */
public class Branch implements Entita{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_BRANCH. */
	public final static String MSG_BRANCH = "[ INIZIO BRANCH %s (ID = %d)\n";
	
	/** The Constant MSG_ENTITA_RAMO_BRANCH. */
	public final static String MSG_ENTITA_RAMO_BRANCH = "%s - ENTITA' RAMO N. %d";
	
	/** The Constant MSG_MERGE. */
	public final static String MSG_MERGE = "FINE BRANCH %s (ID = %d) ]";
	
	/** The id. */
	private int id;
	
	/** The id branch. */
	private int idBranch;
	
	/** The titolo. */
	private String titolo;
	
	/** The numero rami. */
	private int numeroRami;
	
	/** The elenco rami. */
	private Ramo[] elencoRami;
	
	/** The elenco entita. */
	private Vector <Entita> elencoEntita;
	
	/** The valore indentazione. */
	private int valoreIndentazione;
	
	/** The id tipo. */
	private String idTipo;
	
	/** The contatore branch. */
	private static int contatoreBranch = 1;
	
	/**
	 * Instantiates a new branch.
	 *
	 * @param _titolo the _titolo
	 * @param _numeroRami the _numero rami
	 */
	public Branch (String _titolo, int _numeroRami) {
		id = GestoreModello.contatoreEntita;
		idBranch = contatoreBranch;
		titolo = _titolo;
		numeroRami = _numeroRami;
		elencoRami = new Ramo [numeroRami];
		idTipo = ID_TIPO_BRANCH;
		elencoEntita = new Vector<Entita>();
		valoreIndentazione = GestoreModello.getRientro();
		GestoreModello.contatoreEntita++;
		contatoreBranch++;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#cercaPerNome(java.lang.String)
	 */
	public Entita cercaPerNome(String nomeDaCercare) {
		if(titolo.equalsIgnoreCase(nomeDaCercare))
			return this;
		else
		{
			Vector <Entita> listaEntita = getEntita();
			for(int i=0; i<listaEntita.size(); i++) {
				Entita e = listaEntita.elementAt(i);
				e = e.cercaPerNome(nomeDaCercare);
				if(e!=null)
					return e;
			}
		}
		return null;
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
	
	/**
	 * Gets the numero rami.
	 *
	 * @return the numero rami
	 */
	public int getNumeroRami() {
		return numeroRami;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getEntita()
	 */
	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			for(int j=0; j<entitaRamo.size(); j++) {
				addEntita(entitaRamo.elementAt(j));
			}
		}
		return elencoEntita;
	}
	 
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#giaPresente(java.lang.String)
	 */
	public boolean giaPresente(String nome) {
		if(titolo.equalsIgnoreCase(nome))
			return true;
		elencoEntita = getEntita();
		for(int i=0; i<elencoEntita.size(); i++) {
			Entita e = elencoEntita.elementAt(i);
				if(e.giaPresente(nome))
					return true;
		}
		return false;	
	}
	
	/**
	 * Adds the entita.
	 *
	 * @param e the e
	 */
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}		
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#addEntita(gestioneModello.Entita, int)
	 */
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getRami()
	 */
	public Ramo[] getRami() {
		return elencoRami;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public boolean rimuoviEntitaAt(int id) {
		Entita daEliminare = null;
		//Per ogni ramo metto le entita' in un vector. Se una di quelle soddisfa la condizione, la tolgo dal ramo
		for (int i=0; i<numeroRami; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			//Ricerca l'entita' da eliminare tra le entita' interne del ramo i-esimo
			for(int j=0; j<entitaRamo.size(); j++) {
				Entita e = entitaRamo.elementAt(j);
				//Se la trova la elimina dalle entita' del ramo i-esimo di this e restituisce true
				if(e.getId()==id)
				{
					daEliminare = e;
					elencoRami[i].eliminaEntitaRamo(j);
					if(e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE))
						Modello.getInstance().rimuoviAzione(e.getNome());
					System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
					return true;
				}
			}
		}
		/*
		 * Se non ha trovato l'entita' da eliminare tra i componenti dei vari rami di this, la cerca nei 
		 * componenti dei componenti e cosi' via, in maniera ricorsiva 
		 */
		if(daEliminare == null)
			for(int i=0; i<elencoEntita.size(); i++) {
				Entita e = elencoEntita.elementAt(i);
				/*
				 * Quando il metodo applicato ad una delle attivita' componenti restituisce true, il metodo 
				 * restituisce true.
				 */
				if(e.rimuoviEntitaAt(id)==true)
					return true;   
			}
		/*
		 * Viene restituito false se il metodo applicato a ciascuna entita' componente non ha mai restituito 
		 * un valore true.
		 */
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(MSG_BRANCH, titolo.toUpperCase(), idBranch));
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_BRANCH, titolo.toUpperCase(),i+1), SPAZIO, valoreIndentazione));
			if(elencoRami[i].isEmpty())
				risultato.append(GUI.indenta(MSG_RAMO_VUOTO,SPAZIO,valoreIndentazione));
			else
				risultato.append(elencoRami[i].toString());
		}
		risultato.append(String.format(MSG_MERGE, titolo.toUpperCase(),idBranch));
		return risultato.toString();
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIdTipo()
	 */
	public String getIdTipo() {
		return idTipo;
	}
}