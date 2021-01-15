package no.uib.info233v18.oblig4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K, V> {
    private int size;
    private Node first;
    private Comparator<K> comparator;

    SortedTreeMap(Comparator<K> comparator) {
        size = 0;
        this.comparator = comparator;
        first = null;
    }

    /**
     * Finds the minimum value in the map, if no value is found, returns null instead.
     *
     * @return minimum value
     */
    @Override
    public Entry<K, V> min() {
        Entry<K, V> result = null;
        if (first != null) {
            Node node = first;
            while (node.hasLeftChild()) {
                node = node.leftChild;
            }
            result = node.data;
        }
        return result;
    }

    /**
     * Finds the maximum value in the map, if no value is found returns null instead.
     *
     * @return maximum value
     */
    @Override
    public Entry<K, V> max() {
        Entry<K, V> result = null;
        if (first != null) {
            Node node = first;
            while (node.hasRightChild()) {
                node = node.rightChild;
            }
            result = node.data;
        }
        return result;
    }

    /**
     * Inserts the specified value with the specified key as a new entry into the map.
     * If the value is already present, return the previous value, else null.
     *
     * @param key   The key to be inserted
     * @param value The value to be inserted
     * @return Previous value
     */
    @Override
    public V add(K key, V value) {
        return add(new Entry<>(key, value));
    }

    /**
     * Inserts the specified entry into the map. If the key is already a part of the map,
     * return the previous value, else null.
     *
     * @param entry The new entry to be inserted into the map
     * @return Previous value
     */
    @Override
    public V add(Entry<K, V> entry) {
        V result = null;
        if (isEmpty()) {
            first = new Node(entry);
        } else {
            result = addEntry(entry, first);
        }
        if (!isBalanced()) {
            first = balance(first);
        }
        updateSize(first);
        return result;
    }

    private V addEntry(Entry<K, V> entry, Node root) {
        V result = null;
        if (entry != null && root != null) {
            if (comparator.compare(entry.key, root.data.key) == 0) {
                result = root.data.value;
                root.data = entry;
            } else if (comparator.compare(entry.key, root.data.key) < 0) {
                if (root.hasLeftChild()) {
                    result = addEntry(entry, root.leftChild);
                } else {
                    root.leftChild = new Node(entry);
                    root.leftChild.parent = root;
                }
            } else if (comparator.compare(entry.key, root.data.key) > 0) {
                if (root.hasRightChild()) {
                    result = addEntry(entry, root.rightChild);
                } else {
                    root.rightChild = new Node(entry);
                    root.rightChild.parent = root;
                }
            }
        }
        return result;
    }

    /**
     * Replaces the value for key in the map as long as it is already present. If they key
     * is not present, the method throws an exception.
     *
     * @param key   The key for which the value is replaced
     * @param value The new value
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public void replace(K key, V value) throws NoSuchElementException {
        if (!containsKey(key)) {
            throw new NoSuchElementException("No entry with that key in TreeMap");
        } else {
            replace(first, new Entry<>(key, value));
        }

    }

    private void replace(Node root, Entry<K, V> entry) {
        if (root != null) {
            if (comparator.compare(root.data.key, entry.key) == 0) {
                root.data = entry;
            } else {
                replace(root.leftChild, entry);
                replace(root.rightChild, entry);
            }
        }
    }

    /**
     * Applies a function to the value at key and replaces that value. Throws an exception
     * if the key is not present in the map.
     *
     * @param key The key for which we are replacing the value
     * @param f   The function to apply to the value
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {
        if (!containsKey(key)) {
            throw new NoSuchElementException("No entry with that key in TreeMap");
        } else {
            replace(first, key, f);
        }
    }

    private void replace(Node root, K key, BiFunction<K, V, V> f) {
        if (root != null) {
            if (comparator.compare(root.data.key, key) == 0) {
                V value = f.apply(root.data.key, root.data.value);
                replace(key, value);
            } else {
                replace(root.leftChild, key, f);
                replace(root.rightChild, key, f);
            }
        }
    }

    /**
     * Removes the entry for key in the map. Throws an exception if the key is not present
     * in the map.
     *
     * @param key The key for the entry to remove
     * @return The removed value
     * @throws NoSuchElementException When key is not in map.
     */
    @Override
    public V remove(Object key) throws NoSuchElementException {
        V result = null;
        if (containsKey((K) key)) {
            result = removeEntry(first, key);
            if (!isBalanced()) {
                first = balance(first);
            }
            size--;
        } else if (!containsKey((K) key)){
            throw new NoSuchElementException("No entry with that key in TreeMap");
        }
        return result;
    }

    private V removeEntry(Node root, Object key) {
        V result = null;
        if (root != null) {
            if (comparator.compare((K) key, root.data.key) == 0) {
                result = root.data.value;
                delete(root);
                return result;
            } else if (comparator.compare((K) key, root.data.key) < 0) {
                result = removeEntry(root.leftChild, key);
            } else {
                result = removeEntry(root.rightChild, key);
            }
        }
        return result;
    }

    private void delete(Node n) {
        if (n.hasChildren()) {
            Node tempNode = n.rightChild;
            n.replaceWith(n.leftChild);
            addChildren(tempNode);
        } else if (n.hasRightChild() && !n.hasLeftChild()) {
            n.replaceWith(n.rightChild);
        } else if (n.hasLeftChild()) {
            n.replaceWith(n.leftChild);
        } else {
            n.data = null;
        }
    }

    private void addChildren(Node root) {
        if (root != null && root.data != null) {
            addEntry(root.data, first);
            if (root.hasRightChild()) {
                addChildren(root.rightChild);
            }
            if (root.hasLeftChild()) {
                addChildren(root.leftChild);
            }
        }
    }

    /**
     * Retrieves the value for the key in the map.
     *
     * @param key The key for the value to retrieve
     * @return The value for the key
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public V getValue(Object key) throws NoSuchElementException {
        if (!containsKey((K) key)) {
            throw new NoSuchElementException("No entry with that key in TreeMap");
        }
        Node node = first;
        while (true) {
            int cmp = comparator.compare((K)key, node.data.key);
            if (cmp < 0) {
                node = node.leftChild;
            } else if (cmp > 0) {
                node = node.rightChild;
            } else {
                return node.data.value;
            }
        }
    }

    /**
     * Checks if a key is in the map.
     *
     * @param key The key to check
     * @return true if the key is in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        boolean result = false;
        if (!isEmpty()) {
            result = search(first, key);
        }
        return result;
    }

    private boolean search(Node root, K key) {
        if (root == null) {
            return false;
        } else if (comparator.compare(key, root.data.key) == 0) {
            return true;
        } else if (comparator.compare(key, root.data.key) < 0) {
            return search(root.leftChild, key);
        } else {
            return search(root.rightChild, key);
        }
    }

    /**
     * Checks if a value is in the map
     *
     * @param value the value to look for
     * @return True if the value is present, false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        boolean result = false;
        if (!isEmpty()) {
            result = search(first, value);
        }
        return result;
    }

    private boolean search(Node root, V value) {
        boolean result = false;
        if (root != null) {
            if (value.equals(root.data.value)) {
                result = true;
            } else {
                boolean rightRes = search(root.rightChild, value);
                boolean leftRes = search(root.leftChild, value);
                if (rightRes) {
                    result = true;
                } else if (leftRes) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Finds all the keys in the map and returns them in order.
     *
     * @return keys in order
     */
    @Override
    public Iterable<K> keys() {
        ArrayList<K> result = new ArrayList<>();
        Iterator<Entry<K, V>> iterator = new TreeMapIterator();
        if (!isEmpty()) {
            Entry<K, V> entry = iterator.next();
            while (entry != null) {
                result.add(entry.key);
                entry = iterator.next();
            }
        }
        return result;
    }

    /**
     * Finds the values in order of the keys.
     *
     * @return values in order of the keys
     */
    @Override
    public Iterable<V> values() {
        ArrayList<V> result = new ArrayList<>();
        Iterator<Entry<K, V>> iterator = new TreeMapIterator();
        if (!isEmpty()) {
            Entry<K, V> entry = iterator.next();
            while (entry != null) {
                result.add(entry.value);
                entry = iterator.next();
            }
        }
        return result;
    }

    /**
     * Finds all entries in the map in order of the keys.
     *
     * @return All entries in order of the keys
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        ArrayList<Entry<K, V>> result = new ArrayList<>();
        Iterator<Entry<K, V>> iterator = new TreeMapIterator();
        if (!isEmpty()) {
            Entry<K, V> entry = iterator.next();
            while (entry != null) {
                result.add(entry);
                entry = iterator.next();
            }
        }
        return result;
    }

    /**
     * Finds the entry for the key, if the key is not in the map returns the leftChild
     * highest entry if such an entry exists
     *
     * @param key The key to find
     * @return The entry for the key or the leftChild highest
     */
    @Override
    public Entry<K, V> higherOrEqualEntry(K key) {
        if (containsKey(key)) {
            Iterable<Entry<K, V>> entries = entries();
            for (Entry<K, V> next : entries) {
                if (comparator.compare(next.key, key) == 0) {
                    return next;
                }
            }
        } else {
            Iterable<Entry<K, V>> entries = entries();
            for (Entry<K, V> next : entries) {
                if (comparator.compare(next.key, key) > 0) {
                    return next;
                }
            }
        }
        return null;
    }

    /**
     * Finds the entry for the key, if the key is not in the map, returns the leftChild
     * lower entry if such an entry exists
     *
     * @param key The key to find
     * @return The entry for the key or the leftChild lower
     */
    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        Entry<K, V> result = null;
        if (containsKey(key)) {
            Iterable<Entry<K, V>> entries = entries();
            for (Entry<K, V> next : entries) {
                if (comparator.compare(next.key, key) == 0) {
                    result = next;
                }
            }
        } else {
            Iterable<Entry<K, V>> entries = entries();
            for (Entry<K, V> next : entries) {
                if (comparator.compare(next.key, key) < 0) {
                    result = next;
                }
            }
        }
        return result;
    }

    /**
     * Adds all entries in the other map into the current map. If a key is present
     * in both maps, the key in the other map takes precedent.
     *
     * @param other The map to add to the current map.
     */
    @Override
    public void merge(ISortedTreeMap<K, V> other) {
        Iterable<Entry<K, V>> iterable = other.entries();
        for (Entry<K, V> e : iterable) {
            add(e);
        }
    }

    /**
     * Removes any entry for which the predicate holds true. The predicate can
     * trigger on both the key and value of each entry.
     *
     * @param p The predicate that tests which entries should be kept.
     */
    @Override
    public void removeIf(BiPredicate<K, V> p) {
        if (!isEmpty()) {
            Iterable<Entry<K, V>> entries = entries();
            clear();
            for (Entry<K, V> e : entries) {
                if (!p.test(e.key, e.value)) {
                    add(e);
                }
            }
            if (!isBalanced()) {
                first = balance(first);
            }
        }
    }

    /**
     * Checks if the map is empty
     *
     * @return True if the map is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0 && first == null;
    }

    /**
     * Returns the number of entries in the map
     *
     * @return Number of entries
     */
    @Override
    public int size() {
        return size;
    }

    private int updateSize(Node root){
        int result = 0;
        if (root != null) {
            result = updateSize(root.leftChild) + updateSize(root.rightChild) + 1;
        }
        size = result;
        return result;
    }

    /**
     * Clears the map of entries.
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    private Node balance(Node root) {
        if (isBalanced()) {
            return root;
        } else if (root != null) {
            if (getMaxDepth(root.leftChild) > (getMaxDepth(root.rightChild) + 1)) {
                if (getMaxDepth(root.leftChild.leftChild) > (getMaxDepth(root.leftChild.rightChild) + 1)) {
                    Node right = rotateRight(root);
                    if (right != null) {
                        root = right;
                    }
                } else {
                    Node leftRight = rotateLeftRight(root);
                    if (leftRight != null) {
                        root = leftRight;
                    }
                }
            } else if ((getMaxDepth(root.leftChild) + 1) < getMaxDepth(root.rightChild)) {
                if ((getMaxDepth(root.rightChild.leftChild) + 1) < getMaxDepth(root.rightChild.rightChild)) {
                    Node left = rotateLeft(root);
                    if (left != null) {
                        root = left;
                    }
                } else {
                    Node rightLeft = rotateRightLeft(root);
                    if (rightLeft != null) {
                        root = rightLeft;
                    }
                }
            }
        }
        return root;
    }

    private boolean isBalanced() {
        return first == null || first.getBalance() == 0;
    }

    private int getMaxDepth(Node node) {
        if (node == null)
            return 0;

        return Math.max(getMaxDepth(node.leftChild), getMaxDepth(node.rightChild)) + 1;
    }

    private Node rotateRight(Node root) {
        Node result  = null;
        if (root != null) {
            if (root.hasLeftChild()) {
                result = root.leftChild;
                if (result.hasRightChild()) {
                    root.leftChild = result.rightChild;
                } else {
                    root.leftChild = null;
                }
                result.rightChild = root;
            }
        }
        return result;
    }

    private Node rotateLeft(Node root) {
        Node result = null;
        if (root != null) {
            if (root.hasRightChild()) {
                result = root.rightChild;
                if (result.hasLeftChild()) {
                    root.rightChild = result.leftChild;
                } else {
                    root.rightChild = null;
                }
                result.leftChild = root;
            }
        }
        return result;
    }

    private Node rotateRightLeft(Node root) {
        if (root != null) {
            if (root.hasRightChild()) {
                Node rightChild = root.rightChild;
                root.rightChild = rotateRight(rightChild);
            }
        }
        return rotateLeft(root);
    }

    private Node rotateLeftRight(Node root) {
        if (root != null) {
            if (root.hasLeftChild()) {
                Node leftChild = root.leftChild;
                root.leftChild = rotateLeft(leftChild);
            }
        }
        return rotateRight(root);
    }

    private class TreeMapIterator implements Iterator<Entry<K, V>> {
        private Node next;

        TreeMapIterator() {
            next = first;
            if(next == null)
                return;

            while (next.leftChild != null)
                next = next.leftChild;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than null.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration, or null if there is no next element
         */
        @Override
        public Entry<K, V> next() {
            if(!hasNext()) {
                return null;
            }
            Node r = next;

            if(next.rightChild != null) {
                next = next.rightChild;
                while (next.leftChild != null) {
                    next = next.leftChild;
                }
                return r.data;
            }

            while(true) {
                if(next.parent == null) {
                    next = null;
                    return r.data;
                }
                if(next.parent.leftChild == next) {
                    next = next.parent;
                    return r.data;
                }
                next = next.parent;
            }

        }
    }

    private class Node {
        private Entry<K, V> data;
        private Node leftChild, rightChild, parent;

        private Node(Entry<K, V> elem) {
            data = elem;
            leftChild = null;
            rightChild = null;
            parent = null;
        }


        private void replaceWith(Node node) {
            if (node != null) {
                data = node.data;
                leftChild = node.leftChild;
                rightChild = node.rightChild;
                if (node.hasRightChild()) {
                    node.rightChild.parent = this;
                }
                if (node.hasLeftChild()) {
                    node.leftChild.parent = this;
                }
            }
        }

        private int getBalance() {
            int result;
            int left = 0;
            int right = 0;
            if (hasLeftChild()) {
                left = leftBalance();
            }
            if (hasRightChild()) {
                right = rightBalance();
            }
            result = left - right;
            return result;
        }

        private int leftBalance() {
            int result = 0;
            if (leftChild.hasLeftChild() || leftChild.hasRightChild()) {
                result = leftChild.getBalance();
            }
            return result;
        }

        private int rightBalance() {
            int result = 0;
            if (rightChild.hasLeftChild() || rightChild.hasRightChild()) {
                result = rightChild.getBalance();
            }
            return result;
        }

        private boolean hasChildren() {
            return hasLeftChild() && hasRightChild();
        }

        private boolean hasLeftChild() {
            return leftChild != null;
        }

        private boolean hasRightChild() {
            return rightChild != null;
        }
    }
}
