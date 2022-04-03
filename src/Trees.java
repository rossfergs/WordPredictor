import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;

public class Trees {
	private TreeNode root;
	private String fileName;
	String temp = "";
	
	/**
	 * Instantiation method
	 */
	public Trees() {
		root = null;
		fileName = new String();
	}

	/**
	 *constructor method
	 * @param newFileName new word to set fileName to
	 */
	public Trees(String newFileName) {
		fileName = newFileName;
	}

	/**
	 * Method for getting root
	 * @return
	 */
	public TreeNode getRoot() {
		return root;
	}
	
	/**
	 * Method for setting the root
	 * @param _root
	 */
	public void setRoot(TreeNode _root) {
		root = _root;
	}
	
	/**
	 * Method for getting user Input for add method
	 * @param word
	 */
	public void getInput(String word) {
		try {
		for(int i = 0; i < 5; i++) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Word");
		word = s.nextLine();

		if(root == null) {
			root = new TreeNode(word);
		}
		else {
			add(root, word);
		}

		}
		} catch(InputMismatchException e) {
			System.out.println("You entered invalid value");
		}
	}

	/**
	 *compareWords
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
	 * @param word value to set the new nodes word field to
	 * @param node the starting node, intended for the root of the dictionary
	 */
	public void add(TreeNode node, String word) {

		String result = compareWords(word, node.getWord(), 0);

		//checking if the node is greater than or less than the id in the current node
		//if the id is less and no node exists, the node will be added to the left
		if((result.equals("left")) && (node.getLeftNode() == null)) {
			node.setLeftNode(new TreeNode(word));
		}

		//if the is is greater and no node exists, the node will be added to the right
		else if((result.equals("right")) && (node.getRightNode() == null)) {
			node.setRightNode(new TreeNode(word));
		}

		//if the id is less and a node exists, the left node will be checked
		else if((result.equals("left")) && (node.getLeftNode() != null)) {
			add(node.getLeftNode(), word);
		}

		//if the id is greater and a node exists, the right node will be checked
		else if((result.equals("right")) && (node.getRightNode() != null)) {
			add(node.getRightNode(), word);
		}
	}

	/**
	 * method used to calculate and print the total cost in the tree
	 *
	 * @param node the current node to work from
	 */
	public void writeDictionary(TreeNode node) {
		try {
			File file = new File(fileName + ".txt");
			FileWriter writer = new FileWriter(file);

			//will check if the node exists before calculating
			if(node != null) {

				//using recursion to go through the entire tree
				//this line checks the left node
				subWriteDictionary(node.getLeftNode(), file);

				//write from current node
				writer.write(node.getWord());

				//checking the right node
				subWriteDictionary(node.getRightNode(), file);

			}
			//printing out success message
			System.out.println("Dictionary written successfully.");

		} catch(IOException e) {
			System.out.println("Error.");
		}

	}


	/**
	 * method called in writeDictionary();
	 *
	 * @param node node to start on
	 */
	public void subWriteDictionary(TreeNode node, File file) {

		try {
			FileWriter writer = new FileWriter(file);

			if(node != null) {

				//using recursion to go through the entire tree
				//this line checks the left node
				if(node.getLeftNode() != null){
					subWriteDictionary(node.getLeftNode(), file);
				}

				//write from current node
				writer.write(node.getWord());

				//checking the right node
				subWriteDictionary(node.getRightNode(), file);
			}
			else {
				System.out.println("Reached the end of the line");
			}

		}	catch (IOException e) {
			System.out.println("Error");
		}



		return;
	}
	
	/**
	 * Method for calculating if tree should be shown
	 */
//	public void showTree() {
//		if (root != null) {
//		System.out.println("----------------------");
//		String indent = "";
//		displayTree(root, indent);
//		System.out.println("----------------------");
//		}
//		else if (root == null) {
//			System.out.println("Empty tree");
//		}
//	}
//	
	/**
	 * Method for displaying tree
	 * @param newNode
	 * @param indent
	 */
//	public void displayTree(TreeNode newNode, String indent)
//	{
//		if (newNode != null)
//		{
//			// display the right sub-tree of 'p', increasing the indent
//	           	displayTree(newNode.getRight(), indent + "\t");              
//	           
//	           	// display 'p' itself, at the current level of indent 
//	            	System.out.println(indent + newNode.getIDNum());
//	            	System.out.println(indent + newNode.getCost()); 
//	            	System.out.println(indent + " " + newNode.getName());
//			// display left sub-tree of 'p', increasing the indent
//	            	displayTree(newNode.getLeft(), indent + "\t");
//		}	
//	}
//	
	/**
	 * Getting user input for search value
	 * @param newNode
	 * @param IDNum
	 * @param FValue
	 */
//	
//	public void searchValue(TreeNode newNode, int IDNum, int FValue) {
//		System.out.println("Enter an ID to search for");
//		Scanner s = new Scanner(System.in);
//		FValue = s.nextInt();
//		findInList(newNode, IDNum, FValue);
//	}
	/**
	 * Find method
	 * @param newNode
	 * @param IDNum
	 * @param FValue
	 * @return
	 */
	

	public void ValueForDelete(TreeNode newNode, String word, String temp, int i) {
		try {
		System.out.println("Enter Word to delete");
		Scanner s = new Scanner(System.in);
		String p = s.nextLine();
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
		
		try {
			compareWords(word, temp, i);
		while (currentNode != null && found == false) {
			if (temp.equals(currentNode.getWord())) {
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
					nodeToAdopt = currentNode.getLeftNode(); 
					previousNode.setLeftNode(nodeToAdopt);

				
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
		}
		} catch(NullPointerException e) {
			System.out.print("Node cannot be deleted");
		}
		}	
	}
