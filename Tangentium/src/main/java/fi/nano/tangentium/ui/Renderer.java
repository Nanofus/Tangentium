package fi.nano.tangentium.ui;

import fi.nano.tangentium.gameLogic.Level;
import fi.nano.tangentium.gameLogic.Position;
import fi.nano.tangentium.gameLogic.entities.Actor;
import fi.nano.tangentium.gameLogic.entities.Item;
import fi.nano.tangentium.gameLogic.enums.Direction;
import static fi.nano.tangentium.gameLogic.enums.Direction.*;
import fi.nano.tangentium.gameLogic.enums.TileType;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Draws the UI, Level, Actors and Items.
 *
 * @author Nanofus
 */
public class Renderer extends JPanel {

    private final int tileSizeX;
    private final int tileSizeY;

    private final int actorSizeX;
    private final int actorSizeY;

    private Level level;
    private Actor player;

    private boolean gameWon = false;

    private final ImageLoader imageLoader;

    private int windowWidth;
    private int windowHeight;
    private int offsetX;
    private int offsetY;

    private ArrayList<String> guiTexts;
    private ArrayList<Integer> guiPosX;
    private ArrayList<Integer> guiPosY;
    private ArrayList<Boolean> isWhite;

    private Direction cameraDirection = UP;
    private int rotationX = 1;
    private int rotationY = 1;

    private final int repaintDelay = 10;

    public Renderer(ImageLoader imageLoader, Level level, Actor player, int windowWidth, int windowHeight) {

        setOpaque(true);
        setBackground(Color.BLACK);

        this.imageLoader = imageLoader;

        this.level = level;
        this.player = player;

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        tileSizeX = imageLoader.GetImage("Floor").getWidth();
        tileSizeY = imageLoader.GetImage("Floor").getHeight();

        actorSizeX = imageLoader.GetImage("Player").getWidth();
        actorSizeY = imageLoader.GetImage("Player").getHeight();

        StartTimer();
    }

