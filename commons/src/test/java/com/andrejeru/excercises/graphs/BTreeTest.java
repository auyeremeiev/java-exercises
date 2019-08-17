package com.andrejeru.excercises.graphs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BTreeTest {
    @Test
    public void testDummyCase() {
        BTree<Integer> bTree = new BTree<>();

        List<Integer> allElements = bTree.getAllElements();
        assertTrue(allElements.isEmpty());
    }

    @Test
    public void testSimpleAdd() {
        BTree<Integer> bTree = new BTree<>();

        bTree.add(10);
        bTree.add(20);

        List<Integer> actual = bTree.getAllElements();
        List<Integer> expected = Arrays.asList(10,20);
        assertEquals(expected, actual);
    }

    @Test
    public void testSimpleAdd2() {
        BTree<Integer> bTree = new BTree<>();

        bTree.add(10);
        bTree.add(20);
        bTree.add(0);

        List<Integer> actual = bTree.getAllElements();
        List<Integer> expected = Arrays.asList(0,10,20);
        assertEquals(expected, actual);
    }

//    @Test
//    public void testComplexAdd() {
//        BTree<Integer> bTree = new BTree<>();
//
//        bTree.add(10);
//        bTree.add(20);
//        bTree.add(0);
//
//        bTree.add(35);
//
//        List<Integer> actual = bTree.getAllElements();
//        List<Integer> expected = Arrays.asList(0,10,20,35);
//        assertEquals(expected, actual);
//
//        bTree.add(15);
//        bTree.add(30);
//        bTree.add(5);
//
//        actual = bTree.getAllElements();
//        expected = Arrays.asList(0,5,10,15,20,30,35);
//        assertEquals(expected, actual);
//
//
//        bTree.add(18);
//        bTree.add(17);
//        bTree.add(40);
//        bTree.add(13);
//
//        actual = bTree.getAllElements();
//        expected = Arrays.asList(0,5,10,13,15,17,18,20,30,35,40);
//        assertEquals(expected, actual);
//
//        bTree.add(25);
//
//        actual = bTree.getAllElements();
//        expected = Arrays.asList(0,5,10,13,15,17,18,20,25,30,35,40);
//        assertEquals(expected, actual);
//
//
//    }

}