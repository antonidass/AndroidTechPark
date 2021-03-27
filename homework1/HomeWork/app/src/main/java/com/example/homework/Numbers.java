package com.example.homework;

import java.util.ArrayList;

public class Numbers {
    private static Numbers instance;
    private static ArrayList<Integer> numbers = new ArrayList<>();

    private Numbers() {
        if (numbers.size() == 0) {
            for (int i = 1; i < 101; i++) {
                this.numbers.add(i);
            }
        }
    }

    public static Numbers getInstance() {
        if (instance == null) {
            instance = new Numbers();
        }
        return instance;
    }

    public static ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public static void addNumber() {
        numbers.add(numbers.size() + 1);
    }

    public static int getSize() {
        return numbers.size();
    }

    public static void setNumbers(ArrayList<Integer> nums) {
        numbers = nums;
    }
}