package wargame;

import wargame.gameplay.Map;

class Main{
    public static void main (String[] args){
        FenetreBoutonsListener f = new FenetreBoutonsListener();
        f.setVisible(true);//On la rend visible
        Map map = f.getMap();
    }
}