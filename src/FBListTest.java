import student.TestCase;
/**
 * Test class for the free block list
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
public class FBListTest extends TestCase {

    private FBList list;
    private FreeBlock block;
    private FreeBlock block1;
    private FreeBlock block2;
    private FreeBlock block3;
    
    /**
     * Sets up every test case
     */
    public void setUp() {
        list = new FBList(32);
        block = new FreeBlock(0, 32);
        block1 = new FreeBlock(32, 32);
        block2 = new FreeBlock(64, 32);
        block3 = new FreeBlock(96, 32);
    }

    /**
     * tests the add method
     */
    public void testAdd() {
        assertTrue(list.add(block, 0));
        assertEquals(1, list.getSize());
        block = null;
        assertNull(block);
        assertFalse(list.add(block));
        assertEquals(1, list.getSize());
        assertTrue(list.add(block1, 1));
        assertEquals(2, list.getSize());
        
    }
    /**
     * tests the get method
     */
    public void testGet()
    {
        list.add(block, 0);
        assertEquals(block, list.get(0));
        assertNull(list.get(-1));
        assertNull(list.get(10));
        assertTrue(list.add(block1, 1));
        assertTrue(list.add(block2, 2));
        assertTrue(list.add(block3, 3));
        assertFalse(list.add(block3, 6));
        assertFalse(list.add(block3, -1));

        assertEquals(block1, list.get(1));
        assertEquals(block2, list.get(2));
        assertEquals(block3, list.get(3));

    }
    
    /**
     * tests the get size method
     */
    public void testGetSize()
    {
        assertEquals(0, list.getSize());
        list.add(block, 0);
        assertEquals(1, list.getSize());
    }
    
    /**
     * tests the best fit method
     */
    public void testBestFit()
    {
        list.add(block, 0);
        list.add(block1, 1);
        list.add(block2, 2);
        list.add(block3, 3);
        int index = list.bestFit(16);
        
        assertEquals(0, index);
        assertEquals(16, list.get(0).getPos());
        index = list.bestFit(26);
        assertEquals(32, index);
        assertEquals(6, list.get(1).getSize());
        assertEquals(58, list.get(1).getPos());
    }
    
    /**
     * tests the remove method
     */
    public void testRemove()
    {
        list.add(block, 0);
        list.add(block1, 1);
        assertEquals(2, list.getSize());
        //assertEquals(0, list.getSize());
        list.remove(block);
        assertEquals(1, list.getSize());
        assertEquals(block1, list.get(0));
        assertFalse(list.remove(list.get(1)));
    }
    
    /**
     * tests the toString method
     */
    public void testToString()
    {
        assertEquals("", list.toString());
        list.add(block, 0);
        list.add(block1, 1);
        list.add(block2, 2);
        list.add(block3, 3);
        assertEquals("(0,32) -> (32,32) -> (64,32) -> (96,32)",               
            list.toString());
        list.remove(block);
        System.out.println(list.toString());

        list.remove(block1);
        System.out.println(list.toString());

        list.remove(block2);
        System.out.println(list.toString());

        list.get(0).setPos(list.get(0).getSize());
        list.get(0).setSize(0);
        System.out.println(list.toString());
        assertEquals("(32,0)", list.toString());
        
        
        
    }
    /**
     * test the merge method
     */
    public void testMerge()
    {
        list.add(block, 0);
        list.add(block1, 1);
        list.add(block2, 2);
        list.add(block3, 3);
        list.merge(block, 0);
        assertEquals("(0,64) -> (64,32) -> (96,32)", list.toString());
        list.merge(block3, 2);
        assertEquals("(0,64) -> (64,64)", list.toString());
        FreeBlock notmerge = new FreeBlock(300, 9);
        list.add(notmerge);
        list.merge(notmerge, 3);
        assertEquals("(0,64) -> (64,64) -> (300,9)", list.toString());
        list.merge(block3, 1);
        assertEquals("(0,128) -> (300,9)", list.toString());
        list.merge(block, -1);
        assertEquals("(0,128) -> (300,9)", list.toString());
        
    }
    /**
     * test the insertAt method
     */
    public void testInsertAt()
    {
        list.add(block1, 0);
        list.add(block2, 1);
        assertEquals(list.insertAt(block), 0);
        assertEquals(list.insertAt(block3), 2);
        list.add(block3);
        assertEquals(list.insertAt(block3), 3);
        
    }

}