//import java.util.Arrays; 
//import java.util.List; 
import java.util.*;

public class PPJ04_GenericClassTest {
  
  public static void main(String[] args) {  
    
    //Para<int, int> pii = new Para<>(1939, 1945); // Error
    // Typ pierwotny nie może być parametrem szablonu
    Para<Integer, Integer> pii = new Para<>(1939, 1945);
    System.out.println(pii);  // (1939, 1945)

    Para<Integer, String> pis = new Para<>(100, "sto");
    System.out.println(pis);  // (100, sto)

    Para<String, Para<String, String>> psp = 
        new Para<>("en2pl", new Para<>("one", "jeden"));
      System.out.println(psp);  // (en2pl, (one, jeden))
    
    Integer[] a = {1,2,3};
    String[] b = {"raz", "dwa", "trzy"};
    reverse(a); reverse(b);
    System.out.println(a);  // [Ljava.lang.Integer;@2a139a55
    System.out.println(b);  // [Ljava.lang.String;@15db9742
    System.out.println(Arrays.toString(a)); // [3, 2, 1]
    System.out.println(Arrays.toString(b)); // [trzy, dwa, raz]
    
    Integer[] x = new Integer[4];
    String[]  y = new String[4];
    x[0] = 1; x[1]=2; x[2]=3;
    y[0] = "raz"; y[1]="dwa"; y[2]="trzy";
    System.out.println("x=" + x); // x=[Ljava.lang.Integer;@6d06d69c
    System.out.println("y=" + y); // y=[Ljava.lang.String;@7852e922
    reverse(x);
    reverse(y);
    System.out.println("x=" + x); // x=[Ljava.lang.Integer;@6d06d69c
    System.out.println("y=" + y); // y=[Ljava.lang.String;@7852e922
    System.out.println("x=" + Arrays.toString(x));  // x=[null, 3, 2, 1]
    System.out.println("y=" + Arrays.toString(y));  // y=[null, trzy, dwa, raz]

    // Para<String, String>[] tpss = new Para<String, String>[10]; // Error
    // Cannot create a generic array of Para<String,String>
    Para<String, String>[] tpss = (Para<String, String>[]) new Para<?, ?>[10];

    tpss[0] = new Para<String, String>("A", "B");
    Object[] tob = new Object[10]; 
    tob[0] = new Para<String, String>("a", "b");
    
    System.out.println(tpss[0]);  // (A, B)
    System.out.println(tob[0]);   // (a, b)
    
    // Przykład wyrażenia lambda
    Runnable r = () -> System.out.println("Wyrażenie lambda");
    r.run();  // Wyrażenie lambda
    
    List<String> lista = new ArrayList<>();
    lista.add("xyz"); lista.add("pqr");
    System.out.println(lista);  // [xyz, pqr]
    
    System.out.println(Arrays.toString(args));  // np. [a, b, c, d]
  }
  
  static <T> void reverse(T[] tab) {
    int n = tab.length;
    T temp;
    for(int i=0; i<n/2; ++i) {
      temp = tab[i];
      tab[i] = tab[n-1-i];
      tab[n-1-i] = temp;
    }      
  }

}

// K, V to nazwy parametrów reprezentujących typy
class Para<K, V> {
  private K key;
  private V val;
  //public Para() { key = null; val = null; }
  public Para(K key, V val) { this.key = key; this.val = val; }
  public K getKey() { return key; }
  public V getVal() { return val; }
  public void setKey(K newKey) { key = newKey; }
  public void setVal(V newVal) { val = newVal; }
  public String toString() { return "("+key+", "+val+")"; }
}
