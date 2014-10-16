import java.util.*;

public class Flusso extends Entita {

	public final static String id_tipo = "FL";
	private boolean scattato = false;
	
	public Flusso (int id) {
		
		super(id_tipo, id);
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
