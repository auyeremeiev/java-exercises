package com.andrejeru.excercises.lists;

import com.andrejeru.collections.SimpleForwardLinkedList;

import java.util.Iterator;

public class Tasks {

    /**
     * Write code to removeLast duplicates from an unsorted linked list.
     * FOLLOW UP
     * How would you solve this problem if a temporary buffer is not allowed?
     * */
    public static boolean hasDuplicates(SimpleForwardLinkedList list) {
        return list.hasDuplicates();
    }

    /**
     * Implement an algorithm to find the nth to last element of a singly linked list.
     * */
    public static <E> E nthFromLast(SimpleForwardLinkedList<E> list, int n) {
        return list.nthFromLast(n);
    }

    /**
     * You have two numbers represented by a linked list, where each node contains a sin-
     * gle digit. The digits are stored in reverse order, such that the 1’s digit is at the head of
     * the list. Write a function that adds the two numbers and returns the sum as a linked
     * list.
     * EXAMPLE
     * Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
     * Output: 8 -> 0 -> 8
     *
     * // Assuming with won't get bit number
     *
     * Can be made by only one loop using recursion
     */
    public static SimpleForwardLinkedList sum(SimpleForwardLinkedList<Character> firstNumber, SimpleForwardLinkedList<Character> secondNumber) {
        SimpleForwardLinkedList<Character> result = new SimpleForwardLinkedList<>();

        Iterator<Character> firstNumberIterator;
        Iterator<Character> secondNumberIterator;

        if(firstNumber.getSize() > secondNumber.getSize()) {
            firstNumberIterator = firstNumber.iterator();
            secondNumberIterator = secondNumber.iterator();
        }
        else {
            firstNumberIterator = secondNumber.iterator();
            secondNumberIterator = firstNumber.iterator();
        }


        boolean overflow = false;
        while (firstNumberIterator.hasNext()) {
            Character currentFirstNumberDigit = firstNumberIterator.next();
            int sumResult = Character.getNumericValue(currentFirstNumberDigit);

            if(overflow) {
                sumResult++;
            }

            if(secondNumberIterator.hasNext()) {
                sumResult  += Character.getNumericValue(secondNumberIterator.next());
            }

            result.add(Character.forDigit(sumResult % 10, 10));


            if(sumResult >= 10) {
                overflow = true;
            }
            else {
                overflow = false;
            }
        }

        if(overflow) {
            result.add('1');
        }

       return result;
    }

    /**
     * Given a circular linked list, implement an algorithm which returns node at the beginning of the loop.
     * DEFINITION
     * Circular linked list: A (corrupt) linked list in which a node’s next pointer points to an
     * earlier node, so as to make a loop in the linked list.
     * EXAMPLE
     * input: A -> B -> C -> D -> E -> C [the same C as earlier]
     * output: C
     * */
    public static <T> T findLoopNode(SimpleForwardLinkedList<T> list) {
        Iterator<T> iterator = list.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            T curElement = iterator.next();
            Iterator<T> innerIterator = list.iterator();
            int innerIndex = 0;
            while (innerIndex < index) {
                T nextAlreadyPastElement = innerIterator.next();
                if(nextAlreadyPastElement == curElement) {
                    return curElement;
                }
                innerIndex++;
            }

            index++;
        }

        return null;
    }

}
