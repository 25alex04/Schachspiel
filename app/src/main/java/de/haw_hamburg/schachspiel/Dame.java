package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public class Dame extends Pieces{

    //constructor for queen
    public Dame(ImageView img, String color, int xPos, int yPos, int id) {
        //super();
        super.img = img;
        super.color = color;
        super.xPosition = xPos;
        super.yPosition = yPos;
        super.id = id;
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
