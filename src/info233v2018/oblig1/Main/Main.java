package Main;

import Deque.ArrayDeque;
import Deque.IDeque;

public class Main {
    private static boolean var;

    public static void main(String[] args) {
        IDeque<String> deque = new ArrayDeque<>(15);
        deque.addFirst("1");
        deque.addLast("1.5");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addLast("4.5");
        deque.addFirst("5");
        deque.addFirst("6");
        deque.addFirst("7");
        deque.addLast("7.5");
        System.out.printf("size: %2d%n", deque.size());
        System.out.printf("true: %b", var);
    }
}
