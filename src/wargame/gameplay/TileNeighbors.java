package wargame.gameplay;

import wargame.gui.Tile;

import java.util.ArrayList;

/**
 * TileNeighbors est la classe responsable des voisins de chaque armees posees.
 * Elle verifie a l'aide d'un iterateur, a chaque fois qu'une armee est posee, s'il faut effectuer
 * des changements. S'il faut augmenter le taille d'une armee alliee ou prendre possession d'une armee ennemi
 * car l'armee posee est plus grande.
 *
 * @author Tom Maillard
 * @version 1.0
 */
public abstract class TileNeighbors {
    //Declaration array list
    ArrayList<Tile> neighbors;

    //Methode Get
    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }


    //Methode de traitement des armees voisines d'une armee posee

    /**
     * Si l'armee voisine est de plus petite taill eque l'armee posee et qu'elle appartient
     * au même proprietaire, sa taille augmente de 1.
     * Si l'armee voisine est de plus petite taill eque l'armee posee et que leurs proprietaires
     * sont differents, l'armee de plus petite taille est conquise par le proprietaire de l'armee qui
     * vient d'être posee.
     *
     * @param centerTile represente l'armee posee.
     *                   currentTile est une armee voisine de centerTile, qui est changee par l'iterateur pour verifier
     *                   tout les voisins.
     */
    public void updateNeighbors(Tile centerTile) {
        for (Tile neighbor : this.getNeighbors()) {

            if (centerTile.getArmy().getSize() > neighbor.getArmy().getSize() && centerTile.getArmy().getOwner() == neighbor.getArmy().getOwner()) {
                neighbor.getArmy().setSize(neighbor.getArmy().getSize() + 1);
                neighbor.setTextContent("" + neighbor.getArmy().getSize());
                neighbor.repaint();
            }
            //Gestion  du cas des armees ennemies sur une montagne
            if (centerTile.getType() == Tile.MOUNTAIN) {
                centerTile.getArmy().setSize(centerTile.getArmy().getSize() + 2);
            }
            if (neighbor.getType() == Tile.MOUNTAIN) {
                neighbor.getArmy().setSize(neighbor.getArmy().getSize() + 2);
            }
            if (centerTile.getArmy().getSize() > neighbor.getArmy().getSize() && centerTile.getArmy().getOwner() != neighbor.getArmy().getOwner()) {
                neighbor.getArmy().setOwner(centerTile.getArmy().getOwner());
                neighbor.setTextBackgroundColor(neighbor.getArmy().getOwner().getPlayerColor());
                neighbor.repaint();
            }

            //On retablie les valeurs normales d'armees
            if (centerTile.getType() == Tile.MOUNTAIN) {
                centerTile.getArmy().setSize(centerTile.getArmy().getSize() - 2);
            }
            if (neighbor.getType() == Tile.MOUNTAIN) {
                neighbor.getArmy().setSize(neighbor.getArmy().getSize() - 2);
            }
        }
    }
}
