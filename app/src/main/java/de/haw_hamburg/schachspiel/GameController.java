package de.haw_hamburg.schachspiel;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameController{

    ArrayList<Pieces> piecesP1 = new ArrayList();
    ArrayList<Pieces> piecesP2 = new ArrayList();
    ArrayList<Feld> possibleFelder = new ArrayList();
    Schachfeld schachfeld;
    int clickedXPosition;
    int clickedYPosition;
    Pieces p;

    public GameController() {

//        for(int i = 0; i<8;i++){
//            Bauer bauer = new Bauer("img","weiß", i, 6, i);
//            piecesP1.add(bauer);
//        }

//        Turm turm1P1 = new Turm("img","weiß",0,7,8);
//        Springer springer1P1 = new Springer("img","weiß", 1, 7, 9);
//        Läufer läufer1P1 = new Läufer("img", "weiß", 2,7,10);
//        Dame dameP1 = new Dame("img", "weiß",3,7,11);
//        König königP1 = new König("img", "weiß", 4,7,12);
//        Läufer läufer2P1 = new Läufer("img", "weiß",5,7,13);
//        Springer springer2P1 = new Springer("img","weiß", 6, 7, 14);
//        Turm turm2P1 = new Turm("img","weiß",7,7,15);

//        piecesP1.add(turm1P1);
//        piecesP1.add(springer1P1);
//        piecesP1.add(läufer1P1);
//        piecesP1.add(dameP1);
//        piecesP1.add(königP1);
//        piecesP1.add(läufer2P1);
//        piecesP1.add(springer2P1);
//        piecesP1.add(turm2P1);

//        for(int i = 0; i<8;i++){
//            Bauer bauer = new Bauer("img","weiß", i, 6, 16+i);
//            piecesP2.add(bauer);
//        }
//
//        Turm turm1P2 = new Turm("img","schwarz",0,0,24);
//        Springer springer1P2 = new Springer("img","schwarz", 1, 0, 25);
//        Läufer läufer1P2 = new Läufer("img", "schwarz", 2,0,26);
//        Dame dameP2 = new Dame("img", "schwarz",3,0,27);
//        König königP2 = new König("img", "schwarz", 4,0,28);
//        Läufer läufer2P2 = new Läufer("img", "schwarz",5,0,29);
//        Springer springer2P2 = new Springer("img","schwarz", 6, 0, 30);
//        Turm turm2P2 = new Turm("img","schwarz",7,0,31);
//
//        piecesP2.add(turm1P2);
//        piecesP2.add(springer1P2);
//        piecesP2.add(läufer1P2);
//        piecesP2.add(dameP2);
//        piecesP2.add(königP2);
//        piecesP2.add(läufer2P2);
//        piecesP2.add(springer2P2);
//        piecesP2.add(turm2P2);

        schachfeld = new Schachfeld();

        for(Feld feld: schachfeld.getFelder()){
            if (feld.getyPos()==0 || feld.getyPos()==1 || feld.getyPos()==6 || feld.getyPos()==7){
                feld.setTaken(true);
            }
        }
    }

    public boolean isClickedFieldTaken(int x, int y){

        for(Feld feld: schachfeld.getFelder()){
            if(feld.getxPos()==x && feld.getyPos()==y){
                return feld.isTaken();
            }
        }
        return false;
    }

    public void setFieldToTaken(int x, int y, boolean taken){
        for (Feld feld: schachfeld.getFelder()){
            if(feld.getxPos()==x && feld.getyPos()==y){
                feld.setTaken(taken);
            }
        }
    }

    public int getPieceOnField(int x, int y){

        for(Pieces p : piecesP1){
            if(p.getxPosition()==x && p.getyPosition()==y){
                return p.getId();
            }
        }
        for(Pieces p : piecesP2){
            if(p.getxPosition()==x && p.getyPosition()==y){
                return p.getId();
            }
        }
        return 100;
    }

    public void destroyPiece(int id){

        for(Pieces p : piecesP1){
            if(p.getId()==id){
                p.setAlive(false);
                break;
            }
        }
        for(Pieces p : piecesP2){
            if(p.getId()==id){
                p.setAlive(false);
                break;
            }
        }
    }

    public void gameloop(){
        while(true){
            clickedYPosition++;
        }
    }

    public Pieces getPiece(int id){
        for(Pieces p: piecesP1){
            if(p.getId()==id){
                return p;
            }
        }
        for(Pieces p: piecesP2){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public Feld getFeld(int x, int y){
        for(Feld feld: schachfeld.getFelder()){
            if (feld.getxPos()==x && feld.getyPos()==y){
                return feld;
            }
        }
        return null;
    }

    public void p1Turn(){

        if(isClickedFieldTaken(clickedXPosition,clickedYPosition)){
            p = getPiece(getPieceOnField(clickedXPosition,clickedYPosition));
            switch (p.getId()){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if(!isClickedFieldTaken(clickedXPosition,clickedYPosition+1)){
                        p.setyPosition(clickedYPosition+1);
                        setFieldToTaken(clickedXPosition,clickedYPosition,false);
                        setFieldToTaken(clickedXPosition,clickedYPosition+1,true);
                        break;
                    }
                    if(clickedXPosition+1<8){
                        if(isClickedFieldTaken(clickedXPosition+1,clickedYPosition+1) && getPieceOnField(clickedXPosition+1,clickedYPosition+1)>15){
                            p.setxPosition(clickedXPosition+1);
                            p.setyPosition(clickedYPosition+1);
                            setFieldToTaken(clickedXPosition,clickedYPosition,false);
                            break;
                        }
                    }
                    if(clickedXPosition-1>=0){
                        if(isClickedFieldTaken(clickedXPosition-1,clickedYPosition+1) && getPieceOnField(clickedXPosition-1,clickedYPosition+1)>15){
                            p.setxPosition(clickedXPosition-1);
                            p.setyPosition(clickedYPosition+1);
                            setFieldToTaken(clickedXPosition,clickedYPosition,false);
                            break;
                        }
                    }
                    break;
                case 8:
                case 15:
                    for(int y = 1; y<=7;y++){
                        if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+y) && p.getyPosition()+y<8){
                            possibleFelder.add(getFeld(p.getxPosition(),p.getyPosition()+y));
                        }else{
                            break;
                        }
                    }

                    for (int y = 1; y<=7;y++){
                        if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()-y) && p.getyPosition()-y>=0){
                            possibleFelder.add(getFeld(p.getxPosition(),p.getyPosition()-y));
                        }else{
                            break;
                        }
                    }

                    for(int x = 1; x<=7;x++){
                        if (!isClickedFieldTaken(p.getxPosition()+x,p.getyPosition()) && p.getxPosition()+x<8){
                            possibleFelder.add(getFeld(p.getxPosition()+x,p.getyPosition()));
                        }else{
                            break;
                        }

                    }
                    for (int x = 1;x<=7;x++){
                        if (!isClickedFieldTaken(p.getxPosition()-x,p.getyPosition()) && p.getxPosition()-x>=0){
                            possibleFelder.add(getFeld(p.getxPosition()-x,p.getyPosition()));
                        }else{
                            break;
                        }
                    }



            }

        }

    }

    public int getClickedXPosition() {
        return clickedXPosition;
    }

    public void setClickedXPosition(int clickedXPosition) {
        this.clickedXPosition = clickedXPosition;
    }

    public int getClickedYPosition() {
        return clickedYPosition;
    }

    public void setClickedYPosition(int clickedYPosition) {
        this.clickedYPosition = clickedYPosition;
    }

}
