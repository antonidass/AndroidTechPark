package com.example.homework;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class FirstFragment extends Fragment implements MyCallback {
    private static final String data_key = "data";
    private MyListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int last_num;

        if (savedInstanceState != null) {
            last_num = savedInstanceState.getInt(data_key);
            Numbers.setNumbers(last_num);
        } else {
            Numbers.initInstance();
        }

        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        adapter = new MyListAdapter(Numbers.getNumbers(), this, this.getContext());
        recyclerView.setAdapter(adapter);

        Button addNumberButton = view.findViewById(R.id.next_number);
        addNumberButton.setOnClickListener(this::onClickAddNumber);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(data_key, Numbers.getSize());
    }

    /* Добавляем число при нажатии на кнопку ADD NUMBER */
    public void onClickAddNumber(View view) {
        Numbers.addNumber();
        adapter.notifyItemInserted(Numbers.getSize());
    }

    /* Заменяем первый фрагмент на второй */
    public void removeFragment(TextView view) {
        FragmentTransaction transaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        transaction.remove(this);

        // Передаем цвет и число во второй фрагмент
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setParams(view.getText().toString(), view.getCurrentTextColor());

        transaction.add(R.id.fragment_container, secondFragment);
        transaction.commit();

        // Добвляем в стек, чтобы получить возможность вернуться к первому фрагменту при нажатии кнопки "back"
        transaction.addToBackStack(null);
    }
}