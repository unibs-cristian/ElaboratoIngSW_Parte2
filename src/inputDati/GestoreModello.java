/*
 * @author Pietro Rossi, Lorenzo Rubagotti, Cristian Sampietri
 */
package inputDati;

import gestioneModello.Azione;
import gestioneModello.Branch;
import gestioneModello.Ciclo;
import gestioneModello.Entita;
import gestioneModello.Fork;
import gestioneModello.Modello;
import gestioneModello.NodoFinale;
import gestioneModello.Ramo;

import java.io.File;
import java.util.Vector;

import utilita.Menu;
import utilita.Stream;
import utilita.Util;

import java.io.Serializable;

/**
 * Classe GestoreModello.
 * Un'istanza di questa classe si riferisce ad uno e un solo modello e gestisce l'interazione con 
 * l'utente per tutte le azioni che e' possibile eseguire su un modello. 
 */
public class GestoreModello implements Serializable {

	/** Costante per il salvataggio */
	private static final long serialVersionUID = 1L;
	
	/** Costanti stringa per i titoli dei menu' */
	public final static String MSG_TITOLO_MENU_INSERIMENTO_MODELLO = "BENVENUTO NEL MENU INSERIMENTO MODELLO\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_BRANCH = "MENU GESTIONE BRANCH %s - RAMO %d\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_CICLO = "MENU GESTIONE CICLO %s - RAMO %d\n\nCosa si desidera fare?";
	public final static String MSG_TITOLO_MENU_FORK = "MENU GESTIONE FORK %s - RAMO %d\n\nCosa si desidera fare?";
	
	/** Costanti stringa per le opzioni dei menu' */
	public final static String MSG_INSERIMENTO_AZIONE = "1 - Inserimento Azione";
	public final static String MSG_INSERIMENTO_BRANCH = "2 - Inserimento Branch";
	public final static String MSG_INSERIMENTO_CICLO = "3 - Inserimento Ciclo";
	public final static String MSG_INSERIMENTO_FORK = "4 - Inserimento Fork";
	public final static String MSG_MODIFICA_MODELLO = "5 - Eliminare l'ultima entita' inserita";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = "6 - Visualizzare Modello";
	public final static String MSG_USCITA_INSERIMENTO = "7 - Tornare al menu principale";
	public final static String MSG_CHIUSURA_RAMO = "7 - Chiudere ramo corrente";
	public final static String MSG_INSERIMENTO_NODO_FINALE = "8 - Inserimento Nodo Finale";
	public final static String MSG_SALVATAGGIO_MODELLO = "9 - Salvataggio Modello";

	/** Messaggi di errori vari */
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	public final static String MSG_NO_AZIONE = "Errore! Non e' ancora presente nessuna azione nel modello.";
	public final static String MSG_ERRORE_RAMI = "L'entita' ha gia' un ramo vuoto inserito. Impossibile creare piu' rami vuoti.\nInserire almeno un'entita' per il ramo corrente.";
	public final static String MSG_DUPLICATO = "Errore! E' gia' presente nel modello un'entita' con lo stesso nome. Si prega di inserire un nome diverso.";
	public final static String MSG_NODO_FINALE_PRESENTE = "Attenzione! Nel modello e' gia' presente il Nodo Finale.\nPer poter inserire nuove entita' eliminare il Nodo Finale.";
	public final static String MSG_ERRORE_PRECONDIZIONE_1 = "Precondizione violata per il metodo menuInserimentoSecondario";
	public final static String MSG_ERRORE_PRECONDIZIONE_2 = "Precondizione violata per il metodo inserimentoAzione";
	public final static String MSG_ERRORE_PRECONDIZIONE_3 = "Precondizione violata per il metodo inserimentoBranch";
	public final static String MSG_ERRORE_PRECONDIZIONE_4 = "Precondizione violata per il metodo inserimentoCiclo";
	public final static String MSG_ERRORE_PRECONDIZIONE_5 = "Precondizione violata per il metodo inserimentoFork";
	public final static String MSG_ERRORE_PRECONDIZIONE_6 = "Precondizione violata per il metodo inserimentoNodoFinale";

	/** Messaggi stampati a video quando si sta creando una nuova azione */
	public final static String MSG_TITOLO_AZIONE = "Digitare il titolo dell'azione che si sta inserendo: ";
	public final static String MSG_COMPOSTA_SI_NO = "L'azione e' composta?";
	public final static String MSG_MODELLO_COMPOSTA = "Digitare il titolo del relativo modello : ";
	
