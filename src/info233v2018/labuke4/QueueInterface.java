package info233v2018.labuke4;

public interface QueueInterface<T> {
    public void enqueue(T newEntry);

    public T dequeue();

    public T getFront();

    public boolean isEmpty();

    public void clear();

    public void checkInitialized();
}
/**
 * 11.1
 *
 */