package gestioneModello;
import java.util.Vector;
import Utilita.*;

public class Branch implements Entita {
	
	public final static String MSG_BRANCH = "- BRANCH N. %d : %s\n";
	public final static String MSG_ENTITA_RAMO_BRANCH = "ENTITA' RAMO N. %d";
	public final static String MSG_MERGE = "- MERGE N. %d\n";
	
	
	private int id;
	private int idBranch;
	private String titolo;
	private int numeroRami;
	private Ramo[] elencoRami;
	private Vector <Entita> elencoEntita;
	private int valoreIndentazione;
	private static int contatoreBranch = 1;
	
	public Branch (String _titolo, int _numeroRami) {
		id = GestoreModello.contatoreEntita;
		titolo = _titolo;
		numeroRami = _numeroRami;
		elencoRami = new Ramo [numeroRami];
		idBranch = contatoreBranch;
		elencoEntita = new Vector<Entita>();
		valoreIndentazione = GUI.getRientro();
		contatoreBranch++;
		GestoreModello.contatoreEntita++;
	}

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return titolo;
	}
	
	public int getNumeroRami() {
		return numeroRami;
	}
	
	public Vector<Entita> getEntita() {
		for(int i=0; i<elencoRami.length; i++) {
			Vector <Entita> entitaRamo = elencoRami[i].getEntitaRamo();
			for(int j=0; j<entitaRamo.size(); j++) {
				addEntita(entitaRamo.elementAt(j));
			}
		}
		return elencoEntita;
	}
	
	public void addEntita(Entita e) {
		elencoEntita.add(e);
	}		
	
	public boolean ramiTuttiVuoti() {
		for(int i=0; i<elencoRami.length; i++)
			if (elencoRami[i].getEntitaRamo().isEmpty() == false)
				return false;
		return true;
	}
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}
	
	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.indenta(String.format(MSG_BRANCH, idBranch, titolo.toUpperCase()), SPAZIO, valoreIndentazione));
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_BRANCH, i+1), SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
			risultato.append(GUI.indenta(elencoRami[i].toString(), SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		}
		risultato.append(GUI.indenta(String.format(MSG_MERGE, idBranch), SPAZIO, valoreIndentazione));
		return risultato.toString();
	}
}