package gestioneModello;
import java.util.*;

public interface Entita {
	
	public final static String SPAZIO = " ";
	public final static int INCREMENTO_INDENTAZIONE = 2;
	
	public void addEntita(Entita e, int qualeRamo);
	
	public int getId();
	
	public Vector <Entita> getEntita();
	
	public String getNome();
	
	public Ramo[] getRami();
	
	public int getIndentazione();
	
	public String toString();
}

