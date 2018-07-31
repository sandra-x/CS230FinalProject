
/**
 * A home panel that prompts the user to select the level of the maze.
 *
 * @author Kaori Hayashi
 * @version 5/16/2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HomePanel extends JPanel
{
    // instance variables 
    private JLabel header; // the title of the home screen
    private JLabel instruction;
    public String level;
        
    /**
     * Constructor for objects of class HomePanel
     */
    public HomePanel()
    {
        
        setPreferredSize(new Dimension(1000, 1000));
        header = new JLabel("Wanna start a game?  First choose the level of the maze!",SwingConstants.CENTER); 
        header.setPreferredSize(new Dimension(1000,300));
        header.setOpaque(true);
                        
        add(header);

        
    }

    
}