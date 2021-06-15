package de.haw_hamburg.schachspiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Gameover extends AppCompatActivity {

    public static final String DATA_KEY = "GameView.Data";
    String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.gameoverwhite);


        Intent i = getIntent();
        if (i.hasExtra(DATA_KEY)){
            data = i.getStringExtra(DATA_KEY);
            if (data.equalsIgnoreCase("white")){
                setContentView(R.layout.gameoverwhite);
            } else if (data.equalsIgnoreCase("black")){
                setContentView(R.layout.gameoverblack);
            }
        }


        Button menu = findViewById(R.id.whiteGameOverMenuButton);
        Button replay = findViewById(R.id.whiteGameOverReplayButton);



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gameover.this, MainActivity.class);
                startActivity(intent);
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gameover.this, GameView.class);
                startActivity(intent);
            }
        });

    }
}
