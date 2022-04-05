import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.util.ArrayList;

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
			}
			else if ((int) newWord.charAt(i) < (int) currentWord.charAt(i)) {
				return ("left");
			}
			else if ((int) newWord.charAt(i) == (int) currentWord.charAt(i)) {
				if (((i + 1) == newWord.length()) && ((i + 1) == currentWord.length())) {
					return "match";
				}
			}
			else {
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



		if (node == null) {
			root = new TreeNode(word);
			return;
		}
		else {
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


	}

	/**
	 * method used to calculate and print the total cost in the tree
	 *
	 * @param node the current node to work from
	 */
	public void writeDictionary(TreeNode node, Writer writer) {

		clearFile();


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

	}

	/**
	 * method used to find a word close to the imput
	 *
	 * @param word word user input
	 * @param node starting node to check from
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

			result = compareWords(word, node.getWord(), 0);

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
				if (potentialNode != null){
					return (previousNode.getWord() + " " + potentialNode.getWord());
				}
				else {
					return (previousNode.getWord());
				}
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
	 *method used for user input and printing results for predicting words
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
			ArrayList<String> wordArray = new ArrayList<String>();

			while (reader.hasNext()) {
				String data = reader.nextLine();

				wordArray.add(data);
			}

			for (int i = 0; i< wordArray.size(); i++){

				add(root, wordArray.get(i));
			}
			System.out.println("Loaded dictionary.");

			reader.close();

		} catch (Exception e) {
			System.out.println("Error in read method");
		}

	}

	/**
	 * method used to clear the save file
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
	 * method used to reset the tree
	 */
	public void clearTree() {
		root = null;
	}

	/**
	 * find method for delete
	 *
	 * @param newNode
	 * @param word
	 * @param temp
	 * @param i
	 */
	public void ValueForDelete(TreeNode newNode, String word, String temp, int i) {
		try {
			System.out.println("Enter Word to delete");
			Scanner s = new Scanner(System.in);
			String p = s.nextLine().toLowerCase();
			temp = p;
			DeleteNode(newNode, word, temp, i);
		}catch(InputMismatchException e) {
			System.out.println("You entered an invalid value");
		}
	}

	/**
	 * Method for processing delete
	 * @param newNode
	 * @param word
	 * @param temp
	 */
	public void DeleteNode(TreeNode newNode, String word, String temp, int i) {
		TreeNode currentNode = root;
		TreeNode previousNode = null;
		TreeNode nodeToAdopt = null;
		TreeNode nodeToOverwrite = currentNode;
		boolean found = false;

		compareWords(word, temp, i);
		try {
			while (currentNode != null && found == false) {
				if (currentNode.getWord().equals(temp)) {
					System.out.println("Node to delete found: " + temp);
					found = true;
				}
				else {
					if ((int) temp.charAt(i) < (int) currentNode.getWord().charAt(i)) {
						previousNode = currentNode;
						currentNode = currentNode.getLeftNode();
					} else if (temp.charAt(i) > currentNode.getWord().charAt(i)) {
						previousNode = currentNode;
						currentNode = currentNode.getRightNode();
					}
				}
			}

			if (currentNode == null) {
				System.out.println("Node to delete not found");
				found = false;
			} else {
				if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
					if (currentNode == previousNode.getLeftNode()) {
						System.out.println("Node removed!");
						previousNode.setLeftNode(null);

					} else if (currentNode == previousNode.getRightNode()) {
						System.out.println("Node removed!");
						previousNode.setRightNode(null);
					}

				} else if (currentNode.getLeftNode() == null) {
					if (currentNode == previousNode.getLeftNode()) {
						System.out.println("Node removed!");
						nodeToAdopt = currentNode.getRightNode();
						previousNode.setRightNode(nodeToAdopt);


					} else if (currentNode == previousNode.getRightNode()) {
						System.out.println("Node removed!");
						nodeToAdopt = currentNode.getRightNode();
						previousNode.setRightNode(nodeToAdopt);
					}


				} else if (currentNode.getRightNode() == null) {
					if (currentNode == previousNode.getLeftNode()) {
						System.out.println("Node removed!");
						nodeToAdopt = currentNode.getLeftNode();
						previousNode.setLeftNode(nodeToAdopt);

					} else if (currentNode == previousNode.getRightNode()) {
						System.out.println("Node removed!");
						nodeToAdopt = currentNode.getRightNode();
						previousNode.setRightNode(nodeToAdopt);
					}
				}

//			else if(currentNode.getLeftNode() != null && currentNode.getRightNode() != null) {
//					nodeToOverwrite = currentNode;
//					previousNode = null;
//					currentNode = currentNode.getLeftNode();
//					while(currentNode.getRightNode() != null) {
//					previousNode = currentNode;
//					currentNode = currentNode.getRightNode();
//					}
//				}
			}
		} catch(NullPointerException e) {
			System.out.print("Node cannot be deleted");
		}

		if (currentNode == null) {
			System.out.println("Node to delete not found");
			return;
		}
	}

	/**
	 * Method for calculating if tree should be shown
	 */
	public void showTree() {
		if (root != null) {
			System.out.println("----------------------");
			String indent = "";
			displayTree(root, indent);
			System.out.println("----------------------");
		}
		else if (root == null) {
			System.out.println("Empty tree");
		}
	}

	/**
	 * printing
	 *
	 * @param newNode
	 * @param indent
	 */
	public void displayTree(TreeNode newNode, String indent)
	{
		if (newNode != null)
		{
			// display the right sub-tree of 'p', increasing the indent
			displayTree(newNode.getRightNode(), indent + "\t");

			// display 'p' itself, at the current level of indent
			System.out.println(indent + " " + newNode.getWord());
			// display left sub-tree of 'p', increasing the indent
			displayTree(newNode.getLeftNode(), indent + "\t");
		}
	}
}

