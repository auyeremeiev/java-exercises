package com.andrejeru.excercises.graphs;

public class RedBlackTree<T extends Comparable> extends BinarySearchTree<T> {


    private Vertex<T> root = null;

    @Override
    public Tree.Vertex<T> getRoot() {
        return root;
    }

    @Override
    public void addElement(T element) {
        if(root == null) {
            root = new Vertex<>(element, true);
        } else {
            addToSubtree(root, element);
        }
    }

    private void addToSubtree(Vertex<T> vertex, T element) {
        // Common BST search
        // Every comment is symmetrical
        if(element.compareTo(vertex.getValue()) > 0) {
            // If it is about to add  new child
            if(vertex.getRightChild() == null) {
                // Just append a new child as a
                appendAsRightChild(vertex, element);
                adjustColoring(vertex);
            } else {
                // Then search in a subtree
                addToSubtree(vertex.getRightChild(), element);
            }
        } else {
            if(vertex.getLeftChild() == null) {
                appendAsLeftChild(vertex, element);
                adjustColoring(vertex);
            } else  {
                addToSubtree(vertex.getLeftChild(), element);
            }
        }

        if(vertex == root) {
            vertex.setBlack(true);
        }
    }

    private void adjustColoring(Vertex<T> vertex) {
        // Root is always a black node. If element is root and parent is null
        // then red rule cannot be violated.
        // If it is violated than there is a parent element. Otherwise
        // it couldn't be violated
        if(violatesRedRule(vertex)) {
            // If it is red, then change the color of parent to red
            // and change parents children color to black. The black length
            // won't be focused in a subtree root node then and spreaded
            // across it's children by shifting "blackness" down by 1
            if(!siblingColorIsBlack(vertex)) {
                switchingColorsSolution(vertex);
                // Also need to check coloring after adding somewhere in subtree. Maybe it was violated by previous
                // operation in insertion recursion
            } else {
                rotationSolution(vertex);
            }
        }

        Vertex<T> parent = vertex.getParent();

        if(parent != null) {
            // Also need to check coloring after adding somewhere in subtree. Maybe it was violated by previous
            // operation in insertion recursion
            adjustColoring(parent);
        }
    }

    /**
     * Preconditions: vertex has a red color that's why it is called. It just violates the rule
     * and a sibling (so called nephew) has a black color (it is not a null node as well).
     *
     * In that case it is needed to make a rotation. It is not fresh child in that case has balanced number of black nodes
     * then it means that in another subtree should be the same number of black notes is in sibling of new node on the level below (because
     * current is red). Then sibling of new node subtree can be moved to another subtree without
     * afraid that it violates any rule. Because also it will
     * have the same black origin as before - root of vertex. It will just skip the red its parent and be assigned
     * exactly to the root of subtree. It will not change the black depth rule.
     * It allows us to make a restruction, by pushing vertex up. It still has to have black nodes in the left and right.
     * Then it is needed to change the color of new root node - vertex to black. The previos structure of blackness won't change in that case
     * we should only recolor red subtree root node to red to not make its subtree "overblack".
     *
     * Also if the relation "triangle", it should be prerotated.
     * */
    private void rotationSolution(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();

        rotate(vertex);

        if(parent == root) {
            root = vertex;
        }
    }

    private void toggleColors(Vertex<T> newRootNode, Vertex<T> newRightSubtreeNodeOfNewRootNode) {
        boolean newRootNodeIsBlack = newRootNode.isBlack();
        boolean newRightSubtreeNodeOfNewRootNodeIsBlack = newRightSubtreeNodeOfNewRootNode.isBlack();

        newRootNode.setBlack(!newRootNodeIsBlack);
        newRightSubtreeNodeOfNewRootNode.setBlack(!newRightSubtreeNodeOfNewRootNodeIsBlack);
    }

    private void rotate(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();
        // It is to define which rotation is necessary
        // If the child is left then right rotation
        Edge edge = whichChild(vertex, parent);

        // It is to define whether additional rotation is required before main one
        Vertex<T> violatingChild = findViolatingChild(vertex);
        Edge violatingChildEdge = whichChild(vertex, vertex);

        if(edge != violatingChildEdge) {
            doRotate(violatingChild);

            // after rotation vertex will be pushed down. They will rotate
            // and next rotation should be done against new root - former
            // violatin child
            vertex = violatingChild;
        }

        doRotate(vertex);

        Vertex<T> newRootNode = vertex;
        Vertex<T> newRightSubtreeNodeOfNewRootNode = parent;

        toggleColors(newRootNode, newRightSubtreeNodeOfNewRootNode);
    }

    // Actual atomic rotation
    private void doRotate(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();

        Edge edge = whichChild(vertex, parent);

        if(edge == Edge.LEFT) {
            rotateToRight(parent);
        } else {
            rotateToLeft(parent);
        }
    }

