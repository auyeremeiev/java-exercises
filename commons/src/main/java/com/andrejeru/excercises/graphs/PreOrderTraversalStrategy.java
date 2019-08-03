package com.andrejeru.excercises.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PreOrderTraversalStrategy<T> extends AbstractTreeTraversalStrategy<T> {
    @Override
    public void doTraversal(Tree<T> tree, Consumer<T> callback) {
        Tree.Vertex<T> root = tree.getRoot();
        if(root != null) {
            doTraversalInternal(root, callback);
        }
    }

    private void doTraversalInternal(Tree.Vertex<T> vertex, Consumer<T> callback) {
        if(vertex != null) {
            callback.accept(vertex.getValue());

            doTraversalInternal(vertex.getLeftChild(), callback);
            doTraversalInternal(vertex.getRightChild(), callback);
        }
    }
}
