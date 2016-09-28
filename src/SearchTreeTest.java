//import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class SearchTreeTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    private String[] arg;

    /**
     * sets up the test class
     */
    public void setUp() {
        // Nothing Here
        arg = new String[3];
        arg[0] = "10";
        arg[1] = "32";
        arg[2] = "P2_Input1_Sample.txt";
    }

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }

    /**
     * Get code coverage of the class declaration.
     * @throws IOException 
     */
    public void testMInit() throws IOException {
        SearchTree mem = new SearchTree();
        assertNotNull(mem);
        String outPut = readFile("P2_Output1_Sample.txt");
        SearchTree.main(arg);
        //assertEquals(outPut, systemOut().getHistory());
    }
}
