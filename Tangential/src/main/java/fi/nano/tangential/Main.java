
package fi.nano.tangential;

import fi.nano.tangential.ui.Window;
import javax.swing.SwingUtilities;

/**
 * Main-luokka.
 * 
 * @author Nanofus
 */
public class Main {

    public static void main(String[] args) {
        
        Game game = new Game("level1");
        
        Window window = new Window(game);
        SwingUtilities.invokeLater(window);
        
        game.SetWindow(window);
           
    }
}