
public class InternalNode extends Node {

    private Node left;
    private Node middle;
    private Node right;
    
    
    public InternalNode() {
        super();
        
    }
    public InternalNode(KVPair firstPair, KVPair secPair)
    {
        super(firstPair, secPair);
    }

    /**
     * Returns next node
     * 
     * @return next node
     */
    public Node getRightChild() {
        return right;
    }
    
    /**
     * 
     * @return
     */
    public boolean isLeafNode() {
        return left == null && right == null && middle == null;
    }

    /**
     * Sets next node
     * 
     * @param next
     *            sets previous node
     */
    public void setRightChild(Node right) {
        this.right = right;
    }

    /**
     * Gets previous node
     * 
     * @return previous node
     */
    public Node getLeftChild() {
        return left;
    }

    /**
     * Sets the previous node
     * 
     * @param previous
     *            node that sets the previous node
     */
    public void setLeftChild(Node left) {
        this.left = left;
    }
    
    /**
     * Getter for middle node
     * 
     * @return middle node
     */
    public Node getMiddleChild() {
        return middle;
    }

    /**
     * Sets the middle node
     * 
     * @param middle
     *            node to be set
     */
    public void setMiddleChild(Node middle) {
        this.middle = middle;
    }    
}
