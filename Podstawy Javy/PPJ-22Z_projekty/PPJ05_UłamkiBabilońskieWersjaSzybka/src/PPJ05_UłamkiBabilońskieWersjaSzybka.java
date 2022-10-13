/* Rozkład liczby wymiernej na czść całkowitą i ułamki babilońskie postaci 1/m. 
   Zastosowana metoda zachłanna: kolejny ułamek babiloński w rozkładzie 
   jest największy z możliwych.
   Program działa poprawnie, jeśeli nie następuje przekroczenie 
   zakresu reprezentacji liczb typu long. 

    Na przykład:
       2/3 = 1/2 + 1/6
       19/23 = 1/2 + 1/4 + 1/14 + 1/215 + 1/138460
       211/321 = 1/2 + 1/7 + 1/70 + 1/5618 + 1/63118230
       
   Uwaga: program może działać błędnie dla pewnych ułamków
   z powodu przekroczenia zakresu reprezentacji long.
   Kontrolę poprawności przeprowadzono sumując ułamki babilońskie
   i porównując wynik z oryginałem.
*/

import java.util.*; // Scanner, ArrayList

public class PPJ05_UłamkiBabilońskieWersjaSzybka {  
  static final Ulamek ZERO = new Ulamek();
  static final Ulamek ONE  = new Ulamek(1);
  
  public static void main(String[] args) {  
    
    Scanner scn = new Scanner(System.in);
    while(true) {
      Ulamek u; //Ułamek rozkładany na składniki "babilońskie"
       
      System.out.print("\nPodaj ułamek: licznik/mianownik: ");
      u = Ulamek.readUlamek(scn);
       
      if(Ulamek.eq(u, ZERO)) break;  // Normalne zakończenie programu
      if(u.lt(ZERO)) {
        System.out.println("Tylko liczby nieujemne!");
        continue; 
      }
      
      ArrayList<Ulamek> babTab = toBabylon(u);  // Konwersja
      System.out.print(u + " = "); // Początek wydruku

      // Prezentacja  i kontrola poprawności
      int n = babTab.size()-1;      
      for(int i=0; i<=n; ++i) {
        System.out.print(babTab.get(i));
        if(i<n) System.out.print(" + ");
      }
      
      // Kontrola przez sumowanie
      Ulamek suma = ZERO;
      for(Ulamek e: babTab) suma = suma.add(e);
      System.out.println("\nWynik sumowania: " + suma);
      if(suma.getLicznik() != u.getLicznik())
        System.out.println("Przekroczony zakres long podczas konwersji.");
    } 
    scn.close();
    System.out.println("\nKONIEC PROGRAMU");
  }
  
  static public ArrayList<Ulamek> toBabylon(Ulamek u) {
    ArrayList<Ulamek> wynik = new ArrayList<>();
    
    // Część całkowita
    if(u.gt(ONE)) {
      Ulamek whole = new Ulamek(u.getLicznik()/u.getMianownik());
      wynik.add(whole);
      u = u.sub(whole);
    }
    
    // u jest ułamkiem albo zerem: wyznacz ułamki babilońskie
    //while(u.gt(ZERO)) {
    while(u.ne(ZERO)) {
      long p = u.getLicznik(), q = u.getMianownik();  // u = p/q
      
      // Wyznacz mianownik m następnego ułamka babilońskiego
      // p/q >= 1/m ==> m>=q/p; zatem m = (q+p-1)/p = 1+(q-1)/p;
      Ulamek bab = new Ulamek(1, 1+(q-1)/p);
      wynik.add(bab);
      u = u.sub(bab);
    }
    return wynik; // Nie ma gwarancji poprawności - kontrola u wywołującego
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
  //public Ulamek(Ulamek u) { this(u.l, u.m); }// kopiujący - Równoważny z powyższym
  
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
  
  // Realizacja interfejsu Comparable<Ulamek>
  public int compareTo(Ulamek u) {
    Ulamek dif = this.sub(u);
    //return (int) dif.l;   // Może dawać wynik błędny
    return (dif.l<0)? -1 : ((dif.l>0)? 1: 0);
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

} // Ulamek

/*
Podaj ułamek: licznik/mianownik: 12/17
12/17 = 1/2 + 1/5 + 1/170

Podaj ułamek: licznik/mianownik: 211/321
211/321 = 1/2 + 1/7 + 1/70 + 1/5618 + 1/63118230

Podaj ułamek: licznik/mianownik: 0/0
KONIEC PROGRAMU
*/
