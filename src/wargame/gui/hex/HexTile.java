package wargame.gui.hex;

import wargame.gui.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Defines a Hexagonal tile, meant to be used with a HexLayout
 * Basedased on keang's Hex button package http://www.keang.co.uk/hex.php
 *
 * @author Franck Vandewiele
 */
public class HexTile extends Tile {
    private static final long serialVersionUID = 42L;


    public HexTile(String text, BufferedImage image, int type, int row, int col) {
        super(text, image, type, row, col);
    }

    @Override
    protected void recomputeBorder() {
        this.getBounds(this.boundingBox);

        int width = getWidth() - 1;
        int height = getHeight() - 1;

        int ratio = height / 4;

        int[] hexX = new int[6];
        int[] hexY = new int[6];

        computeCoords(width, height, ratio, hexX, hexY);

        this.border = new Polygon(hexX, hexY, 6);
    }

    private void computeCoords(int width, int height, int ratio, int[] hexX, int[] hexY) {
        hexX[0] = width / 2;
        hexY[0] = 0;

        hexX[1] = width;
        hexY[1] = ratio;

        hexX[2] = width;
        hexY[2] = height - ratio;

        hexX[3] = width / 2;
        hexY[3] = height;

        hexX[4] = 0;
        hexY[4] = height - ratio;

        hexX[5] = 0;
        hexY[5] = ratio;
    }
}