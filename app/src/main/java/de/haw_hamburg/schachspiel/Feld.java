package de.haw_hamburg.schachspiel;

public class Feld {

    int yPos;
    int xPos;
    boolean taken = false;

    public Feld(int yPos, int xPos) {
        this.yPos = yPos;
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
