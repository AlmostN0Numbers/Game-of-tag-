//Name: Reid Moirn
//Class:	CS145
//Assignment: Project 2
//Purpose: Simualtes a random game of tag, using the support files provided.
import java.io.*;
import java.util.*;

public class TagManager {
   private LinkedList<PlayerNode> gameRing = new LinkedList<PlayerNode>();
   private LinkedList<PlayerNode> history = new LinkedList<PlayerNode>();
   //constructor, adds the entire list of names string into the game ring, 
   //throws and Illegal for the names file being empty or nonexistent
   public TagManager(List<String>names){
      if (names == null || names.size() == 0){
         throw new IllegalArgumentException();
         } 
      else {
         gameRing.add(new PlayerNode(names.get(0)));
         for (int i = 1; i <= names.size() - 1; i++) {
            gameRing.add(new PlayerNode(names.get(i), gameRing.getLast()));
         }
         gameRing.getFirst().next = gameRing.get(1);
      }
   }
   //prints out what has been passed to it by the constructor, throws an Illegal if the gameOver condition has been met
   public void printGameRing(){
      if (isGameOver() == true) {
         throw new IllegalArgumentException();
         }
      else {
         for (int i = 0; i <= gameRing.size() - 1; i++){
            if( i == gameRing.size() - 1){
               System.out.println(gameRing.getLast().name + " Is Trying To Tag " + gameRing.getFirst().name);
            }
            else {
               System.out.println(gameRing.get(i).name + " Is Trying To Tag " + gameRing.get(i + 1).name);
            }
         }
      }
   }
   //Prints the history of the gameRing by reading from a seperate instance of the Linked PlayerNode list 
   public void printHistory(){
      if (history.isEmpty()){
      }
      else {
         for(int i = history.size() - 1;  i >= 0; i--) {
            System.out.println(history.get(i).name + " was tagged by " + history.get(i).tagger);
         }
      }
   }
   //condition for removing a name from the game ring, will only do so if the name and user input match
   public boolean gameRingContains(String name){
      boolean result = false;
      for (PlayerNode player : gameRing) {
         if (player.name.equalsIgnoreCase(name)) {
            result = true;
            break;
         }
      }
      return result;
   }
   //same as the prior method, but for the game ring history
   public boolean historyContains(String name){
      boolean result = false;
      for (PlayerNode player : history) {
         if (player.name.equalsIgnoreCase(name)) {
            result = true;
            break;
         }
      }
      return result;
   }
   //conition that ends the game if the list is 1 or less
   public boolean isGameOver(){
      boolean result = false;
      if (gameRing.size() == 1){
         result = true;
      }
      return result;
   }
   //runs if the gamering has one person left
   public String winner(){
       if (isGameOver()) {
         return gameRing.getLast().name;
      }
      return null;
   }
   //method for tagging and removing a name, throws Illegal if the game is over, or if the name is not valid, or both.
   public void tag(String name){
      if (isGameOver() && gameRingContains(name)){
         throw new IllegalStateException();
      }
      else if (isGameOver()){
         throw new IllegalStateException();
      }
      else if (gameRingContains(name) == false) {
         throw new IllegalArgumentException();   
      }
      for (int i = 0; i <= gameRing.size() -1; i++) {
         PlayerNode player = gameRing.get(i);
         if (player.name.equalsIgnoreCase(name)) { //ignores casing for input to check
            if (i == gameRing.size() -1) {
               player.tagger = gameRing.get(i - 1).name; //for all names but the one at the end
            }
            else if (i == 0){
               player.tagger = gameRing.getLast().name;//for the final name in the gameRing
            }
            else {
               player.tagger = gameRing.get(i + 1).name;
            } 
            gameRing.remove(player);
            history.add(player);
            break; //remove the player from the gameRing and add them to the history list
         }
      }
   }
}