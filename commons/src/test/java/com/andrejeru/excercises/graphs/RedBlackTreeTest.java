package com.andrejeru.excercises.graphs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RedBlackTreeTest {

    @Test
    public void testSimpleAddCase() {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        rbt.addElement(1000);

        rbt.setTreeTraversalStrategy(new PreOrderTraversalStrategy<>());
        assertEquals(rbt.getAllElements(), Arrays.asList(1000));
    }

    @Test
    public void testSimpleAddCase2() {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.setTreeTraversalStrategy(new PreOrderTraversalStrategy<>());

        rbt.addElement(10);
        rbt.addElement(15);
        rbt.addElement(5);

        assertEquals(rbt.getAllElements(), Arrays.asList(10, 5, 15));

    }

    @Test
    public void testComplexAddCase() {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.setTreeTraversalStrategy(new PreOrderTraversalStrategy<>());

        rbt.addElement(1000);
        rbt.addElement(1500);
        rbt.addElement(500);

        assertEquals(Arrays.asList(1000, 500, 1500), rbt.getAllElements());

        rbt.addElement(250);
        rbt.addElement(200);

        assertEquals(Arrays.asList(1000, 250, 200, 500, 1500), rbt.getAllElements());

        rbt.addElement(100);
        rbt.addElement(225);

        assertEquals(Arrays.asList(1000, 250, 200, 100, 225, 500, 1500), rbt.getAllElements());

        rbt.addElement(50);
        rbt.addElement(245);

        assertEquals(Arrays.asList(250, 200, 100, 50, 225, 245, 1000, 500, 1500), rbt.getAllElements());

        rbt.addElement(2000);
        rbt.addElement(1750);


        assertEquals(Arrays.asList(250, 200, 100, 50, 225, 245, 1000, 500, 1750, 1500, 2000), rbt.getAllElements());

    }

}