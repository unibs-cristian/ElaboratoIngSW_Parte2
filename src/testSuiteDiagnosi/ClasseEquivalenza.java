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

	public ClasseEquivalenza(int _cardinalita, CamminoAzioni _cammGlob) {
		cardinalita = _cardinalita;
		camminoGlobale = _cammGlob;
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
	
	public Coppia getCoppiaAt(int index) {
		return elencoCoppie.elementAt(index);
	}
	
	public int getNumeroCoppie() {
		return elencoCoppie.size();
	}
	
	public CamminoAzioni getCamminoGlobale() {
		return camminoGlobale;
	}
	
	public void setCamminoGlobale(CamminoAzioni cammGlob) {
		camminoGlobale = cammGlob;
	}
	
	public String toString() {
		StringBuffer risultato = new StringBuffer();
		risultato.append("Cammino Globale --> " + camminoGlobale.toString() + "\n");
		for(int i=0; i<getNumeroCoppie(); i++) {
			risultato.append(String.format("Coppia n.%d\n" + getCoppiaAt(i).toString(),i+1));
		}
		
		return risultato.toString();
	}
}
