package gestioneModello;

import inputDati.GestoreModello;

import java.util.Vector;
import java.io.Serializable;

public class NodoFinale implements Entita, Serializable
{
	private static final long serialVersionUID = 1L;
	public final static String MSG_TITOLO_NF = "NODO_FINALE";
	
	private int id;
	/** Identificatore del tipo */
	private static String idTipo;
	
	public NodoFinale() {
		id = GestoreModello.contatoreEntita;
		GestoreModello.contatoreEntita++;
		idTipo = ID_TIPO_NODO_FINALE;
	}
	
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
		return MSG_TITOLO_NF;
	}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> daRestituire = new Vector<Entita>();
		daRestituire.add(this);
		return daRestituire;
	}
	 
	public boolean giaPresente(String nome) {
		return nome==ID_TIPO_NODO_FINALE;	
	}	
	
	public void addEntita(Entita e, int qualeRamo) {}
	
	public boolean rimuoviEntitaAt(int id) {
		return false;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(MSG_TITOLO_NF);
		return risultato.toString();
	}
	
	public String getIdTipo() {
		return idTipo;
	}
	
	public Vector <Entita> getAzioni() {
		return null;
	}
}
