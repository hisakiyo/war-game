package wargame.gameplay;

import wargame.gui.Tile;

import java.util.ArrayList;

public class SquareTileNeighbors extends TileNeighbors {
    ArrayList<Tile> neighbors;

    public SquareTileNeighbors(Map map, int row, int col){
        neighbors = new ArrayList<Tile>();
        if (row-1>0){
            neighbors.add(map.getMapTile(row-1,col));
        }
        if (row+1<map.getMap().length) {
            neighbors.add(map.getMapTile(row+1,col));
        }
        if (col-1<0){
            neighbors.add(map.getMapTile(row, col-1));
        }
        if (col+1<map.getMap()[0].length){
            neighbors.add(map.getMapTile(row, col+1));
        }
    }


}
