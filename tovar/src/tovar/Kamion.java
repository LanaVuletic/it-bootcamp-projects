package tovar;

import java.util.ArrayList;

public class Kamion {
	private String regBroj;
	private ArrayList<Tovar> tovar;
	private double nosivost;
	private double teret;

	public Kamion(String regBroj, double nosivost) {
		this.regBroj = regBroj;
		this.nosivost = nosivost;
		this.tovar = new ArrayList<Tovar>();
		this.teret = 0;

	}

	public boolean stavi(Tovar tovar) {
		if ((teret + tovar.tezina()) > nosivost) {
			return false;
		}
		this.tovar.add(tovar);
		teret += tovar.tezina();
		return true;
	}

	public void skini() {
		teret -= tovar.get(tovar.size() - 1).tezina();
		this.tovar.remove(tovar.size() - 1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(regBroj);
		sb.append("(" + teret + "/" + nosivost + ")\n");
		for (Tovar t : tovar) {
			sb.append(" " + t + "\n");
		}
		return sb.toString();
	}

}
