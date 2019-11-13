package wargame;

import wargame.gameplay.Army;

public class Main{

    public static Army currentArmy; // Var ""globale"" pour gérer la progression du jeu ???

    public static void main (String[] args){
        FenetreBoutonsListener f = new FenetreBoutonsListener();
        f.setVisible(true);//On la rend visible
    }
}