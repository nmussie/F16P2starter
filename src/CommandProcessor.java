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
        Handle artistHandle = artist.getHandle(strings[0]);
        // for artists
        if (artistH) {
            System.out.println("|" + strings[0] + "| " 
                        + "is added to the artist database.");
        } 
        else {
            System.out.println("|" + strings[0] + "| " 
                    + "duplicates a record already in the artist database.");
        }
        boolean songH = song.insert(strings[1]);
        Handle songHandle = song.getHandle(strings[1]);
        KVPair pairArt = new KVPair(artistHandle, songHandle);
        KVPair pairSong = new KVPair(songHandle, artistHandle);
        // for songs
        if (songH) {
            System.out.println("|" + strings[1] + "| " 
                        + "is added to the song database.");
        } 
        else {
            System.out.println("|" + strings[1] + "| " 
                    + "duplicates a record already in the song database.");
        }  
        
        //for KVPairs
        if (tree.insert(pairArt) != null) {
            System.out.println("The KVPair (|" + strings[0] 
                    + "|,|" + strings[1] + "|)," +
                    "(" + artistHandle.getRef() + "," 
                    + songHandle.getRef() + ")" + 
                    " is added to the tree.");
        }
        else {
            System.out.println("The KVPair (|" + strings[0] 
                    + "|,|" + strings[1] + "|)," +
                    "(" + artistHandle.getRef() + "," + 
                    songHandle.getRef() + ")" 
                    + " duplicates a record already in the tree.");
        }
        if (tree.insert(pairSong) != null) {
            System.out.println("The KVPair (|" + strings[1] 
                    + "|,|" + strings[0] + "|)," +
                    "(" + songHandle.getRef() + "," + 
                    artistHandle.getRef() + ")" + 
                    " is added to the tree.");
        }
        else {
            System.out.println("The KVPair (|" + strings[1] 
                    + "|,|" + strings[0] + "|)," +
                    "(" + songHandle.getRef() + "," + 
                    artistHandle.getRef() + ")" + 
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
        String value = in.substring(index + 1);
        if (command2.equals("artist")) {
            Handle handleA = artist.getHandle(value);
            Handle [] handleList = tree.list(handleA);
            String artistList = song.handlesToString(handleList);
            if (artistList == null)
            {
                System.out.println( "|" + value + "| " + 
                        "does not exist in the artist database.");
            }
            else {
                System.out.println(artistList);
            }
        }
        else {
            Handle handleS = song.getHandle(value);
            Handle [] handleList = tree.list(handleS);
            String songList = artist.handlesToString(handleList);
            if (songList == null)
            {
                System.out.println( "|" + value + "| " + 
                        "does not exist in the song database.");
            }
            else {
                System.out.println(songList);
            }
        }
    }
    
    /**
     * 
     * @param value
     * @param name
     * @return
     */
    private String buildRemoveString(String value, String name) {
        if (name == "artist" && tree.list(artist.getHandle(value)) != null) {
            Handle[] artistHandles = tree.list(artist.getHandle(value));
            String[] artistOut = new String[artistHandles.length];
            String artistStr = "";
            for (int i = 0; i < artistHandles.length; i++) {
                artistOut[i] = song.get(mem.get(artistHandles[i])); 
            }
            for (int j = 0; j < artistOut.length; j++) {
                artistStr += "The KVPair (|" + value 
                        + "|,|" + artistOut[j] + "|)" +
                        " is deleted to the tree.";
            }
            return artistStr;
        }
        if (name == "song" && tree.list(song.getHandle(value)) != null) {
            Handle[] songHandles = tree.list(song.getHandle(value));
            String[] songOut = new String[songHandles.length];
            String songStr = "";
            for (int i = 0; i < songHandles.length; i++) {
                songOut[i] = artist.get(mem.get(songHandles[i])); 
            }
            for (int j = 0; j < songOut.length; j++) {
                songStr += "The KVPair (|" + value 
                        + "|,|" + songOut[j] + "|)" +
                        " is deleted to the tree.";
            }
            return songStr;
        }
        return null;
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
            String artistOut = buildRemoveString(value, "artist");
            Handle artistH = artist.getHandle(value);
            if (artistOut == null) {
                System.out.println("|" + value + "| " 
                        + "does not exist in the artist database.");
            }
            else if (artist.remove(value)) {
                System.out.println("|" + value + "| " 
                        + "is removed from the artist database.");
                tree.remove(artistH);
                System.out.println(artistOut);
            } 
        } 
        else {
            String songOut = buildRemoveString(value, "song");
            Handle songH = song.getHandle(value);
            if (songOut == null) {
                System.out.println("|" + value + "| " 
                        + "does not exist in the song database.");
            }
            else if (song.remove(value)) {
                System.out.println("|" + value + "| " 
                        + "is removed from the song database.");
                tree.remove(songH);
                System.out.println(songOut);
            } 
        }
    }
    
    /**
     * Prints and executes the delete command
     * @param in String input for delete
     */
    public void delete(String in) {
        String[] strings = in.split("<SEP>");
        Handle artistHandle = artist.getHandle(strings[0]);
        Handle songHandle = song.getHandle(strings[1]);
        KVPair pairArt = new KVPair(artistHandle, songHandle);
        KVPair pairSong = new KVPair(songHandle, artistHandle);
        boolean isThere = artistHandle != null && songHandle != null;
        if (isThere && tree.delete(pairArt)) {
            System.out.println("The KVPair (|" + strings[0] 
                    + "|,|" + strings[1] + "|)" +
                    " is deleted to the tree.");
            if (!tree.contains(artistHandle)) {
                remove("artist "  + strings[0]);
            }
        }
        
        else {
            System.out.println("|" + strings[0] + "| " 
                    + "does not exist in the artist database.");
        }
        
        if (isThere && tree.delete(pairSong)) {
            System.out.println("The KVPair (|" + strings[1] 
                    + "|,|" + strings[0] + "|)" +
                    " is deleted to the tree.");
            if (!tree.contains(songHandle)) {
                remove("song " + strings[1]);
            }
        }
        else {
            System.out.println("|" + strings[1] + "| " 
                    + "does not exist in the song database.");
        }
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
