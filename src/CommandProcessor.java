/**
 * Class containing the command processor
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

public class CommandProcessor {

    private HashTable artist;
    private HashTable song;
    private MemManager mem;
    private TTT tree;

    /**
     * This is the constructor of the command processor Initializes two hash
     * tables (song and artist)
     * 
     * @param hSize
     *            of the initial hash table
     * @param bSize
     *            is the initial block size
     */
    public CommandProcessor(int hSize, int bSize) {
        mem = new MemManager(bSize);
        artist = new HashTable("Artist", hSize, mem);
        song = new HashTable("Song", hSize, mem);
        tree = new TTT();
    }

    /**
     * This method uses print statements to insert songs and artists
     * 
     * @param in
     *            is the string input used
     */
    public void insert(String in) {
        // separate songs from artists
        String[] strings = in.split("<SEP>");
        boolean artistH = artist.insert(strings[0]);
        Handle artistHandle = artist.getHandle();
        boolean insertA = false;
        boolean insertS = false;
        // for artists
        if (artistH) {
            System.out.println("|" + strings[0] + "| " 
                        + "is added to the artist database.");
            insertA = true;
        } 
        else {
            System.out.println("|" + strings[0] + "| " 
                    + "duplicates a record already in the artist database.");
        }
        boolean songH = song.insert(strings[1]);
        Handle songHandle = song.getHandle();
        KVPair pairArt = new KVPair(artistHandle, songHandle);
        KVPair pairSong = new KVPair(songHandle, artistHandle);
        // for songs
        if (songH) {
            System.out.println("|" + strings[1] + "| " 
                        + "is added to the song database.");
            insertS = true;
        } 
        else {
            System.out.println("|" + strings[1] + "| " 
                    + "duplicates a record already in the song database.");
        }  
        
        //for KVPairs
        if (insertA && tree.insert(pairArt)) {
            System.out.println("The KVPair (|" + strings[0] 
                    + "|,|" + strings[1] + "|)," +
                    "(" + artist.getHandle().getRef() + "," 
                    + song.getHandle().getRef() + ")" + 
                    " is added to the tree.");
        }
        else {
            System.out.println("The KVPair (|" + strings[0] 
                    + "|,|" + strings[1] + "|)," +
                    "(" + artist.getHandle().getRef() + "," + 
                    song.getHandle().getRef() + ")" 
                    + " duplicates a record already in the tree.");
        }
        if (insertS && tree.insert(pairSong)) {
            System.out.println("The KVPair (|" + strings[1] 
                    + "|,|" + strings[0] + "|)," +
                    "(" + song.getHandle().getRef() + "," + 
                    artist.getHandle().getRef() + ")" + 
                    " is added to the tree.");
        }
        else {
            System.out.println("The KVPair (|" + strings[1] 
                    + "|,|" + strings[0] + "|)," +
                    "(" + song.getHandle().getRef() + "," + 
                    artist.getHandle().getRef() + ")" + 
                    " duplicates a record already in the tree.");
        }
    }

    /**
     * Prints the list command
     * @param in string input for list
     */
    public void list(String in) {
        int index = in.indexOf(" ");
        String command2 = in.substring(0, index);
        //String value = in.substring(index + 1);
        if (command2.equals("artist")) {
            System.out.println("List TO-DO");
        }
        else {
            System.out.println("List TO-DO");
        }
    }
    
    /**
     * This method deals with print statements to remove artists and songs
     * 
     * @param in
     *            is the string input used
     */
    public void remove(String in) {
        int index = in.indexOf(" ");
        String command2 = in.substring(0, index);
        String value = in.substring(index + 1);

        if (command2.equals("artist")) {

            if (artist.remove(value)) {
                System.out.println("|" + value + "| " 
                        + "is removed from the artist database.");
            } 
            else {
                System.out.println("|" + value + "| " 
                        + "does not exist in the artist database.");
            }
        } 
        else {
            if (song.remove(value)) {
                System.out.println("|" + value + "| " 
                        + "is removed from the song database.");
            } 
            else {
                System.out.println("|" + value + "| " 
                        + "does not exist in the song database.");
            }
        }
    }
    
    /**
     * Prints and executes the delete command
     * @param in String input for delete
     */
    public void delete(String in) {
        //do nothing
    }

    /**
     * gets the artist Hashtable
     * 
     * @return the artist hashtable
     */
    public HashTable getArtist() {
        return artist;
    }

    /**
     * gets the song Hashtable
     * 
     * @return the song hashtable
     */
    public HashTable getSong() {
        return song;
    }

    /**
     * This method prints out the total songs and artists currently in the
     * tables
     * 
     * @param in
     *            is the string input used
     * @return a string of what we should be printing out
     */
    public String toString(String in) {
        if (in.equals("artist")) {
            return artist.toString() + "total artists: " 
                    + artist.getSize();
        } 
        else if (in.equals("song")) {
            return song.toString() + "total songs: " 
                    + song.getSize();
        } 
        else if (in.equals("blocks")) {
            return mem.dump();
        }
        else if (in.equals("tree")) {
            return tree.toString();
        }
        else {
            return "list tree";
        }
    }
}
