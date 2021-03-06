import student.TestCase;

/**
 * Leaf node test class
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
public class LeafNodeTest extends TestCase {

    private LeafNode node;
    private KVPair pair;
    private KVPair bigPair;
    private KVPair coolPair;

    /**
     * Sets up every test case
     */
    public void setUp() {
        Handle coolHandle = new Handle(1);
        Handle key = new Handle(3);
        Handle val = new Handle(4);
        pair = new KVPair(key, val);
        bigPair = new KVPair(val, key);
        node = new LeafNode();
        coolPair = new KVPair(coolHandle, val);
    }

    /**
     * Tests methods in LeafNode
     */
    public void testStuff() {
        assertFalse(node.isFull());
        assertFalse(node.onlyFirstNode());
        node.insert(bigPair);
        assertTrue(node.onlyFirstNode());
        node.insert(pair);
        assertTrue(node.isFull());
        node.setFirstPair(null);
        node.insert(pair);
        assertEquals(pair, node.getFirstPair());
        node.insert(coolPair);
        assertEquals(pair, node.getFirstPair());
    }

    /**
     * Tests is empty
     */
    public void testIsEmpty() {
        assertTrue(node.isEmpty());
        node.insert(pair);
        assertFalse(node.isEmpty());
    }
   
}
