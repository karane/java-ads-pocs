package org.karane.singlylinkedlist;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MiddleNodeTest {

    @Test
    void find_oddLength_returnsCenterNode() {
        assertEquals(2, MiddleNode.find(listOf(1, 2, 3)));
    }

    @Test
    void find_evenLength_returnsSecondMiddle() {
        assertEquals(3, MiddleNode.find(listOf(1, 2, 3, 4)));
    }

    @Test
    void find_singleNode_returnsThatNode() {
        assertEquals(42, MiddleNode.find(listOf(42)));
    }

    @Test
    void find_twoNodes_returnsSecondNode() {
        assertEquals(2, MiddleNode.find(listOf(1, 2)));
    }

    @Test
    void find_emptyList_throwsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> MiddleNode.find(new SinglyLinkedList<>()));
    }

    // -------------------------------------------------------------------------

    @SafeVarargs
    private static <T> SinglyLinkedList<T> listOf(T... values) {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();
        for (T v : values) {
            list.insertAtTail(v);
        }
        return list;
    }
}
