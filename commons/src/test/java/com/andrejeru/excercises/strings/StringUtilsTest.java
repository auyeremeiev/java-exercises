package com.andrejeru.excercises.strings;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void testRemove() {
        char[] data = {'a', 'b', 'c', 'd', 'e'};

        char[] temporaryResult = {'b', 'c', 'd', 'e'};
        assertArrayEquals(StringUtils.remove(data, 0), temporaryResult);

        char[] temporaryResult2 = {'b', 'd', 'e'};
        assertArrayEquals(StringUtils.remove(temporaryResult, 1), temporaryResult2);

        char[] temporaryResult3 = {'b', 'd'};
        assertArrayEquals(StringUtils.remove(temporaryResult2, 2), temporaryResult3);
    }

    @Test
    public void testMoveCharWithShift() {
        char[] data = {'a', 'b', 'c', 'd', 'e'};

        char[] temporaryResult = {'c', 'a', 'b', 'd', 'e'};
        assertArrayEquals(StringUtils.moveCharWithShift(data, 2, 0), temporaryResult);

        char[] temporaryResult2 = {'c', 'a', 'e', 'b', 'd'};
        assertArrayEquals(StringUtils.moveCharWithShift(temporaryResult, 4, 2), temporaryResult2);

        char[] temporaryResult3 = {'d', 'c', 'a', 'e', 'b'};
        assertArrayEquals(StringUtils.moveCharWithShift(temporaryResult2, 4, 0), temporaryResult3);

        char[] temporaryResult4 = {'d', 'a', 'e', 'c', 'b'};
        assertArrayEquals(StringUtils.moveCharWithShift(temporaryResult3, 1, 3), temporaryResult4);

        char[] temporaryResult5 = {'d', 'c', 'a', 'e', 'b'};
        assertArrayEquals(StringUtils.moveCharWithShift(temporaryResult4, 3, 1), temporaryResult5);
    }

    @Test
    public void testDummyGetUniqueChars() {
        char[] data = {'a', 'b', 'c', 'd', 'e'};
        char[] expectedResult = {'a', 'b', 'c', 'd', 'e'};
        assertArrayEquals( StringUtils.getUniqueChars(data), expectedResult);
        assertTrue( StringUtils.areAllCharsUnique(String.valueOf(data)));
    }

    @Test
    public void testDublicatesCloseGetUniqueChars() {
        char[] data = {'a', 'b', 'c', 'd', 'd' ,'e'};
        char[] expectedResult = {'a', 'b', 'c', 'd', 'e'};
        assertArrayEquals( StringUtils.getUniqueChars(data), expectedResult);
        assertFalse( StringUtils.areAllCharsUnique(String.valueOf(data)));
    }

    @Test
    public void testSeveralDublicatesCloseGetUniqueChars() {
        char[] data = {'a', 'a', 'b', 'c', 'd', 'd' ,'e'};
        char[] expectedResult = {'a', 'b', 'c', 'd', 'e'};
        assertArrayEquals( StringUtils.getUniqueChars(data), expectedResult);
        assertFalse( StringUtils.areAllCharsUnique(String.valueOf(data)));
    }

    @Test
    public void testSeveralDublicatesCloseGetUniqueChars2() {
        char[] data = {'a', 'a', 'b', 'c', 'd', 'd' ,'e', 'e', 'e'};
        char[] expectedResult = {'a', 'b', 'c', 'd', 'e'};
        assertArrayEquals( StringUtils.getUniqueChars(data), expectedResult);
        assertFalse( StringUtils.areAllCharsUnique(String.valueOf(data)));
    }

    @Test
    public void testSeveralDublicatesGetUniqueChars3() {
        char[] data = {'a', 'a', 'b', 'c', 'd', 'e', 'd' ,'e', 'e', 'e'};
        char[] expectedResult = {'a', 'b', 'c', 'd', 'e'};
        assertArrayEquals( StringUtils.getUniqueChars(data), expectedResult);
        assertFalse( StringUtils.areAllCharsUnique(String.valueOf(data)));
    }

    @Test
    public void testSeveralDublicatesComplexGetUniqueChars3() {
        char[] data = {'a', 'a', 'b', 'c', 'd', 'e', 'd', 'c' ,'a', 'e', 'e', 'e', 'c'};
        char[] expectedResult = {'a', 'b', 'c', 'd', 'e'};
        assertArrayEquals( StringUtils.getUniqueChars(data), expectedResult);
        assertFalse(StringUtils.areAllCharsUnique(String.valueOf(data)));
    }

}