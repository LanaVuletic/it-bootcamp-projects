package pozoristance;

public class Pozoriste {
	private static int globalID = 0;
	private int id;
	private String naziv;

	public Pozoriste(String naziv) {
		this.naziv = naziv;
		id = globalID;
		globalID++;
		// id = ++globalID;
	}

	public int getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getSkraceniNaziv() {
		String pozoriste = getNaziv();
		String[] niz = pozoriste.split(" ");

		String skraceniNaziv = "";

		for (int i = 0; i < niz.length; i++) {
			skraceniNaziv += niz[i].charAt(0);
		}

		return skraceniNaziv.toUpperCase();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getNaziv() + " [" + getId() + "] ");
		return sb.toString();
	}

}
