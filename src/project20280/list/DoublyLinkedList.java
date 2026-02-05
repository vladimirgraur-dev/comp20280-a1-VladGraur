package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.prev = previous;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.next = newest;
        succ.prev = newest;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> curr = head.getNext();
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    @Override
    public void add(int position, E e) {
        Node<E> succ = head.getNext();
        for (int i = 0; i < position; i++) {
            succ = succ.getNext();
        }
        Node<E> pred = succ.getPrev();

        Node<E> newest = new Node<E>(e, pred, succ);
        pred.next = newest;
        succ.prev = newest;

        size++;
    }

    @Override
    public E remove(int position) {
        if (isEmpty()) {
            return null;
        }

        Node<E> eliminated = head.getNext();
        for (int i = 0; i < position; i++) {
            eliminated = eliminated.getNext();
        }

        Node<E> pred = eliminated.getPrev();
        Node<E> succ = eliminated.getNext();
        pred.next = succ;
        succ.prev = pred;

        size--;
        return eliminated.getData();
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        if (isEmpty()) {
            return null;
        }

        Node<E> eliminated = head.getNext();
        while (eliminated != n && eliminated != tail) {
            eliminated = eliminated.getNext();
        }
        if (eliminated == tail) {
            return null;
        }

        Node<E> pred = eliminated.getPrev();
        Node<E> succ = eliminated.getNext();
        pred.next = succ;
        succ.prev = pred;

        size--;
        return eliminated.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.prev.getData();
    }

    @Override
    public E removeFirst() {
        return remove(head.getNext());
    }

    @Override
    public E removeLast() {
        return remove(tail.getPrev());
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.getPrev(), tail);
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());
    }

    public String toString() {
        int guard = 0;
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            if (guard++ > 1000)
                throw new IllegalStateException("Cycle / broken links");

            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}