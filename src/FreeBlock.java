/**
 * Class containing the free blocks
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
public class FreeBlock {

    private int pos;
    private int size;

    /**
     * Constructor of free block class
     * 
     * @param pos
     *            of free block
     * @param size
     *            of free block
     */
    public FreeBlock(int pos, int size) {
        this.setPos(pos);
        this.setSize(size);
    }

    /**
     * Gets the postion for free block
     * 
     * @return position
     */
    public int getPos() {
        return pos;
    }

    /**
     * Sets the position for a free block
     * 
     * @param pos
     *            that sets free block pos
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Gets the size of free block
     * 
     * @return size of free block
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of a free block
     * 
     * @param size
     *            that sets free block's size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Strings together a free block's pos and size
     * @return string of a free block's pos and size
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("(" + pos + "," + size + ")");
        return string.toString();
    }

    /**
     * Equals method for free blocks
     * 
     * @param obj
     *            that you are comparing the block to
     * @return true if object equals handle
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(this.getClass())) {
            FreeBlock newObj = (FreeBlock) obj;
            return getPos() == newObj.getPos() && getSize() == newObj.getSize();
        }
        return false;
    }

    /**
     * Gets the last instance of the free block
     * 
     * @return pos added to the size
     */
    public int getLast() {
        return pos + size;
    }

}
