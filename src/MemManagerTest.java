import student.TestCase;

/**
 * Test Class for the MemManager class
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
public class MemManagerTest extends TestCase {

    private byte[] array;
    private MemManager mem;

    /**
     * Sets up every test case
     */
    public void setUp() {
        array = new byte[33];
        mem = new MemManager(32);
    }

    /**
     * Tests the insert method
     */
    public void testInsert() {
        array = mem.getMemPool();
        assertEquals(32, mem.getCurrentSize());
        assertEquals(array, mem.getMemPool());
        mem.insert(array);
        assertTrue(mem.isResize());
    }

    /**
     * Seconds test for insert method
     */
    public void testInsert2() {
        array = new byte[20];
        assertEquals(32, mem.getCurrentSize());
        assertEquals("(0,32)", mem.dump());
        mem.insert(array);
        assertEquals(32, mem.getCurrentSize());
        assertEquals(1, mem.getNumEntries());
        assertEquals("(22,10)", mem.dump());
        array = new byte[8];
        mem.insert(array);
        assertEquals("(32,0)", mem.dump());
        assertEquals(32, mem.getCurrentSize());
    }

}
