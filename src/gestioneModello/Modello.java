/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import inputDati.GestoreModello;

import java.util.*;

import utilita.Util;

/**
 * Classe Modello
 * Un'istanza della classe Modello rappresenta un costrutto costituito da un nodo iniziale, da una o
 * piu' entita' seplici (Azione o Azione Complessa) o complesse (Branch, Fork, Cicli), e da un nodo 
 * finale  
 * 
 * INVARIANTI DI CLASSE : 
 * Tra le entita' che costituiscono il Modello, tutte devono avere nomi diversi 
 * tra di loro.
 */
public class Modello implements Entita{
	
	/** Costante numerica per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costanti stringa per la stampa a video */
	public final static String MSG_NOME_MODELLO = "NOME MODELLO : %s\n";
	public final static String MSG_DESCRIZIONE_MODELLO = "DESCRIZIONE MODELLO : %s\n\n";
	
	/** Costante stringa per messaggio d'errore */
	public final static String MSG_ERRORE_MODIFICA = "Errore. Non e' presente alcuna entita' da eliminare.";
	
	/** Il nome sintetico del modello */
	private String nome;
	
	/** Breve descrizione testuale del modello */
	private String descrizione;
		
	/** Vector contenente le entita' che costituiscono il modello */
	private Vector <Entita> elencoEntita;
	
	/** Vector contenente le azioni (semplici o composte) che costituiscono il modello */
	private Vector <Azione> elencoAzioni;
	
	/** Contatore delle entita' del modello */
	private int contatoreEntitaModello;
	
	/** Identificatore numerico del modello */
	private int idModello;
	
	/** Riferimento all'istanza che gestisce l'inserimento delle entita' per il modello */
	private GestoreModello gm;
	
	/** Stringa contenente l'id tipo del modello */
	private String idTipo;
	
	/** L'istanza (unica) del modello */
	private static Modello instance = null;
	
	/**
	 * Costruttore privato della classe Modello.
	 */
	private Modello() {
		elencoEntita = new Vector<Entita>();
		elencoAzioni = new Vector<Azione>();
		gm = new GestoreModello(this);
		idTipo = ID_TIPO_MODELLO;
		contatoreEntitaModello = 0;
	} 
	
	/**
	 * Costruttore privato della classe modello
	 *
	 * @param _nome : il nome da assegnare al modello
	 * @param _descrizione : la descrizione da assegnare al modello
	 */
	private Modello (String _nome, String _descrizione) {
		nome = _nome;
		descrizione = _descrizione;
		elencoEntita = new Vector<Entita>();
		gm = new GestoreModello(this);
		NodoIniziale ni = new NodoIniziale();
		elencoEntita.add(ni);
		contatoreEntitaModello = 0;
	}
	
	public void decrementaContatore() {
		contatoreEntitaModello --;
	}
	
	public void incrementaContatore() {
		contatoreEntitaModello ++;
	}
		
	public int getContatore() {
		return contatoreEntitaModello;
	}
	
	public int getId() {
		return idModello;
	}
	
	/**
	 * Controlla presenza modello nel sistema
	 *
	 * @return true se l'applicazione non ha un modello su cui operare, false altrimenti. 
	 */
	public static boolean isNull() {
		if(instance == null)
			return true;
		return false;
	}
	
	/**
	 * Ritorna la singola istanza di Modello.
	 *
	 * @return l'istanza
	 */
	public static Modello getInstance() {
		if(instance == null)
			instance = new Modello();
		return instance;
	}
	
	/**
	 * Cambia modello.
	 *
	 * @param nuovo : il nuovo modello su cui si vuole operare
	 */
	public static void cambiaModello(Modello nuovo) {
		instance = nuovo;
	}
	
	public Vector <Entita> getEntita() {
		return elencoEntita;
	}
	
	/**
	 * Ritorna l'azione all'indice corrispondente
	 *
	 * @param index the index
	 * @return the azione at
	 */
	public Azione getAzioneAt(int index) {
		return elencoAzioni.elementAt(index);
	}
	
	/**
	 * Dice quante azioni sono state inserite nel modello.
	 *
	 * @return la dimensione del Vector contenente le azioni.
	 */
	public int getNumeroAzioni() {
		return elencoAzioni.size();
	}
	
