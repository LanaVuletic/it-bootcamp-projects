
public class Trougao {
	Tacka pocetnaTacka;
	double a;
	double b;
	double c;

	public Trougao(double a, double b, double c, Tacka tacka) {
		pocetnaTacka = tacka;
		if (daLiJeTrougaoValidan(a, b, c)) {
			this.a = a;
			this.b = b;
			this.c = c;
		} else {
			System.out.println("Uneti trougao je nevalidan!");
		}
	}

	public Tacka getTacka() {
		return pocetnaTacka;
	}

	public void setTacka(Tacka t) {
		this.pocetnaTacka = t;
	}

	public boolean daLiJeTrougaoValidan(double a, double b, double c) {
		if (a > 0 && b > 0 && c > 0 && (a + b) > c && (a + c) > b && (b + c) > a) {
			return true;
		} else {
			return false;
		}
	}

	public void daLiJeTrougaoJednakokraki() {
		if (a == b || a == c || b == c) {
			System.out.println("Trougao je jednakokraki!");
		} else {
			System.out.println("Trougao nije jednakokraki!");
		}
	}

	public double obimTrougla() {
		return a + b + c;
	}

	public double povrsinaTrougla() {
		double s = (a + b + c) / 2;
		return Math.sqrt(s * (s - a) * (s - b) * (s - c));

	}

	public void setA(double a) {
		double privremenoA = this.a; // prvo smo sacuvali this.a
		this.a = a; // dodelili smo vrednost koja ce ostati ako je trougao validan
		if (!daLiJeTrougaoValidan(a, b, c)) {
			this.a = privremenoA; // ako trougao nije validan mi ga vracamo na prvobitnu vrednost jer dodela nije uspela
			System.out.println("Dodela nije uspela, trougao bi postao nevalidan!");
		}
	}

	public void setB(double b) {
		double privremenoB = this.b;
		this.b = b;
		if (!daLiJeTrougaoValidan(a, b, c)) {
			this.b = privremenoB;
			System.out.println("Dodela nije uspela, trougao bi postao nevalidan!");
		}
	}

	public void setC(double c) {
		double privremenoC = this.c;
		this.c = c;
		if (!daLiJeTrougaoValidan(a, b, c)) {
			this.c = privremenoC;
			System.out.println("Dodela  nije uspela, trougao bi postao nevalidan!");
		}
	}

	public void daLiJeIstaPocetnaTacka(Trougao t) {
		if ((pocetnaTacka.getX() == t.getTacka().getX()) && (pocetnaTacka.getY() == t.getTacka().getY())) {
			System.out.println("Ista pocetna tacka!");//poredimo pocetnu tacku x naseg trougla sa pocetnom tackom x prosledjenog Trougla t
		} else {
			System.out.println("Nije ista pocetna tacka!");
		}
	}

	public String ispisTrougla() {
		String ispis = "";
		if (daLiJeTrougaoValidan(a, b, c)) {
			ispis = ispis + "Trougao sa pocetkom: ";
			ispis = ispis + pocetnaTacka.ispisTacke();
			ispis = ispis + "\nStranice trougla: " + a + ", " + b + ", " + c;

			String povrsinaStr = String.format("%.2f", povrsinaTrougla());
			String obimStr = String.format("%.2f", obimTrougla());

			ispis = ispis + "\nPovrsina i obim: P = " + povrsinaStr + "; " + "O = " + obimStr;
		} else {
			ispis = "Trougao nije validan!";
		}
		return ispis;
	}

}
