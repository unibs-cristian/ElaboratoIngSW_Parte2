
public class ProgettoIngeSWMain {
	public final static int MIN_RAMI = 2;
	public final static String MSG_NUM_RAMI = "Inserire il numero di rami d'uscita del branch (minimo 2): \n";
	public final static String CORNICE = "\n----------------------------------------------------------------------------";
	public final static String TITOLO_MENU_PRINCIPALE = "Menu Principale";
	public final static String TITOLO_INSERIMENTO_MODELLO = "Menu Inserimento"; 
	public final static String MSG_NOME_MODELLO = "\nInserire il nome del nuovo modello: ";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello: ";
	public final static String MSG_BENVENUTO = "Benvenuto! Questo programma ti consente di inserire e caricare modelli.";
	public final static String MSG_SALUTO = "Grazie per aver usato il nostro programma! A presto.\n";
	public final static String MSG_USCITA_PROGRAMMA = "Esci dal programma.\n";
	public final static String MSG_ERRORE = "L'opzione inserita è inesistente. Inserire un'opzione valida.\n";
	public final static String MSG_NODO_FINALE_PRESENTE = "Attenzione! L'ultima entità inserita è un nodo finale.\nImpossibile inserire nuove entità.\nEliminare il nodo finale per poter inserire nuovamente.\n";
	public final static String MSG_INSERIMENTO_AZIONE = "Inserisci una nuova azione.";
	public final static String MSG_INSERIMENTO_BRANCH = "Inserisci un nuovo branch.";
	public final static String MSG_INSERIMENTO_FORK = "Inserisci un nuovo fork.";
	public final static String MSG_INSERIMENTO_MERGE = "Inserisci il merge relativo.\n";
	public final static String MSG_INSERIMENTO_JOIN = "Inserisci il join relativo al fork.\n";
	public final static String MSG_MODIFICA_MODELLO = "Modifica il modello.";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = "Visualizza il modello.";
	public final static String MSG_USCITA_INSERIMENTO = "Torna al menu principale.\n";
	public final static String MSG_NUOVO_MODELLO = "Crea nuovo modello";
	public final static String MSG_CARICAMENTO_MODELLO = "Carica modello";
	public final static String MSG_INSERIMENTO_NODO_FINALE = "Inserisci il nodo finale.";
	public final static String MSG_MODELLO_INCOMPLETO = "Attenzione! Per inserire il nodo finale è necessario che nel modello sia\npresente almeno un'azione.\n";	
	public final static String MSG_COND_BRANCH = "Scrivere la condizione relativa al ramo %d del branch.\n";
	
	public static void main(String[] args) {
		benvenuto();
		
		Menu menuPrincipale = new Menu(TITOLO_MENU_PRINCIPALE);
		menuPrincipale.addVoce(MSG_NUOVO_MODELLO);
		menuPrincipale.addVoce(MSG_CARICAMENTO_MODELLO);
		menuPrincipale.addVoce(MSG_USCITA_PROGRAMMA);
		
		boolean finito = false;
		do {
			switch(menuPrincipale.scegli()) {
				case 1:
					String nome_modello = UtilitaGenerale.leggiString(MSG_NOME_MODELLO);
					String descrizione_modello = UtilitaGenerale.leggiString(MSG_DESCRIZIONE_MODELLO);
					Modello m = new Modello(nome_modello, descrizione_modello);
					m.creaNodoIniziale();
					inserimentoEntita(m);
					break;
				case 2: System.out.println("Funzionalità non ancora implementata.\n"); break;
				case 3: finito = true; break;
				default : System.out.println(MSG_ERRORE); break;
			}
		} while(finito == false);
		saluta();
	}
		
	public static void inserimentoEntita(Modello m) {
		Menu menuInserimentoEntita = new Menu(TITOLO_INSERIMENTO_MODELLO);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_AZIONE);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_BRANCH);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_FORK);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_NODO_FINALE);
		menuInserimentoEntita.addVoce(MSG_MODIFICA_MODELLO);
		menuInserimentoEntita.addVoce(MSG_VISUALIZZAZIONE_MODELLO);
		menuInserimentoEntita.addVoce(MSG_USCITA_INSERIMENTO);
		
		boolean insFinito = false;
		do {
			switch(menuInserimentoEntita.scegli()) {
				case 1: 
					if(!(m.nodoFinalePresente()))
					{
							m.creaAzione();
							break;
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
				case 2: 
					if(!(m.nodoFinalePresente()))
					{
					//	creaBranch(m);       //Arrivato qui (da implementare)
						break;
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
			/*	case 3:
					if(!(m.nodoFinalePresente()))
					{
						m.creaFork();
						break
					}
					else
					{ 
						System.out.println(MSG_NODO_FINALE_PRESENTE); 
						break; */
				case 4: 
					if(!(m.nodoFinalePresente()))
						if(m.getNumeroAzioni()>=1)
						{
							m.creaNodoFinale();
							break;
						}
						else
						{
							System.out.println(MSG_MODELLO_INCOMPLETO);
							break;
						}
					else 
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
				case 5: System.out.println("Funzionalita non ancora implementata.\n"); break;
				case 6: System.out.println(m.toString()); break;
				case 7: insFinito = true; break;	
				default : System.out.println(MSG_ERRORE);
			}
		} while(!insFinito);
	};
	
	/* public static void creaBranch(Modello m) {
		int numRami = UtilitaGenerale.leggiInteroConLimite1(MSG_NUM_RAMI, MIN_RAMI);
		Branch b = new Branch(numRami);
		//Creazione rami
		for(int i=1; i<=numRami; i++)
		{
			String cond = UtilitaGenerale.leggiString(MSG_COND_BRANCH);
			RamoBranch r = new RamoBranch(b, cond);
			
		}
		//Aggiornamento successori dell'entità precedente     (da rivedere in fase di refactoring. Si ripete e può essere messo in classe Modello)
		
		Entita prec = elencoEntita.elementAt(elencoEntita.size()-2);
		prec.setEntitaSuccessiva(b);
		//Settaggio dei predecessori dell'entità appena inserita
		b.setEntitaPrecedente(elencoEntita.elementAt(elencoEntita.size()-2));
		int elementoPrecedente = elencoEntita.size()-1;
		return elementoPrecedente;
	}    */
	
//	public static void caricamentoModello() {};
	public static void benvenuto()
	{
		System.out.println(CORNICE+"\n");
		System.out.println(MSG_BENVENUTO);
	}
	
	public static void saluta()
	{
		System.out.println(CORNICE);
		System.out.print(MSG_SALUTO);
	}
}