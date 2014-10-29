package testSuiteDiagnosi;
import java.util.Vector;
import gestioneModello.Entita;

public class ClasseEquivalenza {

	/** La cardinalita' della classe di equivalenza */
	private int cardinalita;
	
	private Vector <Entita> camminoGlobale;
	
	/** Attributo che contiene le coppie (Insieme del cammino, valore della rilevazione) */
	private Vector<Coppia> elencoCoppie;

	public ClasseEquivalenza(int _cardinalita) {
		cardinalita = _cardinalita;
		camminoGlobale = new Vector<Entita>();
		elencoCoppie = new Vector<Coppia>(); 
	}
	 
	public int getCardinalita() {
		return cardinalita;
	}
	
	public void addCoppia(Coppia daAggiungere) {
		elencoCoppie.add(daAggiungere);
	}
	
	public Vector <Coppia> getElencoCoppie() {
		return elencoCoppie;
	}
}
