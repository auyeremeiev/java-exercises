package com.andrejeru.excercises.graphs;

import java.util.List;

public interface Tree<T> {
    Vertex<T> getRoot();

    void addElement(T element);
    T removeElement(T element);
    boolean searchElement(T element);
    List<T> getAllElements();
    int getDepth();

    interface Vertex<T> {
        Vertex<T> getLeftChild();
        Vertex<T> getRightChild();

        T getValue();
    }
}
