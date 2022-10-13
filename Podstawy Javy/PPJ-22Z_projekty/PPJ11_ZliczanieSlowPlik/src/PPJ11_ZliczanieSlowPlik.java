/* Zliczanie słów różnych zawartych we wskazanym pliku tekstowym.
 * Plik powinien znajdować się w katalogu projektu (tam gdzie
 * podkatalog src).
 * 
 * Przez "słowo" rozumiemy dowolny ciąg znaków do "białej plamy",
 * czyli spacji, tabulatora, końca wiersza. Na przykład wiersz:
 * 
 *  (a+b)*c   ==   c(a+b)
 *  
 *  zawiera 3 słowa.
 * 
 * Na pytanie o nazwę pliku należy podać tylko nazwę i rozszerzenie,
 * np. PuchaczKicia_UTF-8.txt.
 */

import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;   

public class PPJ11_ZliczanieSlowPlik {  
  
  public static void main(String... args) throws Exception {

    TreeSet<String> words = new TreeSet<String>();
    Scanner scn = new Scanner(System.in);
    
    System.out.print("Nazwa pliku tekstowego: ");
    String nazwaPliku = scn.next();
    Scanner fin = new Scanner(new File(nazwaPliku));

    System.out.println("Pobieranie słów z pliku \"" + nazwaPliku + "\"");

    while (fin.hasNext()) {
      String word = fin.next();
      words.add(word);
    }
    if(fin != null) fin.close();  // Lepiej użyć try na zasobie
    
    System.out.println("Liczba różnych słów w pliku: " + words.size()); 
    
    for(String w: words)
      System.out.println(w);
    
    scn.close();  
  }
}
