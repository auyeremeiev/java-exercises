package com.andrejeru.excercises.strings;

import org.junit.Test;

import static org.junit.Assert.*;

public class TasksTest {

    @Test
    public void testAllCharactersUnique() {
        assertTrue(Tasks.allCharactersUnique(""));
        assertTrue(Tasks.allCharactersUnique("abc"));
        assertTrue(Tasks.allCharactersUnique("abcjgfxdert"));
        assertFalse(Tasks.allCharactersUnique("abcb"));
        assertFalse(Tasks.allCharactersUnique("abcabc"));
        assertFalse(Tasks.allCharactersUnique("abcjbklzz"));
        assertFalse(Tasks.allCharactersUnique("abbcscr"));
        assertFalse(Tasks.allCharactersUnique("zz"));
    }

    @Test
    public void testAllCharactersUniqueWithBitvector() {
        assertTrue(Tasks.allCharactersUniqueWithBitvector(""));
        assertTrue(Tasks.allCharactersUniqueWithBitvector("abc"));
        assertTrue(Tasks.allCharactersUniqueWithBitvector("abcjgfxdert"));
        assertFalse(Tasks.allCharactersUniqueWithBitvector("abcb"));
        assertFalse(Tasks.allCharactersUniqueWithBitvector("abcabc"));
        assertFalse(Tasks.allCharactersUniqueWithBitvector("abcjbklzz"));
        assertFalse(Tasks.allCharactersUniqueWithBitvector("abbcscr"));
        assertFalse(Tasks.allCharactersUniqueWithBitvector("zz"));
    }

    @Test
    public void testString() {
        String asd = "asdasdf";



        int length = asd.length();

        assertFalse(false   );
    }

    @Test
    public void reverseString() {
        assertEquals("\0", Tasks.reverseString(""));
        assertEquals("\0cba", Tasks.reverseString("abc"));
        assertEquals("\0tredxfgjcba", Tasks.reverseString("abcjgfxdert"));
        assertEquals("\0bcba", Tasks.reverseString("abcb"));
        assertEquals("\0cbacba", Tasks.reverseString("abcabc"));
        assertEquals("\0zzlkbjcba", Tasks.reverseString("abcjbklzz"));
        assertEquals("\0rcscbba", Tasks.reverseString("abbcscr"));
        assertEquals("\0zz", Tasks.reverseString("zz"));
    }

    @Test
    public void testDistinctString() {
        assertEquals("", Tasks.distinct(""));
        assertEquals("abc", Tasks.distinct("abc"));
        assertEquals("abcjgfxdert", Tasks.distinct("abcjgfxdert"));
        assertEquals("abc", Tasks.distinct("abcb"));
        assertEquals("abc", Tasks.distinct("abcabc"));
        assertEquals("abcjklz", Tasks.distinct("abcjbklzz"));
        assertEquals("abcsr", Tasks.distinct("abbcscr"));
        assertEquals("z", Tasks.distinct("zz"));
    }

    @Test
    public void testAreAnagrams() {
        assertTrue(Tasks.areAnagrams("", ""));
        assertTrue(Tasks.areAnagrams("abc", "bca"));
        assertTrue(Tasks.areAnagrams("abcjgfxdert", "cgjxbaedrtf"));
        assertTrue(Tasks.areAnagrams("abcb", "bbca"));
        assertTrue(Tasks.areAnagrams("abcabc", "aacbcb"));
        assertFalse(Tasks.areAnagrams("abcjbklzz", "zabbbzcjllk"));
        assertFalse(Tasks.areAnagrams("abbcscr", "accsr"));
        assertTrue(Tasks.areAnagrams("zz", "zz"));
    }

    @Test
    public void testReplace() {
        assertEquals("", Tasks.replace(""));
        assertEquals("abc%20bav", Tasks.replace("abc bav"));
        assertEquals("abc%20jgfx%20dert%20", Tasks.replace("abc jgfx dert "));
        assertEquals("%20ab%20cb", Tasks.replace(" ab cb"));
        assertEquals("%20abc%20abc%20", Tasks.replace(" abc abc "));
        assertEquals("abc%20%20abc", Tasks.replace("abc  abc"));
        assertEquals("abbcscr%20%20%20", Tasks.replace("abbcscr   "));
        assertEquals("%20%20abb%20c%20%20scr%20%20", Tasks.replace("  abb c  scr  "));
    }

    @Test
    public void testRotate() {
        assertEquals(new int[][]{}, Tasks.rotate(new int[][]{}));
        assertEquals(new int[][]{{1}}, Tasks.rotate(new int[][]{{1}}));

        int[][] data = {{1,2,3},
                        {8,9,4},
                        {7,6,5}};

        int[][] expected = {{7,8,1},
                            {6,9,2},
                            {5,4,3}};

        assertEquals(expected, Tasks.rotate(data));
    }

    @Test
    public void testNulifyRowsAndColumns() {
        assertEquals(new int[][]{}, Tasks.nulifyRowsAndColumns(new int[][]{}));
        assertEquals(new int[][]{{1}}, Tasks.nulifyRowsAndColumns(new int[][]{{1}}));
        assertEquals(new int[][]{{0}}, Tasks.nulifyRowsAndColumns(new int[][]{{0}}));
        assertEquals(new int[][]{{0,0},{0,3}}, Tasks.nulifyRowsAndColumns(new int[][]{{0,1},{2,3}}));

        int[][] data = {{1,2,3,10},
                        {4,0,6,0},
                        {7,8,9,0}};

        int[][] expected = {{1,0,3,0},
                            {0,0,0,0},
                            {0,0,0,0}};

        assertEquals(expected, Tasks.nulifyRowsAndColumns(data));
    }

    @Test
    public void isRotation() {
        String str = "";
        String rotated = "";

        assertTrue(Tasks.isRotation(str, rotated));

        str = "a";
        rotated = "a";
        assertTrue(Tasks.isRotation(str, rotated));

        str = "ab";
        rotated = "ab";
        assertTrue(Tasks.isRotation(str, rotated));

        str = "ab";
        rotated = "ba";
        assertTrue(Tasks.isRotation(str, rotated));

        str = "waterbottle";
        rotated = "erbottlewat";
        assertTrue(Tasks.isRotation(str, rotated));

        str = "waterbottle";
        rotated = "bottlewat";
        assertFalse(Tasks.isRotation(str, rotated));
    }
}