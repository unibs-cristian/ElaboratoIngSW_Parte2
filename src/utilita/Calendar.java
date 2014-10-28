package utilita;

/**
 * Svariati metodi per il calcolo degli anni.
 * @author Lorenzo Rubagotti.
 */
public class Calendar
{
	private static int anno;
	private static final int INIZIO_CICLO = 9;
	private static final int DURATA_CICLO = 28;
	
	/**
	 * Costruttore che richiede l'anno.
	 * @param _anno Anno richiesto.
	 */
	public Calendar (int _anno)
	{
		anno= _anno;
	}
	
	/**
	 * @return Restituisce il numero di ciclo solare.
	 */
	public int cicloSolare ()
	{
		return 1 + ( (anno + INIZIO_CICLO) / DURATA_CICLO);
	}
}
