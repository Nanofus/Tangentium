package fi.nano.tangential;

import fi.nano.tangential.fileProcessing.ConfigReader;
import fi.nano.tangential.fileProcessing.LevelListReader;
import fi.nano.tangential.ui.ErrorDialog;
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
