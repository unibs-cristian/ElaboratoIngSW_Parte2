package gestioneModello;

import java.util.Vector;

import utilita.GUI;
public class Ramo {
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
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		for(int i=0; i<entitaRamo.size(); i++) 
			risultato.append(GUI.indenta(entitaRamo.elementAt(i).toString(),Entita.SPAZIO,entitaRamo.elementAt(i).getIndentazione()));
		return risultato.toString();
	}
}