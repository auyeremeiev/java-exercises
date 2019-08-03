package com.andrejeru.excercises.graphs;

import java.util.List;
import java.util.function.Consumer;

public interface TreeTraversalStrategy<T> {
    List<T> getAllElements(Tree<T> tree);

    void doTraversal(Tree<T> tree, Consumer<T> callback);
}
