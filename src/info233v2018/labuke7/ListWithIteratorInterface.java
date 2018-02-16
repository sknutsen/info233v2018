package info233v2018.labuke7;

public interface ListWithIteratorInterface<T> extends ListInterface, Iterable<T> {
    public Iterator<T> getIterator();
}
