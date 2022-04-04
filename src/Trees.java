import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

public class Trees {
	private TreeNode root;
	private String fileName;

	/**
	 * Instantiation method
	 */
	public Trees() {
		root = null;
		String fileName;
	}

	/**
	 * constructor method
	 *
	 * @param newFileName new word to set fileName to
	 */
	public Trees(String newFileName) {
		fileName = newFileName;
	}

	/**
	 * Method for getting root
	 *
	 * @return
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * Method for setting the root
	 *
	 * @param _root root
	 */
	public void setRoot(TreeNode _root) {
		root = _root;
	}

	/**
	 * Method for getting user Input for add method
	 *
	 * @param word string to set new node's word to
	 */
	public void getInput(String word) {
		try {
			for (int i = 0; i < 5; i++) {

				Scanner s = new Scanner(System.in);
				System.out.println("Enter Word");
				word = s.nextLine();

				if (root == null) {
					root = new TreeNode(word);
				} else {
					add(root, word);
				}
			}

		} catch (InputMismatchException e) {
			System.out.println("You entered invalid value");
		}
	}

	/**
	 * compareWords
	 *
	 * @param newWord
	 * @param currentWord
	 * @param i
	 * @return returns which direction to go in
	 */
	public String compareWords(String newWord, String currentWord, int i) {
		try {
			if ((int) newWord.charAt(i) > (int) currentWord.charAt(i)) {
				return ("right");
			} else if ((int) newWord.charAt(i) < (int) currentWord.charAt(i)) {
				return ("left");
			} else if ((int) newWord.charAt(i) == (int) currentWord.charAt(i)) {
				if (((i + 1) == newWord.length()) && ((i + 1) == currentWord.length())) {
					return "match";
				}
			} else {
				return (compareWords(newWord, currentWord, i + 1));
			}
		} catch (Exception e) {

			if (newWord.length() > currentWord.length()) {
				return ("left");
			} else if (newWord.length() < currentWord.length()) {
				return ("right");
			} else {
				return "match";
			}
		}
		return currentWord;
	}

	/**
	 * Method for adding node to binary tree
	 *
	 * @param word value to set the new node's word field to
	 * @param node the starting node, intended for the root of the dictionary
	 */
	public void add(TreeNode node, String word) {

		if (root == null) {
			root = new TreeNode(word);
			return;
		}

		String result = compareWords(word, node.getWord(), 0);

		//checking if the node is greater than or less than the id in the current node
		//if the character is less and no node exists, the node will be added to the left
		if ((result.equals("left")) && (node.getLeftNode() == null)) {
			node.setLeftNode(new TreeNode(word));
		}

		//if the character is greater and no node exists, the node will be added to the right
		else if ((result.equals("right")) && (node.getRightNode() == null)) {
			node.setRightNode(new TreeNode(word));
		}

		//if the character is less and a node exists, the left node will be checked
		else if ((result.equals("left")) && (node.getLeftNode() != null)) {
			add(node.getLeftNode(), word);
		}

		//if the character is greater and a node exists, the right node will be checked
		else if ((result.equals("right")) && (node.getRightNode() != null)) {
			add(node.getRightNode(), word);
		}
	}

	/**
	 * method used to calculate and print the total cost in the tree
	 *
	 * @param node the current node to work from
	 */
	public void writeDictionary(TreeNode node) {

		clearFile();
		try {

			Writer writer = null;
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName + ".txt"), "utf-8"));

			//will check if the node exists before calculating
			if (node != null) {

				//using recursion to go through the entire tree
				//this line checks the left node
				subWriteDictionary(node.getLeftNode(), writer);

				//write from current node
				try {
					writer.write(node.getWord());
					writer.write(String.format("%n"));
				} catch (IOException ex) {
					System.out.println("Error in writeDictionary [2]");
				}
				//checking the right node
				subWriteDictionary(node.getRightNode(), writer);

			}
			//printing out success message
			System.out.println("Dictionary written successfully.");

			try {
				writer.close();
			} catch (Exception ex) {
				//pass
			}

		} catch (IOException e) {
			System.out.println("Error in writeDictionary [1]");
		}

	}

	/**
	 *
	 */
	public String findCloseWord(String word, TreeNode node) {

		TreeNode previousNode = node;

		TreeNode currentNode;

		TreeNode potentialNode = null;

		String result = compareWords(word, node.getWord(), 0);

		if (word.equalsIgnoreCase(node.getWord().substring(0, node.getWord().length() - 1))) {
			potentialNode = node;
		}

		if (result.equalsIgnoreCase("left")) {
			currentNode = node.getLeftNode();
		}
		else if (result.equalsIgnoreCase("right")) {
			currentNode = node.getRightNode();
		}
		else {
			return node.getWord();
		}

		if (currentNode == null) {
				return node.getWord();
		}
		do {

			previousNode = currentNode;

			result = compareWords(word, currentNode.getWord(), 0);

			if (word.equalsIgnoreCase(currentNode.getWord().substring(0, currentNode.getWord().length() - 1))) {
				potentialNode = currentNode;
			}

			if (result.equalsIgnoreCase("left")) {
				currentNode = currentNode.getLeftNode();
			}
			else if (result.equalsIgnoreCase("right")) {
				currentNode = currentNode.getRightNode();
			}
			else {
				return (previousNode.getWord() + " " + potentialNode.getWord());
			}

			if (currentNode == null) {
				if (potentialNode != null){
					return (previousNode.getWord() + " " + potentialNode.getWord());
				}
				else {
					return (previousNode.getWord());
				}
			}

		} while(true);
	}

	/**
	 *
	 */
	public void predictWord() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please type the word you want to find:");

		String input = scanner.nextLine();

		String result = findCloseWord(input, root);

		System.out.println("Here is the predicted word:");
		System.out.println(result);

		System.out.println("If you didn't find the word you were looking for, please (A)dd one, or type anything else to exit");
		String choice = scanner.nextLine();

		if (choice.equalsIgnoreCase("A")) {
			getInput(null);
		}
	}

	/**
	 * method called in writeDictionary();
	 *
	 * @param node node to start on
	 */
	public void subWriteDictionary(TreeNode node, Writer writer) {

		try {

			if (node != null) {

				//using recursion to go through the entire tree
				//this line checks the left node

				subWriteDictionary(node.getLeftNode(), writer);


				//write from current node
				try {
					writer.write(node.getWord());
					writer.write(String.format("%n"));
				} catch (IOException ex) {
					System.out.println("Error in writeDictionary [2]");
				}

				//checking the right node
				subWriteDictionary(node.getRightNode(), writer);

			}
		} catch (Exception e) {
			System.out.println("Error in writeDictionary [1]");
		}
	}

	/**
	 * method to read from file containing dictionary and gets written to this tree
	 */
	public void readDictionary() {

		clearTree();

		try {
			File file = new File(fileName + ".txt");
			Scanner reader = new Scanner(file);
			String[] wordArray;
			int i = 0;

			while (reader.hasNext()) {
				String data = reader.nextLine();

				wordArray[i] = data;
			}
			System.out.println("Loaded dictionary.");

			reader.close();

		} catch (Exception e) {
			System.out.println("Error in read method");
		}
	}

	/**
	 *
	 */
	public void clearFile() {

		try {
			File file = new File(fileName + ".txt");
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (Exception e) {
			System.out.println("Error in clearFile()");
		}

	}

	/**
	 *
	 */
	public void clearTree() {
		root = null;
	}
}
