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
     * @param newWord string to set as the new word in the left treeNode
     */
    public void setLeftNode(String newWord){
        leftNode = new TreeNode(newWord);
    }

    /**
     *  method used to set a new value to the rightNode field
     *  @param newWord string to set as the new word in the left treeNode
     */
    public void setRightNode(String newWord){
        rightNode = new TreeNode(newWord);
    }

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


}
