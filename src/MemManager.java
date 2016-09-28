import java.nio.ByteBuffer;

/**
 * Class containing the memory manager
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
public class MemManager {

    private byte[] memPool;
    private FBList freeBlocks;
    private int currentSize;
    private int poolSize;
    private int numEntries;
    private boolean isResize;

    /**
     * Constructor of memory pool
     * 
     * @param poolSize
     *            size of memory pool
     */
    public MemManager(int poolSize) {
        this.poolSize = poolSize;
        currentSize = poolSize;
        freeBlocks = new FBList(currentSize);
        FreeBlock fb = new FreeBlock(0, poolSize);
        freeBlocks.add(fb, 0);
        numEntries = 0;
        memPool = new byte[poolSize];
        isResize = false;
    }

    /**
     * Getter for the memPool field
     * 
     * @return memory pool
     */
    public byte[] getMemPool() {
        return memPool;
    }

    /**
     * Inserts a record and return its position handle
     * 
     * @param space
     *            array with record to be inserted
     * @return position handle
     */
    public Handle insert(byte[] space) {
        freeBlocks.upDatePool(currentSize);
        isResize = false;
        int index = freeBlocks.bestFit(space.length + 2);
        while (index == -1) {
            memPool = resize();
            index = freeBlocks.bestFit(space.length + 2);
        }
        Handle handle = new Handle(index);
        byte[] arr = new byte[2];
        ByteBuffer buff = ByteBuffer.wrap(arr);
        buff.putShort(0, (short) space.length);
        System.arraycopy(buff.array(), 0, memPool, index, 2);
        index += 2;
        System.arraycopy(space, 0, memPool, index, space.length);
        numEntries++;
        return handle;
    }
    
    /**
     * Getter for free blocks list
     * @return list of free blocks
     */
    public FBList getList() {
        return freeBlocks;
    }

    /**
     * Resizes memory pool when needed
     * 
     * @return resized memory pool
     */
    private byte[] resize() {
        byte[] temp = new byte[currentSize];
        System.arraycopy(memPool, 0, temp, 0, currentSize);
        currentSize += poolSize;
        memPool = new byte[currentSize];
        System.arraycopy(temp, 0, memPool, 0, temp.length);
        FreeBlock newBlock = new FreeBlock(temp.length, poolSize);
        freeBlocks.add(newBlock);
        
        freeBlocks.merge(newBlock, freeBlocks.getSize());
        isResize = true;
        System.out.println("Memory pool expanded to be " 
                + currentSize + " bytes.");
       
        return memPool;
    }

    /**
     * Returns the string referenced from handle for one instance
     * 
     * @param handle
     *            referenced to memory pool
     * @return the number of bytes actually copied into space
     */
    public byte[] get(Handle handle) {
        ByteBuffer buff = ByteBuffer.wrap(memPool, handle.getRef(), 2);
        int len = buff.getShort();
        byte[] arr = new byte[len];
        // int count = 0;
        int poolCount = handle.getRef() + 2;
        for (int i = 0; i < len; i++) {
            arr[i] = memPool[poolCount];
            poolCount++;
        }
        return arr;
       /* String list = new String(arr);
        return list;*/
    }

    /**
     * Printout of freeblock list
     * @return free blocks list
     */
    public String dump() {
        return freeBlocks.toString();
    }

    /**
     * Getter for number of entries
     * 
     * @return number of entries
     */
    public int getNumEntries() {
        return numEntries;
    }

    /**
     * Checks if resize is needed in memory manager
     * 
     * @return true if a resize is needed
     */
    public boolean isResize() {
        return isResize;
    }

    /**
     * Returns the current size of memory manager
     * 
     * @return current size of memory manager
     */
    public int getCurrentSize() {
        return currentSize;
    }

    /**
     * Removes a handle from memory pool
     * 
     * @param handle
     *            needed to be removed
     */
    public void remove(Handle handle) {
        int index = handle.getRef();
        ByteBuffer buff = ByteBuffer.wrap(memPool, index, 2);
        int len = buff.getShort();
        len += 2;
        for (int i = 0; i < len; i++) {
            memPool[index] = 0;
            index++;
        }
        numEntries--;
        FreeBlock fb = new FreeBlock(handle.getRef(), len);
        int i = freeBlocks.insertAt(fb);
        freeBlocks.add(fb, i);
        freeBlocks.merge(fb, i);
    }

}