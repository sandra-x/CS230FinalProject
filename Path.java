
/**
 * A class that finds a path from current location to goal and gives out a hint to the user.
 * The constructor takes a game as an input and sets the current location to the current location
 * in the input game. Does not work with items (treats them as walls) and there is a bug that in some
 * locations it doesn't work.
 *
 * @author Kaori Hayashi and Sandra Xu
 * @version (a version number or a date)
 */

import java.util.*;
import javafoundations.LinkedQueue;
import javafoundations.LinkedStack;

public class Path
{    
    Game game;
    int playerX;
    int playerY;
    final int X;
    final int Y;
    int[][] gameCopy;
    LinkedStack<Integer> result;
    
    /**
     * Creates a Path that takes as a variable a Game
     * 
     * @param game  the Game
     */
    public Path(Game game){        
        result = new LinkedStack<Integer>();
        this.game = game;
        playerX = game.player.x;
        playerY = game.player.y;
        X = game.player.x;
        Y = game.player.y;        
        gameCopy = game.m.clone();        
    }
    
    /*
     * Finds and returns the path that is represented as a vectors of ints. There are 5 types of ints
     * in the vector and each int represents the movement the user should take. This is a helper 
     * used in the giveHint() method below.
     * 0: no movement, goal reached
     * 1: move right
     * 2: move left
     * 3: move up
     * 4: move down
     * 
     * @ return - Vector representation of the path to goal from current location
     */
    public LinkedStack<Integer> findPath(){
        // LinkedStack<Integer> result = new LinkedStack<Integer>();
        boolean valid = false;
       
        
        // check right
        if(playerX < game.m.rows-1){
            //System.out.println("here");
            if (gameCopy[playerY][playerX+1] == 9){
                
                 result.push(1);
                 return result;
            } else if (gameCopy[playerY][playerX+1] == 0|| gameCopy[playerY][playerX+1]==3 ||gameCopy[playerY][playerX+1]==3){
                //System.out.println("here2");
                
                valid = true;
                
                playerX++;
                gameCopy[playerY][playerX] = 2; //
                result.push(1);
                
                return findPath();
            }            
        
        // check up            
        }  if (playerY > 1){
            //System.out.println("here");
            if (gameCopy[playerY-1][playerX] == 9) {
               
                result.push(3);
                return result;
            } else if (gameCopy[playerY-1][playerX] == 0 ||gameCopy[playerY-1][playerX]==3 || gameCopy[playerY-1][playerX]==3) {
                valid = true;
                
                playerY--;
                gameCopy[playerY][playerX] = 2; //
                result.push(3);
                
              
                return findPath();
            }
            
            // check left
        }  if (playerX > 1){
            if (gameCopy[playerY][playerX-1] == 9) {
                //System.out.println("done");
                result.push(2);
                return result;
            } else if (gameCopy[playerY][playerX-1] == 0 || gameCopy[playerY][playerX-1]==3 ||gameCopy[playerY][playerX-1]==3) {
                valid = true;
                //System.out.println(this);
                playerX--;
                gameCopy[playerY][playerX] = 2; //
                result.push(2);
                //System.out.println("3");
                return findPath();           
            }
            
            // check down
        }  if (playerY < game.m.columns-1){
            //System.out.println("here");
            if (gameCopy[playerY+1][playerX] ==9) {
                //System.out.println("done");
                result.push(4);
                return result;
            } else if (gameCopy[playerY+1][playerX] == 0 || gameCopy[playerY][playerX+1]==3||gameCopy[playerY][playerX+1]==3) {
                valid = true;
                //System.out.println(this);
                playerY++;
                gameCopy[playerY][playerX] = 2; //
                result.push(4);
                //System.out.println("4");
                return findPath();              
            }
            
            // no path exists
        } if (!valid) {
             // pop the integers until it comes to a tile that has a valid move. replace 2 with 8, 8 meaning
             // no path exists from that tile
             
              //System.out.println("valid " + hasValidMove(playerX, playerY));       
              //System.out.println(this);
              
              while (!hasValidMove(playerX, playerY)) {;
                  int move = result.pop();
                  //System.out.println("move" + move);
                  // check which move
                  switch (move) {
                      case 1: gameCopy[playerY][playerX] = 8;
                              playerX--;
                              //System.out.println(this);
                              break;
                      case 3: gameCopy[playerY][playerX] = 8;
                              playerY++;
                              //System.out.println(this);
                              break;
                      case 2: gameCopy[playerY][playerX] = 8;
                              playerX++;
                              //System.out.println(this);
                              break;
                      case 4: gameCopy[playerY][playerX] = 8;
                              playerY--;
                              //System.out.println(this);
                              break;
                  }                  
                    
             }
             return findPath();

             // need help with figuring out that no path exists and going back to a tile where a path exists

        }     
        
        return result;
    }
    
    /**
     * Checks to see if a tile contains a valid move. A valid move exists if a tile to the right, left, up, or
     * down is an unvisited tile (items should also be valid but doesn't work). Used in the findPath() method.
     * 
     * @param x, y  the x and y coordinates of the tile
     */
    private boolean hasValidMove(int x, int y) {        
        boolean hasValid = false;
        if (x > 0 && y > 0 && x < game.m.rows-1 && y < game.m.columns-1) {
            if (gameCopy[y+1][x] == 0 || gameCopy[y+1][x] == 0)
                hasValid = true;
            if (gameCopy[y][x+1] == 0 || gameCopy[y][x+1] == 0)
                hasValid = true;
            if (gameCopy[y-1][x] == 0 || gameCopy[y-1][x] == 0)
                hasValid = true;
            if (gameCopy[y][x-1] == 0 || gameCopy[y][x-1] == 0)
                hasValid = true;
        }
        return hasValid;
    }
    
    /*
     * Returns a string that tells the user which path to take next as a hint.
     * 
     * @ return - the string representation of the next path to take
     */
    public String giveHint(){
        LinkedStack<Integer> path = this.findPath();
        while(path.size() != 1){
            path.pop();
        }
        int step = path.pop();
        String result = "";
        
        if(step == 1){
            result = " Go right.";
        } else if (step == 3){
            result = "Go up.";
        } else if (step == 2){
            result = "Go left.";
        } else {
            result = "Go down.";
        }
        
        return result;
        
    }
    
    /**
     * Returns a string representation of the gameCopy. Used for testing.
     */
    public String toString() {
        String s = "";
        for (int[] a: gameCopy) {
            s += Arrays.toString(a) + "\n";
        }
        return s;
    }
    
    public static void main (String[] args) {
        Game game = new Game("easy");
        Path path = new Path(game);
        System.out.println(path);
        System.out.println("x " + path.playerX);
        System.out.println("y " + path.playerY);
        LinkedStack<Integer> p1 = path.findPath();
        System.out.println(p1);
    }
   
}
