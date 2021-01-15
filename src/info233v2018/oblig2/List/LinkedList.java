package List;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<E> implements IList<E> {

    private Node firstNode;
    private int size;
    private boolean initialization;

    public LinkedList() {
        initialization = true;
        size = 0;
    }

    public LinkedList(E elem) {
        initialization = true;
        put(elem);
    }

    public LinkedList(E elem, IList<E> elems) {
        initialization = true;
        prepend(elems);
        put(elem);
    }

    private void checkInitialization() {
        if (!initialization)
            throw new IllegalStateException("Linked list has not been initialized.");
    }

    /**
     * ,* Returnerer alle elementene i listen bortsett fra det
     * ,* første.
     * ,*
     * ,* @return Resten av listen.
     * ,
     */
    @Override
    public IList<E> rest() {
        checkInitialization();

        if (firstNode.getNext() != null){
            Node node = firstNode.getNext();
            IList<E> rest = new LinkedList<>();
            while (node != null && node.getData() != null) {
                rest.add(node.getData());
                node = node.getNext();
            }

            return rest;
        } else {
            return null;
        }
    }

    /**
     * ,* Legger til et element på slutten av listen.
     * ,
     */
    @Override
    public boolean add(E elem) {
        checkInitialization();

        boolean result = false;
        if (elem != null) {
            if (firstNode == null) {
                firstNode = new Node(elem);
            } else {
                Node last = getNodeAt(size);
                last.createNext(elem);
            }
            size++;
            result = true;
        }

        return result;
    }

    /**
     * ,* Legger til et element på begynnelsen av listen.
     * ,
     */
    @Override
    public boolean put(E elem) {
        checkInitialization();

        boolean result = false;
        if (elem != null) {
            Node oldFirst = firstNode;
            firstNode = new Node(elem);
            firstNode.setNext(oldFirst);
            result = true;
            size++;
        }

        return result;
    }

    /**
     * ,* Gir det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E first() throws NoSuchElementException {
        checkInitialization();

        return firstNode.getData();
    }

    /**
     * ,* Returnerer alle elementene i listen b
     * /**
     * ,* Fjerner det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E remove() throws NoSuchElementException {
        checkInitialization();

        if (this.isEmpty()) {
            throw new NoSuchElementException("List empty");
        }

        E result = firstNode.getData();
        firstNode = firstNode.getNext();
        size--;

        return result;
    }

    /**
     * ,* Fjerner det angitte objektet fra listen.
     * ,*
     * ,* @param o Objektet som skal fjernes.
     * ,* @return true hvis et element ble fjernet, false
     * ,* ellers.
     */
    @Override
    public boolean remove(Object o) {
        this.checkInitialization();

        if (this.isEmpty()) {
            return false;
        }

        Node prev = null;
        Node current = this.firstNode;
        while (true) {
            if (o.equals(current.getData())) {
                if (current.equals(this.firstNode) && current.hasNext()) {
                    this.firstNode = firstNode.getNext();
                } else if (current.equals(this.firstNode)) {
                    this.firstNode = null;
                } else if (current.hasNext()) {
                    prev.setNext(current.getNext());
                } else {
                    prev.setNext(null);
                }

                size--;
                return true;
            }

            if (current.hasNext()) {
                prev = current;
                current = current.getNext();
            } else {
                break;
            }
        }

        return false;
    }

    /**
     * ,* Sjekker om et element er i listen.
     * ,*
     * ,* @param o objektet vi sjekker om er i listen.
     * ,* @return true hvis objektet er i listen, false ellers.
     */
    @Override
    public boolean contains(Object o) {
        this.checkInitialization();

        if(this.isEmpty()) {
            return false;
        }

        for (E elem : this) {
            if (o.equals(elem)) {
                return true;
            }
        }

        return false;
    }

    /**
     * ,* Sjekker om listen er tom.
     * ,*
     * ,* @return true hvis listen er tom, false ellers.
     * ,
     */
    @Override
    public boolean isEmpty() {
        this.checkInitialization();

        return this.size == 0;
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* slutten av listen.
     * ,*
     * ,* @param listen som blir lagt til.
     */
    @Override
    public void append(IList<? extends E> list) {
        this.checkInitialization();

        for(Object elem : list) {
            this.add((E) elem);
        }
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* begynnelsen av listen.
     * ,*
     * ,* @param list listen som blir lagt til
     */
    @Override
    public void prepend(IList<? extends E> list) {
        this.checkInitialization();

        if (!list.isEmpty()) {
            IList<E> tempList = new LinkedList<>();
            for (Object elem : list) {
                tempList.put((E) elem);
            }
            for (Object elem : tempList) {
                this.put((E) elem);
            }
        }
    }

    /**
     * ,* Slår sammen flere lister
     *
     * ,* @param lists listene som skal slås sammen
     * ,* @return Ny liste med alle elementene fra listene som
     * ,* skal slås sammen.
     */
    @Override
    public IList<E> concat(IList<? extends E>... lists) {
        this.checkInitialization();

        IList<E> returnList = new LinkedList<>();
        for(IList<? extends E> list : lists) {
            returnList.prepend(list);
        }

        return returnList;
    }

    /**
     * ,* Sorterer listen ved hjelp av en
     * ,* sammenligningsfunksjon
     * ,* @param c sammenligningsfunksjon som angir rekkefølgen
     * ,* til elementene i listen
     */
    @Override
    public void sort(Comparator<? super E> c) {
        this.checkInitialization();

        IList<E> sortedList = new LinkedList<>();
        while(!isEmpty()){
            E minimumValue = firstNode.getData();
            for (E elem : this) {
                if (c.compare(elem,minimumValue) < 0) {
                    minimumValue = elem;
                }
            }
            sortedList.add(minimumValue);
            this.remove(minimumValue);
        }

        this.clear();
        this.append(sortedList);
    }

    /**
     * ,* Fjerner elementer fra listen som svarer til et
     * ,* predikat.
     * ,* @param filter predikat som beskriver hvilken
     * ,* elementer som skal fjernes.
     */
    @Override
    public void filter(Predicate<? super E> p) {
        checkInitialization();

        Node currentNode = firstNode;
        for (E e : this) {
            if (p.test(e)){
                if (currentNode.equals(firstNode)){
                    firstNode = firstNode.getNext();
                    size--;
                }
                else {
                    remove(e);
                }
            }
            currentNode = currentNode.getNext();
        }
    }

    /**
     * ,* Kjører en funksjon over hvert element i listen
     * ,*
     * ,* @param f en funksjon fra typen til elementene i
     * ,* listen til en annen type
     * ,* @return En liste over elementene som funksjonen
     * ,* returnerer
     */
    @Override
    public <U> IList<U> map(Function<? super E, ? extends U> f) {
        checkInitialization();

        IList<U> tempList = new LinkedList<>();
        for(E elem : this){
            tempList.put(f.apply(elem));
        }
        IList<U> list = new LinkedList<>();
        for (U u : tempList) {
            list.put(u);
        }

        return list;
    }

    /**
     * ,* Slår sammen alle elementene i listen ved hjelp av en
     * ,* kombinasjonsfunksjon.
     * ,*
     * ,* @param t Det første elementet i sammenslåingen
     * ,* @param f Funksjonen som slår sammen elementene
     * ,* @return Den akkumulerte verdien av sammenslåingene
     */
    @Override
    public <T> T reduce(T t, BiFunction<T, ? super E, T> f) {
        checkInitialization();

        for(E elem : this){
            t = f.apply(t, elem);
        }

        return t;
    }

    /**
     * ,* Gir størrelsen på listen
     * ,*
     * ,* @return Størrelsen på listen
     * ,
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * ,* Fjerner alle elementene i listen.
     * ,
     */
    @Override
    public void clear() {
        checkInitialization();

        firstNode = null;
        size = 0;
    }

    /**
     * Gets node at specified position in chain
     * @param pos position of node
     * @return node at specified position
     */
    private Node getNodeAt(int pos) {
        assert (0 <= pos) && (pos <= size);
        Node curr = firstNode;

        for (int i = 0; i <= pos; i++) {
            if (curr.getNext() != null) {
                curr = curr.getNext();
            } else {
                return curr;
            }
        }

        return curr;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        checkInitialization();

        return new IteratorForLinkedList();
    }



    private class IteratorForLinkedList implements Iterator<E> {
        private Node nextNode;

        private IteratorForLinkedList() {
            nextNode = firstNode;
        }

        @Override
        public E next() {
            if (hasNext()) {
                Node returnNode = nextNode;
                nextNode = nextNode.getNext();

                return returnNode.getData();
            } else {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list");
            }
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is currently not supported");
        }
    }



    private class Node {
        private E data;
        private Node next;

        private Node(E elem) {
            data = elem;
            next = null;
        }

        private E getData() {
            return data;
        }

        private void setData(E newData) {
            data = newData;
        }

        private boolean hasNext() {
            return getNext() != null;
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private void createNext(E next) {
            this.next = new Node(next);
        }
    }
}
