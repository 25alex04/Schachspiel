package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public abstract class Pieces {

    ImageView img;
    boolean alive = true;
    String color;
    int yPosition;
    int xPosition;
    int id;

    abstract void move();
    abstract void attack();

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
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

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getId() {
        return id;
    }
}
