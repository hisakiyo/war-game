package wargame.generator.factories;

import wargame.gui.Tile;


public abstract class TileFactory {
    public abstract Tile getTile(String tileType, int col, int row);
}
