package de.haw_hamburg.schachspiel;

import java.util.HashMap;

//NOT USED
public class Flyweight {

    //Hashmap for colors of the pawns
    private static final HashMap<String, Bauer> bauerByColor = new HashMap<String, Bauer>();

    //initialise a pawn object
    public static Bauer getBauer (String color){
        //get color from hashmap
        Bauer bauer = (Bauer)bauerByColor.get(color);
        //if color is not an element of the hashmap...
        if(bauer == null){
            //...create a new object of pawn
            bauer = new Bauer(color);
            //put the "new" color into the hashmap
            bauerByColor.put(color, bauer);
        }
        //return a copy of pawn if the color is an element of the hashmap
        return bauer;
    }
}
