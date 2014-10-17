import java.util.*;

public class Modello {

	private String nome;
	private String descrizione;
	public Vector <Entita> elencoEntita;
	private int idAzione = 0;
	private int idFlusso = 0;
	private int idBranch = 0;
	
	public Modello (String _nome, String _descrizione) {
		nome = _nome;
		descrizione = _descrizione;
		elencoEntita = new Vector<Entita>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public Vector <Entita> getElencoEntita() {
		return elencoEntita;
	}
	
	public void aggiungiEntita (Entita entita) {
		
		elencoEntita.add(entita);
	}
	
	public void creaNodoIniziale () {
		
		NodoIniziale nodo_i = new NodoIniziale();
		aggiungiEntita(nodo_i);
	}
	
	public void creaAzione (Entita elementoBranch) {
		
		Azione action = new Azione(idAzione);
		idAzione++;
		aggiungiEntita(action);
		creaFlusso(elementoBranch, action);
	}
	
	public void creaFlusso (Entita entitaPrecedenteBranch, Entita entitaSuccessiva) {
		
		Flusso flux = new Flusso(idFlusso);
		idFlusso++;
		
		if(entitaPrecedenteBranch == null) {		
			int elementoPrecedente = elencoEntita.size()-2;
			Entita entitaPrecedente = elencoEntita.get(elementoPrecedente);
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
		
		int elementoPrecedente = elencoEntita.size()-1;
		return elementoPrecedente;
	}
	
	
	public int creaMerge() {
		
	//	for
	return 1;
	}
	
	
	public void stampaModello () {
		
		System.out.println();
		
		for(int i=0; i<elencoEntita.size(); i++) {
			
			Entita e = elencoEntita.get(i);
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
