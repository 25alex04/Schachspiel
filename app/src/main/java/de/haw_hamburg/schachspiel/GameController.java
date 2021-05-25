package de.haw_hamburg.schachspiel;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GameController{

    ArrayList<Pieces> piecesP1 = new ArrayList();
    ArrayList<Pieces> piecesP2 = new ArrayList();
    Schachfeld schachfeld;


    public GameController() {

        for(int i = 0; i<8;i++){
            Bauer bauer = new Bauer("img","weiß", i, 6, i);
            piecesP1.add(bauer);
        }

        Turm turm1P1 = new Turm("img","weiß",0,7,8);
        Springer springer1P1 = new Springer("img","weiß", 1, 7, 9);
        Läufer läufer1P1 = new Läufer("img", "weiß", 2,7,10);
        Dame dameP1 = new Dame("img", "weiß",3,7,11);
        König königP1 = new König("img", "weiß", 4,7,12);
        Läufer läufer2P1 = new Läufer("img", "weiß",5,7,13);
        Springer springer2P1 = new Springer("img","weiß", 6, 7, 14);
        Turm turm2P1 = new Turm("img","weiß",7,7,15);

        piecesP1.add(turm1P1);
        piecesP1.add(springer1P1);
        piecesP1.add(läufer1P1);
        piecesP1.add(dameP1);
        piecesP1.add(königP1);
        piecesP1.add(läufer2P1);
        piecesP1.add(springer2P1);
        piecesP1.add(turm2P1);

        for(int i = 0; i<8;i++){
            Bauer bauer = new Bauer("img","weiß", i, 6, 16+i);
            piecesP2.add(bauer);
        }

        Turm turm1P2 = new Turm("img","schwarz",0,0,24);
        Springer springer1P2 = new Springer("img","schwarz", 1, 0, 25);
        Läufer läufer1P2 = new Läufer("img", "schwarz", 2,0,26);
        Dame dameP2 = new Dame("img", "schwarz",3,0,27);
        König königP2 = new König("img", "schwarz", 4,0,28);
        Läufer läufer2P2 = new Läufer("img", "schwarz",5,0,29);
        Springer springer2P2 = new Springer("img","schwarz", 6, 0, 30);
        Turm turm2P2 = new Turm("img","schwarz",7,0,31);

        piecesP2.add(turm1P2);
        piecesP2.add(springer1P2);
        piecesP2.add(läufer1P2);
        piecesP2.add(dameP2);
        piecesP2.add(königP2);
        piecesP2.add(läufer2P2);
        piecesP2.add(springer2P2);
        piecesP2.add(turm2P2);

        schachfeld = new Schachfeld();
    }

    public String HAHA(){
        return "haha";
    }


    public void gameUpdate(){

    }


}
