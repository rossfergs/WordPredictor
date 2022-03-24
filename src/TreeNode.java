import static java.lang.Integer.parseInt;

/**
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

    public TreeNode(String newWord) {
        word = newWord;
        intArray = toIntArray(word);
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

    public String getWord(){ return word; }


    /**
     * method used to convert a word into an array of integers, used for navigation within the binary tree
     * @param word
     * @return an array of integers for each letters
     */
    public int[] toIntArray(String word){
        String[] tempArray = word.split("(?!^)");
        //looping for length of word and
        for(int i = 0; i < (word.length() + 1); i++){
            //kconverting the character stored
            intArray[i] = parseInt(tempArray[i]);
        }
        return(intArray);
    }

    /**
     *
     * @param newWord
     */
    public void addNode(String newWord) {
        int i = 0;
        boolean loop = true;
        TreeNode newNode = new TreeNode(newWord);
        //checking if the id is lower, higher or match, and placing the new node dependant on the result

        if ((newNode.getArrayIndex(i) < this.getArrayIndex(i)) && (this.getLeftNode() == null)){
            this.setLeftNode(newNode);
        }
        else if ((newNode.getArrayIndex(i) > this.getArrayIndex(i)) && (this.getRightNode() == null)) {
            this.setRightNode(newNode);
        }
        else {
            System.out.println("Error");
        }
    }
}
