import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Zadanie6_ScannerDlaPliku {

  public static void main(String[] args) throws IOException { //FileNotFoundException{
    Scanner scn = new Scanner(System.in);
    
    System.out.println("Program wczytuje tablice liczb ze wskazanego pliku i");
    System.out.println("wyprowadza je po uporządkowaniu w wierszach do innego.");
    System.out.println("Format danych w pliku: m n A1 A2 ... Amn. Plik może");
    System.out.println("zawierać wiele tablic - wszystkie będą przetworzone. \n");
    System.out.print("\nNazwa pliku wejściowego: ");
    String plikWE = scn.next();
    String plikWY = "wynikiDla_" + plikWE;
    System.out.println("Nazwa pliku wyjściowego: " + plikWY + "\n");
    
    try { // Otwieranie i przetwarzanie pliku  
      Scanner fin = new Scanner(new File(plikWE));
      PrintWriter pwr = new PrintWriter(plikWY);

      while(fin.hasNext()) {   
        int m = fin.nextInt();
        int n = fin.nextInt();
        pwr.println("\n"+ m + " " + n);
        System.out.println("\n" + m + " " + n);
        
        int[][] Tab = new int[m][n];
        for(int i=0; i<m; ++i)
          for(int j=0; j<n; ++j)
            Tab[i][j] = fin.nextInt();
        
        // Sortowanie w wierszach
        for(int i=0; i<m; ++i)
          Arrays.sort(Tab[i]);
        
        print(Tab, pwr);     
      }
      fin.close();
      pwr.close();
    }
    catch (FileNotFoundException e) {
      System.err.println("Nie można otworzyć pliku '" + plikWE +"'");
      //e.printStackTrace();
    }
    finally {
      scn.close();
    }
  }
  
  static void print(int[][] T, PrintWriter pw) {
    int m = T.length;       // Liczba wierszy
    int n = T[0].length;    // Liczba kolumn
    
    int[] sk = new int[n];  // Szerokości kolumn
    for(int j=0; j<n; ++j) {
      sk[j] = 0;
      for(int i=0; i<m; ++i)
        sk[j] = Math.max(sk[j], (""+T[i][j]).length());
    }
    for(int i=0; i<m; ++i) {
      for(int j=0; j<n; ++j) {
        System.out.printf("%" + sk[j] + "d ", T[i][j]);
        pw.printf("%" + sk[j] + "d ",T[i][j]);
      }
      System.out.println();
      pw.println();
    } 
  }
}
