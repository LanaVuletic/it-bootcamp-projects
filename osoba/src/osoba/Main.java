package osoba;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Unesite broj objekata: ");
		int n = sc.nextInt();

		Osoba[] niz = new Osoba[n];

		for (int i = 0; i < n; i++) {
			System.out.println("Unesite ime: ");
			String ime = unosPodataka();
			System.out.println("Unesite prezime: ");
			String prezime = unosPodataka();
			System.out.println("Unesite adresu: ");
			String adresa = unosPodataka();

			System.out.println("Ako zelite da unesete datum rodjenja pritisnite 1, a ako zelite JMBG pritisnite 2: ");
			int opcija = sc.nextInt();
		
			switch (opcija) {
			case 1:
				Datum datum = vratiDatum();

				OsobaD oD = new OsobaD(ime, prezime, adresa, datum);				
				niz[i] = oD;
				
				break;
			case 2:
				System.out.println("Unesite JMBG");
				boolean dobarJMBG = false;
				String JMBG = "";
				
				while(!dobarJMBG) {
					JMBG = unosPodataka();
					
					dobarJMBG = (JMBG.length() == 13);
					
					if(!dobarJMBG) {
						System.out.println("JMBG je neadekvatne duzine!.");
					}
				}
				
				OsobaJMBG ojmbg = new OsobaJMBG(ime, prezime, adresa, JMBG);
				niz[i] = ojmbg;
			}
			
			
		}
		System.out.println("Uneti danasnji datum! ");
		final Datum d = vratiDatum();
		
		System.out.println("Danasnji datum je: " + d);
		
		ispis(niz, d);
	}
	
	public static void ispis(Osoba[] osobe, Datum d) {
		for(int i = 0; i < osobe.length; i++) {
			int numBroj = osobe[i].numeroloskiBroj();
			String metabolizam = osobe[i].metabolizam(d);
			
			if(numBroj != 7 && !metabolizam.contains("0")) {
				System.out.println("Osoba " + (i + 1) + "\n" + osobe[i]);
				System.out.println("Numericki broj: " + numBroj);
				System.out.println("Broj za racunanje trouglica: " + metabolizam);
			}
			else {
				System.out.println("Osoba " + (i + 1) + "\n" + osobe[i] + "\n Osoba ima ili numeroloski broj 7 ili joj  broj za 'trouglice' sadrzi 0!");
			}
		}
	}
	
	public static String unosPodataka() {
		Scanner sc = new Scanner(System.in);
		
		String podatak = sc.nextLine();
		
		return podatak;
	}

	public static Datum vratiDatum() {
		Scanner sc = new Scanner(System.in);
		
		boolean okDatum = false;
		
		 Datum d = null;

		while(okDatum == false) {
			System.out.println("Unesite dan (dd): ");
			int dan = sc.nextInt();
	
			System.out.println("Unesi mesec (mm): ");
			int mesec = sc.nextInt();
	
			System.out.println("Unesi godinu (gggg): ");
			int godina = sc.nextInt();
	
			d = new Datum(dan, mesec, godina);
			
			okDatum = validacija(d);
			
			if(okDatum == false) {
				System.out.println("Datum nije dobro unesen!");
			}
		}
		
		return d;
	}
	
	public static boolean validacija(Datum d) {
		if(daLiJeBrojDanaOk(d) == false ||
		  (d.getDan() == 29 && d.getMesec() == 2 && daLiJeGodinaPrestupna(d) == false)
		  ) {
			return false;
		}
		return true;
	}
	
	public static boolean daLiJeGodinaPrestupna(Datum d) { 
		int godina = d.getGodina();
		if ((godina % 4 == 0) && (godina % 100 != 0) || (godina % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean daLiJeBrojDanaOk(Datum d) { 
		int mesec = d.getMesec();

		switch (mesec) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if(d.getDan() > 0 && d.getDan() <= 31) {
				return true;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if(d.getDan() > 0 && d.getDan() <= 30) {
				return true;
			}
			break;
		case 2:
			if(d.getDan() > 0 && d.getDan() <= 29) {
				return true;
			}
			break;
		}
		
		return false;
	}

}
