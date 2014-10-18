import java.util.Vector;

public class Branch extends Entita {

	public final static String id_tipo = "BRANCH";
	
	private static int contatoreBranch = 0;
	private int numeroRami;
	private int idBranch;
	private Vector <RamoBranch> elencoRami;
	
	public Branch (int _numeroRami) {
		super(id_tipo);
		numeroRami = _numeroRami;
		contatoreBranch++;
		idBranch = contatoreBranch;
		elencoRami = new Vector<>();
	}
	
	public int getIdBranch() {
		return idBranch;
	}
	
	public int getNumeroRami() {
		return numeroRami;
	}
	
	public Vector <RamoBranch> getElencoRami() {
		return elencoRami;
	}
}
