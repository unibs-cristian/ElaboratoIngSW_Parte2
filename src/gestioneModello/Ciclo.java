package gestioneModello;

import java.util.Vector;
import Utilita.GUI;

public class Ciclo implements Entita {

	public final static String MSG_CICLO = "< CICLO N. %d : %s\nMERGE N. %d\n";
	public final static String MSG_CICLO_COND_INIZIALE = "CICLO A CONDIZIONE INIZIALE";
	public final static String MSG_CICLO_COND_FINALE = "CICLO A CONDIZIONE FINALE\n";
	public final static String MSG_ATTIVITA_INIZIALI = "ENTITA' RAMO N.1 (ATTIVITA' INIZIALI)";
	public final static String MSG_COND_USCITA = "ENTITA' RAMO N.2 (CONDIZIONE DI USCITA)";
	public final static String MSG_RAMO_VUOTO = "Nessuna\n";
	public final static String MSG_COND_PERMANENZA_CICLO = "ENTITA RAMO N.3 (CONDIZIONE DI PERMANENZA NEL CICLO)\n";
	public final static String MSG_BRANCH_CICLO = "- BRANCH N. %d >";
	
	public final static int NUM_RAMI_CICLO = 3;
	
	private int id;
	private int idCiclo;
	private String titolo;
	private int numRami;
	private Ramo [] elencoRami;
	private static int contatoreCicli = 1;
	private Vector<Entita> elencoEntita;
	private int valoreIndentazione;
	
	public Ciclo(String _titolo)
	{
		id = GestoreModello.contatoreEntita;
		idCiclo = contatoreCicli;
		titolo = _titolo;
		numRami = NUM_RAMI_CICLO;
		elencoRami = new Ramo [NUM_RAMI_CICLO];  // Ramo 1 : eventuali attività iniziali    Ramo 2 : condizione uscita ciclo    Ramo 3 : condizione di permanenza nel ciclo
		contatoreCicli++;
		GestoreModello.contatoreEntita++;
		elencoEntita = new Vector<Entita>();
		valoreIndentazione = GUI.getRientro();
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return titolo;
	}
	
	public int getNumeroRami() {
		return numRami;
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
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(GUI.indenta(String.format(MSG_CICLO, idCiclo, titolo.toUpperCase(), idCiclo), SPAZIO, valoreIndentazione));
		if(elencoRami[0].getEntitaRamo().isEmpty())
		{
			risultato.append(GUI.indenta(MSG_CICLO_COND_INIZIALE,SPAZIO,valoreIndentazione+INCREMENTO_INDENTAZIONE));
			risultato.append(GUI.indenta(MSG_ATTIVITA_INIZIALI, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		}
		else
		{
			risultato.append(GUI.indenta(MSG_CICLO_COND_FINALE, SPAZIO,valoreIndentazione+INCREMENTO_INDENTAZIONE));
			risultato.append(GUI.indenta(MSG_ATTIVITA_INIZIALI, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
			for(int j=0; j<elencoRami[0].getEntitaRamo().size(); j++) {
				risultato.append(GUI.indenta(elencoRami[0].toString(),SPAZIO,valoreIndentazione+INCREMENTO_INDENTAZIONE));
			}
		}
		risultato.append(GUI.indenta(MSG_COND_USCITA, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		if(elencoRami[1].getEntitaRamo().isEmpty())
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		else
			risultato.append(GUI.indenta(elencoRami[1].toString(),SPAZIO,valoreIndentazione+INCREMENTO_INDENTAZIONE));
		risultato.append(GUI.indenta(MSG_COND_PERMANENZA_CICLO, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		if(elencoRami[2].getEntitaRamo().isEmpty())
			risultato.append(GUI.indenta(MSG_RAMO_VUOTO, SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		else
			risultato.append(GUI.indenta(elencoRami[2].toString(), SPAZIO, valoreIndentazione+INCREMENTO_INDENTAZIONE));
		risultato.append(GUI.indenta(String.format(MSG_BRANCH_CICLO, idCiclo), SPAZIO, valoreIndentazione));
		return risultato.toString();
	}
}