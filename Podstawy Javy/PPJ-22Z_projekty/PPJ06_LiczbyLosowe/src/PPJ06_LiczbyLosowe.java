// Program ilustruje brak synchronizacji 
// strumieni out i err konsoli Eclipse.

import java.util.Scanner;
import java.util.Random;

public class PPJ06_LiczbyLosowe {
  
  public static void main(String[] args) {  
    
    int n, z;         // Do pobrania od użytkownika
    int licznik = 0;  // Liczba wygenerowanych liczb
    Scanner scn = new Scanner(System.in);
    Random rnum = new Random();
    
    // Pętla konwersacji
    try {
      while(true ){
        System.out.print("Ile liczb generować: n = ");
        n = scn.nextInt();
        
        if(n <= 0) break;
        System.out.print("Jaki zakres 1..z: z = ");
        z = scn.nextInt();
        
        for(int i=0; i<n; ++i){
          int r = rnum.nextInt(z) + 1;
          ++licznik;
          System.out.println("out " + r);  //System.out.flush(); Thread.sleep(10);
          //System.out.flush(); //Thread.sleep(200);
          System.err.println("err " + r);
          //System.err.flush(); //Thread.sleep(200);
        }      
      }
    }
    catch(Exception e) {
      System.err.println("Prawdopodobnie błędny znak na wejściu.");
    }  
    System.out.println("Koniec programu");
    System.out.println("Wygenerowano liczb: " + licznik); 
    
    scn.close();
  }

}
