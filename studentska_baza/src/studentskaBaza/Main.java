package studentskaBaza;

public class Main {

	public static void main(String[] args) {
		StudentskaBaza sb = new StudentskaBaza("jdbc:sqlite://home//pedja//Downloads//studentskabaza.db");
		sb.connect();

		sb.dodajStudenta(707847, "Anja", "Zorkic", "25.06.2010.", "17.9.1990.", "Beograd");
		sb.oSvimPredmetima();
		sb.oSvimStudentimaIzBg();
		sb.oMestuRodjenja();
		sb.predmetiSaViseOdSestBodova();
		sb.predmetiIzmedu8i15();
		sb.studentImaXBodova();
		sb.studentNemaXBodova();
		sb.cenaPredmeta();
		sb.izbrisiNevalidnuOcenu();
		sb.setDatumRodjenja(20140026, "13.06.1986.");

		sb.disconnect();

	}

}
