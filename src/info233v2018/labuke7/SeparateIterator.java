package info233v2018.labuke7;

import java.util.NoSuchElementException;

public class SeparateIterator<T> implements Iterator<T> {
    private ListInterface<T> list;
    private int nextPosition;
    private boolean wasNextCalled;

    public SeparateIterator(ListInterface<T> myList) {
        list = myList;
        nextPosition = 0;
        wasNextCalled = false;
    }

    @Override
    public boolean hasNext() {
        return nextPosition < list.getLength();
    }

    @Override
    public T next() {
        if (hasNext()) {
            wasNextCalled = true;
            nextPosition++;
            return list.getEntry(nextPosition);
        } else {
            throw new NoSuchElementException("");
        }
    }

    @Override
    public void remove() {
        if (wasNextCalled) {
            list.remove(nextPosition);
            nextPosition--;
        } else
            throw new IllegalStateException("");
    }
}
