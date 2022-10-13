//Program rozkłada na czynniki pierwsze liczby
//wprowadzane przez użytkownika.

import java.util.Scanner;   

public class PPJ04_FaktoryzacjaLong {
  
  public static void main(String[] args) {  
  
    long n;         // n - liczba do rozk�adu na czynniki
    long[] czynniki = new long[64];
    Long[] factors = new Long[64];
    long t1, t2, t3;  // Do pomiaru czasu
   
    System.out.println("Program wyznacza czynniki pierwsze liczb.");
    System.out.println("Liczba n <= 1 kończy program\n");

    try (Scanner scn = new Scanner(System.in);) {
      while(true) { // Konwersacja 
       
        System.out.print("\nPodaj liczbę >=1: n = ");
        n = scn.nextLong();
        if(n<=1) break;
        
        t1 = System.currentTimeMillis();
        int licznik = faktoryzuj(n, czynniki);
        t2 = System.currentTimeMillis();
        int count  = faktoryzujLong(n, factors);
        t3 = System.currentTimeMillis();
         
        System.out.print(n + " = " + czynniki[0]);
  
        for(int i=1; i<licznik; ++i)
          System.out.print("*" + czynniki[i]);
        System.out.println();
  
        System.out.print(n + " = " + factors[0]);
  
        for(int i=1; i<count; ++i)
          System.out.print("*" + factors[i]);
        System.out.println();
  
        System.out.println("Czas obliczeń dla long: " +(t2-t1));
        System.out.println("Czas obliczeń dla Long: " +(t3-t2));
      }
    }
    System.out.print("Koniec programu");
    //scn.close(); niepotrzebne (blok try to załatwia)
  }
  
  // Rozkład n na czynniki pierwsze; zwraca liczbę czynników.
  // Warunek wstępny: n>1
  public static int faktoryzuj(long n, long[] czynniki) {
    long dzielnik;
    int licznik = 0;  // liczba czynników
    
    while((n>1) && (n%2==0)) {
      czynniki[licznik++] = 2;
      n /= 2;
    }
     
    // Podzielniki nieparzyste
    dzielnik = 3;
    while(n>1) {
      while((n>1) && (n%dzielnik==0)) {
        czynniki[licznik++] = dzielnik;
        n /= dzielnik;
      }
      dzielnik +=2;
    }
    return licznik;    
  }

  // Rozkład n na czynniki pierwsze; zwraca liczbę czynników.
  // Obliczenia prowadzone na obiektach typu Long.
  // Warunek wstępny: n>1
  public static int faktoryzujLong(Long n, Long[] czynniki) {
    Long dzielnik;
    int licznik = 0;  // liczba czynników
    
    while((n>1) && (n%2==0)) {
      czynniki[licznik++] = 2L;
      n /= 2;
    }
     
    // Podzielniki nieparzyste
    dzielnik = 3L;
    while(n>1) {
      while((n>1) && (n%dzielnik==0)) {
        czynniki[licznik++] = dzielnik;
        n /= dzielnik;
      }
      dzielnik +=2;
    }
    return licznik;    
  } 
}

/*
Podaj liczbe >=1: n = 1234567890987654321
1234567890987654321 = 3*3*7*19*928163*1111211111
1234567890987654321 = 3*3*7*19*928163*1111211111
Czas obliczeń dla long: 7200
Czas obliczeń dla Long: 10683
*/

/*
Zbadaćliczby pierwsze:
n = 100000007
n = 2147483647
n = 1234567891
*/
