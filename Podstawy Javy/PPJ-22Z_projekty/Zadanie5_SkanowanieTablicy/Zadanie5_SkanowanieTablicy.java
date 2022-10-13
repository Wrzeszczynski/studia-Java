import java.util.Arrays;
import java.util.Scanner;

public class Zadanie5_SkanowanieTablicy { 

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    
    System.out.println("Tworzenie tablic mXn wypełnionych liczbami");
    System.out.println("1 .. m*n wg zadanego schematu skanowania.");
    System.out.println("Obsługiwane schematy: LGx, LDx, PGx, PDx gdzie");
    System.out.println("x=w|k oznacza skanowanie wierszami albo kolumnami.");
    System.out.println("LG, LD, PG, PD oznaczają startowy narożnik.\n");

    System.out.println("Parametry od użytkownika: m n schemat, np. 5 7 LDk");
    String[] schematy = {"LGw","LGk","LDw","LDk","PGw","PGk","PDw","PDk"};
    System.out.print("\nMożliwe schematy\n");
    System.out.print(Arrays.toString(schematy));
    
    // Konwersacja
    while(true) {
      System.out.print("\nRozmiary tablicy: m n = ");
      int m = scn.nextInt();
      int n = scn.nextInt();
      if(m<=0 || n<=0) break;

      int[][] T = new int[m][n];
      System.out.print("Schemat skanowania (* --> wszystkie): schemat = ");
      String schemat = scn.next();
      if(schemat.equals("*")) { // Wszystkie
        for(String s : schematy) {
          skanuj(T, s);
          System.out.println("\nTablica " + s);
          printJustowany(T);
        }
      }
      else {   // Wybrany schemat: sprawd�, czy istnieje w wykazie
        if(!isIn(schematy, schemat)) {
          System.out.println("Schemat nieznany: "+schemat);
          break;
        }

        skanuj(T, schemat);
        System.out.println("\nTablica " + schemat);
        printJustowany(T);
      }
    }
    scn.close();
    System.out.println("KONIEC PROGRAMU");
  }  // main()
  
  // Czy tablica �a�cuch�w zawiera konkretny element
  static public boolean isIn(String[] lista, String ten) {
    for(String s: lista)
      if(s.equals(ten)) return true;
    return false;
  }
  
  // Wype�nianie tablicy wg schematu skanowania
  static public void skanuj(int[][] T, String schemat) { 
    int m = T.length;       // Liczba wierszy
    int n = T[0].length;    // Liczba kolumn
    
    // Ustaw indeksy pocz�tkowe i inkrementy wg schematu.
    // Indeksy: i, j; inkrementy: di, dj [i==>wiersz, j==>kolumna]
    int i=0, j=0, di=0, dj=0;
    int z = 0;  // 1 (-1): wiersze/kolumny o rosn�cych (malej�cych) numerach

    switch(schemat) {
      case "LGw": i=0;   j=0;   di= 0; dj= 1; z= 1; break;
      case "LDw": i=m-1; j=0;   di= 0; dj= 1; z=-1; break;
      case "PGw": i=0;   j=n-1; di= 0; dj=-1; z= 1; break;
      case "PDw": i=m-1; j=n-1; di= 0; dj=-1; z=-1; break;
      case "LGk": i=0;   j=0;   di= 1; dj= 0; z= 1; break;
      case "LDk": i=m-1; j=0;   di=-1; dj= 0; z= 1; break;
      case "PGk": i=0;   j=n-1; di= 1; dj= 0; z=-1; break;
      case "PDk": i=m-1; j=n-1; di=-1; dj= 0; z=-1; break;
    }

    for(int val=1; val<=m*n; ++val) {
      T[i][j] = val;    // Wype�nienie 
      i += di; j += dj; // Nast�pna pozycja w tablicy
      
      if(j< 0) { i+=z; j=0;   dj=-dj; } else  // Wyj�cie z wiersza w lewo
      if(j==n) { i+=z; j=n-1; dj=-dj; } else  // Wyj�cie z wiersza w prawo       
      if(i< 0) { j+=z; i=0;   di=-di; } else  // Wyj�cie z kolumny w g�r�
      if(i==m) { j+=z; i=m-1; di=-di; }       // Wyj�cie z kolumny w d� 
    }
  }

  // Wydruk tablicy prostok�tnej z wyr�wnaniem prawostronnym kolumn.
  // Odst�p mi�dzy kolumnami: 1 spacja.
  static void printJustowany(int[][] T) {
    int m = T.length;       // Liczba wierszy
    int n = T[0].length;    // Liczba kolumn
    
    int[] sk = new int[n];  // Szeroko�ci kolumn
    for(int j=0; j<n; ++j) {
      sk[j] = 0;
      for(int i=0; i<m; ++i)
        sk[j] = Math.max(sk[j], (""+T[i][j]).length());
    }
    for(int i=0; i<m; ++i) {
      for(int j=0; j<n; ++j) 
        System.out.printf("%" + sk[j] + "d ",T[i][j]);
      System.out.println();
    } 
  }
}
