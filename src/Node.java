/**
 * Node abstract class
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

public abstract class Node {

    private KVPair firstPair;
    private KVPair secondPair;

    /**
     * Constructor that takes KVPair parameters
     * 
     * @param firstPair
     *            of handles
     * @param secPair
     *            of handles
     */
    public Node(KVPair firstPair, KVPair secPair) {
        setFirstPair(firstPair);
        setSecondPair(secPair);
    }

    /**
     * Empty constructor
     */
    public Node() {
        setFirstPair(null);
        setSecondPair(null);
    }

    /**
     * Returns true if is leaf node
     * 
     * @return true if is leaf node
     */
    public boolean isLeafNode() {
        return false;
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
     * Compares newPair to full node and returns the larger of the three.
     * 
     * @param newPair
     *            thats added when node is full
     * @return KVPair that's largest of the three
     */
    public KVPair addWhenFull(KVPair newPair) {
        KVPair temp = newPair;
        if (newPair.compareTo(firstPair) < 0) {
            temp = getSecondPair();
            secondPair = firstPair;
            firstPair = newPair;
        } 
        else if (newPair.compareTo(secondPair) < 0) {
            temp = getSecondPair();
            secondPair = newPair;
        }
        return temp;
    }
    /**
     * Check first and second pairs to see where to insert to see where you can
     * insert
     * 
     * @param pair
     *            to be inserted
     */
    public void insert(KVPair pair) {
        if (onlyFirstNode()) {
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
