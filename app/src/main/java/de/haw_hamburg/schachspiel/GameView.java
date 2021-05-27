package de.haw_hamburg.schachspiel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class GameView extends AppCompatActivity implements View.OnClickListener{

    public Button bestaetigenP1;
    public Button bestaetigenP2;
    GameController GC;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);


        TextView A1 = findViewById(R.id.A1);
        TextView A2 = findViewById(R.id.A2);
        TextView A3 = findViewById(R.id.A3);
        TextView A4 = findViewById(R.id.A4);
        TextView A5 = findViewById(R.id.A5);
        TextView A6 = findViewById(R.id.A6);
        TextView A7 = findViewById(R.id.A7);
        TextView A8 = findViewById(R.id.A8);
        TextView B1 = findViewById(R.id.B1);
        TextView B2 = findViewById(R.id.B2);
        TextView B3 = findViewById(R.id.B3);
        TextView B4 = findViewById(R.id.b4);
        TextView B5 = findViewById(R.id.B5);
        TextView B6 = findViewById(R.id.B6);
        TextView B7 = findViewById(R.id.B7);
        TextView B8 = findViewById(R.id.B8);
        TextView C1 = findViewById(R.id.C1);
        TextView C2 = findViewById(R.id.C2);
        TextView C3 = findViewById(R.id.C3);
        TextView C4 = findViewById(R.id.C4);
        TextView C5 = findViewById(R.id.C5);
        TextView C6 = findViewById(R.id.C6);
        TextView C7 = findViewById(R.id.C7);
        TextView C8 = findViewById(R.id.C8);
        TextView D1 = findViewById(R.id.D1);
        TextView D2 = findViewById(R.id.D2);
        TextView D3 = findViewById(R.id.D3);
        TextView D4 = findViewById(R.id.D4);
        TextView D5 = findViewById(R.id.D5);
        TextView D6 = findViewById(R.id.D6);
        TextView D7 = findViewById(R.id.D7);
        TextView D8 = findViewById(R.id.D8);
        TextView E1 = findViewById(R.id.E1);
        TextView E2 = findViewById(R.id.E2);
        TextView E3 = findViewById(R.id.E3);
        TextView E4 = findViewById(R.id.E4);
        TextView E5 = findViewById(R.id.E5);
        TextView E6 = findViewById(R.id.E6);
        TextView E7 = findViewById(R.id.E7);
        TextView E8 = findViewById(R.id.E8);
        TextView F1 = findViewById(R.id.F1);
        TextView F2 = findViewById(R.id.F2);
        TextView F3 = findViewById(R.id.F3);
        TextView F4 = findViewById(R.id.F4);
        TextView F5 = findViewById(R.id.F5);
        TextView F6 = findViewById(R.id.F6);
        TextView F7 = findViewById(R.id.F7);
        TextView F8 = findViewById(R.id.F8);
        TextView G1 = findViewById(R.id.G1);
        TextView G2 = findViewById(R.id.G2);
        TextView G3 = findViewById(R.id.G3);
        TextView G4 = findViewById(R.id.G4);
        TextView G5 = findViewById(R.id.G5);
        TextView G6 = findViewById(R.id.G6);
        TextView G7 = findViewById(R.id.G7);
        TextView G8 = findViewById(R.id.G8);
        TextView H1 = findViewById(R.id.H1);
        TextView H2 = findViewById(R.id.H2);
        TextView H3 = findViewById(R.id.H3);
        TextView H4 = findViewById(R.id.H4);
        TextView H5 = findViewById(R.id.H5);
        TextView H6 = findViewById(R.id.H6);
        TextView H7 = findViewById(R.id.H7);
        TextView H8 = findViewById(R.id.H8);

        ImageView bl_bauer1 = findViewById(R.id.bl_bauer1);
        ImageView bl_bauer2 = findViewById(R.id.bl_bauer2);
        ImageView bl_bauer3 = findViewById(R.id.bl_bauer3);
        ImageView bl_bauer4 = findViewById(R.id.bl_bauer4);
        ImageView bl_bauer5 = findViewById(R.id.bl_bauer5);
        ImageView bl_bauer6 = findViewById(R.id.bl_bauer6);
        ImageView bl_bauer7 = findViewById(R.id.bl_bauer7);
        ImageView bl_bauer8 = findViewById(R.id.bl_bauer8);







        //bestaetigenP1 = findViewById(R.id.zugBestätigenButton);
        //bestaetigenP2 = findViewById(R.id.zugBestätigenButton180);

        //ImageButton muteSound = findViewById(R.id.soundIngameButton);
        //ImageButton helpButton = findViewById(R.id.helpButton);

        //TextView counterP1 = findViewById(R.id.counterTextView);
        //TextView counterP2 = findViewById(R.id.counter180TextView);

        //TextView timerP1 = findViewById(R.id.timeTextView);
        //TextView timerP2 = findViewById(R.id.time180TextView);

        //ImageView feld = findViewById(R.id.schachfeld);

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

}
