import student.TestCase;

/**
 * Test class for free blocks
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
public class FreeBlockTest extends TestCase {
    private FreeBlock fb;

    /**
     * Sets up every test case
     */
    public void setUp() {
        fb = new FreeBlock(0, 3);
    }

    /**
     * Tests getter for position of free block
     */
    public void testGetPos() {
        assertEquals(0, fb.getPos());
    }

    /**
     * Tests the set position method
     */
    public void testSetPos() {
        assertEquals(0, fb.getPos());
        fb.setPos(7);
        assertEquals(7, fb.getPos());
    }

    /**
     * Tests another get position
     */
    public void testGetPos2() {
        assertEquals(3, fb.getSize());
    }

    /**
     * Tests set size method
     */
    public void testSetSize() {
        assertEquals(3, fb.getSize());
        fb.setSize(7);
        assertEquals(7, fb.getSize());
    }

    /**
     * Tests get size method
     */
    public void testGetSize() {
        assertEquals(3, fb.getSize());
        fb.setSize(7);
        assertEquals(7, fb.getSize());
        assertEquals(7, fb.getSize());
        fb.setPos(7);
        assertEquals(7, fb.getPos());
    }

    /**
     * Tests to string method
     */
    public void testToString() {
        assertEquals("(0,3)", fb.toString());
    }

    /**
     * Tests equals method
     */
    public void testEquals() {
        FreeBlock e = new FreeBlock(0, 3);
        assertTrue(fb.equals(e));
        FreeBlock q = new FreeBlock(0, 5);
        assertFalse(fb.equals(q));
        assertFalse(fb.equals("fb"));
        q = null;
        assertFalse(fb.equals(q));
    }

}