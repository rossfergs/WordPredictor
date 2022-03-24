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
     *
     * @param i index for array
     * @return individual character from word
     */
    public int getCharacter(int i){
        String[] tempArray = this.getWord().split("(?!^)");
        return(parseInt(tempArray[i]));
    }

    /**
     * method used to add a new node to the end of current node.
     * NOTE: this method does not traverse the tree, another method must be set up in Tree.java for that task.
     * @param newNode the new node, as set up in add() in Tree.java
     */
    public void addNode(TreeNode newNode) {

        String[] tempArray = newNode.getWord().split("(?!^)");
        String[] currentNodeArray = this.getWord().split("(?!^)");

        for(int i = 0; i < word.length(); i++){

            //checking if the id is lower, higher or match, and placing the new node dependant on the result
            if ((parseInt(tempArray[i]) < parseInt(currentNodeArray[i])) && (this.getLeftNode() == null)){
                this.setLeftNode(newNode);
            }

            else if ((parseInt(tempArray[i]) > parseInt(currentNodeArray[i])) && (this.getRightNode() == null)) {
                this.setRightNode(newNode);
            }

            //error message, hopefully not to be used.
            else {
                System.out.println("Error in addNode().");
            }
        }
    }
}
