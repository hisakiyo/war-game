package wargame.gameplay;

import wargame.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    public int getPlayerId() {
        return playerId;
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

    public Army getRandomArmy() {
        Random r = new Random();
        int armyId = r.nextInt(this.getArmyList().size());
        Army army = this.getArmyList().get(armyId);
        this.getArmyList().remove(armyId);
        return army;
    }



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
        System.out.println(this.playerId + ":" + newScore);
    }
    public boolean equals(Player p) {
        return this.getPlayerId() == p.getPlayerId();
    }
}
