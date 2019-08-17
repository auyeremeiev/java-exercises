package com.andrejeru.excercises.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * */
public class BTree<T extends Comparable> {
    private static int DEFAULT_MAX_KEY_SIZE = 3;

    private Vertex<T> root;

    private int mexKeySize = DEFAULT_MAX_KEY_SIZE;

    public BTree(int mexKeySize) {
        this.mexKeySize = mexKeySize;
    }

    public BTree() {
    }

    public void add(T element) {
        if(root == null) {
            root = createNewVertex();
        }

        addToSubtree(root, element);
    }

    private void addToSubtree(Vertex<T> vertex, T element) {
        Object[] keys = vertex.getKeys();
        int nodeKeySize = vertex.getSize();

        // There is a buffer in 1 element for extra element for division
        for(int i = 0; i <= vertex.getMaxKeys(); i++) {
            // If element is needed to be just appended to the end
            // Last child is n < x < positive infinite
            // It cannot be represented in nodes
            if(i == nodeKeySize) {
                Vertex<T> childAt = vertex.getChildAt(i);
                if(childAt != null) {
                    addToSubtree(childAt, element);
                } else {
                    vertex.add(element);

                    adjustSizeViolation(vertex);
                }

                return;
            }
            // if period is found in which to doInsertKey. Loops until the first element
            // which is higher then the element.
            else if(element.compareTo(keys[i]) < 0) {
                Vertex<T> childAt = vertex.getChildAt(i);
                if(childAt!= null) {
                    addToSubtree(childAt, element);
                } else {
                    vertex.doInsertKey(i, element);

                    adjustSizeViolation(vertex);
                }
                return;
            }


        }
    }

    private void adjustSizeViolation(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();
        if(vertex.getSize() > vertex.getMaxKeys()) {
            int theMiddleIndex = vertex.getTheMiddleIndex();

            T middleKey = vertex.getKeyAt(theMiddleIndex);

            Vertex<T> separatedVertex = vertex.separateVertex(theMiddleIndex + 1);

            if(parent == null) {
                Vertex<T> newRoot = new Vertex<>(mexKeySize);

                this.root = newRoot;

                vertex.setParent(newRoot);

                newRoot.insertChild(0, vertex);
            }

            parent = vertex.getParent();
            int newIndexInParent = parent.insertIntoParent(middleKey);
            adjustSizeViolation(parent);

            parent.insertChild(newIndexInParent + 1, separatedVertex);

            vertex.removeLast();
        }
    }

    private Vertex<T> createNewVertex() {
        return new Vertex<>(mexKeySize);
    }

    public List<T> getAllElements() {
        ArrayList<T> elements = new ArrayList<>();

        doGetAllElements(root, elements);

        return elements;
    }

    private void doGetAllElements(Vertex<T> vertex, ArrayList<T> elements) {
        if(vertex != null) {
            for (int i = 0; i < vertex.size; i++) {
                if (vertex.getChildAt(i) != null) {
                    doGetAllElements(vertex.getChildAt(i), elements);
                }

                elements.add(vertex.getKeyAt(i));
            }

            doGetAllElements(vertex.getChildAt(vertex.size), elements);
        }
    }

    static class Vertex<T extends Comparable> {
        private int size = 0;
        private Object[] keys;
        // Here the index - the key index to which this child is assigned
        // It always has one more child which points to all elements which are bigger
        // then the last one until positive infinite value
        private Vertex<T>[] children;
        private boolean isLeaf = true;
        private Vertex<T> parent;

        final Integer maxKeys;

        public Vertex(int maxKeys) {
            this.maxKeys = maxKeys;

            // Buffer with 1 size, so that I could firstly add an eleents,
            // then check whether it exceeds the size and if it doesd, divide.
            // Otherwize, It would be incovenient because, I would need to check whether it is full
            // first
            keys = new Object[maxKeys + 1];
            children = new Vertex[maxKeys + 2];
        }

        public Vertex(Vertex<T> parent, Integer maxKeys) {
            this(maxKeys);

            this.parent = parent;
        }

