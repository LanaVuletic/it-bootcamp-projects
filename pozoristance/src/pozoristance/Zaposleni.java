package pozoristance;

public abstract class Zaposleni {
	private String ime;
	private Pozoriste pozoriste;
	
	public Zaposleni(String ime, Pozoriste pozoriste) {
		this.ime = ime;
		this.pozoriste = pozoriste;
	}

	public String getIme() {
		return ime;
	}

	public Pozoriste getPozoriste() {
		return pozoriste;
	}
	
	public abstract String imePosla();
	
	public abstract char jednoslovnaOznakaPosla();
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t");
		sb.append(getIme());
		sb.append(" (" + imePosla() + ", ");
		sb.append(pozoriste.getSkraceniNaziv() + ") ");
		return sb.toString();
		
	}
	
	

}
