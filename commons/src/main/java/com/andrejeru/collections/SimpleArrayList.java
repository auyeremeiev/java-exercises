package com.andrejeru.collections;


import java.awt.*;
import java.util.ArrayList;

/**
 * TODO
 *
 * The simple cheat note for main concepts and ideas of ArrayList implementation
 * */
public class SimpleArrayList<E> extends ArrayList<E> {

    /**
     * Costructs an empty list with specified initial capacity
     *
     * To notice: if initialCapacity is less then 0 - throws exception
     * */
    public SimpleArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Construct empty list.
     *
     * To notice: there is even special constant for empty list. Expands it's size to DEFAULT_CAPACITY = 10,
     * when first element added. Initialization is HALF-LAZY.
     * */
    public SimpleArrayList() {
        super();
    }

    /**
     * Trims the capacity of the array to the list's current size. Used for optimization.
     *
     * To notice: (Important) Isn't called by the methods of ArraList. Must be called separtely.
     * */
    @Override
    public void trimToSize() {
        super.trimToSize();

        /*
        modCount++;
        if (size < elementData.length) {
            elementData = Arrays.copyOf(elementData, size);
        }
        * */
    }

    /**
     * An application can increase the capacity of an ArrayListinstance
     * before adding a large number of elements using the ensureCapacity
     * operation.  This may reduce the amount of incremental reallocation.
     * */
    @Override
    public void ensureCapacity(int minCapacity) {
        super.ensureCapacity(minCapacity);

        /*
        * To notice: If inner array is empty, minCapacity must be more than DEFAULT_CAPACITY = 10.
        * If it is not - nothing happens, else calls ensureExplicitCapacity(minCapacity) inside.
        * To insure it holds at least DEFAULT_CAPACITY.
        * */
    }

    /**
     * To notice: if specified capacity is more than current inner array length call grow() method.
     * Increments modCount
    */
    private void ensureCapacityInternal(int minCapacity) {
//        if (elementData == EMPTY_ELEMENTDATA) {
//            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
//        }
//
//        ensureExplicitCapacity(minCapacity);
    }

    /**
     * To notice: Expands array in 1.5 times but if minCapacity grater than oldCapacity * 1.5,
     * than the array will be expended to size = minCapacity.
     *
     * So array will be expanded minimum in 1.5 times
     * */
    private void grow(int minCapacity) {
//        // overflow-conscious code
//        int oldCapacity = elementData.length;
//        int newCapacity = oldCapacity + (oldCapacity >> 1);
//        if (newCapacity - minCapacity < 0)
//            newCapacity = minCapacity;
//        if (newCapacity - MAX_ARRAY_SIZE > 0)
//            newCapacity = hugeCapacity(minCapacity);
//        // minCapacity is usually close to size, so this is a win:
//        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * Impl: simply test the presence of object using indexOf(o) >= 0 check
     * */
    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    /**
     * To notice : param o can be null. In this case first null occurrence will be searched.
     *
     * @return -1 if element wasn't found, else returns an element index
     * */
    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);

        /*
        * if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
        * */
    }

    /**
     * Returns the element at the specified position in this list.
     *
     *  To notice : Checks if the given index is in range.  If not, throws an appropriate
     *  runtime exception.
     *
     * To notice: if index < size, throws out of boundaries exception
     * */
    @Override
    public E get(int index) {
        return super.get(index);
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     *  To notice : Checks if the given index is in range.  If not, throws an appropriate
     *  runtime exception.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc} if index < size
     */
    @Override
    public E set(int index, E element) {
        return super.set(index, element);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     */
    @Override
    public boolean add(E e) {
        return super.add(e);

        /*
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
        * */
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     * Can call grow() inside.
     *
     *  To notice : Checks if the given index is in range.  If not, throws an appropriate
     *  runtime exception.
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void add(int index, E element) {
        super.add(index, element);

        /*
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
        * */
    }


    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * To notice : doesn't reduce inner array's capacity automatically. trimToSize()
     * must be called for optimization
     * */
    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * To notice : doesn't reduce inner array's capacity automatically. trimToSize()
     * must be called for optimization
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    /**
     * To notice: dosn't clone elements themselves. So if some element is modified, it will be modified in
     * all clones created by this method.
     * */
    @Override
    public Object clone() {
        return super.clone();
    }
}
