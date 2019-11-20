package wargame.gui;

import wargame.FenetreBoutonsListener;
import wargame.Main;
import wargame.gameplay.*;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Base class of graphical tiles
 * This software is released under the GNU GPLv3 license
 *
 * @author Franck Vandewiele
 */
public abstract class Tile extends JComponent implements MouseListener {

    public static final int RIVER = 0;
    public static final int GRASS = 1;
    public static final int MOUNTAIN = 2;

    private String text;
    private int type;
    private Army army = null;
    private int row;
    private int col;
    protected BufferedImage tile;
    private BufferedImage scaledTile;
    private boolean highlightable = true;
    private Color highlightColor = new Color(255, 0, 0, 64);
    private boolean taken = false;

    protected Polygon border = new Polygon();
    protected Rectangle boundingBox = new Rectangle();
    private boolean isHighlighted = false;
    private int previousHeight = 0;
    private int previousWidth = 0;
    private Color textBackgroundColor = new Color(64, 64, 64);

    public Tile(String text, BufferedImage image, int type, int i, int j) {
        this.text = text;
        this.row = i;
        this.col = j;
        this.recomputeBorder();
        this.addMouseListener(this);
        this.setTile(image);
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        this.setFont(f);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setLayout(new FlowLayout());
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Provides a shape-specific border computation of the attribute border
     */
    protected abstract void recomputeBorder();

    @Override
    public boolean contains(Point p) {
        return border.contains(p);
    }

    @Override
    public boolean contains(int x, int y) {
        return border.contains(x, y);
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        this.recomputeBorder();
    }

    @Override
    public void setSize(int w, int h) {
        super.setSize(w, h);
        this.recomputeBorder();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        this.recomputeBorder();
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r.x, r.y, r.width, r.height);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.setHighlighted(true);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setHighlighted(false);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.placeArmy(Main.currentArmy);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    /**
     *
     * Draws additional content inside the tile (default implementation : some text in a circle)
     *
     * @param g2D Graphic context
     */
    protected void drawContents(Graphics2D g2D) {
        if (this.text.length() == 0) return;

        FontMetrics fontMetrics = getFontMetrics(this.getFont());

        Rectangle iconR = new Rectangle();

        Rectangle textR = new Rectangle();

        SwingUtilities.layoutCompoundLabel(
                null,
                fontMetrics,
                text,
                null,
                SwingUtilities.CENTER,
                SwingUtilities.CENTER,
                SwingUtilities.BOTTOM,
                SwingUtilities.CENTER,
                this.getBounds(),
                iconR,
                textR,
                0
        );

        Point loc = getLocation();

        g2D.setColor(Color.white);
        int margin = 5;
        g2D.fillOval(
                textR.x - loc.x - margin,
                textR.y - loc.y - textR.width / 2 + fontMetrics.getHeight() / 2 + this.boundingBox.height / 4 - margin,
                textR.width + 2 * margin,
                textR.width + 2 * margin
        );

        g2D.setColor(this.textBackgroundColor);
        margin = 3;
        g2D.fillOval(
                textR.x - loc.x - margin,
                textR.y - loc.y - textR.width / 2 + fontMetrics.getHeight() / 2 + this.boundingBox.height / 4 - margin,
                textR.width + 2 * margin,
                textR.width + 2 * margin
        );

        g2D.setColor(Color.white);

        g2D.drawString(
                text,
                textR.x - loc.x,
                textR.y - loc.y + fontMetrics.getAscent() + this.boundingBox.height / 4
        );
    }

    private void rescaleTile() {
        AffineTransform transform = AffineTransform.getScaleInstance(((double) this.boundingBox.width) / this.tile.getWidth(), ((double) this.boundingBox.height) / this.tile.getHeight());
        BufferedImage scaled = new BufferedImage(this.boundingBox.width, this.boundingBox.height, BufferedImage.TYPE_INT_ARGB);
        AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

        this.scaledTile = scaleOp.filter(this.tile, scaled);
        this.scaledTile = new BufferedImage(this.boundingBox.width, this.boundingBox.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2D = this.scaledTile.createGraphics();
        g2D.setClip(this.border);
        g2D.drawImage(scaled, 0, 0, null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) (g.create());
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (this.previousHeight != this.boundingBox.height || this.previousWidth != this.boundingBox.width) {
            this.rescaleTile();
            this.previousWidth = this.boundingBox.width;
            this.previousHeight = this.boundingBox.height;
        }

        g2D.drawImage(this.scaledTile, 0, 0, null);

        this.drawContents(g2D);

        // highlights the tile if hovered by mouse
        if (this.isHighlighted && this.highlightable) {
            g2D.setColor(this.highlightColor);
            g2D.fillPolygon(this.border);
        }

        // draw border
        g2D.setColor(Color.black);
        g2D.drawPolygon(border);

        g2D.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // no border, thanks
    }

    /**
     * Changes the color used to render the circle around the (text) content
     * @param color
     */
    public void setTextBackgroundColor(Color color) {
        this.textBackgroundColor = color;
    }

    /**
     * sets if this tile is highlightable
     * @param highlightable
     */
    public void setHighlightable(boolean highlightable) {
        this.highlightable = highlightable;
    }

    /**
     * sets the color used to render a highlighted tile. Should be a RGBA color
     * @param color the new (RGBA) color
     */
    public void setHighlightColor(Color color) {
        this.highlightColor = color;
    }

    /**
     * forces a tile to be highlighted or not
     * @param highlighted
     */
    public void setHighlighted(boolean highlighted) {
        this.isHighlighted = highlighted;
        this.repaint();
    }

    /**
     * sets new text content for the tile
     * @param text
     */
    public void setTextContent(String text) {
        this.text = text;
        this.invalidate();
        this.repaint();
    }

    /**
     * sets a new image background for the tile
     * @param image L'image servant de background pour la Tile
     */
    public void setTile(BufferedImage image) {
        this.tile = image;
        this.previousHeight = -1;
        this.previousWidth = -1;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
    public Army getArmy() {
        return this.army;
    }

    /**
     * Place une armee sur la Map et passe au tour du joueur suivant
     *
     * @param army L'armee a placer
     */
    public void placeArmy(Army army) {
        if (!this.taken) {
            if (this.getType() != RIVER) {
                if (this.getType() == MOUNTAIN) {
                    if (army.getSize() > 3) {
                        army.setSize(3);
                    }
                }
                this.setArmy(army);
                this.setTextBackgroundColor(army.getOwner().getPlayerColor());
                this.text = "" + army.getSize();
                this.setTaken(true);
                if (this instanceof SquareTile) {
                    TileNeighbors neighborhood = new SquareTileNeighbors(Main.gameMap, this.getRow(), this.getCol());
                    neighborhood.updateNeighbors(this);
                }
                if (this instanceof HexTile) {
                    TileNeighbors neighborhood = new HexTileNeighbors(Main.gameMap, this.getRow(), this.getCol());
                    neighborhood.updateNeighbors(this);
                }
                if (Main.iterPlayer.hasNext()) {
                    Main.currentPlayer = (Player) Main.iterPlayer.next();
                    Main.currentArmy = Main.currentPlayer.getRandomArmy();
                } else {
                    Main.iterPlayer = Main.playerQueue.iterator();
                    Main.currentPlayer = (Player) Main.iterPlayer.next();
                    Main.currentArmy = Main.currentPlayer.getRandomArmy();
                }

                for (Player p : Main.playerQueue) {
                    p.updateScore(Main.gameMap);
                    FenetreBoutonsListener.update_score(p.getScore(), p.getPlayerId() - 1, p.getArmyList().isEmpty());
                }

            }
        }
        this.repaint();
    }

    @Override
    public String toString() {
        return "Tile{" +
                "type=" + type +
                ", row=" + row +
                ", col=" + col +
                ", taken=" + taken +
                '}';
    }
}


