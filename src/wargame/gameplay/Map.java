/**
 * Map est la classe representant le terrain sur lequel la partie se deroule.
 * Le terrain est sur deux dimensions, avec une hauteur et une largeur.
 * La Map represente grace a un tableau a deux dimensions de Tiles
 * @author DELFORCE Pierre
 * @version 1.0
 */

package wargame.gameplay;

import wargame.gui.Tile;

public class Map {

    //Attribut

    //Tableau a deux dimensions
    private Tile[][] map;

    /********************/

    //Constructeur
    /**
    * Constructeur
    * 
    * @param width largeur du terrain
    * @param height hauteur du terrain
    */
    public Map(int width, int height) {
        this.map = new Tile[height][width];
    }

    /********************/

    //Methode set

    public void setMapTile(Tile tile, int row, int col) {
        this.map[row][col] = tile;
    }

    /********************/

    //Methode get

    public Tile getMapTile(int row, int col){
        return this.map[row][col];
    }

}
