package com.latingame.pawel.latingame.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.latingame.pawel.latingame.ChooseCategoryActivity;
import com.latingame.pawel.latingame.GiveNamesActivity;
import com.latingame.pawel.latingame.R;

import java.util.ArrayList;
import java.util.List;

public class ButtonsAdapter extends ArrayAdapter<String>{

    private int layoutResource;

    public ButtonsAdapter(Context context, int layoutResource, String[] buttonsText) {
        super(context, layoutResource, buttonsText);
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

        String buttonText = getItem(position);

        if (buttonText != null) {
            Button button = view.findViewById(R.id.button_custom);
            if (button != null) {
                button.setText(buttonText);
            }

        }
        return view;
    }
}
