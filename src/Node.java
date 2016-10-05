/**
 * Node interface for 2-3 tree
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

public interface Node {

    /**
     * Return first pair o KVPairs
     * 
     * @return first pair of handles
     */
    public KVPair getFirstPair();

    /**
     * Sets the first pair to newLeft
     * 
     * @param newLeft
     *            pair that sets original
     */
    public void setFirstPair(KVPair newLeft);

    /**
     * Returns the second KVPair
     * 
     * @return second pair
     */
    public KVPair getSecondPair();

    /**
     * Sets the second KVPair in node
     * 
     * @param newRight
     *            for the second pair
     */
    public void setSecondPair(KVPair newRight);

    /**
     * Checks if node is full or not
     * 
     * @return true if node is full
     */
    public boolean isFull();

    /**
     * True if only first pair is occupied
     * 
     * @return true if only first pair is used
     */
    public boolean onlyFirstNode();

    /**
     * True if only second pair is used
     * 
     * @return true if only second pair is used
     */
    public boolean onlySecNode();

    /**
     * Compares newPair to full node and returns the larger of the three.
     * 
     * @param newPair
     *            thats added when node is full
     * @return KVPair that's largest of the three
     */
    // public KVPair addWhenFull(KVPair newPair);

    /**
     * Check first and second pairs to see where to insert to see where you can
     * insert
     * 
     * @param pair
     *            to be inserted
     */
    public void insert(KVPair pair);

    /**
     * Shift method for full nodes
     */
    public void swap();

    /**
     * Add method for nodes
     * 
     * @param pair
     *            to be added
     * @return the node that the pair was added to
     */
    public Node add(KVPair pair);

    /**
     * Strings together the 2-3 tree
     * 
     * @param depth
     *            of 2-3 tree
     * @param count
     *            of the current depth
     * @return string representation of tree
     */
    public String toString(int depth, int count);

    /**
     * Gets depth of node
     * 
     * @param node
     *            to get the depth from
     * @return the depth of tree
     */
    public int getDepth(Node node);

    /**
     * Gets the first leafNode that contains handle as its key
     * 
     * @param handle
     *            the key that we are looking for
     * @return the first LEafNode that contains the handle otherwise returns
     *         null
     */
    public LeafNode getToLeaf(Handle handle);

    /**
     * deletes the kvpair from the tree
     * 
     * @param pair
     *            to be deleted
     * @return a node of the new restructured tree or a null
     */
    public Node delete(KVPair pair);

    /**
     * checks if the node has no kvPairs
     * 
     * @return true is there are no kvPairs
     */
    public boolean isEmpty();

}
