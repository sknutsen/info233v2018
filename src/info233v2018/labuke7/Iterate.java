package info233v2018.labuke7;

import java.util.NoSuchElementException;

public class Iterate<T> implements Iterator {

    private T nextNode;

    @Override
    public boolean hasNext() {
        return nextNode != null;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            T returnNode = nextNode;
            nextNode = nextNode;//getNextNode();
            return returnNode;//getData();
        } else
            throw new NoSuchElementException("");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("");
    }
}
