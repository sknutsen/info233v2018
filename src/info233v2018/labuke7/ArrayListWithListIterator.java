package info233v2018.labuke7;

import java.util.NoSuchElementException;

public class ArrayListWithListIterator<T> implements ListWithIteratorInterface<T> {
    private T[] list;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    private enum Move {NEXT, PREVIOUS};

    public ArrayListWithListIterator() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListWithListIterator(int cap) {
        if (cap < DEFAULT_CAPACITY) {
            cap = DEFAULT_CAPACITY;
        } else {
            checkCapacity(cap);

            @SuppressWarnings("unchecked")
            T[] tempList = (T[]) new Object[cap + 1];
            list = tempList;
            numberOfEntries = 0;
            initialized = true;
        }
    }

    private void checkCapacity(int cap) {
        if (cap >= MAX_CAPACITY)
            throw new IllegalStateException("");
    }

    @Override
    public ListIterator<T> getIterator() {
        return new ListIteratorForArrayList();
    }

    @Override
    public Iterator<T> iterator() {
        return getIterator();
    }

    @Override
    public void add(Object newEntry) {

    }

    @Override
    public void add(int newPosition, Object newEntry) {

    }

    @Override
    public Object remove(int givenPosition) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object replace(int givenPosition, Object newEntry) {
        return null;
    }

    @Override
    public Object getEntry(int givenPosition) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean contains(Object anEntry) {
        return false;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private class ListIteratorForArrayList implements ListIterator<T> {

        private int nextIndex;
        private boolean isRemoveOrSetLegal;
        private Move lastMove;

        private ListIteratorForArrayList() {
            nextIndex = 1;
            isRemoveOrSetLegal = false;
            lastMove = null;
        }

        @Override
        public boolean hasNext() {
            return nextIndex <= numberOfEntries;
        }

        @Override
        public T next() {
            if (hasNext()) {
                lastMove = Move.NEXT;
                isRemoveOrSetLegal = true;

                T nextEntry = list[nextIndex];
                nextIndex++;

                return nextEntry;
            } else
                throw new NoSuchElementException("");
        }

        @Override
        public void remove() {
            isRemoveOrSetLegal = false;

            if (lastMove.equals(Move.NEXT)){
                ArrayListWithListIterator.this.remove(nextIndex - 1);
                nextIndex--;
            }
        }

        @Override
        public boolean hasPrevious() {
            return (nextIndex > 1) && (nextIndex <= numberOfEntries + 1);
        }

        @Override
        public T previous() {
            if (hasPrevious()) {
                lastMove = Move.PREVIOUS;
                isRemoveOrSetLegal = true;

                T previousEntry = list[nextIndex - 1];
                nextIndex--;

                return previousEntry;
            } else
                throw new NoSuchElementException("");
        }

        @Override
        public int nextIndex() {
            int result;

            if (hasNext())
                result = nextIndex - 1;
            else
                result = numberOfEntries;

            return result;
        }

        @Override
        public int previousIndex() {
            int result;

            if (hasPrevious())
                result = nextIndex - 2;
            else
                result = -1;

            return result;
        }

        @Override
        public void add(T newEntry) {
            isRemoveOrSetLegal = false;

            ArrayListWithListIterator.this.add(nextIndex, newEntry);
            nextIndex++;
        }

        @Override
        public void set(T newEntry) {

        }
    }
}
