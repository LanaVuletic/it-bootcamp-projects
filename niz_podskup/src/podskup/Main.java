package podskup;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Unesite velicinu niza A: ");
		int n = sc.nextInt();

		int[] A = new int[n];
		System.out.println("Unesite niz A: ");
		for(int i = 0; i < n; i++) {
			A[i] = sc.nextInt();
		}
		
		System.out.println("Unesite velicinu niza B: ");
		int m = sc.nextInt();

		int[] B = new int[m];
		System.out.println("Unesite niz B: ");
		for(int i = 0; i < m; i++) {
			B[i] = sc.nextInt();
		}
		
		boolean rez = podskup(A, B);
		
		if(rez == true) {
			System.out.println("Niz A je podskup niza B!");
		} else {
			System.out.println("Niz A NIJE podskup niza B!");
		}
		
	}
	
	static boolean podskup(int[] A,int[] B) {
		for(int i = 0; i < A.length; i++) { 
			if(!daLiJeUNizu(A[i], B)) { 
				return false; 
			}
		}
		
		return true;
	}
	
	static boolean daLiJeUNizu(int broj, int[] niz) {
		for(int i = 0; i < niz.length; i++) { 
			if(niz[i] == broj) { 
				return true; 
			}
		}
		
		return false;
	}

}
