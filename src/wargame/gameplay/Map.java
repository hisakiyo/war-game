package wargame.gameplay;

import wargame.gui.Tile;

public class Map {
    private Tile[][] map;

    public Map(int width, int height) {
        this.map = new Tile[height][width];
    }
    public Map(Map m){
        this.map = m.getMap();
    }

    public Map(){}

    public void setMapTile(Tile tile, int row, int col) {
        this.map[row][col] = tile;
    }

    public Tile getMapTile(int row, int col){
        return this.map[row][col];
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }
}
