package gestioneModello;

import java.util.Vector;

public class NodoFinale implements Entita 
{
	public final static String MSG_TITOLO_NF = "NODO_FINALE";
	
	private int id;
	/** Identificatore del tipo */
	private static String idTipo = "NF";
	
	public NodoFinale() {
		id = GestoreModello.contatoreEntita;
		GestoreModello.contatoreEntita++;
	}
	
	public Entita cercaId(int idDaCercare) {
		if(id == idDaCercare)
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
}
