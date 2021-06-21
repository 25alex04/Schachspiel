package de.haw_hamburg.schachspiel;

public class Feld {

    private int yPos;              //y-Position of the field
    private int xPos;              //x-Position of the field
    private boolean taken = false; //boolean whether the field is taken

    //planned constructor for field
    public Feld(int yPos, int xPos) {
        this.yPos = yPos;
        this.xPos = xPos;
    }

    //getter for y-position
    public int getyPos() {
        return yPos;
    }

    //setter for y-position
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    //getter for x-position
    public int getxPos() {
        return xPos;
    }

    //setter for x-position
    public void setxPos(int xPos) { this.xPos = xPos; }

    //method to check whether field is taken or not
    public boolean isTaken() {
        return taken;
    }

    //method to set the field status to taken
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
