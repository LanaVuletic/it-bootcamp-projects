package osoba;

public abstract class Osoba {
	protected String ime;
	protected String prezime;
	protected String adresaStanovanja;
	
	public Osoba (String ime, String prezime, String adresaStanovanja) {
		this.ime = ime;
		this.prezime = prezime;
		this.adresaStanovanja = adresaStanovanja;
	}
	
	public Osoba(Osoba o) {
		this.ime = o.ime;
		this.prezime = o.prezime;
		this.adresaStanovanja = o.adresaStanovanja;
	}
	
	public abstract int numeroloskiBroj();
	
	public abstract String metabolizam(final Datum d);

	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ime: " + ime + " || Prezime " + prezime + " || Adresa: " + adresaStanovanja);
		
		return sb.toString();
		
	}

}
