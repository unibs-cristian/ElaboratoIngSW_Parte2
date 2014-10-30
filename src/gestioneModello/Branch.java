package gestioneModello;
import java.util.Vector;

import utilita.*;
import java.io.Serializable;

public class Branch implements Entita, Serializable {
	
	private static final long serialVersionUID = 1L;
	public final static String MSG_BRANCH = "[ BRANCH N. %d : %s";
	public final static String MSG_ENTITA_RAMO_BRANCH = "ENTITA' RAMO N. %d";
	public final static String MSG_MERGE = "MERGE N. %d ]";
	
	private int id;
	private int idBranch;
	private String titolo;
	private int numeroRami;
	private Ramo[] elencoRami;
	private Vector <Entita> elencoEntita;
	private int valoreIndentazione;
	/** Identificatore del tipo */
	private String idTipo;
	private static int contatoreBranch = 1;
	
	public Branch (String _titolo, int _numeroRami) {
		id = GestoreModello.contatoreEntita;
		idBranch = contatoreBranch;
		titolo = _titolo;
		numeroRami = _numeroRami;
		elencoRami = new Ramo [numeroRami];
		idTipo = ID_TIPO_BRANCH;
		elencoEntita = new Vector<Entita>();
		valoreIndentazione = GUI.getRientro();
		GestoreModello.contatoreEntita++;
		contatoreBranch++;
	}
	
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

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return titolo;
	}
	
	public int getNumeroRami() {
		return numeroRami;
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
	 
	public boolean giaPresente(String nome) {
		elencoEntita = getEntita();
		for(int i=0; i<elencoEntita.size(); i++) {
			Entita e = elencoEntita.elementAt(i);
				if(e.giaPresente(nome))
					return true;
		}
		return false;	
	}
	
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}		
	
	public boolean ramiTuttiVuoti() {
		for(int i=0; i<elencoRami.length; i++)
			if (elencoRami[i].getEntitaRamo().isEmpty() == false)
				return false;
		return true;
	}
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
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
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.indenta(String.format(MSG_BRANCH, idBranch, titolo.toUpperCase()), SPAZIO, valoreIndentazione-GUI.FATTORE_INCREMENTO));
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_BRANCH, i+1), SPAZIO, valoreIndentazione));
			risultato.append(elencoRami[i].toString());
		}
		risultato.append(GUI.indenta(String.format(MSG_MERGE, idBranch), SPAZIO, valoreIndentazione-GUI.FATTORE_INCREMENTO));
		return risultato.toString();
	}
	
	public String getIdTipo() {
		return idTipo;
	}
}