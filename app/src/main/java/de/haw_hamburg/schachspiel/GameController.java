package de.haw_hamburg.schachspiel;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameController{

    TextView[][] fields;
    ArrayList<Pieces> w_pieces = new ArrayList();
    ArrayList<Pieces> bl_pieces = new ArrayList();
    ArrayList<TextView> possibles = new ArrayList();
    ArrayList<TextView> beatables = new ArrayList();
    boolean whiteTurn;
    boolean blackTurn;
    boolean gameOver;
    TextView timerW;
    TextView timerB;
    TextView turnCounterW;
    TextView turnCounterB;
    int tmpPiece;
    TextView tmpField;
    int turnCounter;
    Button playerTurnDisplayW;
    Button playerTurnDisplayB;
    Drawable backgroundW;
    Drawable backgroundB;
    Drawable red;
    Drawable green;

    public GameController(TextView[][] fields, ArrayList<Pieces> w_Pieces, ArrayList<Pieces> bl_Pieces, ArrayList<TextView> possibles, ArrayList<TextView> beatables, boolean whiteTurn,
                          boolean blackTurn, boolean gameOver, TextView timerW, TextView timerB, TextView turnCounterW, TextView turnCounterB, int turnCounter, Button playerTurnDisplayW,
                          Button playerTurnDisplayB, Drawable backgroundW, Drawable backgroundB, Drawable red, Drawable green) {
        this.fields = fields;
        this.w_pieces = w_Pieces;
        this.bl_pieces = bl_Pieces;
        this.possibles = possibles;
        this.beatables = beatables;
        this.whiteTurn = whiteTurn;
        this.blackTurn = blackTurn;
        this.gameOver = gameOver;
        this.timerW = timerW;
        this.timerB = timerB;
        this.turnCounterW = turnCounterW;
        this.turnCounterB = turnCounterB;
        this.turnCounter = turnCounter;
        this.playerTurnDisplayW = playerTurnDisplayW;
        this.playerTurnDisplayB = playerTurnDisplayB;
        this.backgroundW = backgroundW;
        this.backgroundB = backgroundB;
        this.red = red;
        this.green = green;
    }

    public Pieces getPiece(int id){
        for(Pieces p: w_pieces){
            if(p.getId()==id){
                return p;
            }
        }
        for(Pieces p: bl_pieces){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public Pieces getPiece(int x, int y){
        for(Pieces p: w_pieces){
            if(p.getxPosition()==x && p.getyPosition()==y){
                return p;
            }
        }
        for(Pieces p: bl_pieces){
            if(p.getxPosition()==x && p.getyPosition()==y){
                return p;
            }
        }
        return null;
    }

    public TextView getField(int x, int y){

        return fields[y][x];
    }

    private void piecesOnStartposition(){

        int index = 0;
        for(int i = 0;i<2;i++){
            for (int j =0;j<8;j++){
                w_pieces.get(index).getImg().setX(fields[i][j].getX());
                w_pieces.get(index).getImg().setY(fields[i][j].getY());
                w_pieces.get(index).getImg().bringToFront();

                index++;
            }
        }
        index = 0;

        for(int i = 7; i>5;i--){
            for (int j = 0;j<8;j++){
                bl_pieces.get(index).getImg().setX(fields[i][j].getX());
                bl_pieces.get(index).getImg().setY(fields[i][j].getY());
                bl_pieces.get(index).getImg().bringToFront();
                index++;
            }
        }
    }

    public boolean isClickedFieldTaken(int x, int y){

        for(int i=0;i<32;i++){
            if (getPiece(i).getxPosition()==x && getPiece(i).getyPosition()==y){
                return true;
            }
        }
        return false;
    }

    public String takenBy(int x, int y){
        return getPiece(x,y).getColor();
    }

    private void undoPossibles(){
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if ((i%2==1&&j%2==1) || (i%2==0&&j%2==0)){
                    fields[i][j].setBackground(backgroundB);
                }else {
                    fields[i][j].setBackground(backgroundW);
                }
            }
        }
    }

    private void showPossibles(Pieces p){

        possibles.clear();
        beatables.clear();

        if (p instanceof Bauer){

//            p.move();
            if (p.getColor().equalsIgnoreCase("weiß")){
                if (((Bauer)p).isFirstTurn() && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1) && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()+2)){//firstTurn ==true
                    possibles.add(getField(p.getxPosition(),p.getyPosition()+2));
                    //((Bauer) p).setFirstTurn(false);
                }
                if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1)){
                    possibles.add(getField(p.getxPosition(),p.getyPosition()+1));
                }
                if (p.getxPosition() <7 && isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+1) && !takenBy(p.getxPosition()+1,p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                    beatables.add(getField(p.getxPosition()+1,p.getyPosition()+1));
                }
                if (p.getxPosition()>0 && isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+1) && !takenBy(p.getxPosition()+1,p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                    beatables.add(getField(p.getxPosition()-1,p.getyPosition()+1));
                }
            }

            if (p.getColor().equalsIgnoreCase("schwarz")){


                if (((Bauer)p).isFirstTurn() && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()-1) && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()-2)){//firstTurn ==true
                    possibles.add(getField(p.getxPosition(),p.getyPosition()-2));
                    //((Bauer) p).setFirstTurn(false);
                }
                if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()-1)){
                    possibles.add(getField(p.getxPosition(),p.getyPosition()-1));
                }
                if (p.getxPosition() <7 && isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-1) && !takenBy(p.getxPosition()+1,p.getyPosition()-1).equalsIgnoreCase(p.getColor())){
                    beatables.add(getField(p.getxPosition()+1,p.getyPosition()-1));
                }
                if (p.getxPosition()>0 && isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-1) && !takenBy(p.getxPosition()-1,p.getyPosition()-1).equalsIgnoreCase(p.getColor())){
                    beatables.add(getField(p.getxPosition()-1,p.getyPosition()-1));
                }
            }
        }

        if(p instanceof Springer){



            //vorne rechts
            if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+2) && p.getxPosition()+1<8 && p.getyPosition()+2<8){
                possibles.add(getField(p.getxPosition()+1, p.getyPosition()+2));
            }
            else if (isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+2) && p.getxPosition()+1<8 && p.getyPosition()+2<8 && !takenBy(p.getxPosition()+1,p.getyPosition()+2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()+1, p.getyPosition()+2));
            }
            //vorne links
            if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+2) && p.getxPosition()-1>-1 && p.getyPosition()+2<8){
                possibles.add(getField(p.getxPosition()-1,p.getyPosition()+2));
            }
            else if (isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+2) && p.getxPosition()-1>-1 && p.getyPosition()+2<8 && !takenBy(p.getxPosition()-1,p.getyPosition()+2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()-1, p.getyPosition()+2));
            }
            //hinten rechts
            if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-2) && p.getxPosition()+1<8 && p.getyPosition()-2>-1){
                possibles.add(getField(p.getxPosition()+1, p.getyPosition()-2));
            }
            else if (isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-2) && p.getxPosition()+1<8 && p.getyPosition()-2>-1 && !takenBy(p.getxPosition()+1,p.getyPosition()-2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()+1, p.getyPosition()-2));
            }
            //hinten links
            if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-2) && p.getxPosition()-1>-1 && p.getyPosition()-2>-1){
                possibles.add(getField(p.getxPosition()-1, p.getyPosition()-2));
            }
            else if (isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-2) && p.getxPosition()-1>-1 && p.getyPosition()-2>-1 && !takenBy(p.getxPosition()-1,p.getyPosition()-2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()-1, p.getyPosition()-2));
            }
            //rechts vorne
            if (!isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()+1) && p.getxPosition()+2<8 && p.getyPosition()+1<8){
                possibles.add(getField(p.getxPosition()+2, p.getyPosition()+1));
            }
            else if (isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()+1) && p.getxPosition()+2<8 && p.getyPosition()+1<8 && !takenBy(p.getxPosition()+2,p.getyPosition()+1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()+2, p.getyPosition()+1));
            }
            //rechts hinten
            if (!isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()-1) && p.getxPosition()+2<8 && p.getyPosition()-1>-1){
                possibles.add(getField(p.getxPosition()+2, p.getyPosition()-1));
            }
            else if (isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()-1) && p.getxPosition()+2<8 && p.getyPosition()-1>-1 && !takenBy(p.getxPosition()+2,p.getyPosition()-1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()+2, p.getyPosition()-1));
            }
            //links vorne
            if (!isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()+1) && p.getxPosition()-2>-1 && p.getyPosition()+1<8){
                possibles.add(getField(p.getxPosition()-2, p.getyPosition()+1));
            }
            else if (isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()+1) && p.getxPosition()-2>-1 && p.getyPosition()+1<8 && !takenBy(p.getxPosition()-2,p.getyPosition()+1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()-2, p.getyPosition()+1));
            }
            //links hinten
            if (!isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()-1) && p.getxPosition()-2>-1 && p.getyPosition()-1>-1){
                possibles.add(getField(p.getxPosition()-2, p.getyPosition()-1));
            }
            else if (isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()-1) && p.getxPosition()-2>-1 && p.getyPosition()-1>-1 && !takenBy(p.getxPosition()-2,p.getyPosition()-1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition()-2, p.getyPosition()-1));
            }
        }

        if(p instanceof Turm){

            //nach vorn
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+i)){
                        possibles.add(getField(p.getxPosition(),p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition(),p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition(),p.getyPosition()+i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach unten
            for(int i=1; i<=7; i++){
                if (p.getyPosition()-i >=0){
                    if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()-i)){
                        possibles.add(getField(p.getxPosition(),p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition(),p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition(),p.getyPosition()-i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach rechts
            for(int i=1; i<=7; i++){
                if (p.getxPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition()+i,p.getyPosition())){
                        possibles.add(getField(p.getxPosition()+i,p.getyPosition()));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()+i,p.getyPosition()));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach links
            for(int i=1; i<=7; i++){
                if (p.getxPosition()-i >=0){
                    if (!isClickedFieldTaken(p.getxPosition()-i,p.getyPosition())){
                        possibles.add(getField(p.getxPosition()-i,p.getyPosition()));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()-i,p.getyPosition()));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }
        }

        if(p instanceof Läufer){

            //nach vorn-rechts
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <= 7 && p.getxPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition()+i,p.getyPosition()+i)){
                        possibles.add(getField(p.getxPosition()+i,p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()+i,p.getyPosition()+i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach unten rechts
            for(int i=1; i<=7; i++){
                if (p.getyPosition()-i >=0 && p.getxPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition()+i,p.getyPosition()-i)){
                        possibles.add(getField(p.getxPosition()+i,p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()+i,p.getyPosition()-i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach hinten links
            for(int i=1; i<=7; i++){
                if (p.getyPosition()-i >=0 && p.getxPosition()-i >= 0){
                    if (!isClickedFieldTaken(p.getxPosition()-i,p.getyPosition()-i)){
                        possibles.add(getField(p.getxPosition()-i,p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()-i,p.getyPosition()-i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach vorn-links
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <=7 && p.getxPosition()-i >=0){
                    if (!isClickedFieldTaken(p.getxPosition()-i,p.getyPosition()+i)){
                        possibles.add(getField(p.getxPosition()-i,p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()-i,p.getyPosition()+i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }


        }

        if(p instanceof Dame){


            //nach vorn
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+i)){
                        possibles.add(getField(p.getxPosition(),p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition(),p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition(),p.getyPosition()+i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach unten
            for(int i=1; i<=7; i++){
                if (p.getyPosition()-i >=0){
                    if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()-i)){
                        possibles.add(getField(p.getxPosition(),p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition(),p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition(),p.getyPosition()-i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach rechts
            for(int i=1; i<=7; i++){
                if (p.getxPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition()+i,p.getyPosition())){
                        possibles.add(getField(p.getxPosition()+i,p.getyPosition()));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()+i,p.getyPosition()));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach links
            for(int i=1; i<=7; i++){
                if (p.getxPosition()-i >=0){
                    if (!isClickedFieldTaken(p.getxPosition()-i,p.getyPosition())){
                        possibles.add(getField(p.getxPosition()-i,p.getyPosition()));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()-i,p.getyPosition()));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach vorn-rechts
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <= 7 && p.getxPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition()+i,p.getyPosition()+i)){
                        possibles.add(getField(p.getxPosition()+i,p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()+i,p.getyPosition()+i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach unten rechts
            for(int i=1; i<=7; i++){
                if (p.getyPosition()-i >=0 && p.getxPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition()+i,p.getyPosition()-i)){
                        possibles.add(getField(p.getxPosition()+i,p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()+i,p.getyPosition()-i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach hinten links
            for(int i=1; i<=7; i++){
                if (p.getyPosition()-i >=0 && p.getxPosition()-i >= 0){
                    if (!isClickedFieldTaken(p.getxPosition()-i,p.getyPosition()-i)){
                        possibles.add(getField(p.getxPosition()-i,p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()-i,p.getyPosition()-i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }

            //nach vorn-links
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <=7 && p.getxPosition()-i >=0){
                    if (!isClickedFieldTaken(p.getxPosition()-i,p.getyPosition()+i)){
                        possibles.add(getField(p.getxPosition()-i,p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getField(p.getxPosition()-i,p.getyPosition()+i));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }
        }

        if (p instanceof König){

            //nach vorn
            if (p.getyPosition()+1<=7){
                if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1)){
                    possibles.add(getField(p.getxPosition(),p.getyPosition()+1));
                }else {
                    if (!takenBy(p.getxPosition(),p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition(),p.getyPosition()+1));
                    }
                }
            }

            //nach unten
            if (p.getyPosition()-1 >=0){
                if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()-1)){
                    possibles.add(getField(p.getxPosition(),p.getyPosition()-1));
                }else {
                    if (!takenBy(p.getxPosition(),p.getyPosition()-1).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition(),p.getyPosition()-1));
                    }

                }
            }

            //nach rechts
            if (p.getxPosition()+1 <= 7){
                if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition())){
                    possibles.add(getField(p.getxPosition()+1,p.getyPosition()));
                }else {
                    if (!takenBy(p.getxPosition()+1,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition()+1,p.getyPosition()));
                    }
                }
            }

            //nach links
            if (p.getxPosition()-1 >=0){
                if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition())){
                    possibles.add(getField(p.getxPosition()-1,p.getyPosition()));
                }else {
                    if (!takenBy(p.getxPosition()-1,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition()-1,p.getyPosition()));
                    }
                }
            }

            //nach vorn-rechts
            if (p.getyPosition()+1 <= 7 && p.getxPosition()+1 <= 7){
                if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+1)){
                    possibles.add(getField(p.getxPosition()+1,p.getyPosition()+1));
                }else {
                    if (!takenBy(p.getxPosition()+1,p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition()+1,p.getyPosition()+1));
                    }
                }
            }

            //nach unten-rechts
            if (p.getyPosition()-1 >=0 && p.getxPosition()+1 <= 7){
                if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-1)){
                    possibles.add(getField(p.getxPosition()+1,p.getyPosition()-1));
                }else {
                    if (!takenBy(p.getxPosition()+1,p.getyPosition()-1).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition()+1,p.getyPosition()-1));
                    }
                }
            }

            //nach unten-links
            if (p.getyPosition()-1 >=0 && p.getxPosition()-1 >= 0){
                if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-1)){
                    possibles.add(getField(p.getxPosition()-1,p.getyPosition()-1));
                }else {
                    if (!takenBy(p.getxPosition()-1,p.getyPosition()-1).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition()-1,p.getyPosition()-1));
                    }
                }
            }

            //nach vorn-links
            if (p.getyPosition()+1 <=7 && p.getxPosition()-1 >=0){
                if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+1)){
                    possibles.add(getField(p.getxPosition()-1,p.getyPosition()+1));
                }else {
                    if (!takenBy(p.getxPosition()-1,p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                        beatables.add(getField(p.getxPosition()-1,p.getyPosition()+1));
                    }
                }
            }

            //Rochade
            if(p.getxPosition()==4 && p.getyPosition()==0 && p.getColor().equalsIgnoreCase("weiß")){
                if (!isClickedFieldTaken(3,0) && !isClickedFieldTaken(2,0) && !isClickedFieldTaken(1,0)) {
                    if (getPiece(0, 0) != null && getPiece(0, 0) instanceof Turm) {
                        possibles.add(getField(0, 0));
                    }
                }else if (!isClickedFieldTaken(5,0) && !isClickedFieldTaken(6,0)){
                    if (getPiece(7, 0) != null && getPiece(7, 0) instanceof Turm) {
                        possibles.add(getField(7, 0));
                    }
                }
            }

            if(p.getxPosition()==4 && p.getyPosition()==7 && p.getColor().equalsIgnoreCase("schwarz")){
                if (!isClickedFieldTaken(3,7) && !isClickedFieldTaken(2,7) && !isClickedFieldTaken(1,7)) {
                    if (getPiece(0, 7) != null && getPiece(0, 7) instanceof Turm) {
                        possibles.add(getField(0, 7));
                    }
                }else if (!isClickedFieldTaken(5,7) && !isClickedFieldTaken(6,7)){
                    if (getPiece(7, 7) != null && getPiece(7, 7) instanceof Turm) {
                        possibles.add(getField(7, 7));
                    }
                }
            }
        }

        for (TextView t: possibles){
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(t== fields[i][j]){
                        try {
                            fields[i][j].setBackground(green);
                        }catch (Exception e){}
                    }
                }
            }
        }
        for (TextView t: beatables){
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(t== fields[i][j]){
                        try {
                            fields[i][j].setBackground(red);
                        }catch (Exception e){

                        }
                    }
                }
            }
        }
    }

    void whiteTurn(boolean playerTurn, int whiteMiliSec, int whiteSec, int whiteMin, boolean clickedPositionhasChanged, int clickedXPosition, int clickedYPosition, int turn)
    {
        if(playerTurn)
        {
            whiteMiliSec+=50;
            if (whiteMiliSec==1000){
                whiteSec--;
                whiteMiliSec=0;
                if(whiteSec==0){
                    whiteMin--;
                    whiteSec=60;
                }
                if (whiteSec!=60&&whiteSec>9){
                    try{
                        timerW.setText("Zeit:"+whiteMin+":"+whiteSec);
                    }catch (Exception e){}
                }else if (whiteSec<10){
                    try{
                        timerW.setText("Zeit:"+ whiteMin+":0"+whiteSec);
                    }catch (Exception e){}
                }else{
                    try {
                        timerW.setText("Zeit:"+(whiteMin+1)+":00");
                    }catch (Exception e){}
                }
            }

            if (clickedPositionhasChanged)
            {
                if (turn % 2 == 1)
                {
                    if (isClickedFieldTaken(clickedXPosition, clickedYPosition))
                    {
                        if (getPiece(clickedXPosition, clickedYPosition) != null)
                        {
                            tmpPiece = getPiece(clickedXPosition, clickedYPosition).getId();
                            if (tmpPiece>15){
                                return;
                            }
                            showPossibles(getPiece(tmpPiece));
                        }
                        clickedPositionhasChanged = false;

                    }
                    else
                    {
                        clickedPositionhasChanged = false;
                        turn++;
                    }
                }
                else
                {
                    if (tmpPiece < 16)
                    {
                        tmpField = getField(clickedXPosition, clickedYPosition);
                        if (isClickedFieldTaken(clickedXPosition, clickedYPosition) && takenBy(clickedXPosition, clickedYPosition).equalsIgnoreCase("weiß"))
                        {
                            if (getPiece(tmpPiece)instanceof König&&  getPiece(clickedXPosition,clickedYPosition)!=null && getPiece(clickedXPosition,clickedYPosition)instanceof Turm && getPiece(clickedXPosition,clickedYPosition).getColor().equalsIgnoreCase("weiß")){
                                if (clickedXPosition==0){
                                    getPiece(clickedXPosition,clickedYPosition).getImg().setX(getField(3,0).getX());
                                    getPiece(clickedXPosition,clickedYPosition).setxPosition(3);
                                    getPiece(tmpPiece).getImg().setX(getField(2,0).getX());
                                    getPiece(tmpPiece).setxPosition(2);
                                } else {

                                    getPiece(clickedXPosition,clickedYPosition).getImg().setX(getField(5,0).getX());
                                    getPiece(clickedXPosition,clickedYPosition).setxPosition(5);
                                    getPiece(tmpPiece).getImg().setX(getField(6,0).getX());
                                    getPiece(tmpPiece).setxPosition(6);
                                }
                                whiteTurn = false;
                                blackTurn = true;
                                playerTurnDisplayW.setText("Not your turn");
                                playerTurnDisplayB.setText("Your turn");
                                turnCounter++;
                                refreshTurnCounter();
                                undoPossibles();
                                clickedPositionhasChanged = false;
                                return;
                            }else {
                                undoPossibles();
                                clickedPositionhasChanged = false;
                                return;
                            }
                        }
                        else if (beatables.contains(getField(clickedXPosition, clickedYPosition)))
                        {
                            getPiece(clickedXPosition, clickedYPosition).getImg().setVisibility(View.INVISIBLE);
                            getPiece(clickedXPosition, clickedYPosition).setxPosition(10);
                            getPiece(10, clickedYPosition).setyPosition(10);
                            getPiece(10, 10).setAlive(false);
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            if(getPiece(tmpPiece) instanceof Bauer)
                            {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            whiteTurn = false;
                            blackTurn = true;
                            playerTurnDisplayW.setText("Not your turn");
                            playerTurnDisplayB.setText("Your turn");
                            turnCounter++;
                            refreshTurnCounter();
                        }
                        else if (possibles.contains(getField(clickedXPosition, clickedYPosition)))
                        {
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            if(getPiece(tmpPiece) instanceof Bauer)
                            {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            whiteTurn = false;
                            blackTurn = true;
                            playerTurnDisplayW.setText("Not your turn");
                            playerTurnDisplayB.setText("Your turn");
                            turnCounter++;
                            refreshTurnCounter();
                        }
                    }else {
                        return;
                    }
                    undoPossibles();
                    clickedPositionhasChanged = false;
                }
            }
        }
    }

    void blackTurn(boolean playerTurn, int blackMiliSec, int blackSec, int blackMin, boolean clickedPositionhasChanged, int clickedXPosition, int clickedYPosition, int turn)
    {
        if(playerTurn)
        {
            blackMiliSec+=50;
            if (blackMiliSec==1000){
                blackSec--;
                blackMiliSec=0;
                if(blackSec==0){
                    blackMin--;
                    blackSec=60;
                }
                if (blackSec!=60&&blackSec>9){
                    try{
                        timerB.setText("Zeit:"+ blackMin+":"+blackSec);
                    }catch (Exception e){}
                }else if (blackSec<10){
                    try{
                        timerB.setText("Zeit:"+ blackMin+":0"+blackSec);
                    }catch (Exception e){}
                }else {
                    try {
                        timerB.setText("Zeit:"+(blackMin+1)+":00");
                    }catch (Exception e){}
                }
            }

            if (clickedPositionhasChanged)
            {
                if (turn % 2 == 1)
                {
                    if (isClickedFieldTaken(clickedXPosition, clickedYPosition))
                    {
                        if (getPiece(clickedXPosition, clickedYPosition) != null)
                        {
                            tmpPiece = getPiece(clickedXPosition, clickedYPosition).getId();
                            if (tmpPiece<16){
                                return;
                            }
                            showPossibles(getPiece(tmpPiece));
                        }
                        clickedPositionhasChanged = false;

                    }
                    else
                    {
                        clickedPositionhasChanged = false;
                        turn++;
                    }
                }
                else
                {
                    if (tmpPiece > 15)
                    {
                        tmpField = getField(clickedXPosition, clickedYPosition);
                        if (isClickedFieldTaken(clickedXPosition, clickedYPosition) && takenBy(clickedXPosition, clickedYPosition).equalsIgnoreCase("schwarz"))
                        {
                            if (getPiece(tmpPiece)instanceof König&&  getPiece(clickedXPosition,clickedYPosition)!=null && getPiece(clickedXPosition,clickedYPosition)instanceof Turm && getPiece(clickedXPosition,clickedYPosition).getColor().equalsIgnoreCase("schwarz")){
                                if (clickedXPosition==0){
                                    getPiece(clickedXPosition,clickedYPosition).getImg().setX(getField(3,0).getX());
                                    getPiece(clickedXPosition,clickedYPosition).setxPosition(3);
                                    getPiece(tmpPiece).getImg().setX(getField(2,0).getX());
                                    getPiece(tmpPiece).setxPosition(2);
                                } else {

                                    getPiece(clickedXPosition,clickedYPosition).getImg().setX(getField(5,0).getX());
                                    getPiece(clickedXPosition,clickedYPosition).setxPosition(5);
                                    getPiece(tmpPiece).getImg().setX(getField(6,0).getX());
                                    getPiece(tmpPiece).setxPosition(6);
                                }
                                blackTurn = false;
                                whiteTurn = true;
                                playerTurnDisplayW.setText("Your turn");
                                playerTurnDisplayB.setText("Not your turn");
                                turnCounter++;
                                refreshTurnCounter();
                                undoPossibles();
                                clickedPositionhasChanged = false;
                                return;
                            }else {
                                undoPossibles();
                                clickedPositionhasChanged = false;
                                return;
                            }
                        }
                        else if (beatables.contains(getField(clickedXPosition, clickedYPosition)))
                        {
                            getPiece(clickedXPosition, clickedYPosition).getImg().setVisibility(View.INVISIBLE);
                            getPiece(clickedXPosition, clickedYPosition).setxPosition(10);
                            getPiece(10, clickedYPosition).setyPosition(10);
                            getPiece(10, 10).setAlive(false);
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            if(getPiece(tmpPiece) instanceof Bauer)
                            {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            blackTurn = false;
                            whiteTurn = true;
                            playerTurnDisplayW.setText("Your turn");
                            playerTurnDisplayB.setText("Not your turn");
                            turnCounter++;
                            refreshTurnCounter();
                        }
                        else if (possibles.contains(getField(clickedXPosition, clickedYPosition)))
                        {
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            if(getPiece(tmpPiece) instanceof Bauer)
                            {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            blackTurn = false;
                            whiteTurn = true;
                            playerTurnDisplayW.setText("Your turn");
                            playerTurnDisplayB.setText("Not your turn");
                            turnCounter++;
                            refreshTurnCounter();
                        }
                    }else {
                        return;
                    }
                    undoPossibles();
                    clickedPositionhasChanged = false;
                }
            }
        }
    }

    public void refreshTurnCounter() {
        if (turnCounter %2 == 1) {
            turnCounterW.setText("Züge: " + ((turnCounter -1)/2 + 1));
        }else{
            turnCounterB.setText("Züge: " + (turnCounter /2));
        }
    }

}
