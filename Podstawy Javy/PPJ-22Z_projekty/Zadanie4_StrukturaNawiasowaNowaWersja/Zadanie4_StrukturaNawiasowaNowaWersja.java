import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie4_StrukturaNawiasowaNowaWersja {

  public static void main(String[] args) throws IOException {
    Scanner scn = new Scanner(System.in);
    
    System.out.print("Nazwa pliku-WE tekstowego: ");
    String fwe = scn.next();
    FileReader ifile = null;

    try {   
      ifile = new FileReader(fwe);
      
      int c;                // Następny znak
      int np=0, nb=0, nk=0; // Liczba otwartych '()', '[]', '{}' 
      boolean okp=true, okb=true, okk=true; // Na razie wszystko OK 
      
      while((c = ifile.read()) != -1) {   
        System.out.print((char)c);
        switch(c) {
          case '(': ++np; break;
          case ')': --np; okp = okp && np>=0; break;
          
          case '[': ++nb; break;
          case ']': --nb; okb = okb && nb>=0; break;
  
          case '{': ++nk; break;
          case '}': --nk; okk = okk && nk>=0; break;
  
          default : break;        
        }
      } 
      
      if(!okp) System.out.println("Wystąpił nadmiarowy ')'");
      if(!okb) System.out.println("Wystąpił nadmiarowy ']'");
      if(!okk) System.out.println("Wystąpił nadmiarowy '}'");

      if(np!=0) System.out.println("Brak bilansowania '()'");
      if(nb!=0) System.out.println("Brak bilansowania '[]'");
      if(nk!=0) System.out.println("Brak bilansowania '{}'");

      System.out.println("\nnp="+np + ", nb="+nb +", nk="+nk);
    } 
    finally {
      if(ifile != null) ifile.close();
    }
    scn.close();
  }
}
