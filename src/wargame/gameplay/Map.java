/**
 * Map est la classe représentant le terrain sur lequel la partie se déroule.
 * Le terrain est sur deux dimensions, avec une hauteur et une largeur.
 * On lui associe aussi une tuile.

 * @author Tom Maillard
 * @version 1.0
 */

package wargame.gameplay;

import wargame.gui.Tile;

public class Map {

    //Attribut

    //Tableau à deux dimensions
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

    //Méthode set

    public void setMapTile(Tile tile, int row, int col) {
        this.map[row][col] = tile;
    }

    /********************/

    //Méthode get

    public Tile getMapTile(int row, int col){
        return this.map[row][col];
    }

}
