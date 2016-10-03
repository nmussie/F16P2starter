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

    /*public boolean contains(Node node, KVPair pair) {
        node = getToLeaf(node, pair);
        return node.getFirstPair().equals(pair) || 
        node.getSecondPair().equals(pair));
    }

    private LeafNode getToLeaf(Node node, Handle handle) {
        if (root == null) { // empty tree
            return null;
        }
        if (node.getClass() == LeafNode.class) { // at leaf node
            return (LeafNode) node;
        } 
        else { // get to leaf node

            // if newPair is smaller than left
            if (handle.compareTo(node.getFirstPair()) < 0) {
                Node leftChild = ((InternalNode) node).getLeftChild();
                return getToLeaf(leftChild, newPair);
            }
            // if newPair is in middle of left and right
            else if ((newPair.compareTo(node.getFirstPair()) > 0) 
            && newPair.compareTo(node.getSecondPair()) < 0) {
                Node middleChild = ((InternalNode) node).getRightChild();
                return getToLeaf(middleChild, newPair);
            }
            // if newPair bigger than right
            else {
                Node rightChild = ((InternalNode) node).getRightChild();
                return getToLeaf(rightChild, newPair);
            }
        }
    }*/

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
     * Counts all handles with the same key
     * @param handle needed to be found
     * @return how many times that handle is in the tree
     */
    private int countHandles(Handle handle) {
        int count = 0;
        if (root == null) {
            return 0;
        }
        LeafNode curr = head.getNext();
        while (curr != tail) {
            if (curr.getFirstPair().key().equals(handle)) {
                count++;
            }
            if (curr.getSecondPair() != null &&
                    curr.getSecondPair().key().equals(handle)) {
                count++;
            }
            curr = curr.getNext();
        }
        return count;
    }
    
    /**
     * Returns an array of handles you need for list
     * @param handle to be compared to
     * @return a handle array of desired handles
     */
    public Handle[] list(Handle handle)
    {
        Handle[] array = new Handle[countHandles(handle)];
        int index = 0;
        if (root == null || array.length == 0) {
            return null;
        }
        LeafNode curr = head.getNext();
        while (curr != tail) {
            if (curr.getFirstPair().key().equals(handle)) {
                array[index] = curr.getFirstPair().value();
                index++;
            }
            if (curr.getSecondPair() != null &&
                    curr.getSecondPair().key().equals(handle)) {
                array[index] = curr.getSecondPair().value();
                index++;
            }
            curr = curr.getNext();
        }
        return array;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
