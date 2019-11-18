package wargame.gameplay;

import wargame.Main;

import java.util.ArrayList;

public class SquareTileNeighbors extends TileNeighbors {

    public SquareTileNeighbors(Map map, int row, int col){
        neighbors = new ArrayList<>();
        if (row - 1 >= 0) {
            if (map.getMapTile(row - 1, col).isTaken()) {
                neighbors.add(map.getMapTile(row - 1, col));
            }
        }
        if (row + 1 < Main.height) {
            if (map.getMapTile(row + 1, col).isTaken()) {
                neighbors.add(map.getMapTile(row + 1, col));
            }
        }
        if (col - 1 >= 0) {
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
