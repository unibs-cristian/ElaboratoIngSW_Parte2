package gestioneModello;
import java.util.*;

public class Modello implements Entita {
	
	public final static String MSG_NOME_MODELLO = "NOME MODELLO : %s\n";
	public final static String MSG_DESCRIZIONE_MODELLO = "DESCRIZIONE MODELLO : %s\n\n";
	public final static String MSG_ERRORE_MODIFICA = "Errore. Non è presente alcuna entita' da eliminare.";
	
	private String nome;
	private String descrizione;
	private GestoreModello gm;
	private Vector <Entita> elencoEntita;
	private static int contatoreModello = 1;
	private int idModello;
	private String idTipo;
	
	/** L'istanza (unica) di modello su cui il sistema lavora e' inizialmente null. */
	private static Modello instance = null;
	
	/** Costruttore del modello. Poiche' si usa il Pattern Singleton, il costruttore e' private. */
	private Modello() {
		elencoEntita = new Vector<Entita>();
		gm = new GestoreModello(this);
		idTipo = ID_TIPO_MODELLO;
		idModello = contatoreModello;
		contatoreModello++;
	}
	
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
	
	public int getId() {
		return idModello;
	}
	
	public static boolean isNull() {
		if(instance == null)
			return true;
		return false;
	}
	
	/** 
	 * Rappresenta il punto di accesso univoco secondo il pattern Singleton. L'istanza (unica) di modello
	 * puo' essere ottenuta all'esterno tramite questo metodo.
     */
	public static Modello getInstance() {
		if(instance == null)
			instance = new Modello();
		return instance;
	}
	
	/** Consente di cambiare il modello (unico) su cui si lavora assegnando il nuovo modello all'attributo instance */
	public static void cambiaModello(Modello nuovo) {
		instance = nuovo;
	}
	
	public Vector <Entita> getEntita() {
		return elencoEntita;
	}
	
	public String getNome() {
		return nome;
	}
	
	public GestoreModello getGm() {
		return gm;
	}
	
	public void addEntita (Entita e, int qualeRamo) {
		elencoEntita.add(e);
	}
	
	public void addEntita (Entita e) {
		elencoEntita.add(e);
	}
	
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
	
	public boolean nodoFinalePresente() {
		if(giaPresente(ID_TIPO_NODO_FINALE))
			return true;
		else
			return false;
	}
	
	public Entita cercaId(int idDaCercare) {
		Entita e = null;
		for (int i = 0; i < elencoEntita.size(); i++) 
		{
			e = elencoEntita.get(i);
			// Chiama il metodo di ricerca per id di ciascuna entita' inserita nel modello.
			e = e.cercaId(idDaCercare);
		}
		/*
		 * Se e e' null significa che l'entita' con id idDaCercare non e' stata trovata. Parte dunque la ricerca 
		 * diminuendo di una unita' l'id da cercare. Cio' e' utile in caso di eliminazioni successive di piu' 
		 * entita'.
		 */
		while(e == null)
			e = cercaId(idDaCercare-1);
		return e;
	}
	
	public Vector<Entita> getAzioniModello() {
		Vector <Entita> risultato = new Vector<Entita>();
		for(int i=0; i<elencoEntita.size()-1; i++) {
			Entita e = elencoEntita.elementAt(i);
			if(e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE))
				risultato.add(e);
		}
	}
	
	public Entita getUltimaEntita() {
		int idUltima = GestoreModello.contatoreEntita-1;
		return cercaId(idUltima);
	} 
	
	public boolean giaPresente(String nome) {
		/* 
		 * Controlla anche se il nome del modello e' gia' presente, per impedire l'inserimento di modelli con
		 * lo stesso nome	
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
	
	// Rimuove l'entita' con tale id, se la trova
	public boolean rimuoviEntitaAt(int id) {
		Entita e = null;
		for (int i = 0; i < elencoEntita.size(); i++) 
		{
			e = elencoEntita.get(i);
			if(e.getId()==id)
			{
				elencoEntita.remove(i);
				System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId()));
				return true; 
			}
			else
				if(e.rimuoviEntitaAt(id))
					return true;
		}
		return false;
	}
	
	public void setNome(String unNome) {
		nome = unNome;
	}
	
	public void setDescrizione(String unaDescrizione) {
		descrizione = unaDescrizione;
	}
	
	//TODO migliorare indentazione
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
	
	public Ramo[] getRami() {
		return null;
	}
	
	public int getIndentazione() {
		return 0;
	}
	
	public String getIdTipo() {
		return idTipo;
	}
}
