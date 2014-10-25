package gestioneModello;

import java.util.Vector;

import Utilita.GUI;

public class Azione implements Entita {
	
	public final static String ID_TIPO = "AZIONE N. %d : %s\n";
	
	private int id;
	private int idAzione;
	private String titolo;
	private int valoreIndentazione;
	private static int contatoreAzioni = 1;
	
	public Azione(String _titolo) {
		id = GestoreModello.contatoreEntita;
		idAzione = contatoreAzioni;
		titolo = _titolo;
		contatoreAzioni++;
		valoreIndentazione=GUI.getRientro();
		GestoreModello.contatoreEntita++;
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
	 
	public void addEntita(Entita e, int qualeRamo) {}
	
	public Vector<Entita> getEntita() {
		Vector <Entita> elencoEntita = new Vector<Entita>();
		elencoEntita.add(this);
		return elencoEntita;
	}
	
	public void decrementaContatore() {
		contatoreAzioni--;
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public Ramo[] getRami() {
		return null;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.indenta(String.format(ID_TIPO, idAzione, titolo), SPAZIO, valoreIndentazione));
		return risultato.toString();
	}	
}