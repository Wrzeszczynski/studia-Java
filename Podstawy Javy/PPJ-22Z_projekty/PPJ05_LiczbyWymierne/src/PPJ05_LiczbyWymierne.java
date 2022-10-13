// Test klasy Ulamek

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class PPJ05_LiczbyWymierne {   
  
  public static void main(String[] args) {  
    Ulamek a = new Ulamek();
    Ulamek b = new Ulamek();
    Ulamek zero = new Ulamek(0, 1);
    
    Scanner scn = new Scanner(System.in);
    
    System.out.println("Wprowadzane liczby wymierne mają postać:");
    System.out.println("  licznik   albo   licznik/mianownik\n");
    
    while(true)  // Konwersacja
    { 
      System.out.print("Pierwsza liczba a = "); a = Ulamek.readUlamek(scn);
      System.out.print("Druga    liczba b = "); b = Ulamek.readUlamek(scn);

      System.out.println("a = " + a); 
      System.out.println("b = " + b);

      System.out.println("a.add(b) == " + a.add(b));
      System.out.println("add(a,b) == " + Ulamek.add(a,b));

      System.out.println("a.sub(b) == " + a.sub(b));
      System.out.println("sub(a,b) == " + Ulamek.sub(a,b));

      System.out.println("a.mul(b) == " + a.mul(b));
      System.out.println("mul(a,b) == " + Ulamek.mul(a,b));

      System.out.println("a.div(b) == " + a.div(b));
      System.out.println("div(a,b) == " + Ulamek.div(a,b));

      System.out.println("med(a,b) == " + Ulamek.med(a,b));

      System.out.println("(a> b)  == " + a.gt(b));
      System.out.println("(a>=b)  == " + a.ge(b));
      System.out.println("(a< b)  == " + a.lt(b));
      System.out.println("(a<=b)  == " + a.le(b));
      System.out.println("a.eq(b) == " + a.eq(b));
      System.out.println("a.ne(b) == " + a.ne(b));
      
      System.out.println("(a==b)  == " + (a==b));
      System.out.println("(a.equals(b) == " + a.equals(b));
      
      if(a.eq(zero) && b.eq(zero)) break;
    }
      
    // Sortowanie tablicy ułamków
    System.out.println("\nGenerowanie i sortowanie tablicy ułamków");
    Random rnd = new Random();
    
    System.out.print("Ile liczb wymiernych i zakres generacji: n z = ");
    int n = scn.nextInt();
    int z = scn.nextInt();
    
    if(n>0 && z>0) { // Dane prawdopodobnie OK
      Ulamek[] ut = new Ulamek[n];
    
      for(int i=0; i<n; ++i) //{
        ut[i] = new Ulamek(rnd.nextInt(z), rnd.nextInt(z));

      System.out.println("Przed sortowaniem");
      System.out.println(Arrays.toString(ut));

      Arrays.sort(ut);
      System.out.println("\nPo sortowaniu");
      System.out.println(Arrays.toString(ut));
      System.out.println();
    }
    else
      System.out.println("Błędne dane");

    System.out.println("Koniec programu"); 
    System.out.println("Utworzono obiektów Ulamek: " +Ulamek.liczbaInstancji);
      
  }
}

//-------- Definicja klasy Ulamek

class Ulamek implements Comparable<Ulamek> {
  public static int liczbaInstancji = 0;
  private long l, m; // licznik, mianownik 
    
  // Inicjuje składowe l, m obiektu według argumentów a, b;
  // postać znormalizowana ułamka: nieskracalna, m >=0.
  private void initUlamek(long a, long b) {
    ++liczbaInstancji;
    long d = nwp(a, b);
    if(b<0) { a = -a; b = -b; }      // Mianownik zawsze nieujemny
    l = a/d; m = b/d;
  }
  // 4 wersje konstruktorów
  public Ulamek(long a, long b){ initUlamek(a, b); }
  public Ulamek(long a){ initUlamek(a, 1); }
  //public Ulamek(long a) { this(a, 1); }      // Równoważny z powyższym
  public Ulamek(){ initUlamek(0, 1); }
  //public Ulamek() { this(0, 1); }            // Równoważny z powyższym
  public Ulamek(Ulamek u){ initUlamek(u.l, u.m); }
  //public Ulamek(Ulamek u) { this(u.l, u.m); }// kopiujący - Równowa�ny z powyższym
  
  public long getLicznik()    { return l; }
  public long getMianownik()  { return m; }
  public void setLicznik  (long nowyl) { initUlamek(nowyl, this.m); }
  public void setMianownik(long nowym) { initUlamek(this.l, nowym); }
  
