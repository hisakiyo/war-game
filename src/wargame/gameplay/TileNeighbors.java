/**
* TileNeighbors est la classe responsable des voisins de chaque armées posées.
* Elle vérifie à l'aide d'un itérateur, à chaque fois qu'une armée est posée, s'il faut effectuer
* des changements. S'il faut augmenter le taille d'une armée alliée ou prendre possession d'une armée ennemi
* car l'armée posée est plus grande.
* 
* @author Tom Maillard
* @version 1.0
*/


package wargame.gameplay;

import wargame.gui.Tile;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TileNeighbors {
    //Déclaration array list
    ArrayList<Tile> neighbors;

    /**********************/

    //Méthode Get
    public ArrayList<Tile> getNeighbors() {
        return neighbors;
    }

    /**********************/

    //Méthode de traitement des armées voisines d'une armée posée
    /**
    * Si l'armée voisine est de plus petite taill eque l'armée posée et qu'elle appartient
    * au même propriétaire, sa taille augmente de 1.
    * Si l'armée voisine est de plus petite taill eque l'armée posée et que leurs propriétaires
    * sont différents, l'armée de plus petite taille est conquise par le propriétaire de l'armée qui
    * vient d'être posée.
    *
    * @param centerTile représente l'armée posée.
    * currentTile est une armée voisine de centerTile, qui est changée par l'itérateur pour vérifier
    * tout les voisins.
    *
    */
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
