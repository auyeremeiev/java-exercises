package com.andrejeru.excercises.graphs;

import java.util.List;

public abstract class AbstractTree<T> implements Tree<T> {
    @Override
    public List<T> getAllElements() {
        TreeTraversalStrategy treeTraversalStrategy = getTreeTraversalStrategy();

        if(treeTraversalStrategy == null) {
            throw new IllegalStateException("treeTraversalStrategy cannot be null");
        }

        return treeTraversalStrategy.getAllElements(this);
    }

    public abstract TreeTraversalStrategy<T> getTreeTraversalStrategy();

}
