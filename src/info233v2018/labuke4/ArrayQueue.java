package info233v2018.labuke4;

public final class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;

    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayQueue(int iniCap) {
        checkCapacity(iniCap);

        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[iniCap + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = iniCap;
        initialized = true;
    }

    @Override
    public void enqueue(T newEntry) {

    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public T getFront() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void checkInitialized() {
        assert initialized;
    }

    private void checkCapacity(int cap) {
        assert cap <= MAX_CAPACITY;
    }
}
