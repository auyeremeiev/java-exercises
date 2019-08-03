package com.andrejeru.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleForwardLinkedListTest {

    @Test
    public void testConstructor() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6);

        SimpleForwardLinkedList<Integer> list = new SimpleForwardLinkedList<>(data);

        assertEquals(data, list.toList());
    }

}