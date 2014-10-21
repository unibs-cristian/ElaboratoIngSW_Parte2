package progettoINGSW;

public class Azione extends Entita {
	
	public final static String ID_TIPO = "AZIONE";
	public final static String MSG_TITOLO_AZIONE = "Titolo azione : %s\n";
	public final static String MSG_DESCR_AZIONE = "Descrizione : %s\n";
	private String titolo;
	private String descrizione;
	private static int contatoreAzioni = 0;
	private int idAzione;
	
	public Azione(String _titolo, String _descrizione) {
		
		super(ID_TIPO);
		titolo = _titolo;
		descrizione = _descrizione;
		contatoreAzioni++;
		idAzione = contatoreAzioni;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public int getIdAzione() {
		return idAzione;
	}
	
	public void setTitolo(String aTitolo) {
		titolo = aTitolo;
	}
	
	public void setDescr(String aDescr) {
		descrizione = aDescr;
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(super.toString());
		risultato.append(String.format(MSG_TITOLO_AZIONE,titolo));
		risultato.append(String.format(MSG_DESCR_AZIONE,descrizione));
		return risultato.toString();
	}
}