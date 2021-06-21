package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public class Turm extends Pieces{

    //constructor for rook
    public Turm(ImageView img, String color, int xPos, int yPos, int id) {
        //super();
        this.setImg(img);
        this.setColor(color);
        this.setxPosition(xPos);
        this.setyPosition(yPos);
        this.setId(id);
    }

    //planned move()-attack
    @Override
    void move() {

    }

    //planed attack()-method
    @Override
    void attack() {

    }
}
