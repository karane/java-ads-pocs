package org.karane.singlylinkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycleDetectorTest {

    @Test
    void detect_nullHead_returnsFalse() {
        assertFalse(CycleDetector.detect(null));
    }

    @Test
    void detect_singleNodeNoCycle_returnsFalse() {
        Node<Integer> head = new Node<>(1);
        assertFalse(CycleDetector.detect(head));
    }

    @Test
    void detect_multipleNodesNoCycle_returnsFalse() {
        Node<Integer> head = chain(1, 2, 3, 4, 5);
        assertFalse(CycleDetector.detect(head));
    }

    @Test
    void detect_cycleAtHead_returnsTrue() {
        // 1 -> 2 -> 3 -> (back to 1)
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        assertTrue(CycleDetector.detect(n1));
    }

    @Test
    void detect_cycleAtMiddle_returnsTrue() {
        // 1 -> 2 -> 3 -> 4 -> 5 -> (back to 3)
        Node<Integer> head = chain(1, 2, 3, 4, 5);
        // walk to node 5 and node 3 to form cycle
        Node<Integer> node3 = head.next.next;
        Node<Integer> node5 = node3.next.next;
        node5.next = node3;
        assertTrue(CycleDetector.detect(head));
    }

    @Test
    void detect_selfLoop_returnsTrue() {
        Node<Integer> n = new Node<>(99);
        n.next = n;
        assertTrue(CycleDetector.detect(n));
    }

    @SafeVarargs
    private static <T> Node<T> chain(T... values) {
        Node<T> head = new Node<>(values[0]);
        Node<T> current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node<>(values[i]);
            current = current.next;
        }
        return head;
    }
}
