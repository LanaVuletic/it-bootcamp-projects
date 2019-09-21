package fudbalskiSavez;

import java.sql.*;

public class FudbalskiSavez {
	String connectionString;
	Connection con;

	public FudbalskiSavez(String conStr) {
		connectionString = conStr;
	}

	public void connect() {
		try {
			con = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fudbalerTim() {
		try {
			String upit = "select Fudbaler.Ime, Tim.Naziv from Fudbaler, Tim  where Fudbaler.IdTim=Tim.IdTim";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				String ime = rs.getString(1);
				String tim = rs.getString(2);

				System.out.println(ime + " || " + tim);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fudbaleriMU() {
		try {
			String upit = "select Fudbaler.Ime from Fudbaler, Tim  where Fudbaler.IdTim=Tim.IdTim and Tim.Naziv = 'Manchester United'";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				String ime = rs.getString(1);

				System.out.println(ime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void golovi() {
		try {
			String upit = "select Fudbaler.Ime, count(Gol.idGol) from Fudbaler, Gol where Fudbaler.IdFud=Gol.IdFud "
					+ "group by Gol.IdFud";

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				String ime = rs.getString(1);
				int golovi = rs.getInt(2);

				System.out.println(ime + " || broj golova: " + golovi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fudbalerSaNajviseGolova() {
		String upit = "select Fudbaler.Ime, max(brgolova.golovi)\n" + "from Fudbaler, \n" + "(\n"
				+ "select Fudbaler.Ime, count(Gol.IdFud) as golovi\n" + "from Fudbaler, Gol\n"
				+ "where Fudbaler.IdFud = Gol.IdFud\n" + "group by Fudbaler.IdFud\n" + ") brgolova";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(upit);

			System.out.println("Fudbaler sa najvise golova je: " + rs.getString(1) + " (" + rs.getInt(2) + " gola)");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void kartoni() {
		try {

			String upit = " select fudbaler.Ime, "
					+ "(select count(Karton.IdFud) from Karton where Fudbaler.IdFud = Karton.IdFud and Karton.Tip = 'zuti karton') as zutikarton, "
					+ "(select count(Karton.IdFud) from Karton where Fudbaler.IdFud = Karton.IdFud and Karton.Tip = 'crveni karton') as crvenikarton "
					+ "from Fudbaler";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				String ime = rs.getString(1);
				int brojZutih = rs.getInt(2);
				int brojCrvenih = rs.getInt(3);

				System.out.println(ime + " || zuti kartoni: " + brojZutih + " || crveni kartoni: " + brojCrvenih);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void gdeJeFudbalerIgrao(int id) {
		try {
			String upit = "select  sve.Mesto " + "from " + "(select * " + "from Igrao " + "left join " + "Utakmica "
					+ "on igrao.IdUta = Utakmica.IdUta " + "left join " + "Tim " + "on  tim.IdTim = Utakmica.IdDomacin "
					+ ") sve " + "where sve.idFud = " + id;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				String mesto = rs.getString(1);
				System.out.println(mesto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dodajFudbalera(String imeFudbalera, String imeTima) {
		int id = 0;
		int idTima = 0;

		try {
			String upitMaxId = "select max(idFud) from Fudbaler";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upitMaxId);

			id = rs.getInt(1);
			id++;

			String upitIdTima = "select idTim from Tim where naziv = '" + imeTima + "' ";
			Statement idTimaStm = con.createStatement();
			ResultSet rsIdTima = idTimaStm.executeQuery(upitIdTima);

			idTima = rsIdTima.getInt(1);

			String insertFudbalerUpit = "insert into Fudbaler (idFud, Ime, IdTim) VALUES (?,?,?)";

			PreparedStatement ps = con.prepareStatement(insertFudbalerUpit);
			ps.setInt(1, id);
			ps.setString(2, imeFudbalera);
			ps.setInt(3, idTima);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dodajTim(String imeTima, String mesto) {
		int idTima = 0;

		try {
			String upitMaxIdT = "select max(idTim) from Tim";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upitMaxIdT);

			idTima = rs.getInt(1);
			idTima++;

			String insertTimUpit = "insert into Tim (idTim, Naziv, Mesto) VALUES (?,?,?)";

			PreparedStatement ps = con.prepareStatement(insertTimUpit);
			ps.setInt(1, idTima);
			ps.setString(2, imeTima);
			ps.setString(3, mesto);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dodajUtakmicu(String gTim, String dTim, int kolo, String ishod, String godina) {
		int idUtakmice = 0;
		int idGT = 0;
		int idDT = 0;

		try {
			String upitMaxIdU = "select max(IdUta) from Utakmica";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upitMaxIdU);

			idUtakmice = rs.getInt(1);
			idUtakmice++;

			String upitIdGost = "select IdTim from Tim where Naziv = '" + gTim + "'";
			Statement stmG = con.createStatement();
			ResultSet rsG = stmG.executeQuery(upitIdGost);

			idGT = rsG.getInt(1);

			String upitIdDomacin = "select IdTim from Tim where Naziv = '" + dTim + "'";
			Statement stmD = con.createStatement();
			ResultSet rsD = stmD.executeQuery(upitIdDomacin);

			idDT = rsD.getInt(1);

			String insertTimUpit = "insert into Utakmica (IdUta, IdDomacin, IdGost, Kolo, Ishod, Godina ) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = con.prepareStatement(insertTimUpit);
			ps.setInt(1, idUtakmice);
			ps.setInt(2, idDT);
			ps.setInt(3, idGT);
			ps.setInt(4, kolo);
			ps.setString(5, ishod);
			ps.setString(6, godina);

			ps.execute();

			// dodavanje u Igrao
			dodavanjeUIgrao(idDT, idUtakmice);
			dodavanjeUIgrao(idGT, idUtakmice);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dodavanjeUIgrao(int tim, int utakmica) {
		try {
			String upitFudbaleri = "select Fudbaler.IdFud\n" + "from Fudbaler\n" + "where Fudbaler.IdTim = '" + tim
					+ "'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(upitFudbaleri);

			while (rs.next()) {
				int Fudbaler = rs.getInt(1);
				String upit = "insert into Igrao (IdFud, IdUta, PozicijaFudbalera ) VALUES (?, ?, ?)";

				PreparedStatement ps = con.prepareStatement(upit);
				ps.setInt(1, Fudbaler);
				ps.setInt(2, utakmica);
				ps.setInt(3, 0);

				ps.execute();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void obrisiTim(String nazivTima) {
		try {
			String upitIdTima = "select Tim.IdTim\n" + "from Tim\n" + "where Tim.Naziv = '" + nazivTima + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upitIdTima);

			int idTima = rs.getInt(1);

			String upitFudbaleri = "select Fudbaler.IdFud\n" + "from Fudbaler\n" + "where Fudbaler.IdTim = '" + idTima
					+ "'";
			Statement st = con.createStatement();
			ResultSet rsFudbaleri = st.executeQuery(upitFudbaleri);

			while (rsFudbaleri.next()) {
				int idFudbaler = rsFudbaleri.getInt(1);
				obrisiUtakmice(idFudbaler);
				obrisiFudbalera(idFudbaler);
			}

			String deleteTimUpit = "delete from Tim where IdTim = " + idTima;
			Statement stDeleteTim = con.createStatement();
			stDeleteTim.executeUpdate(deleteTimUpit);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void obrisiUtakmice(int fudbaler) {
		try {
			String upit = "select distinct Igrao.IdUta\n" + "from Igrao\n" + "where Igrao.IdFud = " + fudbaler;

			Statement st = con.createStatement();
			ResultSet rsUtakmice = st.executeQuery(upit);
			while (rsUtakmice.next()) {
				int utakmica = rsUtakmice.getInt(1);
				String deleteUpit = "delete from Utakmica where idUta = " + utakmica;
				Statement deleteSt = con.createStatement();
				deleteSt.executeUpdate(deleteUpit);

				obrisiUtakmicuIzTabele("Karton", utakmica);
				obrisiUtakmicuIzTabele("Gol", utakmica);
				obrisiUtakmicuIzTabele("Igrao", utakmica);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void obrisiUtakmicuIzTabele(String tabela, int utakmica) {
		try {
			String deleteUpit = "delete from " + tabela + " where idUta = " + utakmica;
			Statement deleteSt = con.createStatement();
			deleteSt.executeUpdate(deleteUpit);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void obrisiFudbalera(int idFudbalera) {
		try {
			String deleteUpit = "delete from Fudbaler where IdFud = " + idFudbalera;
			Statement deleteSt = con.createStatement();
			deleteSt.executeUpdate(deleteUpit);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
