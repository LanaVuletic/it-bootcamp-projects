package fudbalskiSavez;

public class Main {

	public static void main(String[] args) {
		FudbalskiSavez fs = new FudbalskiSavez("jdbc:sqlite://home//pedja//Downloads//FudbalskiSavez.db");

		fs.connect();

		fs.fudbalerTim();
		fs.fudbaleriMU();
		fs.golovi();
		fs.fudbalerSaNajviseGolova();
		fs.kartoni();
		fs.gdeJeFudbalerIgrao(1);
		fs.dodajFudbalera("Andrea Conti", "Milano");
		fs.dodajTim("Ajax", "Amsterdam");
		fs.dodajUtakmicu("Manchester United", "Milano", 1, "1", "2019");
		fs.obrisiTim("Manchester United");

		fs.disconnect();

	}

}
