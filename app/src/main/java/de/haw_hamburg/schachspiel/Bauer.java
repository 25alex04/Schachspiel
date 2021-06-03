package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public class Bauer extends Pieces {

    String color;
    ImageView img;
    int xPos;
    int yPos;
    int id;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public Bauer(ImageView img, String color, int xPos, int yPos, int id) {
        //super();
        super.img = img;
        super.color = color;
        super.xPosition = xPos;
        super.yPosition = yPos;
        super.id = id;
        }
     */

    public Bauer(String color){
        super.color = color;
    }

    public void initialise(ImageView img, int xPos, int yPos, int id){
        this.img = img;
        this.xPos = xPos;
        this.yPos = yPos;
        this.id = id;
    }

    @Override
    void move() {

    }

    @Override
    void attack() {

    }




}