  public static Ulamek add(Ulamek a, Ulamek b) {
    long tl, tm;
    if(a.m==0 && b.m==0) { 
      tl = (a.l+b.l)/2;  tm = 0; 
    }
    else { 
      tl = a.l*b.m + a.m*b.l; 
      tm = a.m*b.m;
    }
    return new Ulamek(tl, tm);
  }
  
  public Ulamek add(Ulamek b) {
    return Ulamek.add(this, b);
  }
  
  public Ulamek sub(Ulamek b) { 
    long tl, tm;
    if(this.m==0 && b.m==0) 
    { tl = (this.l-b.l)/2;    tm = 0; }
    else           
    { tl = this.l*b.m - this.m*b.l; tm = this.m*b.m; }
    return new Ulamek(tl, tm);
  }
  
  public static Ulamek sub(Ulamek a, Ulamek b) {
    return a.sub(b);
  }
  
  public Ulamek mul(Ulamek b)
  { long d1,d2;
    d1 = nwp(this.l, b.m);
    d2 = nwp(this.m, b.l);
    return new Ulamek((this.l/d1)*(b.l/d2) , (this.m/d2)*(b.m/d1) );
  }
  
  public static Ulamek mul(Ulamek a, Ulamek b) {
    return a.mul(b);
  }
  
  public Ulamek div(Ulamek b)
  { long d1,d2;
    d1 = nwp(this.l, b.l);
    d2 = nwp(this.m, b.m);
    return new Ulamek((this.l/d1)*(b.m/d2) , (this.m/d2)*(b.l/d1));
  }
  
  public static Ulamek div(Ulamek a, Ulamek b) {
    return a.div(b);
  }
  
  public Ulamek neg() {
    return new Ulamek(-this.l, this.m); 
  }
  
  public static Ulamek neg(Ulamek a) {
    return new Ulamek(-a.l, a.m); 
  }
  
  // Medianta pary liczb wymiernych
  public static Ulamek med(Ulamek a, Ulamek b) {
    return new Ulamek(a.l+b.l, a.m+b.m);
  }
  
  public Ulamek med(Ulamek b) {
    return med(this, b);
  }
  
  // Relacje eq, ne, gt, ge, lt, le
  public  static boolean eq(Ulamek a, Ulamek b) {
    return a.sub(b).l==0;
  }
  
  public static boolean ne(Ulamek a, Ulamek b) {
    return !Ulamek.eq(a,b);    
  }
  
  public static boolean gt(Ulamek a, Ulamek b) {
    return a.sub(b).l>0;
  }
  
  public  static boolean ge(Ulamek a, Ulamek b) {
    return !lt(a, b);    
  }
  
  public static boolean lt(Ulamek a, Ulamek b) {
    return a.sub(b).l<0;
  } 
  
  public static boolean le(Ulamek a, Ulamek b) {
    return !gt(a, b);    
  }
  
  public boolean eq(Ulamek b) { return eq(this, b); }   
  public boolean ne(Ulamek b) { return ne(this, b); } 
  public boolean gt(Ulamek b) { return gt(this, b); } 
  public boolean ge(Ulamek b) { return!lt(this, b); } 
  public boolean lt(Ulamek b) { return lt(this, b); } 
  public boolean le(Ulamek b) { return!gt(this, b); } 
    
  public String toString() { 
    String str = "" + this.l;
    if(this.m != 1)
      str += "/" + this.m;
    return str;
  }
  
  @Override
  public boolean equals(Object o) {
  //public boolean equals(Ulamek o) {
    if(! (o instanceof Ulamek)) return false;
    return this.eq((Ulamek)o);
  }
  
  // Realizacja interfejsu Comparable<Ulamek>
  public int compareTo(Ulamek u) {
    Ulamek dif = this.sub(u);
    return (int) dif.l;
  }
   
  // -----
  // Najwiekszy wspólny podzielnik
  public static long nwp(long p, long q) {
    long t;
    p = Math.abs(p); q = Math.abs(q);  // nwp zawsze dodatni
  
    if(p == 0)
      if(q == 0) return 1; else return q;
    else
    if(q == 0) return p;
  
    // p>0, q>0
    while((t=p%q) !=0) { p = q; q = t; }
    return q;
  }
  
  // -----
  // Metoda czytania Ulamka ze strumienia spodziewa się
  // postaci: licznik albo licznik/mianownik
  public static Ulamek readUlamek(Scanner is) { 
    String s = is.next();
    String[] ss = s.split("/");
    long mianownik = 1;  
    
    long licznik = Long.parseLong(ss[0]);
    if(ss.length>1) mianownik = Long.parseLong(ss[1]);
    return new Ulamek(licznik, mianownik);
  }  

}

