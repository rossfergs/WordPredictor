
import java.util.Locale;
import java.util.Scanner;
import java.io.*;
public class Tester {
	
	Trees english;
	Trees german;

	/**
	 * constructor used to set up fields
	 */
	public Tester() {
		english = new Trees("english");
		german = new Trees("german");
	}

	/**
	 * main method
	 *
	 * @param args java args
	 */
	public static void main (String [] args) {
		Tester t = new Tester();
		t.menu();
	}

	/**
	 * method used to choose what to do in a menu
	 */
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
			System.out.println("D - Delete Word");
			System.out.println("E - Predict Word");
			System.out.println("F - Show Tree");

			System.out.println("Q - Quit program");
            
            choice=getString("Please make a choice, and press ENTER: ");
        
            switch (choice.toUpperCase(Locale.ROOT))
            {
                case "A":
					chooseLanguageAdd();
                	break;

				case "B":
					try{
						chooseLanguageWrite();
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					break;

				case "C":
					chooseLanguageRead();
					break;

				case "D":
					chooseLanguageDelete();
					break;

				case "E":
					chooseLanguageFind();
					break;

				case "F":
					chooseLanguageShow();
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

	/**
	 * method used to choose language for add method
	 */
	public void chooseLanguageAdd() {
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

	/**
	 * method used to choose language for find method
	 */
	public void chooseLanguageFind() {
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

	/**
	 * method used to choose language for write method
	 */
	public void chooseLanguageWrite() throws FileNotFoundException {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);

		switch(choice) {
			case "A":
				try {
					english.writeDictionary(english.getRoot(),new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream("english" + ".txt"), "utf-8")));
				}
				catch(Exception e) {
					System.out.println("Error");
				}
				break;

			case "B":
				try {
					german.writeDictionary(english.getRoot(),new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream("german" + ".txt"), "utf-8")));
				}
				catch(Exception e) {
					System.out.println("Error");
				}
				break;
		}

	}

	/**
	 * method used to choose the language of the read method
	 */
	public void chooseLanguageRead() {
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

	/**
	 * method used to choose the language of the delete method
	 */
	public void chooseLanguageDelete() {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);

		switch(choice) {
			case "A":
				english.ValueForDelete(null,null,null,0);
				break;

			case "B":
				german.ValueForDelete(null,null,null,0);
				break;
		}

	}

	/**
	 * method used to choose the language of the delete method
	 */
	public void chooseLanguageShow() {
		String choice;

		System.out.println("Which language?");
		System.out.println("A - English");
		System.out.println("B - German");

		choice=getString("Please make a choice, and press ENTER: ").toUpperCase(Locale.ROOT);

		switch(choice) {
			case "A":
				english.showTree();
				break;

			case "B":
				german.showTree();
				break;
		}

	}

	/**
	 *methods used to get input from user after printing a prompt
	 *
	 * @param userPrompt prompt to display to user
	 */
	public String getString(String userPrompt) {
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
