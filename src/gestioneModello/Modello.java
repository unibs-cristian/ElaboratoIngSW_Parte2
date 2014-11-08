package gestioneModello;
import inputDati.GestoreModello;

import java.util.*;
import java.io.Serializable;

public class Modello implements Entita, Serializable {
	
	private static final long serialVersionUID = 1L;
	public final static String MSG_NOME_MODELLO = "NOME MODELLO : %s\n";
	public final static String MSG_DESCRIZIONE_MODELLO = "DESCRIZIONE MODELLO : %s\n\n";
	public final static String MSG_ERRORE_MODIFICA = "Errore. Non e' presente alcuna entita' da eliminare.";
	
	private String nome;
	private String descrizione;
	private GestoreModello gm;
	private Vector <Entita> elencoEntita;
	private Vector <Azione> elencoAzioni;
	private static int contatoreModello = 1;
	private int idModello;
	private String idTipo;
	
	/** L'istanza (unica) di modello su cui il sistema lavora e' inizialmente null. */
	private static Modello instance = null;
	
	/** Costruttore del modello. Poiche' si usa il Pattern Singleton, il costruttore e' private. */
	private Modello() {
		elencoEntita = new Vector<Entita>();
		elencoAzioni = new Vector<Azione>();
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
	
	public Azione getAzioneAt(int index) {
		return elencoAzioni.elementAt(index);
	}
	
	public int getNumeroAzioni() {
		return elencoAzioni.size();
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
	
	public void addAzione(Azione a) {
		elencoAzioni.add(a);
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
	
	public Vector<Azione> getElencoAzioni() {
		return elencoAzioni;
	}
	
	public boolean nodoFinalePresente() {
		if(giaPresente(ID_TIPO_NODO_FINALE))
			return true;
		else
			return false;
	}
	
	/**
	 * Cerca un'entita' per nome (il quale e' associato univocamente ad un'entita') e se la 
	 * trova la restituisce, altrimenti restituisce null.
	 */
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
	
	public boolean isEqual(Modello altro) {
		if((nome.equals(altro.getNome()) == false) || getNumeroAzioni() != altro.getNumeroAzioni())
			return false;
		else 
			for(int i=0; i<elencoAzioni.size(); i--)
				if(getAzioneAt(i).equals(altro.getAzioneAt(i)) == false)
					return false;
		return true;
	}
	
	public boolean nessunaAzione() {
		return elencoAzioni.isEmpty();
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
	
	public void rimuoviAzione(String nomeAzione) {
		for(int i=0; i<elencoAzioni.size(); i++)
			if(elencoAzioni.elementAt(i).getNome().equalsIgnoreCase(nomeAzione))
				elencoAzioni.remove(i);
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