import java.util.Random;
import java.util.Scanner;

public class Zadanie2_TablicaSubdiagonalna {
  public static void main(String[] args) {  
    Scanner scn = new Scanner(System.in);
    Random rnd = new Random();  
    
    // Tworzenie losowych subdiagonalnych tablic nXn, z - zakres generacji
    System.out.println("Generacja tablic losowych subdiagonalnych wg parametrów n, z.");
    
    // Konwersacja
    while(true) {
      System.out.print("\nn z = ");
      int n = scn.nextInt();
      int z = scn.nextInt();
      
      if(n<=0 || z<=0) break;
      
      int[][] T = new int[n][];
      for(int i=0; i<n; ++i) {
        T[i] = new int[i+1]; 
        for(int j=0; j<i+1; ++j)
          T[i][j] = rnd.nextInt(2*z)-z;
      }
      System.out.println("\nWygenerowana tablica\n");
      for(int i=0; i<n; ++i) {
        for(int j=0; j<i+1; ++j) 
          System.out.print(T[i][j] + " ");
        System.out.println();
      } 
      
      System.out.println("\nTablica wyrównana w kolumnach\n");
      printSubdiagonal(T);
    }
    scn.close();
    System.out.println("KONIEC PROGRAMU");
  }  // main()
  
  // Wydruk tablicy prostokątnej z wyrównaniem prawostronnym kolumn
  static void printSubdiagonal(int[][] T) {
    int n = T.length;       // Liczba wierszy
    
    int[] sk = new int[n];  // Szeroko�ci kolumn
    for(int j=0; j<n; ++j) {
      sk[j] = 0;
      for(int i=j; i<n; ++i)
        sk[j] = Math.max(sk[j], (""+T[i][j]).length());
    }
    for(int i=0; i<n; ++i) {
      for(int j=0; j<i+1; ++j) 
        System.out.printf("%" + sk[j] + "d ",T[i][j]);
      System.out.println();
    } 
  }
}  // Klasa Zadanie2TablicaSubdiagonalna  
