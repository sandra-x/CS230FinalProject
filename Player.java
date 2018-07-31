
/**
 * A Player class that keeps track of the location of the player. The locations are kept as the ints
 * for the x grid and y grid. It is used in the game class and the x, y changes according to the movement
 * that takes place in game class. For example, if the user moves right, x increases by 1.
 * 
 * @ author: Sandra Xu
 * @ version: 5/17/2018
 *
 */
public class Player
{
    int x, y;
    
    public Player(){
        this.x = 1;
        y = 1;
    }
    
    public String toString() {
        return this.x + ", " + this.y;
    }
    
    
    
    public static void main (String[] args) {
        //Player p = new Player();
        //System.out.println(p);
        
    }
}