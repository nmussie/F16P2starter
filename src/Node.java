
public abstract class Node {
private KVPair pair;
    
    public Node(KVPair pair) {
        setData(pair);
    }
    public Node()
    {
        setData( null);
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
    /**
     * 
     * @return
     */
    public boolean isLeafNode()
    {
        return false;
    }
    
}
