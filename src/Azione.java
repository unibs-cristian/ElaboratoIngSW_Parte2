import java.util.*;

public class Azione extends Entita {
	
	public final static String id_tipo = "AZ";
	
	public Azione(int id) {
		
		super(id_tipo, id);
	}
	
	public int narobabella()
	{
		return 1;
	}
}
