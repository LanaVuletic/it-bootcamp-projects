package ocena;


public class Ocena {
	private int brojPoena;
	private String nazivPredmeta;
	private String rok;

	public Ocena(int brojPoena, String nazivPredmeta, String rok) {

		this.brojPoena = brojPoena;
		this.nazivPredmeta = nazivPredmeta;
		this.rok = rok;
	}

	public int getOcena() {

		if (brojPoena < 50) {
			return 5;
		} else if (brojPoena < 60) {
			return 6;
		} else if (brojPoena < 70) {
			return 7;
		} else if (brojPoena < 80) {
			return 8;
		} else if (brojPoena < 90) {
			return 9;
		} else
			return 10;

	}
	
	public static double getProsek(Ocena[] nizOcena ) {	
		int zbirOcena = 0;
		int prelazneOcene = 0;
		
		for(int i =0; i<nizOcena.length; i++) {
			if (nizOcena[i].getOcena() > 5) {
			zbirOcena += nizOcena[i].getOcena();
			prelazneOcene++;
			}
		}
		
		return zbirOcena/prelazneOcene;
	}

	public int getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public String getRok() {
		return rok;
	}

	public void setRok(String rok) {
		this.rok = rok;
	}

}
