package gestioneModello;
import java.io.Serializable;

import java.util.Vector;

import utilita.GUI;

public class Ramo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Vector <Entita> entitaRamo;        
	
	public Ramo()
	{
		entitaRamo = new Vector<Entita>();  
	}
	
	public Vector<Entita> getEntitaRamo() {
		return entitaRamo;
	}
	 
	public void aggiungiEntitaRamo(Entita daAggiungere) {
		entitaRamo.addElement(daAggiungere);
	}
	
	public boolean isEmpty() {
		return entitaRamo.isEmpty();
	}
	
	public void eliminaEntitaRamo(int i) {
		entitaRamo.remove(i);
	}
	
	public Vector <Azione> getAzioniRamo() {
		Vector <Azione> risultato = new Vector<Azione>();
		for(int i=0; i<entitaRamo.size(); i++) 
			if(entitaRamo.elementAt(i).getIdTipo().equals(Entita.ID_TIPO_AZIONE))
				risultato.add((Azione)entitaRamo.elementAt(i));
		return risultato;
	}
	
	public int getNumeroEntita() {
		return entitaRamo.size();
	}
	
	public Entita getEntitaAt(int index) {
		return entitaRamo.elementAt(index);
	}
	
	//TODO togliere se non serve
	public boolean presente(Entita e) {
		for(int i=0; i<getNumeroEntita(); i++)
			if(getEntitaAt(i).getNome().equals(e.getNome()))
				return true;
		return false;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		for(int i=0; i<entitaRamo.size(); i++) 
			risultato.append(GUI.indenta(entitaRamo.elementAt(i).toString(),Entita.SPAZIO,entitaRamo.elementAt(i).getIndentazione()));
		return risultato.toString();
	}
}
