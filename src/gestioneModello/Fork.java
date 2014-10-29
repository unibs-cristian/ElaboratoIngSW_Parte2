package gestioneModello;

import java.util.Vector;

import utilita.GUI;

public class Fork implements Entita {
	
	public final static String MSG_FORK = "{ FORK N. %d : %s";
	public final static String MSG_ENTITA_RAMO_FORK = "ENTITA' RAMO PARALLELO N. %d";
	public final static String MSG_JOIN = "JOIN N. %d }\n";
	
	private int id;
	private int idFork;
	private String titolo;
	private int numRami;
	private Ramo[] elencoRami;
	private Vector<Entita> elencoEntita;
	private int valoreIndentazione;
	private static int contatoreFork = 1;
	/** Identificatore del tipo */
	private String idTipo;
	
	public Fork(String _titolo, int _numRami) {
		id = GestoreModello.contatoreEntita;
		idFork = contatoreFork;
		titolo = _titolo;
		numRami = _numRami;
		elencoRami = new Ramo[numRami];
		elencoEntita = new Vector<Entita>();
		idTipo = ID_TIPO_FORK;
		valoreIndentazione = GUI.getRientro();
		contatoreFork++;
		GestoreModello.contatoreEntita++;
	}
	
	public Entita cercaId(int idDaCercare) {
		Entita trovato = null;
		if(id == idDaCercare)
			trovato = this;
		else
		{
			Vector <Entita> listaEntita = getEntita();
			for(int i=0; i<listaEntita.size(); i++) {
				Entita e = listaEntita.elementAt(i);
				trovato = e.cercaId(idDaCercare);
			}
		}
		return trovato;
	}
	
	public Vector<Entita> getAzioni() {
		Vector <Entita> res = new Vector<Entita>();
		elencoEntita = getEntita();
		for(int i=0; i<elencoEntita.size(); i++) {
			if(elencoEntita.elementAt(i).getIdTipo()==ID_TIPO_AZIONE)
				if(!giaPresente(elencoEntita.elementAt(i).getNome()))
					res.add(elencoEntita.elementAt(i));
			else {
				Vector <Entita> azioniEntita = new Vector<Entita>();
				azioniEntita = elencoEntita.elementAt(i).getAzioni();
				if(azioniEntita!=null)
					for(int j=0; j<azioniEntita.size(); j++)
						if(!giaPresente(azioniEntita.elementAt(j).getNome()))
							res.add(azioniEntita.elementAt(j));
			}
		}
		return res;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return titolo;
	}

	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			for(int j=0; j<entitaRamo.size(); j++) {
				addEntita(entitaRamo.elementAt(j));
			}
		}
		return elencoEntita;
	}
	
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
	}
	
	public int getNumeroRami() {
		return numRami;
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}

	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public boolean giaPresente(String nome) {
		elencoEntita = getEntita();
		for(int i=0; i<elencoEntita.size(); i++) {
			Entita e = elencoEntita.elementAt(i);
				if(e.giaPresente(nome))
					return true;
		}
		return false;	
	}
	 
	public boolean ramiTuttiVuoti() {
		for(int i=0; i<elencoRami.length; i++)
			if (elencoRami[i].getEntitaRamo().isEmpty() == false)
				return false;
		return true;
	}
	
	public boolean rimuoviEntitaAt(int id) {
		Entita daEliminare = null;
		//Per ogni ramo metto le entita' in un vector. Se una di quelle soddisfa la condizione, la tolgo dal ramo
		for (int i=0; i<numRami; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			//Ricerca l'entita' da eliminare tra le entita' interne del ramo i-esimo
			for(int j=0; j<entitaRamo.size(); j++) {
				Entita e = entitaRamo.elementAt(j);
				//Se la trova la elimina dalle entita' del ramo i-esimo di this e restituisce true
				if(e.getId()==id)
				{
					daEliminare = e;
					elencoRami[i].eliminaEntitaRamo(j);
					System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
					return true;
				}
			}
		}
		/*
		 * Se non ha trovato l'entita' da eliminare tra i componenti dei vari rami di this, la cerca nei 
		 * componenti dei componenti e così via, in maniera ricorsiva 
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
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.indenta(String.format(MSG_FORK, idFork, titolo.toUpperCase()),SPAZIO,valoreIndentazione-GUI.FATTORE_INCREMENTO));
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_FORK, i+1), SPAZIO, valoreIndentazione));
			risultato.append(elencoRami[i].toString());
		}
		risultato.append(GUI.indenta(String.format(MSG_JOIN, idFork), SPAZIO, valoreIndentazione - GUI.FATTORE_INCREMENTO));
		return risultato.toString();
	}
	
	public String getIdTipo() {
		return idTipo;
	}
}
