package com.andrejeru.excercises.graphs;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTreeTraversalStrategy<T> implements TreeTraversalStrategy<T> {
    @Override
    public List<T> getAllElements(Tree<T> tree) {
        ArrayList<T> elements = new ArrayList<>();
        doTraversal(tree, elements::add);

        return elements;
    }


}
