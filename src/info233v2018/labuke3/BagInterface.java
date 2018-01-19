package info233v2018.labuke3;

public interface BagInterface<T> {
    int getCurrentSize();
    boolean isEmpty();
    boolean add(T newEntry);
    T remove();
    boolean remove(T t);
    void clear();
    int getFrequencyOf(T t);
    boolean contains(T t);
    T[] toArray();
}