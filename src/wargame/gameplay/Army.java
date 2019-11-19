/**
 * Army est la classe représentant les armées jouées dans le jeu.
 * Chaque armée a une taille ainsi qu'un propriétaire.
 * 
 *
 * @author Tom Maillard
 * @version 1.0
 */

package wargame.gameplay;

public class Army {

    //Attributs
    private int size;
    private Player owner;

    /********************/

    //Constructeur
    /**
    * Constructeur
    * 
    * @param size taille de l'armée
    * @param owner propriétaire de l'armée
    */
    public Army(int size, Player owner) {
        this.size = size;
        this.owner = owner;
    }

    /********************/

    // Méthodes get 

    public int getSize() {
        return size;
    }

    public Player getOwner() {
        return owner;
    }

    /********************/

    // Méthodes set

    public void setSize(int size) {
        this.size = size;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}

