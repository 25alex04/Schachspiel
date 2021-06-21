package de.haw_hamburg.schachspiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Gameover extends AppCompatActivity {

    private static final String DATA_KEY = "GameView.Data";
    private String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent for post-game
        Intent i = getIntent();
        if (i.hasExtra(DATA_KEY)){
            data = i.getStringExtra(DATA_KEY);
            if (data.equalsIgnoreCase("white")){
                setContentView(R.layout.gameoverwhite);
            } else if (data.equalsIgnoreCase("black")){
                setContentView(R.layout.gameoverblack);
            }
        }

        //create buttons for post-game
        Button menu = findViewById(R.id.whiteGameOverMenuButton);
        Button replay = findViewById(R.id.whiteGameOverReplayButton);

        //onClickListener for the menu-Button
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gameover.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //onClickListener for the replay-Button
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gameover.this, GameView.class);
                startActivity(intent);
            }
        });

    }
}
