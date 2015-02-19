package fi.nano.tangential;

import fi.nano.tangential.ui.Window;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Main-luokka.
 *
 * @author Nanofus
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<String> levels = new ArrayList<>();
        levels.add("level1");
        levels.add("level1");
        
        Game game = new Game(levels);

        Window window = new Window(game);
        SwingUtilities.invokeLater(window);

        game.SetWindow(window);

    }
}
