/**
 * Army est la classe representant les armees jouees dans le jeu.
 * Chaque armee a une taille ainsi qu'un proprietaire.
 * 
 *
 * @author DELFORCE Pierre
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
     * @param size taille de l'armee
     * @param owner proprietaire de l'armee
    */
    public Army(int size, Player owner) {
        this.size = size;
        this.owner = owner;
    }

    /********************/

    // Methodes get

    public int getSize() {
        return size;
    }

    public Player getOwner() {
        return owner;
    }

    /********************/

    // Methodes set

    public void setSize(int size) {
        this.size = size;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}

