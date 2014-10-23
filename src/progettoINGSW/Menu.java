package progettoINGSW;
import java.util.Vector;

// Classe che crea e stampa il menu' e ritorna un intero corrispondente all'opzione scelta.


 public class Menu 
 
{
	public final static String CORNICE = "\n---------------------------------------------------------------------------------";
	public final static String RICHIESTA_INSERIMENTO = "Selezione > ";
	
	private String titolo;
	private Vector <String> voci;
		
	public Menu(String _titolo, Vector<String> _voci)
	{
		titolo = _titolo;
		voci = _voci;
	}
		
	public int scegli()
	{
		stampa();
		return (UtilitaGenerale.leggiInteroConLimite2(RICHIESTA_INSERIMENTO, 0, voci.size()));
	}
		
	private void stampa()
	{
		System.out.println(CORNICE);
		System.out.print(titolo);
		System.out.println(CORNICE);
		for(int i = 0; i < voci.size(); i++)
		{
			System.out.println((i + 1) + " --> " + voci.get(i));
		}		
	}	
}