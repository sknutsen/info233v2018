package Deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDequeTest {
    private IDeque<String> deque;
    private String first;

    @BeforeEach
    void setUp() {
        deque = new ArrayDeque<>();
        first = "1";
    }

    @Test
    void size() {
        assertEquals(deque.size(), 0);
        deque.addFirst(first);
        assertEquals(deque.size(), 1);
    }

    @Test
    void addFirst() {
        deque.addFirst(first);
        assertEquals(deque.size(), 1);
        assertEquals(first, deque.peekFirst());
    }

    @Test
    void pullFirst() {
        deque.addFirst(first);
        String pull = deque.pullFirst();
        assertEquals(first, pull);
        assertEquals(deque.size(), 0);
    }

    @Test
    void peekFirst() {
        deque.addFirst(first);
        String peek = deque.peekFirst();
        assertEquals(first, peek);
    }

    @Test
    void addLast() {
        deque.addLast(first);
        assertEquals(deque.size(), 1);
        assertEquals(first, deque.peekLast());
    }

    @Test
    void pullLast() {
        deque.addLast(first);
        String pull = deque.pullLast();
        assertEquals(first, pull);
        assertEquals(deque.size(), 0);
    }

    @Test
    void peekLast() {
        deque.addLast(first);
        String peek = deque.peekLast();
        assertEquals(first, peek);
    }

    @Test
    void contains() {
        deque.addLast("nein");
        deque.addFirst(first);
        deque.addFirst("kek");
        deque.addLast("123");
        assertTrue(deque.contains(first));
    }

    @Test
    void clear() {
        deque.addFirst(first);
        deque.addLast("nein");
        deque.clear();
        assertEquals(deque.size(), 0);
    }

    @Test
    void toArray() {
    }
}