package studentskaBaza;

import java.sql.*;

public class StudentskaBaza {
	String connectionString;
	Connection con;

	public StudentskaBaza(String conStr) {
		connectionString = conStr;
	}

	public void connect() {
		try {
			con = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void disconnect() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dodajStudenta(int indeks, String ime, String prezime, String datumUpisa, String datumRodjenja,
			String mestoRodjenja) {
		try {
			String upit = " INSERT INTO  dosije (indeks, ime, prezime, datum_upisa, datum_rodjenja, mesto_rodjenja) "
					+ "VALUES ( " + indeks + ", '" + ime + "', '" + prezime + "', '" + datumUpisa + "', '"
					+ datumRodjenja + "', '" + mestoRodjenja + "')";

			Statement stm = con.createStatement();
			stm.executeUpdate(upit);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void oSvimPredmetima() {
		try {
			String upit = "SELECT * FROM Predmet";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				int id_predmeta = rs.getInt(1);
				String sifra = rs.getString(2);
				String naziv = rs.getString(3);
				int bodovi = rs.getInt(4);

				System.out.println(id_predmeta + " " + sifra + " " + naziv + " " + bodovi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void oSvimStudentimaIzBg() {
		try {
			String upit = "SELECT * FROM dosije WHERE mesto_rodjenja = \"Beograd\"";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				int indeks = rs.getInt(1);
				String ime = rs.getString(2);
				String prezime = rs.getString(3);
				String datum_upisa = rs.getString(4);
				String datum_rodjenja = rs.getString(5);
				String mesto_rodjenja = rs.getString(5);

				System.out.println(indeks + " " + ime + " " + prezime + " " + datum_upisa + " " + datum_rodjenja + " "
						+ mesto_rodjenja);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void oMestuRodjenja() {
		try {
			String upit = "SELECT DISTINCT dosije.mesto_rodjenja FROM dosije " + "WHERE mesto_rodjenja is not null";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {

				String mesto_rodjenja = rs.getString(1);

				System.out.println(mesto_rodjenja);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void predmetiSaViseOdSestBodova() {
		try {
			String upit = "select naziv from predmet where bodovi > 6";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {

				String naziv = rs.getString(1);

				System.out.println(naziv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void predmetiIzmedu8i15() {
		try {
			String upit = "select naziv, sifra  from predmet where bodovi between 8 and 15";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {

				String naziv = rs.getString(1);
				String sifra = rs.getString(2);

				System.out.println(naziv + " " + sifra);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void studentImaXBodova() {
		try {
			String upit = "select * from ispit where bodovi = 81 or bodovi = 76 or bodovi = 59";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {

				int indeks = rs.getInt(1);
				int id_predmeta = rs.getInt(2);
				int godina_roka = rs.getInt(3);
				String oznaka_roka = rs.getString(4);
				int ocena = rs.getInt(5);
				String datum_ispita = rs.getString(6);
				int bodovi = rs.getInt(7);

				System.out.println(indeks + " " + id_predmeta + " " + godina_roka + " " + oznaka_roka + " " + ocena
						+ " " + datum_ispita + " " + bodovi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void studentNemaXBodova() {
		try {
			String upit = "select * from ispit where not bodovi = 81 and not bodovi = 76 and not bodovi = 59";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {

				int indeks = rs.getInt(1);
				int id_predmeta = rs.getInt(2);
				int godina_roka = rs.getInt(3);
				String oznaka_roka = rs.getString(4);
				int ocena = rs.getInt(5);
				String datum_ispita = rs.getString(6);
				int bodovi = rs.getInt(7);

				System.out.println(indeks + " " + id_predmeta + " " + godina_roka + " " + oznaka_roka + " " + ocena
						+ " " + datum_ispita + " " + bodovi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cenaPredmeta() {
		try {
			String upit = "select naziv, bodovi * 1500  from predmet";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {

				String naziv = rs.getString(1);
				int cena = rs.getInt(2);

				System.out.println(naziv + " " + cena);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void izbrisiNevalidnuOcenu() {

		try {
			String upit = "delete from ispit where ocena < 5 or ocena > 10";

			Statement stm = con.createStatement();
			stm.executeUpdate(upit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDatumRodjenja(int indeks, String datumRodjenja) {
		try {
			PreparedStatement ps = con.prepareStatement("update dosije set datum_rodjenja = ? where indeks = ?");
			ps.setString(1, datumRodjenja);
			ps.setInt(2, indeks);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
