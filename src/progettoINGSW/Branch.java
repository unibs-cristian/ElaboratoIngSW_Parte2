package progettoINGSW;

import java.util.Vector;
import Utilita.*;

public class Branch extends Entita {

	public final static String MSG_BRANCH = "%d\n";
	public final static String id_tipo = "BRANCH";
	
	private static int contatoreBranch = 0;
	private int numeroRami;
	private int idBranch;
	private Merge mergeAssociato;
	private Vector <RamoBranch> elencoRami;
	
	public Branch (int _numeroRami) {
		super(id_tipo);
		numeroRami = _numeroRami;
		contatoreBranch++;
		idBranch = contatoreBranch;
		mergeAssociato = null;
		elencoRami = new Vector<RamoBranch>();
	}
	
	public int getIdBranch() {
		return idBranch;
	}
	
	public int getNumeroRami() {
		return numeroRami;
	}
	
	public int getContatoreBranch() {
		return contatoreBranch;
	}
	
	public Merge getMerge() {
		return mergeAssociato;
	}
	
	public void setMerge(Merge m) {
		mergeAssociato = m;
	}
	
	public Vector <RamoBranch> getElencoRami() {
		return elencoRami;
	}
	
	public void aggiungiRamo(RamoBranch rbr) {
		elencoRami.addElement(rbr);
	}
	
	public String toString()
	{
		StringBuffer risultato = new StringBuffer();
		risultato.append(CORNICE);
		risultato.append(String.format(MSG_ENTITA, super.getId(), id_tipo));
		risultato.append(String.format(MSG_BRANCH,idBranch));
		risultato.append(super.toString());
		return risultato.toString();
	}
}
