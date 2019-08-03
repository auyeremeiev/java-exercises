package com.andrejeru.excercises.graphs;

import java.util.function.Consumer;

public class InOrderTreeTraversalStrategy<T> extends AbstractTreeTraversalStrategy<T> {
    @Override
    public void doTraversal(Tree<T> tree, Consumer<T> callback) {
        Tree.Vertex<T> root = tree.getRoot();
        if(root != null) {
            doTraversalInternal(root, callback);
        }
    }

    private void doTraversalInternal(Tree.Vertex<T> vertex, Consumer<T> callback) {
        if(vertex != null) {
            doTraversalInternal(vertex.getLeftChild(), callback);
            callback.accept(vertex.getValue());
            doTraversalInternal(vertex.getRightChild(), callback);
        }
    }
}
