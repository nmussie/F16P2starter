
public class LeafNode extends Node {

    private KVPair pair;
    
    public LeafNode(KVPair pair) {
        super(pair);
    }
    /**
     * 
     */
    @Override
    public boolean isLeafNode()
    {
        return true;
    }
    

}
