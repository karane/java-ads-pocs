package org.karane.singlylinkedlist;

import java.util.NoSuchElementException;

/**
 * Finds the middle node of a singly linked list using the slow/fast pointer
 * technique.
 *
 * Two pointers start at the head. The slow pointer advances one step per
 * iteration. The fast pointer advances two. When fast reaches the end, slow
 * is at the middle.
 *
 * Time: O(n), Space: O(1)
 */
public class MiddleNode {

    private MiddleNode() {}

    public static <T> T find(SinglyLinkedList<T> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<T> slow = list.getFirstNode();
        Node<T> fast = list.getFirstNode();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }
}
