package knjiga;

public class List {
	private int redniBroj;
	private String tekst;
	
	public List(String tekst, int redniBroj) {
		this.redniBroj = redniBroj;
		this.tekst = tekst;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
	public static boolean poredjenjeListova(List l1, List l2) {
		if (l1.getRedniBroj() == l2.getRedniBroj()) {
			return true;
		}
		return false;
	}
	
	public boolean poredjenjeListova(List list) {
		if (list.getRedniBroj() == redniBroj) {
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		return tekst + " (" + redniBroj + ")";
	}
	
	
	
}
