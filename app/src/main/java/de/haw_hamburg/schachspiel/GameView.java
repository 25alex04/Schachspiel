package de.haw_hamburg.schachspiel;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends AppCompatActivity implements View.OnClickListener{

    boolean clickedPositionhasChanged = false;
    boolean endTurn = false;
    public Button bestaetigenP1;
    public Button bestaetigenP2;
    public Button start;
    int clickedXPosition;
    int clickedYPosition;
    int turn = 0;
    int tmpPiece = 0;
    boolean whiteTurn = true;
    ImageView feld;
    GameController GC;
    TextView tmpFeld;
    ArrayList<TextView> possibles = new ArrayList();
    ArrayList<TextView> beatables = new ArrayList();

    TextView[][] felder = new TextView[8][8];
    ArrayList<Pieces> bl_pieces = new ArrayList();
    ArrayList<Pieces> w_pieces = new ArrayList();
    ImageView[] imageViewsBl = new ImageView[8];
    ImageView[] imageViewsW = new ImageView[8];



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);

        feld = findViewById(R.id.schachfeld);


        bestaetigenP1 = findViewById(R.id.zugBestätigenButton);
        bestaetigenP2 = findViewById(R.id.zugBestätigenButton180);
        start = findViewById(R.id.startGame);

        ImageButton muteSound = findViewById(R.id.soundIngameButton);
        ImageButton helpButton = findViewById(R.id.helpButton);

        TextView counterP1 = findViewById(R.id.counterTextView);
        TextView counterP2 = findViewById(R.id.counter180TextView);

        TextView timerP1 = findViewById(R.id.timeTextView);
        TextView timerP2 = findViewById(R.id.time180TextView);

        initializeField();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piecesOnStartposition();
                start.setVisibility(View.INVISIBLE);

            }
        });

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                whiteTurn(whiteTurn);
            }
        };
        timer.schedule(timerTask,10,10);

