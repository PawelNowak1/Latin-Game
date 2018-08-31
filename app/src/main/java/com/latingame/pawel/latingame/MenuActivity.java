package com.latingame.pawel.latingame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button playButton, howToPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }

    private void init(){
        Log.d("He2e2e2", "init: ");
        initButtons();
    }

    private void initButtons(){
        playButton = findViewById(R.id.playBtn);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHowManyActivity();
            }
        });

        howToPlayButton = findViewById(R.id.howToPlayBtn);
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: howToPlayActivity
            }
        });
    }

    private void startHowManyActivity(){
        Intent newActivityStart = new Intent(MenuActivity.this, HowManyPlayersActivity.class);
        MenuActivity.this.startActivity(newActivityStart);
    }

}