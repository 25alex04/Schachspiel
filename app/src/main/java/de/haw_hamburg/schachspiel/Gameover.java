package de.haw_hamburg.schachspiel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Gameover extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button menu = findViewById(R.id.gameOverMenuButton);
        Button replay = findViewById(R.id.gameOverReplayButton);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gameover.this, Menu.class);
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
