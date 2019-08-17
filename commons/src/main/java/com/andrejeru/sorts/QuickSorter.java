package com.andrejeru.sorts;


/**
 * The short idea description of the algorithm is following:
 *
 * 1) We take some element in array
 * 2) We put it on such location when all elements on the right position are
 *      lower then the current and on the right - bigger.
 * 3) Of curse meanwhile we distribute all elements as in the second step (condition)
 *      * Actually now this element (pivot) on the real right place in sorted array.
 *       In sorted array all lower elements are also lefter then the current one and
 *       bigger ones righter. Order inside subarrays doesn't play role in the place for
 *       current pivot.
 * 4) Then we make recursive such algorithm to subarrays on the left side of pivor and the right one
 *
 * 5) So in the end we and up with arrays with 1 element which already fulfills 2 condition.
 * In that case do nothing.
 *
 * We sequentially partially sort array while we go downer and downer. in that case we can just apply
 * algorithm to smaller subarrays and smaller because we know that the subarray on the right side of pivot
 * will be also quarantined to be sorted based on the algorithm.
 *
 * If in merge sort, we firstly go down until the lowest subarrays and using the quarantied sorted results
 * of lower level of recursion we make "easier" actions to finishing sorting of some smaller subarrays, but here
 * we firstly do some actions and then go down, considering that some conditions are done on upper
 * level of recursion and we can not care about some things safely and also do some easier actions to make
 * then the arrays sorted.
 *
 * Complexity is approximetely O(n log n). In worst case it is O (n) but it is very unlikely situation. Most of
 * cases constant complexity here which is not considered in big O is smaller than in Merge sort. And mostly quick
 * sort is faster the merge sort.
 *
 * This sort is unstable.
 *
 * */
public class QuickSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public T[] sort(T[] data) {
        return null;
    }
}
