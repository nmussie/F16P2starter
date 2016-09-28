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
    //private Handle key;
    //private Handle value;

    /**
     * sets up every test case
     */
    public void setUp() {
        tree = new TTT();
        Handle key = new Handle(1);
        Handle value = new Handle(2);
        pair = new KVPair(key, value);
    }

    /**
     * tests the insert method
     */
    public void testInsert() {
        assertTrue(tree.insert(pair));

    }

}