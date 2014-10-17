import java.util.*;

public class Entita {

	private String id_tipo;
	private static int id;
	private Vector <Entita> entitaPrecedenti;
	private Vector <Entita> entitaSuccessive;
	
	public Entita (String id_tipo) {
		
		this.id_tipo = id_tipo;
		id++;
		entitaPrecedenti = new Vector <Entita> ();
		entitaSuccessive = new Vector <Entita> ();
	}
	
	public String getIdTipo () {
		
		return id_tipo;
	}
	
	public int getId () {
		
		return id;
	}
	
	public void setEntitaPrecedente (Entita e) {
		
		entitaPrecedenti.add(e);
	}
	
	public void setEntitaSuccessiva (Entita e) {
		
		entitaSuccessive.add(e);
	}
	
	public Vector <Entita> getEntitaPrecedenti() {
	
		return entitaPrecedenti;
	}
	
	public Vector <Entita> getEntitaSuccessive() {
		
		return entitaSuccessive;
	}
	
}