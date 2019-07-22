package com.andrejeru.collections;

import java.io.OptionalDataException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class ArraysUtil {

    private ArraysUtil() {
    }

    /**
     * @return index of element in array
     * */
    public static <T> int indexOf(T[] array, T element) {
        int index = -1;
        for(int i = 0; i < array.length; i++) {
            if (array[i].equals(element))
                index = i;
        }

        return index;
    }


    /**
     * Dynamically puts element to the first empty cell in array. If array size is greater than threshold coefficient, expands array in 1.5 times.
     *
     * @param array array to which add new element. Size can be modified if the occupancy exceeds the threshold.
     * @param element element to add to array.
     * @param threshold if array exceeds this coefficient - expand array int 1.5 times.
     *
     * @return index of new element in array
     * */
    public static <T> int add(T[] array, T element, double threshold) {
        T[] result = array;

        // If array exceeds the threshold, expand it
        if(exceedThreshold(array, threshold)) {
            result = Arrays.copyOf(array, (int) (array.length * 1.5));
        }

        int newIndex = putInFirstEmptyCell(array, element);

        array = result;

        return newIndex;
    }

    /**
     * @return new index of element in array.
     *
     * @throws IllegalArgumentException when array is full and new element can't be added
     * */
    private static <T> int putInFirstEmptyCell(T[] array, T element) {
        boolean arrayIsFull = true;

        for (int i = 0; i < array.length; i++) {
            // if first empty cell is found, put element in it and return it's index
            if (array[i] == null) {
                array[i] = element;
                arrayIsFull = false;
                return i;
            }
        }

        // if no empty cell is found -  array is full. Throw an exception.
        throw new IllegalArgumentException("The array with length " + array.length + " is full. Element cannot be added");
    }

    /**
     * @return  does the occupancy of array exceeds the threshold
     * */
    public static <T> boolean exceedThreshold(T[] array, double threshold) {
        int occupiedCells = 0;

        for(int i = 0; i < array.length; i++) {
            if (array[i] == null)
                occupiedCells++;
        }

        int occupancyRatio = (occupiedCells / array.length);
        return occupancyRatio > threshold;
    }

    /**
     * @return is the array full
     * */
    public static <T> boolean isFull(T[] array) {
        boolean isFull = true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null)
                isFull = false;
        }

        return isFull;
    }

    /**
     * Removes element from array and shifts each element on -1 index after this element. When the number of elements drops
     * lower than threshold - array's capacity is decreased
     * */
    public static <T> void removeWithShift(T[] array, T element, double threshold) {
        // TODO
    }
}
