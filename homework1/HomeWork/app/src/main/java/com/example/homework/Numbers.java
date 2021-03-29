package com.example.homework;

import java.util.ArrayList;

public class Numbers {
    private static Numbers instance;
    private static ArrayList<Integer> numbers = new ArrayList<>();

    private Numbers() {
        if (numbers.size() == 0) {
            for (int i = 1; i < 101; i++) {
                numbers.add(i);
            }
        }
    }

    public static void initInstance() {
        if (instance == null) {
            instance = new Numbers();
        }
    }

    public static ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public static void addNumber() {
        numbers.add(numbers.size() + 1);
    }

    public static void setNumbers(int lastNum) {
        ArrayList<Integer> tempNumbers = new ArrayList<>();

        for (int i = 1; i < lastNum + 1; i++) {
            tempNumbers.add(i);
        }

        numbers = tempNumbers;
    }

    public static int getSize() {
        return numbers.size();
    }
}