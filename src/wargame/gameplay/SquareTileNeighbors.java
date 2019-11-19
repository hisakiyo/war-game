/**
* SquareTileNeighbors est la classe qui s'occupe de trouver les voisins sur le terrain 
* quand les tuiles ont la forme "carrée".
* 
* @author Tom Maillard
* @version 1.0
*/

package wargame.gameplay;

import wargame.Main;
import wargame.gui.Tile;

import java.util.ArrayList;

public class SquareTileNeighbors extends TileNeighbors {

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
        System.out.println(neighbors);
    }


}
