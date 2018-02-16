package info233v2018.labuke7;

public interface ListIterator<T> extends Iterator<T> {
    public boolean hasNext();

    public T next();

    public void remove();

    public boolean hasPrevious();

    public T previous();

    public int nextIndex();

    public int previousIndex();

    public void add(T newEntry);

    public void set(T newEntry);
}
