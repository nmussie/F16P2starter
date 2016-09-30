/**
 * Internal nodes class
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

public class InternalNode extends Node {

    private Node left;
    private Node middle;
    private Node right;

    /**
     * Empty Constructor
     */
    public InternalNode() {
        super();
        left = null;
        middle = null;
        right = null;
    }

    /**
     * Constructor that takes handles
     * 
     * @param firstPair
     *            of handles
     * @param secPair
     *            of handles
     */
    public InternalNode(KVPair firstPair, KVPair secPair) {
        super(firstPair, secPair);
        left = null;
        middle = null;
        right = null;
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
     * Returns true if is leaf node
     * 
     * @return true if leaf node
     */
    public boolean isLeafNode() {
        return left == null && right == null && middle == null;
    }

    /**
     * Sets next node
     * 
     * @param newRight
     *            sets previous node
     */
    public void setRightChild(Node newRight) {
        right = newRight;
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
     * @param newLeft
     *            node that sets the previous node
     */
    public void setLeftChild(Node newLeft) {
        left = newLeft;
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
     * @param newMiddle
     *            node to be set
     */
    public void setMiddleChild(Node newMiddle) {
        middle = newMiddle;
    }

    public Node add(KVPair pair) {
        Node check = new InternalNode();
        if (!isFull()) {
            if (pair.compareTo(getFirstPair()) < 0) {
                check = this.left.add(pair);
                if (!check.getClass().equals(LeafNode.class)) {

                    this.insert(check.getFirstPair());
                    this.setRightChild(middle);
                    this.setMiddleChild(((InternalNode) check).getMiddleChild());
                    this.setLeftChild(((InternalNode) check).getLeftChild());
                    check = null;
                    return this;
                }
                return this;

            } else if (pair.compareTo(getSecondPair()) < 0) {
                check = this.middle.add(pair);
                if (!check.getClass().equals(LeafNode.class)) {
                    this.insert(check.getFirstPair());
                    this.setMiddleChild(((InternalNode) check).getLeftChild());
                    this.setRightChild(((InternalNode) check).getMiddleChild());
                    check = null;
                    return this;
                }
            } else {
                check = this.right.add(pair);
                if (!check.getClass().equals(LeafNode.class))
                {
                    this.insert(check.getFirstPair());
                    this.setMiddleChild(((InternalNode) check).getLeftChild());
                    this.setRightChild(((InternalNode) check).getMiddleChild());
                    check = null;
                    return this;                                      
                }
            }
        } 
        else {

        }
        return this;
    }
    public String toString(int depth, int count)
    {
        int indent = (depth - count)*2;
        String indentSpace  = new String(new char[indent]).replace('\0', ' ');
        String ans = indentSpace + this.getFirstPair().toString();
        if (this.getSecondPair() != null)
        {
            ans += " " + this.getSecondPair().toString();
        }
        
        ans += "/n" + this.getLeftChild().toString(depth, count - 1) + 
                this.getMiddleChild().toString(depth, count - 1);
        if (this.getRightChild() != null)
        {
            ans += this.getRightChild().toString(depth, count - 1) ; 
        }
        
        return ans;
    }
    public int getDepth(Node node)
    {
        if (node == null) {
            return 0;
        }
        if (!((InternalNode) node).getClass().equals(LeafNode.class))
        {
            return 1 + getDepth(((InternalNode) node).getLeftChild());
        }
        else {
            return 1;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
