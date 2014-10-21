package progettoINGSW;
import java.util.*;

public class Entita {
	
	public final static String CORNICE = "\n-----------------------------------------------------------\n";
	public final static String MSG_ENTITA = "Entita' n. %d    %s";
	public final static String MSG_ENTITA_PRECEDENTI = "Entita' precedenti: \n";
	public final static String MSG_ENTITA_SUCCESSIVE = "\nEntita' successive: \n";
	
	private String id_tipo;
	private static int contatore = 0;
	private int id;
	private Vector <Entita> entitaPrecedenti;
	private Vector <Entita> entitaSuccessive;
	private RamoBranch ramoBr;
	
	public Entita (String id_tipo) {
		
		this.id_tipo = id_tipo;
		contatore++;
		id=contatore;
		entitaPrecedenti = new Vector <Entita> ();
		entitaSuccessive = new Vector <Entita> ();
		ramoBr = null;
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
	
	public RamoBranch getRamoBranch() {
		return ramoBr;
	}
	
	public void setBranchAssociato(RamoBranch rbr) {
		ramoBr = rbr;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append(MSG_ENTITA_PRECEDENTI);
		if(entitaPrecedenti.isEmpty())
			risultato.append("Nessuna.");
		else
		{		
			for (int i=0; i<entitaPrecedenti.size(); i++) {
				Entita ePrec = entitaPrecedenti.elementAt(i);
				risultato.append("- "+String.format(MSG_ENTITA,ePrec.getId(),ePrec.getIdTipo()));
			}
		}
		risultato.append("\n");
		risultato.append(MSG_ENTITA_SUCCESSIVE);
		if(entitaSuccessive.isEmpty())
			risultato.append("Nessuna\n");
		else
		{
			for (int i=0; i<entitaSuccessive.size(); i++) {		
				Entita eSucc = entitaSuccessive.elementAt(i);
				risultato.append("- "+String.format(MSG_ENTITA,eSucc.getId(),eSucc.getIdTipo()));   
			}
		}
		return risultato.toString();
	}
}
