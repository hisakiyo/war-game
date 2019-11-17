package wargame.gameplay;

import wargame.gui.Tile;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TileNeighbors {
    ArrayList<Tile> neighbors;

    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    public void updateNeighbors(Tile centerTile) {
        Tile currentTile = null;
        Iterator<Tile> iterator = this.getNeighbors().iterator();
        while(iterator.hasNext()){
            currentTile = iterator.next();
            if (centerTile.getArmy().getSize() > currentTile.getArmy().getSize() && centerTile.getArmy().getOwner() == currentTile.getArmy().getOwner()) {
                currentTile.getArmy().setSize(currentTile.getArmy().getSize() + 1);
                currentTile.setTextContent("" + currentTile.getArmy().getSize());
            }
            if(centerTile.getArmy().getSize() > currentTile.getArmy().getSize() && centerTile.getArmy().getOwner() != currentTile.getArmy().getOwner()){
                currentTile.getArmy().setOwner(centerTile.getArmy().getOwner());
                currentTile.setTextBackgroundColor(currentTile.getArmy().getOwner().getPlayerColor());
            }
        }
    }
}
