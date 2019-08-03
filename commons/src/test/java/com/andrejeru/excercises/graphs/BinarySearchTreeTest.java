package com.andrejeru.excercises.graphs;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private <T extends Comparable> BinarySearchTree<T> defaultTree() {
        return BinarySearchTree.createBinaryTreeWithPreOrderTraversal();
    }


    private BinarySearchTree<Integer> getFilledBinaryTree() {
        BinarySearchTree<Integer> binaryTree = defaultTree();

        binaryTree.addElement(5);
        binaryTree.addElement(6);
        binaryTree.addElement(2);
        binaryTree.addElement(3);
        binaryTree.addElement(1);
        binaryTree.addElement(4);
        binaryTree.addElement(10);
        binaryTree.addElement(7);
        binaryTree.addElement(0);
        binaryTree.addElement(11);

        return binaryTree;
    }

    @Test
    public void testSimpleCase() {
        BinarySearchTree binaryTree = defaultTree();

        assertEquals(binaryTree.getDepth(), 0);
        assertNull(binaryTree.getRoot());
    }

    @Test
    public void testAdd() {
        List<Integer> actual = getFilledBinaryTree().getAllElements();
        List<Integer> expected = Arrays.asList(5,2,1,0,3,4,6,10,7,11);

        assertEquals(expected, actual);
    }

    @Test
    public void testAddWithInOrderTraversal() {
        BinarySearchTree<Integer> filledBinaryTree = getFilledBinaryTree();
        filledBinaryTree.setTreeTraversalStrategy(new InOrderTreeTraversalStrategy<>());

        List<Integer> actual = filledBinaryTree.getAllElements();
        List<Integer> expected = Arrays.asList(0,1,2,3,4,5,6,7,10,11);

        assertEquals(expected, actual);
    }

    @Test
    public void testComplexCase() {
        BinarySearchTree<Integer> filledBinaryTree = getFilledBinaryTree();

        filledBinaryTree.removeElement(5);

        List<Integer> actual = filledBinaryTree.getAllElements();
        List<Integer> expected = Arrays.asList(6,2,1,0,3,4,10,7,11);

        assertEquals(expected, actual);

        filledBinaryTree.removeElement(11);

        actual = filledBinaryTree.getAllElements();
        expected = Arrays.asList(6,2,1,0,3,4,10,7);

        assertEquals(expected, actual);

        filledBinaryTree.removeElement(10);

        actual = filledBinaryTree.getAllElements();
        expected = Arrays.asList(6,2,1,0,3,4,7);

        assertEquals(expected, actual);

        filledBinaryTree.removeElement(2);

        actual = filledBinaryTree.getAllElements();
        expected = Arrays.asList(6,1,0,3,4,7);

        assertEquals(expected, actual);

        filledBinaryTree.removeElement(3);

        actual = filledBinaryTree.getAllElements();
        expected = Arrays.asList(6,1,0,4,7);

        assertEquals(expected, actual);

        filledBinaryTree.addElement(5);

        actual = filledBinaryTree.getAllElements();
        expected = Arrays.asList(6,1,0,4,5,7);

        assertEquals(expected, actual);
    }
}