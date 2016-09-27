
public abstract class Node {
    
    private KVPair firstPair;
    private KVPair secondPair;

    public Node(KVPair firstPair, KVPair secPair) {
        setFirstPair(firstPair);
        setSecondPair(secPair);
    }

    public Node() {
        setFirstPair(null);
        setSecondPair(null);
    }
    
    /**
     * 
     * @return
     */
    public boolean isLeafNode() {
        return false;
    }

    /**
     * 
     * @return
     */
    public KVPair getFirstPair() {
        return firstPair;
    }

    /**
     * 
     * @param first
     */
    public void setFirstPair(KVPair newLeft) {
        firstPair = newLeft;
    }

    /**
     * 
     * @return
     */
    public KVPair getSecondPair() {
        return secondPair;
    }

    /**
     * 
     * @param second
     */
    public void setSecondPair(KVPair newRight) {
        secondPair = newRight;
    }
    public boolean isFull()
    {
        return firstPair != null && secondPair != null;
    }
    
    public boolean onlyFirstNode() {
        return firstPair != null && secondPair == null;
    }
    
    public boolean onlySecNode() {
        return firstPair == null && secondPair != null;
    }
    public KVPair addWhenFull(KVPair newPair)
    {
        KVPair temp = newPair;
        if (newPair.compareTo(firstPair) < 0 )
        {
            temp = getSecondPair();
            secondPair = firstPair;
            firstPair = newPair;
        }
        else if (newPair.compareTo(secondPair) < 0)
        {
            temp = getSecondPair();
            secondPair = newPair;
        }
        return temp;
    }
    public void insert(KVPair pair)
    {
        if (onlyFirstNode())
        {
            setSecondPair(pair);
        }
        else if (onlySecNode())
        {
            setFirstPair(pair);
        }
        swap();
    }
    public void swap()
    {
        KVPair temp = secondPair;
        if (secondPair.compareTo(firstPair) < 0 )
        {
            temp = secondPair;
            secondPair = firstPair;
            firstPair = temp;
        }        
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
