package osoba;

public class OsobaD extends Osoba {
	private Datum datumRodjenja;

	public OsobaD(String ime, String prezime, String adresaStanovanja, Datum datumRodjenja) {
		super(ime, prezime, adresaStanovanja);
		this.datumRodjenja = datumRodjenja;
	}

	public OsobaD(OsobaD od) {
		super(od);
		datumRodjenja = od.datumRodjenja;

	}

	public Datum getDatumRodjenja() {
		return datumRodjenja;
	}

	@Override
	public int numeroloskiBroj() {

		int zbirDan = zbirBrojeva(datumRodjenja.getDan());
		int zbirMesec = zbirBrojeva(datumRodjenja.getMesec());
		int zbirGodina = zbirBrojeva(datumRodjenja.getGodina());

		int sum = zbirDan + zbirMesec + zbirGodina;

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
		String datumR = getDatumRodjenja().toString();
		datumR = datumR.replace(".", "");

		String danasnjiD = d.toString();
		danasnjiD = danasnjiD.replace(".", "");

		String rezultat = Integer.toString(Integer.parseInt(datumR) + Integer.parseInt(danasnjiD));

		if (rezultat.length() == 7) {
			return rezultat = '0' + rezultat;
		} else
			return rezultat;
	}
	

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" || Datum rodjenja: " + datumRodjenja);

		return sb.toString();

	}

}
