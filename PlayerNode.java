// Original Author:
// CSE 143, Winter 2010, Marty Stepp


// Modified by:
// Ryan Parsons
// CS 145 Assignment 2 - Tag

// Instructor-provided support class.  You should not modify this code!

/** Each PlayerNode object represents a single node in a linked list
    for a game of tag. */
public class PlayerNode {
    public String name;        // this person's name
    public String tagger;      // name of who killed this person (null if alive)
    public PlayerNode next;  // next node in the list (null if none)
    
    /** Constructs a new node to store the given name and no next node. */
    public PlayerNode(String name) {
        this(name, null);
    }

    /** Constructs a new node to store the given name and a reference
        to the given next node. */
    public PlayerNode(String name, PlayerNode next) {
        this.name = name;
        this.tagger = null;
        this.next = next;
        constructorCount++;
    }
    
    
    // code below this point is for CSE 143 grading script use only
    private static int constructorCount = 0;
    
    public static void resetCount() {
        constructorCount = 0;
    }

    public static int getCount() {
        return constructorCount;
    }
}

