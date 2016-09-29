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
public class LeafNode extends Node {

    private LeafNode next;
    private LeafNode prev;

    /**
     * Constructor that takes handles as parameters
     * 
     * @param firstPair
     *            of handles
     * @param secPair
     *            of handles
     */
    public LeafNode(KVPair firstPair, KVPair secPair) {
        super(firstPair, secPair);
        setNext(null);
        setPrev(null);
    }

    /**
     * Empty constructors
     */
    public LeafNode() {
        super();
        setNext(null);
        setPrev(null);
    }

    /**
     * Checks if node is leaf
     * 
     * @return true if node is leaf node
     */
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

    public LeafNode getPrev() {
        return prev;
    }

    public void setPrev(LeafNode prev) {
        this.prev = prev;
    }

}
