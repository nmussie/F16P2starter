//import FBList.Node;

/**
 * Class containing the doubly linked list connected to the FreeBlock class
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

public class FBList {

    private int size;
    private Node head;
    private Node tail;
    private int poolSize;

    /**
     * Constructor of Free Blocks List
     * 
     * @param poolSize
     *            of memPool
     */
    public FBList(int poolSize) {
        size = 0;
        this.poolSize = poolSize;
        head = new FBList.Node(null);
        tail = new FBList.Node(null);
        head.setNext(tail);
        tail.setPrevious(head);
    }

    /**
     * Gets the size of list
     * 
     * @return size of list
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines the index of the best fit
     * 
     * @param inputSize
     *            size of block
     * @return the index of the best fit
     */
    public int bestFit(int inputSize) {
        int subtraction = -1;
        int bestFitIndex = -1;
        int bestFit = poolSize;
        int index = -1;
        for (int i = 0; i < size; i++) {
            Node curr = getNodeAt(i);
            int nodeSize = curr.getData().getSize();
            if (inputSize <= nodeSize) {
                subtraction = nodeSize - inputSize;
                if (subtraction < bestFit) {
                    bestFit = subtraction;
                    bestFitIndex = curr.getData().getPos();
                    index = i;
                }
            }
        }
        if (index != -1) {
            get(index).setSize(bestFit);
            get(index).setPos(bestFitIndex + inputSize);
        }
        return bestFitIndex;
    }

    /**
     * Adds a free block to list at index
     * 
     * @param data
     *            needed to be added
     * @param index
     *            of where to add
     * @return true if block was added
     */
    public boolean add(FreeBlock data, int index) {
        Node curr = getNodeAt(index);
        if (curr == null || data == null) {
            return false;
        }
        Node newNode = new Node(data);
        if (index == size) {
            newNode.setNext(tail);
            newNode.setPrevious(tail.getPrevious());
            tail.getPrevious().setNext(newNode);
            tail.setPrevious(newNode);
            size++;
            return true;
        }
        //Node curr = getNodeAt(index);
        /*if (curr == null || data == null) {
            return false;
        }*/
        newNode.setNext(curr);
        newNode.setPrevious(curr.getPrevious());
        curr.getPrevious().setNext(newNode);
        curr.setPrevious(newNode);
        size++;
        return true;
    }

    /**
     * Adds a block at the end of the list
     * 
     * @param data
     *            of free block added
     * @return true if block is added
     */
    public boolean add(FreeBlock data) {
        return add(data, size);
    }

    /**
     * Gets a free block given the index
     * 
     * @param index
     *            of where block is in list
     * @return block at index
     */
    public FreeBlock get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node curr = head.getNext();
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Gets node at a given index
     * 
     * @param index
     *            of the node
     * @return node at index
     */
    private Node getNodeAt(int index) {
        if (index < 0 || index > size)
        {
            return null;
        }
        Node curr = head.getNext();
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }

    /**
     * Returns true if data is removed
     * 
     * @param data
     *            needed to be removed
     * @return true if data was removed
     */
    public boolean remove(FreeBlock data) {
        Node curr = head.getNext();
        while (!curr.equals(tail)) {
            if (curr.getData().equals(data)) {
                curr.getPrevious().setNext(curr.getNext());
                curr.getNext().setPrevious(curr.previous);
                size--;
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * This method updates the free blocks when there's a block with size 0
     */
    private void clearEmpties() {
        Node temp = head;
        while (temp.next != null && temp.getNext().
                getData() != null && size > 1) {
            if (temp.next.getData().getSize() == 0) {
                temp.next = temp.next.next;
                temp.next.previous = temp;
                size--;
            } 
            else {
                temp = temp.next;
            }
        }

    }

    /**
     * To string method for free block list
     * 
     * @return the string for the free blocks
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        clearEmpties();
        Node curr = head.getNext();
        for (int i = 0; i < size; i++) {
           // if (curr.getData() != null) {
            string.append(curr.getData().toString());
            if (i + 1 != size) {
                string.append(" -> ");
            }
            //}
            
            curr = curr.getNext();
            
        }
        return string.toString();
    }

    /**
     * Merges blocks after resize/remove in memory manager
     * 
     * @param block
     *            needed to be merged
     * 
     * @param index
     *            of block needed to be merged
     */
    public void merge(FreeBlock block, int index) {
        Node currNode = getNodeAt(index);
        if (index == size) {
            currNode = currNode.getPrevious();
            int prevPos = currNode.getPrevious().getData().getLast();
            if (prevPos == currNode.getData().getPos()) {
                currNode.getData().setSize(currNode.
                        getPrevious().getData().getSize() + block.getSize());
                currNode.getData().setPos(currNode.
                        getPrevious().getData().getPos());
                remove(currNode.getPrevious().getData());
            }
        }

        else if (index == 0) {
            int lastpos = currNode.getData().getLast();

            if (currNode.getNext().getData().getPos() == lastpos) {
                currNode.getData().setSize(currNode.
                        getNext().getData().getSize() + block.getSize());
                remove(currNode.getNext().getData());
            }
        } 
        else {
            if (index > 0) {
                
                int prevPos2 = currNode.getPrevious().getData().getLast();
                if (prevPos2 == currNode.getData().getPos()) {
                    currNode.getData().setSize(currNode.
                            getPrevious().getData().getSize() + 
                            block.getSize());
                    currNode.getData().setPos(currNode.
                            getPrevious().getData().getPos());
                    remove(currNode.getPrevious().getData());
                }
            }
            if (index < size) {
                if (currNode == null)
                {
                    return;
                }
                int nextpos = currNode.getData().getLast();
                if (currNode.getNext().getData().getPos() == nextpos) {

                    currNode.getData().setSize(currNode.
                            getNext().getData().getSize() + block.getSize());
                    remove(currNode.getNext().getData());
                }
            }

        }
    }

    /**
     * Finds an index where fb can be inserted
     * 
     * @param fb
     *            block needed to be inserted
     * @return index of where fb needs to be inserted
     */
    public int insertAt(FreeBlock fb) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            FreeBlock newFb = get(i);
            if (fb.getPos() < newFb.getPos() && index == -1) {
                index = i;           
            }

        }
        if (index == -1) {
            index = size;
        }

        return index;
    }

    /**
     * Updates the pool size used in bestFit
     * 
     * @param currentSize
     *            the current size of memory manager
     */
    public void upDatePool(int currentSize) {
        poolSize = currentSize;
    }

    /**
     * Private node class for free block list
     * 
     * @author Enrique Prieto
     * @author Nathan Mussie
     * 
     */
    private static class Node {
        private Node next;
        private Node previous;
        private FreeBlock data;

        /**
         * Node constructor class
         * 
         * @param data
         *            block held by node
         */
        public Node(FreeBlock data) {
            setData(data);
        }

        /**
         * Returns next node
         * 
         * @return next node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Sets next node
         * 
         * @param next
         *            sets previous node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Gets previous node
         * 
         * @return previous node
         */
        public Node getPrevious() {
            return previous;
        }

        /**
         * Sets the previous node
         * 
         * @param previous
         *            node that sets the previous node
         */
        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        /**
         * Returns a free block
         * 
         * @return free block
         */
        public FreeBlock getData() {
            return data;
        }

        /**
         * Sets a free block
         * 
         * @param data
         *            of free block being set
         */
        public void setData(FreeBlock data) {
            this.data = data;
        }
    }

}
