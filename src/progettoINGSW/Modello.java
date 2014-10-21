package progettoINGSW;
import java.util.*;

public class Modello {
	
	public final static String TITOLO_INSERIMENTO_MODELLO = "Menu Inserimento";
	public final static String MSG_INSERIMENTO_AZIONE = "Inserisci una nuova azione.";
	public final static String MSG_INSERIMENTO_BRANCH = "Inserisci un nuovo branch.";
	public final static String MSG_INSERIMENTO_FORK = "Inserisci un nuovo fork.";
	public final static String MSG_INSERIMENTO_MERGE = "Inserisci un merge.";
	public final static String MSG_INSERIMENTO_JOIN = "Inserisci un join.";
	public final static String MSG_INSERIMENTO_NODO_FINALE = "Inserisci il nodo finale.";
	public final static String MSG_NODO_FINALE_PRESENTE = "Attenzione! L'ultima entita'† inserita e' un nodo finale.\nImpossibile inserire nuove entit√†.\nEliminare il nodo finale per poter inserire nuovamente.\n";
	public final static String MSG_MODIFICA_MODELLO = "Modifica il modello.";
	public final static String MSG_VISUALIZZAZIONE_MODELLO = "Visualizza il modello.";
	public final static String MSG_USCITA_INSERIMENTO = "Torna al menu principale.\n";
	public final static String MSG_TITOLO_AZIONE = "\nDigitare il titolo dell'azione che si sta inserendo: ";
	public final static String MSG_DESCRIZIONE_AZIONE = "Fornire una breve descrizione dell'azione che si sta inserendo: ";
	public final static String MSG_NUM_RAMI = "Inserire il numero di rami d'uscita del branch (minimo 2): ";
	public final static String MSG_COND_BRANCH = "Scrivere la condizione relativa al ramo %d del branch.";
	public final static String MSG_MODELLO_INCOMPLETO = "Attenzione! Per inserire il nodo finale e' necessario che nel modello sia\npresente almeno un'azione.\n";	
	public final static String MSG_ERRORE = "L'opzione inserita e' inesistente. Inserire un'opzione valida.\n";
	public final static String MSG_RAMO_BRANCH = "Ramo numero ";
	public final static String MSG_ERRORE_MERGE = "Impossibile inserire il merge. Nessun branch da chiudere.\n";
	public final static String MSG_ERRORE_MERGE_2 = "Errore! Impossibile chiudere il merge. Nessuna entita'† tra il merge e il branch da chiudere.\n";
	public final static String MSG_INSERIMENTO_ENTITA_RAMO = "\nInserire le entita'†relative al ramo n∞ ";
	public final static int MIN_RAMI = 2;
	
	private String nome;
	private String descrizione;
	public Vector <Entita> elencoEntita;
	public Vector <Branch> elencoBranch;
	
