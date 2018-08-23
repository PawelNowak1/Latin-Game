package com.latingame.pawel.latingame.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.latingame.pawel.latingame.R;

import java.util.ArrayList;
import java.util.List;

public class EditTextsAdapter extends ArrayAdapter<String> {

    private int layoutResource;
    public ArrayList<EditText> editTexts;

    public EditTextsAdapter(Context context, int layoutResource, List<String> hints) {
        super(context, layoutResource, hints);
        this.layoutResource = layoutResource;
        editTexts = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        String hint = getItem(position);

        if (hint != null) {
            EditText editText = view.findViewById(R.id.editText);
            if (editText != null && editText.getHint() == null) {
                editText.setHint(hint);
                editTexts.add(editText);
            }
        }
        return view;
    }
}