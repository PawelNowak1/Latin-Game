package com.latingame.pawel.latingame;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.latingame.pawel.latingame.game.Word;

public class ResultActivity extends AppCompatActivity {

    private String[] playerNames, translations, englishWords;
    private String currentPlayer;
    private static final int CORRECT = 0;
    private static final int WRONG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getExtras();
        initTextViews();
        initProgressBars();
    }

    private void getExtras(){
        if(getIntent().getExtras() != null){
            translations = getIntent().getExtras().getStringArray("translations");
            englishWords = getIntent().getExtras().getStringArray("englishWords");
            playerNames = getIntent().getExtras().getStringArray("listOfPlayers");
            currentPlayer = getIntent().getExtras().getString("currentPlayer");
        }
    }

    private void initTextViews(){
        TextView textViewEnglishWords = findViewById(R.id.englishWords);
        String text = "";

        StringBuilder stringBuilder = new StringBuilder("");
        for (String englishWord : englishWords) {
            stringBuilder.append(englishWord);
            stringBuilder.append("\n");
        }
        textViewEnglishWords.setText(stringBuilder);

        TextView question = findViewById(R.id.question);
        String questionText = "Czy po sprawdzeniu łacińskiej sentencji gracz: " + currentPlayer + " poprawnie wypowiedział swoją kwestię?";
        question.setText(questionText);
    }

    private void initProgressBars(){
        ProgressBar progressBarCorrect, progressBarWrong;
        ImageButton correctBtn, wrongBtn;

        progressBarCorrect = findViewById(R.id.result_progressBarCorrect);
        correctBtn = findViewById(R.id.result_correctBtn);
        initProgressBar(progressBarCorrect, correctBtn, CORRECT);

        progressBarWrong = findViewById(R.id.result_progressBarWrong);
        wrongBtn = findViewById(R.id.result_wrongBtn);
        initProgressBar(progressBarWrong, wrongBtn, WRONG);
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
                        //update();
                    } else if(progressBar.getProgress() > 99 && BUTTON_ID == WRONG){
                        progressBar.setProgress(0);
                        //wrongAnswer();
                    }
                    mHandler.postDelayed(this, 1);
                }
            };
        });

    }
}
