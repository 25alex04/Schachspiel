package de.haw_hamburg.schachspiel;

public abstract class Pieces {

    String img;
    boolean alive = true;
    String color;
    int yPosition;
    int xPosition;
    int id;

    abstract void move();
    abstract void attack();

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
