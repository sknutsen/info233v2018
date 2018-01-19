package info233v2018.labuke3;

public final class LinkedBag<T> implements BagInterface<T> {
    private boolean initialized = false;
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(Object newEntry) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getFrequencyOf(T o) {
        return 0;
    }

    @Override
    public boolean contains(T o) {
        return false;
    }

    @Override
    public T[] toArray() {
        return null;
    }

    public boolean getInitialized(){
        return initialized;
    }

    public void setInitialized(boolean init){
        this.initialized = init;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion){
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }
    }

}
