import java.util.Vector;


public class ProgettoIngeSWMain {

	public final static String CORNICE = "---------------------------------------------------\n";
	public final static String TITOLO_MENU_PRINCIPALE = "Menu Principale";
	public final static Vector<String> OPZIONI_PRINCIPALE = new Vector<>();
	public final static String TITOLO_INSERIMENTO_MODELLO = "Menu Inserimento"; 
	public final static String MSG_NOME_MODELLO = "Inserire il nome del nuovo modello :\n";
	public final static String MSG_DESCRIZIONE_MODELLO = "Inserire una sintetica descrizione del modello :\n";
	
	public final static String OPZIONE_PRINCIPALE_1 = "Crea nuovo modello";
	public final static String OPZIONE_PRINCIPALE_2 = "Carica modello";
	
	public static void main(String[] args) {
		
		// INIZIALIZZAZIONE VOCI MENU OPZIONI_PRINCIPALE
		OPZIONI_PRINCIPALE.add(OPZIONE_PRINCIPALE_1);
		OPZIONI_PRINCIPALE.add(OPZIONE_PRINCIPALE_2);
		Menu menuPrincipale = new Menu(TITOLO_MENU_PRINCIPALE);
		
		boolean finito = false;
		do {
		
			switch(menuPrincipale.scegli()) {
			
				case 0: finito = true; break;
				case 1:
					String nome_modello = UtilitaGenerale.leggiString(MSG_NOME_MODELLO);
					String descrizione_modello = UtilitaGenerale.leggiString(MSG_DESCRIZIONE_MODELLO);
					Modello m = new Modello(nome_modello, descrizione_modello);
					m.creaNodoIniziale();
					inserimentoEntita(m);
			  //case 2: caricamentoModello();
					default : System.out.println("Errore non previsto.");
			}
		} while(!finito);
	}
		
	public static void inserimentoEntita(Modello m) {
		Menu menuInserimentoEntita = new Menu(TITOLO_INSERIMENTO_MODELLO);
		boolean insFinito = false;
		do {
			menuInserimentoEntita.ottieniOpzioniDisponibili();  //(arrivato qui) da implementare!!
			switch(menuInserimentoEntita.scegli()) {
			
				case 0: insFinito = true; break;
				case 1:
					
			  //case 2: caricamentoModello();
					default : System.out.println("Errore non previsto.");
			}
		} while(!insFinito);
	};
	
//	public static void caricamentoModello() {};
}
