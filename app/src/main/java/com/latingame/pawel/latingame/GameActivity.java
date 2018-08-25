package com.latingame.pawel.latingame;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.latingame.pawel.latingame.game.MySQLiteHelper;
import com.latingame.pawel.latingame.game.Word;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = GameActivity.class.getSimpleName();
    private String[] playerNames;
    private int whosTurn = 0; // first player has index 0
    private int whichMaxim = 1;
    private int whichSplit = 0;
    private TextView textViewPlayersName, textViewWord;
    private MySQLiteHelper db;
    private Word word;
    private SharedPreferences sharedPref;
    private static final int CORRECT = 0;
    private static final int WRONG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        update();
    }

    private void update(){
        showNextWord();
        updatePlayerTurn();
    }

    private void showNextWord(){
        textViewWord.setText(word.getSplit()[whichSplit]);
        whichSplit++;
        // TODO: NEXT ACTIVITY IF LAST
    }

    private void updatePlayerTurn(){
        textViewPlayersName.setText(playerNames[whosTurn]);
        whosTurn++;
        if(whosTurn >= playerNames.length)
            whosTurn = 0;
    }
    private void init(){
        initPlayers();
        initWord();
        initProgressBars();
        initTextViews();
    }

    private void initTextViews(){
        textViewPlayersName = findViewById(R.id.whichPlayer);
        textViewWord = findViewById(R.id.wordInLatin);
    }

    private void initProgressBars(){
        ProgressBar progressBarCorrect, progressBarWrong;
        ImageButton correctBtn, wrongBtn;

        progressBarCorrect = findViewById(R.id.progressBarCorrect);
        correctBtn = findViewById(R.id.correctBtn);
        initProgressBar(progressBarCorrect, correctBtn, CORRECT);

        progressBarWrong = findViewById(R.id.progressBarWrong);
        wrongBtn = findViewById(R.id.wrongBtn);
        initProgressBar(progressBarWrong, wrongBtn, WRONG);
    }

    private void initWord(){
        db = new MySQLiteHelper(this);
        sharedPref = getSharedPreferences("com.latingame.pawel.latingame", MODE_PRIVATE);

        whichMaxim = sharedPref.getInt("whichMaxim", whichMaxim);
        word = db.getWord(whichMaxim);

    }

    private void wrongAnswer(){
        
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initProgressBar(final ProgressBar progressBar, ImageButton button, final int BUTTON_ID){

        button.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null)
                            return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 500);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null)
                            return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        if(progressBar.getProgress() < 100)
                            progressBar.setProgress(0);
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    int progress = progressBar.getProgress();
                    progressBar.setProgress(progress + 2);
                    if(progressBar.getProgress() > 99 && BUTTON_ID == CORRECT){
                        progressBar.setProgress(0);
                        update();
                    } else if(progressBar.getProgress() > 99 && BUTTON_ID == WRONG){
                        progressBar.setProgress(0);
                    }
                    mHandler.postDelayed(this, 1);
                }
            };
        });

    }


    private void initPlayers(){
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        playerNames = extras.getStringArray("names");
    }
}
