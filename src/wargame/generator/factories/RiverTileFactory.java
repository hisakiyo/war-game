/**
 * Cette classe permet la generation de cases du type RIVER
 *
 * @author MAILLARD Tom
 * @version 1.0
 */

package wargame.generator.factories;

import wargame.gui.Tile;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareTile;

import java.awt.image.BufferedImage;


public class RiverTileFactory implements TileFactory {

    /**
     * Prend une image
     */
    private BufferedImage image;

    /********************/

    //Constructeur
    /**
    * Constructeur
    *
     * @param image image representant une rivière
    */
    public RiverTileFactory(BufferedImage image){
        this.image = image;
    }

    /**
     * La methode retourne une case dont la forme depend du type passe en parametre
     *
     * @param tileType Le type de case a retourner (Hexa ou Square)
     * @param col      La colonne de la case a creer
     * @param row      La ligne de la case a creer
     * @return
     */
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