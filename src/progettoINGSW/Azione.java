package progettoINGSW;

public class Azione extends Entita {
	
	public final static String id_tipo = "AZIONE";
	public final static String MSG_TITOLO_AZIONE = "%d\nTitolo azione : %s\n";
	public final static String MSG_DESCR_AZIONE = "Descrizione : %s\n";
	private String titolo;
	private String descrizione;
	private static int contatoreAzioni = 0;
	private int idAzione;
	
	public Azione(String _titolo, String _descrizione) {
		
		super(id_tipo);
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
		risultato.append(CORNICE);
		risultato.append(String.format(MSG_ENTITA, super.getId(), id_tipo));
		risultato.append(String.format(MSG_TITOLO_AZIONE,idAzione,titolo));
		risultato.append(super.toString());
		return risultato.toString();
	}
}
