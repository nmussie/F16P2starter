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
    
    public TTT() {
        root = null;
    }
    
    public boolean insert(KVPair newPair) {
        if (root == null) {
            root = new LeafNode(newPair, null);
            return true;
        }
        if (root.isLeafNode()) {
            root.insert(newPair);
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param pair
     * @return
     *//*
    public boolean insert(KVPair pair) {
        root = getToLeaf(root, pair);
        
        if (root.isFull()) {
            LeafNode bigLeaf = new LeafNode(root.addWhenFull(pair), null);
            KVPair promote = root.getRightPair();
            
        }
        return false;
    }
    
    *//**
     * 
     * @param node
     * @param newPair
     * @return
     *//*
    private LeafNode getToLeaf(Node node, KVPair newPair) {
        if (node == null) { //empty tree
            return new LeafNode(newPair, null);
        }
        if (node.isLeafNode()) { //at leaf node 
            return (LeafNode)node;
        }
        else { //get to leaf node
            
            //if newPair is smaller than left
            if (newPair.compareTo(node.getLeftPair()) < 0) {
                Node leftChild = ((InternalNode) node).getLeftChild();
                return getToLeaf(leftChild, newPair);
            }
            //if newPair is in middle of left and right
            else if ((newPair.compareTo(node.getLeftPair()) > 0) 
                    && newPair.compareTo(node.getRightPair()) < 0) {
                Node middleChild = ((InternalNode) node).getRightChild();
                return getToLeaf(middleChild, newPair);
            }
            //if newPair bigger than right
            else {
                Node rightChild = ((InternalNode) node).getRightChild();
                return getToLeaf(rightChild, newPair);
            }
        }
    }*/
    
    
    public String toString() {
        if (root == null) {
            return "Printing 2-3 tree:";
        }
        else if (root.isFull()) {
        return "Printing 2-3 tree:\n" + root.getFirstPair().toString() 
                + " " + root.getSecondPair().toString(); 
        }
        else {
            return "Printing 2-3 tree:\n" + root.getFirstPair().toString();
        }
    }
    
    
    public boolean delete(KVPair pair) {
        return false;
    }
    
    public boolean contains(int key) {
        return false;
    }


    public Node getRoot() {
        return root;
    }


    public void setRoot(Node root) {
        this.root = root;
    }
    

}
