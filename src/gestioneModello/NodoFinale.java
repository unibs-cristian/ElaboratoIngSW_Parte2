/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneModello;

import java.util.Vector;

/**
 * Classe NodoFinale
 * Un'istanza di questa classe rappreseta il nodo finale di un modello
 */
public class NodoFinale implements Entita
{
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costante per stampa a video */
	public final static String MSG_TITOLO_NF = "NODO_FINALE";
	
	/** id numrico dell'entita' */
	private int id;
	
	/** Il tipo dell'entita' */
	private static String idTipo;
	
	/**
	 * Costruttore della classe NodoFinale
	 */
	public NodoFinale() {
		id = Modello.getInstance().getContatore();
		Modello.getInstance().incrementaContatore();
		idTipo = ID_TIPO_NODO_FINALE;
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
	
	public void rimuoviEntitaAt(int id) {}

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