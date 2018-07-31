/**
 * A class that combines Maze and Player class. The constructor takes in a string that specifies the level
 * and creates a maze of that level. The player location is initially set to (1,1).
 * It has methods that allows the user to move within the maze by changing the x and y value of player
 * every time.
 * 
 * @ author: Mainly coded by Sandra Xu. 
 * @ Version: 5/16/2018
 * 
 *
 */
public class Game
{
    Maze m;
    Player player;
    int count;
    int score;
    private boolean gameOver = false;
    private boolean win = false;
    private String level;
    
    
    /*
     * A constructor that takes in a string to specify the level and creates a maze of that level.
     * The intial location of the player (start point) is set to (1, 1).
     * 
     * @param - String difficulty
     * 
     */
    public Game(String difficulty) {
        if (difficulty == "easy") {
            m = new Maze("easy");
            level = "easy";
        } else if (difficulty == "medium"){
            m = new Maze("medium");
            level = "medium";
        } else if (difficulty == "hard"){
            m = new Maze("hard");
            level = "hard";
        }
       
        count = 0;
        score = 0;
        player = new Player();
        m.maze[player.y][player.x] = 4;
        // sets the initial location of the player as the starting point
    }
    
    /*
     * Getter method of the level
     * @ return - String level of the maze
     */
    public String getLevel(){
        return level;        
    }
       
    /*
     * A method that allows the user to move within the maze. User cannot move if the tile that he/she
     * is trying to move to is already visited or a wall. If it is a goal, the game is over and the user
     * wins. 
     */
    public void moveUp() {
        if (player.y > 0) {
            switch(m.maze[player.y-1][player.x]) {
                case 0: m.maze[player.y][player.x] = 2;  
                        player.y--;
                        m.maze[player.y][player.x] = 4;  
                        score += 100;
                        break;
                        // moves to the upper tile
                case 1: break;
                        // cannot move to a wall
                case 2: gameOver = true;
                        break;
                        // cannot visit the same cell twice
                case 3: m.maze[player.y][player.x] = 2;
                        count++; 
                        score += 500;
                        player.y--;
                        m.maze[player.y][player.x] = 4;
                        break;
                        // an item is collected
                case 9: m.maze[player.y][player.x] = 2;
                        player.y--;
                        m.maze[player.y][player.x] = 4;
                        if (count == 0) gameOver = true;
                        else win = true;                       
                        break;
                        // the user reaches goal and wins
                        
            }
        }
    }
    
    /*
     * Same as the moveUp() method except that it moves down
     */
    public void moveDown() {
        if (player.y < m.getCols()-1) {
            
            switch(m.maze[player.y+1][player.x]) {
                case 0: m.maze[player.y][player.x] = 2;
                        player.y++;
                        m.maze[player.y][player.x] = 4;
                        score += 100;
                        break;
                case 1: break;
                case 2: gameOver = true;
                        break;
                case 3: m.maze[player.y][player.x] = 2;
                        count++;
                        score += 500;
                        player.y++;
                        m.maze[player.y][player.x] = 4;
                        break;
                case 9: m.maze[player.y][player.x] = 2;
                        player.y++;
                        m.maze[player.y][player.x] = 4;
                        if (count == 0) gameOver = true;
                        else win = true;                       
                        break;
            }
        }
    }
    
    /*
     * Same as the moveUp() method except that it moves left
     */
     public void moveLeft() {
        if (player.x > 0) {           
            switch(m.maze[player.y][player.x-1]) {
                case 0: m.maze[player.y][player.x] = 2;
                        player.x--;
                        m.maze[player.y][player.x] = 4;
                        score += 100;
                        break;
                case 1: break;
                case 2: gameOver = true;
                        break;
                case 3: m.maze[player.y][player.x] = 2;
                        count++;
                        score += 500;
                        player.x--;
                        m.maze[player.y][player.x] = 4;
                        break;
                case 9: m.maze[player.y][player.x] = 2;
                        player.x--;
                        m.maze[player.y][player.x] = 4;
                        if (count == 0) gameOver = true;
                        else win = true;                       
                        break;
            }
        }
    }
    
    /*
     * Same as the moveUp() method except that it moves right
     */
    public void moveRight() {
        if (player.x < m.getRows()-1) {
            switch(m.maze[player.y][player.x+1]) {
                case 0: m.maze[player.y][player.x] = 2;
                        player.x++;
                        m.maze[player.y][player.x] = 4;
                        score += 100;
                        break;
                case 1: break;
                case 2: gameOver = true;
                        break;
                case 3: m.maze[player.y][player.x] = 2;
                        count++;
                        score += 500;
                        player.x++;
                        m.maze[player.y][player.x] = 4;
                        break;
                case 9: m.maze[player.y][player.x] = 2;
                        player.x++;
                        m.maze[player.y][player.x] = 4;
                        if (count == 0) gameOver = true;
                        else win = true;                       
                        break;
            }
        }
    }
    
    /*
     * checks if the game is over. It is used in the moving methods.
     * @return - boolean. True if game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }
    
    /*
     * Checks if the user won the game.
     * @ return - boolean. True if the user won, false otherwise
     */
    public boolean isWin() {
        return win;
    }
    
    public static void main(String[] args) {
        

       
    }
}