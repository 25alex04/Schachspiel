package de.haw_hamburg.schachspiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends AppCompatActivity implements View.OnClickListener {

    private static final String DATA_KEY = "GameView.Data";         // key to  access information in different intents

    private Button start;                                           // Button to start the chess game

    private int clickedXPosition;                                   // Holds X coordinate of clicked position
    private int clickedYPosition;                                   // Holds Y coordinate of clicked position
    private boolean clickedPositionHasChanged = false;              // boolean: true if a player clicked on a chess square to move a piece

    private int clickedCounter = 0;                                 // Counts the amount of clicks
    private Button playerTurnDisplayW;                              // Button that shows the active players turn
    private Button playerTurnDisplayB;                              // Button that shows the active players turn

    private boolean whiteTurn = false;                              // boolean: true if it's the white players turn
    private boolean blackTurn = false;                              // boolean: true if it's the black players turn
    private boolean gameOver = false;                               // boolean: true if one of the player lost the game by time or by checkmate

    private ImageView field;                                        // Create imageview for the chess board
    private TextView[][] fields = new TextView[8][8];               // Initialize textview array of the chess board

    private ArrayList<TextView> possibles = new ArrayList();        // ArrayList of possible moves
    private ArrayList<TextView> beatables = new ArrayList();        // ArrayList of beatable pieces

    private ArrayList<Pieces> bl_pieces = new ArrayList();          // ArrayList of black pieces
    private ArrayList<Pieces> w_pieces = new ArrayList();           // ArrayList of white pieces
    private ImageView[] imageViewsBl = new ImageView[8];            // ImageViews of the black chess pieces
    private ImageView[] imageViewsW = new ImageView[8];             // ImageViews of the white chess pieces

    private int tmpPiece = 0;                                       // Remembers clicked piece until piece move is confirmed
    private TextView tmpField;                                      // Temporary version of game state

    private TextView turnCounterW;                                  // Turn counter for white player
    private TextView turnCounterB;                                  // Turn counter for black player

    private TextView timerW;                                        // Textview to show the white players timer
    private TextView timerB;                                        // Textview to show the black players timer

    private GameController GC;                                      // Initialize GameController

    //Create instances
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);

        field = findViewById(R.id.schachfeld);

        //Assign view to active player turn
        playerTurnDisplayW = findViewById(R.id.zugBest??tigenButton);
        playerTurnDisplayB = findViewById(R.id.zugBest??tigenButton180);
        //Set text for starting player
        playerTurnDisplayW.setText(" !Wei?? startet! "); //
        playerTurnDisplayB.setText(" !Wei?? startet! "); //

        //Assign view to startGame button
        start = findViewById(R.id.startGame);

        //Assign mute sound and help views to buttons
        ImageButton muteSound = findViewById(R.id.soundIngameButton);
        ImageButton helpButton = findViewById(R.id.helpButton);

        //Assign turn counter views of both players to textViews
        turnCounterW = findViewById(R.id.counterTextView);
        turnCounterB = findViewById(R.id.counter180TextView);

        //Set default amount of turns at the start of each game
        turnCounterW.setText("Z??ge: 0");
        turnCounterB.setText("Z??ge: 0");

        //Assign timer views of both players to textViews
        timerW = findViewById(R.id.timeTextView);
        timerB = findViewById(R.id.time180TextView);

        //Set default amount of time each game
        timerW.setText("Zeit: 15:00");
        timerB.setText("Zeit: 15:00");

        //Call initializeField to set chess board to starting state of a game
        initializeField();

        //Create onClickListener for start button to start the chess game
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piecesOnStartposition(); //Set pieces on their starting position
                start.setVisibility(View.INVISIBLE); //Set start button to invisible

                whiteTurn = true; // boolean that indicates it's whites turn
            }
        });

        //GC = new GameController(fields, w_pieces, bl_pieces, possibles, beatables, whiteTurn, blackTurn, gameOver, timerW, timerB, turnCounterW, turnCounterB, turnCounter,
        //        playerTurnDisplayW, playerTurnDisplayB, getResources().getDrawable(R.drawable.weiss), getResources().getDrawable(R.drawable.schwarz),
        //        getResources().getDrawable(R.drawable.red), getResources().getDrawable(R.drawable.green));

        // Create timertask that updates the game state and calls the turn functions every 50ms
        GC = new GameController();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (GC.getWhiteMin() < 0 || GC.getBlackMin() < 0 || gameOver) {
                    timer.cancel(); // Cancel timer if the timer ran out or checkmate happened
                }
                // As long as the game is not over, call whiteTurn and blackTurn functions, as well as check gameOver conditions
                if (!gameOver) {
//                    GC.whiteTurn(whiteTurn,whiteMiliSec,whiteSec,whiteMin,clickedPositionHasChanged,clickedXPosition,clickedYPosition,turn);
//                    GC.blackTurn(blackTurn,blackMiliSec,blackSec,blackMin,clickedPositionHasChanged,clickedXPosition,clickedYPosition,turn);
                    whiteTurn(whiteTurn);
                    blackTurn(blackTurn);
                    gameOverCondition();
                }
            }
        };
        timer.schedule(timerTask, 10, 50);
    }

    private void initializeField() {
        //Assign chess squares to textviews
        fields[0][0] = (TextView) findViewById(R.id.A1);
        fields[0][1] = (TextView) findViewById(R.id.B1);
        fields[0][2] = (TextView) findViewById(R.id.C1);
        fields[0][3] = (TextView) findViewById(R.id.D1);
        fields[0][4] = (TextView) findViewById(R.id.E1);
        fields[0][5] = (TextView) findViewById(R.id.F1);
        fields[0][6] = (TextView) findViewById(R.id.G1);
        fields[0][7] = (TextView) findViewById(R.id.H1);
        fields[1][0] = (TextView) findViewById(R.id.A2);
        fields[1][1] = (TextView) findViewById(R.id.B2);
        fields[1][2] = (TextView) findViewById(R.id.C2);
        fields[1][3] = (TextView) findViewById(R.id.D2);
        fields[1][4] = (TextView) findViewById(R.id.E2);
        fields[1][5] = (TextView) findViewById(R.id.F2);
        fields[1][6] = (TextView) findViewById(R.id.G2);
        fields[1][7] = (TextView) findViewById(R.id.H2);
        fields[2][0] = (TextView) findViewById(R.id.A3);
        fields[2][1] = (TextView) findViewById(R.id.B3);
        fields[2][2] = (TextView) findViewById(R.id.C3);
        fields[2][3] = (TextView) findViewById(R.id.D3);
        fields[2][4] = (TextView) findViewById(R.id.E3);
        fields[2][5] = (TextView) findViewById(R.id.F3);
        fields[2][6] = (TextView) findViewById(R.id.G3);
        fields[2][7] = (TextView) findViewById(R.id.H3);
        fields[3][0] = (TextView) findViewById(R.id.A4);
        fields[3][1] = (TextView) findViewById(R.id.b4);
        fields[3][2] = (TextView) findViewById(R.id.C4);
        fields[3][3] = (TextView) findViewById(R.id.D4);
        fields[3][4] = (TextView) findViewById(R.id.E4);
        fields[3][5] = (TextView) findViewById(R.id.F4);
        fields[3][6] = (TextView) findViewById(R.id.G4);
        fields[3][7] = (TextView) findViewById(R.id.H4);
        fields[4][0] = (TextView) findViewById(R.id.A5);
        fields[4][1] = (TextView) findViewById(R.id.B5);
        fields[4][2] = (TextView) findViewById(R.id.C5);
        fields[4][3] = (TextView) findViewById(R.id.D5);
        fields[4][4] = (TextView) findViewById(R.id.E5);
        fields[4][5] = (TextView) findViewById(R.id.F5);
        fields[4][6] = (TextView) findViewById(R.id.G5);
        fields[4][7] = (TextView) findViewById(R.id.H5);
        fields[5][0] = (TextView) findViewById(R.id.A6);
        fields[5][1] = (TextView) findViewById(R.id.B6);
        fields[5][2] = (TextView) findViewById(R.id.C6);
        fields[5][3] = (TextView) findViewById(R.id.D6);
        fields[5][4] = (TextView) findViewById(R.id.E6);
        fields[5][5] = (TextView) findViewById(R.id.F6);
        fields[5][6] = (TextView) findViewById(R.id.G6);
        fields[5][7] = (TextView) findViewById(R.id.H6);
        fields[6][0] = (TextView) findViewById(R.id.A7);
        fields[6][1] = (TextView) findViewById(R.id.B7);
        fields[6][2] = (TextView) findViewById(R.id.C7);
        fields[6][3] = (TextView) findViewById(R.id.D7);
        fields[6][4] = (TextView) findViewById(R.id.E7);
        fields[6][5] = (TextView) findViewById(R.id.F7);
        fields[6][6] = (TextView) findViewById(R.id.G7);
        fields[6][7] = (TextView) findViewById(R.id.H7);
        fields[7][0] = (TextView) findViewById(R.id.A8);
        fields[7][1] = (TextView) findViewById(R.id.B8);
        fields[7][2] = (TextView) findViewById(R.id.C8);
        fields[7][3] = (TextView) findViewById(R.id.D8);
        fields[7][4] = (TextView) findViewById(R.id.E8);
        fields[7][5] = (TextView) findViewById(R.id.F8);
        fields[7][6] = (TextView) findViewById(R.id.G8);
        fields[7][7] = (TextView) findViewById(R.id.H8);

        //Assign onClickListener to every chess square
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fields[i][j].setOnClickListener(this::onClick);
            }
        }

        //Assign variables to imageviews
        ImageView bl_bauer1 = findViewById(R.id.bl_bauer1);
        ImageView bl_bauer2 = findViewById(R.id.bl_bauer2);
        ImageView bl_bauer3 = findViewById(R.id.bl_bauer3);
        ImageView bl_bauer4 = findViewById(R.id.bl_bauer4);
        ImageView bl_bauer5 = findViewById(R.id.bl_bauer5);
        ImageView bl_bauer6 = findViewById(R.id.bl_bauer6);
        ImageView bl_bauer7 = findViewById(R.id.bl_bauer7);
        ImageView bl_bauer8 = findViewById(R.id.bl_bauer8);

        ImageView bl_turm1 = findViewById(R.id.bl_turm1);
        ImageView bl_turm2 = findViewById(R.id.bl_turm2);

        ImageView bl_springer1 = findViewById(R.id.bl_springer1);
        ImageView bl_springer2 = findViewById(R.id.bl_springer2);

        ImageView bl_l??ufer1 = findViewById(R.id.bl_l??ufer);
        ImageView bl_l??ufer2 = findViewById(R.id.bl_l??ufer2);

        ImageView bl_queen = findViewById(R.id.bl_queen);
        ImageView bl_king = findViewById(R.id.bl_king);

        //Assign black pawns to a pawn array
        imageViewsBl[0] = bl_bauer1;
        imageViewsBl[1] = bl_bauer2;
        imageViewsBl[2] = bl_bauer3;
        imageViewsBl[3] = bl_bauer4;
        imageViewsBl[4] = bl_bauer5;
        imageViewsBl[5] = bl_bauer6;
        imageViewsBl[6] = bl_bauer7;
        imageViewsBl[7] = bl_bauer8;

        //Initialize white pieces with color, id and position
        Turm turm1_bl = new Turm(bl_turm1, "schwarz", 0, 7, 24);
        Springer springer1_bl = new Springer(bl_springer1, "schwarz", 1, 7, 25);
        L??ufer l??ufer1_bl = new L??ufer(bl_l??ufer1, "schwarz", 2, 7, 26);
        Dame dame_bl = new Dame(bl_queen, "schwarz", 3, 7, 27);
        K??nig k??nig_bl = new K??nig(bl_king, "schwarz", 4, 7, 28);
        L??ufer l??ufer2_bl = new L??ufer(bl_l??ufer2, "schwarz", 5, 7, 29);
        Springer springer2_bl = new Springer(bl_springer2, "schwarz", 6, 7, 30);
        Turm turm2_bl = new Turm(bl_turm2, "schwarz", 7, 7, 31);

        //Add black pieces to black pieces array
        bl_pieces.add(turm1_bl);
        bl_pieces.add(springer1_bl);
        bl_pieces.add(l??ufer1_bl);
        bl_pieces.add(dame_bl);
        bl_pieces.add(k??nig_bl);
        bl_pieces.add(l??ufer2_bl);
        bl_pieces.add(springer2_bl);
        bl_pieces.add(turm2_bl);

        //Create and add black pawns to black pieces array
        for (int i = 0; i < 8; i++) {
            Bauer bauer = new Bauer("schwarz");
            bauer.initialise(imageViewsBl[i], 0 + i, 6, 16 + i);
            bl_pieces.add(bauer);
        }

        //Assign variables to imageviews
        ImageView w_bauer1 = findViewById(R.id.w_bauer1);
        ImageView w_bauer2 = findViewById(R.id.w_bauer2);
        ImageView w_bauer3 = findViewById(R.id.w_bauer3);
        ImageView w_bauer4 = findViewById(R.id.w_bauer4);
        ImageView w_bauer5 = findViewById(R.id.w_bauer5);
        ImageView w_bauer6 = findViewById(R.id.w_bauer6);
        ImageView w_bauer7 = findViewById(R.id.w_bauer7);
        ImageView w_bauer8 = findViewById(R.id.w_bauer8);

        ImageView w_turm1 = findViewById(R.id.w_turm1);
        ImageView w_turm2 = findViewById(R.id.w_turm2);

        ImageView w_springer1 = findViewById(R.id.w_springer1);
        ImageView w_springer2 = findViewById(R.id.w_springer2);

        ImageView w_l??ufer1 = findViewById(R.id.w_l??ufer1);
        ImageView w_l??ufer2 = findViewById(R.id.w_l??ufer2);

        ImageView w_queen = findViewById(R.id.w_queen);
        ImageView w_king = findViewById(R.id.w_king);

        //Assign white pawns to a pawn array
        imageViewsW[0] = w_bauer1;
        imageViewsW[1] = w_bauer2;
        imageViewsW[2] = w_bauer3;
        imageViewsW[3] = w_bauer4;
        imageViewsW[4] = w_bauer5;
        imageViewsW[5] = w_bauer6;
        imageViewsW[6] = w_bauer7;
        imageViewsW[7] = w_bauer8;

        //Initialize white pieces with color, id and position
        Turm turm1_w = new Turm(w_turm1, "wei??", 0, 0, 8);
        Springer springer1_w = new Springer(w_springer1, "wei??", 1, 0, 9);
        L??ufer l??ufer1_w = new L??ufer(w_l??ufer1, "wei??", 2, 0, 10);
        Dame dame_w = new Dame(w_queen, "wei??", 3, 0, 11);
        K??nig k??nig_w = new K??nig(w_king, "wei??", 4, 0, 12);
        L??ufer l??ufer2_w = new L??ufer(w_l??ufer2, "wei??", 5, 0, 13);
        Springer springer2_w = new Springer(w_springer2, "wei??", 6, 0, 14);
        Turm turm2_w = new Turm(w_turm2, "wei??", 7, 0, 15);

        //Add white pieces to white pieces array
        w_pieces.add(turm1_w);
        w_pieces.add(springer1_w);
        w_pieces.add(l??ufer1_w);
        w_pieces.add(dame_w);
        w_pieces.add(k??nig_w);
        w_pieces.add(l??ufer2_w);
        w_pieces.add(springer2_w);
        w_pieces.add(turm2_w);

        //Create and add white pawns to white pieces array
        for (int i = 0; i < 8; i++) {
            Bauer bauer = new Bauer("wei??");
            bauer.initialise(imageViewsW[i], 0 + i, 1, 0 + i);
            w_pieces.add(bauer);
        }
    }
    //Set starting position for every piece
    private void piecesOnStartposition() {
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                w_pieces.get(index).getImg().setX(fields[i][j].getX());
                w_pieces.get(index).getImg().setY(fields[i][j].getY());
                w_pieces.get(index).getImg().bringToFront();

                index++;
            }
        }
        index = 0;

        for (int i = 7; i > 5; i--) {
            for (int j = 0; j < 8; j++) {
                bl_pieces.get(index).getImg().setX(fields[i][j].getX());
                bl_pieces.get(index).getImg().setY(fields[i][j].getY());
                bl_pieces.get(index).getImg().bringToFront();
                index++;
            }
        }
    }
    //Get chess square by coordinates
    public TextView getField(int x, int y) {
        return fields[y][x];
    }
    //Get chess piece by piece id
    public Pieces getPiece(int id) {
        for (Pieces p : w_pieces) {
            if (p.getId() == id) {
                return p;
            }
        }
        for (Pieces p : bl_pieces) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    //Get chess piece by coordinates
    public Pieces getPiece(int x, int y) {
        for (Pieces p : w_pieces) {
            if (p.getxPosition() == x && p.getyPosition() == y) {
                return p;
            }
        }
        for (Pieces p : bl_pieces) {
            if (p.getxPosition() == x && p.getyPosition() == y) {
                return p;
            }
        }
        return null;
    }
    //Show possible moves for pieces
    private void showPossibles(Pieces p) {
        //Clear array of possible moves and beatable pieces before showing new possible moves and beatable pieces
        possibles.clear();
        beatables.clear();
        //Check possible moves and possible beatable pieces for pawn
        if (p instanceof Bauer) {
            //Check white pawn
            if (p.getColor().equalsIgnoreCase("wei??") && p.getyPosition() < 7) {
                if (((Bauer) p).isFirstTurn()
                        && !isClickedFieldTaken(p.getxPosition(), p.getyPosition() + 1)
                        && !isClickedFieldTaken(p.getxPosition(), p.getyPosition() + 2)) {//firstTurn ==true
                    possibles.add(getField(p.getxPosition(), p.getyPosition() + 2));
                    //((Bauer) p).setFirstTurn(false);
                }
                if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() + 1)) {
                    possibles.add(getField(p.getxPosition(), p.getyPosition() + 1));
                }
                if (p.getxPosition() < 7
                        && isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() + 1)
                        && !takenBy(p.getxPosition() + 1, p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                    beatables.add(getField(p.getxPosition() + 1, p.getyPosition() + 1));
                }
                if (p.getxPosition() > 0
                        && isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() + 1)
                        && !takenBy(p.getxPosition() - 1, p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                    beatables.add(getField(p.getxPosition() - 1, p.getyPosition() + 1));
                }
            }
            //Check black pawn
            if (p.getColor().equalsIgnoreCase("schwarz") && p.getyPosition() > 0) {
                if (((Bauer) p).isFirstTurn()
                        && !isClickedFieldTaken(p.getxPosition(), p.getyPosition() - 1)
                        && !isClickedFieldTaken(p.getxPosition(), p.getyPosition() - 2)) {//firstTurn ==true
                    possibles.add(getField(p.getxPosition(), p.getyPosition() - 2));
                    //((Bauer) p).setFirstTurn(false);
                }
                if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() - 1)) {
                    possibles.add(getField(p.getxPosition(), p.getyPosition() - 1));
                }
                if (p.getxPosition() < 7
                        && isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() - 1)
                        && !takenBy(p.getxPosition() + 1, p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                    beatables.add(getField(p.getxPosition() + 1, p.getyPosition() - 1));
                }
                if (p.getxPosition() > 0
                        && isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() - 1)
                        && !takenBy(p.getxPosition() - 1, p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                    beatables.add(getField(p.getxPosition() - 1, p.getyPosition() - 1));
                }
            }
        }
        //Check possible moves and possible beatable pieces for knight
        if (p instanceof Springer) {
            //front right
            if (!isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() + 2)
                    && p.getxPosition() + 1 < 8
                    && p.getyPosition() + 2 < 8) {
                possibles.add(getField(p.getxPosition() + 1, p.getyPosition() + 2));
            } else if (isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() + 2)
                    && p.getxPosition() + 1 < 8 && p.getyPosition() + 2 < 8
                    && !takenBy(p.getxPosition() + 1, p.getyPosition() + 2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() + 1, p.getyPosition() + 2));
            }
            //front left
            if (!isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() + 2)
                    && p.getxPosition() - 1 > -1
                    && p.getyPosition() + 2 < 8) {
                possibles.add(getField(p.getxPosition() - 1, p.getyPosition() + 2));
            } else if (isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() + 2)
                    && p.getxPosition() - 1 > -1 && p.getyPosition() + 2 < 8
                    && !takenBy(p.getxPosition() - 1, p.getyPosition() + 2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() - 1, p.getyPosition() + 2));
            }
            //back right
            if (!isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() - 2)
                    && p.getxPosition() + 1 < 8
                    && p.getyPosition() - 2 > -1) {
                possibles.add(getField(p.getxPosition() + 1, p.getyPosition() - 2));
            } else if (isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() - 2)
                    && p.getxPosition() + 1 < 8 && p.getyPosition() - 2 > -1
                    && !takenBy(p.getxPosition() + 1, p.getyPosition() - 2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() + 1, p.getyPosition() - 2));
            }
            //back left
            if (!isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() - 2)
                    && p.getxPosition() - 1 > -1
                    && p.getyPosition() - 2 > -1) {
                possibles.add(getField(p.getxPosition() - 1, p.getyPosition() - 2));
            } else if (isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() - 2)
                    && p.getxPosition() - 1 > -1 && p.getyPosition() - 2 > -1
                    && !takenBy(p.getxPosition() - 1, p.getyPosition() - 2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() - 1, p.getyPosition() - 2));
            }
            //right front
            if (!isClickedFieldTaken(p.getxPosition() + 2, p.getyPosition() + 1)
                    && p.getxPosition() + 2 < 8
                    && p.getyPosition() + 1 < 8) {
                possibles.add(getField(p.getxPosition() + 2, p.getyPosition() + 1));
            } else if (isClickedFieldTaken(p.getxPosition() + 2, p.getyPosition() + 1)
                    && p.getxPosition() + 2 < 8 && p.getyPosition() + 1 < 8
                    && !takenBy(p.getxPosition() + 2, p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() + 2, p.getyPosition() + 1));
            }
            //right back
            if (!isClickedFieldTaken(p.getxPosition() + 2, p.getyPosition() - 1)
                    && p.getxPosition() + 2 < 8
                    && p.getyPosition() - 1 > -1) {
                possibles.add(getField(p.getxPosition() + 2, p.getyPosition() - 1));
            } else if (isClickedFieldTaken(p.getxPosition() + 2, p.getyPosition() - 1)
                    && p.getxPosition() + 2 < 8 && p.getyPosition() - 1 > -1
                    && !takenBy(p.getxPosition() + 2, p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() + 2, p.getyPosition() - 1));
            }
            //left front
            if (!isClickedFieldTaken(p.getxPosition() - 2, p.getyPosition() + 1)
                    && p.getxPosition() - 2 > -1
                    && p.getyPosition() + 1 < 8) {
                possibles.add(getField(p.getxPosition() - 2, p.getyPosition() + 1));
            } else if (isClickedFieldTaken(p.getxPosition() - 2, p.getyPosition() + 1)
                    && p.getxPosition() - 2 > -1
                    && p.getyPosition() + 1 < 8
                    && !takenBy(p.getxPosition() - 2, p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() - 2, p.getyPosition() + 1));
            }
            //left back
            if (!isClickedFieldTaken(p.getxPosition() - 2, p.getyPosition() - 1)
                    && p.getxPosition() - 2 > -1
                    && p.getyPosition() - 1 > -1) {
                possibles.add(getField(p.getxPosition() - 2, p.getyPosition() - 1));
            } else if (isClickedFieldTaken(p.getxPosition() - 2, p.getyPosition() - 1)
                    && p.getxPosition() - 2 > -1
                    && p.getyPosition() - 1 > -1
                    && !takenBy(p.getxPosition() - 2, p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getField(p.getxPosition() - 2, p.getyPosition() - 1));
            }
        }
        //Check possible moves and possible beatable pieces for rook
        if (p instanceof Turm) {
            //front
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() + i)) {
                        possibles.add(getField(p.getxPosition(), p.getyPosition() + i));
                    } else {
                        if (!takenBy(p.getxPosition(), p.getyPosition() + i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition(), p.getyPosition() + i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //back
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() - i)) {
                        possibles.add(getField(p.getxPosition(), p.getyPosition() - i));
                    } else {
                        if (!takenBy(p.getxPosition(), p.getyPosition() - i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition(), p.getyPosition() - i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //right
            for (int i = 1; i <= 7; i++) {
                if (p.getxPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition() + i, p.getyPosition())) {
                        possibles.add(getField(p.getxPosition() + i, p.getyPosition()));
                    } else {
                        if (!takenBy(p.getxPosition() + i, p.getyPosition()).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() + i, p.getyPosition()));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //left
            for (int i = 1; i <= 7; i++) {
                if (p.getxPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition() - i, p.getyPosition())) {
                        possibles.add(getField(p.getxPosition() - i, p.getyPosition()));
                    } else {
                        if (!takenBy(p.getxPosition() - i, p.getyPosition()).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() - i, p.getyPosition()));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        //Check possible moves and possible beatable pieces for bishop
        if (p instanceof L??ufer) {
            //front right
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() + i <= 7 && p.getxPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition() + i, p.getyPosition() + i)) {
                        possibles.add(getField(p.getxPosition() + i, p.getyPosition() + i));
                    } else {
                        if (!takenBy(p.getxPosition() + i, p.getyPosition() + i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() + i, p.getyPosition() + i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //back right
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() - i >= 0 && p.getxPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition() + i, p.getyPosition() - i)) {
                        possibles.add(getField(p.getxPosition() + i, p.getyPosition() - i));
                    } else {
                        if (!takenBy(p.getxPosition() + i, p.getyPosition() - i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() + i, p.getyPosition() - i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //back left
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() - i >= 0 && p.getxPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition() - i, p.getyPosition() - i)) {
                        possibles.add(getField(p.getxPosition() - i, p.getyPosition() - i));
                    } else {
                        if (!takenBy(p.getxPosition() - i, p.getyPosition() - i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() - i, p.getyPosition() - i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //front left
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() + i <= 7 && p.getxPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition() - i, p.getyPosition() + i)) {
                        possibles.add(getField(p.getxPosition() - i, p.getyPosition() + i));
                    } else {
                        if (!takenBy(p.getxPosition() - i, p.getyPosition() + i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() - i, p.getyPosition() + i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        //Check possible moves and possible beatable pieces for queen
        if (p instanceof Dame) {
            //front
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() + i)) {
                        possibles.add(getField(p.getxPosition(), p.getyPosition() + i));
                    } else {
                        if (!takenBy(p.getxPosition(), p.getyPosition() + i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition(), p.getyPosition() + i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //back
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() - i)) {
                        possibles.add(getField(p.getxPosition(), p.getyPosition() - i));
                    } else {
                        if (!takenBy(p.getxPosition(), p.getyPosition() - i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition(), p.getyPosition() - i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //right
            for (int i = 1; i <= 7; i++) {
                if (p.getxPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition() + i, p.getyPosition())) {
                        possibles.add(getField(p.getxPosition() + i, p.getyPosition()));
                    } else {
                        if (!takenBy(p.getxPosition() + i, p.getyPosition()).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() + i, p.getyPosition()));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //left
            for (int i = 1; i <= 7; i++) {
                if (p.getxPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition() - i, p.getyPosition())) {
                        possibles.add(getField(p.getxPosition() - i, p.getyPosition()));
                    } else {
                        if (!takenBy(p.getxPosition() - i, p.getyPosition()).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() - i, p.getyPosition()));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //front right
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() + i <= 7 && p.getxPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition() + i, p.getyPosition() + i)) {
                        possibles.add(getField(p.getxPosition() + i, p.getyPosition() + i));
                    } else {
                        if (!takenBy(p.getxPosition() + i, p.getyPosition() + i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() + i, p.getyPosition() + i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //back right
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() - i >= 0 && p.getxPosition() + i <= 7) {
                    if (!isClickedFieldTaken(p.getxPosition() + i, p.getyPosition() - i)) {
                        possibles.add(getField(p.getxPosition() + i, p.getyPosition() - i));
                    } else {
                        if (!takenBy(p.getxPosition() + i, p.getyPosition() - i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() + i, p.getyPosition() - i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //back left
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() - i >= 0 && p.getxPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition() - i, p.getyPosition() - i)) {
                        possibles.add(getField(p.getxPosition() - i, p.getyPosition() - i));
                    } else {
                        if (!takenBy(p.getxPosition() - i, p.getyPosition() - i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() - i, p.getyPosition() - i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
            //front left
            for (int i = 1; i <= 7; i++) {
                if (p.getyPosition() + i <= 7 && p.getxPosition() - i >= 0) {
                    if (!isClickedFieldTaken(p.getxPosition() - i, p.getyPosition() + i)) {
                        possibles.add(getField(p.getxPosition() - i, p.getyPosition() + i));
                    } else {
                        if (!takenBy(p.getxPosition() - i, p.getyPosition() + i).equalsIgnoreCase(p.getColor())) {
                            beatables.add(getField(p.getxPosition() - i, p.getyPosition() + i));
                        }
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        //Check possible moves and possible beatable pieces for king
        if (p instanceof K??nig) {
            //front
            if (p.getyPosition() + 1 <= 7) {
                if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() + 1)) {
                    possibles.add(getField(p.getxPosition(), p.getyPosition() + 1));
                } else {
                    if (!takenBy(p.getxPosition(), p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition(), p.getyPosition() + 1));
                    }
                }
            }
            //back
            if (p.getyPosition() - 1 >= 0) {
                if (!isClickedFieldTaken(p.getxPosition(), p.getyPosition() - 1)) {
                    possibles.add(getField(p.getxPosition(), p.getyPosition() - 1));
                } else {
                    if (!takenBy(p.getxPosition(), p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition(), p.getyPosition() - 1));
                    }

                }
            }
            //right
            if (p.getxPosition() + 1 <= 7) {
                if (!isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition())) {
                    possibles.add(getField(p.getxPosition() + 1, p.getyPosition()));
                } else {
                    if (!takenBy(p.getxPosition() + 1, p.getyPosition()).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition() + 1, p.getyPosition()));
                    }
                }
            }
            //left
            if (p.getxPosition() - 1 >= 0) {
                if (!isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition())) {
                    possibles.add(getField(p.getxPosition() - 1, p.getyPosition()));
                } else {
                    if (!takenBy(p.getxPosition() - 1, p.getyPosition()).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition() - 1, p.getyPosition()));
                    }
                }
            }
            //front right
            if (p.getyPosition() + 1 <= 7 && p.getxPosition() + 1 <= 7) {
                if (!isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() + 1)) {
                    possibles.add(getField(p.getxPosition() + 1, p.getyPosition() + 1));
                } else {
                    if (!takenBy(p.getxPosition() + 1, p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition() + 1, p.getyPosition() + 1));
                    }
                }
            }
            //back right
            if (p.getyPosition() - 1 >= 0 && p.getxPosition() + 1 <= 7) {
                if (!isClickedFieldTaken(p.getxPosition() + 1, p.getyPosition() - 1)) {
                    possibles.add(getField(p.getxPosition() + 1, p.getyPosition() - 1));
                } else {
                    if (!takenBy(p.getxPosition() + 1, p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition() + 1, p.getyPosition() - 1));
                    }
                }
            }
            //back left
            if (p.getyPosition() - 1 >= 0 && p.getxPosition() - 1 >= 0) {
                if (!isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() - 1)) {
                    possibles.add(getField(p.getxPosition() - 1, p.getyPosition() - 1));
                } else {
                    if (!takenBy(p.getxPosition() - 1, p.getyPosition() - 1).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition() - 1, p.getyPosition() - 1));
                    }
                }
            }
            //front left
            if (p.getyPosition() + 1 <= 7 && p.getxPosition() - 1 >= 0) {
                if (!isClickedFieldTaken(p.getxPosition() - 1, p.getyPosition() + 1)) {
                    possibles.add(getField(p.getxPosition() - 1, p.getyPosition() + 1));
                } else {
                    if (!takenBy(p.getxPosition() - 1, p.getyPosition() + 1).equalsIgnoreCase(p.getColor())) {
                        beatables.add(getField(p.getxPosition() - 1, p.getyPosition() + 1));
                    }
                }
            }
            //Castling left
            if (p.getxPosition() == 4
                    && p.getyPosition() == 0
                    && p.getColor().equalsIgnoreCase("wei??")) {
                if (!isClickedFieldTaken(3, 0)
                        && !isClickedFieldTaken(2, 0)
                        && !isClickedFieldTaken(1, 0)) {
                    if (getPiece(0, 0) != null && getPiece(0, 0) instanceof Turm) {
                        possibles.add(getField(0, 0));
                    }
                } else if (!isClickedFieldTaken(5, 0) && !isClickedFieldTaken(6, 0)) {
                    if (getPiece(7, 0) != null && getPiece(7, 0) instanceof Turm) {
                        possibles.add(getField(7, 0));
                    }
                }
            }
            //Castling right
            if (p.getxPosition() == 4
                    && p.getyPosition() == 7
                    && p.getColor().equalsIgnoreCase("schwarz")) {
                if (!isClickedFieldTaken(3, 7)
                        && !isClickedFieldTaken(2, 7)
                        && !isClickedFieldTaken(1, 7)) {
                    if (getPiece(0, 7) != null && getPiece(0, 7) instanceof Turm) {
                        possibles.add(getField(0, 7));
                    }
                } else if (!isClickedFieldTaken(5, 7) && !isClickedFieldTaken(6, 7)) {
                    if (getPiece(7, 7) != null && getPiece(7, 7) instanceof Turm) {
                        possibles.add(getField(7, 7));
                    }
                }
            }
        }
        //Highlight possible moves green
        for (TextView t : possibles) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (t == fields[i][j]) {
                        try {
                            fields[i][j].setBackground(getResources().getDrawable(R.drawable.green));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        //Highlight beatable red
        for (TextView t : beatables) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (t == fields[i][j]) {
                        try {
                            fields[i][j].setBackground(getResources().getDrawable(R.drawable.red));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    //Undo possible moves when clicking piece again
    private void undoPossibles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    fields[i][j].setBackground(getResources().getDrawable(R.drawable.schwarz));
                } else {
                    fields[i][j].setBackground(getResources().getDrawable(R.drawable.weiss));
                }
            }
        }
    }
    //Check if chess square is taken by another piece
    public boolean isClickedFieldTaken(int x, int y) {
        for (int i = 0; i < 32; i++) {
            if (getPiece(i).getxPosition() == x && getPiece(i).getyPosition() == y) {
                return true;
            }
        }
        return false;
    }
    //Get piece on a taken chess square
    public String takenBy(int x, int y) {
        return getPiece(x, y).getColor();
    }
    //Check if it's whites turn
    private void whiteTurn(boolean playerTurn) {
        if (playerTurn) {
            //GameController counts and updates the timer textview
            GC.refreshTimer(timerW,"white");

            //check for changed click position
            if (clickedPositionHasChanged) {
                //Check for first click
                if (clickedCounter % 2 == 1) {
                    //Check if clicked on Piece
                    if (isClickedFieldTaken(clickedXPosition, clickedYPosition)) {
                        if (getPiece(clickedXPosition, clickedYPosition) != null) {
                            //temporary Variable to know which Piece got clicked
                            tmpPiece = getPiece(clickedXPosition, clickedYPosition).getId();
                            //ids over 15 are black pieces
                            if (tmpPiece > 15) {
                                return;
                            }
                            //highlight possible moves, beatables inclusive
                            showPossibles(getPiece(tmpPiece));
                        }
                        //everything is done for the first click -> boolean set false
                        clickedPositionHasChanged = false;
                    } else {
                        // if first click was not on a piece
                        clickedPositionHasChanged = false;
                        clickedCounter++;
                    }
                } else {
                    //this else block is for the second click while playerturn
                    //tmpPiece under 16 to check if its a white Piece
                    if (tmpPiece < 16) {
                        //temporary variable for the clicked field -> clarity Reasons
                        tmpField = getField(clickedXPosition, clickedYPosition);
                        //check if second click is on a white Piece or an empty field
                        if (isClickedFieldTaken(clickedXPosition, clickedYPosition)
                                && takenBy(clickedXPosition, clickedYPosition).equalsIgnoreCase("wei??")) {
                            //check is Player might want to do castling
                            if (getPiece(tmpPiece) instanceof K??nig
                                    && getPiece(clickedXPosition, clickedYPosition) != null
                                    && getPiece(clickedXPosition, clickedYPosition) instanceof Turm
                                    && getPiece(clickedXPosition, clickedYPosition).getColor().equalsIgnoreCase("wei??")) {
                                if (clickedXPosition == 0) {
                                    getPiece(clickedXPosition, clickedYPosition).getImg().setX(getField(3, 0).getX());
                                    getPiece(clickedXPosition, clickedYPosition).setxPosition(3);
                                    getPiece(tmpPiece).getImg().setX(getField(2, 0).getX());
                                    getPiece(tmpPiece).setxPosition(2);
                                } else {
                                    getPiece(clickedXPosition, clickedYPosition).getImg().setX(getField(5, 0).getX());
                                    getPiece(clickedXPosition, clickedYPosition).setxPosition(5);
                                    getPiece(tmpPiece).getImg().setX(getField(6, 0).getX());
                                    getPiece(tmpPiece).setxPosition(6);
                                }

                                //set variables and textviews at the end of whites turn
                                whiteTurn = false;
                                blackTurn = true;
                                playerTurnDisplayW.setText("Not your turn");
                                playerTurnDisplayB.setText("Your turn");
                                GC.refreshTurnCounter(turnCounterW,turnCounterB);
                                undoPossibles();
                                clickedPositionHasChanged = false;
                                return;
                            } else {
                                //if castling is no option the highlighted fields return to normal colors
                                undoPossibles();
                                clickedPositionHasChanged = false;
                                return;
                            }
                        //when second click initializes to beat a black piece
                        } else if (beatables.contains(getField(clickedXPosition, clickedYPosition))) {
                            //set coordinates and stats for affected pieces
                            getPiece(clickedXPosition, clickedYPosition).getImg().setVisibility(View.INVISIBLE);
                            getPiece(clickedXPosition, clickedYPosition).setxPosition(10);
                            getPiece(10, clickedYPosition).setyPosition(10);
                            getPiece(10, 10).setAlive(false);
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            //check if pawn got moved
                            if (getPiece(tmpPiece) instanceof Bauer) {
                                //pawn firstmove is false so it cannot move two steps
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            //set variables and textviews at the end of whites turn
                            whiteTurn = false;
                            blackTurn = true;
                            playerTurnDisplayW.setText("Not your turn");
                            playerTurnDisplayB.setText("Your turn");
                            GC.refreshTurnCounter(turnCounterW,turnCounterB);
                        //when second click initializes to move a piece
                        } else if (possibles.contains(getField(clickedXPosition, clickedYPosition))) {
                            //set coordinates and stats for affected pieces
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            //check if pawn got moved
                            if (getPiece(tmpPiece) instanceof Bauer) {
                                //pawn firstmove is false so it cannot move two steps
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            //set variables and textviews at the end of whites turn
                            whiteTurn = false;
                            blackTurn = true;
                            playerTurnDisplayW.setText("Not your turn");
                            playerTurnDisplayB.setText("Your turn");
                            GC.refreshTurnCounter(turnCounterW,turnCounterB);
                        }
                    } else {
                        //should not happen
                        return;
                    }
                    //when the turn ended the highlighted fields return to normal colors
                    undoPossibles();
                    clickedPositionHasChanged = false;
                }
            }
        }
    }
    //same procedure as whiteTurn
    private void blackTurn(boolean playerTurn) {
        if (playerTurn) {
            GC.refreshTimer(timerB,"black");
            if (clickedPositionHasChanged) {
                if (clickedCounter % 2 == 1) {
                    if (isClickedFieldTaken(clickedXPosition, clickedYPosition)) {
                        if (getPiece(clickedXPosition, clickedYPosition) != null) {
                            tmpPiece = getPiece(clickedXPosition, clickedYPosition).getId();
                            if (tmpPiece < 16) {
                                return;
                            }
                            showPossibles(getPiece(tmpPiece));
                        }
                        clickedPositionHasChanged = false;

                    } else {
                        clickedPositionHasChanged = false;
                        clickedCounter++;
                    }
                } else {
                    if (tmpPiece > 15) {
                        tmpField = getField(clickedXPosition, clickedYPosition);
                        if (isClickedFieldTaken(clickedXPosition, clickedYPosition)
                                && takenBy(clickedXPosition, clickedYPosition).equalsIgnoreCase("schwarz")) {
                            if (getPiece(tmpPiece) instanceof K??nig
                                    && getPiece(clickedXPosition, clickedYPosition) != null
                                    && getPiece(clickedXPosition, clickedYPosition) instanceof Turm
                                    && getPiece(clickedXPosition, clickedYPosition).getColor().equalsIgnoreCase("schwarz")) {
                                if (clickedXPosition == 0) {
                                    getPiece(clickedXPosition, clickedYPosition).getImg().setX(getField(3, 0).getX());
                                    getPiece(clickedXPosition, clickedYPosition).setxPosition(3);
                                    getPiece(tmpPiece).getImg().setX(getField(2, 0).getX());
                                    getPiece(tmpPiece).setxPosition(2);
                                } else {
                                    getPiece(clickedXPosition, clickedYPosition).getImg().setX(getField(5, 0).getX());
                                    getPiece(clickedXPosition, clickedYPosition).setxPosition(5);
                                    getPiece(tmpPiece).getImg().setX(getField(6, 0).getX());
                                    getPiece(tmpPiece).setxPosition(6);
                                }
                                blackTurn = false;
                                whiteTurn = true;
                                playerTurnDisplayW.setText("Your turn");
                                playerTurnDisplayB.setText("Not your turn");
                                GC.refreshTurnCounter(turnCounterW,turnCounterB);
                                undoPossibles();
                                clickedPositionHasChanged = false;
                                return;
                            } else {
                                undoPossibles();
                                clickedPositionHasChanged = false;
                                return;
                            }
                        } else if (beatables.contains(getField(clickedXPosition, clickedYPosition))) {
                            getPiece(clickedXPosition, clickedYPosition).setAlive(false);
                            getPiece(clickedXPosition, clickedYPosition).getImg().setVisibility(View.INVISIBLE);
                            getPiece(clickedXPosition, clickedYPosition).setxPosition(10);
                            getPiece(10, clickedYPosition).setyPosition(10);
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            if (getPiece(tmpPiece) instanceof Bauer) {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            blackTurn = false;
                            whiteTurn = true;
                            playerTurnDisplayW.setText("Your turn");
                            playerTurnDisplayB.setText("Not your turn");
                            GC.refreshTurnCounter(turnCounterW,turnCounterB);
                        } else if (possibles.contains(getField(clickedXPosition, clickedYPosition))) {
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpField.getX());
                            getPiece(tmpPiece).getImg().setY(tmpField.getY());
                            if (getPiece(tmpPiece) instanceof Bauer) {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                            blackTurn = false;
                            whiteTurn = true;
                            playerTurnDisplayW.setText("Your turn");
                            playerTurnDisplayB.setText("Not your turn");
                            GC.refreshTurnCounter(turnCounterW,turnCounterB);
                        }
                    } else {
                        return;
                    }
                    undoPossibles();
                    clickedPositionHasChanged = false;
                }
            }
        }
    }
    //Check game over conditions
    public void gameOverCondition() {
        //Check if any of the kings is beaten
        for (Pieces p : w_pieces) {
            if (p instanceof K??nig && !p.isAlive()) {
                Intent intent = new Intent(GameView.this, Gameover.class);
                intent.putExtra(DATA_KEY, "white");
                startActivity(intent);
                gameOver = true;
            }
        }
        for (Pieces p : bl_pieces) {
            if (p instanceof K??nig && !p.isAlive()) {
                Intent intent = new Intent(GameView.this, Gameover.class);
                intent.putExtra(DATA_KEY, "black");
                startActivity(intent);
                gameOver = true;
            }
        }
        //Check if any timer ran out
        if (GC.getWhiteMin() < 0) {
            Intent intent = new Intent(GameView.this, Gameover.class);
            intent.putExtra(DATA_KEY, "white");
            startActivity(intent);
            gameOver = true;
        }
        if (GC.getBlackMin() <= 0) {
            Intent intent = new Intent(GameView.this, Gameover.class);
            intent.putExtra(DATA_KEY, "black");
            startActivity(intent);
            gameOver = true;
        }
    }
    //Override onClick to change clickedPosition
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.A1:
                setClickedXPosition(0);
                setClickedYPosition(0);
                break;
            case R.id.A2:
                setClickedXPosition(0);
                setClickedYPosition(1);
                break;
            case R.id.A3:
                setClickedXPosition(0);
                setClickedYPosition(2);
                break;
            case R.id.A4:
                setClickedXPosition(0);
                setClickedYPosition(3);
                break;
            case R.id.A5:
                setClickedXPosition(0);
                setClickedYPosition(4);
                break;
            case R.id.A6:
                setClickedXPosition(0);
                setClickedYPosition(5);
                break;
            case R.id.A7:
                setClickedXPosition(0);
                setClickedYPosition(6);
                break;
            case R.id.A8:
                setClickedXPosition(0);
                setClickedYPosition(7);
                break;
            case R.id.B1:
                setClickedXPosition(1);
                setClickedYPosition(0);
                break;
            case R.id.B2:
                setClickedXPosition(1);
                setClickedYPosition(1);
                break;
            case R.id.B3:
                setClickedXPosition(1);
                setClickedYPosition(2);
                break;
            case R.id.b4:
                setClickedXPosition(1);
                setClickedYPosition(3);
                break;
            case R.id.B5:
                setClickedXPosition(1);
                setClickedYPosition(4);
                break;
            case R.id.B6:
                setClickedXPosition(1);
                setClickedYPosition(5);
                break;
            case R.id.B7:
                setClickedXPosition(1);
                setClickedYPosition(6);
                break;
            case R.id.B8:
                setClickedXPosition(1);
                setClickedYPosition(7);
                break;
            case R.id.C1:
                setClickedXPosition(2);
                setClickedYPosition(0);
                break;
            case R.id.C2:
                setClickedXPosition(2);
                setClickedYPosition(1);
                break;
            case R.id.C3:
                setClickedXPosition(2);
                setClickedYPosition(2);
                break;
            case R.id.C4:
                setClickedXPosition(2);
                setClickedYPosition(3);
                break;
            case R.id.C5:
                setClickedXPosition(2);
                setClickedYPosition(4);
                break;
            case R.id.C6:
                setClickedXPosition(2);
                setClickedYPosition(5);
                break;
            case R.id.C7:
                setClickedXPosition(2);
                setClickedYPosition(6);
                break;
            case R.id.C8:
                setClickedXPosition(2);
                setClickedYPosition(7);
                break;
            case R.id.D1:
                setClickedXPosition(3);
                setClickedYPosition(0);
                break;
            case R.id.D2:
                setClickedXPosition(3);
                setClickedYPosition(1);
                break;
            case R.id.D3:
                setClickedXPosition(3);
                setClickedYPosition(2);
                break;
            case R.id.D4:
                setClickedXPosition(3);
                setClickedYPosition(3);
                break;
            case R.id.D5:
                setClickedXPosition(3);
                setClickedYPosition(4);
                break;
            case R.id.D6:
                setClickedXPosition(3);
                setClickedYPosition(5);
                break;
            case R.id.D7:
                setClickedXPosition(3);
                setClickedYPosition(6);
                break;
            case R.id.D8:
                setClickedXPosition(3);
                setClickedYPosition(7);
                break;
            case R.id.E1:
                setClickedXPosition(4);
                setClickedYPosition(0);
                break;
            case R.id.E2:
                setClickedXPosition(4);
                setClickedYPosition(1);
                break;
            case R.id.E3:
                setClickedXPosition(4);
                setClickedYPosition(2);
                break;
            case R.id.E4:
                setClickedXPosition(4);
                setClickedYPosition(3);
                break;
            case R.id.E5:
                setClickedXPosition(4);
                setClickedYPosition(4);
                break;
            case R.id.E6:
                setClickedXPosition(4);
                setClickedYPosition(5);
                break;
            case R.id.E7:
                setClickedXPosition(4);
                setClickedYPosition(6);
                break;
            case R.id.E8:
                setClickedXPosition(4);
                setClickedYPosition(7);
                break;
            case R.id.F1:
                setClickedXPosition(5);
                setClickedYPosition(0);
                break;
            case R.id.F2:
                setClickedXPosition(5);
                setClickedYPosition(1);
                break;
            case R.id.F3:
                setClickedXPosition(5);
                setClickedYPosition(2);
                break;
            case R.id.F4:
                setClickedXPosition(5);
                setClickedYPosition(3);
                break;
            case R.id.F5:
                setClickedXPosition(5);
                setClickedYPosition(4);
                break;
            case R.id.F6:
                setClickedXPosition(5);
                setClickedYPosition(5);
                break;
            case R.id.F7:
                setClickedXPosition(5);
                setClickedYPosition(6);
                break;
            case R.id.F8:
                setClickedXPosition(5);
                setClickedYPosition(7);
                break;
            case R.id.G1:
                setClickedXPosition(6);
                setClickedYPosition(0);
                break;
            case R.id.G2:
                setClickedXPosition(6);
                setClickedYPosition(1);
                break;
            case R.id.G3:
                setClickedXPosition(6);
                setClickedYPosition(2);
                break;
            case R.id.G4:
                setClickedXPosition(6);
                setClickedYPosition(3);
                break;
            case R.id.G5:
                setClickedXPosition(6);
                setClickedYPosition(4);
                break;
            case R.id.G6:
                setClickedXPosition(6);
                setClickedYPosition(5);
                break;
            case R.id.G7:
                setClickedXPosition(6);
                setClickedYPosition(6);
                break;
            case R.id.G8:
                setClickedXPosition(6);
                setClickedYPosition(7);
                break;
            case R.id.H1:
                setClickedXPosition(7);
                setClickedYPosition(0);
                break;
            case R.id.H2:
                setClickedXPosition(7);
                setClickedYPosition(1);
                break;
            case R.id.H3:
                setClickedXPosition(7);
                setClickedYPosition(2);
                break;
            case R.id.H4:
                setClickedXPosition(7);
                setClickedYPosition(3);
                break;
            case R.id.H5:
                setClickedXPosition(7);
                setClickedYPosition(4);
                break;
            case R.id.H6:
                setClickedXPosition(7);
                setClickedYPosition(5);
                break;
            case R.id.H7:
                setClickedXPosition(7);
                setClickedYPosition(6);
                break;
            case R.id.H8:
                setClickedXPosition(7);
                setClickedYPosition(7);
                break;
        }
        clickedCounter++;
        clickedPositionHasChanged = true;
    }
    //Set clicked position x axis
    public void setClickedXPosition(int clickedXPosition) {
        this.clickedXPosition = clickedXPosition;
    }
    //Set clicked position y axis
    public void setClickedYPosition(int clickedYPosition) {
        this.clickedYPosition = clickedYPosition;
    }
}
