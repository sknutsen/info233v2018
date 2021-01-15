package Deque;

import Exceptions.*;

/**
 * En samling som tillater innsetting og fjerning fra begge
 * sider av samlingen.
 */
public interface IDeque<E> {

    /**
     * Retunerer hvor mange elementer samlingen inneholder.
     *
     * @return Størrelsen på samlingen.
     */
    int size();

    /**
     * Legger til et element på begynnelsen av samlingen.
     *
     * @param elem Elementet som skal legges til.
     * @throws DequeFullException når det ikke er plass til
     *                            elementet.
     */
    void addFirst(E elem) throws DequeFullException;

    /**
     * Fjerner det første elementet fra samlingen og
     * returnerer det.
     *
     * @return Det første elementet i samlingen.
     * @throws DequeEmptyException hvis samlingen er tom.
     */
    E pullFirst() throws DequeEmptyException;

    /**
     * Retunerer det første elementet fra samlingen, men
     * fjerner det ikke.
     *
     * @return Det første elementet i samlingen.
     * @throws DequeEmptyException hvis samlingen er tom.
     */
    E peekFirst() throws DequeEmptyException;

    /**
     * Legger til et element på slutten av samlingen.
     *
     * @param elem Elementet som skal legges til.
     * @throws DequeFullException når det ikke er plass til
     *                            elementet.
     */
    void addLast(E elem) throws DequeFullException;

    /**
     * Fjerner det siste elementet i samlingen og returnerer
     * det.
     *
     * @return Det siste elementet i samlingen.
     * @throws DequeEmptyException hvis samlingen er tom.
     */
    E pullLast() throws DequeEmptyException;

    /**
     * Retunerer det siste elementet i samlingen, men
     * fjerner det ikke.
     *
     * @return Det siste elementet i samlingen.
     * @throws DequeEmptyException hvis samlingen er tom.
     */
    E peekLast() throws DequeEmptyException;

    /**
     * Sjekker om et element er i samlingen.
     *
     * @param elem Elementet som kanskje er i samlingen.
     * @return retunerer true hvis elementet er i samlingen.
     */
    boolean contains(Object elem);

    /**
     * Retunerer en tabell med alle elementene i samlingen.
     *
     * @param a En tabell med samme type som T[].
     * @return En tabell med alle elementene i samlingen.
     */
    <T> T[] toArray(T[] a);

    /**
     * Fjerner alle elementene fra samlingen.
     */
    void clear();
}