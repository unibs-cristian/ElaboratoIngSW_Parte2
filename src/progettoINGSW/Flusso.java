package progettoINGSW;

import Utilita.*;

public class Flusso extends Entita {

	public final static String ID_TIPO = "FL";
	private boolean scattato = false;
	
	public Flusso (int id) {
		
		super(ID_TIPO);
		
	}

	public void setScattato () {
		
		if(scattato == false)
			scattato = true;
		else
			scattato = false;
	}
	
	public boolean getScattato () {
		
		return scattato;
	}

}
