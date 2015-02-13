package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import fi.nano.tangential.gameLogic.enums.Direction;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Peli-ikkuna.
 *
 * @author Nanofus
 */
public class Window implements Runnable {

    private final int windowWidth = 800;
    private final int windowHeight = 600;
    
    private final Game game;

    private JFrame frame;

    private final InputListener inputListener;
    private LevelRenderer levelRenderer;
    private final ImageLoader imageLoader;

    public Window(Game game) {
        this.game = game;

        imageLoader = new ImageLoader();
        inputListener = new InputListener(game);
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(inputListener);

        levelRenderer = new LevelRenderer(imageLoader,game.GetLevel(),windowWidth,windowHeight);

        frame.add(levelRenderer);
    }

    private void createComponents(Container container) {

    }
    
    public void Refresh() {
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void RotateCamera(Direction direction) {
        levelRenderer.RotateCamera(direction);
    }

}
