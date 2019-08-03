package com.andrejeru.excercises.stacks;

import com.andrejeru.collections.ArraysUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MultiStack<T> {
    public static final int DEFAULT_CAPACITY = 300;

    private Object[] data;
    private int capacity = DEFAULT_CAPACITY;
    private List<StackInfo> stackInfos  = new ArrayList<>();

    private int size = 0;

    public MultiStack(int capacity) {
        this.capacity = capacity;

        data = new Object[this.capacity];
    }

    public MultiStack() {
        data = new Object[this.capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * just for debugging
     * */
    Object[] getData() {
        return data;
    }

    /**
     * @return index of new stack
     * */
    public int createNewStack() {
        int numberOfOccupiedCells = getOccupiedCells();

        if(numberOfOccupiedCells == data.length) {
            throw new IllegalStateException("The data is full");
        }

        int newNextStartIndex = numberOfOccupiedCells;

        StackInfo stackInfo = new StackInfo();
        stackInfo.setLength(0);
        stackInfo.setStartingPosition(newNextStartIndex);

        stackInfos.add(stackInfo);

        return stackInfo.getLength() - 1;
    }

    public int getOccupiedCells() {
        return size;
    }

    public int getNumberOfStacks() {
        return stackInfos.size();
    }

    public Stack getStackByNumber(int number) {
        Stack stack = new Stack();
        StackInfo stackInfo = stackInfos.get(number);

        for (int i = stackInfo.getStartingPosition(); i < stackInfo.getLength() + stackInfo.getStartingPosition(); i++) {
            stack.push((T) data[i]);
        }

        return stack;
    }

    public T push(T element, int stackNumber) {
        checkIfFullThenException();

        if(stackNumber >= stackInfos.size()) {
            throw new IndexOutOfBoundsException("Stack number is greater then number of stacks");
        }
        StackInfo stackInfo = stackInfos.get(stackNumber);

        int newElementIndex = stackInfo.getStartingPosition() + stackInfo.getLength();

        // Shift all data to make space for new element
        ArraysUtil.shiftDataForward(data, newElementIndex, 1, size);

        data[newElementIndex] = element;

        if(stackNumber != stackInfos.size() - 1) {
            shiftAllStacksForward(stackNumber + 1);
        }

        size++;
        stackInfo.increaseLength();
        return element;
    }

    public boolean isFull() {
        return getOccupiedCells() == data.length;
    }

    private void checkIfFullThenException() {
        if(isFull()) {
            throw new IllegalStateException();
        }
    }

    private void shiftAllStacksForward(int startingFromIndex) {
        if(startingFromIndex >= stackInfos.size()) {
            throw new IndexOutOfBoundsException();
        }

        for(int i = startingFromIndex; i < stackInfos.size(); i++) {
            StackInfo curStackInfo = stackInfos.get(i);

            int oldStartingPosition = curStackInfo.getStartingPosition();

            curStackInfo.setStartingPosition(oldStartingPosition + 1);
        }
    }
}
