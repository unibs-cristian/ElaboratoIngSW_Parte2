package progettoINGSW;

public class NodoIniziale extends Entita {

	public final static String id_tipo = "NODO_INIZIALE";
	
	public NodoIniziale() {

		super(id_tipo);
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(CORNICE);
		risultato.append(String.format(MSG_ENTITA, super.getId(), id_tipo));
		risultato.append("\n");
		risultato.append(super.toString());
		return risultato.toString();
	}
}
