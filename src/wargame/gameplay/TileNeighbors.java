package wargame.gameplay;

import wargame.gui.Tile;

import java.util.ArrayList;

public abstract class TileNeighbors {
    ArrayList<Tile> neighbors;

    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    public void updateNeighbors(Tile centerTile) {

        for (Tile neighbor : this.getNeighbors()) {

            if (centerTile.getArmy().getSize() > neighbor.getArmy().getSize() && centerTile.getArmy().getOwner() == neighbor.getArmy().getOwner()) {
                neighbor.getArmy().setSize(neighbor.getArmy().getSize() + 1);
                neighbor.setTextContent("" + neighbor.getArmy().getSize());
            }
            //Gestion  du cas des armées ennemies sur une montagne
            if (centerTile.getType() == Tile.MOUNTAIN) {
                centerTile.getArmy().setSize(centerTile.getArmy().getSize() + 2);
            }
            if (neighbor.getType() == Tile.MOUNTAIN) {
                neighbor.getArmy().setSize(neighbor.getArmy().getSize() + 2);
            }
            if (centerTile.getArmy().getSize() > neighbor.getArmy().getSize() && centerTile.getArmy().getOwner() != neighbor.getArmy().getOwner()) {
                neighbor.getArmy().setOwner(centerTile.getArmy().getOwner());
                neighbor.setTextBackgroundColor(neighbor.getArmy().getOwner().getPlayerColor());
            }

            //On rétablie les valeurs normales d'armées
            if (centerTile.getType() == Tile.MOUNTAIN) {
                centerTile.getArmy().setSize(centerTile.getArmy().getSize() - 2);
            }
            if (neighbor.getType() == Tile.MOUNTAIN) {
                neighbor.getArmy().setSize(neighbor.getArmy().getSize() - 2);
            }
        }
    }
}