	public Modello (String _nome, String _descrizione) {
		nome = _nome;
		descrizione = _descrizione;
		elencoEntita = new Vector<Entita>();
		elencoBranch = new Vector<Branch>();
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
	
	public Vector <Branch> getElencoBranch() {
		return elencoBranch;
	}
	
	public Entita getUltimaEntita()
	{
		int numeroEntita = elencoEntita.size()-1;
		return elencoEntita.elementAt(numeroEntita);
	}
	
	public Branch getUltimoBranch()
	{
		int numeroBranch = elencoBranch.size()-1;
		return elencoBranch.elementAt(numeroBranch);
	}
	
	public int getNumeroAzioni() {
		int contatore_Azioni = 0;
		int numero_entita = elencoEntita.size();   //Si pu√≤ mettere il metodo in fase di refactoring al posto della variabile locale.
		for (int i=0; i<numero_entita; i++)
		{
			Entita e = elencoEntita.elementAt(i);
			if (e.getIdTipo()==Azione.id_tipo)
				contatore_Azioni++;
		}
		return contatore_Azioni;
	}
	
	private void aggiungiEntita (Entita entita) {
		
		elencoEntita.add(entita);
	}
	
	private void aggiungiBranch (Branch b) {
		
		elencoBranch.add(b);
	}
	
/*	public void aggiornaEntitaCircostanti() {
		//Aggiornamento successori dell'entit√† precedente
		Entita prec = elencoEntita.elementAt(elencoEntita.size()-2);
		prec.setEntitaSuccessiva(action);
		//Settaggio dei predecessori dell'entit√† appena inserita
		action.setEntitaPrecedente(elencoEntita.elementAt(elencoEntita.size()-2));
	}
*/	
	public void creaNodoIniziale () {
		
		NodoIniziale nodo_i = new NodoIniziale();
		aggiungiEntita(nodo_i);
	}
	
	public void creaAzione (RamoBranch ramoCorrente) {
		String tit = UtilitaGenerale.leggiString(MSG_TITOLO_AZIONE);
		String descr = UtilitaGenerale.leggiString(MSG_DESCRIZIONE_AZIONE);
		Azione action = new Azione(tit,descr);
		aggiungiEntita(action);
		if(ramoCorrente == null) {	
			aggiornaPredSucc(action);
		}
		else ramoCorrente.aggiungiEntitaRamo(action);
	}
	
	public void creaBranch(RamoBranch ramoCorrente) {
		int numRami = UtilitaGenerale.leggiInteroConLimite1(MSG_NUM_RAMI, MIN_RAMI);
		Branch b = new Branch(numRami);
		aggiungiEntita(b);
		aggiungiBranch(b);
		b.setEntitaPrecedente(elencoEntita.elementAt(elencoEntita.size()-2));
		//Creazione rami
		for(int i=1; i<=numRami; i++)
		{
			System.out.println(MSG_RAMO_BRANCH + i +"\n");
			String cond = UtilitaGenerale.leggiString(String.format(MSG_COND_BRANCH,i));
			RamoBranch r = new RamoBranch(cond);
			b.aggiungiRamo(r);
			System.out.println(MSG_INSERIMENTO_ENTITA_RAMO + i + "\n");
			menuInserimentoEntita(r);
			if(ramoCorrente == null)
			{
				Vector <Entita> entitaRamoR = r.getEntitaRamo();
				Entita primaEntitaRamo = entitaRamoR.elementAt(0);
				b.setEntitaSuccessiva(primaEntitaRamo);
				primaEntitaRamo.setEntitaPrecedente(b);
				
				Entita ultimaEntitaRamo = entitaRamoR.elementAt(entitaRamoR.size()-1);
				Merge m = b.getMerge();
				ultimaEntitaRamo.setEntitaSuccessiva(m);
				m.setEntitaPrecedente(ultimaEntitaRamo);
				
				if(entitaRamoR.size()>=2)
				{
					for(int j=2; j<entitaRamoR.size(); j++)
					{
						Entita e = entitaRamoR.elementAt(j);
						aggiornaPredSucc(e);
					}
				}
			}
			else 
				ramoCorrente.aggiungiEntitaRamo(b);
		}
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
		aggiornaPredSucc(nodo_f);
	}
	
	public void creaMerge(Branch brAssociato) {
		Merge m = new Merge(brAssociato);
		aggiungiEntita(m);
		
		//I predecessori del merge sono le ultime entita'† di ciascun ramo.
		Vector <RamoBranch> rami = brAssociato.getElencoRami();
		for (int i=0; i<rami.size(); i++)
		{
			RamoBranch r = rami.elementAt(i);
			Vector <Entita> entitaRamo = r.getEntitaRamo();
			Entita ultimaEntitaRamo = entitaRamo.elementAt(entitaRamo.size()-1);
			m.setEntitaPrecedente(ultimaEntitaRamo);
		}
	}
	
	public void menuInserimentoEntita(RamoBranch ramoCorrente) {
		Menu menuInserimentoEntita = new Menu(TITOLO_INSERIMENTO_MODELLO);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_AZIONE);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_BRANCH);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_MERGE);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_FORK);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_JOIN);
		menuInserimentoEntita.addVoce(MSG_INSERIMENTO_NODO_FINALE);
		menuInserimentoEntita.addVoce(MSG_MODIFICA_MODELLO);
		menuInserimentoEntita.addVoce(MSG_VISUALIZZAZIONE_MODELLO);
		menuInserimentoEntita.addVoce(MSG_USCITA_INSERIMENTO);
		
		boolean insFinito = false;
		do {
			switch(menuInserimentoEntita.scegli()) {
				case 1:     //Inserimento azione.
					if(!(nodoFinalePresente()))
					{
						creaAzione(ramoCorrente);
						break;
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
				case 2:    //Inserimento branch
					if(!(nodoFinalePresente()))
					{
						if(getNumeroAzioni()>=1)            
						{
							creaBranch(ramoCorrente);      
							break;
						}
						else
						{
							System.out.println(MSG_MODELLO_INCOMPLETO);
							break;
						}
					}
					else
					{
						System.out.println(MSG_NODO_FINALE_PRESENTE);
						break;
					}
				case 3:   //inserimento merge
					if(!(nodoFinalePresente()))
					{
						Branch ultimoBranchAperto = trovaBranchDaChiudere();
						if(ultimoBranchAperto!=null)
						{
							if(ultimoBranchAperto.getId()!=getUltimaEntita().getId())
							{
								creaMerge(ultimoBranchAperto);
								insFinito = true;
								break;
							}
							else
							{
								System.out.println(MSG_ERRORE_MERGE_2);
								break;
							}
							
						}
						else
							System.out.println(MSG_ERRORE_MERGE);
						break;
					}
					else
					{ 
						System.out.println(MSG_NODO_FINALE_PRESENTE); 
						break; 
					}	
				case 6: 
					if(!(nodoFinalePresente()))
						if(getNumeroAzioni()>=1)
						{
							creaNodoFinale();
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
				case 7: System.out.println("Funzionalita non ancora implementata.\n"); break;
				case 8: System.out.println(toString()); break;
				case 9: insFinito = true; break;	
				default : System.out.println(MSG_ERRORE);
			}
		} while(!insFinito);
	};
	
	private void aggiornaPredSucc(Entita e) {
	//Aggiornamento successori dell'entit√† precedente
	Entita prec = elencoEntita.elementAt(elencoEntita.size()-2);
	prec.setEntitaSuccessiva(e);
	//Settaggio dei predecessori dell'entit√† appena inserita
	e.setEntitaPrecedente(elencoEntita.elementAt(elencoEntita.size()-2));
	}
	
	//Trova l'ultimo branch aperto (branch a cui non √® associato alcun merge).
	public Branch trovaBranchDaChiudere() {    
		if (elencoBranch.isEmpty())
		{
			return null;
		}
		Branch ultimoBranch = elencoBranch.elementAt(elencoBranch.size()-1);
		if(ultimoBranch.getMerge()==null)
		{
			return ultimoBranch;
		}
		else
			return null;
	}
	
	public boolean nodoFinalePresente() {
		Entita e = getUltimaEntita();
		if(e.getIdTipo() == NodoFinale.id_tipo)
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
