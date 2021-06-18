package de.haw_hamburg.schachspiel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class GameView extends AppCompatActivity implements View.OnClickListener{

    private boolean clickedPositionhasChanged = false;
    private static final String DATA_KEY = "GameView.Data";
    private Button playerTurnDisplayW;
    private Button playerTurnDisplayB;
    private Button start;
    private int clickedXPosition;
    private int clickedYPosition;
    private int turn = 0;
    private int tmpPiece = 0;
    private boolean whiteTurn = false;
    private boolean blackTurn = false;
    private boolean gameOver = false;
    private ImageView field;

    private TextView tmpField;
    private ArrayList<TextView> possibles = new ArrayList();
    private ArrayList<TextView> beatables = new ArrayList();

    private TextView[][] fields = new TextView[8][8];
    private ArrayList<Pieces> bl_pieces = new ArrayList();
    private ArrayList<Pieces> w_pieces = new ArrayList();
    private ImageView[] imageViewsBl = new ImageView[8];
    private ImageView[] imageViewsW = new ImageView[8];


    private int turnCounter = 0;
    private TextView turnCounterW;
    private TextView turnCounterB;

    private int whiteMiliSec = 0;
    private int whiteSec = 60;
    private int whiteMin = 14;
    private TextView timerW;

    private int blackMiliSec = 0;
    private int blackSec = 60;
    private int blackMin = 14;
    private TextView timerB;

    private GameController GC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);

        field = findViewById(R.id.schachfeld);


        playerTurnDisplayW = findViewById(R.id.zugBestätigenButton);
        playerTurnDisplayB = findViewById(R.id.zugBestätigenButton180);

        start = findViewById(R.id.startGame);

        ImageButton muteSound = findViewById(R.id.soundIngameButton);
        ImageButton helpButton = findViewById(R.id.helpButton);

        turnCounterW = findViewById(R.id.counterTextView);
        turnCounterB = findViewById(R.id.counter180TextView);
        turnCounterW.setText("Züge: 0");
        turnCounterB.setText("Züge: 0");

        timerW = findViewById(R.id.timeTextView);
        timerW.setText("Zeit: 15:00");
        timerB = findViewById(R.id.time180TextView);
        timerB.setText("Zeit:15:00");

        initializeField();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piecesOnStartposition();
                start.setVisibility(View.INVISIBLE);
                playerTurnDisplayW.setText(" !White starts! ");
                playerTurnDisplayB.setText(" !White starts! ");
                whiteTurn = true;
            }
        });

        GC = new GameController(fields,w_pieces,bl_pieces,possibles,beatables,whiteTurn,blackTurn,gameOver,timerW,timerB,turnCounterW,turnCounterB, turnCounter,
                playerTurnDisplayW, playerTurnDisplayB,getResources().getDrawable(R.drawable.weiss),getResources().getDrawable(R.drawable.schwarz),
                getResources().getDrawable(R.drawable.red),getResources().getDrawable(R.drawable.green));
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                if (whiteMin<0 || blackMin<0 || gameOver){
                    timer.cancel();
                }
                if (!gameOver){
//                    GC.whiteTurn(whiteTurn,whiteMiliSec,whiteSec,whiteMin,clickedPositionhasChanged,clickedXPosition,clickedYPosition,turn);
//                    GC.blackTurn(blackTurn,blackMiliSec,blackSec,blackMin,clickedPositionhasChanged,clickedXPosition,clickedYPosition,turn);
                    whiteTurn(whiteTurn);
                    blackTurn(blackTurn);
                    kingDead();
                }

            }
        };
        timer.schedule(timerTask,10,50);
    }


    private void initializeField(){

        fields[0][0] = (TextView) findViewById(R.id.A1);
        fields[0][1] = (TextView)findViewById(R.id.B1);
        fields[0][2] = (TextView)findViewById(R.id.C1);
        fields[0][3] = (TextView)findViewById(R.id.D1);
        fields[0][4] = (TextView)findViewById(R.id.E1);
        fields[0][5] = (TextView)findViewById(R.id.F1);
        fields[0][6] = (TextView)findViewById(R.id.G1);
        fields[0][7] = (TextView)findViewById(R.id.H1);
        fields[1][0] = (TextView)findViewById(R.id.A2);
        fields[1][1] = (TextView)findViewById(R.id.B2);
        fields[1][2] = (TextView)findViewById(R.id.C2);
        fields[1][3] = (TextView)findViewById(R.id.D2);
        fields[1][4] = (TextView)findViewById(R.id.E2);
        fields[1][5] = (TextView)findViewById(R.id.F2);
        fields[1][6] = (TextView)findViewById(R.id.G2);
        fields[1][7] = (TextView)findViewById(R.id.H2);
        fields[2][0] = (TextView)findViewById(R.id.A3);
        fields[2][1] = (TextView)findViewById(R.id.B3);
        fields[2][2] = (TextView)findViewById(R.id.C3);
        fields[2][3] = (TextView)findViewById(R.id.D3);
        fields[2][4] = (TextView)findViewById(R.id.E3);
        fields[2][5] = (TextView)findViewById(R.id.F3);
        fields[2][6] = (TextView)findViewById(R.id.G3);
        fields[2][7] = (TextView)findViewById(R.id.H3);
        fields[3][0] = (TextView)findViewById(R.id.A4);
        fields[3][1] = (TextView)findViewById(R.id.b4);
        fields[3][2] = (TextView)findViewById(R.id.C4);
        fields[3][3] = (TextView)findViewById(R.id.D4);
        fields[3][4] = (TextView)findViewById(R.id.E4);
        fields[3][5] = (TextView)findViewById(R.id.F4);
        fields[3][6] = (TextView)findViewById(R.id.G4);
        fields[3][7] = (TextView)findViewById(R.id.H4);
        fields[4][0] = (TextView)findViewById(R.id.A5);
        fields[4][1] = (TextView)findViewById(R.id.B5);
        fields[4][2] = (TextView)findViewById(R.id.C5);
        fields[4][3] = (TextView)findViewById(R.id.D5);
        fields[4][4] = (TextView)findViewById(R.id.E5);
        fields[4][5] = (TextView)findViewById(R.id.F5);
        fields[4][6] = (TextView)findViewById(R.id.G5);
        fields[4][7] = (TextView)findViewById(R.id.H5);
        fields[5][0] = (TextView)findViewById(R.id.A6);
        fields[5][1] = (TextView)findViewById(R.id.B6);
        fields[5][2] = (TextView)findViewById(R.id.C6);
        fields[5][3] = (TextView)findViewById(R.id.D6);
        fields[5][4] = (TextView)findViewById(R.id.E6);
        fields[5][5] = (TextView)findViewById(R.id.F6);
        fields[5][6] = (TextView)findViewById(R.id.G6);
        fields[5][7] = (TextView)findViewById(R.id.H6);
        fields[6][0] = (TextView)findViewById(R.id.A7);
        fields[6][1] = (TextView)findViewById(R.id.B7);
        fields[6][2] = (TextView)findViewById(R.id.C7);
        fields[6][3] = (TextView)findViewById(R.id.D7);
        fields[6][4] = (TextView)findViewById(R.id.E7);
        fields[6][5] = (TextView)findViewById(R.id.F7);
        fields[6][6] = (TextView)findViewById(R.id.G7);
        fields[6][7] = (TextView)findViewById(R.id.H7);
        fields[7][0] = (TextView)findViewById(R.id.A8);
        fields[7][1] = (TextView)findViewById(R.id.B8);
        fields[7][2] = (TextView)findViewById(R.id.C8);
        fields[7][3] = (TextView)findViewById(R.id.D8);
        fields[7][4] = (TextView)findViewById(R.id.E8);
        fields[7][5] = (TextView)findViewById(R.id.F8);
        fields[7][6] = (TextView)findViewById(R.id.G8);
        fields[7][7] = (TextView)findViewById(R.id.H8);

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

        ImageView bl_läufer1 = findViewById(R.id.bl_läufer);
        ImageView bl_läufer2 = findViewById(R.id.bl_läufer2);

        ImageView bl_queen = findViewById(R.id.bl_queen);
        ImageView bl_king = findViewById(R.id.bl_king);

        imageViewsBl[0] = bl_bauer1;
        imageViewsBl[1] = bl_bauer2;
        imageViewsBl[2] = bl_bauer3;
        imageViewsBl[3] = bl_bauer4;
        imageViewsBl[4] = bl_bauer5;
        imageViewsBl[5] = bl_bauer6;
        imageViewsBl[6] = bl_bauer7;
        imageViewsBl[7] = bl_bauer8;

        Turm turm1_bl = new Turm(bl_turm1,"schwarz",0,7,24);
        Springer springer1_bl = new Springer(bl_springer1,"schwarz", 1, 7, 25);
        Läufer läufer1_bl = new Läufer(bl_läufer1, "schwarz", 2,7,26);
        Dame dame_bl = new Dame(bl_queen, "schwarz",3,7,27);
        König könig_bl = new König(bl_king, "schwarz", 4,7,28);
        Läufer läufer2_bl = new Läufer(bl_läufer2, "schwarz",5,7,29);
        Springer springer2_bl = new Springer(bl_springer2,"schwarz", 6, 7, 30);
        Turm turm2_bl = new Turm(bl_turm2,"schwarz",7,7,31);

        bl_pieces.add(turm1_bl);
        bl_pieces.add(springer1_bl);
        bl_pieces.add(läufer1_bl);
        bl_pieces.add(dame_bl);
        bl_pieces.add(könig_bl);
        bl_pieces.add(läufer2_bl);
        bl_pieces.add(springer2_bl);
        bl_pieces.add(turm2_bl);

        for(int i = 0; i<8;i++){
            Bauer bauer = new Bauer("schwarz");
            bauer.initialise(imageViewsBl[i], 0+i, 6, 16+i);
            bl_pieces.add(bauer);
        }

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

        ImageView w_läufer1 = findViewById(R.id.w_läufer1);
        ImageView w_läufer2 = findViewById(R.id.w_läufer2);

        ImageView w_queen = findViewById(R.id.w_queen);
        ImageView w_king = findViewById(R.id.w_king);

        imageViewsW[0] = w_bauer1;
        imageViewsW[1] = w_bauer2;
        imageViewsW[2] = w_bauer3;
        imageViewsW[3] = w_bauer4;
        imageViewsW[4] = w_bauer5;
        imageViewsW[5] = w_bauer6;
        imageViewsW[6] = w_bauer7;
        imageViewsW[7] = w_bauer8;

        Turm turm1_w = new Turm(w_turm1,"weiß",0,0,8);
        Springer springer1_w = new Springer(w_springer1,"weiß", 1, 0, 9);
        Läufer läufer1_w = new Läufer(w_läufer1, "weiß", 2,0,10);
        Dame dame_w = new Dame(w_queen, "weiß",3,0,11);
        König könig_w = new König(w_king, "weiß", 4,0,12);
        Läufer läufer2_w = new Läufer(w_läufer2, "weiß",5,0,13);
        Springer springer2_w = new Springer(w_springer2,"weiß", 6, 0, 14);
        Turm turm2_w = new Turm(w_turm2,"weiß",7,0,15);

        w_pieces.add(turm1_w);
        w_pieces.add(springer1_w);
        w_pieces.add(läufer1_w);
        w_pieces.add(dame_w);
        w_pieces.add(könig_w);
        w_pieces.add(läufer2_w);
        w_pieces.add(springer2_w);
        w_pieces.add(turm2_w);

        for(int i = 0; i<8;i++){
            Bauer bauer = new Bauer("weiß");
            bauer.initialise(imageViewsW[i], 0+i, 1, 0+i);
            w_pieces.add(bauer);
        }

        for (int i=0;i<8;i++){
            for (int j=0; j<8;j++){
                fields[i][j].setOnClickListener(this::onClick);
            }
        }
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

    public TextView getField(int x, int y){

        return fields[y][x];
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


    private void showPossibles(Pieces p){

        possibles.clear();
        beatables.clear();

        if (p instanceof Bauer){

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
                            fields[i][j].setBackground(getResources().getDrawable(R.drawable.green));
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
                            fields[i][j].setBackground(getResources().getDrawable(R.drawable.red));
                        }catch (Exception e){

                        }
                    }
                }
            }
        }
    }

    private void undoPossibles(){
        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                if ((i%2==1&&j%2==1) || (i%2==0&&j%2==0)){
                    fields[i][j].setBackground(getResources().getDrawable(R.drawable.schwarz));
                }else {
                    fields[i][j].setBackground(getResources().getDrawable(R.drawable.weiss));
                }
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

    private void whiteTurn(boolean playerTurn)
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

    private void blackTurn(boolean playerTurn)
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

    public void kingDead(){
        for (Pieces p: w_pieces){
            if(p instanceof König && !p.isAlive()){
                Intent intent = new Intent(GameView.this, Gameover.class);
                intent.putExtra(DATA_KEY,"white");
                startActivity(intent);
                gameOver=true;
            }
        }
        for (Pieces p: bl_pieces){
            if(p instanceof König && !p.isAlive()){
                Intent intent = new Intent(GameView.this, Gameover.class);
                intent.putExtra(DATA_KEY,"black");
                startActivity(intent);
                gameOver=true;
            }
        }


        if (whiteMin<0){

            Intent intent = new Intent(GameView.this, Gameover.class);
            intent.putExtra(DATA_KEY,"white");
            startActivity(intent);
            gameOver=true;
        }
        if (blackMin<=0){
            Intent intent = new Intent(GameView.this, Gameover.class);
            intent.putExtra(DATA_KEY,"black");
            startActivity(intent);
            gameOver=true;
        }
    }

    // Zug Counter
    // ----------
    public void refreshTurnCounter() {
        if (turnCounter %2 == 1) {
            turnCounterW.setText("Züge: " + ((turnCounter -1)/2 + 1));
        }else{
            turnCounterB.setText("Züge: " + (turnCounter /2));
        }
    }
    

    @Override
    public void onClick(View v){

        switch (v.getId()){

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
        turn++;
        clickedPositionhasChanged = true;
    }

    public void setClickedXPosition(int clickedXPosition) {
        this.clickedXPosition = clickedXPosition;
    }

    public void setClickedYPosition(int clickedYPosition) {
        this.clickedYPosition = clickedYPosition;
    }
}
