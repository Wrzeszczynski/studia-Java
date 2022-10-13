import java.util.Arrays;

public class PPJ04_SortArrays {  
  public static void main(String[] args) {  

    String[] liczby = {"zero", "jeden", "dwa", "trzy", "cztery"};
    
    System.out.println("\nWykaz przed sortowaniem");
    System.out.println(Arrays.toString(liczby));
    
    System.out.println("\nPo sortowaniu \"naturalnym\"");
    Arrays.sort(liczby);
    System.out.println(Arrays.toString(liczby));

    System.out.println("\nPo sortowaniu malejącym");
    Arrays.sort(liczby, (a, b) -> b.compareTo(a));
    System.out.println(Arrays.toString(liczby));

    System.out.println("\nPo sortowaniu wg długości słowa");
    Arrays.sort(liczby, (a, b) -> a.length() - b.length());
    System.out.println(Arrays.toString(liczby));
  
    System.out.println("\nPo sortowaniu wg drugiej litery słowa");
    Arrays.sort(liczby, (a, b) -> a.charAt(1) - b.charAt(1));
    System.out.println(Arrays.toString(liczby));

    System.out.println("\nPo sortowaniu wg ostatniej litery słowa");
    Arrays.sort(liczby, (a, b) -> a.charAt(a.length()-1) - b.charAt(b.length()-1));
    System.out.println(Arrays.toString(liczby));  
    
    System.out.println(liczby); // [Ljava.lang.String;@816f27d
  }
}

