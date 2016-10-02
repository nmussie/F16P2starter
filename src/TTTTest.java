import student.TestCase;

/**
 * Test class for the 2-3+ tree
 *
 * @author CS3114 staff
 * @author Nathan Mussie
 * @author Enrique Prieto
 * @version 9/2/2016
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
public class TTTTest extends TestCase {

    private TTT tree;
    private KVPair pair;
    private KVPair anotherPair;
    private KVPair coolPair;
    private Handle key;
    private Handle value;
    private Handle anotherKey;
    private Handle anotherVal;
    private Handle coolKey;
    private Handle coolVal;
    private Handle bigKey;
    private Handle bigVal;
    private Handle bigGuy;

    /**
     * sets up every test case
     */
    public void setUp() {
        tree = new TTT();
        key = new Handle(1);
        value = new Handle(2);
        anotherKey = new Handle(7);
        anotherVal = new Handle(9);
        coolKey = new Handle(6);
        coolVal = new Handle(25);
        bigKey = new Handle(34);
        bigVal = new Handle(23);
        anotherPair = new KVPair(anotherKey, anotherVal);
        coolPair = new KVPair(coolKey, coolVal);
        pair = new KVPair(key, value);
        bigGuy = new Handle(60);
    }

    /**
     * tests the insert method
     */
    public void testOneInsert() {
        assertEquals("Printing 2-3 tree:", tree.toString());
        assertNull(tree.getRoot());
        tree.insert(pair);
        assertEquals("Printing 2-3 tree:\n1 2", tree.toString());
        assertTrue(tree.getRoot().getClass() == LeafNode.class);
    }

    /**
     * 
     */
    public void testDuplicateInsert() {
        tree.insert(pair);
        assertNull(tree.insert(pair));
    }
    
    /**
     * 
     */
    public void testMultipleInsert() {
        assertEquals("Printing 2-3 tree:", tree.toString());
        tree.insert(pair);
        tree.insert(anotherPair);
        tree.insert(coolPair);
        KVPair morePairs = new KVPair(key, anotherVal);
        tree.insert(morePairs);
        morePairs = new KVPair(anotherKey, value);
        tree.insert(morePairs);
        morePairs = new KVPair(coolKey, anotherVal);
        tree.insert(morePairs);
        morePairs = new KVPair(value, coolKey);
        tree.insert(morePairs);
        morePairs = new KVPair(anotherVal, coolVal);
        tree.insert(morePairs);
        morePairs = new KVPair(coolVal, anotherKey);
        tree.insert(morePairs);
        morePairs = new KVPair(bigKey, bigVal);
        tree.insert(morePairs);
        morePairs = new KVPair(bigVal, bigKey);
        tree.insert(morePairs);
        morePairs = new KVPair(bigKey, key);
        tree.insert(morePairs);
        morePairs = new KVPair(bigVal, coolKey);
        tree.insert(morePairs);
        morePairs = new KVPair(bigGuy, bigKey);
        tree.insert(morePairs);
        morePairs = new KVPair(bigGuy, bigVal);
        tree.insert(morePairs);
        assertTrue(tree.getRoot().getClass() == InternalNode.class);
        assertEquals(
        "Printing 2-3 tree:" + 
        "\n7 9" + 
        "\n  6 25" + 
        "\n    1 9 2 6" + 
        "\n      1 2" +
        "\n      1 9" + 
        "\n      2 6 6 9" +
        "\n    7 2" +
        "\n      6 25" +
        "\n      7 2" +
        "\n  25 7 34 23" +
        "\n    9 25 23 6" +
        "\n      7 9" +
        "\n      9 25" +
        "\n      23 6 23 34" +
        "\n    34 1" +
        "\n      25 7" +
        "\n      34 1" +
        "\n    60 23" +
        "\n      34 23" +
        "\n      60 23 60 34", tree.toString());
    }
    
    
    
    
    
}