package prodavnicaAutomobila;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ProdavnicaAutomobila {
	String connectionString;
	Connection con;

	private String trenutniUsername;

	Calendar calendar;
	Date datum;
	
	public ProdavnicaAutomobila(String conStr) {
		connectionString = conStr;

		this.calendar = Calendar.getInstance();
		this.datum = new Date(this.calendar.getTimeInMillis());
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

	public void login() {
		Scanner sc = new Scanner(System.in);
		String username = "";
		String password = "";

		boolean loggedIn = false;

		while (loggedIn == false) { 
			System.out.println("Unesite vas Username: ");
			username = sc.nextLine();
			System.out.println("Unesite vas Password: ");
			password = sc.nextLine();

			loggedIn = proverUsernameIPassword(username, password); 
			if (!loggedIn) { 
				System.out.println("Uneli ste pogresan username ili password!");
			}
		}

		System.out.println("Uspesno ste se ulogovali!"); 
		
		this.trenutniUsername = username;

		daljeOpcije(); 
	}

	public boolean proverUsernameIPassword(String username, String password) {
		try {
			String upit = "select Korisnik.Username, Korisnik.Password from Korisnik";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) {
				if (username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					return true; 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void daljeOpcije() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Unesite opciju 1 ili 2");
		System.out.println("1. Pogled i kupovina");
		System.out.println("2. Uplata novca");

		int izbor = sc.nextInt();

		switch (izbor) {
		case 1:
			prikaziAutomobile(); 
			break;
		case 2:
			System.out.println("Izvrsavanje uplate..."); 
			uplataNovca(datum);
			break;
		default:
			System.out.println("Pogresan izbor, pokrenite aplikaciju ponovo!");
			break;
		}
	}

	public void register() {
		Scanner sc = new Scanner(System.in);
		String username = "";
		String password = "";

		boolean validanUsername = false; 
		boolean validanPassword = false; 

		while (!validanUsername) { 
			System.out.println("Unesite Vas novi Username: ");
			username = sc.nextLine();
			validanUsername = daLiJeUserNameValidan(username); 
		}

		while (!validanPassword) { 
			System.out.println("Unesite Vas novi Password: ");
			password = sc.nextLine();
			System.out.println("Ponovite Vas novi Password: ");
			String ponovniPassword = sc.nextLine();
			validanPassword = daLiJePasswordValidan(password, ponovniPassword); 
		}

		registruj(username, password); 
		System.out.println("Uspesno ste registrovani!");
	}

	public boolean daLiJeUserNameValidan(String username) {
		if (username.contains(" ")) { 
			System.out.println("Username ne sme sadrzati blanko znake!");
			return false;
		}

		try {
			String upit = "select Korisnik.Username from Korisnik";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			while (rs.next()) { 
				if (username.equals(rs.getString(1))) {
					System.out.println("Izabrani Username vec postoji!"); 
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true; 
	}

	public boolean daLiJePasswordValidan(String password, String ponovniPassword) {
		if (password.contains(" ")) { 
			System.out.println("Password ne sme sadrzati blanko znake!");
			return false;
		}
		if (password.length() < 5) { 
			System.out.println("Password nije dovoljne duzine (min 5 karaktera)!");
			return false;
		}
		if (!password.equals(ponovniPassword)) { 
			System.out.println("Niste korektno ponovili Password!");
			return false;
		}

		return true; 
	}

	private void registruj(String username, String password) {
		try {
			String insertKorisnik = "insert into Korisnik (Username, Password) VALUES (?,?)";

			PreparedStatement ps = con.prepareStatement(insertKorisnik);
			ps.setString(1, username);
			ps.setString(2, password);

			ps.execute(); 

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void prikaziAutomobile() {
		try {
			String upit = "select Model.Naziv, Automobil.Cena, Automobil.IdAuto " + "from Automobil\n"
					+ "left join Model\n" + "on Automobil.IdModel = model.IdModel\n"
					+ "where Model.BrNabavljenih - Model.BrProdatih > 0 " + "group by Model.Naziv";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			System.out.println("Automobili: ");
			while (rs.next()) {
				System.out.println(rs.getString(1) + " || " + rs.getString(2) + " (EUR)" + " || ID: " + rs.getInt(3));
			} 

			kupovinaAutomobila(datum); 

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void kupovinaAutomobila(Date datum) {

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Koji automobil zelite kupiti - unesite ID automobila? ");
			int idA = sc.nextInt(); 

			String upit = "insert  into  Prodaja (IdAuto, Username, Datum) values ( " + idA + ", '"
					+ this.trenutniUsername + "','" + datum + "')";

			Statement stm = con.createStatement(); 
			stm.executeUpdate(upit);

			String upitZaModel = "select Automobil.IdModel\n" + "from Automobil\n" + "where Automobil.IdAuto = " + idA;
			Statement stmModel = con.createStatement();
			ResultSet rs = stmModel.executeQuery(upitZaModel);

			int model = rs.getInt(1); 

			String updateBrPr = " update Model\n" + " set BrProdatih = BrProdatih + 1\n" + " where Model.IdModel = "
					+ model; 

			Statement update = con.createStatement();
			update.execute(updateBrPr);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uplataNovca(Date datum) {

		int id = 0;
		try {
			Scanner sc = new Scanner(System.in);

			String upit = "select Prodaja.* , Automobil.Cena "
					+ "from Prodaja, Automobil where  Prodaja.IdAuto = Automobil.IdAuto and Prodaja.Username = '"
					+ trenutniUsername + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(upit);

			System.out.println("Kupljeni automobil(i)..."); 
			
			List<Integer> kupljeniAutomobili = new ArrayList<>();
			
			while (rs.next()) {		
				kupljeniAutomobili.add(rs.getInt(1));
				
				String upitIznos = "select  sum (Uplata.Iznos) from Uplata where IdAuto = " + rs.getInt(1);
				Statement stmI = con.createStatement();
				ResultSet rsI = stmI.executeQuery(upitIznos);

				System.out.println("ID automobila: " + rs.getInt(1) + " || Username: " + rs.getString(2) + " || Datum: "
						+ rs.getString(3) + " || Cena automobila:  " + rs.getInt(4) + " || Uplaceno: " + rsI.getInt(1));

			}

			int idAP = -1;
			
			while(!kupljeniAutomobili.contains(idAP)) {
				System.out.println("Unesite ID automobila: ");
				idAP = sc.nextInt();
				
				if(!kupljeniAutomobili.contains(idAP)) {
					System.out.println("Uneli ste pogresan ID!");
				}
			}

			System.out.println("Koliko novca zelite uplatiti?");
			int uplataNovca = sc.nextInt(); 

			String cenaAut = "select cena from automobil where IdAuto = " + idAP;

			Statement stmC = con.createStatement(); 
			ResultSet rsC = stmC.executeQuery(cenaAut);

			int cenaAutomobila = rsC.getInt(1);

			String vecPlacenIznos = "select sum(Uplata.Iznos) from Uplata where IdAuto = " + idAP;

			Statement stmPI = con.createStatement(); 
			ResultSet rsPI = stmPI.executeQuery(vecPlacenIznos);

			int placeniIznos = rsPI.getInt(1);

			int kusur = 0;

			if ((uplataNovca + placeniIznos) > cenaAutomobila) { 
				System.out.println("Vas kusur je: " + (kusur = cenaAutomobila - uplataNovca - placeniIznos));
			} else
				System.out.println("Uspesno ste uplatili " + uplataNovca + " EUR. Za uplatu Vam je ostalo jos "
						+ (cenaAutomobila - placeniIznos - uplataNovca) + "!");

			String upitMaxId = "select max(idUplata) from Uplata";
			Statement stmMI = con.createStatement();
			ResultSet rsMI = stmMI.executeQuery(upitMaxId);

			id = rsMI.getInt(1);
			id++;
			
			if(kusur < 0) {
				uplataNovca += kusur;
			}

			String upitUnosUplate = "insert  into  Uplata (IdAuto, Iznos, Datum, IdUplata) values ( " + idAP + ", " + uplataNovca
					+ ", '" + datum + "', " + id + ")";

			Statement stmUU = con.createStatement();
			stmUU.executeUpdate(upitUnosUplate);
			
			String modelAutomobila = "select idModel from Automobil where idAuto = " + idAP;
			Statement stmModel = con.createStatement();
			ResultSet rsModel = stmModel.executeQuery(modelAutomobila);
			
			String modelA = rsModel.getString(1);
			
			String updateProfit = " update Model  set Profit = Profit + " + uplataNovca + " where Model.IdModel = "
					+ modelA; 

			Statement updateP = con.createStatement();
			updateP.execute(updateProfit);
			
			String updateStatus = "update Automobil set Status = 'p' where idAuto = " + idAP;
			Statement stmStatus = con.createStatement();
			stmStatus.execute(updateStatus);
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
