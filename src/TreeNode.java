import java.util.Locale;
import static java.lang.Integer.parseInt;

/**
 * this class will store the the information in each node. A node will store a word for the dictionary, which
 * will be a binary tree.
 * @author ross ferguson
 */
public class TreeNode {

    //defining new fields for use in the binary tree

    //word: used to store the word in the node
    private String word;
    //intArray: used to store an array of integers, each index represents a character in the word stored as an integer
    private int[] intArray;
    //leftNode: used to store the left node branching from current node
    private TreeNode leftNode;
    //rightNode: used to store the right node branching from current node
    private TreeNode rightNode;

    /**
     * constructor method used to set up or initialize fields
     * @param newWord new value to set field "word" to
     */
    public TreeNode(String newWord) {
        //making the word lowercase, to avoid errors in navigation within the tree.
        word = newWord.toLowerCase(Locale.ROOT);
        //converting the word into an array of integers to use for navigation
        intArray = toIntArray(word);
        //setting the two nodes to null
        leftNode = null;
        rightNode = null;
    }

    /**
     * method used to set a new value to the 'word' field
     * @param newWord a new value to set word to
     */
    public void setWord(String newWord){
        word = newWord;
    }

    /**
     * method used to set a new value to the leftNode field
     * @param newNode npde to set as the new node in the left treeNode
     */
    public void setLeftNode(TreeNode newNode){
        leftNode = newNode;
    }


    /**
     *  method used to set a new value to the rightNode field
     *  @param newNode string to set as the new node in the left treeNode
     */
    public void setRightNode(TreeNode newNode){
        rightNode = newNode;
    }

    /**
     * method to return the an index of intArray
     * @return returns the interger stored in the desired index of the array
     */
    public int getArrayIndex(int i) {
        return(intArray[i]);
    }

    /**
     * method used to return the value stored in field LeftNode
     * @return the value of leftNode
     */
    public TreeNode getLeftNode() {
        return leftNode;
    }

    /**
     * method used to return the value stored in field rightNode
     * @return the value of rightNode
     */
    public TreeNode getRightNode() {
        return rightNode;
    }

    /**
     * method used to get the value of word
     * @return the value of word
     */
    public String getWord(){ return word; }

    /**
     * method used to convert a word into an array of integers, used for navigation within the binary tree
     * @param word the field containing the word for this place in the dictionary
     * @return an array of integers for each letters
     */
    public int[] toIntArray(String word){

        String[] tempArray = word.split("(?!^)");
        //looping for length of word and
        for(int i = 0; i < (word.length() + 1); i++){
            //converting the character stored into an integer and adding it to the array.
            intArray[i] = parseInt(tempArray[i]);
        }
        return(intArray);
    }

    /**
     * method used to add a new node to the end of current node.
     * NOTE: this method does not traverse the tree, another method must be set up in Tree.java for that task.
     * @param newNode the new node, as set up in add() in Tree.java
     */
    public void addNode(TreeNode newNode) {

        for(int i = 0; i < word.length(); i++){
            //checking if the id is lower, higher or match, and placing the new node dependant on the result
            if ((newNode.getArrayIndex(i) < this.getArrayIndex(i)) && (this.getLeftNode() == null)){
                this.setLeftNode(newNode);
            }

            else if ((newNode.getArrayIndex(i) > this.getArrayIndex(i)) && (this.getRightNode() == null)) {
                this.setRightNode(newNode);
            }
            //error message, hopefully not to be used.
            else {
                System.out.println("Error in addNode().");
            }
        }
    }
}
