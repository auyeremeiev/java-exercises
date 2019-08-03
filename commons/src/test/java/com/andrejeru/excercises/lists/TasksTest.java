package com.andrejeru.excercises.lists;

import com.andrejeru.collections.SimpleForwardLinkedList;
import org.junit.Test;

import java.util.Arrays;

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

        assertEquals(SimpleForwardLinkedList.fromList(8), Tasks.sum(firstNumb, secondNumb));

        firstNumb.add('1');
        firstNumb.add('5');
        secondNumb.add('9');
        secondNumb.add('2');

        assertEquals(SimpleForwardLinkedList.fromList(8,0,8), Tasks.sum(firstNumb, secondNumb));

        secondNumb.add('1');
        assertEquals(SimpleForwardLinkedList.fromList(1,8,0,8), Tasks.sum(firstNumb, secondNumb));
    }

    @Test
    public void testFindLoopNode_withoutLoop() {
        SimpleForwardLinkedList<Integer> withoutLoopTest = new SimpleForwardLinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        assertNull(Tasks.findLoopNode(withoutLoopTest));
    }

    @Test
    public void testFindLoopNode_withLoop() {
        SimpleForwardLinkedList<Integer> withoutLoopTest = new SimpleForwardLinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));

        SimpleForwardLinkedList.Node<Integer> loopNode = withoutLoopTest.get(5);
        loopNode.setNext(withoutLoopTest.get(2));

        assertEquals(withoutLoopTest.get(2).getContent(), Tasks.findLoopNode(withoutLoopTest));
    }

    @Test
    public void testFindLoopNode_emptyList() {
        SimpleForwardLinkedList<Integer> withoutLoopTest = new SimpleForwardLinkedList<>();

        assertNull(Tasks.findLoopNode(withoutLoopTest));
    }

    @Test
    public void testFindLoopNode_withLoopLastElement() {
        SimpleForwardLinkedList<Integer> withoutLoopTest = new SimpleForwardLinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));

        SimpleForwardLinkedList.Node<Integer> loopNode = withoutLoopTest.get(6);
        loopNode.setNext(withoutLoopTest.get(0));

        assertEquals(withoutLoopTest.get(0).getContent(), Tasks.findLoopNode(withoutLoopTest));
    }

}