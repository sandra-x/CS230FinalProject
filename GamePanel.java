import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String;
import java.util.*;
import java.awt.event.KeyListener;
/**
 * GamePanel class to which the maze and the game is added. It constructs a game and assigns images to each
 * tile of the maze according to it's integer. Has a hashtable to store the images that will be assigned
 * to each integer of tile. Has an actionListener class that is added to the mazePanel, to which the maze is added.
 * 
 * @ author: joint-coded by Sandra Xu and Kaori Hayashi. 
 * @ version: 5/16/2018
 *
 */
public class GamePanel extends JPanel
{
    
    JLabel[][] labels; // tiles of the maze
    private JButton options;
    private ImageIcon i0, i1, i2, i9; // default -- use hash table or something for different settings
    JPanel mazePanel = new JPanel(); // panel which the maze is placed on
    JPanel buttonPanel= new JPanel();
    Game game;
    private String settings;
    private CardLayoutTest cards;
    Hashtable<String, ImageIcon> images = new Hashtable<String, ImageIcon>(); // hashtable to store the images for each tile integer
    private JLabel gameover;
    private JLabel win;
    private JLabel score;
    private JLabel header;
    
    
    public GamePanel(Game game, String settings) { 
        this.setLayout(new FlowLayout());
        
        setPreferredSize(new Dimension(1000, 1000));
        this.game = game;
        
        header = new JLabel("Help Square Man comfort Sad Man by collecting squares!",SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(1000, 50));
        header.setVisible(true);
        this.add(header);
        
        mazePanel.setLayout(new GridLayout(game.m.getRows(), game.m.getCols()));
        mazePanel.setFocusable(true);
        mazePanel.requestFocusInWindow();
        mazePanel.addKeyListener(new KeysListener());
        this.settings = settings;
        this.add(mazePanel);
        
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setFocusable(true);
        buttonPanel.addKeyListener(new KeysListener());
        this.add(buttonPanel);
        
        
        gameover = new JLabel("Game over! Click restart game.");
        gameover.setVisible(false);
        
        
        win = new JLabel("You win! Click restart game.");
        win.setVisible(false); 
        win.setPreferredSize(new Dimension(200,50));
        //win.setHorizontalAlignment(75);
        buttonPanel.add(win, BorderLayout.NORTH);
        
        score = new JLabel("The score is " + game.score);
        score.setPreferredSize(new Dimension(200,50)     );
        //score.setHorizontalAlignment(75);
        buttonPanel.add(score, BorderLayout.SOUTH);
                           
        // 2d array of labels that is the same size as the maze
        labels = new JLabel[game.m.getRows()][game.m.getCols()];
        
        
        for (int i = 0; i < labels.length; i++) {
            
            for (int j = 0; j < labels[0].length; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setOpaque(true);
                labels[i][j].setPreferredSize(new Dimension(15,15)); // make them fit to the frame depending on number of labels
                mazePanel.add(labels[i][j]);
            }
            
        }
        
        
        // puts the images to the hashtable according to the setting
        if (settings == "classic"){
            images.put("i0", new ImageIcon("images/white.jpg"));
            images.put("i1", new ImageIcon("images/black.jpg"));
            images.put("i2", new ImageIcon("images/gray.jpg"));
            images.put("i3", new ImageIcon("images/itemclassic.gif"));
            images.put("i4", new ImageIcon("images/charclassic.gif"));
            images.put("i9", new ImageIcon("images/sad.gif"));
        } else if(settings == "universe"){
            images.put("i0", new ImageIcon("images/black.jpg"));
            images.put("i1", new ImageIcon("images/galaxy.png"));
            images.put("i2", new ImageIcon("images/startrailb.gif"));
            images.put("i3", new ImageIcon("images/blingitem.gif"));
            images.put("i4", new ImageIcon("images/charclassic.gif"));
            images.put("i9", new ImageIcon("images/sad.gif"));
        } else if (settings == "flower"){
            images.put("i0", new ImageIcon("images/white.jpg"));
            images.put("i1", new ImageIcon("images/flowers.png"));
            images.put("i2", new ImageIcon("images/leaftrail.gif"));
            images.put("i3", new ImageIcon("images/flitem.gif"));
            images.put("i4", new ImageIcon("images/charclassic.gif"));
            images.put("i9", new ImageIcon("images/sad.gif"));
        } else if (settings == "ocean"){
            images.put("i0", new ImageIcon("images/blue.png"));
            images.put("i1", new ImageIcon("images/wave.gif"));
            images.put("i2", new ImageIcon("images/bubbles.gif"));
            images.put("i3", new ImageIcon("images/fish.gif"));
            images.put("i4", new ImageIcon("images/charclassic.gif"));
            images.put("i9", new ImageIcon("images/sad.gif"));
        }
        
        // Assigns an image to each tile according to its integer
        this.update();
        
        //mazePanel.requestFocus();
        
        
    }
    
    /*
     * A method that sets an image to the tile according to each number
     */
    public void update() {
        for (int i = 0; i < labels.length; i++) {
            
            for (int j = 0; j < labels[0].length; j++) {
                
                if (game.m.maze[i][j] == 0){
                    labels[i][j].setIcon(images.get("i0"));
                } else if (game.m.maze[i][j] == 1){
                    labels[i][j].setIcon(images.get("i1"));
                } else if (game.m.maze[i][j] == 2) {
                    labels[i][j].setIcon(images.get("i2"));
                } else if(game.m.maze[i][j] == 3){
                    labels[i][j].setIcon(images.get("i3"));
                } else if (game.m.maze[i][j] == 4) {
                    labels[i][j].setIcon(images.get("i4"));
                } else if (game.m.maze[i][j] == 9) {
                    labels[i][j].setIcon(images.get("i9"));
                } else labels[i][j].setIcon(images.get("i0")); // delete
            }
            
        }
        
    }
    
    public Game getGame(){
        return game;
    }
    
    // button listener for moving
    private class KeysListener implements KeyListener {       
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            
            score.setText("The score is " + game.score);
            
            if(game.isGameOver()){
                buttonPanel.add(gameover, BorderLayout.NORTH);
        
                gameover.setVisible(true);
            }
            if(game.isWin()){                
                 win.setVisible(true);
                 
            }
            
            switch (key) {
                case KeyEvent.VK_UP: game.moveUp();
                    
                    break;
                case KeyEvent.VK_DOWN: game.moveDown();
                    break;
                case KeyEvent.VK_LEFT: game.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT: game.moveRight();
                    break;
            }
            update();
        }
        
        public void keyReleased(KeyEvent e) {
            
        }
        
        public void keyTyped(KeyEvent e) {
            
        }
        
        
    }
    
    
    
}