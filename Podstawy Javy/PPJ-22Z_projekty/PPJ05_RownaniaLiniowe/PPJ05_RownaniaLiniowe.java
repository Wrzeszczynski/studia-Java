/* A. Pająk, 2021
 * Rozwiązywanie układu równań liniowych w dziedzinie liczb wymiernych.
 * Współczynniki równań i wyrazy wolne mogą być generowane losowo albo
 * wprowadzane ręcznie przez użytkownika (TA OPCJA JEST POZOSTAWIONA
 * JAKO ĆWICZENIE). Aktualna wersja programu generuje współczynniki
 * całkowite z zadanego zakresu; można zmienić tt zachowanie na generację
 * współczynników wymiernych modyfikując wiersze 68, 69 w metodzie generate(). 
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import static java.lang.System.*;

public class PPJ05_RownaniaLiniowe {

  public static void main(String[] args) {

  	out.println("RÓWNANIA LINIOWE W DZIEDZINIE LICZB WYMIERNYCH\n");
  	out.println("Użytkownik podaje parametry całkowite n, z określające:");
  	out.println("  n - liczba równań i niewiadomych");
  	out.println("  z - zakres [-z..z) losowanych współczynników równań.");
  	out.println("Podanie z = 0 oznacza ręczne wprowadzanie współczynników");
  	out.println("i wyrazów wolnych (łącznie n*(n+1) liczb).");
  	out.println("Podanie n = 0 oznacza koniec wykonania programu.");
  	out.println("UWAGA: Równanie o współczynnikach wymiernych można");
  	out.println("       zastąpić równoważnym o współczynnikach całkowitych.");

  	int n, z;
  	Ulamek[][] M;	// Macierz rozszerzona układu równań
  	
  	try(Scanner scn = new Scanner(in)) {
  		while(true) {	// Konwersacja
  			out.print("\nLiczba równań i zakres generacji: n z = ");
  			n = scn.nextInt();
  			z = scn.nextInt();
  			
  			if(n <= 0) break;
  			M = new Ulamek[n][n+1];
  			if(z <= 0) break;	// ĆWICZENIE: Wprowadzanie ręczne zmiast break;
  			generate(M, z);
  			out.println("\nMacierz rozszerzona");			
  			printTab(M);
  			
  			try {
  			  Thread.sleep(200);
  				Ulamek[] X = solve(M);
  				out.println("\nMacierz naddiagonalna (schodkowa)");
  				printTab(M);
  				out.println("\nRozwiązanie");
  				out.println(Arrays.toString(X));
  			}
  			catch(Exception e) {
  				err.println(e.getMessage());
  				printTab(M);
  			}
  		}
    }
  	out.println("\nKoniec programu");
  }
  
  static void generate(Ulamek[][] T, int z) { // T: n wierszy, n+1 kolumn
  	Random rnd = new Random();
  	int n = T.length;
  	
  	for(int i=0; i<n; ++i)
  		for(int j=0; j<=n; ++j)
  			//T[i][j] = new Ulamek(rnd.nextInt(2*z)-z);											// Całkowite
  			T[i][j] = new Ulamek(rnd.nextInt(2*z)-z, rnd.nextInt(z)+1);	// Wymierne
  }
  
  static void printTab(Ulamek[][] T) {
    int m = T.length; // Liczba wierszy
    int n = m+1;			// Liczba kolumn
    
    int[] kolumny = new int[n];
    int totalwidth = 0;
    for(int j=0; j<n; ++j) {
      int wid = (""+j).length();
      for(int i=0; i<m; ++i) {
        if(T[i].length>j) {
          int width = (""+T[i][j]).length();
          if(width>wid) wid = width;
        }
      }
      kolumny[j] = wid;	// Zarejestruj szerokość kolumny j
      totalwidth += wid;// Uwzględnij w całkowitej szerokości wydruku
    }
    totalwidth += n-1; // Uwzględnij odstępy między kolumnami
    
    // Wydruk
    
    // Nagłówek z numerami kolumn
    System.out.print("    ");
    for(int j=0; j<n; ++j)
      System.out.printf("%" + kolumny[j] + "d ", j);
    System.out.println();
    
    String separator = new String(new char[totalwidth]).replace('\0', '-');
    System.out.println("    "+separator);
    
    for(int i=0; i<m; ++i) {
      System.out.printf("%2d: ", i);     // Numer wiersza
      for(int j=0; j<n; ++j) {
      	String str = T[i][j].toString();
      	if(j!=n-2)
      		System.out.printf("%" + kolumny[j] + "s ", str);
      	else
      		System.out.printf("%" + kolumny[j] + "s|", str);
      }
      System.out.println();
    }   
  }
  
	// Metoda eliminacji Gaussa
  public static Ulamek[] solve(Ulamek[][] M) {
    int m = M.length;	// Liczba równań; m+1 liczba kolumn w macierzy rozszerzonej M

    // Tworzenie macierzy "schodkowej naddiagonalnej
    for(int r=0; r<m; ++r) {	// Dla każdej koluny (bez wyrazów wolnych)
    	// (1) Znajdz wiersz max poniżej r z największą wartością w kolumnie r 
      int max = r;
      for(int i=r+1; i<m; ++i)
        if(Ulamek.gt(M[i][r].abs(), M[max][r].abs())) max = i;

      // (2) Swap wiersz r i max i sprawdz czy macierz osobliwa
      Ulamek[] tmp = M[r]; M[r] = M[max]; M[max] = tmp;
      if(M[r][r].getLicznik() == 0L) 
        throw new ArithmeticException("Macierz osobliwa - brak rozwiązań");

      // Redukuj kolumnę poniżej r
      for(int i=r+1; i<m; ++i) {
        Ulamek d = Ulamek.div(M[i][r], M[r][r]);
        for(int j=r; j<=m; ++j) 
          M[i][j] = Ulamek.sub(M[i][j], M[r][j].mul(d));
      }
    }

    // Obliczanie pierwiastków rozwiązania
    Ulamek[] sol = new Ulamek[m];
    for(int i=m-1; i>=0; --i) {
      Ulamek suma = new Ulamek(0);
      for(int j=i+1; j<m; ++j)
        suma = Ulamek.add(suma, M[i][j].mul(sol[j]));
      sol[i] = Ulamek.div(M[i][m].sub(suma), M[i][i]);
   	}
   	return sol;
  }	// solve()
   
}	// PPJ05_RownaniaLiniowe

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
	
	public static Ulamek abs(Ulamek a) { return (a.l>=0)? a: neg(a); }
	public Ulamek abs() { return abs(this); }
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
	  
	  long licznik = Integer.parseInt(ss[0]);
	  if(ss.length>1) mianownik = Integer.parseInt(ss[1]);
	  return new Ulamek(licznik, mianownik);
	}  	
}  
