package com.latingame.pawel.latingame;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.latingame.pawel.latingame.game.MySQLiteHelper;
import com.latingame.pawel.latingame.game.Word;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = GameActivity.class.getSimpleName();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
    }

    private void init(){
        initPlayers();
        initWords();
        initProgressBar();
    }

    private void initWords(){
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.addWord(new Word("A fructibus eorum cognoscetis eos", "Po ich owocach ich poznacie", "Jezus Chrystus - Wulgata, Ewangelia Mateusza 7:15, 20"));
        db.addWord(new Word("A planta pedis usque verticum eius", "Od podeszwy stopy do głowy jego", "Wulgata, Księga Hioba 2, 7"));
        db.addWord(new Word("Ab alio exspectes, alteri quod feceris", "Od innego oczekuj tego, co robisz drugiemu; jak Kuba Bogu, tak Bóg Kubie", "Autor: Seneka"));
        db.addWord(new Word("Absterget Deus omnem lacrimam ab oculis eorum", "I otrze Bóg wszelką łzę z oczu ich. ", "Wulgata, Apokalipsa wg św. Jana"));
        db.addWord(new Word("Actus hominis non dignitas iudicentur", "Niech będą sądzone czyny człowieka, a nie jego godność", "Minucjusz Feliks, Octavius 36"));


        db.addWord(new Word("", "", ""));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initProgressBar(){

        progressBar = findViewById(R.id.progressBar);
        ImageButton correctBtn = findViewById(R.id.correctBtn);

        correctBtn.setOnTouchListener(new View.OnTouchListener() {

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
                    mHandler.postDelayed(this, 1);
                }
            };
        });

    }


    private void initPlayers(){
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String[] names = extras.getStringArray("names");
        TextView textView = findViewById(R.id.textView3);

    }
}
