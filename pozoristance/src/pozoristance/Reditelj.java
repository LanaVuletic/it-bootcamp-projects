package pozoristance;

public class Reditelj extends Zaposleni {
	public Reditelj(String ime, Pozoriste pozoriste) {
		super(ime, pozoriste);
	}

	@Override
	public String imePosla() {
		return "Reditelj";
	}
	
	public char jednoslovnaOznakaPosla() {
		return imePosla().charAt(0);
	}
	
	
}
