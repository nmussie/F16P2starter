/**
 * Hash Table class, which holds methods for the Artist and Song Hash Tables
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
public class HashTable {

    // initial array size
    private int tableSize;
    // the current size of array
    private int currentSize;
    private Handle errorHandle;
    // lets us know if the array is doubled
    private boolean morespace;
    // tells us if it's an artist or song
    private String name;
    private Handle tempHandle;
    private MemManager mem;

    /**
     * String array for the hash tables
     */
    Handle[] hTable;

    /**
     * Create a new Hash object.
     * 
     * @param name
     *            either song or artist
     * 
     * @param size
     *            is the initial size of the table
     * 
     * @param mem
     *            is the memory manager used in the table
     */
    public HashTable(String name, int size, MemManager mem) {
        // collisions = 0;
        this.name = name;
        errorHandle = new Handle(-1);
        tableSize = size;
        hTable = new Handle[tableSize];
        currentSize = 0;
        for (int i = 0; i < tableSize; i++) {
            // ensures table is empty
            hTable[i] = null;
        }
        this.mem = mem;
        morespace = false;
        // tempHandle = new Handle(-2);
    }

    /**
     * Inserts a given value with the key given
     * 
     * @param value
     *            to be inserted
     * @return true if value is inserted into table
     */
    public boolean insert(String value) {
        if (value == null) {
            return false;
        }
        tempHandle = null;
        morespace = false;
        currentSize++;
        /* hTable = moreSpace(); */
        int key = getKey(value);
        if (contains(key, value)) {
            tempHandle = new Handle(hTable[key].getRef());
            currentSize--;
            return false;
        }
        // currentSize++;
        hTable = moreSpace();
        if (morespace) {
            key = getKey(value);
            if (contains(key, value)) {
                tempHandle = new Handle(hTable[key].getRef());
                currentSize--;
                return false;
            }
        }
        hTable[key] = mem.insert(value.getBytes());
        tempHandle = new Handle(hTable[key].getRef());
        return true;
    }

    /**
     * Gets the hash table size
     * 
     * @return the table size hash table
     */
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Getter for handle
     * 
     * @return current handle
     */
    public Handle getHandle() {
        return tempHandle;
    }

    /**
     * Gets the current size of the hash table
     * 
     * @return the size of the hash table
     */
    public int getSize() {
        return currentSize;
    }

    /**
     * Checks to see if table contains a value
     * 
     * @param value
     *            you want to check for
     * 
     * @return true if value is found in table
     */
    private boolean contains(int key, String value) {
        if (hTable[key] == null || hTable[key].equals(errorHandle)) {
            return false;
        }
        return value.equals(get(mem.get(hTable[key])));
    }

    /**
     * If the array is 50% full, then it doubles in size.
     */
    private Handle[] moreSpace() {
        int sizeDiv2 = tableSize / 2;
        Handle[] temp = new Handle[hTable.length];
        System.arraycopy(hTable, 0, temp, 0, hTable.length);
        if (getSize() > sizeDiv2) {
            tableSize = tableSize * 2; // new table size
            // new array with new size
            hTable = new Handle[tableSize];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null && !temp[i].equals(errorHandle)) {
                    // re-hash new array
                    int newkey = getKey(get(mem.get(temp[i])));
                    hTable[newkey] = temp[i];
                    morespace = true;
                }
            }
            System.out.println(name + " hash table size doubled.");
        }
        return hTable;
    }

    /**
     * the get method that takes in byte array and returns a string
     * 
     * @param arr
     *            byte array from the Memory manager
     * @return the string gotten from the memManager
     */
    public String get(byte[] arr) {
        return new String(arr);
    }

    /**
     * Removes a string from the array
     * 
     * @param value
     *            needed to be removed
     * 
     * @return true if value is removed from table
     */
    public boolean remove(String value) {
        if (currentSize == 0 || value == null) {
            return false;
        } 
        else {
            int key = getKey(value);
            if (hTable[key] != null && !hTable[key].equals(errorHandle)) {
                mem.remove(hTable[key]);
                hTable[key] = errorHandle;
                currentSize--;
                return true;
            } 
            else {
                return false;
            }
        }
    }

    /**
     * Checks to see if there's a collision and if so, it executes a quadratic
     * probing function.
     * 
     * @param key
     *            needed to be checked for probing
     * @param array
     *            the handle array that is being iterated through
     * @param value
     *            whose handle is going to be inserted in the handle array
     * 
     * @return key position after probing
     */
    public int quadProb(Handle[] array, int key, String value) {
        // Check if there's a collision
        // Square's i every iteration
        int i = 1;
        int baseIndex = key;
        int okey = -1;
        while (array[key] != null) {
            if (array[key].equals(errorHandle) && okey < 0) {
                okey = key;
            } 
            else if (!array[key].equals(errorHandle) && 
                    value.equals(get(mem.get(array[key])))) {
                return key;
            }
            key = (baseIndex + i * i++) % array.length;
        }
        if (okey >= 0) {
            key = okey;
        }
        return key;
    }

    /**
     * 
     * @param value
     *            the value that we need the key for
     * @return the key where either the value is already or where we can insert
     *         the value
     */
    public int getKey(String value) {
        int key = h(value, hTable.length);
        key = quadProb(hTable, key, value);
        return key;
    }

    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    private int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int) (Math.abs(sum) % m);
    }

    /**
     * this method gets the value of morespace
     * 
     * @return boolean checks when the array is doubled
     */
    public boolean isMorespace() {
        return morespace;
    }

    /**
     * this is the toString method makes this class into a readable String
     * 
     * @return the HashTable in String form
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < tableSize; i++) {
            if (hTable[i] != null && !hTable[i].equals(errorHandle)) {
                string.append("|");
                String check = get(mem.get(hTable[i]));
                string.append(check);

                string.append("| ");
                string.append(i);
                string.append("\n");
            }
        }
        return string.toString();
    }
}