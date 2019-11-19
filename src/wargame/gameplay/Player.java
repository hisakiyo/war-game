/**
 * Player est la classe représentant les joueurs.
 * Elle contient son identifiant, la couleur de ses armées, son score et les armées qu'il peut obtenir
 * aléatoirement
 * Elle contient la méthode qui "pioche" une armée aléatoirement et celle qui affiche son score actuel.
 * 
 * @author Tom Maillard
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
    * @param color couleur des armées du joueur
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
    //Méthodes get

    /**
    * Retourne la liste des armées du joueur
    * 
    * @return les armées du joueur
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
    * Retourne la couleur des armées du joueur
    * 
    * @return la couleur des amrlées
    */
    public Color getPlayerColor() {
        return playerColor;
    }

    /*****************/

    //Méthode set
    /**
    * @param playerColor couleur des armées du joueur
    */

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    /*****************/

    // Méthode choississant aléatoirement une armée disponible 
    // dans les armées du joueur 

    public Army getRandomArmy() {
        Random r = new Random();
        int armyId = r.nextInt(this.getArmyList().size());
        Army army = this.getArmyList().get(armyId);
        this.getArmyList().remove(armyId);
        return army;
    }

    /*****************/

    //Méthode qui parcours le terrain pour connaître le score d'un joueur
    /**
    * On initialise le score du joueur à 0. Puis on parcours le terrain en
    * largeur (Main.width) et en hauteur (Main.height) à l'aide de deux boucles.
    * On vérifie à chaque fois si l'armée appartient à quelqu'un, puis en suite
    * si elle appartient au joueur concerné. Si oui, on rajoute sa taille au score.
    * Enfin, on affiche l'identifiant du joueur couplé à son score.
    *
    * @param map terrain utilisé sur la partie en cours
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

    //Méthode equals pour comparer deux identifiants de deux armées

    public boolean equals(Player p) {
        return this.getPlayerId() == p.getPlayerId();
    }
}
