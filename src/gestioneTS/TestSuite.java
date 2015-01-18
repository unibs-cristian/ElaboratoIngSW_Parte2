/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package gestioneTS;
import inputDati.InserimentoCammino;

import java.io.Serializable;

import gestioneModello.Modello;
import statisticheGuasti.Distanze;
import statisticheGuasti.OrdinaElencoProbabilitaEIntervalliPosizione;
import utilita.Util;
import utilita.UtilitaStringhe;

import java.util.Vector;

/**
 * La classe Test Suite.
 * Un Test Suite e' un insieme di prove, strutturate in classi di equivalenza.
 * 
 * INVARIANTE DI CLASSE : Un Test Suite non puo' contenere due classi di equivalenza uguali.
 */
public class TestSuite implements Serializable {
	
	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costante per stampa a video */
	public final static String MSG_INTESTAZIONE_TS = "\n\nTEST SUITE PER IL MODELLO %s\n\n";
	
	/** Costanti stringa per messaggi stampati a video */
	public final static String MSG_CONTINUA_SI_NO_CE = "Si desidera inserire un'altra classe di equivalenza?";
	public final static String MSG_INS_CLASSE_EQ = "CLASSE DI EQUIVALENZA N. %d - INSERIMENTO INFORMAZIONI";
	public final static String MSG_CARD_CE = "Inserire la cardinalita' relativa alla classe di equivalenza : ";
	public final static String MSG_ERRORE_CE = "Errore! E' gia' presente nel Test Suite una classe di equivalenza uguale. Ripetere l'inserimento.";
	
	/** Vector con le classi di equivalenza che costituiscono il Test Suite. */
	private Vector <ClasseEquivalenza> elencoClassi;
	
	/** La diagnosi associata al Test Suite */
	private Diagnosi diagnosiAssociata;
	
	/** Il Modello relativo al Test Suite */
	private Modello modelloAssociato;
	
	/** Gli elenchi delle probabilita' */
	private OrdinaElencoProbabilitaEIntervalliPosizione elencoProb1;
	private OrdinaElencoProbabilitaEIntervalliPosizione elencoProb2;
	
	/** Le distanze */
	private Distanze dist;
	
	/** L'istanza unica di Test Suite */
	private static TestSuite instance = null;
	
	/**
	 * Costruttore della classe TestSuite. 
	 * Crea la struttura dati per contenere le classi di equivalenza inserite dall'utente.
	 */
	private TestSuite() {
		elencoClassi = new Vector <ClasseEquivalenza>();
	}
	
	/**
	 * Fornisce l'istanza singola di TestSuite
	 *
	 * @return Il Test Suite corrente. Se vuoto ne crea uno nuovo.
	 */
	public static TestSuite getInstance() {
		if(instance==null)
			instance = new TestSuite();
		return instance;
	}
	 
	/**
	 * Cambia test suite.
	 *
	 * @param nuovo : il nuovo Test Suite
	 */
	public static void cambiaTestSuite(TestSuite nuovo) {
		instance = nuovo;
	}
	
	/**
	 * Controlla se il Test Suite e' nullo.
	 *
	 * @return true, se non c'e' nessun Test Suite inserito
	 */
	public static boolean isNull() {
		if(instance==null)
			return true;
		return false;
	}
	
	/**
	 * Aggiunge la classe di equivalenza.
	 *
	 * @param clEq : la classe d'equivalenza da aggiungere alla struttura dati
	 */
	public void addClasseEquivalenza(ClasseEquivalenza daAggiungere) {
		//PRECONDIZIONE
		assert daAggiungere!=null : "Violata precondizione metodo addClasseEquivalenza. Passata classe nulla.";
		
		int sizeVecchia = elencoClassi.size();
		elencoClassi.add(daAggiungere);
		
		//POSTCONDIZIONE
		assert elencoClassi.size() == sizeVecchia+1;
	}
	
	/**
	 * Controlla se una classe di equivalenza e' gia' presente nel Test Suite, per evitare
	 * l'inserimento di doppioni
	 *
	 * @param ce : la classe d'equivalenza di cui verificare la presenza.
	 * @return true, se la classe e' gia' presente, false altrimenti.
	 */
	public boolean classeGiaPresente(ClasseEquivalenza daVerificare) {
		//PRECONDIZIONE
		assert daVerificare!=null : "Violata precondizione nel metodo giaInserita. Passata classe nulla.";
		
		for(int i=0; i<elencoClassi.size(); i++)
			if(elencoClassi.elementAt(i).isEqual(daVerificare))
				return true;
		return false;
	} 
		
	/**
	 * Fornisce le classi d'equivalenza del Test Suite.
	 *
	 * @return la struttura dati contenente le classi.
	 */
	public Vector <ClasseEquivalenza> getElencoClassi() {
		return elencoClassi;
	}
	
	/**
	 * Fornisce la diagnosi
	 *
	 * @return la diagnosi
	 */
	public Diagnosi getDiagnosi() {
		return diagnosiAssociata;
	}
	
