package com.latingame.pawel.latingame;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = GameActivity.class.getSimpleName();
    private String[] playerNames;
    private int whosTurn = 0; // first player has index 0
    private int whichWord = 0;

    private TextView textViewPlayersName, textViewEnglishWord, textViewWordTranslation;
    private MySQLiteHelper db;
    public ArrayList<Word> words;
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
        textViewEnglishWord.setText(words.get(whichWord).getEnglishWord());
        textViewWordTranslation.setText(words.get(whichWord).getTranslation());
        whichWord++;
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
        textViewEnglishWord = findViewById(R.id.wordInEnglish);
        textViewWordTranslation = findViewById(R.id.wordTranslation);
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
        db = MySQLiteHelper.getInstance(this);
        sharedPref = getSharedPreferences("com.latingame.pawel.latingame", MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String category = extras.getString("category");
        Integer[] ids = db.getIdsWordsByCategory(category);
        words = new ArrayList<>();
        for(Integer id : ids)
            words.add(db.getWord(id));
        Collections.shuffle(words);
    }

    private void wrongAnswer(){
        Intent newActivityStart = new Intent(GameActivity.this, ResultActivity.class);
        Bundle bundle = new Bundle();
        String[] englishWords = new String[whichWord];
        String[] translations = new String[whichWord];

        for(int i = 0; i < whichWord; i++)
        {
            englishWords[i] = words.get(i).getEnglishWord();
            translations[i] = words.get(i).getTranslation();
        }

        bundle.putStringArray("englishWords", englishWords);
        bundle.putStringArray("translations", translations);
        bundle.putStringArray("listOfPlayers", playerNames);
        whosTurn--;
        if(whosTurn < 0)
            whosTurn = playerNames.length - 1;
        bundle.putString("currentPlayer", playerNames[whosTurn]);
        newActivityStart.putExtras(bundle);
        GameActivity.this.startActivity(newActivityStart);
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
                        wrongAnswer();
                    }
                    mHandler.postDelayed(this, 1);
                }
            };
        });

    }


    private void initPlayers(){ // powwino ndzialc
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        playerNames = extras.getStringArray("names");
    }
}
