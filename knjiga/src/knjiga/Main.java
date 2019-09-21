package knjiga;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Polica p1 = napraviPolicu();
		System.out.println(p1.toString());

		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("Unesite knjigu koju zelite uzeti: ");
		String imeKnjige = sc.nextLine();
		Knjiga knjigaNaPolici = p1.uzmiKnjiguZadatogNaziva(imeKnjige);
		if (knjigaNaPolici == null) {
			System.out.println("Knjiga nije na polici!");
		} else {
			System.out.println("Uzeli ste knjigu " + imeKnjige + " sa police!");
			System.out
					.println("---------------------------------------------------------------------------------------");
			System.out.println(
					"Broj listova u knjizi " + imeKnjige + " je: " + knjigaNaPolici.dohvatiBrojListovaUKnjizi() + ".");
			System.out
					.println("---------------------------------------------------------------------------------------");
			System.out.println("Koju stranicu zelite procitati?");
			int redniBroj = sc.nextInt();
			if (knjigaNaPolici.dohvatiListSaZadatimRednimBrojem(redniBroj) == null) {
				System.out.println("Taj list ne postoji u knjizi!");
			} else {
				System.out.println("\t" + knjigaNaPolici.dohvatiListSaZadatimRednimBrojem(redniBroj));
			}
			
			System.out
			.println("---------------------------------------------------------------------------------------");

			List[] nizListova = knjigaNaPolici.getNizListova();

			System.out.println("Poredimo listove uzete knjige: \n\t" + nizListova[0] + "\n i \n\t" + nizListova[1]);
			boolean daLiSuJednakiListovi = List.poredjenjeListova(nizListova[0], nizListova[1]);

			if (daLiSuJednakiListovi) {
				System.out.println("i oni su jednaki!");
			} else {
				System.out.println("i oni nisu jednaki!");
			}
		}
	}

	public static Polica napraviPolicu() {
		Polica p1 = new Polica();

		Knjiga k1 = new Knjiga("The Little Prince - Antoine de Saint-Exupéry");

		k1.dodajList(new List("And now here is my secret, a very simple secret: \n"
				+ "\tIt is only with the heart that one can see rightly;\n"
				+ "\twhat is essential is invisible to the eye.", 21));
		k1.dodajList(new List(
				"It is the time you have wasted for your rose\n"
				+ " \tthat makes your rose so important.", 26));
		k1.dodajList(new List("When someone blushes, doesn't that mean 'yes'?", 24));
		k1.dodajList(new List("You become responsible, forever, for what you have tamed.", 69));

		p1.dodajKnjiguNaPolicu(k1);

		Knjiga k2 = new Knjiga("The Sandman Series - Neil Gaiman");

		k2.dodajList(new List("People think dreams aren't real just because \n"
				+ "\tthey aren't made of matter, of particles. Dreams are real. \n"
				+ "\tBut they are made of viewpoints, of images, of memories and puns and lost hopes.", 99));
		k2.dodajList(new List("I move from dreamer to dreamer, from dream to dream, hunting for what I need. \n"
				+ "\tSlipping and sliding and flickering through the dreams; \n"
				+ "\tand the dreamer will wake, and wonder why this dream seemed different, \n"
				+ "\twonder how real their lives can truly be.", 19));
		k2.dodajList(new List("Any view of things that is not strange, is false.", 3));

		p1.dodajKnjiguNaPolicu(k2);

		Knjiga k3 = new Knjiga("Complete Stories and Poems - Edgar Allan Poe");

		k3.dodajList(new List("Is all that we see or seem\n" + "\nBut a dream within a dream?", 13));
		k3.dodajList(new List("Men have called me mad; but the question is not yet settled, \n"
				+ "\twhether madness is or is not the loftiest intelligence – whether much that is glorious –\n"
				+ "\twhether all that is profound – does not spring from disease of thought –\n"
				+ "\tfrom moods of mind exalted at the expense of the general intellect.", 666));
		k3.dodajList(new List("To die laughing must be the most glorious of all glorious deaths!", 13));

		p1.dodajKnjiguNaPolicu(k3);

		Knjiga k4 = new Knjiga("Harry Potter - J.K. Rowling");

		k4.dodajList(new List("I solemnly swear that I am up to no good.", 17));
		k4.dodajList(new List("Just because you have the emotional range of a teaspoon doesn't mean we all have.", 3));
		k4.dodajList(new List(
				"Dumbledore watched her fly away, and as her silvery glow faded he turned back to Snape,\n"
						+ "\tand his eyes were full of tears.\n" + "\t'After all this time?', 'Always' said Snape.",
				29));

		p1.dodajKnjiguNaPolicu(k4);

		return p1;

	}

}
