import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Zadanie3_WielkieLiczby { 
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    Random rnd = new Random();  
    
    // Test BigInteger
    System.out.println("Przykłady użycia BigInteger\n");
    
    while(true) {
      // Ograniczenie 100 jest arbitralne - przykładowe
      System.out.print("\nLiczba bitów generowanej liczby (<=100): n = ");
      int n = scn.nextInt();
      if(n<=0 || n>100) break;
    
      BigInteger x = new BigInteger(n, rnd);
      BigInteger y = new BigInteger(n, rnd);
      System.out.println("Wylosowane liczby "+n+"b: ");
      System.out.println("x   = " + x.toString(10)); 
      System.out.println("y   = " + y.toString(10)); 
      System.out.println("x+y = " + x.add(y)); 
      System.out.println("x-y = " + x.subtract(y)); 
      System.out.println("x*y = " + x.multiply(y)); 
      System.out.println("x%y = " + x.mod(y));
      System.out.println("n**n(10) = " + new BigInteger(""+n).pow(n)); 
      System.out.println("n**n(16) = " + (new BigInteger(""+n).pow(n)).toString(16)); 
      System.out.println("#cyfr10(n**n) = " + (new BigInteger(""+n).pow(n)).toString().length()); 
      System.out.println("#cyfr16(n**n) = " + (new BigInteger(""+n).pow(n)).toString(16).length()); 
      System.out.println("n!  = " + silnia(n)); 
      // ...
    }  
    scn.close();
    System.out.println("KONIEC PROGRAMU");
  }
  
  // Obliczanie n!  
  static BigInteger silnia(int n) {
    BigInteger fact = new BigInteger("1");

    for(int i=2; i<=n; ++i)
      fact = fact.multiply(new BigInteger(""+i));
    return fact;
    
  }  
}