//        showPossibles();
//        movePiece(3,3,3);
//        while(true){
//            showPossibles();
//        }
    }


    private void initializeField(){

        felder[0][0] = (TextView) findViewById(R.id.A1);
        felder[0][1] = (TextView)findViewById(R.id.B1);
        felder[0][2] = (TextView)findViewById(R.id.C1);
        felder[0][3] = (TextView)findViewById(R.id.D1);
        felder[0][4] = (TextView)findViewById(R.id.E1);
        felder[0][5] = (TextView)findViewById(R.id.F1);
        felder[0][6] = (TextView)findViewById(R.id.G1);
        felder[0][7] = (TextView)findViewById(R.id.H1);
        felder[1][0] = (TextView)findViewById(R.id.A2);
        felder[1][1] = (TextView)findViewById(R.id.B2);
        felder[1][2] = (TextView)findViewById(R.id.C2);
        felder[1][3] = (TextView)findViewById(R.id.D2);
        felder[1][4] = (TextView)findViewById(R.id.E2);
        felder[1][5] = (TextView)findViewById(R.id.F2);
        felder[1][6] = (TextView)findViewById(R.id.G2);
        felder[1][7] = (TextView)findViewById(R.id.H2);
        felder[2][0] = (TextView)findViewById(R.id.A3);
        felder[2][1] = (TextView)findViewById(R.id.B3);
        felder[2][2] = (TextView)findViewById(R.id.C3);
        felder[2][3] = (TextView)findViewById(R.id.D3);
        felder[2][4] = (TextView)findViewById(R.id.E3);
        felder[2][5] = (TextView)findViewById(R.id.F3);
        felder[2][6] = (TextView)findViewById(R.id.G3);
        felder[2][7] = (TextView)findViewById(R.id.H3);
        felder[3][0] = (TextView)findViewById(R.id.A4);
        felder[3][1] = (TextView)findViewById(R.id.b4);
        felder[3][2] = (TextView)findViewById(R.id.C4);
        felder[3][3] = (TextView)findViewById(R.id.D4);
        felder[3][4] = (TextView)findViewById(R.id.E4);
        felder[3][5] = (TextView)findViewById(R.id.F4);
        felder[3][6] = (TextView)findViewById(R.id.G4);
        felder[3][7] = (TextView)findViewById(R.id.H4);
        felder[4][0] = (TextView)findViewById(R.id.A5);
        felder[4][1] = (TextView)findViewById(R.id.B5);
        felder[4][2] = (TextView)findViewById(R.id.C5);
        felder[4][3] = (TextView)findViewById(R.id.D5);
        felder[4][4] = (TextView)findViewById(R.id.E5);
        felder[4][5] = (TextView)findViewById(R.id.F5);
        felder[4][6] = (TextView)findViewById(R.id.G5);
        felder[4][7] = (TextView)findViewById(R.id.H5);
        felder[5][0] = (TextView)findViewById(R.id.A6);
        felder[5][1] = (TextView)findViewById(R.id.B6);
        felder[5][2] = (TextView)findViewById(R.id.C6);
        felder[5][3] = (TextView)findViewById(R.id.D6);
        felder[5][4] = (TextView)findViewById(R.id.E6);
        felder[5][5] = (TextView)findViewById(R.id.F6);
        felder[5][6] = (TextView)findViewById(R.id.G6);
        felder[5][7] = (TextView)findViewById(R.id.H6);
        felder[6][0] = (TextView)findViewById(R.id.A7);
        felder[6][1] = (TextView)findViewById(R.id.B7);
        felder[6][2] = (TextView)findViewById(R.id.C7);
        felder[6][3] = (TextView)findViewById(R.id.D7);
        felder[6][4] = (TextView)findViewById(R.id.E7);
        felder[6][5] = (TextView)findViewById(R.id.F7);
        felder[6][6] = (TextView)findViewById(R.id.G7);
        felder[6][7] = (TextView)findViewById(R.id.H7);
        felder[7][0] = (TextView)findViewById(R.id.A8);
        felder[7][1] = (TextView)findViewById(R.id.B8);
        felder[7][2] = (TextView)findViewById(R.id.C8);
        felder[7][3] = (TextView)findViewById(R.id.D8);
        felder[7][4] = (TextView)findViewById(R.id.E8);
        felder[7][5] = (TextView)findViewById(R.id.F8);
        felder[7][6] = (TextView)findViewById(R.id.G8);
        felder[7][7] = (TextView)findViewById(R.id.H8);

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
                felder[i][j].setOnClickListener(this::onClick);
            }
        }
    }

    private void piecesOnStartposition(){


        int index = 0;
        for(int i = 0;i<2;i++){
            for (int j =0;j<8;j++){
                w_pieces.get(index).getImg().setX(felder[i][j].getX());
                w_pieces.get(index).getImg().setY(felder[i][j].getY());
                w_pieces.get(index).getImg().bringToFront();

                index++;
            }
        }
        index = 0;

        for(int i = 7; i>5;i--){
            for (int j = 0;j<8;j++){
                bl_pieces.get(index).getImg().setX(felder[i][j].getX());
                bl_pieces.get(index).getImg().setY(felder[i][j].getY());
                bl_pieces.get(index).getImg().bringToFront();
                index++;
            }
        }
    }

    public TextView getFeld(int x, int y){

        return felder[y][x];
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

            Log.i("test","Hier bin ich");

            if (((Bauer)p).isFirstTurn() && !isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1)){//firstTurn ==true
                possibles.add(getFeld(p.getxPosition(),p.getyPosition()+2));
                //((Bauer) p).setFirstTurn(false);
            }
            if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1)){
                possibles.add(getFeld(p.getxPosition(),p.getyPosition()+1));
            }
            if (p.getxPosition() <7 && isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+1) && !takenBy(p.getxPosition()+1,p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                beatables.add(getFeld(p.getxPosition()+1,p.getyPosition()+1));
            }
            if (p.getxPosition()>0 && isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+1) && !takenBy(p.getxPosition()+1,p.getyPosition()+1).equalsIgnoreCase(p.getColor())){
                beatables.add(getFeld(p.getxPosition()-1,p.getyPosition()+1));
            }
        }

        if(p instanceof Springer){

            Log.i("test","Hier bin ich");

            //vorne rechts
            if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+2) && p.getxPosition()+1<8 && p.getyPosition()+2<8){
                possibles.add(getFeld(p.getxPosition()+1, p.getyPosition()+2));
            }
            else if (isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()+2) && p.getxPosition()+1<8 && p.getyPosition()+2<8 && !takenBy(p.getxPosition()+1,p.getyPosition()+2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()+1, p.getyPosition()+2));
            }
            //vorne links
            if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+2) && p.getxPosition()-1>-1 && p.getyPosition()+2<8){
                possibles.add(getFeld(p.getxPosition()-1,p.getyPosition()+2));
            }
            else if (isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()+2) && p.getxPosition()-1>-1 && p.getyPosition()+2<8 && !takenBy(p.getxPosition()-1,p.getyPosition()+2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()-1, p.getyPosition()+2));
            }
            //hinten rechts
            if (!isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-2) && p.getxPosition()+1<8 && p.getyPosition()-2>-1){
                possibles.add(getFeld(p.getxPosition()+1, p.getyPosition()-2));
            }
            else if (isClickedFieldTaken(p.getxPosition()+1,p.getyPosition()-2) && p.getxPosition()+1<8 && p.getyPosition()-2>-1 && !takenBy(p.getxPosition()+1,p.getyPosition()-2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()+1, p.getyPosition()-2));
            }
            //hinten links
            if (!isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-2) && p.getxPosition()-1>-1 && p.getyPosition()-2>-1){
                possibles.add(getFeld(p.getxPosition()-1, p.getyPosition()-2));
            }
            else if (isClickedFieldTaken(p.getxPosition()-1,p.getyPosition()-2) && p.getxPosition()-1>-1 && p.getyPosition()-2>-1 && !takenBy(p.getxPosition()-1,p.getyPosition()-2).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()-1, p.getyPosition()-2));
            }
            //rechts vorne
            if (!isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()+1) && p.getxPosition()+2<8 && p.getyPosition()+1<8){
                possibles.add(getFeld(p.getxPosition()+2, p.getyPosition()+1));
            }
            else if (isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()+1) && p.getxPosition()+2<8 && p.getyPosition()+1<8 && !takenBy(p.getxPosition()+2,p.getyPosition()+1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()+2, p.getyPosition()+1));
            }
            //rechts hinten
            if (!isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()-1) && p.getxPosition()+2<8 && p.getyPosition()-1>-1){
                possibles.add(getFeld(p.getxPosition()+2, p.getyPosition()-1));
            }
            else if (isClickedFieldTaken(p.getxPosition()+2,p.getyPosition()-1) && p.getxPosition()+2<8 && p.getyPosition()-1>-1 && !takenBy(p.getxPosition()+2,p.getyPosition()-1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()+2, p.getyPosition()-1));
            }
            //links vorne
            if (!isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()+1) && p.getxPosition()-2>-1 && p.getyPosition()+1<8){
                possibles.add(getFeld(p.getxPosition()-2, p.getyPosition()+1));
            }
            else if (isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()+1) && p.getxPosition()-2>-1 && p.getyPosition()+1<8 && !takenBy(p.getxPosition()-2,p.getyPosition()+1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()-2, p.getyPosition()+1));
            }
            //links hinten
            if (!isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()-1) && p.getxPosition()-2>-1 && p.getyPosition()-1>-1){
                possibles.add(getFeld(p.getxPosition()-2, p.getyPosition()-1));
            }
            else if (isClickedFieldTaken(p.getxPosition()-2,p.getyPosition()-1) && p.getxPosition()-2>0 && p.getyPosition()-1>0 && !takenBy(p.getxPosition()-2,p.getyPosition()-1).equalsIgnoreCase(p.getColor())) {
                beatables.add(getFeld(p.getxPosition()-2, p.getyPosition()-1));
            }
        }

        if(p instanceof Turm){

            //nach vorn
            for(int i=1; i<=7; i++){
                if (p.getyPosition()+i <= 7){
                    if (!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+i)){
                        possibles.add(getFeld(p.getxPosition(),p.getyPosition()+i));
                    }else {
                        if (!takenBy(p.getxPosition(),p.getyPosition()+i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getFeld(p.getxPosition(),p.getyPosition()+i));
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
                        possibles.add(getFeld(p.getxPosition(),p.getyPosition()-i));
                    }else {
                        if (!takenBy(p.getxPosition(),p.getyPosition()-i).equalsIgnoreCase(p.getColor())){
                            beatables.add(getFeld(p.getxPosition(),p.getyPosition()-i));
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
                        possibles.add(getFeld(p.getxPosition()+i,p.getyPosition()));
                    }else {
                        if (!takenBy(p.getxPosition()+i,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                            beatables.add(getFeld(p.getxPosition()+i,p.getyPosition()));
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
                        possibles.add(getFeld(p.getxPosition()-i,p.getyPosition()));
                    }else {
                        if (!takenBy(p.getxPosition()-i,p.getyPosition()).equalsIgnoreCase(p.getColor())){
                            beatables.add(getFeld(p.getxPosition()-i,p.getyPosition()));
                        }
                        break;
                    }
                }else {
                    break;
                }
            }
        }

        for (TextView t: possibles){
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(t==felder[i][j]){
                        try {
                            felder[i][j].setBackground(getResources().getDrawable(R.drawable.green));
                        }catch (Exception e){}
                    }
                }
            }
        }
        for (TextView t: beatables){
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(t==felder[i][j]){
                        try {
                            Log.i("test", "huhuh "+!isClickedFieldTaken(p.getxPosition(),p.getyPosition()+1)+" Länge:"+possibles.size());
                            felder[i][j].setBackground(getResources().getDrawable(R.drawable.red));
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
                    felder[i][j].setBackground(getResources().getDrawable(R.drawable.schwarz));
                }else {
                    felder[i][j].setBackground(getResources().getDrawable(R.drawable.weiss));
                }
            }
        }
    }

    private void movePiece(int id, int x, int y){

        bestaetigenP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPiece(id).getImg().setX(getFeld(x,y).getX());
                getPiece(id).getImg().setY(getFeld(x,y).getY());
                getPiece(id).setxPosition(x);
                getPiece(id).setyPosition(y);
            }
        });
    }

    public boolean isClickedFieldTaken(int x, int y){

        for(int i=0;i<32;i++){
            Log.i("test","x "+getPiece(i).getxPosition()+" y "+getPiece(i).getyPosition()+" id "+i);
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
            if (clickedPositionhasChanged)
            {
                //Log.i("test",""+tmpPiece+ "Boolean:"+isClickedFieldTaken(clickedXPosition,clickedYPosition));
                if (turn % 2 == 1)
                {
                    if (isClickedFieldTaken(clickedXPosition, clickedYPosition))
                    {
//                        Log.i("test", "haha3");
                        if (getPiece(clickedXPosition, clickedYPosition) != null)
                        {
//                            Log.i("test", "haha4");
                            tmpPiece = getPiece(clickedXPosition, clickedYPosition).getId();
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
                        tmpFeld = getFeld(clickedXPosition, clickedYPosition);
                        if (isClickedFieldTaken(clickedXPosition, clickedYPosition) && takenBy(clickedXPosition, clickedYPosition).equalsIgnoreCase("weiss"))
                        {
                            return;
                        }
                        else if (beatables.contains(getFeld(clickedXPosition, clickedYPosition)))
                        {
                            //Log.i("test","x:"+getPiece(clickedXPosition,clickedYPosition).getxPosition()+" y:"+ getPiece(clickedXPosition,clickedYPosition).getyPosition());
                            getPiece(clickedXPosition, clickedYPosition).getImg().setVisibility(View.INVISIBLE);
                            getPiece(clickedXPosition, clickedYPosition).setxPosition(10);
                            getPiece(10, clickedYPosition).setyPosition(10);
                            getPiece(10, 10).setAlive(false);
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpFeld.getX());
                            getPiece(tmpPiece).getImg().setY(tmpFeld.getY());
                            if(getPiece(tmpPiece) instanceof Bauer)
                            {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                        }
                        else if (possibles.contains(getFeld(clickedXPosition, clickedYPosition)))
                        {
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpFeld.getX());
                            getPiece(tmpPiece).getImg().setY(tmpFeld.getY());
                            if(getPiece(tmpPiece) instanceof Bauer)
                            {
                                ((Bauer) getPiece(tmpPiece)).setFirstTurn(false);
                            }
                        }
                    }
                    undoPossibles();
                    clickedPositionhasChanged = false;
                    endTurn = true;
                }
            }
        }
    }

    /*private void blackTurn(boolean playerTurn)
    {
        if(playerTurn)
        {
            if (clickedPositionhasChanged)
            {
                //Log.i("test",""+tmpPiece+ "Boolean:"+isClickedFieldTaken(clickedXPosition,clickedYPosition));
                if (turn % 2 == 0)
                {
                    if (isClickedFieldTaken(clickedXPosition, clickedYPosition))
                    {
                        Log.i("test", "haha3");
                        if (getPiece(clickedXPosition, clickedYPosition) != null)
                        {
                            Log.i("test", "haha4");
                            tmpPiece = getPiece(clickedXPosition, clickedYPosition).getId();
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
                        tmpFeld = getFeld(clickedXPosition, clickedYPosition);
                        if (isClickedFieldTaken(clickedXPosition, clickedYPosition) && takenBy(clickedXPosition, clickedYPosition).equalsIgnoreCase("schwarz"))
                        {
                            return;
                        }
                        else if (beatables.contains(getFeld(clickedXPosition, clickedYPosition)))
                        {
                            //Log.i("test","x:"+getPiece(clickedXPosition,clickedYPosition).getxPosition()+" y:"+ getPiece(clickedXPosition,clickedYPosition).getyPosition());
                            getPiece(clickedXPosition, clickedYPosition).getImg().setVisibility(View.INVISIBLE);
                            getPiece(clickedXPosition, clickedYPosition).setxPosition(10);
                            getPiece(10, clickedYPosition).setyPosition(10);
                            getPiece(10, 10).setAlive(false);
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpFeld.getX());
                            getPiece(tmpPiece).getImg().setY(tmpFeld.getY());
                        }
                        else if (possibles.contains(getFeld(clickedXPosition, clickedYPosition)))
                        {
                            getPiece(tmpPiece).setxPosition(clickedXPosition);
                            getPiece(tmpPiece).setyPosition(clickedYPosition);
                            getPiece(tmpPiece).getImg().setX(tmpFeld.getX());
                            getPiece(tmpPiece).getImg().setY(tmpFeld.getY());
                        }
                    }
                    undoPossibles();
                    clickedPositionhasChanged = false;
                    endTurn = true;
                }
            }
        }
    }*/


    @Override
    public void onClick(View v){

        //Log.i("test", "Click"+ clickedXPosition+" "+clickedYPosition);

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

    public void gameLoop(){
        whiteTurn(whiteTurn);
        gameLoop();
    }

    public void setClickedXPosition(int clickedXPosition) {
        this.clickedXPosition = clickedXPosition;
    }

    public void setClickedYPosition(int clickedYPosition) {
        this.clickedYPosition = clickedYPosition;
    }

    //GameController GC = new GameController();

    //ImageButton IB1 = findViewById(R.id.imageButton);
    //ImageButton IB2 = findViewById(R.id.imageButton2);
    //ImageButton IB3 = findViewById(R.id.imageButton3);
    //int d = R.drawable.schachfeld_final;
    //IB1.setOnClickListener(new View.OnClickListener() {
    //    @RequiresApi(api = Build.VERSION_CODES.Q)
    //    @Override
    //    public void onClick(View v) {
    //        int left = IB2.getLeft();
    //        int top = IB2.getTop();
    //        int right = IB2.getRight();
    //        int bottom = IB2.getBottom();
    //        IB2.setImageDrawable(getDrawable(d));
    //        //IB2.setLeftTopRightBottom(left,top,right,bottom);
    //        IB2.layout(left,top,right,bottom);
    //    }
    //});
    //ImageButton bauer = findViewById(R.id.imageButton5);
    //ImageButton field = findViewById(R.id.imageButton4);
    //bauer.setOnClickListener(new View.OnClickListener() {
    //    @Override
    //    public void onClick(View v) {
    //        field.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                int left = bauer.getLeft();
    //                int top = bauer.getTop();
    //                int right = bauer.getRight();
    //                int bottom = bauer.getBottom();
    //                bauer.setImageDrawable(getDrawable(R.drawable.ic_launcher_background));
    //                bauer.layout(left,top,right,bottom);
    //                field.setImageDrawable(getDrawable(R.drawable.bauer));
    //                field.layout(left,top,right,bottom);
    //            }
    //        });
    //    }
    //});

}
