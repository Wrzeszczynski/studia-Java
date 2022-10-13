// Podstawy zapisu liczb

import java.util.Scanner;

public class PPJ2_PodstawyZapisuLiczb {

  public static void main(String[] args) {  

    try ( Scanner scn = new Scanner(System.in)) {
      long n; // n - liczba do prezentacji rozwinięć
      int  p; // Podstawa zapisu { 2 .. 36 }
      
      System.out.println("Liczba dziesiętna n w zapisie przy podstawie p.");
      System.out.println("Podstawa p ze zbioru {2..36}; liczba n = 0 kończy program.\n");

      while(true) { // Konwersacja        
        System.out.print("Liczba n i podstawa zapisu p: n p = ");
        n = scn.nextLong();
        p = scn.nextInt();
          
        if(n==0 || p<2 || p>36) break;	// Koniec programu
         
        System.out.println(n + " = " + Long.toString(n, p) + "(" + p + ")");
      }
      System.out.print("\nKoniec programu");
    }
  }
}
/*
Liczba n i podstawa zapisu p: n p = 3405691582 16 
3405691582 = cafebabe(16)
Liczba n i podstawa zapisu p: n p = 0 0

Koniec programu
*/  
