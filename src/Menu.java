// Classe che crea e stampa il menù e ritorna un intero corrispondente all'opzione scelta.

public class Menu 
{
	private final static String CORNICE = "*************************************";
	private final static String VOCE_USCITA = "0 --> Esci";
	private final static String RICHIESTA_INSERIMENTO = "Inserisci il numero corrispondente all'opzione desiderata: ";
		
	private String titolo;
	private String [] voci;
		
	public Menu(String _titolo, String [] _voci)
	{
		titolo = _titolo;
		voci = _voci;
	}
		
	public int scegli()
	{
		stampa();
		return (UtilitaGenerale.leggiInteroConLimite(RICHIESTA_INSERIMENTO, 0, voci.length));
	}
		
	private void stampa()
	{
		System.out.println(CORNICE);
		System.out.println(titolo);
		System.out.println(CORNICE);
		for(int i = 0; i < voci.length; i++)
		{
			System.out.println((i + 1) + " --> " + voci[i]);
		}
		System.out.println();
		System.out.println(VOCE_USCITA);
		System.out.println();		
	}	
}
