import student.TestCase;

/**
 * Internal node test class
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

public class InternalNodeTest extends TestCase {

    private InternalNode node;
    private InternalNode emptyNode;
    private KVPair first;
    private KVPair second;
    private Node child;

    /**
     * Sets up every test case
     */
    public void setUp() {
        Handle handle = new Handle(1);
        Handle handle2 = new Handle(2);
        Handle handle3 = new Handle(3);
        Handle handle4 = new Handle(4);
        first = new KVPair(handle, handle2);
        second = new KVPair(handle3, handle4);
        node = new InternalNode(first, second);
        emptyNode = new InternalNode();
    }

    /**
     * Tests all the methods in internal nodes
     */
    public void testStuff() {
        assertEquals(first, node.getFirstPair());
        assertEquals(second, node.getSecondPair());
        node.setLeftChild(child);
        assertEquals(child, node.getLeftChild());
        node.setRightChild(child);
        assertEquals(child, node.getRightChild());
        node.setMiddleChild(child);
        assertEquals(child, node.getMiddleChild());
        assertNull(emptyNode.getFirstPair());
        assertNull(emptyNode.getSecondPair());
    }

    /**
     * Tests methods in LeafNode
     */
    public void testOtherStuff() {
        assertFalse(emptyNode.isFull());
        assertFalse(emptyNode.onlyFirstNode());
        emptyNode.insert(second);
        assertTrue(emptyNode.onlyFirstNode());
        emptyNode.insert(first);
        assertTrue(emptyNode.isFull());
        emptyNode.setFirstPair(null);
        emptyNode.insert(first);
        assertEquals(first, emptyNode.getFirstPair());
    }

    /**
     * Tests get depth
     */
    public void testGetDepth() {
        assertEquals(0, node.getDepth(null));
    }

}
