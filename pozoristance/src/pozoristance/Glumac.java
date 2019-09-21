package pozoristance;

public class Glumac extends Zaposleni {
	public Glumac(String ime, Pozoriste pozoriste) {
		super(ime, pozoriste);
	}

	@Override
	public String imePosla() {
		return "Glumac";
	}

	public char jednoslovnaOznakaPosla() {
		return imePosla().charAt(0);
	}

	
}
