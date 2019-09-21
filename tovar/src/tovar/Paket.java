package tovar;

import java.util.ArrayList;


public class Paket extends Tovar{
	private ArrayList<Tovar> tovar;
	
	
	public Paket() {
		
		this.tovar = new ArrayList<Tovar>();
	}

	@Override
	public String vrsta() {
		return "paket";
	}

	@Override
	public double tezina() {
		double ukupnaTezina = 0;
		for (Tovar t: tovar) {
			ukupnaTezina += t.tezina();
		}
		return ukupnaTezina;
	}
	
	public void dodaj(Tovar tovar) {
		this.tovar.add(tovar);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(vrsta() + "[");
		for (int i =0; i < tovar.size(); i++) {
			sb.append(tovar.get(i).vrsta());
			if(i != tovar.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		sb.append("(" + tezina() + ")");
		
		return sb.toString();

	}

}
