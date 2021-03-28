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

import java.util.List;


public class FirstFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyListAdapter adapter;
    private Button addNumberButton;

    private static final int PORTRAIT_COLS = 3;
    private static final int LANDSCAPE_COLS = 4;


    /* Добавляем число при нажатии на кнопку ADD NUMBER */
    public void onClickAddNumber(View view) {
        Numbers.addNumber();
        List<Integer> curData = Numbers.getNumbers();
        adapter.notifyItemInserted(curData.size() - 1);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.recycler);

        int spanCount = PORTRAIT_COLS;

        // Выбираем число столбцов в зависимости от ориентации ус-ва
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = LANDSCAPE_COLS;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), spanCount));

        adapter = new MyListAdapter(Numbers.getNumbers());
        recyclerView.setAdapter(adapter);

        addNumberButton = view.findViewById(R.id.next_number);
        addNumberButton.setOnClickListener(this::onClickAddNumber);

        return view;
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


    public class MyListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private final List<Integer> numbers;

        public MyListAdapter(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int getItemViewType(final int position) {
            return R.layout.frame_textview;
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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFragment((TextView) v);
                }
            });

            // Выбираем цвет в зависимости от четности
            if (numbers.get(position) % 2 == 0) {
                view.setTextColor(getResources().getColor(R.color.red));
            } else {
                view.setTextColor(getResources().getColor(R.color.blue));
            }

            view.setText(String.valueOf(numbers.get(position)));
        }

        @Override
        public int getItemCount() {
            return numbers.size();
        }
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView numberView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            numberView = itemView.findViewById(R.id.randomText);
        }

        public TextView getView() {
            return numberView;
        }
    }
}