package fi.nano.tangential.ui;

import fi.nano.tangential.gameLogic.Level;
import fi.nano.tangential.gameLogic.Position;
import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.entities.Item;
import fi.nano.tangential.gameLogic.enums.Direction;
import static fi.nano.tangential.gameLogic.enums.Direction.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Piirtää tason, hahmot ja esineet ruudulle.
 *
 * @author Nanofus
 */
public class Renderer extends JPanel {

    private final int tileSizeX;
    private final int tileSizeY;

    private final int actorSizeX;
    private final int actorSizeY;

    private final Level level;
    private final Actor player;

    private final ImageLoader imageLoader;

    private int windowWidth;
    private int windowHeight;
    private int offsetX;
    private int offsetY;

    private ArrayList<String> guiTexts;
    private ArrayList<Integer> guiPosX;
    private ArrayList<Integer> guiPosY;

    private Direction cameraDirection = UP;
    private int rotationX = 1;
    private int rotationY = 1;

    Renderer(ImageLoader imageLoader, Level level, Actor player, int windowWidth, int windowHeight) {
        this.imageLoader = imageLoader;

        this.level = level;
        this.player = player;

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        tileSizeX = imageLoader.GetImage("Floor").getWidth();
        tileSizeY = imageLoader.GetImage("Floor").getHeight();

        actorSizeX = imageLoader.GetImage("Player").getWidth();
        actorSizeY = imageLoader.GetImage("Player").getHeight();
    }

    public void SetWindowSize(int x, int y) {
        windowWidth = x;
        windowHeight = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        guiTexts = new ArrayList();
        guiPosX = new ArrayList();
        guiPosY = new ArrayList();

        PaintLevel(g);
        PaintGUI(g);
    }

