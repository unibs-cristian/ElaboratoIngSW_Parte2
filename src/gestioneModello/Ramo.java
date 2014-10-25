package gestioneModello;

import java.util.Vector;

import Utilita.GUI;


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
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		for(int i=0; i<entitaRamo.size(); i++) 
			risultato.append(GUI.indenta(entitaRamo.elementAt(i).toString(), Entita.SPAZIO, entitaRamo.elementAt(i).getIndentazione()));
		return risultato.toString();
	}
}
