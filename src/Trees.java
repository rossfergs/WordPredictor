import java.util.Scanner;
import java.util.InputMismatchException;

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
	 * @param IDNum
	 * @param cost
	 * @param name
	 */
	public void getInput(String word) {
		try {
		for(int i = 0; i < 5; i++) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Word");
		word = s.nextLine();
		
		add(word);
		}
		} catch(InputMismatchException e) {
			System.out.println("You entered invalid value");
		}
	}
	
	/**
	 * Method for adding node to binary tree
	 * @param IDNum
	 * @param cost
	 * @param name
	 */
	public void add(String word) {
		try {
		TreeNode newNode = new TreeNode(word);
		TreeNode c = root;
		TreeNode p = c;
		int temp = 0;
		
		if (root == null) {
			root = newNode;
			return;
		}
		
		while(c != null) {
			p = c;
		if(newNode.getArrayIndex(temp) < c.getArrayIndex(temp)) {
			c = c.getLeftNode();
		}
		
		else if (newNode.getArrayIndex(temp) == c.getArrayIndex(temp)) {
			System.out.println("You entered a duplicate");
			return;
		}
		
		else {
			c = c.getRightNode();
		}
		temp++;
		}
		
		if(newNode.getArrayIndex(temp) < p.getArrayIndex(temp)) {
			p.setLeftNode(newNode);
		}
		else {
			p.setRightNode(newNode);
		}
		System.out.println(newNode.getArrayIndex(temp));
		return;
	} catch(InputMismatchException e) {
		System.out.println("You entered invalid value");
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