	/**
	 * Fornisce l'elenco probabilita' 1
	 *
	 * @return l'elenco probabilita' 1
	 */
	public OrdinaElencoProbabilitaEIntervalliPosizione getElencoProb1() {
		return elencoProb1;
	}
	
	/**
	 * Fornisce l'elenco probabilita' 2
	 *
	 * @return l'elenco probabilita' 2
	 */
	public OrdinaElencoProbabilitaEIntervalliPosizione getElencoProb2() {
		return elencoProb2;
	}
	
	/**
	 * Fornisce le distanze
	 *
	 * @return le distanze
	 */
	public Distanze getDistanze() {
		return dist;
	}
	
	/**
	 * Fornisce il modello relativo al Test Suite
	 *
	 * @return il modello 
	 */
	public Modello getModello() {
		return modelloAssociato;
	}
	
	/**
	 * Controlla se il Test Suite ha gia' un insieme delle diagnosi associato
	 * 
	 * @return true, se la diagnosi e' diversa da null, false altrimenti
	 */
	public boolean hasDiagnosi() {
		if(diagnosiAssociata == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Controlla se il Test Suite ha associate le probabilita' e le distanze.
	 * 
	 * @return true, se le probabilita' e le distanze sono null, false altrimenti
	 */
	public boolean hasProbabilitaDistanze() {
		if(elencoProb1 == null || elencoProb2 == null || dist == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Inserisce le classi di equivalenza nel Test Suite
	 * @param attuale : il test suite per cui effettuare l'inserimento.
	 */
	public void inserisciClassiEquivalenza() {
		int i=1;
		boolean giaPresente = false;
		//Inserimento classi di equivalenza
		do {
			do {
				System.out.println(String.format(MSG_INS_CLASSE_EQ, i));
				//Inserimento cardinalita' e creazione classe di equivalenza.
				int cardinalita = Util.leggiIntConMinimo(MSG_CARD_CE, 1);
				ClasseEquivalenza daInserire = new ClasseEquivalenza(cardinalita);
				//Inserimento Cammino Globale
				InserimentoCammino inserimento = new InserimentoCammino(daInserire, new CamminoAzioni(true));
				inserimento.inserisciCamm();
				daInserire.inserisciInsiemeCopertura(i);
				//Se la classe e' gia' presente nel TS non la aggiunge e fa ripetere l'inserimento.
				if(classeGiaPresente(daInserire)) {
					System.out.println(MSG_ERRORE_CE);
					giaPresente = true;
				}
				//Se non e' gia' presente, aggiunge la nuova classe al TS
				else {
					addClasseEquivalenza(daInserire);
					giaPresente = false;
				}
			} while(giaPresente == true);
			i++;
		} while(Util.yesOrNo(MSG_CONTINUA_SI_NO_CE));
	}
	
	/**
	 * Confronta due Test Suite verificandone l'uguaglianza.
	 * 
	 * @param altro : il Test Suite con cui confrontare
	 * @return true, se i due Test Suite sono uguali, false altrimenti.
	 */
	public boolean isEqual(TestSuite altro) {
		for(int i=0; i<elencoClassi.size(); i++)
			if(!altro.getElencoClassi().elementAt(i).isEqual(elencoClassi.elementAt(i)))
				return false;
		return true;
	}
	
	/**
	 * Setta l'istanza di Diagnosi
	 * 
	 * @param d : la diagnosi da associare.
	 */
	public void setDiagnosi(Diagnosi d) {
		diagnosiAssociata = d;
	}
	
		/**
	 * Setta l'istanza di Probabilita'
	 * 
	 * @param elenco1 : l'elenco di probabilita' col metodo 1 
	 */
	public void setElencoProb1(OrdinaElencoProbabilitaEIntervalliPosizione elenco1) {
		elencoProb1 = elenco1;		
	}
	
	/**
	 * Setta l'istanza di Probabilita'
	 * 
	 * @param elenco2 : l'elenco di probabilita' col metodo 2 
	 */
	public void setElencoProb2(OrdinaElencoProbabilitaEIntervalliPosizione elenco2) {
		elencoProb2 = elenco2;		
	}
	
	/**
	 * Setta le distanze
	 * 
	 * @param di : le distanze 
	 */
	public void setDistanze(Distanze di) {
		dist = di;
	}
	
	/**
	 * Setta l'istanza di modello.
	 *
	 * @param m : il modello da associare
	 */
	public void setModello(Modello m) {
		modelloAssociato = m;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(String.format(UtilitaStringhe.incorniciaStringa(MSG_INTESTAZIONE_TS), modelloAssociato.getNome()));
		for(int i=0; i<elencoClassi.size(); i++) {
			risultato.append("\n\n");
			risultato.append(String.format("- CLASSE DI EQUIVALENZA N.%d\n"+elencoClassi.elementAt(i).toString(),i+1));
		}
		return risultato.toString();
	}
}
