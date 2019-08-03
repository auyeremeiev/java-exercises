package com.andrejeru.excercises.graphs;

public class BinarySearchTree<T extends Comparable> extends AbstractTree<T> {
    public enum Edge {
        LEFT,
        RIGHT
    }

    private Vertex<T> root;
    private int size = 0;

    private TreeTraversalStrategy<T> treeTraversalStrategy = new PreOrderTraversalStrategy<>();

    public BinarySearchTree() {
    }

    public BinarySearchTree(TreeTraversalStrategy<T> treeTraversalStrategy) {
        this.treeTraversalStrategy = treeTraversalStrategy;
    }

    @Override
    public TreeTraversalStrategy<T> getTreeTraversalStrategy() {
        return treeTraversalStrategy;
    }

    @Override
    public void addElement(T element) {
        if(root == null) {
            root = new Vertex<>(element);
        } else {
            addToSubtree(root, element);
        }

        size++;
    }

    @Override
    public Tree.Vertex<T> getRoot() {
        return root;
    }

    private void addToSubtree(Vertex<T> vertex, T element) {
        if (element.compareTo(vertex.getValue()) > 0) {
            if (vertex.getRightChild() != null) {
                addToSubtree(vertex.getRightChild(), element);
            } else {
                vertex.setRightChild(new Vertex<>(element));
            }
        } else {
            if (vertex.getLeftChild() != null) {
                addToSubtree(vertex.getLeftChild(), element);
            } else {
                vertex.setLeftChild(new Vertex<>(element));
            }
        }
    }

    /**
     * More efficient way via replacement element with the biggest one from the left subtree
     * */
    @Override
    public T removeElement(T element) {
        if(root != null) {
            return removeFromSubtree(root, element, null);
        }

        throw new IllegalStateException("There is no such element");
    }

    private T removeFromSubtree(Vertex<T> vertex, T element, Vertex<T> parent) {
        T value = vertex.getValue();

        if(value.equals(element)) {
            return removeVertex(vertex, parent);
        } else {
            Vertex<T> curElement = vertex;

            if(element.compareTo(value) > 0) {
                if(curElement.getRightChild() == null) {
                    throw new IllegalStateException("There is no such element");
                }

                return removeFromSubtree(curElement.getRightChild(), element, curElement);
            } else {
                if(curElement.getLeftChild() == null) {
                    throw new IllegalStateException("There is no such element");
                }

                return removeFromSubtree(curElement.getLeftChild(), element, curElement);
            }
        }
    }

    private T removeVertex(Vertex<T> vertex, Vertex<T> parent) {
        T value = vertex.getValue();

        Vertex<T> rightChild = vertex.getRightChild();
        Vertex<T> leftChild = vertex.getLeftChild();

        if(parent == null) {
            return removeRoot().getValue();
        } else {
            if(vertex.getLeftChild() == null && vertex.getRightChild() == null) {
                doRemoveVertex(vertex, parent);
            } else if(vertex.getLeftChild() != null) {
                Vertex<T> maxVertexInLeftSubtree = getMaxVertex(vertex.getLeftChild());

                vertex.setValue(maxVertexInLeftSubtree.getValue());
                removeFromSubtree(vertex.getLeftChild(), vertex.getValue(), vertex);
            } else if(vertex.getRightChild() != null) {
                replaceVertex(parent, vertex, vertex.getRightChild());
            }
        }

        return value;
    }

    private void replaceVertex(Vertex<T> parent, Vertex<T> whichVertex, Vertex<T> to) {
        if(parent.getRightChild() == whichVertex) {
            parent.setRightChild(to);
        } else if (parent.getLeftChild() == whichVertex) {
            parent.setLeftChild(to);
        }
    }

    protected void doRemoveVertex(Vertex<T> vertex, Vertex<T> parent) {
        if(parent.getRightChild() == vertex) {
            parent.setRightChild(null);
        } else {
            parent.setLeftChild(null);
        }
    }

    private Vertex<T> removeRoot() {
        Vertex<T> oldRoot = root;

        if(root == null) {
            return null;
        }

        if(root.getRightChild() != null) {
            Vertex<T> minVertex = getMinVertex(root.getRightChild());

            minVertex.setLeftChild(root.getLeftChild());

            this.root = root.getRightChild();
        } else if(root.getLeftChild() != null) {
            this.root = root.getLeftChild();
        } else {
            this.root = null;
        }

        return oldRoot;
    }

    Vertex<T> getMinVertex(Vertex<T> vertex) {
        if(vertex.getLeftChild() != null) {
            return getMinVertex(vertex.getLeftChild());
        } else {
            return vertex;
        }
    }

    Vertex<T> getMaxVertex(Vertex<T> vertex) {
        if(vertex.getRightChild() != null) {
            return getMaxVertex(vertex.getRightChild());
        } else {
            return vertex;
        }
    }

    @Override
    public boolean searchElement(T element) {
        if(root == null) {
            return false;
        }

        return searchElementInternal(root, element);
    }

    private boolean searchElementInternal(Vertex<T> vertex, T element) {
        T vertexValue = vertex.getValue();
        if(vertexValue.compareTo(element) == 0) {
            return true;
        } else if(vertexValue.compareTo(element) > 0) {
            Vertex<T> rightChild = vertex.getRightChild();
            if(rightChild != null) {
                return searchElementInternal(rightChild, element);
            } else {
                return false;
            }
        } else {
            Vertex<T> leftChild = vertex.getLeftChild();
            if(leftChild != null) {
                return searchElementInternal(leftChild, element);
            } else {
                return false;
            }
        }
    }

    @Override
    public int getDepth() {
        if(root == null) {
            return 0;
        }

        return getDepthInternal(root, 0);
    }

    private int getDepthInternal(Vertex<T> vertex, int depth) {
        depth++;

        if(hasNoChildren(vertex)) {
            return depth;
        } else {
            return Integer.max(getDepthInternal(vertex.getLeftChild(), depth), getDepthInternal(vertex.getRightChild(), depth));
        }
    }

    private boolean hasNoChildren(Vertex<T> vertex) {
        return vertex.getRightChild() == null && vertex.getLeftChild() == null;
    }

    public static BinarySearchTree createBinaryTreeWithPreOrderTraversal() {
        return new BinarySearchTree(new PreOrderTraversalStrategy());
    }

    public static BinarySearchTree createBinaryTreeWithInOrderTraversal() {
        return new BinarySearchTree(new InOrderTreeTraversalStrategy());
    }

    public void setTreeTraversalStrategy(TreeTraversalStrategy<T> treeTraversalStrategy) {
        this.treeTraversalStrategy = treeTraversalStrategy;
    }

    static class Vertex<T extends Comparable> implements Tree.Vertex<T>{
        private T value;

        private Vertex<T> leftChild;
        private Vertex<T> rightChild;

        public Vertex(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Vertex<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Vertex<T> leftChild) {
            this.leftChild = leftChild;
        }

        public Vertex<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Vertex<T> rightChild) {
            this.rightChild = rightChild;
        }
    }
}
