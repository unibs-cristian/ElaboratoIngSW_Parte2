package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;

import utilita.GUI;

public class Ciclo implements Entita{

	private static final long serialVersionUID = 1L;
	public final static String MSG_CICLO = "< INIZIO CICLO %s (ID = %d) - MERGE\n";
	public final static String MSG_ATTIVITA_INIZIALI = "%s - ENTITA' RAMO N.1 (ATTIVITA' INIZIALI)";
	public final static String MSG_COND_PERMANENZA_CICLO = "%s - ENTITA RAMO N.2 (CONDIZIONE DI PERMANENZA NEL CICLO)";
	public final static String MSG_BRANCH_CICLO = "FINE CICLO %s (ID = %d) - BRANCH >";
	
	public final static int NUM_RAMI_CICLO = 2;
	
	private int id;
	private int idCiclo;
	private String titolo;
	private int numRami;
	private Ramo [] elencoRami;
	private static int contatoreCicli = 1;
	private Vector<Entita> elencoEntita;
	private int valoreIndentazione;
	/** Identificatore del tipo */
	private String idTipo;
	
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
	
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
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
	
	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			for(int j=0; j<entitaRamo.size(); j++) {
				addEntita(entitaRamo.elementAt(j));
			}
		}
		return elencoEntita;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public String getNome() {
		return titolo;
	}
	
	public int getNumeroRami() {
		return numRami;
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}
	
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
	
	public String getIdTipo() {
		return idTipo;
	}
}