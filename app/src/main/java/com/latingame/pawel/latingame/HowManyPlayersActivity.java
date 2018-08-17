package com.latingame.pawel.latingame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class HowManyPlayersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howmanyplayers);
        init();
    }

    private void init(){
        initSeekBar();
        initButton();
    }

    private void initSeekBar(){
        final int MAX_NUMBER_OF_PLAYERS = 5;
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(MAX_NUMBER_OF_PLAYERS - 1);
        final TextView seekBarValue = findViewById(R.id.textViewHowManyPlayers);
        seekBarValue.setText("1");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initButton(){
        Button playButton = findViewById(R.id.button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivityStart = new Intent(HowManyPlayersActivity.this, GiveNamesActivity.class);
                HowManyPlayersActivity.this.startActivity(newActivityStart);
            }
        });
    }
}
