package controlloCammino;

import testSuiteDiagnosi.CamminoAzioni;

public interface StatoCammino {
	public final static String ENTRATO_RAMO = "ENTRATO RAMO";
	public final static String FERMATO = "FERMATO";
	public final static String FERMATO_DENTRO = "FERMATO DENTRO";
	public final static String NON_PERCORSO = "RAMO NON PERCORSO";
	public final static String PERCORSO_PARZ = "RAMO PERCORSO PARZIALMENTE";
	public final static String PERCORSO_TUTTO = "RAMO PERCORSO TUTTO";
	public final static String STATO_OK = "OK";
	public final static String STATO_NON_OK = "NON VALIDO";
	public final static String STATO_VUOTO = "VUOTO";
	public final static String SUPERATO_FORK = "SUPERATO FORK";
	
	public void gestisciStato(CamminoAzioni camm, String stato);
	
	public String getStringaStato();
	
	public boolean isValid();

}
