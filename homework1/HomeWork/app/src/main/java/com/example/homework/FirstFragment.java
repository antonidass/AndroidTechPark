package com.example.homework;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class FirstFragment extends Fragment implements MyCallback {
    private static final String data_key = "data";
    private MyListAdapter adapter;
    private Numbers instance; // синглтон

    /* Добавляем число при нажатии на кнопку ADD NUMBER */
    public void onClickAddNumber(View view) {
        instance.addNumber();
        adapter.notifyItemInserted(instance.getSize());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Integer> tempNumbers;

        if (savedInstanceState != null) {
            tempNumbers = savedInstanceState.getIntegerArrayList(data_key);
            instance.setNumbers(tempNumbers);
        } else {
            instance = Numbers.getInstance();
            tempNumbers = instance.getNumbers();
        }

        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        int spanCount = getResources().getInteger(R.integer.PORTRAIT_COLS);

        // Выбираем число столбцов в зависимости от ориентации ус-ва
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = getResources().getInteger(R.integer.LANDSCAPE_COLS);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), spanCount));

        adapter = new MyListAdapter(tempNumbers, this::removeFragment, this.getContext());
        recyclerView.setAdapter(adapter);

        Button addNumberButton = view.findViewById(R.id.next_number);
        addNumberButton.setOnClickListener(this::onClickAddNumber);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(data_key, instance.getNumbers());
    }

    /*
    Заменяем первый фрагмент на второй
     */
    public void removeFragment(TextView view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
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