// Dziedziczenie, klasy i metody abstrakcyjne  
// plik FiguryGeometryczne.java

//Figury geometryczne   
abstract class Figura {         // Klasa abstrakcyjna
  abstract void rysuj();        // Metoda abstrakcyjna
  void transform(){ /* ... */ } // Metoda wirtualna "zwykła"
  // inne metody
}

class Wielobok extends Figura { 
  Wielobok()  { System.out.println("Powstał wielobok"); }
  void rysuj() { System.out.println("Wielobok.rysuj()"); }
}

class Histogram extends Figura { 
  Histogram() { System.out.println("Powstał histogram"); }
  void rysuj() { System.out.println("Histogram.rysuj()"); }
}

class Bezier extends Figura { 
   Bezier()    { System.out.println("Powstała krzywa Beziera"); } 
   void rysuj() { System.out.println("Bezier.rysuj()"); }
}

public class PPJ07_FiguryGeometryczne {
  
  public static void main(String[] args) {
    
   //Figura ff = new Figura();  // Nie można tworzyć instancji
                                // klasy abstrakcyjnej Figura
   Figura hf = new Histogram();
   Figura wf = new Wielobok();
   Figura bf = new Bezier();
   Figura ef = new Elipsa();
   Figura lf = new Lamana();
   
   Figura[] figury = new Figura[]{ hf, bf, wf, hf, lf, ef };
   System.out.println("\nPrzed pokazem");
   rysuj(figury);
   rysuj1(figury);
   System.out.println("Po pokazie\n");
   
   //A a=new A(); // Niedopuszczalne - abstrakcyjna klasa
   B b=new B();   // OK, ostrzeżenie o nieużyteczności b
   System.out.println("b = new B(): " + b);
   
   // C c = new C();  // Błąd: klasa C jest abstrakcyjna

  }
  
  static void rysuj(Figura[] figury) { 
    int i;
    for(i=0; i<figury.length; ++i)
      figury[i].rysuj();

    System.out.println("\nLiczba figur: " + i);
  }

  static void rysuj1(Figura[] figury) { 
    int i = 0;
    for(Figura f: figury) {
      f.rysuj();
      ++i;
    }

    System.out.println("\nLiczba figur: " + i);
  }
}

abstract class FiguraZamknieta extends Figura {}
abstract class FiguraOtwarta   extends Figura {}

class Lamana extends FiguraOtwarta {  
  Lamana() { System.out.println("Powstała lamana"); }
  void rysuj() { System.out.println("Lamana.rysuj()"); }
}

class Elipsa extends FiguraZamknieta {
  Elipsa() { System.out.println("Powstała elipsa"); }
  void rysuj() { System.out.println("Elipsa.rysuj()"); }
}

abstract class A {}   // Składnia na to pozwala
class B extends A {}  // na to też
abstract class C { 
  public void show() {
    System.out.println("abstract class C");   
  }
}  

/*
abstract class Abc {
 default int size() { return 0; } // Error: tylko w interfejsach
 abstract void resize(int siz);
}
*/
