package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public abstract class Pieces {

    private ImageView img;          //ImageView of the piece
    private boolean alive = true;   //boolean whether the piece is alive
    private String color;           //string color to differentiate
    private int yPosition;          //coordinates on board
    private int xPosition;          //      -||-
    private int id;                 //id to find piece in list

    //planned move()- and attack()-method
    abstract void move();
    abstract void attack();

    //getter for ImageView
    public ImageView getImg() {
        return img;
    }

    //setter for ImageView
    public void setImg(ImageView img) {
        this.img = img;
    }

    //method to check whether the piece is alive
    public boolean isAlive() {
        return alive;
    }

    //method to set the status of the piece
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //getter for the color
    public String getColor() {
        return color;
    }

    //setter for the color
    public void setColor(String color) {
        this.color = color;
    }

    //getter for y-Position
    public int getyPosition() {
        return yPosition;
    }

    //setter for y-Position
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    //getter for x-Position
    public int getxPosition() {
        return xPosition;
    }

    //setter for x-Position
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    //getter for pieces id
    public int getId() {
        return id;
    }

    //setter for pieces id
    public void setId(int id) {
        this.id = id;
    }
}
