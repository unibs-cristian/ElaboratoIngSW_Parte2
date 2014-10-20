import java.util.*;

public class Modello {
	
	public final static String MSG_TITOLO_AZIONE = "\nDigitare il titolo dell'azione che si sta inserendo: ";
	public final static String MSG_DESCRIZIONE_AZIONE = "Fornire una breve descrizione dell'azione che si sta inserendo: ";
	
	private String nome;
	private String descrizione;
	public Vector <Entita> elencoEntita;

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
	
	public Entita getUltimaEntita()
	{
		int numeroEntita = elencoEntita.size()-1;
		return elencoEntita.elementAt(numeroEntita);
	}
	
	public int getNumeroAzioni() {
		int contatore_Azioni = 0;
		int numero_entita = elencoEntita.size();   //Si può mettere il metodo in fase di refactoring al posto della variabile locale.
		for (int i=0; i<numero_entita; i++)
		{
			Entita e = elencoEntita.elementAt(i);
			if (e.getIdTipo()=="AZ" || e.getIdTipo()=="AZC")
				contatore_Azioni++;
		}
		return contatore_Azioni;
	}
	
	public void aggiungiEntita (Entita entita) {
		
		elencoEntita.add(entita);
	}
	
	public void creaNodoIniziale () {
		
		NodoIniziale nodo_i = new NodoIniziale();
		aggiungiEntita(nodo_i);
	}
	
	public void creaAzione () {
		String tit = UtilitaGenerale.leggiString(MSG_TITOLO_AZIONE);
		String descr = UtilitaGenerale.leggiString(MSG_DESCRIZIONE_AZIONE);
		Azione action = new Azione(tit,descr);
		aggiungiEntita(action);
		//Aggiornamento successori dell'entità precedente
		Entita prec = elencoEntita.elementAt(elencoEntita.size()-2);
		prec.setEntitaSuccessiva(action);
		//Settaggio dei predecessori dell'entità appena inserita
		action.setEntitaPrecedente(elencoEntita.elementAt(elencoEntita.size()-2));
	}
	
	/*public void creaFlusso (Entita entitaPrecedenteBranch, Entita entitaSuccessiva) {
		
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
	} */
	
	public void creaNodoFinale () {
		
		NodoFinale nodo_f = new NodoFinale(1);
		aggiungiEntita(nodo_f);
	}
	
	public int creaMerge() {
		
	//	for
	return 1;
	}
	
	public boolean isEmpty() {
		return elencoEntita.isEmpty();
	}
	
	public boolean nodoFinalePresente() {
		Entita e = getUltimaEntita();
		if(e.getIdTipo() == "NF")
			return true;
		else 
			return false;
	}
	
	public String toString() {
		
		StringBuffer risultato = new StringBuffer();
		System.out.println("\nNOME MODELLO : "+nome);
		System.out.println("DESCRIZIONE MODELLO : "+descrizione+"\n\n");
		
		for(int i=0; i<elencoEntita.size(); i++) {
			Entita e = elencoEntita.get(i);
			risultato.append(e.toString());
		}  
		return risultato.toString();
	} 
}