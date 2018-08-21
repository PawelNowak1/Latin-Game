package com.latingame.pawel.latingame;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GiveNamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_names);
        init();
    }

    private void init(){
        initListView();
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
        EditTextsAdapter editTextsAdapter = new EditTextsAdapter(this, R.layout.custom_edit_text, hints);
        listView.setAdapter(editTextsAdapter);
    }

    class EditTextsAdapter extends ArrayAdapter<String>{

        private int layoutResource;

        public EditTextsAdapter(Context context, int layoutResource, List<String> hints) {
            super(context, layoutResource, hints);
            this.layoutResource = layoutResource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                view = layoutInflater.inflate(layoutResource, null);
            }

            String threeStrings = getItem(position);

            if (threeStrings != null) {
                EditText editText = view.findViewById(R.id.editText32);

                if (editText != null) {

                    editText.setHint(threeStrings);
                }
            }

            return view;
        }


    }

}
