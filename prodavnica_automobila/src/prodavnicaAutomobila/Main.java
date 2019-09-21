package prodavnicaAutomobila;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ProdavnicaAutomobila pa = new ProdavnicaAutomobila("jdbc:sqlite://home//pedja//Downloads//Prodavnica.db");
		
		pa.connect();
		

		System.out.println("Dobrodosli u BrmBrm prodavnicu automobila!");
		
		System.out.println("Izaberite neku od opcija:");
		System.out.println("1. Login");
		System.out.println("2. Registracija");
		
		Scanner sc = new Scanner(System.in);
		
		int izbor = sc.nextInt();
		sc.nextLine();

		switch (izbor) {
		case 1:
			pa.login(); 
			break;
		case 2: 
			pa.register(); 
			break;
		default:
			System.out.println("Pogresan izbor, pokrenite aplikaciju ponovo!");
			break;
		}
		
		pa.disconnect();
	}
	
}
