import student.TestCase;

/**
 * Test the Handle class.
 * 
 * @author CS3114 Instructor and TAs
 * @version 9/16/2016
 */

public class HandleTest extends TestCase {

    private Handle handle;
    private Handle errorHandle;
    private Handle dupHandle;
    
    /**
     * Sets up every test case
     */
    public void setUp() {
        handle = new Handle(10);
        errorHandle = new Handle(-1);
        dupHandle = new Handle(10);
    }
    
    /**
     * Test the Handle class.
     */
    public void testH() {
        Handle myHandle = new Handle(1);
        Handle lessHandle = new Handle(2);
        Handle sameHandle = new Handle(1);
        Handle moreHandle = new Handle(0);
        assertEquals(myHandle.compareTo(lessHandle), -1);
        assertEquals(myHandle.compareTo(sameHandle), 0);
        assertEquals(myHandle.compareTo(moreHandle), 1);
        assertEquals(myHandle.getRef(), 1);
        assertEquals(myHandle.toString(), "1");
    }
    
    /**
     * Tests equals method
     */
    public void testEquals() {
        assertEquals(10, handle.getRef());
        assertFalse(handle.equals(errorHandle));
        assertTrue(handle.equals(dupHandle));
        handle = null;
        assertFalse(dupHandle.equals(handle));
        dupHandle.setRef(7);
        assertEquals(7, dupHandle.getRef());
        assertFalse(dupHandle.equals("handle"));
    }
}