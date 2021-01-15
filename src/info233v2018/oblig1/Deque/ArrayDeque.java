package Deque;

import Exceptions.DequeEmptyException;
import Exceptions.DequeFullException;
import java.util.Arrays;

/**
 * Array based implementation Deque interface.
 *
 * @author Skn003
 */
public class ArrayDeque<E> implements IDeque<E> {

    //Fields
    private E[] deque;
    private int frontIndex;
    private int backIndex;
    private boolean initialized;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 100;
    private int capacity;
    private int size;

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayDeque(int cap) {
        checkCapacity(cap);

        @SuppressWarnings("unchecked")
        E[] tempDeque = (E[]) new Object[cap + 1];
        deque = tempDeque;
        frontIndex = 0;
        backIndex = cap;
        capacity = deque.length;
        initialized = true;
    }

    private void checkCapacity(int cap) {
        if (cap >= MAX_CAPACITY)
            throw new IllegalStateException("Attempt at creating a deque whose capacity exceeds the max of " +
                                            MAX_CAPACITY);
    }

    private void ensureCapacity() throws DequeFullException {
        if (frontIndex == ((backIndex + 2) % capacity)) {
            E[] oldDeque = deque;
            int oldSize = oldDeque.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize);
            @SuppressWarnings("unchecked")
                    E[] tempDeque = (E[]) new Object[newSize];
            deque = tempDeque;
            for (int i = 0; i < oldSize - 1; i++) {
                deque[i] = oldDeque[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
        } else if (capacity >= MAX_CAPACITY){
            throw new DequeFullException("Full deque");
        }
    }

    private void checkInitialization() {
        if(!initialized)
            throw new SecurityException("Deque is not initialized properly");
    }

    private void checkEmpty() throws DequeEmptyException {
        if (size() == 0){
            throw new DequeEmptyException("Deque is empty, cannot peek at first element");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E elem) throws DequeFullException {
        checkInitialization();
        ensureCapacity();

        frontIndex = (frontIndex - 1 + deque.length) % deque.length;
        deque[frontIndex] = elem;
        size++;
    }

    @Override
    public E pullFirst() throws DequeEmptyException {
        checkInitialization();
        checkEmpty();

        E result = deque[frontIndex];
        deque[frontIndex] = null;
        frontIndex = (frontIndex + 1) % deque.length;
        size--;

        return result;
    }

    @Override
    public E peekFirst() throws DequeEmptyException {
        checkInitialization();
        checkEmpty();

        return size() == 0 ? null : deque[frontIndex];
    }

    @Override
    public void addLast(E elem) throws DequeFullException {
        checkInitialization();
        ensureCapacity();

        backIndex = (backIndex + 1) % deque.length;
        deque[backIndex] = elem;
        size++;
    }

    @Override
    public E pullLast() throws DequeEmptyException {
        checkInitialization();
        checkEmpty();

        E result = deque[backIndex];
        deque[backIndex] = null;
        backIndex = (backIndex + deque.length) % deque.length;
        size--;

        return result;
    }

    @Override
    public E peekLast() throws DequeEmptyException {
        checkInitialization();
        checkEmpty();

        return size() == 0 ? null : deque[backIndex];
    }

    @Override
    public boolean contains(Object elem) {
        checkInitialization();

        for(Object e : deque) {
            if (elem.equals(e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        checkInitialization();

        @SuppressWarnings("unchecked")
        E[] cleanDeque = (E[]) new Object[capacity];

        deque = cleanDeque;
        frontIndex = 0;
        backIndex = capacity;
        size = 0;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        checkInitialization();

        @SuppressWarnings("unchecked")
        T[] result = (T[]) Arrays.copyOf(deque, capacity, a.getClass());
        return result;
    }
}
