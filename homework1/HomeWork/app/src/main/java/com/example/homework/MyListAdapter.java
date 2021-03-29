package com.example.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.RecyclerViewHolder> {
    private final ArrayList<Integer> numbers;
    private final MyCallback fragmentCallback;
    private final Context context;

    public MyListAdapter(ArrayList<Integer> numbers, MyCallback callbackFunc, Context context) {
        this.numbers = numbers;
        this.fragmentCallback = callbackFunc;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_textview, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        TextView view = holder.getView();
        view.setOnClickListener(v -> fragmentCallback.removeFragment((TextView) v));

        // Выбираем цвет в зависимости от четности
        if (numbers.get(position) % 2 == 0) {
            view.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            view.setTextColor(context.getResources().getColor(R.color.blue));
        }

        view.setText(String.valueOf(numbers.get(position)));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private final TextView numberView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.randomText);
        }

        public TextView getView() {
            return numberView;
        }
    }
}