    private void StartTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("asd");
                repaint();
            }
        };
        new Timer(repaintDelay, taskPerformer).start();
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
        isWhite = new ArrayList();

        PaintLevel(g);
        PaintGUI(g);
    }

    private void PaintLevel(Graphics g) {
        Position camPosition = new Position(level.GetPlayer().GetPosition().x, level.GetPlayer().GetPosition().y);
        Position isoCamPosition = TwoDToIso(camPosition);
        offsetX = (windowWidth / 2) - isoCamPosition.x;
        offsetY = (windowHeight / 2) - isoCamPosition.y;

        DrawGuide(g);
        DrawTileContents(g);
    }

    private void DrawGuide(Graphics g) {
        BufferedImage drawnImage;

        // Tutoriaalitekstit
        if (level.GetGame().GetLevelID() == 0) {
            drawnImage = imageLoader.GetImage("Logo");
            g.drawImage(drawnImage, offsetX - 360, offsetY - 300, null);

            int guiX = 340;
            int guiY = 180;
            int lineHeight = 12;

            guiTexts.add("Controls");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY);
            isWhite.add(true);
            guiTexts.add("Arrows or WASD to move");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight);
            isWhite.add(true);
            guiTexts.add("Space to pick up a weapon / use item / use a lever / use a goal circle");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 2);
            isWhite.add(true);

            guiTexts.add("User interface");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 4);
            isWhite.add(true);
            guiTexts.add("Brown square: equipped weapon");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 5);
            isWhite.add(true);
            guiTexts.add("Hearts: health");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 6);
            isWhite.add(true);
            guiTexts.add("Yellow stars: character's remaining stun (can't move while active) in turns");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 7);
            isWhite.add(true);
            guiTexts.add("Green bars: character's remaining attack cooldown (can't attack while active)");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 8);
            isWhite.add(true);

            guiTexts.add("Items");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 4);
            isWhite.add(true);
            guiTexts.add("Sword - Slash-type weapon");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 5);
            isWhite.add(true);
            guiTexts.add("Spear - Pierce-type weapon");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 6);
            isWhite.add(true);
            guiTexts.add("Mace - Crush-type weapon");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 7);
            isWhite.add(true);
            guiTexts.add("Pyrospell - Burn-type weapon");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 8);
            isWhite.add(true);
            guiTexts.add("Ice Staff - Freeze-type weapon");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 9);
            isWhite.add(true);
            guiTexts.add("Wand - Arcane-type weapon");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 10);
            isWhite.add(true);
            guiTexts.add("Health Potion - Hearts restored by power value");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 11);
            isWhite.add(true);
            guiTexts.add("Buff Potion - Equipped item's power is permanently increased by power value");
            guiPosX.add(offsetX - guiX + 500);
            guiPosY.add(offsetY - guiY + lineHeight * 12);
            isWhite.add(true);

            guiTexts.add("Tactics");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 10);
            isWhite.add(true);
            guiTexts.add("If you have a weapon, bump into enemies to attack them");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 11);
            isWhite.add(true);
            guiTexts.add("But beware - they strike back if you stay too close!");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 12);
            isWhite.add(true);
            guiTexts.add("Your weapon has to be the right type, too. Pick a weapon");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 13);
            isWhite.add(true);
            guiTexts.add("that is a type the enemy is weak to.");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 14);
            isWhite.add(true);
            guiTexts.add("Or at least not resistant!");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 15);
            isWhite.add(true);
            guiTexts.add("Taking damage stuns for three turns");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 16);
            isWhite.add(true);
            guiTexts.add("but the attacker has to wait four turns");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 17);
            isWhite.add(true);
            guiTexts.add("until they can attack again.");
            guiPosX.add(offsetX - guiX);
            guiPosY.add(offsetY - guiY + lineHeight * 18);
            isWhite.add(true);
        }
    }

    private void DrawTileContents(Graphics g) {
        BufferedImage drawnImage;

        // Tilet sisältöineen
        for (int i = 0; i < level.GetWidth(); i++) {
            for (int j = 0; j < level.GetHeight(); j++) {
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
                    case DOOR_LEFT:
                        drawnImage = imageLoader.GetImage("Door Left");
                        drawnImageBottom = imageLoader.GetImage("Floor");
                        break;
                    case DOOR_RIGHT:
                        drawnImage = imageLoader.GetImage("Door Right");
                        drawnImageBottom = imageLoader.GetImage("Floor");
                        break;
                    case LEVER_ON_FLOOR:
                        drawnImage = imageLoader.GetImage("Lever");
                        drawnImageBottom = imageLoader.GetImage("Floor");
                        break;
                    case WINCIRCLE:
                        drawnImage = imageLoader.GetImage("Win Circle");
                        drawnImageBottom = imageLoader.GetImage("Floor");
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

                TileType tileType = level.GetTile(i, j).GetType();
                if (tileType.is(TileType.LEVER)) {
                    guiTexts.add("Lever");
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY);
                    isWhite.add(false);
                    guiTexts.add("Use to open a door");
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY + 12);
                    isWhite.add(false);
                    guiTexts.add("Active: " + level.GetTile(i, j).IsActive());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY + 24);
                    isWhite.add(false);
                }

                Item itemInTile = level.GetItemInTile(i, j);
                if (itemInTile != null) {
                    drawnImage = imageLoader.GetImage(itemInTile.GetName());
                    isoPos = TwoDToIso(new Position(i, j));
                    g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y + offsetY, null);

                    guiTexts.add(itemInTile.GetName());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY);
                    isWhite.add(false);
                    guiTexts.add("Type: " + itemInTile.GetDamageType().toString());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY + 12);
                    isWhite.add(false);
                    guiTexts.add("Power: " + itemInTile.GetPower());
                    guiPosX.add(isoPos.x + tileSizeX + offsetX);
                    guiPosY.add(isoPos.y + offsetY + 24);
                    isWhite.add(false);
                }

                Actor actorInTile = level.GetActorInTile(i, j);
                if (actorInTile != null) {
                    drawnImage = imageLoader.GetImage(actorInTile.GetName());
                    isoPos = TwoDToIso(new Position(i, j));
                    g.drawImage(drawnImage, isoPos.x + offsetX, isoPos.y - 64 + offsetY, null);

                    drawnImage = imageLoader.GetImage("Stun Icon");
                    if (actorInTile.GetStun() > 0) {
                        g.drawImage(drawnImage, isoPos.x + offsetX + tileSizeX / 2 - drawnImage.getWidth() / 2 - 6, isoPos.y - 68 + offsetY, null);
                    }
                    if (actorInTile.GetStun() > 1) {
                        g.drawImage(drawnImage, isoPos.x + offsetX + tileSizeX / 2 - drawnImage.getWidth() / 2, isoPos.y - 74 + offsetY, null);
                    }
                    if (actorInTile.GetStun() > 2) {
                        g.drawImage(drawnImage, isoPos.x + offsetX + tileSizeX / 2 - drawnImage.getWidth() / 2 + 6, isoPos.y - 68 + offsetY, null);
                    }

                    drawnImage = imageLoader.GetImage("Weapon Delay Icon");
                    if (actorInTile.GetWeaponDelay() > 0) {
                        g.drawImage(drawnImage, isoPos.x + offsetX + tileSizeX / 2 - drawnImage.getWidth() / 2 - 10, isoPos.y - 58 + offsetY, null);
                    }
                    if (actorInTile.GetWeaponDelay() > 1) {
                        g.drawImage(drawnImage, isoPos.x + offsetX + tileSizeX / 2 - drawnImage.getWidth() / 2, isoPos.y - 58 + offsetY, null);
                    }
                    if (actorInTile.GetWeaponDelay() > 2) {
                        g.drawImage(drawnImage, isoPos.x + offsetX + tileSizeX / 2 - drawnImage.getWidth() / 2 + 10, isoPos.y - 58 + offsetY, null);
                    }

                    if (!actorInTile.equals(player)) {
                        guiTexts.add(actorInTile.GetName());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY - 16 - 12);
                        isWhite.add(false);
                        guiTexts.add("Health: " + actorInTile.GetHealth() + "/" + actorInTile.GetMaxHealth());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY - 16);
                        isWhite.add(false);
                        guiTexts.add("Weak: " + actorInTile.GetWeakness());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY - 16 + 12);
                        isWhite.add(false);
                        guiTexts.add("Resistant: " + actorInTile.GetStrength());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY - 16 + 24);
                        isWhite.add(false);
                        guiTexts.add("Stance: " + actorInTile.GetStance());
                        guiPosX.add(isoPos.x + tileSizeX + offsetX);
                        guiPosY.add(isoPos.y + offsetY - 16 + 36);
                        isWhite.add(false);
                        /* guiTexts.add("Pdir: " + actorInTile.GetAI().GetTargetDirection());
                         guiPosX.add(isoPos.x + tileSizeX + offsetX);
                         guiPosY.add(isoPos.y + offsetY + 48);
                         guiTexts.add("Stun: " + actorInTile.GetStun());
                         guiPosX.add(isoPos.x + tileSizeX + offsetX);
                         guiPosY.add(isoPos.y + offsetY + 60);
                         guiTexts.add("WDel: " + actorInTile.GetWeaponDelay());
                         guiPosX.add(isoPos.x + tileSizeX + offsetX);
                         guiPosY.add(isoPos.y + offsetY + 72);*/
                    }
                }
            }
        }
    }

    private void PaintGUI(Graphics g) {

        //UI texts
        for (int i = 0; i < guiTexts.size(); i++) {
            if (isWhite.get(i) == true) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.DARK_GRAY);
            }

            switch (guiTexts.get(i)) {
                case "Sword":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Spear":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Mace":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Pyrospell":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Ice Staff":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Wand":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;

                case "Health Potion":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Buff Potion":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;

                case "Skeleton":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Troll":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Lizard Man":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;
                case "Dragon":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;

                case "Lever":
                    g.setFont(new Font("default", Font.BOLD, 11));
                    break;

                case "Controls":
                    g.setFont(new Font("default", Font.BOLD, 12));
                    break;
                case "User interface":
                    g.setFont(new Font("default", Font.BOLD, 12));
                    break;
                case "Tactics":
                    g.setFont(new Font("default", Font.BOLD, 12));
                    break;
                case "Items":
                    g.setFont(new Font("default", Font.BOLD, 12));
                    break;

                default:
                    g.setFont(new Font("default", Font.PLAIN, 11));
                    break;
            }
            g.drawString(guiTexts.get(i), guiPosX.get(i), guiPosY.get(i));
        }

        // UI icons
        BufferedImage drawnImage = imageLoader.GetImage("Weapon Background");
        g.drawImage(drawnImage, windowWidth - drawnImage.getWidth(), windowHeight - drawnImage.getHeight(), null);

        drawnImage = imageLoader.GetImage("Health Icon Background");
        for (int i = 0; i < player.GetMaxHealth(); i++) {
            g.drawImage(drawnImage, 30 + (i * 50), windowHeight - drawnImage.getHeight() - 10, null);
        }

        drawnImage = imageLoader.GetImage("Health Icon");
        for (int i = 0; i < player.GetHealth(); i++) {
            g.drawImage(drawnImage, 30 + (i * 50), windowHeight - drawnImage.getHeight() - 10, null);
        }

        // Victory and Game Over texts
        if (level.IsGameOver() && !gameWon) {
            drawnImage = imageLoader.GetImage("Game Over");
            g.drawImage(drawnImage, (windowWidth / 2) - (drawnImage.getWidth() / 2), (windowHeight / 2) - (drawnImage.getHeight() / 2), null);
        }

        if (gameWon) {
            drawnImage = imageLoader.GetImage("Victory");
            g.drawImage(drawnImage, (windowWidth / 2) - (drawnImage.getWidth() / 2), (windowHeight / 2) - (drawnImage.getHeight() / 2), null);
        }

        //Texts in the player's UI:
        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 12));

        g.drawString("Health: " + player.GetHealth() + "/" + player.GetMaxHealth(), 30, windowHeight - 75);

        if (player.GetWeapon() != null) {
            drawnImage = imageLoader.GetImage(player.GetWeapon().GetName() + " Icon");
            g.drawImage(drawnImage, windowWidth - drawnImage.getWidth(), windowHeight - drawnImage.getHeight(), null);
            g.drawString("Weapon power: " + player.GetWeapon().GetPower(), windowWidth - drawnImage.getWidth() - 110, windowHeight - 10);
        }
    }

    /**
     * Converts a 2D position into an isometric position
     *
     * @param position Position in 2D coordinates
     * @return New position in isometric coordinates.
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
     * Refreshes the UI if a new Level is loaded.
     *
     * @param level New level
     * @param player New player
     */
    public void NewLevel(Level level, Actor player) {
        this.level = level;
        this.player = player;
    }

    /**
     * Sets the game as won.
     */
    public void GameWon() {
        gameWon = true;
    }

    /**
     * Rotates the camera. An experimental method.
     *
     * @param direction Direction
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
