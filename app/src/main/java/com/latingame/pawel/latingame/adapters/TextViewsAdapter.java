package com.latingame.pawel.latingame.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.latingame.pawel.latingame.R;

import java.util.List;

public class TextViewsAdapter extends ArrayAdapter<String> {

    private int layoutResource;

    public TextViewsAdapter(Context context, int layoutResource, String[] texts) {
        super(context, layoutResource, texts);
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

        String text = getItem(position);

        if (text != null) {
            TextView textView = view.findViewById(R.id.textViewCustom);
            if (textView != null) {
                textView.setText(text);
            }
        }
        return view;
    }
}
