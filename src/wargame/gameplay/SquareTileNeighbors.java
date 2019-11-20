/**
 * SquareTileNeighbors est la classe qui s'occupe de trouver les voisins pris par une armee sur le terrain
 * quand les tuiles ont la forme "carree".
* 
* @author Tom Maillard
* @version 1.0
*/

package wargame.gameplay;

import wargame.Main;
import wargame.gui.Tile;

import java.util.ArrayList;

public class SquareTileNeighbors extends TileNeighbors {

    /**
     * On cree un voisinnage d'un case donnee en verifiant si les coordonnes des cases adjacantes a cette case sont possibles (donc ne sorte pas de la carte) et si les dites cases sont prises ou non
     *
     * @param map La carte associee a la partie en cours
     * @param row La ligne de la case dont on va creer le voisinnage
     * @param col La colonne de la ligne dont on cree le voisinnage
     */
    public SquareTileNeighbors(Map map, int row, int col){
        neighbors = new ArrayList<Tile>();
        if (row-1>0){
            if (map.getMapTile(row - 1, col).isTaken()) {
                neighbors.add(map.getMapTile(row - 1, col));
            }
        }
        if (row + 1 < Main.height) {
            if (map.getMapTile(row + 1, col).isTaken()) {
                neighbors.add(map.getMapTile(row + 1, col));
            }
        }
        if (col - 1 > 0) {
            if (map.getMapTile(row, col - 1).isTaken()) {
                neighbors.add(map.getMapTile(row, col - 1));
            }
        }
        if (col + 1 < Main.width) {
            if (map.getMapTile(row, col + 1).isTaken()) {
                neighbors.add(map.getMapTile(row, col + 1));
            }
        }
    }


}
