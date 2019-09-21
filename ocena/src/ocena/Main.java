package ocena;

public class Main {

	public static void main(String[] args) {
		Ocena o1 = new Ocena (66,"sociologija", "januar");
		Ocena o2 = new Ocena (78, "Java", "januar");
		Ocena o3 = new Ocena (99, "Art", "septembar");
		
		Ocena[] nizOcena = {o1, o2, o3};
		
		System.out.println(o1.getBrojPoena());
		System.out.println(o3.getOcena());
		System.out.println(o2.getRok());
		System.out.println(Ocena.getProsek(nizOcena));
	}

}
