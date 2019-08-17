package com.andrejeru.sorts;

import java.util.Arrays;

public class BubbleSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public T[] sort(T[] data) {
        T[] result = Arrays.copyOf(data, data.length);

        if(result.length > 1) {
            for (int i = 1; i < result.length; i++) {
                adjustOrderUntil(result, i);
            }
        }

        return result;
    }

    private void adjustOrderUntil(T[] data, int index) {
        if(data.length < 2 || index != 0) {
            int i = index;
            T curElement = data[i];
            T previousElement = data[i - 1];

            while (i > 0 && data[i].compareTo(data[i-1]) < 0) {
                swap(data, i, i-1);

                i--;
            }
        }
    }

    private void swap(T[] data, int firstIndex, int secondIndex) {
        T temp = data[firstIndex];

        data[firstIndex] = data[secondIndex];
        data[secondIndex] = temp;
    }
}
