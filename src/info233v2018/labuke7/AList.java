package info233v2018.labuke7;

import java.util.Arrays;

public class AList<T> implements ListInterface<T> {

    private T[] list;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public AList() {
        this(DEFAULT_CAPACITY);
    }

    public AList(int cap) {
        if (cap < DEFAULT_CAPACITY) {
            cap = DEFAULT_CAPACITY;
        } else {
            checkCapacity(cap);
        }

        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[cap + 1];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;
    }

    @Override
    public void add(T newEntry) {
        checkInitialization();

        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
    }

    @Override
    public void add(int newPosition, T newEntry) {
        checkInitialization();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)){
            if (newPosition <= numberOfEntries)
                makeRoom(newPosition);
            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity();
        } else {
            throw new IndexOutOfBoundsException("");
        }
    }

    private void makeRoom(int newPosition) {
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        for (int i = lastIndex; i >= newIndex; i--) {
            list[i + 1] = list[i];
        }
    }

    private void checkInitialization() {
    }

    @Override
    public T remove(int givenPosition) {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T result = list[givenPosition];
            if (givenPosition < numberOfEntries)
                removeGap(givenPosition);
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("");
        }
    }

    private void removeGap(int givenPosition) {
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int i = removedIndex; i < lastIndex; i++)
            list[i] = list[i + 1];
    }

    @Override
    public void clear() {

    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        return null;
    }

    @Override
    public T getEntry(int givenPosition) {
        return null;
    }

    @Override
    public T[] toArray() {
        checkInitialization();

        T[] result = (T[]) new Object[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = list[i + 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public void checkCapacity(int cap) {
        cap = MAX_CAPACITY;
    }

    private void ensureCapacity() {
        int capacity = list.length - 1;
        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity);
            list = Arrays.copyOf(list, newCapacity + 1);
        }
    }
}
