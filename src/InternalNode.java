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

public class InternalNode implements Node {

    private Node left;
    private Node middle;
    private Node right;
    private KVPair firstPair;
    private KVPair secondPair;

    /**
     * Empty Constructor
     */
    public InternalNode() {
        setFirstPair(null);
        setSecondPair(null);
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
        setFirstPair(firstPair);
        setSecondPair(secPair);
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
     * Return first pair o KVPairs
     * 
     * @return first pair of handles
     */
    public KVPair getFirstPair() {
        return firstPair;
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

    /**
     * Add method for internal nodes
     * 
     * @param pair
     *            to be inserted
     * @return current node where pair was inserted
     */
    public Node add(KVPair pair) {
        Node check = new InternalNode();
        // KVPair testPair;
        if (!isFull()) {
            if (pair.compareTo(getFirstPair()) == 0) {
                return null;
            }
            if (pair.compareTo(getFirstPair()) < 0) {
                check = this.left.add(pair);
                if (check == null) {
                    return null;
                }
                if (check.isFull() || (this.left.getFirstPair().compareTo(check.getFirstPair()) == 0)) {
                    return this;
                }
                if (!check.getClass().equals(LeafNode.class)) {
                    this.insert(check.getFirstPair());
                    this.setRightChild(middle);
                    this.setMiddleChild(((InternalNode) check).getMiddleChild());
                    this.setLeftChild(((InternalNode) check).getLeftChild());
                    check = null;
                    return this;
                }
                return this;
            }
            else {
                // testPair = this.middle.getFirstPair();
                check = this.middle.add(pair);
                if (check == null) {
                    return null;
                }

                if (check.isFull() || (this.middle.getFirstPair().compareTo(check.getFirstPair()) == 0)) {
                    return this;
                }
                if (!check.getClass().equals(LeafNode.class)) {
                    this.insert(check.getFirstPair());
                    this.setMiddleChild(((InternalNode) check).getLeftChild());
                    this.setRightChild(((InternalNode) check).getMiddleChild());
                    check = null;
                    return this;
                }
                return this;
            }
        }
        else {
            if (pair.compareTo(getFirstPair()) == 0 || pair.compareTo(getSecondPair()) == 0) {
                return null;
            }
            if (pair.compareTo(getFirstPair()) < 0) {
                check = this.left.add(pair);
                if (check == null) {
                    return null;
                }
                if (check.isFull() || (this.left.getFirstPair().compareTo(check.getFirstPair()) == 0)) {
                    return this;
                }
                if (!check.getClass().equals(LeafNode.class)) {
                    InternalNode intNode = new InternalNode(this.getSecondPair(), null);
                    intNode.setMiddleChild(this.right);
                    intNode.setLeftChild(this.middle);
                    this.setLeftChild(check);
                    this.setMiddleChild(intNode);
                    this.setSecondPair(null);
                    this.setRightChild(null);
                    return this;
                }
                return this;
            }
            else if (pair.compareTo(getSecondPair()) < 0) {
                check = this.middle.add(pair);
                if (check == null) {
                    return null;
                }
                if (check.isFull() || (this.middle.getFirstPair().compareTo(check.getFirstPair()) == 0)) {
                    return this;
                }
                if (!check.getClass().equals(LeafNode.class)) {
                    InternalNode intNode = new InternalNode(this.getSecondPair(), null);
                    this.setSecondPair(null);
                    intNode.setMiddleChild(this.right);
                    this.setRightChild(null);
                    intNode.setLeftChild(((InternalNode) check).getMiddleChild());
                    ((InternalNode) check).setLeftChild(this);
                    ((InternalNode) check).setMiddleChild(intNode);
                    return check;
                }
                return this;
            }
            else {
                check = this.right.add(pair);
                if (check == null) {
                    return null;
                }
                if (check.isFull() || (this.right.getFirstPair().compareTo(check.getFirstPair()) == 0)) {
                    return this;
                }
                if (!check.getClass().equals(LeafNode.class)) {
                    InternalNode intNode = new InternalNode(this.getSecondPair(), null);
                    this.setSecondPair(null);
                    intNode.setLeftChild(this);
                    intNode.setMiddleChild(check);
                    this.setRightChild(null);
                    return intNode;
                }
                return this;
            }
        }
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
     * Strings together the internal nodes
     * 
     * @param depth
     *            of 2-3 tree
     * @param count
     *            of the current depth
     * @return string representation of internal node
     */
    public String toString(int depth, int count) {
        int indent = (depth - count) * 2;
        String indentSpace = indentTimes(indent);
        String ans = indentSpace + this.getFirstPair().toString();
        if (this.getSecondPair() != null) {
            ans += " " + this.getSecondPair().toString();
        }

        ans += "\n" + this.getLeftChild().toString(depth, count - 1) + this.getMiddleChild().toString(depth, count - 1);
        if (this.getRightChild() != null) {
            ans += this.getRightChild().toString(depth, count - 1);
        }

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
        if (node == null) {
            return 0;
        }
        if (!node.getClass().equals(LeafNode.class)) {
            return 1 + getDepth(((InternalNode) node).getLeftChild());
        }
        else {
            return 1;
        }

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

    /**
     * Gets the first leafNode that contains handle as its key
     * 
     * @param handle
     *            the key that we are looking for
     * @return the first LaafNode that contains the handle otherwise returns
     *         null
     */
    public LeafNode getToLeaf(Handle handle) {
        LeafNode check;
        if (!isFull()) {
            if (handle.compareTo(getFirstPair().key()) <= 0) {
                check = left.getToLeaf(handle);
                if (check == null) {
                    return null;
                }
                return check;
            }
            else {
                check = middle.getToLeaf(handle);
                if (check == null) {
                    return null;
                }
                return check;
            }
        }
        else {
            if (handle.compareTo(getFirstPair().key()) <= 0) {
                check = left.getToLeaf(handle);
                if (check == null) {
                    return null;
                }
                return check;
            }
            else if (handle.compareTo(getSecondPair().key()) <= 0) {
                check = middle.getToLeaf(handle);
                if (check == null) {
                    return null;
                }
                return check;
            }
            else {
                check = right.getToLeaf(handle);
                if (check == null) {
                    return null;
                }
                return check;
            }
        }
    }

    /**
     * Delete method for internal node
     * 
     * @param
     * @return
     */
    @Override
    public Node delete(KVPair pair) {
        Node check;
        // if internal node isn't full
        if (onlyFirstNode()) {
            if (pair.compareTo(getFirstPair()) < 0) {
                check = this.left.delete(pair);
                // if pair wasn't in the tree
                if (check == null) {
                    return null;
                }
                // if check is a leaf node
                if (check.getClass().equals(LeafNode.class)) { //to save space we could potentially do this in the leaf Node
                    if (check.isEmpty() && (this.getMiddleChild().isFull())) {
                        check.setFirstPair(this.middle.getFirstPair());
                        this.middle.setFirstPair(this.middle.getSecondPair());
                        this.middle.setSecondPair(null);
                        this.setFirstPair(middle.getFirstPair());
                        ((LeafNode) middle).getPrev().setNext((LeafNode) check);
                        ((LeafNode) check).setPrev(((LeafNode) middle).getPrev());
                        ((LeafNode) middle).setPrev((LeafNode) check);
                        ((LeafNode) check).setNext((LeafNode) middle);
                        return this;
                    }
                    // if leaf node is empty
                    else if (check.isEmpty()) {
                        this.setLeftChild(middle);
                        this.setMiddleChild(null);
                        this.setFirstPair(null);
                        return this;
                    }
                    return this;
                }
                else {
                    // if internal node and is not full
                    if (check.isEmpty()) { // if restructure needed
                        if (!middle.isFull()) {
                            middle.setSecondPair(middle.getFirstPair());
                            middle.setFirstPair(this.getFirstPair());
                            this.setFirstPair(null);
                            ((InternalNode) middle).setRightChild(((
                                    InternalNode) middle).getMiddleChild());
                            ((InternalNode) middle).setMiddleChild(((
                                    InternalNode) middle).getLeftChild());
                            ((InternalNode) middle).setLeftChild(((
                                    InternalNode) check).getLeftChild());
                            this.setLeftChild(middle);
                            this.setMiddleChild(null);
                            check = null;
                            return this;
                        }
                        else {
                            check.setFirstPair(this.getFirstPair());
                            //this.setFirstPair(middle.getFirstPair());
                            middle.setFirstPair(middle.getSecondPair());
                            middle.setSecondPair(null);
                            ((InternalNode) check).setMiddleChild(((
                                    InternalNode) middle).getLeftChild());
                            ((InternalNode) middle).setLeftChild(((
                                    InternalNode) middle).getMiddleChild());
                            ((InternalNode) middle).setMiddleChild(((
                                    InternalNode) middle).getRightChild());
                            ((InternalNode) middle).setRightChild(null);
                            this.setFirstPair(middle.getLeast());//changed
                            return this;
                        }
                    }
                    else {
                        return this;
                    }
                }
            }
            else if (pair.compareTo(getFirstPair()) >= 0 ) {
                check = this.middle.delete(pair);
                // if pair wasn't in the tree
                if (check == null) {
                    return null;
                }
                // if check is a leaf node
                if (check.getClass().equals(LeafNode.class)) { //to save space we could potentially do this in the leaf Node
                    if (check.isEmpty() && (this.getLeftChild().isFull())) {
                        check.setFirstPair(this.getLeftChild().getSecondPair());
                        this.getLeftChild().setSecondPair(null);
                        //this.setFirstPair(middle.getFirstPair());
                        ((LeafNode) left).getNext().setPrev((LeafNode) check);
                        ((LeafNode) check).setNext(((LeafNode) left).getNext());

                        ((LeafNode) left).setNext((LeafNode) check);
                        ((LeafNode) check).setPrev((LeafNode) left);
                        this.setFirstPair(middle.getLeast());//changed
                        return this;
                    }
                    // if leaf node is empty
                    else if (check.isEmpty()) {
                        this.setMiddleChild(null);
                        this.setFirstPair(null);
                        
                        return this;
                    }
                    return this;
                }
                else {
                    // if internal node and is not full
                    if (check.isEmpty()) {
                        if (!this.getLeftChild().isFull()) {
                            ((InternalNode) getLeftChild()).setRightChild(((InternalNode) check).getLeftChild());
                            this.getLeftChild().setSecondPair(this.getFirstPair());
                            this.setMiddleChild(null);
                            this.setFirstPair(null);
                            return this;
                        }
                    }
                    else {
                        this.setFirstPair(middle.getLeast());
                        return this;
                    }
                }
            }
        }
        // if internal node is full
        else {
            // left case if this is full
            if (pair.compareTo(getFirstPair()) < 0) {
                check = this.left.delete(pair);
                // if pair wasn't in the tree
                if (check == null) {
                    return null;
                }
                // if check is a leaf node
                if (check.getClass().equals(LeafNode.class)) { //to save space we could potentially do this in the leaf Node
                    if (check.isEmpty() && (this.getMiddleChild().isFull())) {
                        check.setFirstPair(this.middle.getFirstPair());
                        this.middle.setFirstPair(this.middle.getSecondPair());
                        this.middle.setSecondPair(null);
                        this.setFirstPair(middle.getFirstPair());
                        ((LeafNode) middle).getPrev().setNext((LeafNode) check);
                        ((LeafNode) check).setPrev(((LeafNode) middle).getPrev());
                        ((LeafNode) middle).setPrev((LeafNode) check);
                        ((LeafNode) check).setNext((LeafNode) middle);
                        return this;
                    }
                    // if leaf node is empty
                    else if (check.isEmpty()) {
                        this.setFirstPair(secondPair);
                        this.setSecondPair(null);
                        this.setLeftChild(middle);
                        this.setMiddleChild(right);
                        this.setRightChild(null);
                        return this;
                    }
                    return this;
                }
                else {
                    // if internal node and full// why full??
                    if (check.isEmpty())
                    {
                        //middle child not full 
                        if (!getMiddleChild().isFull())
                        {
                            middle.setSecondPair(middle.getFirstPair());
                            middle.setFirstPair(this.getFirstPair());
                            //this.setFirstPair(this.getSecondPair());
                            this.setSecondPair(null);
                            ((InternalNode) middle).setRightChild(((InternalNode) middle).getMiddleChild());
                            ((InternalNode) middle).setMiddleChild(((InternalNode) middle).getLeftChild());
                            ((InternalNode) middle).setLeftChild(((InternalNode) check).getLeftChild());
                            this.setLeftChild(middle);
                            this.setMiddleChild(right);
                            this.setRightChild(null);
                            this.setFirstPair(middle.getLeast());//changed
                            return this;                            
                        }
                        else {
                            //middle child full
                            check.setFirstPair(this.getFirstPair());
                            ((InternalNode) check).setMiddleChild(((InternalNode) this.getMiddleChild()).getLeftChild());
                            //this.setFirstPair(middle.getFirstPair());
                            middle.setFirstPair(middle.getSecondPair());
                            middle.setSecondPair(null);
                            ((InternalNode) middle).setLeftChild(((InternalNode) middle).getMiddleChild());
                            ((InternalNode) middle).setMiddleChild(((InternalNode) middle).getRightChild());
                            ((InternalNode) middle).setRightChild(null);
                            this.setFirstPair(middle.getLeast());//changed
                            return this;
                        }
                    }
                }
                return this;
            }
            // middle case if this is full
            if (pair.compareTo(getSecondPair()) < 0 &&
                    pair.compareTo(getFirstPair()) >= 0) {
                check = this.middle.delete(pair);
                if (check == null) {
                    return null;
                }
                // if check is a leaf node
                if (check.getClass().equals(LeafNode.class)) {
                    // if the previous leaf node is full
                    if (check.isEmpty() && (this.getLeftChild().isFull())) {
                        check.setFirstPair(this.getLeftChild().getSecondPair());
                        this.getLeftChild().setSecondPair(null);
                        this.setFirstPair(check.getFirstPair());
                        //this.setFirstPair(getLeast(middle));//changed
                        ((LeafNode) left).getNext().setPrev((LeafNode) check);
                        ((LeafNode) check).setNext(((LeafNode) left).getNext());
                        ((LeafNode) left).setNext((LeafNode) check);
                        ((LeafNode) check).setPrev((LeafNode) left);
                        
                        return this;
                    }
                    // if previous leaf node isn't full and check is empty
                    else if (check.isEmpty()) {
                        this.setFirstPair(secondPair);
                        this.setSecondPair(null);
                        this.setMiddleChild(right);
                        this.setRightChild(null);
                        return this;
                    }
                    return this;
                }
                else {
                    // if check is internal node and full
                    if (check.isEmpty()) {
                        if (this.getLeftChild().isFull()) {
                            check.setFirstPair(this.getFirstPair());
                            //this.setFirstPair(this.getLeftChild().getSecondPair());
                            getLeftChild().setSecondPair(null);
                            ((InternalNode)check).setMiddleChild(((InternalNode) check).getLeftChild());
                            ((InternalNode)check).setLeftChild(((InternalNode)getLeftChild()).getRightChild());
                            ((InternalNode) getLeftChild()).setRightChild(null);
                            this.setFirstPair(middle.getLeast());//changed
                            return this;
                        }
                        //left child isn't full
                        else {
                            this.getLeftChild().setSecondPair(this.getFirstPair());
                            this.setFirstPair(getSecondPair());
                            this.setSecondPair(null);
                            ((InternalNode) getLeftChild()).setRightChild(((InternalNode) check).getLeftChild());
                            this.setMiddleChild(this.getRightChild());
                            this.setRightChild(null);
                            return this;
                        }
                    }
                   return this;
                }
            }
            else {
                check = this.right.delete(pair);
                if (check == null) {
                    return null;
                }
                // if check is a leaf node
                if (check.getClass().equals(LeafNode.class)) {
                    // if the previous leaf node is full
                    if (check.isEmpty() && (this.middle.isFull())) {//to save space we could potentially do this in the leaf Node
                        check.setFirstPair(middle.getSecondPair());
                        middle.setSecondPair(null);
                        //this.setSecondPair(check.getFirstPair());
                        ((LeafNode) middle).getNext().setPrev((LeafNode) check);
                        ((LeafNode) check).setNext(((LeafNode) middle).getNext());

                        ((LeafNode) middle).setNext((LeafNode) check);
                        ((LeafNode) check).setPrev((LeafNode) middle);
                        this.setSecondPair(right.getLeast());//changed
                        return this;
                    }
                    // if the previous leaf node is not full
                    else if (check.isEmpty()) {
                        this.setSecondPair(null);
                        this.setRightChild(null);
                        return this;
                    }
                    return this;
                }
                else {
                    // if check is internal node and full
                    if (check.isEmpty())
                    {
                        if (middle.isFull()) {
                            check.setFirstPair(this.getSecondPair());
                            ((InternalNode) check).setMiddleChild(((InternalNode) check).getLeftChild());
                            ((InternalNode) check).setLeftChild(((InternalNode) middle).getRightChild());
                            ((InternalNode) middle).setRightChild(null);
                            //this.setSecondPair(middle.getSecondPair());
                            this.setSecondPair(right.getLeast());//changed
                            middle.setSecondPair(null);
                            return this;
                        }
                        else {
                            middle.setSecondPair(this.secondPair);
                            this.setSecondPair(null);
                            ((InternalNode) middle).setRightChild(((InternalNode) check).getLeftChild());
                            this.setRightChild(null);
                            check = null;
                            return this;
                        }
                    }
                }
                return this;
            }
        }
        return this;
    }
    /**
     * checks if the node is empty
     * @return true is the nodes pairs are both null
     */
    @Override
    public boolean isEmpty() {
        return firstPair == null && secondPair == null;
    }
    public KVPair getLeast()
    {
        /*if (newNode == null)
        {
            return null;
        }*/
        KVPair least = left.getLeast();//getLeast(((Node)(((InternalNode) newNode).getLeftChild())));
        return least;
    }
}
