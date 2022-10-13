// Realizacja prostego stosu o ustalonej pojemności
// w wersji "zwykłej" i generycznej.

//import java.util.Random;

public class PPJ09_StosyTestowanie {
  public static void main(String[] args) throws Exception {  
    
    // Stos z tablicą Object[] 
    Stos s1 = new Stos(10);
    //s1.push(new Integer(11));	// Deprecated
    s1.push(11);
    s1.push(22);              // 22 "opakowane" do Integer
    s1.push("wstawka");
    s1.push(3.3);             // Opakowanie do Double
    System.out.println("Stos z tablicą Object[]");
    while(!s1.empty())
      System.out.print(s1.pop() + " ");
    System.out.println();
    
    //System.out.println(s1.pop()); // Będzie wyjątek
    
    // Stos generyczny 
    Stack<String> s2 = new Stack<>(10);
    //s2.push(new Integer(111)); // Error: Tylko String
    s2.push("111");
    s2.push("222");
    s2.push("WSTAWKA");  
    s2.push("333");
    System.out.println("Stos generyczny Stack<String>");
    while(!s2.empty())
      System.out.print(s2.pop() + " ");
    System.out.println();
    
    // Testowanie move()
    // Random gen = new Random();
    
    Stack<Number> sn = new Stack<>(20);
    sn.push(4.56); sn.push(7.89);
    
    Stack<Integer> si = new Stack<>(5);
    si.push(11); si.push(22); si.push(33);

//    for(int i=0; i<5; ++i) {
//      sn.push(gen.nextFloat()*10);
//      si.push(gen.nextInt(100));
//    }
    
    move(si, sn); // error dla wersji void move(Stack<T> src, Stack<T> dst)
    
    while(!sn.empty())
      System.out.print(sn.pop() + " ");
    System.out.println();
  }

/*
  // Taka operacja move() jest zbyt restrykcyjna  
  public static <T> void move(Stack<T> src, Stack<T> dst)
    throws Exception
  {
    while(!src.empty())
      dst.push(src.pop());
  
  }
*/

  // Stos "dostawca" może zawierać obiekty T lub podtypy T,
  // stos "odbiorca" może odbierać obiekty T lub nadtypy T.
  // Zasada PECS (Producer Extends, Consumer Super)
  public static <T> void move(
      Stack<? extends T> src, Stack<? super T> dst) throws Exception {
    
    while(!src.empty()) dst.push(src.pop());
  } // move()

} // PPJ09

// Wersja z tablicą Object[]
class Stos {
  private Object[] stos;
  private int top;  // Indeks wolnego miejsca

  public Stos(int size) {   
    stos = new Object[size];
    top = 0;  // Stos pusty
  }
  
  public void push(Object e) throws Exception {
    if(top == stos.length) throw new Exception("Stos pe�ny");
    stos[top++] = e;
  }

  public Object pop() throws Exception {   
    if (top == 0) throw new Exception("Stos pusty");
    Object e = stos[--top];
    stos[top] = null;  // Kasowanie rezydenta
    return e;
  }

  public boolean empty() { return top == 0; }
  public boolean full () { return top == stos.length; }
}

//Wersja generyczna
class Stack<E> {
  private E[] stack;
  private int top;  // Indeks wolnego miejsca

  @SuppressWarnings("unchecked")
  public Stack(int size) {
    //stack = new E[size];  // Error: nie można utworzyć E[]
    stack = (E[]) new Object[size];
    top = 0;  // Stos pusty
  }

  public void push(E e) throws Exception {
    if(top == stack.length) throw new Exception("Stack full");
    stack[top++] = e;
  }
  
  public E pop() throws Exception {
    if (top == 0) throw new Exception("Stack empty");
    E e = stack[--top];
    stack[top] = null;  // Kasowanie rezydenta
    return e;
  }
    
  public boolean empty() { return top == 0; }
  public boolean full () { return top == stack.length; }
}
