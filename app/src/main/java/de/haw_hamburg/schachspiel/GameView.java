package de.haw_hamburg.schachspiel;

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

public class GameView extends AppCompatActivity implements View.OnClickListener{

    public Button bestaetigenP1;
    public Button bestaetigenP2;
    ImageView feld;
    GameController GC;

    TextView[][] felder = new TextView[8][8];
    ArrayList<Pieces> bl_pieces = new ArrayList();
    ArrayList<Pieces> w_pieces = new ArrayList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);

        feld = findViewById(R.id.schachfeld);


        bestaetigenP1 = findViewById(R.id.zugBestätigenButton);
        bestaetigenP2 = findViewById(R.id.zugBestätigenButton180);

        ImageButton muteSound = findViewById(R.id.soundIngameButton);
        ImageButton helpButton = findViewById(R.id.helpButton);

        TextView counterP1 = findViewById(R.id.counterTextView);
        TextView counterP2 = findViewById(R.id.counter180TextView);

        TextView timerP1 = findViewById(R.id.timeTextView);
        TextView timerP2 = findViewById(R.id.time180TextView);


        initializeField();
        test();
    }

    public static Point getLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new Point(location[0], location[1]);
    }


    private void initializeField(){
        felder[0][0] = (TextView) findViewById(R.id.A1);
        felder[0][1] = findViewById(R.id.B1);
        felder[0][2] = findViewById(R.id.C1);
        felder[0][3] = findViewById(R.id.D1);
        felder[0][4] = findViewById(R.id.E1);
        felder[0][5] = findViewById(R.id.F1);
        felder[0][6] = findViewById(R.id.G1);
        felder[0][7] = findViewById(R.id.H1);
        felder[1][0] = findViewById(R.id.A2);
        felder[1][1] = findViewById(R.id.B2);
        felder[1][2] = findViewById(R.id.C2);
        felder[1][3] = findViewById(R.id.D2);
        felder[1][4] = findViewById(R.id.E2);
        felder[1][5] = findViewById(R.id.F2);
        felder[1][6] = findViewById(R.id.G2);
        felder[1][7] = findViewById(R.id.H2);
        felder[2][0] = findViewById(R.id.A3);
        felder[2][1] = findViewById(R.id.B3);
        felder[2][2] = findViewById(R.id.C3);
        felder[2][3] = findViewById(R.id.D3);
        felder[2][4] = findViewById(R.id.E3);
        felder[2][5] = findViewById(R.id.F3);
        felder[2][6] = findViewById(R.id.G3);
        felder[2][7] = findViewById(R.id.H3);
        felder[3][0] = findViewById(R.id.A4);
        felder[3][1] = findViewById(R.id.b4);
        felder[3][2] = findViewById(R.id.C4);
        felder[3][3] = findViewById(R.id.D4);
        felder[3][4] = findViewById(R.id.E4);
        felder[3][5] = findViewById(R.id.F4);
        felder[3][6] = findViewById(R.id.G4);
        felder[3][7] = findViewById(R.id.H4);
        felder[4][0] = findViewById(R.id.A5);
        felder[4][1] = findViewById(R.id.B5);
        felder[4][2] = findViewById(R.id.C5);
        felder[4][3] = findViewById(R.id.D5);
        felder[4][4] = findViewById(R.id.E5);
        felder[4][5] = findViewById(R.id.F5);
        felder[4][6] = findViewById(R.id.G5);
        felder[4][7] = findViewById(R.id.H5);
        felder[5][0] = findViewById(R.id.A6);
        felder[5][1] = findViewById(R.id.B6);
        felder[5][2] = findViewById(R.id.C6);
        felder[5][3] = findViewById(R.id.D6);
        felder[5][4] = findViewById(R.id.E6);
        felder[5][5] = findViewById(R.id.F6);
        felder[5][6] = findViewById(R.id.G6);
        felder[5][7] = findViewById(R.id.H6);
        felder[6][0] = findViewById(R.id.A7);
        felder[6][1] = findViewById(R.id.B7);
        felder[6][2] = findViewById(R.id.C7);
        felder[6][3] = findViewById(R.id.D7);
        felder[6][4] = findViewById(R.id.E7);
        felder[6][5] = findViewById(R.id.F7);
        felder[6][6] = findViewById(R.id.G7);
        felder[6][7] = findViewById(R.id.H7);
        felder[7][0] = findViewById(R.id.A8);
        felder[7][1] = findViewById(R.id.B8);
        felder[7][2] = findViewById(R.id.C8);
        felder[7][3] = findViewById(R.id.D8);
        felder[7][4] = findViewById(R.id.E8);
        felder[7][5] = findViewById(R.id.F8);
        felder[7][6] = findViewById(R.id.G8);
        felder[7][7] = findViewById(R.id.H8);

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

        Bauer bauer1_bl = new Bauer(bl_bauer1,"schwarz", 0, 6, 16);
        Bauer bauer2_bl = new Bauer(bl_bauer2,"schwarz", 1, 6, 17);
        Bauer bauer3_bl = new Bauer(bl_bauer3,"schwarz", 2, 6, 18);
        Bauer bauer4_bl = new Bauer(bl_bauer4,"schwarz", 3, 6, 19);
        Bauer bauer5_bl = new Bauer(bl_bauer5,"schwarz", 4, 6, 20);
        Bauer bauer6_bl = new Bauer(bl_bauer6,"schwarz", 5, 6, 21);
        Bauer bauer7_bl = new Bauer(bl_bauer7,"schwarz", 6, 6, 22);
        Bauer bauer8_bl = new Bauer(bl_bauer8,"schwarz", 7, 6, 23);

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
        bl_pieces.add(bauer1_bl);
        bl_pieces.add(bauer2_bl);
        bl_pieces.add(bauer3_bl);
        bl_pieces.add(bauer4_bl);
        bl_pieces.add(bauer5_bl);
        bl_pieces.add(bauer6_bl);
        bl_pieces.add(bauer7_bl);
        bl_pieces.add(bauer8_bl);


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

        Bauer bauer1_w = new Bauer(w_bauer1,"weiß", 0, 1, 0);
        Bauer bauer2_w = new Bauer(w_bauer2,"weiß", 1, 1, 1);
        Bauer bauer3_w = new Bauer(w_bauer3,"weiß", 2, 1, 2);
        Bauer bauer4_w = new Bauer(w_bauer4,"weiß", 3, 1, 3);
        Bauer bauer5_w = new Bauer(w_bauer5,"weiß", 4, 1, 4);
        Bauer bauer6_w = new Bauer(w_bauer6,"weiß", 5, 1, 5);
        Bauer bauer7_w = new Bauer(w_bauer7,"weiß", 6, 1, 6);
        Bauer bauer8_w = new Bauer(w_bauer8,"weiß", 7, 1, 7);

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
        w_pieces.add(bauer1_w);
        w_pieces.add(bauer2_w);
        w_pieces.add(bauer3_w);
        w_pieces.add(bauer4_w);
        w_pieces.add(bauer5_w);
        w_pieces.add(bauer6_w);
        w_pieces.add(bauer7_w);
        w_pieces.add(bauer8_w);

    }

    private void test(){
        feld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                //feld.getLocationOnScreen(location);
//                Toast.makeText(GameView.this,"X axis is "+location[0] +"and Y axis is "+location[1],Toast.LENGTH_LONG).show();

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
        });


    }

    @Override
    public void onClick(View v){

        switch (v.getId()){

            case R.id.A1:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(0);
                break;
            case R.id.A2:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(1);
                break;
            case R.id.A3:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(2);
                break;
            case R.id.A4:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(3);
                break;
            case R.id.A5:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(4);
                break;
            case R.id.A6:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(5);
                break;
            case R.id.A7:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(6);
                break;
            case R.id.A8:
                GC.setClickedXPosition(0);
                GC.setClickedYPosition(7);
                break;
            case R.id.B1:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(0);
                break;
            case R.id.B2:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(1);
                break;
            case R.id.B3:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(2);
                break;
            case R.id.b4:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(3);
                break;
            case R.id.B5:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(4);
                break;
            case R.id.B6:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(5);
                break;
            case R.id.B7:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(6);
                break;
            case R.id.B8:
                GC.setClickedXPosition(1);
                GC.setClickedYPosition(7);
                break;
            case R.id.C1:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(0);
                break;
            case R.id.C2:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(1);
                break;
            case R.id.C3:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(2);
                break;
            case R.id.C4:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(3);
                break;
            case R.id.C5:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(4);
                break;
            case R.id.C6:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(5);
                break;
            case R.id.C7:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(6);
                break;
            case R.id.C8:
                GC.setClickedXPosition(2);
                GC.setClickedYPosition(7);
                break;
            case R.id.D1:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(0);
                break;
            case R.id.D2:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(1);
                break;
            case R.id.D3:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(2);
                break;
            case R.id.D4:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(3);
                break;
            case R.id.D5:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(4);
                break;
            case R.id.D6:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(5);
                break;
            case R.id.D7:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(6);
                break;
            case R.id.D8:
                GC.setClickedXPosition(3);
                GC.setClickedYPosition(7);
                break;
            case R.id.E1:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(0);
                break;
            case R.id.E2:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(1);
                break;
            case R.id.E3:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(2);
                break;
            case R.id.E4:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(3);
                break;
            case R.id.E5:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(4);
                break;
            case R.id.E6:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(5);
                break;
            case R.id.E7:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(6);
                break;
            case R.id.E8:
                GC.setClickedXPosition(4);
                GC.setClickedYPosition(7);
                break;
            case R.id.F1:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(0);
                break;
            case R.id.F2:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(1);
                break;
            case R.id.F3:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(2);
                break;
            case R.id.F4:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(3);
                break;
            case R.id.F5:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(4);
                break;
            case R.id.F6:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(5);
                break;
            case R.id.F7:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(6);
                break;
            case R.id.F8:
                GC.setClickedXPosition(5);
                GC.setClickedYPosition(7);
                break;
            case R.id.G1:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(0);
                break;
            case R.id.G2:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(1);
                break;
            case R.id.G3:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(2);
                break;
            case R.id.G4:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(3);
                break;
            case R.id.G5:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(4);
                break;
            case R.id.G6:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(5);
                break;
            case R.id.G7:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(6);
                break;
            case R.id.G8:
                GC.setClickedXPosition(6);
                GC.setClickedYPosition(7);
                break;
            case R.id.H1:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(0);
                break;
            case R.id.H2:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(1);
                break;
            case R.id.H3:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(2);
                break;
            case R.id.H4:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(3);
                break;
            case R.id.H5:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(4);
                break;
            case R.id.H6:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(5);
                break;
            case R.id.H7:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(6);
                break;
            case R.id.H8:
                GC.setClickedXPosition(7);
                GC.setClickedYPosition(7);
                break;
        }

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
