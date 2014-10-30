package gestioneModello;

import java.util.Vector;

import utilita.GUI;

public class NodoIniziale implements Entita {

	public final static String MSG_TITOLO_NI = "NODO_INIZIALE";
	
	private int id;
	
	/** Identificatore del tipo */
	private static String idTipo;
	
	public NodoIniziale() {
		id = GestoreModello.contatoreEntita;
		GestoreModello.contatoreEntita=1;
		idTipo = ID_TIPO_NODO_INIZIALE;
	}
	
	public void addEntita(Entita e, int qualeRamo) {}
	
	public Entita cercaPerNome(String nomeDaCercare) {
		if(idTipo == nomeDaCercare)
			return this;
		else 
			return null;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIndentazione() {
		return 0;
	}
	
	public String getNome() {
		return MSG_TITOLO_NI;
	}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> daRestituire = new Vector<Entita>();
		daRestituire.add(this);
		return daRestituire;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public boolean giaPresente(String nome) {
		return false;
	}

	public boolean rimuoviEntitaAt(int id) {
		return false;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.aCapoDopo(MSG_TITOLO_NI));
		return risultato.toString();
	}
	
	public String getIdTipo() {
		return idTipo;
	}
	
	public Vector <Entita> getAzioni() {
		return null;
	}
}
