/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.GUI;

// TODO: Auto-generated Javadoc
/**
 * The Class Ciclo.
 */
public class Ciclo implements Entita{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_CICLO. */
	public final static String MSG_CICLO = "< INIZIO CICLO %s (ID = %d) - MERGE\n";
	
	/** The Constant MSG_ATTIVITA_INIZIALI. */
	public final static String MSG_ATTIVITA_INIZIALI = "%s - ENTITA' RAMO N.1 (ATTIVITA' INIZIALI)";
	
	/** The Constant MSG_COND_PERMANENZA_CICLO. */
	public final static String MSG_COND_PERMANENZA_CICLO = "%s - ENTITA RAMO N.2 (CONDIZIONE DI PERMANENZA NEL CICLO)";
	
	/** The Constant MSG_BRANCH_CICLO. */
	public final static String MSG_BRANCH_CICLO = "FINE CICLO %s (ID = %d) - BRANCH >";
	
	/** The Constant NUM_RAMI_CICLO. */
	public final static int NUM_RAMI_CICLO = 2;
	
	/** The id. */
	private int id;
	
	/** The id ciclo. */
	private int idCiclo;
	
	/** The titolo. */
	private String titolo;
	
	/** The num rami. */
	private int numRami;
	
	/** The elenco rami. */
	private Ramo [] elencoRami;
	
	/** The contatore cicli. */
	private static int contatoreCicli = 1;
	
	/** The elenco entita. */
	private Vector<Entita> elencoEntita;
	
	/** The valore indentazione. */
	private int valoreIndentazione;
	
	/** The id tipo. */
	private String idTipo;
	
	/**
	 * Instantiates a new ciclo.
	 *
	 * @param _titolo the _titolo
	 */
	public Ciclo(String _titolo)
	{
		id = GestoreModello.contatoreEntita;
		idCiclo = contatoreCicli;
		titolo = _titolo;
		numRami = NUM_RAMI_CICLO;
		elencoRami = new Ramo [NUM_RAMI_CICLO]; 
		elencoEntita = new Vector<Entita>();
		idTipo = ID_TIPO_CICLO;
		valoreIndentazione = GestoreModello.getRientro();
		contatoreCicli++;
		GestoreModello.contatoreEntita++;
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
	 * @see gestioneModello.Entita#getId()
	 */
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return valoreIndentazione;
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
		return numRami;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getRami()
	 */
	public Ramo[] getRami() {
		return elencoRami;
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
  
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public boolean rimuoviEntitaAt(int id) {
		Entita daEliminare = null;
		//Per ogni ramo metto le entita' in un vector. Se una di quelle soddisfa la condizione, la tolgo dal ramo
		for (int i=0; i<elencoRami.length; i++) {
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
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(MSG_CICLO, titolo.toUpperCase(), idCiclo));
		if(elencoRami[0].getEntitaRamo().isEmpty())
		{
			risultato.append(GUI.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		}
		else
		{
			risultato.append(GUI.indenta(String.format(MSG_ATTIVITA_INIZIALI,titolo), SPAZIO, valoreIndentazione));
			for(int j=0; j<elencoRami[0].getEntitaRamo().size(); j++) {
				risultato.append(elencoRami[0].toString());
			}
		}
		risultato.append(GUI.indenta(String.format(MSG_COND_PERMANENZA_CICLO,titolo), SPAZIO, valoreIndentazione));
		if(elencoRami[1].getEntitaRamo().isEmpty())
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione));
		else
			risultato.append(elencoRami[1].toString());
		risultato.append(String.format(MSG_BRANCH_CICLO, titolo.toUpperCase(), idCiclo));
		return risultato.toString();
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIdTipo()
	 */
	public String getIdTipo() {
		return idTipo;
	}
}