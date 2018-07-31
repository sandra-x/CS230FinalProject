
/**
 * A Panel that generates a maze of the input size. Since we could not succeed in making a complete random maze,
 * we kept it seperate from the CardLayoutTest GUI and added it on the MazeGUI to test it. 
 * 
 * We believe the problem is that we tried to add a wall to the tiles next to the next tile, however,
 * that messes up which tiles are visited and might make the tiles that are already visited into a wall. 
 * But if we do not make them into a wall, there are too many visitable tiles in the maze.

 * 
 * @author Kaori Hayah
 * @version 5/17/2018
 */
import java.util.*;
import javax.swing.ImageIcon;;
import javax.swing.*;
import java.awt.*;
import javafoundations.LinkedQueue;
import javafoundations.LinkedStack;
public class MazeGeneratorPanel extends JPanel
{
    private int width, height;
    private int [][] maze;
    private Random random = new Random();
    private JLabel[][] labels;
    private LinkedStack<Integer> visited = new LinkedStack<Integer>();
    private int totalCells;
    private int visitedCells;
    private Hashtable<String, ImageIcon> images = new Hashtable<String, ImageIcon>();
    Game game;
    private boolean[][] isVisited;
    
    
    public MazeGeneratorPanel(int width, int height){
        JPanel mazePanel = new JPanel(); // panel which the maze is placed on
        mazePanel.setLayout(new GridLayout(width, height));
        mazePanel.setFocusable(true);
        mazePanel.requestFocusInWindow();
        
        
        this.add(mazePanel);
        
        totalCells = width*height;
        visitedCells = 1;
        
        this.width = width;
        this.height = height;
        maze = new int[width][height];
        isVisited = new boolean[width][height];
        
        labels = new JLabel[width][height];
        
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                labels[i][j] = new JLabel();
                labels[i][j].setOpaque(true);
                labels[i][j].setPreferredSize(new Dimension(15,15));
                maze[i][j] = 1;
                mazePanel.add(labels[i][j]);
                isVisited[i][j] = false;
            }
        }
        
        int x =  random.nextInt(width-2); // avoiding the ends
        int y =  random.nextInt(height-2);  // avoiding the ends
                
        generateMaze(1, 1);
        
        
            images.put("i0", new ImageIcon("images/white.jpg"));
            images.put("i1", new ImageIcon("images/black.jpg"));
            images.put("i2", new ImageIcon("images/gray.jpg"));
                            // one for i3 items
            images.put("i4", new ImageIcon("images/red.jpg"));
            images.put("i9", new ImageIcon("images/green.jpg"));
            
            this.update();
        
    }
    
    public void update() {
        for (int i = 0; i < labels.length; i++) {
            
            for (int j = 0; j < labels[0].length; j++) {
                
                if (maze[i][j] == 0 ){
                    labels[i][j].setIcon(images.get("i0"));
                } else if (maze[i][j] == 1){
                    labels[i][j].setPreferredSize(new Dimension(15,15));
                    labels[i][j].setIcon(images.get("i1"));
                } else if (maze[i][j] == 2) {
                    labels[i][j].setIcon(images.get("i2"));
                } else if( maze[i][j] == 3){ 
                    labels[i][j].setIcon(images.get("i3"));
                }
                else if (maze[i][j] == 4) {
                    labels[i][j].setIcon(images.get("i4"));
                } else if (maze[i][j] == 9) {
                    labels[i][j].setIcon(images.get("i9"));
                } else labels[i][j].setIcon(images.get("i0")); // delete
            }
            
        }
        
    }
    
    /*
     * Returns a LinkedList of the adjacent tiles that are visited
     * 
     * @ param - int x, int y: location of the tile
     * @ return - linked list of the tiles that are adjacent to the tile and are not visited yet
     * 
     */
    public LinkedList<String> getAdj(int x, int y){
        
        LinkedList<String> result = new LinkedList<String>();
        
        if(y+1 < height  && !isVisited[x][y+1]) result.add("south");
        if (y-1 >= 0 && !isVisited[x][y-1]) result.add("north");
        if (x+1 < width  && !isVisited[x+1][y] ) result.add("east");
        if (x-1 >= 0 && !isVisited[x-1][y] ) result.add("west");
               
        return result;
    
    }
    
   /*
    * Generates a maze of the size the input specifies. The general idea is that you mark the current tile as visited, 
    * add it to a stack, and while there are unvisited cells, randomly move to an adjacent tile that is unvisited and repreat 
    * the same step. If there is no adjacent unvisited tile, the program backtracks by poping the indeces of the previous tiles
    * out of the stack and moving back to a tile that has adjacent unvisited tiles.
    */
   public void generateMaze(int x, int y){
                           
        maze[x][y] = 0; 
        visited.push(x);
        visited.push(y);
        isVisited[x][y] = true;
        
        String move = "";
      
        
        //System.out.println(adjacent);
        
        
        while(visitedCells < totalCells - 4){
            
            LinkedList<String> adjacent = new LinkedList<String>();
            adjacent = getAdj(x,y);
                      
            //randomly chooses the next tile
            int next = random.nextInt(4);
            
                        
            if(adjacent.size() >= 1){
                
                // if next == 0, move up
                if(next == 0 && y - 1 >=  0){
                    
                    visited.push(x);  // push the index to stack
                    visited.push(y);
                    
                    maze[x][y-1] = 0;
                    
                    isVisited[x][y-1] = true;  // mark the tile as visited
                    
                    
                    // // make the adjacent unvisited tiles into a wall
                    if(x+1 < height && maze[x+1][y-1] != 0){
                        
                        isVisited[x+1][y-1] = true;
                        maze[x+1][y-1] = 1;
                        visited.push(x+1);
                        visited.push(y-1);
                        visitedCells++;
                    }
                    if(x-1 >= 0 && maze[x-1][y-1] != 0){
                        isVisited[x-1][y-1] = true;
                        maze[x-1][y-1] = 1;
                        visited.push(x-1);
                        visited.push(y-1);
                        visitedCells++;
                    }
                    
                    y = y - 1;
                    visitedCells++;
                } 
                 // if next == 1, move down
                else if(next == 1 && y + 1 < height){
                    
                    visited.push(x);
                    visited.push(y);
                    maze[x][y+1] = 0;
                    isVisited[x][y+1] = true;
                    
                    if(x+1 < height && maze[x+1][y+1] != 0){
                        isVisited[x+1][y+1] = true;
                        maze[x+1][y+1] = 1;
                        visited.push(x+1);
                        visited.push(y+1);
                        visitedCells++;
                    }
                    if(x-1 >= 0 && maze[x-1][y+1] != 0){
                        isVisited[x-1][y+1] = true;
                        maze[x-1][y+1] = 1;
                        visited.push(x-1);
                        visited.push(y+1);
                        visitedCells++;
                    }
                    
                    y = y + 1;
                    visitedCells++;
                } 
                 // if next == 2, move left
                else if(next == 2 && x - 1 >= 0){
                    
                    visited.push(x);
                    visited.push(y);
                    maze[x-1][y] = 0;
                    isVisited[x-1][y] = true;
                    
                if(y-1>= 0 && maze[x-1][y-1] != 0){
                    isVisited[x-1][y-1] = true;
                    maze[x-1][y-1] = 1;
                    visited.push(x-1);
                    visited.push(y-1);
                    visitedCells++;
                }
                if(y+1 < height && maze[x-1][y+1] != 0){
                    isVisited[x-1][y+1] = true;
                    maze[x-1][y+1] = 1;
                    visited.push(x-1);
                    visited.push(y+1);
                    visitedCells++;
                    }
                    
                    x = x - 1;
                    visitedCells++;
                } 
                 // if next == 3, move right
                else if(next == 3 && x + 1 < width){
                    
                    visited.push(x);
                    visited.push(y);
                    maze[x+1][y] = 0;
                    isVisited[x+1][y] = true;
                    
                    if(y+1 < height && maze[x+1][y+1] != 0){
                        isVisited[x+1][y+1] = true;
                        maze[x+1][y+1] = 1;
                        visited.push(x+1);
                        visited.push(y+1);
                        visitedCells++;
                    }
                    if(y-1 >= 0 && maze[x+1][y-1] != 0){
                        isVisited[x+1][y-1] = true;
                        maze[x+1][y-1] = 1;
                        visited.push(x+1);
                        visited.push(y-1);
                        visitedCells++;
                    }
                    
                    x = x + 1;
                    visitedCells++;
                }
            } 
            // backtracking happens
            else{
                if(!visited.isEmpty()){
                
                    int currentY = visited.pop();
                    int currentX = visited.pop();
                    
                    x = currentX;
                    y = currentY;
                }
            }
            
            
        }
            
        for(int i = 0; i < width; i++){
             maze[0][i] = 1;
             maze[i][0] = 1;
             maze[height - 1][i] = 1;
             maze[i][height - 1] = 1;
        }
        
        maze[height - 2][width - 2] = 9;        
        maze[1][1] = 4;
        
    }

    
}


