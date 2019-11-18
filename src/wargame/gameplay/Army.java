package wargame.gameplay;

public class Army {
    private int size;
    private Player owner;

    public Army(int size, Player owner) {
        this.size = size;
        this.owner = owner;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}


