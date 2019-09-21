package prodavnicaMuzike;

public abstract class Artikal {
	private static int globalID = 1;
	protected int id;
	private double cena;
	private int kolicina;

	public Artikal(double cena, int kolicina) {
		this.cena = cena;
		this.kolicina = kolicina;
		this.id = globalID;
		globalID++;
	}


	
	public int getId() {
		return id;
	}

	public double getCena() {
		return cena;
	}

	public int getKolicina() {
		return kolicina;
	}

	public boolean kupi() {
		if (kolicina > 0) {
			kolicina--;
			return true;
		}

		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("#" + id + ": " + ime() + " - " + cena + " [kol: " + kolicina + "]" + "\n");

		return sb.toString();
	}

	public abstract String ime();

}
