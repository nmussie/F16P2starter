/**
 * Leaf node class
 * 
 * @author Nathan Mussie
 * @author Enrique Prieto
 * @version Fall 2016
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
public class LeafNode implements Node {

    private LeafNode next;
    private LeafNode prev;
    private KVPair firstPair;
    private KVPair secondPair;

    /**
     * Constructor that takes handles as parameters
     * 
     * @param firstPair
     *            of handles
     * @param secPair
     *            of handles
     */
    public LeafNode(KVPair firstPair, KVPair secPair) {
        setFirstPair(firstPair);
        setSecondPair(secPair);
        setNext(null);
        setPrev(null);
    }

    /**
     * Empty constructors
     */
    public LeafNode() {
        setFirstPair(null);
        setSecondPair(null);
        setNext(null);
        setPrev(null);
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

    /**
     * Gets previous leaf node
     * 
     * @return previous leaf node
     */
    public LeafNode getPrev() {
        return prev;
    }

    /**
     * Sets previous node to parameter
     * 
     * @param prev
     *            node that changes prev
     */
    public void setPrev(LeafNode prev) {
        this.prev = prev;
    }

    /**
     * Add method for leaf node
     * 
     * @param pair
     *            to be added
     * @return the current node that you inserted pair into
     */
    public Node add(KVPair pair) {
        if (!this.isFull()) {
            if (pair.compareTo(getFirstPair()) == 0) {
                return null;
            }
            this.insert(pair);
            return this;
        } 
        else {
            InternalNode newNode = new InternalNode();
            LeafNode sibiling = new LeafNode();
            if (pair.compareTo(getFirstPair()) == 0 || 
                    pair.compareTo(getSecondPair()) == 0) {
                return null;
            } 
            else if (pair.compareTo(getFirstPair()) < 0) {
                sibiling.setFirstPair(pair);
                sibiling.setPrev(this.prev);
                sibiling.setNext(this);
                this.getPrev().setNext(sibiling);
                this.setPrev(sibiling);
                newNode.setFirstPair(this.getFirstPair());
                newNode.setLeftChild(sibiling);
                newNode.setMiddleChild(this);
                return (Node) newNode;
            } 
            else if (pair.compareTo(getSecondPair()) < 0) {
                sibiling.setFirstPair(pair);
                sibiling.setSecondPair(this.getSecondPair());
                this.setSecondPair(null);
                sibiling.setPrev(this);
                sibiling.setNext(this.next);
                this.next.setPrev(sibiling);
                this.setNext(sibiling);
                newNode.setFirstPair(sibiling.getFirstPair());
                newNode.setLeftChild(this);
                newNode.setMiddleChild(sibiling);
                return (Node) newNode;
            }

            else {
                sibiling.setSecondPair(pair);
                sibiling.setFirstPair(this.getSecondPair());
                this.setSecondPair(null);
                sibiling.setPrev(this);
                sibiling.setNext(this.getNext());
                this.getNext().setPrev(sibiling);
                this.setNext(sibiling);
                newNode.setFirstPair(sibiling.getFirstPair());
                newNode.setLeftChild(this);
                newNode.setMiddleChild(sibiling);
                return (Node) newNode;
            }
        }
    }
    
    /**
     * Gets the first leafNode that contains handle as its key
     * 
     * @param handle
     *            the key that we are looking for
     * @return the first LEafNode that contains the handle otherwise returns
     *         null
     */
    public LeafNode getToLeaf(Handle handle)
    {
        if (!isFull())
        {
            if (handle.compareTo(getFirstPair().key()) == 0)
            {
                return this;
            }
        }
        else {
            if (handle.compareTo(getSecondPair().key()) == 0)
            {
                return this;
            }
        }
        if (next.getFirstPair().key().compareTo(handle) != 0)
        {
            return null;
        }
        return next;
    }

    /**
     * Strings together the leaf nodes
     * 
     * @param depth
     *            of 2-3 tree
     * @param count
     *            of the current depth
     * @return string representation of leaf node
     */
    public String toString(int depth, int count) {
        int indent = (depth - count) * 2;
        String indentSpace = indentTimes(indent);
        String ans = indentSpace + this.getFirstPair().toString();
        if (this.getSecondPair() != null) {
            ans += " " + this.getSecondPair().toString();
        }
        ans += "\n";
        return ans;
    }

    /**
     * Gets depth of node
     * 
     * @param node
     *            to get the depth from
     * @return the depth of tree
     */
    public int getDepth(Node node) {
        return 1;
    }

    /**
     * Helper method for toString
     * 
     * @param indentTimes
     *            how many times you need to indent
     * @return a string array of spaces
     */
    private String indentTimes(int indentTimes) {
        char[] indentSpace = new char[indentTimes];
        for (int i = 0; i < indentTimes; i++) {
            indentSpace[i] = ' ';
        }
        String indent = new String(indentSpace);
        return indent;
    }

    /**
     * Return first pair o KVPairs
     * 
     * @return first pair of handles
     */
    public KVPair getFirstPair() {
        return firstPair;
    }

    /**
     * Sets the first pair to newLeft
     * 
     * @param newLeft
     *            pair that sets original
     */
    public void setFirstPair(KVPair newLeft) {
        firstPair = newLeft;
    }

    /**
     * Returns the second KVPair
     * 
     * @return second pair
     */
    public KVPair getSecondPair() {
        return secondPair;
    }

    /**
     * Sets the second KVPair in node
     * 
     * @param newRight
     *            for the second pair
     */
    public void setSecondPair(KVPair newRight) {
        secondPair = newRight;
    }

    /**
     * Checks if node is full or not
     * 
     * @return true if node is full
     */
    public boolean isFull() {
        return firstPair != null && secondPair != null;
    }

    /**
     * True if only first pair is occupied
     * 
     * @return true if only first pair is used
     */
    public boolean onlyFirstNode() {
        return firstPair != null && secondPair == null;
    }

    /**
     * True if only second pair is used
     * 
     * @return true if only second pair is used
     */
    public boolean onlySecNode() {
        return firstPair == null && secondPair != null;
    }

    /**
     * Check first and second pairs to see where to insert to see where you can
     * insert
     * 
     * @param pair
     *            to be inserted
     */
    public void insert(KVPair pair) {
        if (firstPair == null && secondPair == null) {
            setFirstPair(pair);
        } 
        else if (onlyFirstNode()) {
            setSecondPair(pair);
        } 
        else if (onlySecNode()) {
            setFirstPair(pair);
        }
        if (isFull()) {
            swap();
        }
    }

    /**
     * Shift method for full nodes
     */
    public void swap() {
        KVPair temp = secondPair;
        if (secondPair.compareTo(firstPair) < 0) {
            temp = secondPair;
            secondPair = firstPair;
            firstPair = temp;
        }
    }

}