        public Vertex(Vertex<T> parent, Integer maxKeys, List<T> newElements) {
            this(maxKeys);

            this.parent = parent;

            for (T newElement : newElements) {
                add(newElement);
            }
        }

        public int getSize() {
            return size;
        }

        public Object[] getKeys() {
            return keys;
        }

        public Vertex<T>[] getChildren() {
            return children;
        }

        public Vertex<T> getChildAt(int index) {
            return children[index];
        }

        public Integer getMaxKeys() {
            return maxKeys;
        }

        public Vertex<T> getParent() {
            return parent;
        }

        public void setParent(Vertex<T> parent) {
            this.parent = parent;
        }

        public void add(T element) {
            keys[size] = element;

            size++;
        }

        private int insertIntoParent(T element) {
            for(int i = 0; i < keys.length; i++) {
                if(keys[i] == null || element.compareTo(keys[i]) < 0) {
                    doInsertKey(i, element);
                    // Old i-th child points to the right new key
                    // but old shifted value require new child which will contain
                    // elements which are lower for exactly this old shifted value
                    doInsertChild(i + 1);

                    return i;
                }
            }

            return -1;
        }

        private Vertex<T> doInsertChild(int index) {
            // Vertex always contains +1 children then number of keys
            int elementsToShiftLength = size - index + 1;

            System.arraycopy(children, index, children, index + 1, elementsToShiftLength);

            Vertex<T> newChild = new Vertex<>(maxKeys);
            children[index] = newChild;

            return newChild;
        }

        private Vertex<T> getNextSibling() {
            Vertex<T> parent = getParent();
            if(parent == null) {
                throw new IllegalStateException("there is no parent node to find a sibling");
            }

            int childIndex = parent.getChildIndex(this);

            return parent.getChildAt(childIndex + 1);
        }

        private T getKeyAt(int i) {
            return (T) this.keys[i];
        }

        private boolean isFull() {
            return size >= maxKeys;
        }

        private int getTheMiddleIndex() {
            return keys.length / 2;
        }

        private T elementAt(int index) {
            return (T) keys[index];
        }

        private void doInsertKey(int index, T element) {
            int elementsToShiftLength = size - index;

            System.arraycopy(keys, index, keys, index + 1, elementsToShiftLength);
            keys[index] = element;

            size++;
        }

        private Vertex<T> separateVertex(int index) {
            Vertex<T> newVertex = new Vertex<>(maxKeys);

            List<T> elements = elementsToSeparate(index);
            newVertex.addAll(elements);

            if(!isLeaf) {
                List<Vertex<T>> children = childrenToSeparate(index);
                newVertex.children = children.toArray(new Vertex[0]);

                newVertex.isLeaf = false;
            }

            return newVertex;
        }

        private List<T> elementsToSeparate(int index) {
            //Just to simplify I used list
            List<T> elementsToSeparate = new ArrayList<>();

            for(int i = index; i < size; i++) {
                elementsToSeparate.add((T) keys[i]);

                keys[i] = null;
                size--;
            }

            return elementsToSeparate;
        }

        private List<Vertex<T>> childrenToSeparate(int index) {
            //Just to simplify I used list
            List<Vertex<T>> childrenToSeparate = new ArrayList<>();

            for(int i = index; i < size + 1; i++) {
                childrenToSeparate.add(children[i]);

                children[i] = null;
            }

            return childrenToSeparate;
        }

        private void removeLast() {
            keys[size - 1] = null;
            children[size] = null;

            size--;
        }

        private int getChildIndex(Vertex<T> vertex) {
            for(int i = 0; i < children.length; i++) {
                if(children[i] == vertex) {
                    return i;
                }
            }

            return -1;
        }

        private void addAll(List<T> elements){
            for (T element : elements) {
                add(element);
            }
        }

        public Vertex<T> insertChild(int index, Vertex<T> separatedVertex) {
            // Vertex always contains +1 children then number of keys
            int elementsToShiftLength = size - index + 1;

            System.arraycopy(children, index, children, index + 1, elementsToShiftLength);

            children[index] = separatedVertex;

            return separatedVertex;
        }
    }
}
