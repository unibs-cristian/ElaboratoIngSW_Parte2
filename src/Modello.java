import java.util.*;

public class Modello {

	private String nomeModello;
	private String descrizioneModello;
	public Vector <Entita> elencoElementi;
	private int idAzione = 0;
	private int idFlusso = 0;
	private int idBranch = 0;
	
	public Modello () {
		
		elencoElementi = new Vector<Entita>();
	}
	
	public void aggiungiEntita (Entita entita) {
		
		elencoElementi.add(entita);
	}
	
	public void creaNodoIniziale () {
		
		NodoIniziale nodo_i = new NodoIniziale(0);
		aggiungiEntita(nodo_i);
		
		System.out.println("Nodo Iniziale creato con ID: "+nodo_i.getIdTipo()+nodo_i.getId());
	}
	
	public void creaAzione (Entita elementoBranch) {
		
		Azione action = new Azione(idAzione);
		idAzione++;
		aggiungiEntita(action);
		
		System.out.println("Azione creata con ID: "+action.getIdTipo()+action.getId());
		creaFlusso(elementoBranch, action);
	}
	
	public void creaFlusso (Entita entitaPrecedenteBranch, Entita entitaSuccessiva) {
		
		Flusso flux = new Flusso(idFlusso);
		idFlusso++;
		
		if(entitaPrecedenteBranch == null) {		
			int elementoPrecedente = elencoElementi.size()-2;
			Entita entitaPrecedente = elencoElementi.get(elementoPrecedente);
			entitaPrecedente.setEntitaSuccessiva(entitaSuccessiva);
			entitaSuccessiva.setEntitaPrecedente(entitaPrecedente);
		}
		else {
			
			entitaPrecedenteBranch.setEntitaSuccessiva(entitaSuccessiva);
			entitaSuccessiva.setEntitaPrecedente(entitaPrecedenteBranch);
		}
	}
	
	public void creaNodoFinale () {
		
		NodoFinale nodo_f = new NodoFinale(1);
		aggiungiEntita(nodo_f);
		
		System.out.println("Nodo Finale creato con ID: "+nodo_f.getIdTipo()+nodo_f.getId());
		creaFlusso(null, nodo_f);
	}
	
	public int creaBranch() {
		
		int elementoPrecedente = elencoElementi.size()-1;
		return elementoPrecedente;
	}
	
	/*
	public int creaMerge() {
		
	//	for
	return 1;
	}
	*/
	
	public void stampaModello () {
		
		System.out.println();
		
		for(int i=0; i<elencoElementi.size(); i++) {
			
			Entita e = elencoElementi.get(i);
			System.out.println();
			System.out.println("-- ID Entita': "+e.getIdTipo()+e.getId()+" --");
			System.out.print("Precedenti: ");
			for (int j=0; j<e.getEntitaPrecedenti().size(); j++) {
				
				Entita ePrec = e.getEntitaPrecedenti().get(j);
				System.out.print(""+ePrec.getIdTipo()+ePrec.getId()+"\t");
			}
			System.out.println("");
			System.out.print("Successivi: ");
			for (int j=0; j<e.getEntitaSuccessive().size(); j++) {
				
				Entita ePrec = e.getEntitaSuccessive().get(j);
				System.out.println(""+ePrec.getIdTipo()+ePrec.getId()+"\t");
			}	
		}
	}
}
