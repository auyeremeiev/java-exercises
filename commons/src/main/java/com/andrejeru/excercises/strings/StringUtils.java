package com.andrejeru.excercises.strings;

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
}
