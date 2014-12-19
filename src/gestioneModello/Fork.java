/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.GUI;
import utilita.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class Fork.
 */
public class Fork implements Entita{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant MSG_FORK. */
	public final static String MSG_FORK = "{ INIZIO FORK %s (ID = %d)";
	
	/** The Constant MSG_ENTITA_RAMO_FORK. */
	public final static String MSG_ENTITA_RAMO_FORK = "%s - ENTITA' RAMO PARALLELO N. %d\n";
	
	/** The Constant MSG_JOIN. */
	public final static String MSG_JOIN = "FINE FORK %s (ID = %d) }\n";
	
	/** The id. */
	private int id;
	
	/** The titolo. */
	private String titolo;
	
	/** The num rami. */
	private int numRami;
	
	/** The elenco rami. */
	private Ramo[] elencoRami;
	
	/** The elenco entita. */
	private Vector<Entita> elencoEntita;
	
	/** The valore indentazione. */
	private int valoreIndentazione;
	
	/** The id tipo. */
	private String idTipo;
	
	/**
	 * Instantiates a new fork.
	 *
	 * @param _titolo the _titolo
	 * @param _numRami the _num rami
	 */
	public Fork(String _titolo, int _numRami) {
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		titolo = _titolo;
		numRami = _numRami;
		elencoRami = new Ramo[numRami];
		elencoEntita = new Vector<Entita>();
		idTipo = ID_TIPO_FORK;
		valoreIndentazione = GestoreModello.getRientro();
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
	 * @see gestioneModello.Entita#getIndentazione()
	 */
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#giaPresente(java.lang.String)
	 */
	public boolean giaPresente(String nome) {
		if(titolo.equalsIgnoreCase(nome))
			return true;
		boolean trovata = false;
		int i=0;
		while(trovata == false && i<numRami) {
			int j=0;
			while(trovata == false && j<getRami()[i].getNumeroEntita()) {
				Entita e = getRami()[i].getEntitaAt(j);
				if(e.getNome().equalsIgnoreCase(nome))
					return true;
				else {
					trovata = e.giaPresente(nome);
					j++;
				}
			}
			i++;
		}
		return trovata;	
	}
	 
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#rimuoviEntitaAt(int)
	 */
	public void rimuoviEntitaAt(int id) {
		//Per ogni ramo metto le entita' in un vector. Se una di quelle soddisfa la condizione, la tolgo dal ramo
		for (int i=0; i<numRami; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			//Ricerca l'entita' da eliminare tra le entita' interne del ramo i-esimo
			for(int j=0; j<entitaRamo.size(); j++) {
				Entita e = entitaRamo.elementAt(j);
				//Se la trova la elimina dalle entita' del ramo i-esimo di this e restituisce true
				if(e.getId()==id)
				{
					if(Util.yesOrNo(String.format(MSG_CONFERMA_CANCELLAZIONE,e.getNome()))) {
						elencoRami[i].eliminaEntitaRamo(j);
						Modello.getInstance().decrementaContatore();
						if(e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE) || e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE_COMPOSTA))
							Modello.getInstance().rimuoviAzione(e.getNome());
						System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
					}
				}
				else 
					e.rimuoviEntitaAt(id);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append("\n");
        risultato.append(GUI.indenta(String.format(MSG_FORK, titolo.toUpperCase(), id),SPAZIO,valoreIndentazione-GestoreModello.FATTORE_INCREMENTO));
		risultato.append("\n");
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_FORK, titolo.toUpperCase(),i+1), SPAZIO, valoreIndentazione));
			if(elencoRami[i].isEmpty())
				risultato.append(GUI.indenta(MSG_RAMO_VUOTO,SPAZIO,valoreIndentazione));
			else
				risultato.append(elencoRami[i].toString());
		}
		if(valoreIndentazione >= GestoreModello.FATTORE_INCREMENTO)
			risultato.append(GUI.indenta(String.format(MSG_JOIN, titolo.toUpperCase(),id),SPAZIO,valoreIndentazione - GestoreModello.FATTORE_INCREMENTO));
		else
			risultato.append(String.format(MSG_JOIN, titolo.toUpperCase(),id));
		return risultato.toString();
	}
	
	/* (non-Javadoc)
	 * @see gestioneModello.Entita#getIdTipo()
	 */
	public String getIdTipo() {
		return idTipo;
	}
}