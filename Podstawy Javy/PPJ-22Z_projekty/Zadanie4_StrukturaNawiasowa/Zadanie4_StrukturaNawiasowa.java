import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie4_StrukturaNawiasowa {

  public static void main(String[] args) throws IOException {
    Scanner scn = new Scanner(System.in);
    
    System.out.print("Nazwa pliku-WE tekstowego: ");
    String fwe = scn.next();
    FileReader ifile = null;

    try {
      ifile = new FileReader(fwe);
      
      int c;                // Następny znak
      int np=0, nb=0, nk=0; // Liczba otwartych '()', '[]', '{}' 
      
      while((c = ifile.read()) != -1) {   
        System.out.print((char)c);
        if(c == '(') ++np; else
        if(c == ')') --np; else
        if(c == '[') ++nb; else
        if(c == ']') --nb; else
        if(c == '{') ++nk; else
        if(c == '}') --nk;
        
        if(np<0 || nb<0 || nk<0) break;
        
      } 
      if(np+nb+nk != 0)   // Brak bilansowania nawiasów
        System.out.println("\nNiezbilansowane nawiasy: " + 
          ((np!=0)?"()":"") + ((nb!=0)?"[]":"") + ((nk!=0)?"{}":""));
      else
        System.out.println("Nawiasy OK");
    } 
    finally {
      if(ifile != null) ifile.close();
    }
    scn.close();
  }
}