    private void PaintLevel(Graphics g) {
        Position camPosition = new Position(level.GetPlayer().GetPosition().x, level.GetPlayer().GetPosition().y);
        Position isoCamPosition = TwoDToIso(camPosition);
        offsetX = (windowWidth / 2) - isoCamPosition.x;
        offsetY = (windowHeight / 2) - isoCamPosition.y;

        for (int i = 0; i < level.GetWidth(); i++) {
            for (int j = 0; j < level.GetHeight(); j++) {
                BufferedImage drawnImage;
                BufferedImage drawnImageBottom = null;
                switch (level.GetTile(i, j).GetType()) {
                    case WALL:
                        drawnImage = imageLoader.GetImage("Wall");
                        break;
                    case FLOOR:
                        drawnImage = imageLoader.GetImage("Floor");
                        break;
                    case WATER:
                        drawnImage = imageLoader.GetImage("Water");
                        break;
                    case PILLAR_WITH_FLOOR:
                        drawnImage = imageLoader.GetImage("Pillar");
                        drawnImageBottom = imageLoader.GetImage("Floor");
                        break;
                    case PILLAR_WITH_GRASS:
                        drawnImage = imageLoader.GetImage("Pillar");
                        drawnImageBottom = imageLoader.GetImage("Grass");
                        break;
                    case CHASM:
                        drawnImage = imageLoader.GetImage("Chasm");
                        break;
                    case PATH:
                        drawnImage = imageLoader.GetImage("Path");
                        break;
                    case GRASS:
                        drawnImage = imageLoader.GetImage("Grass");
                        break;
                    case TREE:
                        drawnImage = imageLoader.GetImage("Tree");
                        drawnImageBottom = imageLoader.GetImage("Grass");
                        break;
                    default:
                        drawnImage = imageLoader.GetImage("Chasm");
                        break;
                }
                Position isoPos = TwoDToIso(new Position(i, j));
                int tileOffset = 0;

                if (drawnImage.getHeight() > tileSizeY) {
                    tileOffset = tileSizeY * 2;
                }

                if (drawnImageBottom != null) {
                    g.drawImage(drawnImageBottom, isoPos.x + offsetX, isoPos.y + offsetY, null);
                }
                g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - tileOffset + offsetY, null);

                Item itemInTile = level.GetItemInTile(i, j);
                if (itemInTile != null) {
                    drawnImage = imageLoader.GetImage(itemInTile.GetName());
                    isoPos = TwoDToIso(new Position(i, j));
                    g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - 16 + offsetY, null);

                    guiTexts.add("Name: " + itemInTile.GetName());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY);
                    guiTexts.add("Type: " + itemInTile.GetDamageType().toString());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY + 12);
                    guiTexts.add("Power: " + itemInTile.GetPower());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY + 24);
                }

                Actor actorInTile = level.GetActorInTile(i, j);
                if (actorInTile != null) {
                    drawnImage = imageLoader.GetImage(actorInTile.GetName());
                    isoPos = TwoDToIso(new Position(i, j));
                    g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - 16 + offsetY, null);

                    if (!actorInTile.equals(player)) {
                        guiTexts.add("Health: " + actorInTile.GetHealth() + "/" + actorInTile.GetMaxHealth());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY);
                        guiTexts.add("Weak: " + actorInTile.GetWeakness());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY + 12);
                        guiTexts.add("Strong: " + actorInTile.GetStrength());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY + 24);
                        guiTexts.add("Stance: " + actorInTile.GetStance());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY + 36);
                        guiTexts.add("Pdir: " + actorInTile.GetAI().GetTargetDirection());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY + 48);
                        guiTexts.add("Stun: " + actorInTile.GetStun());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY + 60);
                        guiTexts.add("WDel: " + actorInTile.GetWeaponDelay());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY + 72);
                    }
                }
            }
        }
    }

    private void PaintGUI(Graphics g) {
        for (int i = 0; i < guiTexts.size(); i++) {
            g.drawString(guiTexts.get(i), guiPosX.get(i), guiPosY.get(i));
        }

        BufferedImage drawnImage = imageLoader.GetImage("Weapon Background");
        g.drawImage(drawnImage, windowWidth - drawnImage.getWidth(), windowHeight - drawnImage.getHeight(), null);

        drawnImage = imageLoader.GetImage("Stun Icon");
        for (int i = 0; i < player.GetStun(); i++) {
            g.drawImage(drawnImage, 30 + (i * 50), windowHeight - drawnImage.getHeight() - 80, null);
        }
        drawnImage = imageLoader.GetImage("Health Icon");
        for (int i = 0; i < player.GetHealth(); i++) {
            g.drawImage(drawnImage, 30 + (i * 50), windowHeight - drawnImage.getHeight() - 10, null);
        }
        drawnImage = imageLoader.GetImage("Weapon Delay Icon");
        for (int i = 0; i < player.GetWeaponDelay(); i++) {
            g.drawImage(drawnImage, 30 + (i * 50), windowHeight - drawnImage.getHeight() - 150, null);
        }

        if (level.IsGameOver()) {
            drawnImage = imageLoader.GetImage("Game Over");
            g.drawImage(drawnImage, (windowWidth/2)-(drawnImage.getWidth()/2), (windowHeight/2)-(drawnImage.getHeight()/2), null);
        }

        if (player.GetWeapon() != null) {
            drawnImage = imageLoader.GetImage(player.GetWeapon().GetName() + " Icon");
            g.drawImage(drawnImage, windowWidth - drawnImage.getWidth(), windowHeight - drawnImage.getHeight(), null);
        }
    }

    /**
     * Konvertoi sijainnin 2D-koordinaatistosta isometriseen koordinaatistoon.
     *
     * @param position Sijainti 2D-koordinaatistossa
     * @return Uusi sijainti isometrisessa koordinaatistossa
     */
    public Position TwoDToIso(Position position) {
        position.x = position.x * rotationX;
        position.y = position.y * rotationY;

        Position pos = new Position(0, 0);
        pos.x = (position.x - position.y) * (tileSizeX / 2);
        pos.y = (position.x + position.y) * (tileSizeY / 2);
        return pos;
    }

    /**
     * Kääntää kameraa. Hyvin kokeellinen metodi.
     *
     * @param direction Suunta
     */
    public void RotateCamera(Direction direction) {
        switch (cameraDirection) {
            case LEFT:
                if (direction == LEFT) {
                    cameraDirection = DOWN;
                } else {
                    cameraDirection = UP;
                }
                break;
            case RIGHT:
                if (direction == LEFT) {
                    cameraDirection = UP;
                } else {
                    cameraDirection = DOWN;
                }
                break;
            case UP:
                if (direction == LEFT) {
                    cameraDirection = LEFT;
                } else {
                    cameraDirection = RIGHT;
                }
                break;
            case DOWN:
                if (direction == LEFT) {
                    cameraDirection = RIGHT;
                } else {
                    cameraDirection = LEFT;
                }
                break;
        }

        switch (cameraDirection) {
            case LEFT:
                rotationX = -1;
                rotationY = 1;
                break;
            case RIGHT:
                rotationX = 1;
                rotationY = -1;
                break;
            case UP:
                rotationX = 1;
                rotationY = 1;
                break;
            case DOWN:
                rotationX = -1;
                rotationY = -1;
                break;
        }

        repaint();
    }

}
