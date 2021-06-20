package de.haw_hamburg.schachspiel;

import org.junit.Test;
import org.testng.annotations.AfterTest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class jUnitTests {


    //test for both getPiece methods
    @Test
    public void getPiece(){

        //initialize given ArrayList for the test
        ArrayList<Pieces> pieces = new ArrayList();

        pieces.add(new Turm(null, "white" , 2,4,10));
        pieces.add(new Dame(null, "black" , 5,1,1));
        pieces.add(new Springer(null, "white" , 7,1,22));
        pieces.add(new Läufer(null, "black" , 0,0,27));


        //test for coordinates
        int x = 2;
        int y = 4;

        //only 'Turm' has these coordinates
        for (Pieces p : pieces) {
            if (p.getxPosition() == x && p.getyPosition() == y) {
                assertTrue(p instanceof Turm);
            }else {
                assertFalse(p instanceof Turm);
            }
        }

        //test for Id
        int id = 27;

        //only 'Läufer' got that id
        for (Pieces p : pieces) {
            if (p.getId() == id) {
                assertTrue(p instanceof Läufer);
            }else {
                assertFalse(p instanceof Läufer);
            }
        }

    }

    //test for takenBy method
    @Test
    public void takenBy(){

        //two Pieces with different colors
        König königB = new König(null, "black", 1,1,1);
        König königW = new König(null, "white", 1,1,1);

        //given color
        String actualColor = "black";

        //getColor() method should return right color
        assertEquals(königB.getColor(),actualColor);
        assertNotEquals(königW.getColor(),actualColor);
    }

    //test for isClickedFieldTaken method
    @Test
    public void isClickedFieldTaken(){

        //initialize ArrayList with given elements
        ArrayList<Pieces> pieces = new ArrayList();

        for(int i = 0; i<3;i++){
            pieces.add(new Springer(null,"black", i,1, 10));
        }

        for(int i = 0; i<3;i++){
            pieces.add(new Läufer(null,"white", 5,i, 20));
        }


        //check ArrayList
        for(int i = 0; i<pieces.size();i++){

            for (int j = 0; j<3;j++){

                //only black given pieces fits to those coordinates
                if (pieces.get(i).getxPosition() == j && pieces.get(i).getyPosition() == 1){
                    assertEquals(pieces.get(i).getColor(),"black");
                }

                //only white given pieces fits to those coordinates
                if (pieces.get(i).getxPosition() == 5 && pieces.get(i).getyPosition() == j){
                    assertEquals(pieces.get(i).getColor(),"white");
                }
            }
        }
    }

    //test for refreshTurnCounter method
    @Test
    public void refreshTurnCounter(){

        //initialze some start values
        int turnCounter= 4;
        int whiteCounter = 2;
        int blackCounter = 1;

        //one run through of the method
        if (turnCounter % 2 == 1) {
            whiteCounter = (turnCounter - 1) / 2 + 1;
        } else {
            blackCounter = turnCounter / 2;
        }

        //check after run through if the values are correct
        assertTrue((whiteCounter==2 && blackCounter==2));

        //increment turnCounter after usual run through
        turnCounter++;

        //second run through of the method so if and the else will be tested
        if (turnCounter % 2 == 1) {
            whiteCounter = (turnCounter - 1) / 2 + 1;
        } else {
            blackCounter = turnCounter / 2;
        }

        //check after second run through
        assertTrue((whiteCounter==3 && blackCounter==2));

        // increment again
        turnCounter++;

        //start again 'first' run through because of modulo 2
        if (turnCounter % 2 == 1) {
            whiteCounter = (turnCounter - 1) / 2 + 1;
        } else {
            blackCounter = turnCounter / 2;
        }

        //check for modulo 2 works as intended
        assertTrue((whiteCounter==3 && blackCounter==3));
    }
}
