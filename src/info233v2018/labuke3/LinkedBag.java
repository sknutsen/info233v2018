package info233v2018.labuke3;

public final class LinkedBag<T> implements BagInterface<T> {
    private boolean initialized = false;
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
        initialized = true;
    }

    @Override
    public int getCurrentSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if (getInitialized() == false){
            result = true;
        } else if (firstNode == null){
            result = true;
        }
        return result;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        firstNode = newNode;
        newNode.next = firstNode;
        numberOfEntries++;

        return true;
    }

    @Override
    public T remove() {
        T result = null;
        if (!isEmpty()) {
            if (firstNode != null) {
                result = firstNode.data;
                firstNode = firstNode.next;
                numberOfEntries--;
            }
        }
        return result;
    }

    private Node getReferenceTo(T entry){
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)){
            if (entry.equals(currentNode.data)){
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return currentNode;
    }

    @Override
    public boolean remove(T o) {
        boolean result = false;
        Node nodeN = getReferenceTo(o);
        if (nodeN != null) {
            nodeN.data = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            result = true;
        }
        return result;
    }

    @Override
    public void clear() {
        while(!isEmpty()){
            remove();
        }
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

    public T replace(T t){
        getInitialized();
        T result = remove();
        add(t);
        return result;
    }

    public T getMin(){
        Node result = null;
        if (firstNode.next != null){

        } else {
            result = firstNode;
        }
        return result.data;
    }

    public T getMin(Node node){
        Node result = null;
        return result.data;
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
    /**
     * exercise 3.2 a
     *  returns the contents of the bag as a line of text in the console
     *
     *  exercise 3.2 b
     *
     */
}
