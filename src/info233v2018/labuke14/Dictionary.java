package info233v2018.labuke14;

import info233v2018.labuke7.Iterator;

public class Dictionary<K, V> implements DictionaryInterface<K, V> {
    private int size;

    public V add(K key, V value) {
        return null;
    }

    public V remove(K key) {
        return null;
    }

    public V getValue(K key) {
        return null;
    }

    public boolean contains(K key) {
        return false;
    }

    public Iterator<K> getKeyIterator() {
        Iterator<K> keyIterator = new KeyIterator<K>();
        return keyIterator;
    }

    public Iterator<V> getValueIterator() {
        Iterator<V> keyIterator = new KeyIterator<V>();
        return keyIterator;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void clear() {

    }

    private class KeyIterator<E> implements Iterator<E>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }
}
