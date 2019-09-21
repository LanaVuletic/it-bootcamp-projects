package prodavnicaMuzike;

import java.time.LocalDate;

public class Album {
	private String naziv;
	private String izvodjac;
	private LocalDate datum;
	private MuzickaNumera[] nizPesama;
	private int brojac;

	public Album(String naziv, String izvodjac, LocalDate datum) {
		this.naziv = naziv;
		this.izvodjac = izvodjac;
		this.datum = datum;
		nizPesama = new MuzickaNumera[5];
		brojac = 0;

	}

	public void dodaj(MuzickaNumera mn) {
		if (brojac < nizPesama.length) {
			nizPesama[brojac] = mn;
			brojac++;
		} else {
			MuzickaNumera[] noviNiz = new MuzickaNumera[nizPesama.length + 5];
			for (int i = 0; i < nizPesama.length; i++) {
				noviNiz[i] = nizPesama[i];
			}
			nizPesama = noviNiz;

			nizPesama[brojac] = mn;
			brojac++;
		}

	}

	public void dodaj(String name, String trajanje) {
		MuzickaNumera numera = new MuzickaNumera(name, izvodjac, trajanje);

		dodaj(numera);
	}

	public String getNaziv() {
		return naziv;
	}

	public String getIzvodjac() {
		return izvodjac;
	}

	public String getTrajanje() {
		int ukupnoMin = 0;
		int ukupnoSek = 0;

		for (int i = 0; i < nizPesama.length; i++) {
			if (nizPesama[i] != null) {
				String[] secko = nizPesama[i].getTrajanje().split(":");

				ukupnoMin += Integer.parseInt(secko[0]);
				ukupnoSek += Integer.parseInt(secko[1]);
			}
		}
		ukupnoMin += ukupnoSek / 60;

		ukupnoSek %= 60;

		String ukupnoTrajanje = Integer.toString(ukupnoMin) + ":" + Integer.toString(ukupnoSek);
		return ukupnoTrajanje;

	}

	public LocalDate getDatum() {
		return datum;
	}

	public MuzickaNumera getMuzickanNumera(int index) {
		return nizPesama[index];
	}

	public MuzickaNumera getMuzickanNumera(String ime) {
		for (int i = 0; i < nizPesama.length; i++) {
			if (nizPesama[i].getNaziv().equals(ime) == true) {
				return nizPesama[i];
			}
		}

		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(izvodjac + " - " + naziv + "(" + datum + "): [\n");
		for (int i = 0; i < nizPesama.length; i++) {
			if (nizPesama[i] != null) {
				sb.append("\t" + nizPesama[i] + "\n");
			}

		}
		return sb.toString();

	}
}
