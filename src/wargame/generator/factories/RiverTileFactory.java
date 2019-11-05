package wargame.generator.factories;

import wargame.gui.Tile;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareTile;

import java.awt.image.BufferedImage;


public class RiverTileFactory extends TileFactory {

    private BufferedImage image;

    public RiverTileFactory(BufferedImage image){
        this.image = image;
    }

    public Tile getTile(String tileType, int col, int row){
        if(tileType.equals("HEX")){
            return new HexTile(row+" ; "+col, this.image);
        }

        else if(tileType.equals("SQUARE")){
            return new SquareTile(row+" ; "+col, this.image);
        }

        return null;
    }
}