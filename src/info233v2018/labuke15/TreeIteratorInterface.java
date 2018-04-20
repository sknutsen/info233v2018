package info233v2018.labuke15;

import info233v2018.labuke7.Iterator;

public interface TreeIteratorInterface<T> {
    public Iterator<T> getPreorderIterator();
    public Iterator<T> getPostOrderIterator();
    public Iterator<T> getInorderIterator();
    public Iterator<T> getLevelOrderIterator();
}
