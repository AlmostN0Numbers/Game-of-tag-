// Original Author:
// CSE 143, Winter 2010, Marty Stepp


// Modified by:
// Ryan Parsons
// CS 145 Assignment 2 - Tag
// Instructor-provided testing program.

import java.io.*;
import java.util.*;

/** Class TagMain is the main client program for tag game management.
    It reads names from a file names.txt, shuffles them, and uses them to
    start the game.  The user is asked for the name of the next person until
    the game is over. */
public class TagMain {
    /** input file name from which to read data */
    public static final String INPUT_FILENAME = "names.txt";
    
    /** true for different results every run; false for predictable results */
    public static final boolean RANDOM = false;
    
    /** If not random, use this value to guide the sequence of numbers
        that will be generated by the Random object. */
    public static final int SEED = 42;


    public static void main(String[] args) throws FileNotFoundException {
        // read names into a Set to eliminate duplicates
        File inputFile = new File(INPUT_FILENAME);
        if (!inputFile.canRead()) {
            System.out.println("Required input file not found; exiting.\n" + inputFile.getAbsolutePath());
            System.exit(1);
        }
        Scanner input = new Scanner(inputFile);
        
        Set<String> names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        while (input.hasNextLine()) {
            String name = input.nextLine().trim().intern();
            if (name.length() > 0) {
                names.add(name);
            }
        }

        // transfer to an ArrayList, shuffle and build an TagManager
        List<String> nameList = new ArrayList<String>(names);
        Random rand = (RANDOM) ? new Random() : new Random(SEED);
        Collections.shuffle(nameList, rand);
        
        TagManager manager = new TagManager(nameList);

        // prompt the user for people until the game is over
        Scanner console = new Scanner(System.in);
        while (!manager.isGameOver()) {
            oneTag(console, manager);
        }

        // report who won
        System.out.println("Game was won by " + manager.winner());
        System.out.println("Final history is as follows:");
        manager.printHistory();
    }

    /** Handles the details of recording one person.  Shows the current game
        ring and history to the user, prompts for a name and records the
        tag if the name is legal. */
    public static void oneTag(Scanner console, TagManager manager) {
        // print both linked lists
        System.out.println("Current game ring:");
        manager.printGameRing();
        System.out.println("Current history:");
        manager.printHistory();
        
        // prompt for next person to tag
        System.out.println();
        System.out.print("next person? ");
        String name = console.nextLine().trim();
        
        // tag the person, if possible
        if (manager.historyContains(name)) {
            System.out.println(name + " is already out.");
        } else if (!manager.gameRingContains(name)) {
            System.out.println("Unknown person.");
        } else {
            manager.tag(name);
        }
        System.out.println();
    }
}
