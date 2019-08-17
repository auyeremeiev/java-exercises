package com.andrejeru.sorts;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSorterTest {
    private Sorter<Integer> sorter = new BubbleSorter<>();


    @Test
    public void testDummy() {
        Integer[] result = sorter.sort(new Integer[]{});

        assertEquals(0, result.length);
    }

    @Test
    public void testDummy2() {
        Integer[] result = sorter.sort(new Integer[]{100});

        assertEquals(1, result.length);
        assertArrayEquals(new Integer[]{100}, result);
    }

    @Test
    public void testForSorted() {
        Integer[] result = sorter.sort(new Integer[]{5, 1, 6, 2, 4, 3, 0});

        assertArrayEquals(new Integer[]{ 0, 1, 2, 3, 4, 5, 6}, result);
    }
}