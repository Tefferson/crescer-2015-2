package list.br.com.cwi.list.linked;

import java.util.ArrayList;
import java.util.List;

import list.br.com.cwi.list.exception.EmptyListException;

public class DoublyLinkedList<T> implements ILinkedList<T> {

    private Node<T> first, last;

    @Override
    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null, first);

        if (isEmpty()) {
            last = node;
        }
        first = node;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        return first.getValue();
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        return last.getValue();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public List<T> list() {
        ArrayList<T> list = new ArrayList<>();

        Node<T> actual = first;

        while (actual != null) {
            list.add(actual.getValue());
            actual = actual.getNext();
        }

        return list;
    }

    @Override
    public void addLast(T value) {
        Node<T> newLast = new Node<>(value, last, null);

        if (isEmpty()) {
            first = newLast;
        } else {
            last.setNext(newLast);
        }
        last = newLast;
    }

    @Override
    public void removeFirst() {
        if (!isEmpty()) {
            first = first.getNext();
        }
    }

    @Override
    public void add(int index, T value) {

        if (index == 0 || isEmpty()) {
            addFirst(value);
        } else {
            Node<T> actualNode = getNode(index);

            if (actualNode != null) {
                Node<T> newNode = new Node<>(value);

                newNode.setNext(actualNode.getNext());
                newNode.setPrevious(actualNode);

                actualNode.setNext(newNode);
                newNode.getNext().setPrevious(newNode);
            }
        }
    }

    @Override
    public void remove(int index) {
        if (index == 0 || isEmpty()) {
            removeFirst();
        } else {
            Node<T> actualNode = getNode(index);

            if (actualNode != null) {
                actualNode.setNext(actualNode.getNext().getNext());
            }
        }
    }
    
    private Node<T> getNode(int index) {

        Node<T> actualNode = first;
        if (index > 0) {
            for (int i = 1; i < index; i++) {
                if (actualNode == null) {
                    return null;
                }
                actualNode = actualNode.getNext();
            }
        }
        return actualNode;
    }

    private class Node<E> {

        private E value;

        private Node<E> next, previous;

        public Node(E value, Node<E> previous, Node<E> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return value.toString();
        }

    }
}
