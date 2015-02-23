package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.enums.Direction;
import java.awt.Dimension;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Peli-ikkuna.
 *
 * @author Nanofus
 */
public class Window implements Runnable {

    private int windowWidth = 1280;
    private int windowHeight = 720;

    private final Game game;

    private JFrame frame;

    private final InputListener inputListener;
    private Renderer renderer;
    private final ImageLoader imageLoader;

    public Window(Game game) {
        this.game = game;

        imageLoader = new ImageLoader();
        inputListener = new InputListener(game);
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(imageLoader.GetImage("Icon"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(windowWidth + frame.getInsets().left + frame.getInsets().right, windowHeight + frame.getInsets().top + frame.getInsets().bottom));
        
        frame.addKeyListener(inputListener);

        renderer = new Renderer(imageLoader, game.GetLevel(), game.GetLevel().GetPlayer(), windowWidth, windowHeight);

        frame.add(renderer);
    }

    public void RestartLevel(Level level) {
        renderer.RestartLevel(level, level.GetPlayer());
        Refresh();
    }

    public void Refresh() {
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void RotateCamera(Direction direction) {
        renderer.RotateCamera(direction);
    }

    public void WinGame() {
        renderer.GameWon();
        Refresh();
    }

}
