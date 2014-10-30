package testSuiteDiagnosi;
import java.util.Vector;
import gestioneModello.Entita;

public class ClasseEquivalenza {

	/** La cardinalita' della classe di equivalenza */
	private int cardinalita;
	
	/** Cammino di esecuzione globale relativo alla classe di equivalenza */
	private CamminoAzioni camminoGlobale;
	
	/** Attributo che contiene le coppie (Insieme del cammino, valore della rilevazione) */
	private Vector<Coppia> elencoCoppie;

	public ClasseEquivalenza(int _cardinalita) {
		cardinalita = _cardinalita;
		elencoCoppie = new Vector<Coppia>(); 
	}
	 
	public void addCoppia(Coppia daAggiungere) {
		elencoCoppie.add(daAggiungere);
	}
	
	public int getCardinalita() {
		return cardinalita;
	}
	
	public Vector <Coppia> getElencoCoppie() {
		return elencoCoppie;
	}
	
	public CamminoAzioni getCamminoGlobale() {
		return camminoGlobale;
	}
	
	public void setCamminoGlobale(CamminoAzioni cammGlob) {
		camminoGlobale = cammGlob;
	}
}
