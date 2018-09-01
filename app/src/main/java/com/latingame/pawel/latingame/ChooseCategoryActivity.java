package com.latingame.pawel.latingame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.latingame.pawel.latingame.adapters.ButtonsAdapter;
import com.latingame.pawel.latingame.game.MySQLiteHelper;
import com.latingame.pawel.latingame.game.Word;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseCategoryActivity extends AppCompatActivity {

    MySQLiteHelper mySQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        handleDataBase();
        init();
    }

    private void handleDataBase(){
        mySQLiteHelper = MySQLiteHelper.getInstance(this);
/*
        mySQLiteHelper.addWord(new Word("white", "biały", "kolory"));
        mySQLiteHelper.addWord(new Word("yellow", "żółty", "kolory"));
        mySQLiteHelper.addWord(new Word("golden", "złoty", "kolory"));
        mySQLiteHelper.addWord(new Word("red", "czerwony", "kolory"));
        mySQLiteHelper.addWord(new Word("orange", "pomarańczowy", "kolory"));
        mySQLiteHelper.addWord(new Word("crimson", "szkarłatny", "kolory"));
        mySQLiteHelper.addWord(new Word("claret", "bordo", "kolory"));
        mySQLiteHelper.addWord(new Word("pink", "różowy", "kolory"));
        mySQLiteHelper.addWord(new Word("violet", "fioletowy", "kolory"));
        mySQLiteHelper.addWord(new Word("purple", "purpurowy", "kolory"));
        mySQLiteHelper.addWord(new Word("blue", "niebieski", "kolory"));
        mySQLiteHelper.addWord(new Word("turquoise", "turkusowy", "kolory"));
        mySQLiteHelper.addWord(new Word("green", "zielony", "kolory"));
        mySQLiteHelper.addWord(new Word("beige", "beżowy", "kolory"));
        mySQLiteHelper.addWord(new Word("brown", "brązowy", "kolory"));
        mySQLiteHelper.addWord(new Word("grey", "szary", "kolory"));
        mySQLiteHelper.addWord(new Word("silver", "srebrny", "kolory"));
        mySQLiteHelper.addWord(new Word("black", "czarny", "kolory"));
        mySQLiteHelper.addWord(new Word("hunt", "polowanie", "las"));
        mySQLiteHelper.addWord(new Word("species", "gatunek", "las"));
        mySQLiteHelper.addWord(new Word("hedgehog", "jeż", "las"));
        mySQLiteHelper.addWord(new Word("wood", "drewno", "las"));
        mySQLiteHelper.addWord(new Word("plant", "roślina", "las"));
        mySQLiteHelper.addWord(new Word("animal", "zwierzę", "las"));
        mySQLiteHelper.addWord(new Word("mushroom", "grzyb", "las"));

        mySQLiteHelper.addWord(new Word("oak", "dąb", "las"));
        mySQLiteHelper.addWord(new Word("leaf", "liść", "las"));
        mySQLiteHelper.addWord(new Word("flower", "kwiat", "las"));
        mySQLiteHelper.addWord(new Word("moss", "mech", "las"));
        mySQLiteHelper.addWord(new Word("squirrel", "wiewiórka", "las"));
        mySQLiteHelper.addWord(new Word("bear", "niedźwiedź", "las"));
        mySQLiteHelper.addWord(new Word("blueberry", "czarna jagoda", "las"));
        mySQLiteHelper.addWord(new Word("bush", "krzak", "las"));
        mySQLiteHelper.addWord(new Word("tree", "drzewo", "las"));
        mySQLiteHelper.addWord(new Word("pine", "sosna", "las"));
        mySQLiteHelper.addWord(new Word("spruce", "świerk", "las"));
        mySQLiteHelper.addWord(new Word("cone", "szyszka", "las"));
        mySQLiteHelper.addWord(new Word("fruit", "owoca", "las"));
        mySQLiteHelper.addWord(new Word("raspberry", "malina", "las"));
        mySQLiteHelper.addWord(new Word("wolf", "wilk", "las"));
        mySQLiteHelper.addWord(new Word("hare", "zając", "las"));
        mySQLiteHelper.addWord(new Word("fox", "lis", "las"));
        mySQLiteHelper.addWord(new Word("deer", "jeleń", "las"));
        mySQLiteHelper.addWord(new Word("birch", "brzoza", "las"));

        mySQLiteHelper.addWord(new Word("architect", "architekt", "zawody"));
        mySQLiteHelper.addWord(new Word("scientist", "naukowiec", "zawody"));
        mySQLiteHelper.addWord(new Word("biologist", "biolog", "zawody"));
        mySQLiteHelper.addWord(new Word("economist", "ekonomista", "zawody"));
        mySQLiteHelper.addWord(new Word("historian", "historyk", "zawody"));
        mySQLiteHelper.addWord(new Word("mathematician", "matematyk", "zawody"));
        mySQLiteHelper.addWord(new Word("philologist", "filolog", "zawody"));
        mySQLiteHelper.addWord(new Word("physicist", "fizyk", "zawody"));
        mySQLiteHelper.addWord(new Word("sociologist", "socjolog", "zawody"));
        mySQLiteHelper.addWord(new Word("engineer", "inżynier", "zawody"));
        mySQLiteHelper.addWord(new Word("doctor", "lekarz", "zawody"));
        mySQLiteHelper.addWord(new Word("dentist", "dentysta", "zawody"));
        mySQLiteHelper.addWord(new Word("ophthalmologist", "okulista", "zawody"));
        mySQLiteHelper.addWord(new Word("orthopaedist", "ortopeda", "zawody"));
        mySQLiteHelper.addWord(new Word("surgeon", "chirurg", "zawody"));
        mySQLiteHelper.addWord(new Word("veterinarian", "weterynarz", "zawody"));
        mySQLiteHelper.addWord(new Word("nurse", "pielęgniarka", "zawody"));
        mySQLiteHelper.addWord(new Word("pharmacist", "farmaceuta", "zawody"));
        mySQLiteHelper.addWord(new Word("teacher", "nauczyciel", "zawody"));
        mySQLiteHelper.addWord(new Word("lecturer", "wykładowca", "zawody"));
        mySQLiteHelper.addWord(new Word("lawyer", "prawnik", "zawody"));
        mySQLiteHelper.addWord(new Word("prosecutor", "prokurator", "zawody"));
        mySQLiteHelper.addWord(new Word("artist", "artysta", "zawody"));
        mySQLiteHelper.addWord(new Word("painter", "malarz", "zawody"));
        mySQLiteHelper.addWord(new Word("sculptor", "rzeźbiarz", "zawody"));
        mySQLiteHelper.addWord(new Word("designer", "projektant", "zawody"));
        mySQLiteHelper.addWord(new Word("musician", "muzyk", "zawody"));
        mySQLiteHelper.addWord(new Word("cunductor", "dyrygent", "zawody"));
        mySQLiteHelper.addWord(new Word("violinist", "skrzypek", "zawody"));
        mySQLiteHelper.addWord(new Word("director", "reżyser", "zawody"));
        mySQLiteHelper.addWord(new Word("writer", "pisarz", "zawody"));
        mySQLiteHelper.addWord(new Word("novelist", "powieściopisarz", "zawody"));
        mySQLiteHelper.addWord(new Word("journalist", "dziennikarz", "zawody"));
        mySQLiteHelper.addWord(new Word("accountant", "księgowy", "zawody"));
        mySQLiteHelper.addWord(new Word("clerk", "urzędnik", "zawody"));
        mySQLiteHelper.addWord(new Word("secretary", "sekretarz", "zawody"));
        mySQLiteHelper.addWord(new Word("cashier", "kasjer", "zawody"));
        mySQLiteHelper.addWord(new Word("firefighter", "strażak", "zawody"));
        mySQLiteHelper.addWord(new Word("policemen", "policjant", "zawody"));
        mySQLiteHelper.addWord(new Word("mailman", "listonosz", "zawody"));
        mySQLiteHelper.addWord(new Word("sailor", "marynarz", "zawody"));
        mySQLiteHelper.addWord(new Word("gardener", "ogrodnik", "zawody"));
        mySQLiteHelper.addWord(new Word("gravedigger", "grabarz", "zawody"));
        mySQLiteHelper.addWord(new Word("inventor", "wynalazca", "zawody"));
        mySQLiteHelper.addWord(new Word("spy", "szpieg", "zawody"));
        mySQLiteHelper.addWord(new Word("translator", "tłumacz", "zawody"));
        mySQLiteHelper.addWord(new Word("photographer", "fotograf", "zawody"));
        mySQLiteHelper.addWord(new Word("driver", "kierowca", "zawody"));
        mySQLiteHelper.addWord(new Word("politician", "polityk", "zawody"));
        mySQLiteHelper.addWord(new Word("hairdresser", "fryzjer", "zawody"));
        mySQLiteHelper.addWord(new Word("aviator", "lotnik", "zawody"));
        mySQLiteHelper.addWord(new Word("miner", "górnik", "zawody"));
        mySQLiteHelper.addWord(new Word("librarian", "bibliotekarz", "zawody"));
        mySQLiteHelper.addWord(new Word("electrician", "elektryk", "zawody"));
        mySQLiteHelper.addWord(new Word("doorman", "portier", "zawody"));
        mySQLiteHelper.addWord(new Word("farmer", "rolnik", "zawody"));
        mySQLiteHelper.addWord(new Word("maid", "sprzątaczka", "zawody"));
        mySQLiteHelper.addWord(new Word("beautician", "kosmetyczka", "zawody"));
        mySQLiteHelper.addWord(new Word("baker", "piekarz", "zawody"));
        mySQLiteHelper.addWord(new Word("plumber", "hydraulik", "zawody"));
        mySQLiteHelper.addWord(new Word("stockbroker", "makler giełdowy", "zawody"));
*/

    }

    private void init(){
        initListView();
    }

    private void initListView(){
        final String[] buttonsText = mySQLiteHelper.getCategories();
        ListView listView = findViewById(R.id.categoryListView);
        ButtonsAdapter buttonsAdapter = new ButtonsAdapter(this, R.layout.custom_button, buttonsText);
        listView.setAdapter(buttonsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startGameActivity(buttonsText[i]);

            }
        });
    }

    public void startGameActivity(String category){
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        extras.putString("category", category);
        Intent newActivityStart = new Intent(ChooseCategoryActivity.this, GameActivity.class);
        newActivityStart.putExtras(extras);
        Log.d("Halo", "startGameActivity: ");
        ChooseCategoryActivity.this.startActivity(newActivityStart);
    }
}
