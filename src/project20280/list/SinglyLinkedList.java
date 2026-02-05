package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private E element; // reference to the element stored at this node

        private Node<E> next; // reference to the subsequent node in the list

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods
        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        // Modifier methods
        public void setNext(Node<E> n) {
            next = n;
        }
    } // ----------- end of nested Node class -----------

    private Node<E> head = null; // head node of the list (or null if empty)

    private int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    } // constructs an initially empty list

    // @Override
    public int size() {
        return size;
    }

    // @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // @Override
    public E get(int position) {
        Node<E> curr = (Node<E>) head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    // @Override
    public void add(int position, E e) {
        Node<E> curr = (Node<E>) head;
        for (int i = 0; i < position - 1; i++) {
            curr = curr.getNext();
        }
        Node<E> newest = new Node<E>(e, curr.next);
        curr.next = newest;

        size++;
    }

    // @Override
    public void addFirst(E e) {
        head = new Node<E>(e, head);
        size++;
    }

    // @Override
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        if (last == null) {
            head = newest;
        } else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    // @Override
    public E remove(int position) {
        if (isEmpty()) {
            return null;
        }

        Node<E> previous = (Node<E>) head;
        for (int i = 0; i < position - 1; i++) {
            previous = previous.getNext();
        }

        Node<E> eliminated = previous.getNext();
        previous.next = eliminated.getNext();

        size--;
        return eliminated.getElement();
    }

    // @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E element = head.getElement();
        head = head.getNext();
        size--;
        return element;
    }

    // @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node<E> newLast = (Node<E>) head;
        if (newLast.getNext() == null) {
            head = null;
            size--;
            return newLast.getElement();
        }

        for (int i = 0; i < size - 2; i++) {
            newLast = newLast.getNext();
        }

        Node<E> eliminated = newLast.getNext();
        newLast.next = null;

        size--;
        return eliminated.getElement();
    }

    // @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }



    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        // LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        // ll.removeLast();
        // ll.removeFirst();
        // System.out.println("I accept your apology");
        // ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
