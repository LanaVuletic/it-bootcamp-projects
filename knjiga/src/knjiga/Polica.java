package knjiga;

public class Polica {
	private Knjiga[] nizKnjiga;
	private int brojKnjiga;

	public Polica() {
		nizKnjiga = new Knjiga[10];
		brojKnjiga = 0;
	}

	public void dodajKnjiguNaPolicu(Knjiga k) {
		if (brojKnjiga < nizKnjiga.length) {
			nizKnjiga[brojKnjiga] = k;
			brojKnjiga++;
		}
	}

	public Knjiga uzmiKnjiguZadatogNaziva(String nazivKnjige) {
		for (int i = 0; i < nizKnjiga.length; i++) {
			if (nizKnjiga[i] != null && nizKnjiga[i].getNazivKnjige().equals(nazivKnjige)) {
				return nizKnjiga[i];
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nizKnjiga.length; i++) {
			if (nizKnjiga[i] != null) {
				sb.append(nizKnjiga[i] + "\n");
			}
		}
		return sb.toString();
	}

	public Knjiga[] getNizKnjiga() {
		return nizKnjiga;
	}

}