	/** Messaggi stampati a video quando si sta creando un nuovo Branch */
	public final static String MSG_TITOLO_BRANCH = "Digitare il titolo del Branch che si sta inserendo: ";
	public final static String MSG_NUM_RAMI_BRANCH = "Quanti flussi d'uscita si vuole che abbia il nuovo Branch? ->";
	public final static String MSG_RAMO_BRANCH = "%s (ID = %d) - INSERIMENTO ENTITA' PER IL RAMO N. %d";
	public final static String MSG_RAMO_SUCC = "Per chiudere il ramo corrente selezionare l'opzione 'Chiudere ramo corrente'.\n";
	public final static String MSG_CHIUSURA_BRANCH = "Tutti i rami di %s (ID = %d) sono stati completati ed e' stato creato il relativo Merge N.%d";
	
	/** Messaggi stampati a video quando si sta creando un nuovo Ciclo */
	public final static String MSG_TITOLO_CICLO = "Digitare il titolo del Ciclo che si sta inserendo. ";
	public final static String MSG_CHIUSURA_CICLO = "E' stato completato l'inserimento di tutti i rami del Ciclo N.%d";
	public final static String MSG_ATTIVITA_INIZIALI_CICLO = "CICLO %s - INSERIMENTO ENTITA' PER IL RAMO 'ATTIVITA' INIZIALI'.\nNel caso in cui tale ramo venga lasciato vuoto verra' creato un ciclo\na condizione iniziale, altrimenti il ciclo sara' a condizione finale.\n";
	public final static String MSG_ATTIVITA_COND_PERMANENZA_CICLO = "CICLO %s - INSERIMENTO ENTITA' PER IL RAMO 'CONDIZIONE DI PERMANENZA NEL CICLO'.\nTale ramo puo' essere lasciato vuoto (se non sono e' vuoto il precedente)";
	
	/** Messaggi stampati a video quando si sta creando un nuovo Fork */
	public final static String MSG_TITOLO_FORK = "Digitare il titolo del costrutto Fork-Join che si sta inserendo: ";
	public final static String MSG_NUM_RAMI_FORK = "Quanti rami si vuole che abbia il fork? ->";
	public final static String MSG_RAMO_FORK = "%s (ID = %d) - INSERIMENTO ENTITA' PER IL RAMO PARALLELO N. %d";
	public final static String MSG_CHIUSURA_FORK = "Tutti i rami paralleli di %s (ID = %d) sono stati completati ed e' stato creato il relativo Join N.%d";
	
	/** Messaggio stampato a video al termine della creazione di un'entita' */
	public final static String MSG_NUOVA_ENTITA = "La nuova entita' %s e' stata aggiunta a %s";
		
	//TODO se torno al menu' principale non posso piu' modificare. Vedere se va bene.
	/** Messaggio di conferma per il ritorno al menu' principale */
	public final static String MSG_CONFERMA = "Attenzione! Se si ritorna al menu' principale non sara' piu' possibile inserire entita' per\nquesto modello. Sei sicuro di voler uscire dalla modalita' inserimento?";
		
	/** Costanti stringa varie */
	public final static String MSG_CONFERMA_CANCELLAZIONE = "Si desidera procedere con l'eliminazione dell'entita' %s?";
	public static final String MSG_NOME_MODELLO = "Come si desidera chiamare il modello?";
	public final static String MSG_ENTITA_ELIMINATA = "E' stata eliminata l'entita' di nome %s (id %d)";
	public final static String MSG_NODO_FINALE = "Nodo finale inserito per il modello %s";
	public final static String MSG_MODELLO_INCOMPLETO = "Attenzione! Per inserire il nodo finale e' necessario che nel modello sia presente almeno un'azione.";	
	
	/** Costanti numeriche varie */
	public final static int MIN_RAMI = 2;
	public final static int OPZ_BRANCH = 1;
	public final static int OPZ_CICLO = 2;
	public final static int OPZ_FORK = 3;
	public final static int FATTORE_INCREMENTO = 4;
	
	/** Il valore dell'indentazione usato per visualizzare correttamente il modello */
	private static int rientro;
	
	/** Il modello per cui viene gestita l'interazione con l'utente. */
	private Modello mod;
	
	/**
	 * Costruttore
	 *
	 * @param _mod : il modello associato
	 */
	public GestoreModello(Modello _mod) {
		mod = _mod;
	}
		
