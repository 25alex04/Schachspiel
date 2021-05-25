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
        setContentView(R.layout.test);

        TextView textView = findViewById(R.id.testclickview);

        textView.setOnClickListener(this::onClick);


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
        }

    }

}
