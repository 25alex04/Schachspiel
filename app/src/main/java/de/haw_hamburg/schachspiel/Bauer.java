package de.haw_hamburg.schachspiel;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Function;

public class Bauer extends Pieces {

    /*String color;
    ImageView img;
    int xPos;
    int yPos;
    int id;*/
    boolean firstTurn = true;

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }
    /*
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

    /*
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
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.id = id;
    }

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

    @Override
    void attack() {

    }




}
