package com.andrejeru.excercises.strings;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tasks {

    /**
     * 1.1
     * Implement an algorithm to determine if a string has all unique characters. What if you
     * can not use additional data structures?
     * */
    public static boolean allCharactersUnique(String str) {
        // Capitals must also be considered as well as different charset and special characters. But
        // in favor of simplicity made so.
        for (int i = 0; i <= (int) 'z' - (int) 'a'; i++) {

            boolean alreadyFound = false;
            for (int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == (char) ('a' + i)) {
                    if(alreadyFound)
                        return false;

                    alreadyFound = true;
                }
            }
        }

        return true;
    }

    /**
     * 1.1
     * Implement an algorithm to determine if a string has all unique characters. What if you
     * can not use additional data structures?
     * */
    public static boolean allCharactersUniqueWithBitvector(String str) {
        // Capitals must also be considered as well as different charset and special characters. But
        // in favor of simplicity made so.
        int bitVector = 0;
        for(int i =0; i < str.length(); i++) {
            char c = str.charAt(i);
            int value = c - 'a';

            if((bitVector & (1 << value)) > 0) {
                return false;
            }
            else {
                bitVector |= (1 << value);
            }

        }


        return true;
    }

    /**
     * Write code to reverse a C-Style String. (C-String means that "abcd" is represented as
     * five characters, including the null character.)
     * */
    public static String reverseString(String str) {
        StringBuilder result = new StringBuilder(str);
        result.append('\0');

        for(int i = 0; i < result.length() / 2; i++)
            StringUtils.swap(result, i, result.length() - 1 - i);

        return result.toString();
    }

    /**
     * Design an algorithm and write code to remove the duplicate characters in a string
     * without using an extra copy of the array.
     *
     * FOLLOW UP
     * Write the test cases for this method.
     * */
    public static String distinct(String str) {
        Set<Character> presentInStr = new HashSet<>();

        StringBuilder result = new StringBuilder(str);

        for(int i = 0; i < result.length(); i++) {
            char curChar = result.charAt(i);
            if(presentInStr.contains(curChar)) {
                result.deleteCharAt(i);
                i--;
            }
            else {
                presentInStr.add(curChar);
            }
        }

        return result.toString();
    }

    /**
     * Write a method to decide if two strings are anagrams or not.
     * */
    public static boolean areAnagrams(String s1, String s2) {
        if(s1.length() != s2.length())
            return false;

        // With sorting it would take O(n logn) which is slower
        Map<Character, Integer> charsInS1 = StringUtils.calculateChars(s1);
        Map<Character, Integer> charsInS2 = StringUtils.calculateChars(s2);

        return charsInS1.equals(charsInS2);
    }

    /**
     * Write a method to replace all spaces in a string with `%20`.
     *
     * Implementation commentary:
     * StringBuilder#append method can have 0(n) complexity, because it sometimes reallocate the whole array inside,
     * when the resulting size exceeds the array size. It its better to do it using additional char array.
     */
    public static String replace(String string) {
        char[] chars = string.toCharArray();

        int spaceCount = 0;

        // Calculating all spaces
        for (char aChar : chars) {
            if(aChar == ' ')
                spaceCount++;
        }

        // Determine the new array size
        int newSizeArray = chars.length + spaceCount * 2;

        int resultingArrayIndex = 0;
        char[] result = new char[newSizeArray];
        for (char aChar : chars) {
            if(aChar == ' ') {
                result[resultingArrayIndex++] = '%';
                result[resultingArrayIndex++] = '2';
                result[resultingArrayIndex++] = '0';
            }
            else {
                result[resultingArrayIndex++] = aChar;
            }
        }

        return String.valueOf(result);
    }

    /**
     * Given an image represented by an NxN matrix, where each pixel in the image is 4
     * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
     * */
    public static int[][] rotate(int[][] matrix) {
        int N = matrix.length;

        for (int i = 0; i < N / 2; i++) {
            for(int j = i; j < N - 1 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N - 1 - j][i];
                matrix[N - 1 - j][i] = matrix[N - 1- i][N - 1 - j];
                matrix[N - 1 - i][N - 1- j] = matrix[j][N - 1 - i];
                matrix[j][N - 1 - i] = temp;
            }

        }

        return matrix;
    }

    /**
     * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
     * column is set to 0.
     *
     * Note: changes array content inside
     * */
    public static int[][] nulifyRowsAndColumns(int[][] matrix) {
        Set<Integer> columnsToNulify = new HashSet<>();
        Set<Integer> rowsToNulify = new HashSet<>();


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    rowsToNulify.add(i);
                    columnsToNulify.add(j);
                }
            }
        }

        for (int i : rowsToNulify) {
            matrix[i] = new int[matrix[i].length];
        }

        for (int j : columnsToNulify) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][j] = 0;
        }

        return matrix;
    }

    /**
     * Assume you have a method isSubstring which checks if one word is a substring of
     * another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
     * only one call to isSubstring (i.e., "watterbottle" is a rotation of "erbottlewat").
     * */
    public static boolean isRotation(String str1, String str2) {
        str2 += str2;

        return str2.contains(str1);
    }

    /**
     * Special class for utils, to separate pure implementation from auxiliary methods
     *
     * */
    private static class Utils {
        /**
         * Swaps elements in matrix
         *
         *
         * @param matrix matrix in which elements to swap
         * @param i1 x axis index of first element
         * @param j1 y axis index of first element
         * @param i2 x axis index of second element
         * @param j2 y axis index of second element
         */
        public static int[][] swap(int[][] matrix, int i1, int j1, int i2, int j2) {
            int temp = matrix[i1][j1];

            matrix[i1][j1] = matrix[i2][j2];
            matrix[i2][j2] = temp;

            return matrix;
        }
    }
}
