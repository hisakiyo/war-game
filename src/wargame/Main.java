/**
 * Main est le programme principal. Les joueurs ainsi que tout les elements permettant le deroulement
 * du jeu sont declares ici ( fenêtre, iterateur, armees...)
 * 
 *
 * @author MAILLARD Tom & MARETTE Gauthier
 * @version 1.0
 */


package wargame;

import wargame.gameplay.Army;
import wargame.gameplay.Map;
import wargame.gameplay.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Main{

    public static Army currentArmy;
    public static Player currentPlayer;
    public static ArrayList<Player> playerQueue = new ArrayList<>();
    public static Iterator iterPlayer;
    public static Map gameMap;
    public static int width = 10;
    public static int height = 10;

    public static void main (String[] args){
        Main.playerQueue.add(new Player(1, Color.RED));
        Main.playerQueue.add(new Player(2, Color.BLUE));
        Main.playerQueue.add(new Player(3, Color.GREEN));
        Main.playerQueue.add(new Player(4, Color.MAGENTA));
        iterPlayer = playerQueue.iterator();
        Main.currentPlayer = (Player) iterPlayer.next();
        currentArmy = currentPlayer.getRandomArmy();

        FenetreBoutonsListener f = new FenetreBoutonsListener();
        f.setVisible(true);//On la rend visible
    }
}