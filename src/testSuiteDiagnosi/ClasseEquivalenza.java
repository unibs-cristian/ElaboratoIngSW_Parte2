package testSuiteDiagnosi;
import java.util.Vector;

public class ClasseEquivalenza {

	private int cardinalita;
	/** Attributo che contiene le coppie (Insieme del cammino, valore della rilevazione */
	private Vector<Coppia> elencoCoppie;

	public ClasseEquivalenza(int _cardinalita) {
		cardinalita = _cardinalita;
		elencoCoppie = new Vector<Coppia>(); 
	}
	
	public int getCardinalita() {
		return cardinalita;
	}
	
	public Vector <Coppia> getElencoCoppie() {
		return elencoCoppie;
	}
}
