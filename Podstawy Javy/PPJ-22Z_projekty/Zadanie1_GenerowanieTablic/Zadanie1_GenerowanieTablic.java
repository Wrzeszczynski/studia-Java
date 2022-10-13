import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class Zadanie1_GenerowanieTablic {  

  public static void main(String[] args) {  
    Scanner scn = new Scanner(System.in);
    Random rnd = new Random();
    
    // Tworzenie losowych tablic mXn, z - zakres generacji
    System.out.println("Generacja tablic losowych wg parametrów użytkownika\n");
    
    // Konwersacja
    while(true) {
      System.out.print("\nLiczba wierszy: m = ");
      int m = scn.nextInt();
      if(m<=0) break;
      int[][] T = new int[m][];
      
      System.out.println("Liczba kolumn w wierszach (n0 n1 ...)");
      for(int i=0; i<m; ++i)
        T[i] = new int[scn.nextInt()];
      
      System.out.println("Zakres generacji -z..z: z = ");
      int z = scn.nextInt();
      
      for(int i=0; i<m; ++i)
        for(int j=0; j<T[i].length; ++j)
          T[i][j] = rnd.nextInt(2*z)-z;
      
      System.out.println("\nWygenerowana tablica\n");
      for(int i=0; i<m; ++i) {
        for(int j=0; j<T[i].length; ++j) 
          System.out.print(T[i][j] + " ");
        System.out.println();
      } 
      
      System.out.println("\nTablica wyrównana w kolumnach\n");
      print(T);
    }
    scn.close();
    System.out.println("KONIEC PROGRAMU");
  }  // main()
  
  // Wydruk tablicy prostokątnej z wyrównaniem prawostronnym kolumn
  static void print(int[][] T) {
    int m = T.length;       // Liczba wierszy
    int n = 0;              // Liczba kolumn
    for(int i=0; i<m; ++i)
      if(T[i].length>n) n = T[i].length;
    
    int[] sk = new int[n];  // Szerokości kolumn
    for(int j=0; j<n; ++j) {
      sk[j] = 0;
      for(int i=0; i<m; ++i)
        if(j<T[i].length)
          sk[j] = Math.max(sk[j], (""+T[i][j]).length());
    }
    
    for(int i=0; i<m; ++i) {
      for(int j=0; j<T[i].length; ++j) 
        System.out.printf("%" + sk[j] + "d ",T[i][j]);
      System.out.println();
    } 
  }
}  // Klasa Zadanie1_GenerowanieTablic  
