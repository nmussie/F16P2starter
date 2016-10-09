/**
 * Class containing the 2-3+ Tree
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

public class TTT {

    private Node root;
    private LeafNode head;
    private LeafNode tail;

    /**
     * Constructor for Tree class
     */
    public TTT() {
        root = null;
        head = new LeafNode();
        tail = new LeafNode();
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Insert method for 2-3+ tree
     * 
     * @param newPair
     *            to be inserted
     * @return true if item is inserted
     */
    public Node insert(KVPair newPair) {
        if (root == null) {
            root = new LeafNode(newPair, null);
            ((LeafNode) root).setNext(tail);
            ((LeafNode) root).setPrev(head);
            head.setNext((LeafNode) root);
            tail.setPrev((LeafNode) root);
            return root;
        }
        Node temp = root.add(newPair);
        if (temp == null) {
            return null;
        }
        root = temp;
        return root;
    }

    /**
     * To string method for 2-3+ tree
     * 
     * @return a string representation of a tree
     */
    public String toString() {
        if (root == null) {
            return "Printing 2-3 tree:";
        }
        int depth = root.getDepth(root);
        int count = depth;
        // System.out.println("Current Depth: " + depth);
        String result = "Printing 2-3 tree:\n" + root.toString(depth, count);
        return result.substring(0, result.length() - 1);
    }

    /**
     * Getter for root node
     * 
     * @return root of tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Returns an array of handles you need for list
     * 
     * @param handle
     *            to be compared to
     * @return a handle array of desired handles
     */
    public Handle[] list(Handle handle) {
        if (root == null || handle == null) {
            return null;
        }
        LeafNode found = root.getToLeaf(handle);
        if (found == null) {
            return null;
        }
        int count = 0;
        Handle[] array = new Handle[10];
        while (found != tail
                && found.getFirstPair().key().compareTo(handle) == 0) {
            array[count] = found.getFirstPair().value();
            count++;
            realoc(array, count, false);
            if (found.getSecondPair() != null
                    && found.getSecondPair().key().compareTo(handle) == 0) {
                array[count] = found.getSecondPair().value();
                count++;
                realoc(array, count, false);
            }
            found = found.getNext();
        }
        array = realoc(array, count, true);
        return array;
    }

    /**
     * Reallocates a handle array
     * 
     * @param array
     *            needed to be reallocated
     * @param i
     *            the size array should be
     * @param sizeBack
     *            checks to see if array needs to be
     * @return an array thats reallocated if needed
     */
    private Handle[] realoc(Handle[] array, int i, boolean sizeBack) {
        if (sizeBack) {
            Handle[] newArray = new Handle[i];
            System.arraycopy(array, 0, newArray, 0, newArray.length);
            return newArray;
        }
        else if (i == array.length - 1) {
            Handle[] newArray = new Handle[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            return newArray;
        }
        else {
            return array;
        }
    }
    
    /**
     * Removes every KVPair in the tree with handle as the key
     * 
     * @param handle
     *            to be removed
     * @param values
     *            array of handles needed to removed
     */
    public void remove(Handle handle, Handle[] values) {
        // Handle[] values = list(handle);
        if (values == null || values.length == 0) {
            return;
        }
        for (int i = 0; i < values.length; i++) {
            KVPair artistPair = new KVPair(handle, values[i]);
            KVPair songPair = new KVPair(values[i], handle);
            delete(artistPair);
            delete(songPair);
        }
    }
    
    /**
     * Deletes one instance of a KVPair
     * 
     * @param pair
     *            to be deleted
     * @return true if pair was deleted
     */
    public boolean delete(KVPair pair) {
        if (root == null) {
            return false;
        }
        Node temp = root.delete(pair);
        if (temp != null) {
            root = temp;
            if (temp.isEmpty() && temp.getClass().equals(LeafNode.class)) {
                root = null;
            }
            else if (temp.isEmpty()) {
                root = ((InternalNode) temp).getLeftChild();
            }
            return true;
        }
        return false;
    }
    
    /**
     * Contains method for tree
     * 
     * @param handle
     *            tree is searching for
     * @return true if tree contains handle
     */
    public boolean contains(Handle handle) {
        if (root == null) {
            return false;
        }
        Handle[] array = list(handle);
        return array != null && array.length != 0;
    }
}
