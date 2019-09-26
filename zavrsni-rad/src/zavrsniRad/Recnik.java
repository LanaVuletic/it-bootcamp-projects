package zavrsniRad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recnik {
	Connection con;
	HashMap<String, Rec> recnik;

	public Recnik(Connection connection) {
		this.con = connection;
		this.recnik = new HashMap<String, Rec>();
	}

	public HashMap<String, Rec> getRecnik() {
		return recnik;
	}

	public void setRecnik(HashMap<String, Rec> recnik) {
		this.recnik = recnik;
	}

	public void ucitajReci() { // mapiranje reci iz recnika - HashMap<String, Rec> recnik
		try {
			String upit = "SELECT * FROM entries";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				String word = rs.getString("word");
				String wordtype = rs.getString("wordtype");
				String definition = rs.getString("definition");

				recnik.put(word.toLowerCase(), new Rec(word, wordtype, definition));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> pojavljivanjeUKnjizi(Knjiga knjiga) { // reci iz recnika koje se nalaze i u knjizi
		Map<String, Integer> rezultat = new HashMap<String, Integer>(); // mapiranje : rec -> broj ponavljanja

		for (String rec : recnik.keySet()) {
			if (knjiga.getReci().containsKey(rec)) {
				rezultat.put(rec, knjiga.getReci().get(rec));
			} else {
				rezultat.put(rec, 0);
			}
		}
		return rezultat;
	}

	public List<String> dohvatiListuReci() { // dohvata listu reci iz recnika i smesti ih u listu
		List<String> lista = new ArrayList<>();

		for (String rec : recnik.keySet()) {
			lista.add(rec);
		}
		return lista;
	}
}
