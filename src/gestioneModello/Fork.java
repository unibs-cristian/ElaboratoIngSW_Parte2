package gestioneModello;

import java.util.Vector;

import Utilita.GUI;

public class Fork implements Entita {
	
	public final static String MSG_FORK = "{ FORK N. %d : %s";
	public final static String MSG_ENTITA_RAMO_FORK = "ENTITA' RAMO PARALLELO N. %d";
	public final static String MSG_JOIN = "JOIN N. %d }\n";
	
	private int id;
	private int idFork;
	private String titolo;
	private int numRami;
	private Ramo[] elencoRami;
	private Vector<Entita> elencoEntita;
	private int valoreIndentazione;
	private static int contatoreFork = 1;
	
	public Fork(String _titolo, int _numRami) {
		id = GestoreModello.contatoreEntita;
		idFork = contatoreFork;
		titolo = _titolo;
		numRami = _numRami;
		elencoRami = new Ramo[numRami];
		elencoEntita = new Vector<Entita>();
		valoreIndentazione = GUI.getRientro();
		contatoreFork++;
		GestoreModello.contatoreEntita++;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return titolo;
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
	
	public void addEntita(Entita e, int r) {
		elencoRami[r].aggiungiEntitaRamo(e);
	}
	
	public int getNumeroRami() {
		return numRami;
	}
	
	public Ramo[] getRami() {
		return elencoRami;
	}

	public int getIndentazione() {
		return valoreIndentazione;
	}
	
	public boolean ramiTuttiVuoti() {
		for(int i=0; i<elencoRami.length; i++)
			if (elencoRami[i].getEntitaRamo().isEmpty() == false)
				return false;
		return true;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.indenta(String.format(MSG_FORK, idFork, titolo.toUpperCase()),SPAZIO,valoreIndentazione-GUI.FATTORE_INCREMENTO));
		for(int i=0; i<elencoRami.length; i++) {
			risultato.append(GUI.indenta(String.format(MSG_ENTITA_RAMO_FORK, i+1), SPAZIO, valoreIndentazione));
			risultato.append(elencoRami[i].toString());
		}
		risultato.append(GUI.indenta(String.format(MSG_JOIN, idFork), SPAZIO, valoreIndentazione - GUI.FATTORE_INCREMENTO));
		return risultato.toString();
	}
}