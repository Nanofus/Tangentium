package fi.nano.tangentium;

import fi.nano.tangentium.fileProcessing.LevelListReader;
import fi.nano.tangentium.gameLogic.enums.Direction;

import java.util.ArrayList;

/**
 * Main class.
 *
 * @author Nanofus
 */
public class GameMaster {
    private Window window;

    private Game game;

    public GameMaster(Window window) {
        this.window = window;
        Run();
    }

    public void Run() {
        ArrayList<String> levels = new LevelListReader().GetLevelList();
        
        game = new Game(levels);
    }

    public void MovePlayer(Direction dir) {
        game.MovePlayer(dir);
    }

    public void PlayerUse() {
        game.PlayerUse();
    }

}
