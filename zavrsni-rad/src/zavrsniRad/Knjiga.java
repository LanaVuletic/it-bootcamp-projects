package zavrsniRad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zavrsniRad.komparator.LexographicComparator;
import zavrsniRad.komparator.NumberOfRepetitionComparator;

public class Knjiga {
	Connection con;

	Map<String, Integer> reci;

	public Knjiga(Connection connection) {
		this.con = connection;
		this.reci = new HashMap<>();
	}

	public Map<String, Integer> getReci() {
		return reci;
	}

	public void setReci(Map<String, Integer> reci) {
		this.reci = reci;
	}

	public void read(String fileName) { // citamo knjigu
		try {
			BufferedReader readerFajla = new BufferedReader(new FileReader("knjiga"));

			String linija = readerFajla.readLine();
			while (linija != null) {
				if (linija.isEmpty()) { // preskacemo prazne linije
					linija = readerFajla.readLine();
					continue;
				}

				linija = linija.replaceAll("[^A-Za-z\' '-]", ""); // uklanjamo znake interpukcije..
				linija = linija.replace("'", "");

				String[] reciULiniji = linija.split("\\s+");

				if (reciULiniji.length == 0) {
					linija = readerFajla.readLine();
					continue;
				}

				if (proveriDaLiJePoslednjiMinus(reciULiniji)) { // ako se poslednja rec u linij nastavlja u sledeci
																// red..
					reciULiniji = spojiLinije(reciULiniji, readerFajla); // ..spajamo linije..
				}

				for (int i = 0; i < reciULiniji.length; i++) {
					if (!reciULiniji[i].equals("")) {
						if (reciULiniji[i].charAt(0) == '-') {
							reciULiniji[i].replace("-", "");
						}
						if (reci.containsKey(reciULiniji[i])) {
							int brPonavljanja = reci.get(reciULiniji[i]);
							brPonavljanja++;
							reci.replace(reciULiniji[i], brPonavljanja);
						} else {
							reci.put(reciULiniji[i], 1);
						}
					}
				}

				linija = readerFajla.readLine();
			}

			readerFajla.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean proveriDaLiJePoslednjiMinus(String[] reciULiniji) { // provera da li se poslednja rec u liniji
																		// nastavlja u sledeci red
		return reciULiniji[reciULiniji.length - 1].charAt(reciULiniji[reciULiniji.length - 1].length() - 1) == '-';
	}

	private String[] spojiLinije(String[] reciULiniji, BufferedReader readerFajla) throws IOException { // spavajmo
																										// linije
		String polaReci = reciULiniji[reciULiniji.length - 1];
		polaReci = polaReci.replace("-", "");

		reciULiniji[reciULiniji.length - 1] = "";

		String novaLinija = readerFajla.readLine();
		novaLinija = novaLinija.replaceAll("[^A-Za-z\' '-]", "");

		String[] reciUNovojLiniji = novaLinija.split("\\s+");

		polaReci = polaReci + reciUNovojLiniji[0];
		polaReci = polaReci.replaceAll("[^A-Za-z\' '-]", "");
		reciUNovojLiniji[0] = polaReci;

		reciULiniji = spojiNizove(reciULiniji, reciUNovojLiniji);

		if (proveriDaLiJePoslednjiMinus(reciULiniji)) { // proveravam da li kod spojenih linija postoji minus na kraju
														// da bi uradili isto sve
			return spojiLinije(reciULiniji, readerFajla);
		}

		return reciULiniji;
	}

	private String[] spojiNizove(String[] niz1, String[] niz2) { // spajamo redove
		String[] rezultat = new String[niz1.length + niz2.length];

		for (int i = 0; i < niz1.length; i++) {
			rezultat[i] = niz1[i];
		}

		int brojac = niz1.length;

		for (int j = 0; j < niz2.length; j++) {
			rezultat[brojac] = niz2[j];
			brojac++;
		}

		return rezultat;
	}

	public List<String> listaRecikojeNisuURecniku(Recnik recnik) {
		List<String> rezultat = new ArrayList<String>(); // pravimo listu reci koje su u knjizi, a nisu u recniku
		for (String rec : reci.keySet()) {
			if (!recnik.getRecnik().containsKey(rec.toLowerCase())) {
				rezultat.add(rec);
			}
		}
		return rezultat;
	}

	public void kreirajTabeluReciKojeNisuURecniku(List<String> listaReciKojeNisuURecniku) { // tabela novih reci
		try {
			String upit = "CREATE TABLE  nove_reci (reci varchar(25) NOT NULL COLLATE NOCASE)";

			Statement stm = con.createStatement();
			stm.executeUpdate(upit);

			for (String rec : listaReciKojeNisuURecniku) {
				rec = rec.replace("'", "");
				rec = rec.substring(0, 1).toUpperCase() + rec.substring(1).toLowerCase();

				String insertUpit = "INSERT INTO nove_reci (reci) VALUES ('" + rec + "')";
				Statement stmU = con.createStatement();
				stmU.executeUpdate(insertUpit);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void sortiratiPoVrednosti() { // sortiranje reci iz knjige (a koje se nalze i u recniku) po vrednosti - broj
											// ponavljanja
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(reci.entrySet());

		Collections.sort(list, new NumberOfRepetitionComparator());

		for (int i = list.size() - 1; i > list.size() - 21; i--) {
			System.out.println(list.get(i));
		}
	}

	public static void ispisiUFajlSortiraneSveReci(ArrayList<String> ukupnaLista) { // upisivanje sortiarane liste reci
																					// u fajl
		Collections.sort(ukupnaLista, new LexographicComparator());
		try {
			FileWriter fw = new FileWriter("sveReci");

			for (String s : ukupnaLista) {
				fw.write(s.toString() + "\n");
			}

			fw.flush();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
