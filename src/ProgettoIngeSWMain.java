
public class ProgettoIngeSWMain {

	public final static String TITOLO_PRINCIPALE = "Menu Principale";
	public final static String[] OPZIONI_PRINCIPALE = {"Inserimento nuovo modello", "Carica modello"};
	public final static String TITOLO_INSERIMENTO_MODELLO = "Menu Inserimento"; 
	
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
		
	public static void menuInserimento() {};
	
	public static void caricamentoModello() {};
		
}
