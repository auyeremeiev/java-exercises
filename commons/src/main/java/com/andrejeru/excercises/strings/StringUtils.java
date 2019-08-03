package com.andrejeru.excercises.strings;

import com.andrejeru.collections.ArraysUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    /**
     * Swaps 2 elements inside string builder. Changes content of StringBuilder.
     * */
    public static void swap(StringBuilder builder, int i, int j) {
        char charAti = builder.charAt(i);

        builder.setCharAt(i, builder.charAt(j));
        builder.setCharAt(j, charAti);
    }

    public static Map<Character, Integer> calculateChars(String s) {
        Map<Character, Integer> charsNumber = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if(charsNumber.containsKey(curChar))
                charsNumber.put(curChar, charsNumber.get(curChar) + 1);
            else
                charsNumber.put(curChar, 1);
        }

        return charsNumber;
    }

    /**
     * Implement an algorithm to determine if a string has all unique characters.
     *
     * @return true, if all chars in string are unique and false otherwise.
     * */
    public static boolean areAllCharsUnique(String str) {
        if(str == null) {
            return false;
        }

        return getUniqueChars(str).length == str.length();
    }

    public static char[] getUniqueChars(String str) {
        if(str == null) {
            throw new NullPointerException("String cannot be null");
        }

        char[] allChars = str.toCharArray();

        return getUniqueChars(allChars);
    }

    public static char[] getUniqueChars(char[] chars) {
        char[] allChars = Arrays.copyOf(chars, chars.length);

        int length = allChars.length;
        for(int i = 0; i < length; i++) {
            char uniqueCharCandidate = allChars[i];
            for(int j = 0; j < i; j++) {
                char tillNowUniqueChar = allChars[j];

                if(tillNowUniqueChar == uniqueCharCandidate) {
                    allChars = moveCharWithShift(allChars,i, j);
                    if(allChars[i] != uniqueCharCandidate) {
                        i--;
                    }

                    break;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            while(i + 1 < length && allChars[i] == allChars[i + 1]) {
                allChars = remove(allChars, i + 1);
                length--;
            }
        }

        return allChars;
    }

    public static char[] moveCharWithShift(char[] array, char ch, int newIndex) {
        int oldIndex = ArraysUtil.indexOf(array, ch);

        return moveCharWithShift(array, oldIndex, newIndex);
    }

    public static char[] moveCharWithShift(char[] array, int oldIndex, int newIndex) {
        char[] arrayCopy = Arrays.copyOf(array, array.length + 1);

        char charToMove = arrayCopy[oldIndex];

        // Make free space for char
        System.arraycopy(arrayCopy, newIndex, arrayCopy, newIndex + 1, arrayCopy.length - newIndex - 1);
        int indexToSet = newIndex > oldIndex ? newIndex + 1 : newIndex;
        arrayCopy[indexToSet] = charToMove;

        int dirtyElementIndex = newIndex > oldIndex ? oldIndex : oldIndex + 1;

        arrayCopy = remove(arrayCopy, dirtyElementIndex);

        return arrayCopy;
    }

    public static char[] remove(char[] array, int index) {
        char[] arrayCopy = Arrays.copyOf(array, array.length - 1);

        System.arraycopy(array, index + 1, arrayCopy, index, array.length - index - 1);

        return arrayCopy;
    }
}
