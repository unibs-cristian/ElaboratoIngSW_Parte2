package progettoINGSW;
import java.util.Vector;
import Utilita.*;

public class RamoBranch {
	private Vector <Entita> entitaRamo;
	private String descrCondizione;    //Descrizione testuale della condizione
	private boolean condizione;        //Dice se la condizione è vera o falsa (servirà più avanti nei cammini...)
	
	public RamoBranch(String _descrCondizione)
	{
		entitaRamo = new Vector<Entita>();
		descrCondizione = _descrCondizione;
		condizione = false;    //da modificare quando servirà...
	}
	
	public Vector<Entita> getEntitaRamo() {
		return entitaRamo;
	}
	
	public void aggiungiEntitaRamo(Entita daAggiungere) {
		entitaRamo.addElement(daAggiungere);
	}
	
	public String getDescrizione() {
		return descrCondizione;
	}
	
	public boolean getCondizione() {
		return condizione;
	}
}
