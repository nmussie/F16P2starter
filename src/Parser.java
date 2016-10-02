import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the parser
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

public class Parser {

    private Scanner scanner;
    private CommandProcessor commandPro;

    /**
     * This method is parsing the command line by line and executes the
     * instructions given to it.
     * 
     * @param hSize
     *            of the hash table's parameter
     * @param bSize
     *            size of free blocks
     * @param file
     *            from the string of the command file
     * @throws FileNotFoundException
     */
    public Parser(int hSize, int bSize, String file) 
            throws FileNotFoundException {
        commandPro = new CommandProcessor(hSize, bSize);
        File fp = new File(file);
        scanner = new Scanner(fp);
        commands();
    }

    /**
     * the first word of each line in the input file is a command and So what
     * this method does is execute command.
     */
    private void commands() {
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            String input = scanner.nextLine().trim();
            if (command.equals("insert")) {
                commandPro.insert(input);
            } 
            else if (command.equals("remove")) {
                commandPro.remove(input);
            } 
            else if (command.equals("list")) {
                commandPro.list(input);
            }
            else if (command.equals("delete")) {
                commandPro.delete(input);
            }
            else {
                print(commandPro.toString(input));
            }
        }
    }

    /**
     * prints the string it is given
     * 
     * @param in
     *            the string that we want to print
     * @return returns true when we want to print.
     */
    public boolean print(String in) {
        System.out.println(in);
        return true;
    }

}
