package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            data = e;
            next = n;
        }

        public E getData() {
            return data;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    private Node<E> tail;
    private int size = 0;

    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> curr = (Node<E>) tail;
        for (int i = 0; i < position + 1; i++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    @Override
    public void add(int position, E e) {
        if (size == 0) {
            tail = new Node<E>(e, tail);
            tail.next = tail;
        } else {

            Node<E> curr = (Node<E>) tail;
            for (int i = 0; i < position; i++) {
                curr = curr.getNext();
            }
            Node<E> newest = new Node<E>(e, curr.next);
            curr.next = newest;
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (isEmpty()) {
            return null;
        }

        Node<E> previous = (Node<E>) tail;
        for (int i = 0; i < position; i++) {
            previous = previous.getNext();
        }

        Node<E> eliminated = previous.getNext();
        previous.next = eliminated.getNext();

        size--;
        return eliminated.getData();
    }

    public void rotate() {
        addLast(tail.getNext().getData());
        removeFirst();

    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    // @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E element = tail.getNext().getData();
        tail.next = tail.getNext().getNext();
        size--;
        return element;
    }

    // @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (tail.getNext() == tail) {
            E data = tail.getData();
            tail = null;
            size--;
            return data;
        }

        Node<E> newLast = (Node<E>) tail;

        for (int i = 0; i < size - 1; i++) {
            newLast = newLast.getNext();
        }
        E data = tail.getData();
        newLast.next = tail.next;
        tail = newLast;

        size--;
        return data;
    }

    // @Override
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<E>(e, tail);
            tail.next = tail;
        } else {
            Node<E> oldFirst = tail.getNext();
            Node<E> newFirst = new Node<E>(e, oldFirst);
            tail.next = newFirst;
        }
        size++;
    }

    // @Override
    public void addLast(E e) {
        if (size == 0) {
            tail = new Node<E>(e, tail);
            tail.next = tail;
        } else {
            Node<E> newest = new Node<E>(e, tail.next);
            tail.next = newest;
            tail = newest;
        }
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
