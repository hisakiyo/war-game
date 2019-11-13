package wargame.gameplay;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private ArrayList<Army> armyList;
    private int playerId;
    private int score=0;
    private Color playerColor;

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


    public ArrayList<Army> getArmyList() {
        return armyList;
    }

    public void setArmyList(ArrayList<Army> armyList) {
        this.armyList = armyList;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getScore(){
        return this.score;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public void updateScore(Map map) {
        int newScore = this.getScore();
        for(int i=0; i<map.getMap().length;i++){
            for(int j=0;j<map.getMap()[i].length;j++){
                if (map.getMapTile(i,j).getArmy().getOwner() == this){
                    newScore+=map.getMapTile(i,j).getArmy().getSize();
                }
            }
        }
        this.score = newScore;
    }
    public boolean equals(Player p) {
        return this.getPlayerId() == p.getPlayerId();
    }
}
