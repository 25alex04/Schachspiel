package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public class Turm extends Pieces{

    //constructor for rook
    public Turm(ImageView img, String color, int xPos, int yPos, int id) {
        //super();
        super.img = img;
        super.color = color;
        super.xPosition = xPos;
        super.yPosition = yPos;
        super.id = id;
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
