package com.andrejeru.excercises.stacks;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultiStackTest {

    @Test
    public void testObviousCase() {
        MultiStack<Integer> multiStack = new MultiStack<>();

        assertEquals(multiStack.getNumberOfStacks(), 0);

        assertEquals(multiStack.getOccupiedCells(), 0);
    }

    @Test
    public void testSimpleOneStackPushCase() {
        MultiStack<Integer> multiStack = new MultiStack<>();

        multiStack.createNewStack();

        multiStack.push(0, 0);
        multiStack.push(1, 0);
        multiStack.push(2, 0);
        multiStack.push(3, 0);
        multiStack.push(4, 0);

        assertEquals(multiStack.getStackByNumber(0).size(), 5);
        assertEquals(multiStack.getOccupiedCells(), 5);
    }

    @Test
    public void testSimpleManyStackPushCase() {
        MultiStack<Integer> multiStack = new MultiStack<>();

        multiStack.createNewStack();
        multiStack.createNewStack();
        multiStack.createNewStack();
        multiStack.createNewStack();

        multiStack.push(0, 0);
        multiStack.push(1, 0);
        multiStack.push(11, 1);
        multiStack.push(12, 1);
        multiStack.push(21, 2);
        multiStack.push(22, 2);
        multiStack.push(31, 3);

        assertEquals(multiStack.getStackByNumber(0).size(), 2);
        assertEquals(multiStack.getStackByNumber(1).size(), 2);
        assertEquals(multiStack.getStackByNumber(2).size(), 2);
        assertEquals(multiStack.getStackByNumber(3).size(), 1);
        assertEquals(multiStack.getOccupiedCells(), 7);


        assertEquals(multiStack.getStackByNumber(3).peek(), 31);
        assertEquals(multiStack.getStackByNumber(1).peek(), 12);
    }

    @Test
    public void testComplexManyStackPushCase() {
        MultiStack<Integer> multiStack = new MultiStack<>();

        multiStack.createNewStack();
        multiStack.createNewStack();
        multiStack.createNewStack();

        multiStack.push(0, 0);
        multiStack.push(21, 2);
        multiStack.push(12, 1);
        multiStack.push(1, 0);
        multiStack.push(11, 1);

        multiStack.createNewStack();

        multiStack.push(31, 3);
        multiStack.push(22, 2);

        assertEquals(multiStack.getStackByNumber(0).size(), 2);
        assertEquals(multiStack.getStackByNumber(1).size(), 2);
        assertEquals(multiStack.getStackByNumber(2).size(), 2);
        assertEquals(multiStack.getStackByNumber(3).size(), 1);
        assertEquals(multiStack.getOccupiedCells(), 7);


        assertEquals(multiStack.getStackByNumber(3).peek(), 31);
        assertEquals(multiStack.getStackByNumber(1).peek(), 11);
    }
}