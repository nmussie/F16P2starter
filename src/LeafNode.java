
public class LeafNode extends Node {

    private LeafNode next;

    public LeafNode(KVPair firstPair, KVPair secPair) {
        super(firstPair, secPair);
        setNext(null);
    }
    public LeafNode()
    {
        super();
    }

    /**
     * Checks if node is leaf
     * 
     * @return true if node is leaf node
     */
    @Override
    public boolean isLeafNode() {
        return true;
    }

    
    /**
     * Getter for next node
     * 
     * @return gets next node
     */
    public LeafNode getNext() {
        return next;
    }

    /**
     * Sets next node
     * 
     * @param next
     *            node to set
     */
    public void setNext(LeafNode next) {
        this.next = next;
    }

}
