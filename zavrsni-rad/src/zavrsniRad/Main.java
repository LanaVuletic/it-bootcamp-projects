package zavrsniRad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
//	private static final String connectionString = "jdbc:sqlite://home//pedja//Downloads//Dictionary.db";
	private static Connection connection;

	public static void main(String[] args) {
//		args[0] - putanja do knjige, args[1] - putanja do baze;
		
		connect(args[1]);

		Recnik recnik = new Recnik(connection);

		recnik.ucitajReci();

		Knjiga knjiga = new Knjiga(connection);

		knjiga.read(args[0]);

		List<String> listaReciKojeNisuURecniku = knjiga.listaRecikojeNisuURecniku(recnik);

		System.out.println("Broj reci iz knjige koje nisu u recniku: " + listaReciKojeNisuURecniku.size());
		System.out.println("---------------------------------------------------------------------------  \n");

		knjiga.kreirajTabeluReciKojeNisuURecniku(listaReciKojeNisuURecniku);

		Map<String, Integer> pojavljivanjeUKnjizi = recnik.pojavljivanjeUKnjizi(knjiga);

		System.out.println("Reci iz knjige koje se nalaze u recniku (rec - broj pojavljivanja u knjizi): ");
		System.out.println("---------------------------------------------------------------------------  \n");

		for (String rec : pojavljivanjeUKnjizi.keySet()) {
			if (pojavljivanjeUKnjizi.get(rec) > 0) {
				System.out.println(rec + " || " + pojavljivanjeUKnjizi.get(rec));
			}
		}

		System.out.println("\n");

		System.out.println("20 najcesce koriscenih reci: ");
		System.out.println("---------------------------------------------------------------------------  \n");

		knjiga.sortiratiPoVrednosti();

		List<String> listaRecnik = recnik.dohvatiListuReci();

		List<String> ukupnaLista = knjiga.listaRecikojeNisuURecniku(recnik);

		ukupnaLista.addAll(listaRecnik);

		Knjiga.ispisiUFajlSortiraneSveReci((ArrayList<String>) ukupnaLista);

		disconnect();
	}

	public static void connect(String connectionString) {
		try {
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
