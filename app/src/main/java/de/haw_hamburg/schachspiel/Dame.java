package de.haw_hamburg.schachspiel;

public class Dame extends Pieces{

    public Dame(String img, String color, int xPos, int yPos, int id) {
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
