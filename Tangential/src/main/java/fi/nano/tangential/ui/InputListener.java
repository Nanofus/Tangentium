package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import static fi.nano.tangential.gameLogic.enums.Direction.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Käyttäjän syötettä kuunteleva kuuntelija. Lähettää napinpainallukset Gamelle.
 * 
 * @author Nanofus
 */
public class InputListener implements KeyListener {

    private Game game;
    
    public InputListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            game.MovePlayer(RIGHT);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            game.MovePlayer(LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            game.MovePlayer(UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            game.MovePlayer(DOWN);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            game.PickItem();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}