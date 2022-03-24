import java.util.Scanner;

public class Tester {
	
	Trees myTree;
	
	public Tester() {
		myTree = new Trees();
	}
	
	public static void main (String [] args) {
		Tester t = new Tester();
		t.menu();
	}
	
	public void menu() {
	 	String choice;
        boolean exit=false;
        
        do
        {
        	System.out.println("------------------------------");
            System.out.println("Binary Trees");
            System.out.println("A - Add word to dictionary");
            
            choice=getString("Please make a choice, and press ENTER: ");
        
            switch (choice)
            {
                case "a":
                case "A":
                	myTree.getInput(null);
                	break;
                case "Q":
                case "q": 
                	System.out.println("Exiting program");
                      System.exit(0);
                        break;
                default: System.out.println("That is not a valid choice, please try again");
                        break;         
            }
        }while (!exit);
	
}
	private String getString(String userPrompt) {
		Scanner s = new Scanner(System.in);
		System.out.print(userPrompt);
		while (!s.hasNext())
		{
			s.next();
			System.out.print(userPrompt);
		}
		return s.next();	
	}
}
