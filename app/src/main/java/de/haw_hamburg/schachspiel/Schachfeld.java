package de.haw_hamburg.schachspiel;

import java.util.ArrayList;

public class Schachfeld {

    //planned ArrayList to create field
    private ArrayList<Feld> felder = new ArrayList();

    //method to initialise field
    public Schachfeld() {
        for(int x = 0; x <8; x++){
            for(int y = 0; y<8;y++){
                felder.add(new Feld(x,y));
            }
        }
    }

    //method to return field
    public ArrayList<Feld> getFelder() {
        return felder;
    }
}
