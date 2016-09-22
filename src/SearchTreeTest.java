import java.io.FileNotFoundException;

import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class SearchTreeTest extends TestCase {
    private SearchTree parser;

    /**
     * sets up the tests class
     */
    public void setUp() throws FileNotFoundException {
        parser = new SearchTree(10, 32, "P1_Input2_Sample.txt");
    }

    /**
     * this tests the method print
     */
    public void testPrint() {
        String string = "me";
        assertTrue(parser.print(string));
    }
}
