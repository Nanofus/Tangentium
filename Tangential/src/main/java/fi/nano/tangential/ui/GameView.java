package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import fi.nano.tangential.gameLogic.Level;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nanofus
 */
public class GameView extends JFrame {

    Level level;

    public GameView(Game game) {
        this.level = game.GetLevel();
    }

}
