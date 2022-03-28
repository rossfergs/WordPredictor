import java.util.Scanner;
import java.util.InputMismatchException;
import static java.lang.Integer.parseInt;

public class Trees {
	private TreeNode root;
	
	/**
	 * Instantiation method
	 */
	public Trees() {
		root = null;
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

		if((int) newWord.charAt(i) > (int) currentWord.charAt(i)) {
			return("right");
		}
		else if((int) newWord.charAt(i) < (int) currentWord.charAt(i)) {
			return("left");
		}
		else {
			return(compareWords(newWord, currentWord, i+1));
		}
	}

	/**
	 * Method for adding node to binary tree
	 * @param word value to set the new nodes word field to
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

		//if the id is geater and a node exists, the right node will be checked
		else if((result.equals("right")) && (node.getRightNode() != null)) {
			add(node.getRightNode(), word);
		}
	}

		/*
		while(c != null) {

			p = c;
			String direction;
			direction = null;

			while(direction == null) {

			}

			temp++;

			//when the full word has been processed, the computer will assign it a left or right node, or an error.
			if (temp == word.length() - 1) {
				//if all the characters get matched, an error message will be shown and the method stops.
				if (matchCount == (word.length() - 1)) {
					System.out.println("You entered a duplicate.");
					return;
				}

				else {

					int counter = 0;
					boolean assigned = false;

					while(!assigned) {

						try {
							if(newNode.getCharacter(counter) < c.getCharacter(counter)) {
								c = c.getLeftNode();
								System.out.println("Whore.");
							}

							else if(newNode.getCharacter(counter) > c.getCharacter(counter)) {
								c = c.getRightNode();
								System.out.println("Whores.");
							}

							else{
								counter++;
							}

						} catch(Exception e) {
							System.out.println("afafsafsae.");
							if ((newNode.getCharacter(word.length() - 1) < c.getCharacter(word.length() - 1))) {

							}
							else if ((newNode.getCharacter(word.length() - 1) > c.getCharacter(word.length() - 1))) {

							}
						}


					}
				}
			}
		}

		if(newNode.getCharacter(temp) < p.getCharacter(temp)) {
			System.out.println("Whiafnaj.");
			p.setLeftNode(newNode);
		}
		else {
			System.out.println("adsfasfdsf aesfs");
			p.setRightNode(newNode);
		}

		System.out.println(newNode.getCharacter(temp));
		return;

	} catch(InputMismatchException e) {
		System.out.println("You entered invalid value");
	}
		 */


		


	
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

