package com.andrejeru.sorts;

public interface Sorter<T extends Comparable> {
    T[] sort(T[] data);
}
