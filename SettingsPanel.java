
/**
 *  A panel in which the user selects the setting (a theme) of the maze. It has the instruction and buttons for 
 *  each setting. The buttons are added in the CardLayoutTest class and when the user clicks on a button,
 *  it stores the information in a variable so that it can be used to instantiate a GamePanel.
 *
 * @author Kaori Hayashi
 * @version 4/30/2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel
{
    // instance variables 
    
    private JLabel instruction;
    

    /**
     * Constructor for objects of class SettingsPanel
     */
    public SettingsPanel()
    {
        setPreferredSize(new Dimension(1000, 1000));
        instruction = new JLabel("Select the setting below",SwingConstants.CENTER);
        instruction.setPreferredSize(new Dimension(1000, 300));
        instruction.setOpaque(true);
        
        add(instruction);
        
        
    }

    
}