
public class InternalNode extends Node {

    private Node left;
    private Node middle;
    private Node right;
    private KVPair pair;
    
    
    public InternalNode() {
        super();
        
    }
    public InternalNode(KVPair pair)
    {
        super(pair);
    }

    /**
     * Returns next node
     * 
     * @return next node
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets next node
     * 
     * @param next
     *            sets previous node
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Gets previous node
     * 
     * @return previous node
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the previous node
     * 
     * @param previous
     *            node that sets the previous node
     */
    public void setLeft(Node left) {
        this.left = left;
    }
    
    /**
     * Getter for middle node
     * 
     * @return middle node
     */
    public Node getMiddle() {
        return middle;
    }

    /**
     * Sets the middle node
     * 
     * @param middle
     *            node to be set
     */
    public void setMiddle(Node middle) {
        this.middle = middle;
    }    
    /**
     * Returns a KVpair
     * 
     * @return KVpair
     */
    public KVPair getData() {
        return pair;
    }

    /**
     * Sets a free block
     * 
     * @param data
     *            of KVpair being set
     */
    public void setData(KVPair data) {
        pair = data;
    }
}
