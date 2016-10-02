import student.TestCase;

/**
 * Test the Hash Table class
 * 
 * @author Nathan Mussie
 * @version Aug 27, 2016
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

public class HashTableTest extends TestCase {

    private HashTable hashTable;
    private String socks;
    private String crazy;
    private String hola;
    private String turnt;
    private String respect;
    private String earring;
    private MemManager mem;

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        hola = "hola";
        mem = new MemManager(32);
        hashTable = new HashTable(hola, 10, mem);
        socks = "socks";
        crazy = "crazy";
        respect = "respect";
        turnt = "turnt";
        earring = "earring";
    }

    /**
     * Test the insert method
     */
    public void testInsert() {
        assertTrue(hashTable.insert(socks));
        assertTrue(hashTable.insert(crazy));
        assertFalse(hashTable.insert(crazy));
        assertTrue(hashTable.insert(respect));
        assertTrue(hashTable.remove(crazy));
        assertTrue(hashTable.insert(earring));
        
        assertFalse(hashTable.insert(socks));
        assertTrue(hashTable.insert(hola));
        
        assertTrue(hashTable.insert(crazy));
        
    }

    /**
     * Test the insert method
     */
    public void testInsert2() {
        assertTrue(hashTable.insert(socks));
        assertFalse(hashTable.insert(socks));
        crazy = null;
        assertFalse(hashTable.insert(crazy));
        assertTrue(hashTable.insert(hola));
        assertTrue(hashTable.insert(turnt));
    }

    /**
     * Tests print methods
     */
    public void testGetSize() {
        assertEquals(0, hashTable.getSize());
        hashTable.insert(socks);
        assertEquals(1, hashTable.getSize());
    }

    /**
     * Test the insert method
     */
    public void testInsert3() {
        assertEquals(10, hashTable.getTableSize());
        assertFalse(hashTable.isMorespace());
        assertTrue(hashTable.insert(socks));
        assertTrue(hashTable.insert(crazy));
        assertTrue(hashTable.insert(hola));
        assertTrue(hashTable.insert(respect));
        assertTrue(hashTable.insert(earring));
        assertTrue(hashTable.insert(turnt));
        assertEquals(20, hashTable.getTableSize());
        assertTrue(hashTable.isMorespace());
        
    }

    /**
     * Test the remove method
     */
    public void testRemove() {
        assertEquals(0, hashTable.getSize());
        hashTable.insert(turnt);
        assertFalse(hashTable.remove(respect));
        hashTable.insert(respect);
        assertTrue(hashTable.remove(respect));
        respect = null;
        assertNull(respect);
        assertFalse(hashTable.remove(respect));
        assertEquals(1, hashTable.getSize());
        assertTrue(hashTable.remove(turnt));
        assertEquals(0, hashTable.getSize());
    }

    /**
     * Tests contains method in hash table
     */
    public void testContains() {
        hashTable.insert(respect);
        assertFalse(hashTable.insert(respect));
        Handle handle = hashTable.getHandle();
        assertEquals(handle, hashTable.getHandle());
    }
    
    /**
     * Tests the isMorespace method
     */
    public void testIsMoreSpace() {
        assertFalse(hashTable.isMorespace());
    }

    /**
     * Test the to string method
     */
    public void testToString() {
        assertEquals("", hashTable.toString());
        hashTable.insert(crazy);
        hashTable.insert(turnt);
        assertEquals("|crazy| 8\n|turnt| 9\n", hashTable.toString());
    }
    /**
     * Test the quad probing method
     */
    public void testquadProb()
    {
        Handle[] array = new Handle[20];
        Handle handle1 = mem.insert(crazy.getBytes());
        Handle handle2 = mem.insert(turnt.getBytes());
        Handle handle3 = new Handle(-1);
        array[0] = handle1;
        array[1] = handle2;
        array[2] = handle3;
        array[3] = handle3;
        array[9] = handle3;
        array[8] = handle3;
        array[5] = handle3;
        int key = 0;    
        assertEquals(4, hashTable.quadProb(array, key, respect));
        array[4] = mem.insert(respect.getBytes());
        mem.insert(socks.getBytes());
        assertEquals(9, hashTable.quadProb(array, key, socks));
        mem.insert(earring.getBytes());
        int newKey = 4;
        assertEquals(5, hashTable.quadProb(array, newKey, earring));  
        
    }
    
}
