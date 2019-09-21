package pozoristance;

import java.util.ArrayList;

public class Repertoar {
	private Pozoriste pozoriste;
	private ArrayList<Predstava> predstava;

	public Repertoar(Pozoriste pozoriste) {
		this.pozoriste = pozoriste;
		this.predstava = new ArrayList<Predstava>();

	}

	public void dodaj(Predstava predstava) {
		this.predstava.add(predstava);
	}

	public void ukloni(String imePredstave) {
		for (int i = 0; i < predstava.size(); i++) {
			if (predstava.get(i).getNaziv().equals(imePredstave)) {
				predstava.remove(i);
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(pozoriste.getNaziv());
		sb.append(":");
		sb.append(" [");
		sb.append("\n");
		for (Predstava p : predstava) {
			sb.append(p + "\n");
		}
		sb.append("]");
		return sb.toString();
	}
}
