package de.haw_hamburg.schachspiel;

import android.widget.ImageView;

public class König extends Pieces{

    public König(ImageView img, String color, int xPos, int yPos, int id) {
        //super();
        super.img = img;
        super.color = color;
        super.xPosition = xPos;
        super.yPosition = yPos;
        super.id = id;
    }
    @Override
    void move() {

    }

    @Override
    void attack() {

    }
}
