package muzika;

public class MuzickaNumera {
	
	private String naziv;
	private String izvodjac;
	private String trajanje;
	
	public MuzickaNumera (String naziv, String izvodjac, String trajanje) {
		this.naziv = naziv;
		this.izvodjac = izvodjac;
		this.trajanje = trajanje;
	}
	
	public String getNaziv () {
		return naziv;
	}

	public String getIzvodjac() {
		return izvodjac;
	}

	public String getTrajanje() {
		return trajanje;
	}
	
	public String toString() {
		String s = " ";
		s = s + getIzvodjac() + " - " + getNaziv() + ": " + getTrajanje();
		return s;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public void setIzvodjac(String izvodjac) {
		this.izvodjac = izvodjac;
	}

	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}

}
