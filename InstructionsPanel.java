
/**
 * An instructions panel that displays the instruction of the game.
 * If the user clicks the play button on the panel, the game starts. The button will be added in the CardLayoutTest class.
 *
 * @author: Kaori Hayashi
 * @version: 5/16/2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class InstructionsPanel extends JPanel
{
    // instance variables 
    private JLabel instruction;
 

    /**
     * Constructor for objects of class InstructionsPanel
     */
    public InstructionsPanel()
    {        
        setPreferredSize(new Dimension(1000, 1000));
        instruction = new JLabel("Move using the arrow keys on your keyboard. Collect items to " +
            "boost your score. But be careful! You cannot pass the same path twice",SwingConstants.CENTER);
            
       instruction.setPreferredSize(new Dimension(1000, 300));
        instruction.setOpaque(true);        
        add(instruction);
     
    }

    
}
