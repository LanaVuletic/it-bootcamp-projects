
public class Main {

	public static void main(String[] args) {
		Tacka[] niz = new Tacka[10];

		for (int i = 0; i < niz.length; i++) {
			double x = Math.floor(Math.random() * 90 + 10) / 10;
			double y = Math.floor(Math.random() * 90 + 10) / 10;
			Tacka tacka = new Tacka(x, y);
			niz[i] = tacka;
			System.out.println(niz[i].ispisTacke());

		}

		Trougao t1 = new Trougao(10, 5, 14, niz[1]);
		Trougao t2 = new Trougao(6, 7, 7, niz[3]);
		Trougao t3 = new Trougao(5, 7, 8, niz[1]);

		t1.setC(15);

		t2.daLiJeTrougaoJednakokraki();
		t1.daLiJeTrougaoJednakokraki();

		Trougao t4 = new Trougao(7, 9, 13, niz[2]);

		t4.daLiJeIstaPocetnaTacka(t3);
		t1.daLiJeIstaPocetnaTacka(t3);

		System.out.println(t2.ispisTrougla());

	}
}