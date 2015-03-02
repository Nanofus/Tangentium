package fi.nano.tangentium.ui;

import fi.nano.tangentium.Game;
import fi.nano.tangentium.gameLogic.Level;
import fi.nano.tangentium.gameLogic.enums.Direction;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Game window.
 *
 * @author Nanofus
 */
public class OldWindow implements Runnable {

    private int windowWidth = 1280;
    private int windowHeight = 720;

    private final Game game;

    private JFrame frame;

    //private final InputListener inputListener;
    private OldRenderer renderer;
    private final ImageLoader imageLoader;

    public OldWindow(Game game, int windowWidth, int windowHeight) {
        this.game = game;
        
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        imageLoader = new ImageLoader();
        //inputListener = new InputListener(game);
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setIconImage(imageLoader.GetImage("Icon"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(windowWidth + frame.getInsets().left + frame.getInsets().right, windowHeight + frame.getInsets().top + frame.getInsets().bottom));
        //frame.setSize(new Dimension(windowWidth+15,windowHeight+35));
        
        //CenterWindow();
        
        //frame.addKeyListener(inputListener);

        renderer = new OldRenderer(imageLoader, game.GetLevel(), game.GetLevel().GetPlayer(), windowWidth, windowHeight);

        frame.add(renderer);
    }

    /**
     * Tells the renderer if a new Level is loaded.
     * @param level New level
     */
    public void NewLevel(Level level) {
        renderer.NewLevel(level, level.GetPlayer());
        Refresh();
    }

    public void Refresh() {
        frame.repaint();
    }

    public JFrame GetFrame() {
        return frame;
    }

    public void RotateCamera(Direction direction) {
        renderer.RotateCamera(direction);
    }

    public void WinGame() {
        renderer.GameWon();
        Refresh();
    }

    //Doesn't work in Linux!
    private void CenterWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3);
        frame.setLocation(x, y);
    }

}
