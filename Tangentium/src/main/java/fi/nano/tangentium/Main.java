package fi.nano.tangentium;

import fi.nano.tangentium.fileProcessing.ConfigReader;
import fi.nano.tangentium.fileProcessing.LevelListReader;
import fi.nano.tangentium.ui.Window;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Main class.
 *
 * @author Nanofus
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<String> levels = new LevelListReader().GetLevelList();
        
        ConfigReader config = new ConfigReader();
        
        Game game = new Game(levels);

        Window window = new Window(game, config.GetWindowWidth(), config.GetWindowHeight());
        SwingUtilities.invokeLater(window);

        game.SetWindow(window);

    }
}
