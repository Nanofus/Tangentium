package fi.nano.tangential.ui;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.Position;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.enums.Direction;
import static fi.nano.tangential.gameLogic.enums.Direction.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Piirtää käyttöliittymän ja valikot
 *
 * @author Nanofus
 */
public class GUIRenderer extends JPanel {

    private final Actor player;

    private final ImageLoader imageLoader;

    private final int windowWidth;
    private final int windowHeight;

    GUIRenderer(ImageLoader imageLoader, Actor player, int windowWidth, int windowHeight) {
        this.setOpaque(false);
        this.setLayout(null);

        this.imageLoader = imageLoader;

        this.player = player;

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage drawnImage = imageLoader.GetImage("Weapon Background");

        g.drawImage(drawnImage, windowWidth - drawnImage.getWidth(), windowHeight - drawnImage.getHeight(), null);

    }

}
