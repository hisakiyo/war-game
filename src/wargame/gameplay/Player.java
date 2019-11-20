/**
 * Player est la classe representant les joueurs.
 * Elle contient son identifiant, la couleur de ses armees, son score et les armees qu'il peut obtenir
 * aleatoirement
 * Elle contient la methode qui "pioche" une armee aleatoirement et celle qui affiche son score actuel.
 *
 * @author DELFORCE Pierre
 * @version 1.0
 */


package wargame.gameplay;

import wargame.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    //Attributs
    private ArrayList<Army> armyList;
    private int playerId;
    private int score=0;
    private Color playerColor;

    
    /********************/

    
    //Constructeur

    /**
    * Constructeur
    * 
    * @param id identifiant du joueur
     * @param color couleur des armees du joueur
    */

    public Player(int id, Color color){
        this.playerId = id;
        armyList = new ArrayList<Army>();
        armyList.add(new Army(1,this));
        armyList.add(new Army(1,this));
        armyList.add(new Army(2,this));
        armyList.add(new Army(2,this));
        armyList.add(new Army(3,this));
        armyList.add(new Army(3,this));
        armyList.add(new Army(4,this));
        armyList.add(new Army(4,this));
        armyList.add(new Army(5,this));
        armyList.add(new Army(5,this));
        this.playerColor = color;
    }


    /*****************/
    //Methodes get

    /**
     * Retourne la liste des armees du joueur
    *
     * @return les armees du joueur
    */
    public ArrayList<Army> getArmyList() {
        return armyList;
    }

    /**
    * Retourne l'identifiant du joueur
    * 
    * @return l'id du joueur
    */
    public int getPlayerId() {
        return playerId;
    }

    /**
    * Retourne le score actuel du joueur
    * 
    * @return le score du joueur
    */
    public int getScore(){
        return this.score;
    }

    /**
     * Retourne la couleur des armees du joueur
    *
     * @return la couleur des amrlees
    */
    public Color getPlayerColor() {
        return playerColor;
    }

    /*****************/

    //Methode set
    /**
     * @param playerColor couleur des armees du joueur
    */

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    /*****************/

    /**
     * @return Une armee aleatoire
     */
    public Army getRandomArmy() {
        Random r = new Random();
        int armyId = r.nextInt(this.getArmyList().size());
        Army army = this.getArmyList().get(armyId);
        this.getArmyList().remove(armyId);
        return army;
    }

    /*****************/

    //Methode qui parcours le terrain pour connaître le score d'un joueur

    /**
    * On initialise le score du joueur a 0. Puis on parcours le terrain en
     * largeur (Main.width) et en hauteur (Main.height) a l'aide de deux boucles.
     * On verifie a chaque fois si l'armee appartient a quelqu'un, puis en suite
     * si elle appartient au joueur concerne. Si oui, on rajoute sa taille au score.
     * Enfin, on affiche l'identifiant du joueur couple a son score.
     *
     * @param map terrain utilise sur la partie en cours
     *
     */


    public void updateScore(Map map) {
        int newScore = 0;
        for (int i = 0; i < Main.width; i++) {
            for (int j = 0; j < Main.height; j++) {
                if (map.getMapTile(i, j).isTaken()) {
                    if (map.getMapTile(i, j).getArmy().getOwner().equals(this)) {
                        newScore += map.getMapTile(i, j).getArmy().getSize();
                    }
                }
            }
        }
        this.score = newScore;
    }

    /*****************/

    //Methode equals pour comparer deux identifiants de deux armees
    public boolean equals(Player p) {
        return this.getPlayerId() == p.getPlayerId();
    }
}
