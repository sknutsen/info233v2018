package info233v2018.labuke3;

public class ArrayBag<T> implements BagInterface<T> {

    //private final T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 0;

    public int getCurrentSize(){
        return 0;
    }
    public boolean isEmpty(){
        return false;
    }

    @Override
    public boolean add(T newEntry) {
        return false;
    }

    public T remove(){
        return null;
    }
    public boolean remove(T t){
        return false;
    }
    public void clear(){
        while(!isEmpty()){
            remove();
        }
        //checkInitialization();
        //bag = (T[]) new Object[bag.length];
        //numberOfEntries();
    }
    public int getFrequencyOf(T t){
        return 0;
    }
    public boolean contains(T t){
        return false;
    }
    public T[] toArray(){
        return null;
    }
    public boolean isArrayFull(){
        return false;
    }
    public T replace(T t){
        //checkInitialization();
        T result = remove();
        add(t);
        return result;
    }
}
