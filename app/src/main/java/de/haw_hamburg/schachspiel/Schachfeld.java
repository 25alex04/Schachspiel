package de.haw_hamburg.schachspiel;

import java.util.ArrayList;

public class Schachfeld {

    ArrayList<Feld> felder = new ArrayList();

    public Schachfeld() {

        for(int x = 0; x <8; x++){
            for(int y = 0; y<8;y++){
                felder.add(new Feld(x,y));
            }
        }
    }

    public ArrayList<Feld> getFelder() {
        return felder;
    }
}
