
import student.TestCase;

public class TTTTest extends TestCase {

    private TTT tree;
    private KVPair pair;
    private Handle key;
    private Handle value;
    
    /**
     * sets up every test case
     */
    public void setUp() {
        tree = new TTT();
        key = new Handle(1);
        value = new Handle(2);
        pair = new KVPair(key, value);
    }

    /**
     * tests the insert method
     */
    public void testInsert() {
        assertTrue(tree.insert(pair));
        
    }

}