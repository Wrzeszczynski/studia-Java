// Program 0, plik HelloWorld.java 
// import java.lang.*;

public class PPJ00_HelloWorld {  
  
  public static void main(String[] args) { 
    System.out.println("Hello, World!");
    System.out.println(
      "1234567890123456789012345678901234567890");
    PPJ00_HelloWorld obj = new PPJ00_HelloWorld();
	  System.out.println("Hello, " + obj);
	  for(String s: args)
	    System.out.print(s+" ");
	  System.out.println();
  }
}
