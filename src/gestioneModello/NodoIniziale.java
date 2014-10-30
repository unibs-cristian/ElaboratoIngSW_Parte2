package gestioneModello;

import java.util.Vector;
import java.io.Serializable;

import utilita.GUI;

public class NodoIniziale implements Entita, Serializable {

	private static final long serialVersionUID = 1L;
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
