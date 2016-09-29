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

    /**
     * Constructor for Tree class
     */
    public TTT() {
        root = null;
    }

    /**
     * Insert method for 2-3+ tree
     * 
     * @param newPair
     *            to be inserted
     * @return true if item is inserted
     */
    public Node insert2(Node node, KVPair newPair) {
        if (node == null) {
            node = new LeafNode(newPair, null);
            return node;
        }
        if (node.getClass() == LeafNode.class) {//.equals()?
            if (!node.isFull())
            {
                node.insert(newPair);
                return node;
            }
            else
            {
                newPair = promoteNode((LeafNode)node, newPair);
                InternalNode n = new InternalNode();
                n.setFirstPair(newPair);
                n.setLeftChild(node);
                n.setMiddleChild(((LeafNode) node).getNext());
                node = n;
                return node;
            }
        }
        else
        {
            InternalNode nroot = (InternalNode) node;
            if (!node.isFull())
            {
                if (newPair.compareTo(nroot.getFirstPair()) <= 0)
                {
                    nroot.setLeftChild(insert2(nroot.getLeftChild(), newPair));
                    return nroot;
                }
                else
                {
                    
                    nroot.setMiddleChild(insert2(nroot.getMiddleChild(), newPair));
                    return nroot;
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param pair
     * @return
     */
    public boolean insert(KVPair pair) {
        root = getToLeaf(root, pair);
        
        if (root.isFull()) {
            LeafNode bigLeaf = new LeafNode(root.addWhenFull(pair), null);
            KVPair promote = root.getSecondPair();
        }
        else {
            root.insert(pair);
        }
        return false;
    }
    
    /**
     * 
     * @param node
     * @param pair
     * @return
     */
    public boolean contains(Node node, KVPair pair) {
        node = getToLeaf(node, pair);
        if (node.getFirstPair().equals(pair) || node.getSecondPair().equals(pair)) {
            return true;
        }
        return false;
    }
    
    private KVPair promoteNode(LeafNode node, KVPair pair) {
        //if (node.isFull()) {
            pair = node.addWhenFull(pair);
            LeafNode newLeaf = new LeafNode(pair, null);
            //InternalNode intNode = new InternalNode(node.getSecondPair(), null);
            node.setNext(newLeaf);
            newLeaf.setPrev(node);
            //intNode.setLeftChild(node);
            //intNode.setMiddleChild(newLeaf);
            return node.getSecondPair();
        //}
    }
    
    /**
     * 
     * @param node
     * @param newPair
     * @return
     */
    private LeafNode getToLeaf(Node node, KVPair newPair) {
        if (node == null) { //empty tree
            return new LeafNode(newPair, null);
        }
        if (node.getClass() == LeafNode.class) { //at leaf node 
            return (LeafNode)node;
        }
        else { //get to leaf node
            
            //if newPair is smaller than left
            if (newPair.compareTo(node.getFirstPair()) < 0) {
                Node leftChild = ((InternalNode) node).getLeftChild();
                return getToLeaf(leftChild, newPair);
            }
            //if newPair is in middle of left and right
            else if ((newPair.compareTo(node.getFirstPair()) > 0) 
                    && newPair.compareTo(node.getSecondPair()) < 0) {
                Node middleChild = ((InternalNode) node).getRightChild();
                return getToLeaf(middleChild, newPair);
            }
            //if newPair bigger than right
            else {
                Node rightChild = ((InternalNode) node).getRightChild();
                return getToLeaf(rightChild, newPair);
            }
        }
    }
    
    /**
     * To string method for 2-3+ tree
     * @return a string representation of a tree
     */
    public String toString() {
        if (root == null) {
            return "Printing 2-3 tree:";
        } 
        else if (root.isFull()) {
            return "Printing 2-3 tree:\n" + root.getFirstPair().
                    toString() + " " + root.getSecondPair().toString();
        } 
        else {
            return "Printing 2-3 tree:\n" + root.getFirstPair().toString();
        }
    }

    /**
     * Getter for root node
     * 
     * @return root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Sets curr root to root
     * 
     * @param root
     *            that sets curr root
     */
    public void setRoot(Node root) {
        this.root = root;
    }

}
