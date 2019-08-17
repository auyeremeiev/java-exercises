package com.andrejeru.sorts;

import java.util.Arrays;

import static com.andrejeru.collections.ArraysUtil.swap;

/**
 *
 * The idea of a merge sort is following : firstly we go done deeply into recursion to get small
 * arrays(1 size) and go up until we reach the entire array soring smaller parts of array until
 * we reach whole sorted array, using results of sorting of subarrays. When we have 2 subarrayas
 * which are definitely sorted, it is easier to merge them into bigger sorted array. Working with
 * sorted arrays gives ability to make simpler actions to sort them.
 *
 * Of course when we are at some point of time in recursion it is guaranteed that 2 subarrays are sorted.
 *
 * Because for the deepest case we will have just 1 element array which definitely can be considered as sorted
 * then it is obvious that we can merge two arrays of size 1 and get another sorted arrays and in the result get
 * sorted the entire array.
 *
 * The idea of algorithm is following:
 *
 * 1) Divide array into 2 subarrays
 * 2) Recursive do merge sort to that arrays
 * 3) Merge two subarrays as sorted arrays (they are sorted in recursion on a lower level).
 *
 * Worst and best cases are O (n log n) but requires O (n) additional space.
 *
 * Merge sort is a stable sort.
 *
 * */
public class MergeSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public T[] sort(T[] data) {
        T[] arrayCopy = Arrays.copyOf(data, data.length);

        if(data.length == 0) {
            return arrayCopy;
        }

        int firstIndex = 0;
        int lastIndex = data.length - 1;

        doSort(arrayCopy, firstIndex, lastIndex);

        return arrayCopy;
    }

    private void doSort(T[] data, int fromIndex, int lastIndex) {
        if(fromIndex != lastIndex) {
            int middleIndex = (fromIndex + lastIndex) / 2;

            doSort(data, fromIndex, middleIndex);
            doSort(data, middleIndex + 1, lastIndex);

            // Merge sorting parts results of array. middle index defines where the first part ends
            merge(data, fromIndex, middleIndex, lastIndex);
        }
    }

    // Needs refactoring. Use some general independent index for temp array
    // Or make 2 copies of left sub array ind right subarray and copy element from there
    // into original one in place
    private void merge(Comparable[] data, int fromIndex, int middleIndex, int lastIndex) {
        int secondPartStartingIndex = middleIndex + 1;

        // Size is +1 because with are including the last element
        Comparable[] tempArray = new Comparable[lastIndex - fromIndex + 1];

        int i = fromIndex, j = secondPartStartingIndex;
        while(i <= middleIndex && j <= lastIndex) {
            int firstSubarrayIndex = i - fromIndex;
            int secondSubarrayIndex = j - secondPartStartingIndex;

            if(data[j].compareTo(data[i]) > 0) {
                tempArray[firstSubarrayIndex + secondSubarrayIndex] = data[i];
                i++;
            } else {
                tempArray[firstSubarrayIndex + secondSubarrayIndex] = data[j];
                j++;
            }
        }

        int secondSubArraySize = lastIndex - middleIndex;
        int firstSubArraySize = secondPartStartingIndex - fromIndex;

        while(i <= middleIndex) {
            tempArray[i - fromIndex + secondSubArraySize] = data[i];
            i++;
        }

        while(j <= lastIndex) {
            tempArray[firstSubArraySize + j - fromIndex] = data[j];

            j++;
        }

        for(int k = fromIndex; k <= lastIndex; k++) {
            data[k] = tempArray[k - fromIndex];
        }
    }
}
