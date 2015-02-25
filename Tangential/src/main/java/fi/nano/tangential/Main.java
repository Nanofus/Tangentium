package fi.nano.tangential;

import fi.nano.tangential.fileProcessing.ConfigReader;
import fi.nano.tangential.fileProcessing.LevelListReader;
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
        ConfigReader config = new ConfigReader();

        LevelListReader levelListReader = new LevelListReader();
        ArrayList<String> levels = levelListReader.ReadLevelList();

        Game game = new Game(levels);

        Window window = new Window(game, config.GetWindowWidth(), config.GetWindowHeight());
        SwingUtilities.invokeLater(window);

        game.SetWindow(window);

    }
}
