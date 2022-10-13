// Zliczanie słów różych z wejścia in (Set<String>)

import java.util.Scanner; 
import java.util.TreeSet;

public class PPJ10_ZliczanieSlow {    
  
  public static void main(String[] args) throws Exception {
    TreeSet<String> words = new TreeSet<String>();
    Scanner scn = new Scanner(System.in);
 
    System.out.println("Wpisz tekst (Ctrl+Z kończy wprowadzanie)\n");

    // Pamiętaj: String is immutable!
    while (scn.hasNext()) {
      String word = scn.next();
      
      word = word.replaceAll("[^\\p{IsAlphabetic}]", "");
      word.replaceAll("[^\\p{IsAlphabetic}]", "");
      words.add(word);
      //System.err.println(word);
    }

    System.out.println("Liczba słów różnych: " + words.size()); 
    
    for(String w: words)
      System.out.println(w);
    
    scn.close();   
  }
}
