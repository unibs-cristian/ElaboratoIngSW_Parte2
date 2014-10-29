package gestioneModello;
import java.util.*;

public interface Entita {
	
	public final static String SPAZIO = " ";
	public final static String MSG_ENTITA_RIMOSSA = "E' stata rimossa l'entita' %s (id = %d)";
	public final static String ID_TIPO_AZIONE = "AZ";
	public final static String ID_TIPO_BRANCH = "BR";
	public final static String ID_TIPO_CICLO = "CI";
	public final static String ID_TIPO_FORK = "FO";
	public final static String ID_TIPO_MODELLO = "MO";
	public final static String ID_TIPO_NODO_FINALE = "NF";
	public final static String ID_TIPO_NODO_INIZIALE = "NI";
	
	public void addEntita(Entita e, int qualeRamo);
	
	public Entita cercaId(int idDaCercare);
	
	public int getId();
	
	public Vector <Entita> getEntita();
	
	public String getNome();
	
	public Ramo[] getRami();
	
	public int getIndentazione();
	
	public boolean rimuoviEntitaAt(int id);
	
	public String toString();
	
	public String getIdTipo();
}

