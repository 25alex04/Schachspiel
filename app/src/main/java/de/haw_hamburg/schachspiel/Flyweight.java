package de.haw_hamburg.schachspiel;

import java.util.HashMap;


public class Flyweight {
    private static final HashMap<String, Bauer> bauerByColor = new HashMap<String, Bauer>();

    public static Bauer getBauer (String color){
        Bauer bauer = (Bauer)bauerByColor.get(color);
        if(bauer == null){
            bauer = new Bauer(color);
            bauerByColor.put(color, bauer);
        }
        return bauer;
    }





}
