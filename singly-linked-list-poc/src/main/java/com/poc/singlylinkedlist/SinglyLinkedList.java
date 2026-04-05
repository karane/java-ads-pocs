package com.poc.singlylinkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void insertAtHead(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public void insertAtTail(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void insertAt(int index, T data) {
        checkPositionIndex(index);
        if (index == 0) {
            insertAtHead(data);
            return;
        }
        if (index == size) {
            insertAtTail(data);
            return;
        }
        Node<T> prev = nodeAt(index - 1);
        Node<T> node = new Node<>(data);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public T deleteAtHead() {
        checkNotEmpty();
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    public T deleteAtTail() {
        checkNotEmpty();
        T data = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> prev = head;
            while (prev.next != tail) {
                prev = prev.next;
            }
            prev.next = null;
            tail = prev;
        }
        size--;
        return data;
    }

    public boolean delete(T data) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(data)) {
            deleteAtHead();
            return true;
        }
        Node<T> prev = head;
        while (prev.next != null) {
            if (prev.next.data.equals(data)) {
                if (prev.next == tail) {
                    tail = prev;
                }
                prev.next = prev.next.next;
                size--;
                return true;
            }
            prev = prev.next;
        }
        return false;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>(size);
        Node<T> current = head;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }
        return result;
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }
        tail = head;
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public T findMiddle() {
        return MiddleNode.find(head);
    }

    private Node<T> nodeAt(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkNotEmpty() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is out of bounds for size " + size);
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is out of bounds for size " + size);
        }
    }
}
