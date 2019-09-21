package parnost;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Unesite velicinu niza: ");
		int n = sc.nextInt();

		int[] A = new int[n];
		System.out.println("Unesite niz: ");
		for(int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		
		int[] rezultat = sortirajPoParnosti(A);
		
		for(int i = 0; i < rezultat.length; i++) {
			System.out.print(rezultat[i] + " ");
		}
	}
	
	static int[] sortirajPoParnosti(int[] a) {
		for(int i = 0; i < a.length - 1; i++) {
			
			if(!daLiJeParan(a[i]) && daLiJeParan(a[i+1])) {
				int temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				sortirajPoParnosti(a);
				
			}
		}
		return a;
	}
	
	static boolean daLiJeParan(double broj) { 
		if(broj % 2 == 0) {
			return true;
		}
		return false;
	}

}
