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
	 * @param _root root
	 */
	public void setRoot(TreeNode _root) {
		root = _root;
	}
	
	/**
	 * Method for getting user Input for add method
	 * @param word string to set new nodes word to
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
	 * @param newWord the new word being added
	 * @param currentWord the current word stored in this node
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
		return("error");
	}

	/**
	 * Method for adding node to binary tree
	 * @param word value to set the new nodes word field to
	 * @param node the starting node, intended for the root of the dictionary
	 */
	public void add(TreeNode node, String word) {

		String result = compareWords(word, node.getWord(), 0);

		//checking if the node is greater than or less than the id in the current node
		//if the character is less and no node exists, the node will be added to the left
		if((result.equals("left")) && (node.getLeftNode() == null)) {
			node.setLeftNode(new TreeNode(word));
		}

		//if the character is greater and no node exists, the node will be added to the right
		else if((result.equals("right")) && (node.getRightNode() == null)) {
			node.setRightNode(new TreeNode(word));
		}

		//if the character is less and a node exists, the left node will be checked
		else if((result.equals("left")) && (node.getLeftNode() != null)) {
			add(node.getLeftNode(), word);
		}

		//if the character is greater and a node exists, the right node will be checked
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
				System.out.println("Whore");
				//checking the right node
				subWriteDictionary(node.getRightNode(), file);

		}}	catch (IOException e) {
			System.out.println("Error");
		}



		return;
	}

	/**
	 * method to read from file containing dictionary and gets written to this tree
	 */
	public void readDictionary() {

		try{
			File file = new File(fileName + ".txt");
			Scanner reader = new Scanner(file);

			while(reader.hasNext() == true) {
				String data = reader.nextLine();
				this.add(root, data);
			}

			reader.close();
		}
		catch(Exception e) {
			System.out.println("Error in read method");
		}

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
	
//	public boolean findInList(TreeNode newNode, int IDNum, int FValue) {
//		try {
//		TreeNode c = root;
//		boolean found = false;
//		while (found == false) {
//			if(FValue == c.IDNum) {
//				found = true;
//			}
//			else {
//				if(FValue < c.IDNum) {
//					c = c.l;
//				}
//				else {
//					c = c.r;
//				}
//			}
//			
//		}
//		if(found == true) {
//			System.out.println("Node found !");
//			System.out.println(c.print());
//			return true;
//		}
//	}
//		catch(NullPointerException e) {
//			System.out.println("Node not found");
//		}
//		return false;	
//}
//	
//	/**
//	 * Get user input value for delete
//	 * @param newNode
//	 * @param IDNum
//	 * @param temp
//	 */
//	public void ValueForDelete(TreeNode newNode, int IDNum, int temp) {
//		try {
//		System.out.println("Enter ID of node to delete");
//		Scanner s = new Scanner(System.in);
//		temp = s.nextInt();
//		DeleteNode(newNode, IDNum, temp);	
//		}catch(InputMismatchException e) {
//			System.out.println("You entered an invalid value");
//		}
//	}
//	
//	/**
//	 * Method for processing delete
//	 * @param newNode
//	 * @param IDNum
//	 * @param temp
//	 */
//	public void DeleteNode(TreeNode newNode, int IDNum, int temp) {
//		TreeNode currentNode = root; 
//		TreeNode previousNode = null;
//		TreeNode nodeToAdopt = null;
//		TreeNode nodeToOverwrite = currentNode;
//		boolean found = false;
//		
//		try {
//		while (currentNode != null && found == false) {
//			if (currentNode.getIDNum() == temp) {
//				found = true;
//			} else {
//				if (temp < currentNode.getIDNum()) {
//					previousNode = currentNode;
//					currentNode = currentNode.getLeft();
//				} else if (temp > currentNode.getIDNum()) {
//					previousNode = currentNode;
//					currentNode = currentNode.getRight();
//				}
//			}
//		}
//
//		if (currentNode == null) {
//			System.out.println("Node to delete not found");
//			found = false;
//		} else {
//			if (currentNode.getLeft() == null && currentNode.getRight() == null) {
//				if (currentNode == previousNode.getLeft()) {
//					System.out.println("Node removed!");
//					previousNode.setLeft(null);
//					
//				} else if (currentNode == previousNode.getRight()) {
//					System.out.println("Node removed!");
//					previousNode.setRight(null);
//				}
//			
//			} else if (currentNode.getLeft() == null) {
//				if (currentNode == previousNode.getLeft()) {
//					System.out.println("Node removed!");
//					nodeToAdopt = currentNode.getLeft(); 
//					previousNode.setLeft(nodeToAdopt);
//
//				
//				} else if (currentNode == previousNode.getRight()) {
//					System.out.println("Node removed!");
//					nodeToAdopt = currentNode.getRight(); 
//					previousNode.setRight(nodeToAdopt); 
//				}
//
//			
//			} else if (currentNode.getRight() == null) {
//				if (currentNode == previousNode.getLeft()) {
//					System.out.println("Node removed!");
//					nodeToAdopt = currentNode.getLeft(); 
//					previousNode.setLeft(nodeToAdopt); 
//
//				} else if (currentNode == previousNode.getRight()) {
//					System.out.println("Node removed!");
//					nodeToAdopt = currentNode.getRight(); 
//					previousNode.setRight(nodeToAdopt); 
//				}
//			} 
//		}
//		} catch(NullPointerException e) {
//			System.out.print("Node cannot be deleted");
//		}
//		}	
	}

