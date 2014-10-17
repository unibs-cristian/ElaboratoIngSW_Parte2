import java.util.Vector;


public class ProgettoIngeSWMain {

	public final static String TITOLO_PRINCIPALE = "Menu Principale";
	public final static Vector<String> OPZIONI_PRINCIPALE = new Vector();
	public final static String TITOLO_INSERIMENTO_MODELLO = "Menu Inserimento"; 
	
	public final static String OPZIONE_PRINCIPALE_1 = "Crea nuovo modello";
	public final static String OPZIONE_PRINCIPALE_2 = "Carica modello";
	
	public static void main(String[] args) {
	
		Modello mod = new Modello();
		/*mod.creaNodoIniziale();
		mod.creaAzione(null);
		mod.creaAzione(null);
		int b = mod.creaBranch();
		//SOPRA
		mod.creaAzione(mod.elencoElementi.get(b));
		mod.creaAzione(null);
		//MEZZO
		mod.creaAzione(mod.elencoElementi.get(b));
		//SOTTO
		mod.creaAzione(mod.elencoElementi.get(b));
		mod.creaAzione(null);
		mod.creaMerge();
		mod.creaNodoFinale();
		mod.stampaModello();
	*/
		
		// INIZIALIZZAZIONE VOCI MENU OPZIONI_PRINCIPALE
		OPZIONI_PRINCIPALE.add(OPZIONE_PRINCIPALE_1);
		OPZIONI_PRINCIPALE.add(OPZIONE_PRINCIPALE_2);
		
		boolean finito = false;
		
		Menu menuPrincipale = new Menu(TITOLO_PRINCIPALE, OPZIONI_PRINCIPALE);
		
		do {
		
			switch(menuPrincipale.scegli()) {
			
				case 0: finito = true; break;
				case 1: menuInserimento();
				case 2: caricamentoModello();
			}
		} while(!finito);
	}
		
	public static void menuInserimento() {
		
		Menu menuInserimento = new Menu(TITOLO_INSERIMENTO_MODELLO, calcoloPossibilita());
	};
	
	public static void caricamentoModello() {};
	
	private Vector<String> calcoloPossibilita() {
		
		String[] sceltePossibili = new String[50];
	}
		
}
