package prodavnicaMuzike;

public class Prodavnica {
	private String imeProdavnice;
	private String lokacija;
	private Artikal[] inventar;
	int brojac;

	public Prodavnica(String imeProdavnice, String lokacija) {
		inventar = new Artikal[10];
		this.imeProdavnice = imeProdavnice;
		this.lokacija = lokacija;
		brojac = 0;
	}

	public String getImeProdavnice() {
		return imeProdavnice;
	}

	public String getLokacija() {
		return lokacija;
	}

	public Artikal[] pretrazi(String tekst) {
		Artikal[] noviNiz = new Artikal[prebrojArtikle(tekst)];
		int brojacNNiza = 0;
		for (int i = 0; i < inventar.length; i++) {
			if (inventar[i] != null && inventar[i].ime().toLowerCase().contains(tekst.toLowerCase())) {
				noviNiz[brojacNNiza] = inventar[i];
				brojacNNiza++;
			}
		}

		return noviNiz;
	}

	public int prebrojArtikle(String tekst) {
		int brArtikla = 0;

		for (int i = 0; i < inventar.length; i++) {
			if (inventar[i] != null && inventar[i].ime().toLowerCase().contains(tekst.toLowerCase())) {
				brArtikla++;
			}
		}

		return brArtikla;
	}

	public void dodaj(Artikal art) {
		if (brojac < inventar.length) {
			inventar[brojac] = art;
			brojac++;
		} else {
			Artikal[] noviNiz = new Artikal[inventar.length * 2];
			for (int i = 0; i < inventar.length; i++) {
				noviNiz[i] = inventar[i];
			}
			inventar = noviNiz;

			inventar[brojac] = art;
			brojac++;
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(imeProdavnice + ": " + lokacija + " [" + "\n");
		for (int i = 0; i < inventar.length; i++) {
			if (inventar[i] != null) {
				sb.append("\t" + inventar[i]);
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
