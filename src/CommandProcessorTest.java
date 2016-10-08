import student.TestCase;

/**
 * This is the Command Processor test class
 *
 * @author CS3114 staff
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

public class CommandProcessorTest extends TestCase {

    private CommandProcessor commandPro;
    private String insert;
    private String remove;
    private String removeS;
    private String print;
    private String print2;
    private String printTree;
    /**
     * sets up the test fields
     */
    public void setUp() {
        commandPro = new CommandProcessor(10, 10);
        insert = "Blind Lemon Jefferson<SEP>Long Lonesome Blues";
        remove = "artist Blind Lemon Jefferson";
        removeS = "song Long Lonesome Blues";
        print = "song";
        print2 = "artist";
        printTree = "tree";
    }

    /**
     * Test the insert method
     */
    public void testInsert() {
        commandPro.insert(insert);
        assertEquals(1, commandPro.getArtist().getSize());
        assertEquals(1, commandPro.getSong().getSize());
        commandPro.insert(insert);
        assertEquals(1, commandPro.getArtist().getSize());
        assertEquals(1, commandPro.getSong().getSize());
        commandPro.list("artist Blind Lemon Jefferson");
        commandPro.toString(printTree);
        commandPro.delete(insert);
        commandPro.toString(printTree);
    }

    /**
     * Test the remove method
     */
    public void testRemove() {
        commandPro.insert(insert);
        assertEquals(1, commandPro.getArtist().getSize());
        commandPro.remove(remove);
        assertEquals(0, commandPro.getArtist().getSize());
        assertEquals(1, commandPro.getSong().getSize());
        commandPro.remove(removeS);
        assertEquals(0, commandPro.getSong().getSize());
    }
    
    /**
     * Tests the remove method 
     */
    public void testRemove2() {
        commandPro.insert(insert);
        assertEquals(1, commandPro.getSong().getSize());
        commandPro.remove(removeS);
        assertEquals(0, commandPro.getSong().getSize());
        assertEquals(1, commandPro.getArtist().getSize());
        commandPro.remove(remove);
        assertEquals(0, commandPro.getArtist().getSize());
    }

    /**
     * Tests the print method
     */
    public void testPrint() {
        commandPro.insert(insert);
        assertEquals("|Long Lonesome Blues| 5\n" + "total songs: 1", 
                    commandPro.toString(print));
        assertEquals("|Blind Lemon Jefferson| 0\n" + "total artists: 1", 
                    commandPro.toString(print2));
        assertEquals("Printing 2-3 tree:\n0 23 23 0", 
                commandPro.toString(printTree));
    }

}
