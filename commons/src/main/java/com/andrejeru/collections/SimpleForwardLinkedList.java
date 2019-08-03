package com.andrejeru.collections;


import java.util.*;

/**
 * One directional linked list.
 *
 * TODO: tests
 * */
public class SimpleForwardLinkedList <T> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public SimpleForwardLinkedList() {
        tail = head = null;
    }

    public SimpleForwardLinkedList(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();

        T element = iterator.next();
        if(iterator.hasNext()) {
            head = new Node<>(element);
            size++;
        }

        Node<T> curNode = head;

        while (iterator.hasNext()) {
            element = iterator.next();
            Node<T> newNode = new Node<>(element);

            curNode.setNext(newNode);
            curNode = newNode;
            size++;
        }

        tail = curNode;
    }

    /**
     * Adds element to the end of list.
     * */
    public void add(T node) {
        if(node == null)
            throw new IllegalArgumentException("node must not be null");

        Node<T> newNode = new Node<T>(node, null);

        if(isEmpty()) {
            head = newNode;
            tail = head;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    /**
     * Inserts element by the specified index. Shifts all elements by one index. If index = size, inserts element
     * to the end of the list.
     * */
    public void insert(T node, int index) {
        if(index == 0){
            addFirst(node);
        }
        else {
            Node<T> nodeBeforeIndex = getBefore(index);

            nodeBeforeIndex.next = new Node<T>(node, nodeBeforeIndex.next);

            size++;
        }
    }

    public void addFirst(T node) {
        Node<T> newNode = new Node<>(node, this.head);

        if(this.head == null)
            this.tail = newNode;

        this.head = newNode;

        size++;
    }

    public Node<T> getBefore(int index) {
        if(index >= size || index <= 0)
            throw new IndexOutOfBoundsException("index : " + index + " out of bounds. List size : " + size);

        Node<T> currentNode = this.head;
        for(int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    public Node<T> get(int index) {
        if(index >= size)
            throw new IndexOutOfBoundsException("index : " + index + " out of bounds. List size : " + size);

        Node<T> currentNode = this.head;
        for(int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    public int indexOf(T element) {
        if(element == null)
            throw new IllegalArgumentException("element must not be null");

        int result = -1;

        Node<T> currentNode = this.head;

        int i = 0;
        // Until the tail or the moment when specified element is found and resulting index of it was assigned
        while(currentNode.next != null) {
            if(currentNode.content.equals(element)) {
                return i;
            }

            currentNode = currentNode.next;
            i++;
        }

        return -1;
    }

    /**
     * Removes the first occurrence of element. If there is no such elements returns false. Returns true if list conatins
     * such element.
     * */
    public boolean remove(T element) {
        if (isEmpty())
            return false;

        Node<T> currentNode = this.head;
        Node<T> prevNode = null;

        // Looks up until the loop passes the tail
        // or the iteration when the specified element node is found.
        while (currentNode != null) {
            if (currentNode.content.equals(element)) {
                unlink(currentNode, prevNode);

                size--;
                return true;
            }

            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        return false;
    }

    private void unlink(Node<T> currentNode, Node<T> prevNode) {
        if (currentNode == null) {
            return;
        }
        else if (prevNode == null) {
            // case when the element is head
            this.head = this.head.next;

            if(this.head == null)
                this.tail = null;
        } else if (prevNode != null) {
            // case when the element isn't first and it is needed to rewrite references
            prevNode.next = currentNode.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public boolean hasDuplicates() {
        if(head == null)
            return false;

        if(!head.hasNext())
            return false;

        Node<T> curElement = head.next;
        while(curElement != null) {
            Node<T> curElementInsideUnique = head;
            while (!curElementInsideUnique.equals(curElement)) {
                if(curElementInsideUnique.content.equals(curElement.content))
                    return true;

                curElementInsideUnique = curElementInsideUnique.next;
            }

            curElement = curElement.next;
        }

        return false;
    }

    public T nthFromLast(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        Node<T> curTale = head;

        for(int i = 1; i < n; i++) {
            if(!curTale.hasNext()) {
                throw new IllegalArgumentException("n must be less tan size");
            }

            curTale = curTale.next;
        }

        Node<T> result = head;
        if(result == null)
            return null;

        while (curTale.hasNext()) {
            curTale = curTale.next;
            result = result.next;
        }

        return result.content;
    }

    /**
     * Cannot unlink the last element in this way
     * */
    public void unlink(Node<T> node) {
        /*
        * Impl: it just becomes this next element
        * */

        if(!node.hasNext())
            throw new IllegalArgumentException("Cannot unlink last element");

        node.content = node.next.content;
        node.next = node.next.next;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>(size);

        Iterator<T> iterator = iterator();
        iterator.forEachRemaining(e -> result.add(e));

        result.add(iterator.next());

        return result;
    }

    @SafeVarargs
    public static <T> SimpleForwardLinkedList fromList(T... elements) {
        if(elements == null) {
            return new SimpleForwardLinkedList<>();
        }

        return new SimpleForwardLinkedList(Arrays.asList(elements));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleForwardLinkedList<?> that = (SimpleForwardLinkedList<?>) o;
        return size == that.size &&
                Objects.equals(head, that.head) &&
                Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

    /**
     * Represents the node of one directional list. Contains only one reference - next element in list.
     *
     * It is cheaper to use it regarding memory because of one single reference for each node.
     * */
    public static class Node<T> {
        private T content;
        private Node<T> next;

        public Node(T content) {
            if(content == null) {
                throw new IllegalArgumentException("content must no be null");
            }

            this.content = content;
            this.next = null;
        }

        public Node(T content, Node<T> next) {
            if(content == null) {
                throw new IllegalArgumentException("content must no be null");
            }

            this.content = content;
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(content, node.content) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(content, next);
        }
    }


    private class ListIterator implements Iterator<T> {
        private Node<T> curElement = head;

        /**
         * Wrong implementation. Doesn't cover last element
         *
         * Mostly in all iterators there is size property and
         * this method checks wether the current index exceeds this size value. Then it will
         * cover all elements inside collection
         * */
        @Override
        public boolean hasNext() {
            if(head == null) {
                return false;
            }

            return curElement.hasNext();
        }

        @Override
        public T next() {
            if(curElement == null) {
                return null;
            }

            T content = curElement.content;
            curElement = curElement.next;
//            curElement.next = curElement.next.next;

            return content;
        }
    }
}
