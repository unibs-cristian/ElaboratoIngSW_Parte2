package progettoINGSW;

import Utilita.*;

public class Merge extends Entita {

	public final static String id_tipo = "MERGE";
	public final static String MSG_MERGE = "%d\n";
	
	private Branch branchAssociato;
	private int id_merge;
	
	public Merge(Branch _branchAssociato) {
		super(id_tipo);
		branchAssociato = _branchAssociato;
		id_merge = branchAssociato.getIdBranch();
	}
	
	public Branch getBranchAssociato() {
		return branchAssociato;
	}
	
	public int getIdMerge() {
		return id_merge;
	}

	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(CORNICE);
		risultato.append(String.format(MSG_ENTITA, super.getId(), id_tipo));
		risultato.append(String.format(MSG_MERGE,getIdMerge()));
		risultato.append(super.toString());
		return risultato.toString();
	}
}
