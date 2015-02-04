
package fi.nano.tangential;

import fi.nano.tangential.ui.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nanofus
 */
public class Main {

    public static void main(String[] args) {
        
        Game game = new Game(100,100,50,50);
        
        Window window = new Window(game);
        SwingUtilities.invokeLater(window);
        
        game.RunGame();
           
    }
}