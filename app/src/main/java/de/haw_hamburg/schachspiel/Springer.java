package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public class Springer extends Pieces{

    //constructor for knight
    public Springer(ImageView img, String color, int xPos, int yPos, int id) {
        //super();
        this.setImg(img);
        this.setColor(color);
        this.setxPosition(xPos);
        this.setyPosition(yPos);
        this.setId(id);
    }

    //planned move()-method
    @Override
    void move() {

    }

    //planned attack()-method
    @Override
    void attack() {

    }
}
