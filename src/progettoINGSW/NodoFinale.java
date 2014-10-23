package progettoINGSW;

import Utilita.*;

public class NodoFinale extends Entita 
{
	public final static String id_tipo = "NODO_FINALE";
	
	public NodoFinale(int id) {
	
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