	/**
	 * Menu inserimento primario per il modello
	 */
	public void menuInserimentoPrimario() {
		Vector<String> opzioniMenuInserimento = new Vector<String>();
		opzioniMenuInserimento.add(MSG_INSERIMENTO_AZIONE);      //Voce 1 --> azione
		opzioniMenuInserimento.add(MSG_INSERIMENTO_BRANCH);      //Voce 2 --> branch
		opzioniMenuInserimento.add(MSG_INSERIMENTO_CICLO);       //Voce 3 --> ciclo
		opzioniMenuInserimento.add(MSG_INSERIMENTO_FORK);        //Voce 4 --> fork 
		opzioniMenuInserimento.add(MSG_MODIFICA_MODELLO);        //Voce 5 --> modifica modello
		opzioniMenuInserimento.add(MSG_VISUALIZZAZIONE_MODELLO); //Voce 6 --> visualizza modello parziale
		opzioniMenuInserimento.add(MSG_USCITA_INSERIMENTO);      //Voce 7 --> ritorna al menu' principale (aggiungere richiesta di salvataggio modello)
		opzioniMenuInserimento.add(MSG_INSERIMENTO_NODO_FINALE); //Voce 8 --> nodo finale
		opzioniMenuInserimento.add(MSG_SALVATAGGIO_MODELLO);     //Voce 9 --> salvataggio modello 
		Menu menuInserimentoEntita = new Menu(MSG_TITOLO_MENU_INSERIMENTO_MODELLO,opzioniMenuInserimento);
		
		boolean insFinito = false;
		setRientro(0);
		
		do {
			switch(menuInserimentoEntita.scegliVoce()) {
				case 1:
					if(mod.nodoFinalePresente()==false)
					{
						inserimentoAzione(mod,0);
						break;
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}

				case 2:   
					if(mod.nodoFinalePresente()==false)
					{
						if(mod.nessunaAzione()) {
							System.out.println(MSG_NO_AZIONE);
							break;
						}
						else {
							inserimentoBranch(mod,0);
							break;
						}
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
				
				case 3 : 
					if(mod.nodoFinalePresente()==false)
					{
						if(mod.nessunaAzione()) {
							System.out.println(MSG_NO_AZIONE);
							break;
						}
						else {
							inserimentoCiclo(mod,0);
							break;
						}
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
					
				case 4 :
					if(mod.nodoFinalePresente()==false)
					{
						inserimentoFork(mod,0);
						break;
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
					
				case 5:
					mod.eliminaUltimaEntita();   
					break;
				
				case 6: 
					System.out.println(mod.toString()); 
					break;
				
				case 7: 
					if(!(Util.yesOrNo(MSG_CONFERMA)))
						break;
					else {
						insFinito = true;
						break;
					}
					
				case 8 :
					if(mod.getNumeroAzioni()>=1)
					{
						if(mod.nodoFinalePresente()==false)
						{
							inserimentoNodoFinale();
							break;
						}
						else
						{
							System.out.println(MSG_NODO_FINALE_PRESENTE);
							break;
						}
					}
					else
					{
						System.out.println(MSG_MODELLO_INCOMPLETO);
						break;
					}
					
				case 9 : 
					{
						File nomeFile = new File(Util.leggiString(MSG_NOME_MODELLO));
						Stream.salvaFile(nomeFile, mod, true);
					}
				break;
					
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(insFinito == false);
	}
	
	/**
	 * Menu inserimento secondario.
	 * Gestisce l'interazione con l'utente per le entita' complesse quali Branch, Fork e Cicli
	 *
	 * @param e : l'entita' per cui gestire l'inserimento
	 * @param tipo : il tipo dell'entita' e
	 */
	private void menuInserimentoSecondario(Entita e, int tipo) {
		/*
		 * PRECONDIZIONI : l'entita' per la quale si gestisce l'inserimento non deve essere nulla e
		 * l'intero deve corrispondere ad una delle tre opzioni specificate nelle costanti numeriche.
		 */
		assert e != null && (tipo == OPZ_BRANCH || tipo == OPZ_CICLO || tipo == OPZ_FORK) : MSG_ERRORE_PRECONDIZIONE_1; 
		
		String t = "";		
		//TODO METTERE METODO AUSILIARIO IN FASE DI REFACTORING !!!!
		switch(tipo) {
		case OPZ_BRANCH: t=MSG_TITOLO_MENU_BRANCH; break;
		case OPZ_CICLO: t=MSG_TITOLO_MENU_CICLO; break;
		case OPZ_FORK: t=MSG_TITOLO_MENU_FORK; break;
		}
	
		Vector <String> opzioniMenuSecondario = new Vector<String>();
		opzioniMenuSecondario.add(MSG_INSERIMENTO_AZIONE);      //Voce 1 --> azione
		opzioniMenuSecondario.add(MSG_INSERIMENTO_BRANCH);      //Voce 2 --> branch
		opzioniMenuSecondario.add(MSG_INSERIMENTO_CICLO);       //Voce 3 --> ciclo
		opzioniMenuSecondario.add(MSG_INSERIMENTO_FORK);        //Voce 4 --> fork 
		opzioniMenuSecondario.add(MSG_MODIFICA_MODELLO);        //Voce 5 --> modifica modello
		opzioniMenuSecondario.add(MSG_VISUALIZZAZIONE_MODELLO); //Voce 6 --> visualizza modello parziale
		opzioniMenuSecondario.add(MSG_CHIUSURA_RAMO);           //Voce 7 --> Chiudi ramo
			
		boolean ramoVuotoPresente = false;  //Serve per impedire l'inserimento di piu' rami vuoti
		for(int i=0; i<e.getRami().length; i++)
		{
			Menu menuSecondario = new Menu(String.format(t, e.getNome(), i+1),opzioniMenuSecondario);
				
			switch(tipo) {
			case OPZ_BRANCH: System.out.println(String.format(MSG_RAMO_BRANCH, e.getNome(), e.getId(), i+1));
				System.out.println(MSG_RAMO_SUCC);
				break;
			case OPZ_CICLO:
				{
					if(i==0)
					{
						System.out.println(String.format(MSG_ATTIVITA_INIZIALI_CICLO, e.getNome())); break;
					}
					else if(i==1)
					{
						System.out.println(String.format(MSG_ATTIVITA_COND_PERMANENZA_CICLO, e.getNome())); break;
					}
				}
				case OPZ_FORK:   System.out.println(String.format(MSG_RAMO_FORK, e.getNome(), e.getId(), i+1));
				                 System.out.println(MSG_RAMO_SUCC);
				                 break;
				}
			
				boolean esci = false;
				do {
					switch(menuSecondario.scegliVoce()) {
					case 1:    
						inserimentoAzione(e,i);
						break;

					case 2:  
						inserimentoBranch(e,i);
						break;
					
					case 3 : 
						inserimentoCiclo(e,i);
						break;
						
					case 4 :
						inserimentoFork(e,i);
						break;
					 
					case 5:
					{	
						Ramo r = e.getRami()[i];
						/*
						 * Se il ramo corrente e' vuoto, non viene eliminata alcuna entita' e viene stampato a
						 * video un opportuno messaggio d'errore.
						 * L'eliminazione dell'entita' composta deve avvenire da menu' principale ed avviene dopo 
						 * aver eliminato tutte le sottoentita' che la compongono.
						 */
						if(r.isEmpty())
						{
							System.out.println(Modello.MSG_ERRORE_MODIFICA);
							break;
						}
						else {
						/*
						 * Se il ramo corrente non e' vuoto si elimina l'ultima entita' inserita nel ramo.
						 */
							mod.eliminaUltimaEntita();
							break;
						}
					}
					case 6: 
						System.out.println(mod.toString()); 
						break;
					
					case 7:   
					if(e.getRami()[i].isEmpty())
						/*
					 	* Se c'e' gia' un ramo vuoto inserito e il ramo corrente e' vuoto, non esce dal menu' 
					 	* e stampa a video un errore.
						*/
						if(ramoVuotoPresente) {
							System.out.println(MSG_ERRORE_RAMI);
							break;
						}
						else {
							ramoVuotoPresente = true;
							esci = true;
							break;
						}
					else {
						esci = true;
						break;
					}
				
					default : System.out.println(MSG_ERRORE);	
					}
				} while(!esci);
			} 
			switch(tipo) {
			case OPZ_BRANCH: System.out.println(String.format(MSG_CHIUSURA_BRANCH, e.getNome(), e.getId(), e.getId())); break;
			case OPZ_CICLO: System.out.println(String.format(MSG_CHIUSURA_CICLO, e.getId())); break;
			case OPZ_FORK: System.out.println(String.format(MSG_CHIUSURA_FORK, e.getNome(), e.getId(), e.getId())); break;
			}
			decrementaRientro();
	}
	
	/**
	 * Inserimento azione.
	 *
	 * @param e : l'entita' esterna alla quale deve essere aggiunta l'azione. Se non c'e' alcun 
	 * livello di annidamento, questa e' il modello stesso, altrimenti e' un'entita' complessa.
	 * 
	 * @param qualeRamo : il ramo dell'entita' complessa sul quale aggiungere l'entita'. Se non 
	 * c'e' alcun livello di annidamento, puo' assumere un valore qualsiasi
	 */
	private void inserimentoAzione (Entita e, int qualeRamo) {
		/*
		 * PRECONDIZIONI : l'entita' e non deve essere nulla. Inoltre se il tipo dell'entita' esterna
		 * e' relativo ad una delle entita' complesse, allora l'intero relativo al ramo deve essere
		 * compreso tra 0 e il numero di rami di e meno uno. 
		 */
		assert e!=null : MSG_ERRORE_PRECONDIZIONE_2;
		assert e.getIdTipo().equals(Entita.ID_TIPO_MODELLO) || ((e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO) || e.getIdTipo().equals(Entita.ID_TIPO_FORK) && (qualeRamo >= 0 && qualeRamo < e.getRami().length))) : MSG_ERRORE_PRECONDIZIONE_2 ;
		boolean presente = false;
		String t;
		do {
			t = Util.leggiString(MSG_TITOLO_AZIONE);
			presente = mod.giaPresente(t);
			if(presente)
				System.out.println(MSG_DUPLICATO);
		} while(presente==true);
		boolean composta = Util.yesOrNo(MSG_COMPOSTA_SI_NO);
		Azione action = new Azione(t,composta);
		if(action.isComposta()) {
			String modelloAzione = Util.leggiString(MSG_MODELLO_COMPOSTA);
			action.setModelloComposta(modelloAzione);
		}
		e.addEntita(action, qualeRamo);
		mod.addAzione(action);
		System.out.println(String.format(MSG_NUOVA_ENTITA,action.getNome(),e.getNome()));
	}
	
	/**
	 * Inserimento branch.
	 *
	 * @param e : l'entita' esterna alla quale deve essere aggiunto il branch. Se non c'e' alcun 
	 * livello di annidamento, questa e' il modello stesso, altrimenti e' un'entita' complessa.
	 * 
	 * @param qualeRamo : il ramo dell'entita' complessa sul quale aggiungere il branch. Se non 
	 * c'e' alcun livello di annidamento, puo' assumere un valore qualsiasi
	 */
	private void inserimentoBranch(Entita e, int qualeRamo) {
		/*
		 * PRECONDIZIONI : l'entita' e non deve essere nulla. Inoltre se il tipo dell'entita' esterna
		 * e' relativo ad una delle entita' complesse, allora l'intero relativo al ramo deve essere
		 * compreso tra 0 e il numero di rami di e meno uno. 
		 */
		assert e!=null : MSG_ERRORE_PRECONDIZIONE_3;
		assert e.getIdTipo().equals(Entita.ID_TIPO_MODELLO) || ((e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO) || e.getIdTipo().equals(Entita.ID_TIPO_FORK) && (qualeRamo >= 0 && qualeRamo < e.getRami().length))) : MSG_ERRORE_PRECONDIZIONE_3 ;
		String t;
		Boolean presente = false;
		do {
			t = Util.leggiString(MSG_TITOLO_BRANCH);
			presente = mod.giaPresente(t);
			if(presente)
				System.out.println(MSG_DUPLICATO);
		} while(presente==true);
		int n = Util.leggiIntConMinimo(MSG_NUM_RAMI_BRANCH, MIN_RAMI);
		incrementaRientro();
		Branch b = new Branch(t, n);
		for (int i=0; i<b.getNumeroRami(); i++)
			b.getRami()[i] = new Ramo();
		e.addEntita(b, qualeRamo);
		menuInserimentoSecondario(b,OPZ_BRANCH); 
		System.out.println(String.format(MSG_NUOVA_ENTITA,b.getNome(),e.getNome()));
	}
	
	/**
	 * Inserimento ciclo.
	 *
	 * @param e : l'entita' esterna alla quale deve essere aggiunto il ciclo. Se non c'e' alcun 
	 * livello di annidamento, questa e' il modello stesso, altrimenti e' un'entita' complessa.
	 * 
	 * @param qualeRamo : il ramo dell'entita' complessa sul quale aggiungere il ciclo. Se non 
	 * c'e' alcun livello di annidamento, puo' assumere un valore qualsiasi
	 */
	private void inserimentoCiclo(Entita e, int qualeRamo) {
		/*
		 * PRECONDIZIONI : l'entita' e non deve essere nulla. Inoltre se il tipo dell'entita' esterna
		 * e' relativo ad una delle entita' complesse, allora l'intero relativo al ramo deve essere
		 * compreso tra 0 e il numero di rami di e meno uno. 
		 */
		assert e!=null : MSG_ERRORE_PRECONDIZIONE_4;
		assert e.getIdTipo().equals(Entita.ID_TIPO_MODELLO) || ((e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO) || e.getIdTipo().equals(Entita.ID_TIPO_FORK) && (qualeRamo >= 0 && qualeRamo < e.getRami().length))) : MSG_ERRORE_PRECONDIZIONE_4 ;

		String t;
		Boolean presente = false;
		do {
			t = Util.leggiString(MSG_TITOLO_CICLO);
			presente = mod.giaPresente(t);
			if(presente)
				System.out.println(MSG_DUPLICATO);
		} while(presente==true);
		incrementaRientro();
		Ciclo c = new Ciclo(t);
		for (int i=0; i<c.getNumeroRami(); i++)
			c.getRami()[i] = new Ramo();
		e.addEntita(c, qualeRamo);
		menuInserimentoSecondario(c,OPZ_CICLO);
		System.out.println(String.format(MSG_NUOVA_ENTITA,c.getNome(),e.getNome()));
	}
	
	/**
	 * Inserimento fork.
	 *
	 * @param e : l'entita' esterna alla quale deve essere aggiunto il fork. Se non c'e' alcun 
	 * livello di annidamento, questa e' il modello stesso, altrimenti e' un'entita' complessa.
	 * 
	 * @param qualeRamo : il ramo dell'entita' complessa sul quale aggiungere il fork. Se non 
	 * c'e' alcun livello di annidamento, puo' assumere un valore qualsiasi
	 */
	private void inserimentoFork(Entita e, int qualeRamo) {
		/*
		 * PRECONDIZIONI : l'entita' e non deve essere nulla. Inoltre se il tipo dell'entita' esterna
		 * e' relativo ad una delle entita' complesse, allora l'intero relativo al ramo deve essere
		 * compreso tra 0 e il numero di rami di e meno uno. 
		 */
		assert e!=null : MSG_ERRORE_PRECONDIZIONE_5;
		assert e.getIdTipo().equals(Entita.ID_TIPO_MODELLO) || ((e.getIdTipo().equals(Entita.ID_TIPO_BRANCH) || e.getIdTipo().equals(Entita.ID_TIPO_CICLO) || e.getIdTipo().equals(Entita.ID_TIPO_FORK) && (qualeRamo >= 0 && qualeRamo < e.getRami().length))) : MSG_ERRORE_PRECONDIZIONE_5 ;

		String t;
		Boolean presente = false;
		do {
			t = Util.leggiString(MSG_TITOLO_FORK);
			presente = mod.giaPresente(t);
			if(presente)
				System.out.println(MSG_DUPLICATO);
		} while(presente==true);
		int n = Util.leggiIntConMinimo(MSG_NUM_RAMI_FORK, MIN_RAMI);
		incrementaRientro();
		Fork temp = new Fork(t, n);
		for (int i=0; i<temp.getNumeroRami(); i++)
			temp.getRami()[i] = new Ramo();
		e.addEntita(temp, qualeRamo);
		menuInserimentoSecondario(temp,OPZ_FORK);
		System.out.println(String.format(MSG_NUOVA_ENTITA,temp.getNome(),e.getNome()));
	}
	
	/**
	 * Inserimento nodo finale.
	 */
	private void inserimentoNodoFinale () {
		// PRECONDIZIONE : il modello non deve essere nullo
		assert mod!=null : MSG_ERRORE_PRECONDIZIONE_6;
		NodoFinale nodo_f = new NodoFinale();
		mod.addEntita(nodo_f);
		System.out.println(String.format(MSG_NODO_FINALE,mod.getNome()));
	}
	
	/**
	 * Incrementa rientro.
	 */
	private static void incrementaRientro() {
		rientro = rientro + FATTORE_INCREMENTO;
	}
	
	/**
	 * Decrementa rientro.
	 */
	private static void decrementaRientro() {
		rientro = rientro - FATTORE_INCREMENTO;
	}
	
	/**
	 * Fornisce il valore del rientro.
	 *
	 * @return : il rientro
	 */
	public static int getRientro () {
		return rientro;
	}
	
	/**
	 * Setta il rientro.
	 *
	 * @param val : il valore da dare al rientro
	 */
	private static void setRientro (int val) {
		rientro = val;
	}
}