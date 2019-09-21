package pozoristance;

import java.util.ArrayList;

public class Predstava {
	private String naziv;
	private Pozoriste pozoriste;
	private ArrayList<Zaposleni> zaposleni;
	private int brojKostimografa;

	public Predstava(String naziv, Pozoriste pozoriste, Reditelj reditelj) {
		this.naziv = naziv;
		this.pozoriste = pozoriste;
		this.zaposleni = new ArrayList<Zaposleni>();
		this.zaposleni.add(reditelj);
		this.brojKostimografa = 0;
	}
	
	public boolean dodaj(Glumac glumac) {	
		this.zaposleni.add(glumac);
		return true;
	}
	
	public boolean dodaj(Kostimograf kostimograf) {	
		
		if (!kostimograf.getPozoriste().getNaziv().equals(pozoriste.getNaziv()) || brojKostimografa == 2){
			return false;
		}
		this.zaposleni.add(kostimograf);
		this.brojKostimografa++;
		return true;
	}
	
	

	public String getNaziv() {
		return naziv;
	}

	public Pozoriste getPozoriste() {
		return pozoriste;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv + ": ");
		sb.append("\n\t");
		for (Zaposleni z : zaposleni) {
			sb.append(z + "\n\t");
		}
		return sb.toString();
	}

}