	/** Restituisce l'istanza che gestisce l'inserimento delle entita' per il modello */
	public GestoreModello getGm() {
		return gm;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addEntita (Entita e, int qualeRamo) {
		elencoEntita.add(e);
	}
	
	/**
	 * Aggiunge l'entita' e all'elenco di entita' che costituiscono il modello. 
	 *
	 * @param e : l'entita' da inserire 
	 */
	public void addEntita (Entita e) {
		elencoEntita.add(e);
	}
	
	/**
	 * Aggiunge un'azione all'elenco di azioni inserite nel modello
	 *
	 * @param a : l'azione da aggiungere
	 */
	public void addAzione(Azione a) {
		elencoAzioni.add(a);
	}
	
	/**
	 * Elimina ultima entita inserita.
	 */
	public void eliminaUltimaEntita() {
		//Se c'e' solo il nodo iniziale, non e' possibile effettuare la cancellazione.
		if(elencoEntita.size()<=1)     
			System.out.println(MSG_ERRORE_MODIFICA);
		else
		{
			int index = getContatore()-1;
			rimuoviEntitaAt(index);
		}
	}
	
	/**
	 * Fornisce le azioni inserite nel modello.
	 *
	 * @return the elenco azioni
	 */
	public Vector<Azione> getElencoAzioni() {
		return elencoAzioni;
	}
	
	/**
	 * Dice se nel modello e' gia' presente il nodo finale
	 *
	 * @return true, se e' presente il nodo finale
	 */
	public boolean nodoFinalePresente() {
		if(giaPresente(ID_TIPO_NODO_FINALE))
			return true;
		else
			return false;
	}	
	
	public boolean giaPresente(String nome) {
		/* 
		 * Controlla anche se il nome del modello o dell'entita' da inserire in esso  e' gia' 
		 * presente, per impedire l'inserimento di entita' aventi lo stesso nome.
		 */
		Boolean trovato = false;
		if(this.nome.equalsIgnoreCase(nome))
			return true;
		else {
			// Prende l'entita' i-esima. Se il nome coincide ritorna true, altrimenti cerca all'interno dell'entita'
			int i=0;
			while(trovato == false && i<elencoEntita.size()) {
				if(elencoEntita.elementAt(i).getNome().equalsIgnoreCase(nome)) 
					return true;
				else {
					trovato = elencoEntita.elementAt(i).giaPresente(nome);
					i++;
				}
			}
		}
		return trovato;
	}
	
	/**
	 * Controlla se due modelli sono uguali.
	 *
	 * @param altro : il modello da confrontare
	 * @return true se i due modelli sono uguali, false altrimenti
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
	 * Controlla se nel modello e' stata inserita almeno un'azione.
	 *
	 * @return true se elencoAzioni e' vuoto, false altrimenti.
	 */
	public boolean nessunaAzione() {
		return elencoAzioni.isEmpty();
	}  
	
	public void rimuoviEntitaAt(int id) {
		Entita e = null;
		for (int i = 0; i < elencoEntita.size(); i++) 
		{
			e = elencoEntita.get(i);
			if(e.getId()==id)
			{
				if(Util.yesOrNo(String.format(MSG_CONFERMA_CANCELLAZIONE,e.getNome()))) {
					elencoEntita.remove(i);
					decrementaContatore();
					if(e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE) || e.getIdTipo().equalsIgnoreCase(ID_TIPO_AZIONE_COMPOSTA))
						rimuoviAzione(e.getNome());
					System.out.println(String.format(MSG_ENTITA_RIMOSSA, e.getNome(),e.getId())); 
				}
			}
			else
				e.rimuoviEntitaAt(id);
		}
	}
	
	/**
	 * Rimuovi l'azione avente un certo nome, se presente.
	 *
	 * @param nomeAzione : il nome dell'azione da eliminare.
	 */
	public void rimuoviAzione(String nomeAzione) {
		for(int i=0; i<elencoAzioni.size(); i++)
			if(elencoAzioni.elementAt(i).getNome().equalsIgnoreCase(nomeAzione))
				elencoAzioni.remove(i);
	}
	
	/**
	 * Setta il nome del modello
	 *
	 * @param unNome : il nome da assegnare al modello
	 */
	public void setNome(String unNome) {
		nome = unNome;
	}
	
	/**
	 * Setta la descrizione.
	 *
	 * @param unaDescrizione : la descrizione da assegnare al modello.
	 */
	public void setDescrizione(String unaDescrizione) {
		descrizione = unaDescrizione;
	}
	
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