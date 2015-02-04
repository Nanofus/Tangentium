
package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Peli-ikkuna.
 * 
 * @author Nanofus
 */
public class Window implements Runnable {
    
    private Game game;
    
    private JFrame frame;

    public Window(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setPreferredSize(new Dimension(800,600));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
