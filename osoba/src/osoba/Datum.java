package osoba;

public class Datum {
	public int dan;
	public int mesec;
	public int godina;
	
	
	public Datum(int dan, int mesec, int godina) {
		super();
		this.dan = dan;
		this.mesec = mesec;
		this.godina = godina;
	}
	
	public Datum(Datum d) {
		this.dan = d.dan;
		this.mesec = d.mesec;
		this.godina = d.godina;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

	public int getMesec() {
		return mesec;
	}

	public void setMesec(int mesec) {
		this.mesec = mesec;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(izracunajDatum(dan) + "." + izracunajDatum(mesec) + "." + godina + ".");
				
		return sb.toString();
	}
	
	private String izracunajDatum(int datum) {
		String povratniDatum = "";
		
		if(datum <= 9) {
			povratniDatum = "0" + datum;
		}
		else {
			povratniDatum = Integer.toString(datum);
		}
		
		return povratniDatum;
	}

}
