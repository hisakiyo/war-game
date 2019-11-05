package wargame.generator.factories;

import wargame.gui.Tile;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareTile;

import java.awt.image.BufferedImage;


public class RiverTileFactory extends TileFactory {

    private BufferedImage image;

    RiverTileFactory(BufferedImage image){
        this.image = image;
    }

    public Tile getTile(String tileType, int col, int row){
        if(tileType.equals("HEX")){
            return new HexTile(col+" ; "+row, this.image);
        }

        else if(tileType.equals("SQUARE")){
            return new SquareTile(col+" ; "+row, this.image);
        }

        return null;
    }
}
