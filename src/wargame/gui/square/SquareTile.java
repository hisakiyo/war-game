package wargame.gui.square;

import wargame.gui.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SquareTile extends Tile {

    public SquareTile(String text, BufferedImage image) {
        super(text, image);
    }

    public SquareTile(BufferedImage image) {
        this("", image);
    }

    @Override
    protected void recomputeBorder() {
        this.getBounds(this.boundingBox);
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        this.border = new Polygon(
                new int[] {0, width, width, 0},
                new int[] {0, 0, height, height},
                4
        );
    }
}
