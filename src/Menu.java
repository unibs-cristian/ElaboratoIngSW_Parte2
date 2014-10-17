import java.util.Vector;

// Classe che crea e stampa il menu' e ritorna un intero corrispondente all'opzione scelta.

public class Menu 
{
	public final static String CORNICE = "*************************************";
	public final static String VOCE_USCITA = "0 --> Esci";
	public final static String RICHIESTA_INSERIMENTO = "Inserisci il numero corrispondente all'opzione desiderata: ";
		
	private String titolo;
	private Vector<String> voci;
		
	public Menu(String _titolo)
	{
		titolo = _titolo;
		voci = new Vector<>();
	}
		
	public int scegli()
	{
		stampa();
		return (UtilitaGenerale.leggiInteroConLimite(RICHIESTA_INSERIMENTO, 0, voci.size()));
	}
		
	private void stampa()
	{
		System.out.println(CORNICE);
		System.out.println(titolo);
		System.out.println(CORNICE);
		for(int i = 0; i < voci.size(); i++)
		{
			System.out.println((i + 1) + " --> " + voci.get(i));
		}
		System.out.println();
		System.out.println(VOCE_USCITA);
		System.out.println();		
	}	
}
