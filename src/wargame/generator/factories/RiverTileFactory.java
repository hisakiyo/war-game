/**
 * MountainTileFactory est la classe représentant une zone de rivière sur le terrain de jeu.
 * On lui associe une représentation graphique, ainsi qu'une forme en fonction du type de
 * tuile du terrain.
 *
 * @author Tom Maillard
 * @version 1.0
 */

package wargame.generator.factories;

import wargame.gui.Tile;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareTile;

import java.awt.image.BufferedImage;


public class RiverTileFactory extends TileFactory {

    //Attribut
    private BufferedImage image;

    /********************/

    //Constructeur
    /**
    * Constructeur
    * 
    * @param image image représentant une rivière
    */
    public RiverTileFactory(BufferedImage image){
        this.image = image;
    }

    /********************/
    
    //Méthode get 

    public Tile getTile(String tileType, int col, int row){
        if(tileType.equals("HEX")){
            return new HexTile("0", this.image, Tile.RIVER, row, col);
        }

        else if(tileType.equals("SQUARE")){
            return new SquareTile("0", this.image, Tile.RIVER, row, col);
        }

        return null;
    }
}