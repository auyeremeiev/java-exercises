package com.andrejeru.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleHashMap<K,V> implements Map<K, V> {
    /**
     * Buckets by which elements are distributed
     *
     * */
    private BinNode[] bins;
    private int size = 0;
    private int capacity = 16;
    private double load_balancer = 0.75;
    private static final double THRESHOLD_IN_BUCKET = 0.75;

    public SimpleHashMap() {
        this.bins = new BinNode[capacity];
    }

    public SimpleHashMap(int capacity) {
        this.capacity = powerOfTwoFor(capacity);

    }

    public SimpleHashMap(int capacity, double load_balancer) {
        this.capacity = powerOfTwoFor(capacity);
        this.load_balancer = load_balancer;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Tests whether the map contains entry with specified key.
     *
     * Notice: as with get, the algorithms complexity is approximetely O(1)
     * */
    @Override
    public boolean containsKey(Object o) {
        boolean result = false;
        BinNode bin = findABin(o);

        if (findEntryByKey(bin, o) != null) {
            result = true;
        }

        return result;
    }

    private BinNode findABin(Object o) {
        int hash = hash(o);

        int index = size - 1 & hash;

        return bins[index];
    }


    /**
     * Tests whether the map contains entry with specified value.
     *
     * Notice: The algorithms complexity is approximately O(N) because of full search
     * */
    @Override
    public boolean containsValue(Object o) {
        for (BinNode bin : bins) {
            if (bin != null) {
                for (BinNode<K, V> curNode = bin; curNode.next != null; curNode = curNode.next)
                    if(curNode.value.equals(o))
                        return true;
            }
        }

        return false;
    }

    /**
     * Gets value by key.
     *
     * To put a new element in the map, following steps are performed:
     *
     *      1) Get the hash of the new element key. If key is null, 0 is assigned to hash.
     *      2) Make additional manipulation with hash. {@link #hash(int)}
     *      3) Get the index of a bin. {@link #hash(int)}
     *      4) Find the entry with the same key in a forward linked list with this index using {@link Object#equals(Object)} method.
     *
     *  Using this approach, the algorithm complexity is close to O(1) in case of well distributed hash across keys space.
     *
     *  Notice: if method returns null, it doesn't mean that there is no such element. Value can be null under key.
     *  Use {@link #containsKey(Object)} instead.
     *
     * */
    @Override
    public V get(Object o) {
        int keysHash = 0;

        if(o != null)
            keysHash = o.hashCode();

        int modifiedHash = hash(keysHash);
        int bucketIndex = getBucketIndex(modifiedHash);

        BinNode<K, V> entryByKey = findEntryByKey(bins[bucketIndex], o);

        if(entryByKey == null)
            return null;
        else
            return entryByKey.value;
    }

    /**
     * Puts a new element in hashmap.
     *
     * If total occupancy ratio exceeds threshold (16 elements), hashmap is rehashed {@link #rehash()}.
     *
     * To put a new element in the map, following steps are performed:
     *
     * 1) Get the hash of the new element key. If key is null, 0 is assigned to hash.
     * 2) Make additional manipulation with hash. {@link #hash(int)}
     * 3) Get the index of a bin. {@link #hash(int)}
     * 4) Find the entry with the same key in a forward linked list with this index using {@link Object#equals(Object)} method.
     * 5) If element is found, rewrite its value. If not, add it to the end of the linked list.
     * 6) Increments internal size value. If size exceeds threshold (it means number of elements inside the whole table,
     * not bins or elements in bins), rehash (originally resize()) method is called.
     *
     * To notice: the new entry instance is created inside. For the sake of speed its hash, modified by the {@link #hash(int)},
     * is set as an entry property {@link BinNode#hash(int)}. Key {@link BinNode#key} must be immutable,
     * because in case when somewhere key is modified, the hash in entry property {@link BinNode#hash(int) must be
     * also updated. When it doesn't happen, information can be broken.
     *
     * To notice 2: bucket must be power of 2. It is transformed to power of 2 in method {@link #powerOfTwoFor(int)}
     * in constructor.
     *
     * To notice 3: Value can be null
     *
     * In original hashmap in Java 8, when the linked list size exceeds additional threshold (by default 8), linked list
     * is transformed into red-black tree. This implementation doesn't support this feature for the sake of simplicity.
     *
     * @param k key by which element is added. If element with same key exists, rewrites it's value. Can be null.
     * @param v value of new element. Can be null
     *
     * @return value of new element
     *
     * @see #refresh()
     * */
    @Override
    public V put(K k, V v) {
        int keysHash = 0;

        if(k != null)
            keysHash = k.hashCode();

        int modifiedHash = hash(keysHash);
        int bucketIndex = getBucketIndex(modifiedHash);
        BinNode<K, V> newElement = new BinNode<>(modifiedHash, k, v, null);

        BinNode<K, V> binHead = bins[bucketIndex];
        if(binHead == null)
            // if bin is empty
            bins[bucketIndex] = newElement;
        else {
            BinNode<K, V> entryByKey = findEntryByKey(binHead, newElement.key);

            if(entryByKey == null) {
                // If element wasn't found by key, add to beginning of a bin
                newElement.next = binHead;
                bins[bucketIndex] = newElement;
            }
            else entryByKey.value = newElement.value;
        }

        size++;

        if(size > capacity * load_balancer)
            rehash();

        return newElement.value;
    }

    /**
     * Finds element in specified bin. If there is no such element returns null.
     * */
    private BinNode<K, V> findEntryByKey(BinNode bin, Object key) {
        if(bin == null)
            return null;

        BinNode<K,V> currentNode = bin;
        while (currentNode.next != null) {
            if(currentNode.getKey().equals(key))
                return currentNode;

            currentNode = currentNode.next;
        }

        return null;
    }

    private int getBucketIndex(int hash) {
        // define bucket by hash. Using bit mask of length = capacity - 1. The optimized way to make mod
        return hash & capacity - 1;
    }

    final public static int hash(Object o) {
        if(o == null) {
            return 0;
        } else {
            return hash(o.hashCode());
        }
    }

    final public static int hash(int hash) {
        return hash ^ (hash >>> 16);
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int powerOfTwoFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    /**
     * Reconstructs hashmap. Extends hashmap and reallocates ellements between bins. Doubles it's size
     *
     *
     * It's not like an original map. It simply reputs all elements. In the original hashmap:
     * power-of-two expansion are used , the elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     *
     * To notice: never shrinks hashmap. So calling remove and clean methods never calls rehashing
     *
     * In original hashmap mostly it just uses old threshold and old capacity and just multiply their values by 2.
     * If there is no such values it will use default ones : 16 - for capacity and 16 * 0.75 for the threshold.
     * Of course this values can be specified in constructor. By the capacity, number of bins is always meant to be power of 2.
     * Just on the map creation it already sets the capacity which is the power of 2 to the the specified capacity
     * in constructor and then multiplies it each time.
     *
     * Recreates all bins array and extends it length twice.
     *
     *
     * */
    private void rehash() {
        Map<K,V> oldMap = this;

        capacity <<= 1;

        clear();

        for (Map.Entry<K,V> entry : oldMap.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes element by key.
     *
     * To notice: returns null, if no element with such key was found. Key can be null. In this case 0 will be assigned ti hash
     *
     * To notice 2: not calls rehash to shrink the map. It's expansive for memory to use this collection when you need
     * to rapidly remove many elements.
     *
     * @return values of an element, which was deleted
     * */
    @Override
    public V remove(Object k) {
        int hashCode = 0;

        if(k != null)
            hashCode = k.hashCode();

        int binIndex = getBucketIndex(hash(hashCode));
        BinNode<K, V> binHead = bins[binIndex];

        BinNode<K, V> removedEntry = removeByKey(binHead, k);

        if(removedEntry == null)
            return null;
        else {
            size--;
            return removedEntry.value;
        }
    }

    /**
     * Removes element from bin by key.
     *
     * @return Element which was removed. Null if  nothing was found.
     * */
    private BinNode<K, V> removeByKey(BinNode<K, V> binHead, Object k) {

        BinNode<K,V> current = binHead;

        while(current.next != null) {
            if(current.next.key.equals(k)) {
                BinNode<K, V> elementToRemove = current.next;

                current.next = elementToRemove.next;
                return elementToRemove;
            }

            current = current.next;
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {
        capacity = 16;
        bins = new BinNode[capacity];
        size = 0;
    }

    // TODO
    @Override
    public Set<K> keySet() {
        return null;
    }

    // TODO
    @Override
    public Collection<V> values() {
        return null;
    }

    // TODO
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }


    /**
     * ===========================
     * Methods for forward nodes manipulation
     * ===========================
     * */
    /**
     * Class specially built for bin nodes.
     *
     * */
    protected static class BinNode<K,V> implements Entry<K, V> {
        private final K key;
        private V value;

        /**
         * Calculated and compressed hash for concrete this hashmap
         * */
        final int hash;

        BinNode<K,V> next;

        /**
         * @param hash calculated and compressed hash for concrete this hashmap.
         * @param k key of a node. Can be null.
         * @param v value of a node. Can be null.
         * @param next next element in chain. Can be null. If null, this element must be tail.
         * */
        BinNode(int hash, K k, V v, BinNode next) {
            this.hash = hash;
            this.key = k;
            this.value = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }


}
