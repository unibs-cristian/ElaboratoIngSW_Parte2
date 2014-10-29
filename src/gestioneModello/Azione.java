package gestioneModello;

import java.util.Vector;

import utilita.GUI;

/**
 * Classe che istanzia oggetti di tipo Azione. Un'azione e' un'entita' semplice che puo' essere aggiunta 
 * ad altre entita' complesse, come Branch, Cicli, Fork o Modelli.
 * @authors Sampietri Cristian, Rossi Pietro, Rubagotti Lorenzo
 *
 */
public class Azione implements Entita {
	
	/** Costante di tipo stringa per il metodo toString dell'azione */
	public final static String ID_TIPO = "AZIONE N. %d : %s";
	
	/** 
	 * Variabili di istanza. L'id e' un identificatore univoco che viene incrementato per ogni entita' inserita.
	 */
	private int id;
	/** Numero progressivo dell'azione inserita */
	private int idAzione;
	/** Titolo sintetico dell'azione */
	private String titolo;
	/** Variabile di istanza usata in fase di stampa a video del modello */
	private int valoreIndentazione;
	/** Contatore di istanze di Azione */
	private static int contatoreAzioni = 1;
	/** Identificatore del tipo */
	private String idTipo;
	
	//TODO Azioni composte
	
	/**
	 * Costruttore della classe
	 * @param _titolo
	 */
	public Azione(String _titolo) {
		id = GestoreModello.contatoreEntita;
		idAzione = contatoreAzioni;
		titolo = _titolo;
		contatoreAzioni++;
		valoreIndentazione=GUI.getRientro();
		GestoreModello.contatoreEntita++;
		idTipo = ID_TIPO_AZIONE;
	}
	
	public void addEntita(Entita e, int qualeRamo) {}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> elencoEntita = new Vector<Entita>();
		elencoEntita.add(this);
		return elencoEntita;
	}
	
	public Entita cercaId(int idDaCercare) {
		if(id == idDaCercare)
			return this;
		else 
			return null;
	}
	
	public void decrementaContatore() {
		contatoreAzioni--;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public int getId() {
		return id;
	}
	 
	public String getNome() {
		return titolo;
	}
	
	public boolean giaPresente(String nome) {
		return titolo.equalsIgnoreCase(nome);
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public boolean rimuoviEntitaAt(int id) {
		return false;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(ID_TIPO, idAzione, titolo));
		return risultato.toString();
	}

	public String getIdTipo() {
		return idTipo;
	}	
}