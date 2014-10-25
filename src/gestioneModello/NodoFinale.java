package gestioneModello;

import java.util.Vector;

public class NodoFinale implements Entita 
{
	public final static String MSG_TITOLO_NF = "NODO_FINALE";
	
	private int id;
	
	public NodoFinale() {
		id = GestoreModello.contatoreEntita;
		GestoreModello.contatoreEntita++;
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
	
	public void addEntita(Entita e, int qualeRamo) {}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(MSG_TITOLO_NF);
		return risultato.toString();
	}
}