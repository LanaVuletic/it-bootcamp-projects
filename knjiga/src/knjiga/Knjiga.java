package knjiga;

public class Knjiga {
	private String nazivKnjige;
	private List[] nizListova;
	private int brojListova;

	public Knjiga(String nazivKnjige) {
		this.nazivKnjige = nazivKnjige;
		nizListova = new List[5];
		this.brojListova = 0;
	}

	public String getNazivKnjige() {
		return nazivKnjige;
	}

	public void dodajList(List l) {
		for (int i = 0; i < nizListova.length; i++) {
			if (nizListova[i] != null && nizListova[i].poredjenjeListova(l) == true) {
				nizListova[i] = l;
				return;
			}
		}

		if (brojListova < nizListova.length) {
			nizListova[brojListova] = l;
			brojListova++;
		}
	}

	public int dohvatiBrojListovaUKnjizi() {
		return brojListova;
	}

	public List dohvatiListSaZadatimRednimBrojem(int redniBroj) {
		for (int i = 0; i < nizListova.length; i++) {
			if (nizListova[i].getRedniBroj() == redniBroj) {
				return nizListova[i];
			}
		}

		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nazivKnjige + "\n");
		sortirajNizListova();
		for (int i = 0; i < nizListova.length; i++) {
			if (nizListova[i] != null) {
				sb.append("\n\t" + nizListova[i] + "\n");
			}
		}
		return sb.toString();
	}

	public void sortirajNizListova() {
		for (int i = 0; i < nizListova.length - 1; i++) {
			for (int j = i + 1; j < nizListova.length; j++) {
				if ((nizListova[i] != null && nizListova[j] != null)
						&& nizListova[j].getRedniBroj() < nizListova[i].getRedniBroj()) {
					List temp = nizListova[j];
					nizListova[j] = nizListova[i];
					nizListova[i] = temp;
				}
			}
		}
	}

	public List[] getNizListova() {
		return this.nizListova;
	}
}
