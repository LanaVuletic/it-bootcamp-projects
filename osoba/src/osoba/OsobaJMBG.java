package osoba;

public class OsobaJMBG extends Osoba {
	private String JMBG;

	public OsobaJMBG(String ime, String prezime, String adresaStanovanja, String JMBG) {
		super(ime, prezime, adresaStanovanja);
		this.JMBG = JMBG;

	}

	public OsobaJMBG(OsobaJMBG ojmbg) {
		super(ojmbg);
		JMBG = ojmbg.JMBG;
	}

	public String getJMBG() {
		return JMBG;
	}

	@Override
	public int numeroloskiBroj() {
		int sum = 0;

		String milenijalac = "";

		if (JMBG.charAt(4) == '0') {
			milenijalac = "2";
		} else {
			milenijalac = "1";
		}

		String datumRodj = JMBG.substring(0, 2);
		datumRodj += JMBG.substring(2, 4);
		datumRodj += milenijalac + JMBG.substring(4, 7);
		int godR = Integer.parseInt(datumRodj);
		sum = zbirBrojeva(godR);

		for (int i = 0; i < 3; i++) {
			if (sum > 9) {
				sum = zbirBrojeva(sum);
			} else
				break;
		}

		return sum;
	}

	public int zbirBrojeva(int n) {
		int sum = 0;
		while (n > 0) {
			int c = n % 10;
			sum += c;
			n /= 10;
		}
		return sum;
	}

	@Override
	public String metabolizam(final Datum d) {
		String milenijalac = "";

		if (JMBG.charAt(4) == '0') {
			milenijalac = "2";
		} else {
			milenijalac = "1";
		}

		String datumRodj = JMBG.substring(0, 4);
		datumRodj += milenijalac + JMBG.substring(4, 7);

		String danasnjiDatum = d.toString();
		danasnjiDatum = danasnjiDatum.replace(".", "");
		
		String rezultat = Integer.toString(Integer.parseInt(datumRodj) + Integer.parseInt(danasnjiDatum));

		if (rezultat.length() == 7) {
			return rezultat = '0' + rezultat;
		} else
			return rezultat;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" || JMBG: " + JMBG);

		return sb.toString();

	}

}
