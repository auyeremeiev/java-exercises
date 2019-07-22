package com.andrejeru.excercises.lists;

import com.andrejeru.collections.SimpleArrayList;
import com.andrejeru.collections.SimpleForwardLinkedList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TasksTest {
    @Test
    public void testHasDuplicates() {
        SimpleForwardLinkedList<Integer> data = new SimpleForwardLinkedList<>();

        assertFalse(Tasks.hasDuplicates(data));

        data.add(1);
        assertFalse(Tasks.hasDuplicates(data));

        data.add(2);
        data.add(3);
        data.add(4);
        assertFalse(Tasks.hasDuplicates(data));

        data.add(1);
        data.add(5);
        data.add(3);
        assertTrue(Tasks.hasDuplicates(data));

        data = new SimpleForwardLinkedList<>();
        data.add(1);
        data.add(1);
        assertTrue(Tasks.hasDuplicates(data));

        data = new SimpleForwardLinkedList<>();
        data.add(1);
        data.add(2);
        assertFalse(Tasks.hasDuplicates(data));
    }

    @Test
    public void testNthFromLast() {
        SimpleForwardLinkedList<Integer> data = new SimpleForwardLinkedList<>();
        data.add(1);
        assertEquals(Integer.valueOf(1), Tasks.nthFromLast(data, 1));

        data.add(2);
        data.add(3);
        data.add(3);
        data.add(4);
        data.add(7);
        data.add(5);
        data.add(3);

        assertEquals(Integer.valueOf(7), Tasks.nthFromLast(data, 3));

        assertEquals(Integer.valueOf(3), Tasks.nthFromLast(data, 1));

    }

    @Test
    public void testSum() {
        SimpleForwardLinkedList<Character> firstNumb = new SimpleForwardLinkedList<>();
        SimpleForwardLinkedList<Character> secondNumb = new SimpleForwardLinkedList<>();

        firstNumb.add('3');
        secondNumb.add('5');

        assertEquals(8, Tasks.sum(firstNumb, secondNumb));

        firstNumb.add('1');
        secondNumb.add('9');
        firstNumb.add('5');
        secondNumb.add('2');

        assertEquals(808, Tasks.sum(firstNumb, secondNumb));

        secondNumb.add('1');
        assertEquals(1808, Tasks.sum(firstNumb, secondNumb));


    }

}