package com.latingame.pawel.latingame;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.latingame.pawel.latingame.adapters.EditTextsAdapter;

import java.util.ArrayList;
import java.util.List;

public class GiveNamesActivity extends AppCompatActivity {

    private EditTextsAdapter editTextsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_names);
        init();
    }

    private void init(){
        initListView();
        initButton();
    }

    private void initListView(){
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        final int progress = extras.getInt("Progress");

        List<String> hints = new ArrayList<>();

        for(int i = 1; i < progress + 1; i++)
        {
            hints.add("Nazwa gracza nr " + i);
        }

        ListView listView = findViewById(R.id.listView);
        editTextsAdapter = new EditTextsAdapter(this, R.layout.custom_edit_text, hints);
        listView.setAdapter(editTextsAdapter);
    }

    private void initButton(){
        Button button = findViewById(R.id.startGameBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] names = new String[editTextsAdapter.editTexts.size()];
                for(int i = 0; i < editTextsAdapter.editTexts.size(); i++)
                {
                    if(editTextsAdapter.editTexts.get(i).getText().length() == 0)
                        names[i] = "Gracz nr " + (i + 1);
                    else
                        names[i] = editTextsAdapter.editTexts.get(i).getText().toString();
                }

                Intent newActivityStart = new Intent(GiveNamesActivity.this, GameActivity.class);
                newActivityStart.putExtra("names", names);
                GiveNamesActivity.this.startActivity(newActivityStart);
            }
        });
    }

}
