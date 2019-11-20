/**
 * Cette classe permet la generation de cases du type GRASS
 *
 * @author MAILLARD Tom
 * @version 1.0
 */


package wargame.generator.factories;

import wargame.gui.Tile;
import wargame.gui.hex.HexTile;
import wargame.gui.square.SquareTile;

import java.awt.image.BufferedImage;

public class GrassTileFactory implements TileFactory {

    //Attribut
    private BufferedImage image;

    /********************/

    //Constructeur
    /**
    * Constructeur
    *
     * @param image image representant une plaine
    */
    public GrassTileFactory(BufferedImage image){
        this.image = image;
    }

    /********************/


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
            return new HexTile("0", this.image, Tile.GRASS, row, col);
        } else if(tileType.equals("SQUARE")){
            return new SquareTile("0", this.image, Tile.GRASS, row, col);
        }

        return null;
    }
}  