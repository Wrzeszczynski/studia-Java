// Przekazywanie parametrów przez wartość
import java.util.*;  

public class PPJ02_SwapNieDziala {
  public static void main(String[] args) {  
    int x1 = 111, x2 = 999;
    Object o1 = new Object(), o2 = new Object();
    
    System.out.println("Przed swap:    x1=" + x1 + " x2=" + x2);
    System.out.println("Przed swap:    o1=" + o1 + " o2=" + o2);
    
    swap(x1, x2);
    swap(o1, o2);
    
    System.out.println("Po swap:       x1=" + x1 + " x2=" + x2);
    System.out.println("Po swap:       o1=" + o1 + " o2=" + o2);
    
    int[] A = { 1,2,3,4,5 };
    
    opNaA(A); // [1, 2, 3, 4, 5]
              // [1, 4, 9, 16, 25]
    
  }
  
  public static void opNaA(int[] A) {
    for(int e: A) e = e*e;  // Tablica A bez zmian
    System.out.println(Arrays.toString(A));

    for(int i=0; i<A.length; ++i)
      A[i] *=A[i];

    System.out.println(Arrays.toString(A));
  }
  
  static void swap(int a, int b) {
    int tmp = a; a = b; b = tmp;
    System.out.println("Wewnątrz swap: a=" + a + " b=" + b);
  }
  
  static void swap(Object a, Object b) {
    Object tmp = a; a = b; b = tmp;
    System.out.println("Wewnątrz swap: a=" + a + " b=" + b);
  }
  
}