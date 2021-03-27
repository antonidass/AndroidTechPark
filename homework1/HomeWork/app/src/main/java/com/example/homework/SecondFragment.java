package com.example.homework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    // Ключи для доступа к сохраненному состоянию
    private static final String data_key = "data";
    private static final String color_key = "color";

    private String number;
    private int color;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_second, container, false);

        if (savedInstanceState != null) {
            number = savedInstanceState.getString(data_key);
            color = savedInstanceState.getInt(color_key);
        }

        TextView textView = view.findViewById(R.id.big_number);
        textView.setText(number);
        textView.setTextColor(color);

        return view;
    }

    public void setParams(String number, int color) {
        this.number = number;
        this.color = color;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(data_key, number);
        outState.putInt(color_key, color);
    }
}