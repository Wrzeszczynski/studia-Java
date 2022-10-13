import javax.swing.JOptionPane;

// Testowanie operacji dwuargumentowych dla argumentów int
class PPJ02_OperatoryBinarne {  
	
  public static void main(String[] arg) {
    String arg1, arg2, op, wynik;
    Integer a1, a2, w=0;	// w = a1 op a2;
    
    System.out.println("Operacje dwuargumentowe dla argumentów int\n");
    
    while(true)	{ // Pętla konwersacji
      boolean koniec = false;
      
	    // Pobieranie danych i operatora
	    arg1 = JOptionPane.showInputDialog(null, "Argument1: ");
	    arg2 = JOptionPane.showInputDialog(null, "Argument2: ");
	    op   = JOptionPane.showInputDialog(null, "Operator (+ - * / % ^ end): ");
	
	    // Obliczanie wyniku
	    a1 = Integer.parseInt(arg1);
	    a2 = Integer.parseInt(arg2);
	    switch(op) {
	    	case "+": w = a1 + a2; break;
	    	case "-": w = a1 - a2; break;
	    	case "*": w = a1 * a2; break;
	    	case "/": w = a1 / a2; break;
	    	case "%": w = a1 % a2; break;
	    	case "^": w = a1 ^ a2; break;
	    	case "end": 
	    	default:  koniec = true;
	    }
	    
      if(!koniec) {
        wynik = a1+op+a2+" = "+w;
        System.out.println(wynik);  // Wynik w oknie konsoli
        
        // Wynik w oknie graficznym
        JOptionPane.showMessageDialog(null, wynik);
        continue;
      }
      else
	      if(op.equals("end")) break;
	    
      System.out.println("Nieznany operator "+op);
      JOptionPane.showMessageDialog(null, "Nieznany operator "+op);
      continue;
	    
	  } 
    System.out.println("\nKoniec programu");  
  }
}
