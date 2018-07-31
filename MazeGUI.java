

/**
 * A GUI to test the MazeGeneratorPanel class. Since the generator does not completely work, we kept it seperate from the
 * CardLayoutTest GUI.
 * @ author; Kaori Hayashi
 * @ version: 5/17/2018
 * 
 * 
 */

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class MazeGUI extends JFrame
{    
    
    public static void main(String[] args) {
                        
        JFrame frame = new JFrame("aMAZEing!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        Game game = new Game("hard");
        GamePanel panel = new GamePanel(game, "classic");
        
        MazeGeneratorPanel mazeP = new MazeGeneratorPanel(40,40);
        frame.getContentPane().add(mazeP);
        
        
        
        frame.pack();
        frame.setVisible(true);
       
    }   
    
    
}
