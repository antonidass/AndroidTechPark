package com.example.homework;

import java.util.ArrayList;
import java.util.List;

public class Numbers {
    private static List<Integer> numbers = new ArrayList<>();

    public static List<Integer> getNumbers() {
        return numbers;
    }

    public static void addNumber() {
        numbers.add(numbers.size() + 1);
    }
}
