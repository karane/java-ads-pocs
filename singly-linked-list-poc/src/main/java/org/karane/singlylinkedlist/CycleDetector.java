package org.karane.singlylinkedlist;

public class CycleDetector {

    private CycleDetector() {}

    /**
     * Returns true if the list rooted at head contains a cycle.
     */
    public static <T> boolean detect(Node<T> head) {
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
