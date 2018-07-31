/**
 * 
 * A GUI to which all the panels are added. It has a CardLayout and the panels switch according to
 * the button the user clicks in each panel. It has it's own ActionListener class that responds to 
 * the user's clicking of buttons.
 *
 * @author: Mainly implemented by Kaori Hayashi. Sandra Xu defined the helper methods and parts of 
 *          ActionListener.
 *          
 * @version 5/16/2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Image;
import java.awt.event.KeyListener;
public class CardLayoutTest extends JFrame implements ActionListener
{
    
    CardLayout cLayout = new CardLayout(); // layout of the GUI
    private JPanel cardsPanel = new JPanel(); // the cardsPanel to which the panels are added
    private HomePanel homePanel;
    private SettingsPanel settingsPanel;  
    private InstructionsPanel instructions;
    private GamePanel gamePanel;
    private Game game;
    
    // buttons for homePanel class
    private JButton easy;
    private JButton medium;
    private JButton hard;
    
    // buttons for settingsPanel
    private JButton classic;
    private JButton universe;
    private JButton flower;
    private JButton ocean;
    
    // buttons for instructionsPanel
    private JButton play;
    
    // buttons for gamePanel
    private JButton playAgain;
    private JButton hint;
    private JLabel theHint;
    
    
    //private Settings settings;
    String difficulty = "";
    String settings = "";
    
    public CardLayoutTest() {
        JFrame frame = new JFrame("aMAZEing!");
        setTitle("maze game");
                
        cardsPanel.setLayout(cLayout);
        
        homePanel = new HomePanel();
        homePanel.setLayout(new FlowLayout());
        
          
        // add the level selection buttons to the homePanel. 
        easy = new JButton("easy");
        homePanel.add(easy);
        easy.addActionListener(this);
        medium = new JButton("medium");
        homePanel.add(medium);
        medium.addActionListener(this);
        hard = new JButton("hard");
        homePanel.add(hard);
        hard.addActionListener(this);  
        
        
        
        // add the setting buttons to the settingsPanel
        settingsPanel = new SettingsPanel(); 
        classic = new JButton("classic");
        settingsPanel.add(classic);
        classic.addActionListener(this);        
        universe = new JButton("universe");
        settingsPanel.add(universe);
        universe.addActionListener(this);        
        flower = new JButton("flower");
        settingsPanel.add(flower);
        flower.addActionListener(this);       
        ocean = new JButton("ocean");
        settingsPanel.add(ocean);
        ocean.addActionListener(this);
        
        // add the play button to instructionsPanel
        instructions = new InstructionsPanel();
        play = new JButton("Play game!");
        instructions.add(play);
        play.addActionListener(this);
        
        playAgain = new JButton("Restart game");
        playAgain.addActionListener(this);
        
        
        // add all the panels to the cardsPanel
        cardsPanel.add(homePanel, "home");        
        cardsPanel.add(settingsPanel, "settings");
        cardsPanel.add(instructions, "instructions");
        
        
        cLayout.show(cardsPanel, "home");
        
        
        frame.add(cardsPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    /**
     * An actionListener class that switches the panels and stores information to a variable occasionally.
     * 
     *  HomePanel: if the user selects a level, it switches to settingsPanel and the level is stored
     *              in a difficulty variable that is used to instantiate a gamePanel
     *  SettingsPanel: if the user selects a setting, it switches to the instructionsPanel and the setting is stored
     *                  in a settings variables that is later used to instantiate a gamePanel
     *  InstructionsPanel: if the user clicks the play button, the screen switches to gamePanel and the game starts
     *  GamePanel: when the user clicks play again, it returns to the home screen. If the user clicks get hint button,
     *              it gives out a hint.
     * 
     */
    public void actionPerformed(ActionEvent e){
                
        if(e.getSource() == easy){
            
            cLayout.show(cardsPanel, "settings"); // switch to settingsPanel
            difficulty = "easy"; // store the level
            
        } else if(e.getSource() == medium){
            
            cLayout.show(cardsPanel, "settings"); // switch to settingsPanel
            difficulty = "medium";
            
        } else if(e.getSource() == hard){
            
            cLayout.show(cardsPanel, "settings"); // switch to settingsPanel
            difficulty = "hard";
            
        } else if(e.getSource() == classic){
            
            settings = "classic"; // store the setting
            cLayout.show(cardsPanel, "instructions"); // switch to instructionsPanel   
            
        } else if(e.getSource() == universe){
            
            settings = "universe";
            cLayout.show(cardsPanel, "instructions");
            
        } else if(e.getSource() == flower){
            
            settings = "flower";
            cLayout.show(cardsPanel, "instructions");
            
        } else if(e.getSource() == ocean){
            
            settings = "ocean";
            cLayout.show(cardsPanel, "instructions");
            
        }  else if(e.getSource() == play){
            
            
            game = new Game(difficulty);
            
            // instantiate a gamePanel using the variables initialized by previous selections of level and setting
            gamePanel = new GamePanel(game, settings); 
            //gamePanel.setLayout(new BorderLayout());
            gamePanel.add(playAgain);
            
            hint = new JButton("Get hint");
            hint.addActionListener(this);
            gamePanel.add(hint);      
            
            theHint = new JLabel("You should ");
            theHint.setVisible(false);
            gamePanel.add(theHint);
            
            cardsPanel.add(gamePanel, "game");
            cLayout.show(cardsPanel, "game"); // shows the gamePanel
            gamePanel.mazePanel.requestFocus();
            
        } else if(e.getSource() == playAgain){
            
           cLayout.show(cardsPanel, "home"); // return to homePanel
            
        } else if(e.getSource() == hint){
            
           Path hint = new Path(game);  
           theHint.setText("You should" + hint.giveHint());
           theHint.setVisible(true);
           gamePanel.mazePanel.requestFocus();
        } 
                            
    }
    
    public static void main(String args[]){
        new CardLayoutTest();
    }
    
    public void showCard(String card) {
        cLayout.show(cardsPanel, card);
    }
    
    public String getSetting() {
        return settings;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    
    private void changeToGame() {
        this.game = new Game(this.getDifficulty());
        //this.settings= new Settings(this.getSetting());
        
        //this.gamePanel = new GamePanel(game, settings, this);
        cardsPanel.add(gamePanel, "game panel");
        cLayout.show(cardsPanel, "game panel");
        
    }
    
    
}