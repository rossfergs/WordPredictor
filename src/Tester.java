import java.util.Locale;
import java.util.Scanner;

public class Tester {
	
	Trees english;
	Trees german;
	
	public Tester() {
		english = new Trees("english");
		german = new Trees("german");
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
			System.out.println("B - Write dictionary to file");
			System.out.println("C - Read from saved dictionary");
			System.out.println("D - Predict Word");
			System.out.println("Q - Quit program");
            
            choice=getString("Please make a choice, and press ENTER: ");
        
            switch (choice.toUpperCase(Locale.ROOT))
            {
                case "A":
					chooseLanguageAdd();
                	break;

				case "B":
					chooseLanguageWrite();
					break;

				case "C":
					chooseLanguageRead();
					break;

				case "D":
					chooseLanguageFind();
					break;

                case "Q":
                	System.out.println("Exiting program");
					System.exit(0);
					break;

                default:
					System.out.println("That is not a valid choice, please try again");
					break;
            }
        } while (!exit);
	
	}

	private void chooseLanguageAdd() {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);

		switch(choice) {
			case "A":
				english.getInput(null);
				break;

			case "B":
				german.getInput(null);
				break;
		}

	}

	private void chooseLanguageFind() {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);


		switch(choice) {
			case "A":
				if(english.getRoot() != null) {
					english.predictWord();
					break;
				}
				else {
					System.out.println("Tree is empty.");
				}

			case "B":
				if(german.getRoot() != null) {
					german.predictWord();
					break;
				}
				else {
					System.out.println("Tree is empty.");
				}
		}

	}

	private void chooseLanguageWrite() {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);

		switch(choice) {
			case "A":
				english.writeDictionary(english.getRoot());
				break;

			case "B":
				german.writeDictionary(german.getRoot());
				break;
		}

	}

	private void chooseLanguageRead() {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);

		switch(choice) {
			case "A":
				english.readDictionary();
				break;

			case "B":
				german.readDictionary();
				break;
		}

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
