package fi.nano.tangentium;

import fi.nano.tangentium.Game;
import fi.nano.tangentium.fileProcessing.ConfigReader;
import fi.nano.tangentium.fileProcessing.LevelListReader;
import fi.nano.tangentium.ui.ErrorDialog;
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
        ErrorDialog errorDialog = new ErrorDialog();
        
        ConfigReader config = new ConfigReader(errorDialog);

        LevelListReader levelListReader = new LevelListReader(errorDialog);
        ArrayList<String> levels = levelListReader.GetLevelList();

        Game game = new Game(errorDialog,levels);

        Window window = new Window(game, config.GetWindowWidth(), config.GetWindowHeight());
        SwingUtilities.invokeLater(window);

        game.SetWindow(window);

    }
}