    private Vertex<T> findViolatingChild(Vertex<T> vertex) {
        Vertex<T> rightChild = vertex.getRightChild();
        Vertex<T> leftChild = vertex.getLeftChild();

        if(rightChild != null && !rightChild.isBlack()) {
            return rightChild;
        } else if(leftChild != null && !leftChild.isBlack()) {
            return leftChild;
        }

        return null;
    }

    /**
     * Precondition : vertex has a red color (that's why it is called. It just violates the rule
     * and a sibling (so called nephew) has also red color (it is not a null node as well).
     *
     * Then we can adjust red rule violation by changing parent node color to red and shift "darkness" down
     * to its children. By toggling their color to black.
     * */
    private void switchingColorsSolution(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();

        vertex.setBlack(true);

        Vertex<T> sibling = getSibling(vertex);
        sibling.setBlack(true);

        parent.setBlack(false);
    }

    private boolean siblingColorIsBlack(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();

        Vertex<T> sibling = getSibling(vertex);
        if(sibling == null) {
            return true;
        } else {
            return sibling.isBlack();
        }
    }

    private Vertex<T> getSibling(Vertex<T> vertex) {
        Vertex<T> parent = vertex.getParent();

        if(parent == null) {
            throw new IllegalStateException("There can be no nephew is parent is null");
        }

        Edge childSide = whichChild(vertex, parent);

        // Each even empty null child is black. It will anyway
        // cause the rotation solution.
        if(childSide == Edge.RIGHT) {
            return parent.getLeftChild();
        } else {
            return parent.getRightChild();
        }
    }

    protected Edge whichChild(Vertex<T> vertex, Vertex<T> parent) {
        if(parent.getRightChild() == vertex) {
            return Edge.RIGHT;
        } else {
            return Edge.LEFT;
        }
    }

    private boolean violatesRedRule(Vertex vertex) {
        if(!vertex.isBlack()) {
            Vertex rightChild = vertex.getRightChild();
            Vertex leftChild = vertex.getLeftChild();

            if(rightChild != null && !rightChild.isBlack()) {
                return true;
            } else if(leftChild != null && !leftChild.isBlack()) {
                return true;
            }
        }

        return false;
    }

    private void appendAsRightChild(Vertex vertex, T element) {
        Vertex newVertex = new Vertex(element);
        vertex.setRightChild(newVertex);

        newVertex.setParent(vertex);
    }

    private void appendAsLeftChild(Vertex vertex, T element) {
        Vertex newVertex = new Vertex(element);
        vertex.setLeftChild(newVertex);

        newVertex.setParent(vertex);
    }

    private void rotateToLeft(Vertex<T> vertex) {
        Vertex parent = vertex.getParent();

        Vertex rightChild = vertex.getRightChild();

        if(rightChild == null) {
            throw new IllegalStateException("Cannot rotate to left, when there is no right child");
        }

        if(rightChild.getLeftChild() != null) {
            vertex.setRightChild(rightChild.getLeftChild());
            rightChild.getLeftChild().setParent(vertex);
        } else {
            vertex.setRightChild(null);
        }


        rightChild.setLeftChild(vertex);

        if(parent != null) {
            replaceVertex(parent, vertex, rightChild);
        } else {
            rightChild.setParent(null);
        }

        vertex.setParent(rightChild);
    }

    private void rotateToRight(Vertex<T> vertex) {
        Vertex parent = vertex.getParent();

        Vertex leftChild = vertex.getLeftChild();

        if(leftChild == null) {
            throw new IllegalStateException("Cannot rotate to right, when there is no left child");
        }

        if(leftChild.getRightChild() != null) {
            vertex.setLeftChild(leftChild.getRightChild());
            leftChild.getRightChild().setParent(vertex);
        } else {
            vertex.setLeftChild(null);
        }

        leftChild.setRightChild(vertex);

        if(parent != null) {
            replaceVertex(parent, vertex, leftChild);
        } else {
            leftChild.setParent(null);
        }

        vertex.setParent(leftChild);
    }

    protected void replaceVertex(Vertex<T> parent, Vertex<T> whichVertex, Vertex<T> to) {
        if(parent.getRightChild() == whichVertex) {
            parent.setRightChild(to);
        } else if (parent.getLeftChild() == whichVertex) {
            parent.setLeftChild(to);
        }

        to.setParent(parent);
    }

    @Override
    public T removeElement(T element) {
        return super.removeElement(element);
    }

    static class Vertex<T extends Comparable> implements Tree.Vertex<T>{
        private boolean isBlack = false;
        private Vertex<T> leftChild;
        private Vertex<T> rightChild;

        private Vertex<T> parent;

        private T value;

        public Vertex(T value) {
            this.value = value;
        }

        public Vertex(T value, boolean isBlack) {
            this(value);
            this.isBlack = isBlack;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Vertex<T> getParent() {
            return parent;
        }

        public void setParent(Vertex<T> parent) {
            this.parent = parent;
        }

        public boolean isBlack() {
            return isBlack;
        }

        public void setBlack(boolean black) {
            isBlack = black;
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
