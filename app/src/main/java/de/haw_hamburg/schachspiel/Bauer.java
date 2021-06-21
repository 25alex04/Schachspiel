package de.haw_hamburg.schachspiel;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Function;

//Pawn class
public class Bauer extends Pieces {

    boolean firstTurn = true; //boolean whether it is the player's first turn

    //method to check firstTurn
    public boolean isFirstTurn() {
        return firstTurn;
    }

    //set firstTurn
    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    //constructor for pawn
    public Bauer(String color){
        super.color = color;
    }

    //initialise the object with attributes
    public void initialise(ImageView img, int xPos, int yPos, int id){
        this.img = img;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.id = id;
    }

    //first tries to create move()-method
    @Override
    void move() {
//        if (p.getColor().equalsIgnoreCase("weiß")){
//            if (((Bauer)p).isFirstTurn() && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1) && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()+2)){//firstTurn ==true
//                possibles.add(field[p.getxPosition()][p.getyPosition()+2]);
//                //((Bauer) p).setFirstTurn(false);
//            }
//            if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1)){
//                possibles.add(field[p.getxPosition()][p.getyPosition()+1]);
//            }
//            if (p.getxPosition() <7 && isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+1) && !takenByW){
//                beatables.add(field[p.getxPosition()+1][p.getyPosition()+1]);
//            }
//            if (p.getxPosition()>0 && isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+1) && !takenByW){
//                beatables.add(field[p.getxPosition()-1][p.getyPosition()+1]);
//            }
//        }
//
//        if (p.getColor().equalsIgnoreCase("schwarz")){
//
//
//            if (((Bauer)p).isFirstTurn() && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()-1) && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()-2)){//firstTurn ==true
//                possibles.add(field[p.getxPosition()][p.getyPosition()-2]);
//                //((Bauer) p).setFirstTurn(false);
//            }
//            if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()-1)){
//                possibles.add(field[p.getxPosition()][p.getyPosition()-1]);
//            }
//            if (p.getxPosition() <7 && isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-1) && !takenByBl){
//                beatables.add(field[p.getxPosition()+1][p.getyPosition()-1]);
//            }
//            if (p.getxPosition()>0 && isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-1) && !takenByBl){
//                beatables.add(field[p.getxPosition()-1][p.getyPosition()-1]);
//            }
//        }
    }

    //planned attack()-method
    @Override
    void attack() {

    }
}
