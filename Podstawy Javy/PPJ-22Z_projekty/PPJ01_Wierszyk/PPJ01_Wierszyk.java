// Znaki specjalne w łańcuchach

public class PPJ01_Wierszyk {  
  
  public static void main(String[] args) {  
	   
    String treść = 
      "\nKiedy Puchacz i Kicia wyruszyli w rejs życia" +
      "\nW zgrabnej łódce groszkowozielonej" +
      "\nWzięli z sobą parówki, duży zapas gotówki" +
      "\nI słój miodu z nalepką \"The Honey\"." +
      "\n    Edward Lear (tł. S.Barańczak) (\u00B1 \u00AE)\n";
                                          //   ±      ®    
    
    String textBlock = 
    """
    Kiedy Puchacz i Kicia wyruszyli w rejs życia
    W zgrabnej łódce groszkowozielonej
    Wzięli z sobą parówki, duży zapas gotówki
    I słój miodu z nalepką "The Honey".
        Edward Lear (tł. S.Barańczak) (\u00B1 \u00AE) 
    """;
                                            //   ±      ®    

	   System.out.println(treść);
     System.out.println(textBlock);
	   
	   Object o = new Object();
	   System.out.println("o.toString() = " +o.toString());

	   int a =22;  
	   
	   // Uwaga na pułapki
	   System.out.println("a = " + a);
	   System.out.println("a + 1 = " + a + 1 );	   
  }
}
