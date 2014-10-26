package gestioneModello;
import java.util.*;

public class Modello implements Entita {
	
	public final static String MSG_NOME_MODELLO = "NOME MODELLO : %s\n";
	public final static String MSG_DESCRIZIONE_MODELLO = "DESCRIZIONE MODELLO : %s\n\n";
	public final static String MSG_ERRORE_MODIFICA = "Errore. Non è presente alcuna entita' da eliminare.";
	
	private static int contatoreModello = 1;	
	private int idModello;
	private String nome;
	private String descrizione;
	private GestoreModello gm;
	public Vector <Entita> elencoEntita;
	
	/** Costruttore del modello */
	public Modello (String _nome, String _descrizione) {
		idModello = contatoreModello;
		nome = _nome;
		descrizione = _descrizione;
		elencoEntita = new Vector<Entita>();
		contatoreModello++;
		gm = new GestoreModello(this);
		NodoIniziale ni = new NodoIniziale();
		elencoEntita.add(ni);
	}
	
	public Vector <Entita> getEntita() {
		return elencoEntita;
	}
	
	public int getId() {
		return idModello;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Entita getUltimaEntita()
	{
		int numeroEntita = elencoEntita.size()-1;
		return elencoEntita.elementAt(numeroEntita);
	}
	
	public Entita getEntitaInPosizione(int i) {
		return elencoEntita.elementAt(i);
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
	
	public void eliminaUltimoElemento() {
		if(elencoEntita.size()<=1)     
			System.out.println(MSG_ERRORE_MODIFICA);
		else
			elencoEntita.remove(elencoEntita.size()-1);
	}
	
	public boolean nodoFinalePresente() {
		Entita e = getUltimaEntita();
		if(e.getNome()==NodoFinale.MSG_TITOLO_NF)
			return true;
		else
			return false;
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
}
