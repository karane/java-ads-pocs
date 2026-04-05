package com.poc.singlylinkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Nested
    class InsertionTests {

        @Test
        void insertAtHead_onEmptyList_sizeIsOne() {
            list.insertAtHead(1);
            assertEquals(1, list.size());
            assertEquals(List.of(1), list.toList());
        }

        @Test
        void insertAtHead_multipleItems_orderIsReversed() {
            list.insertAtHead(3);
            list.insertAtHead(2);
            list.insertAtHead(1);
            assertEquals(List.of(1, 2, 3), list.toList());
        }

        @Test
        void insertAtTail_onEmptyList_sizeIsOne() {
            list.insertAtTail(42);
            assertEquals(1, list.size());
            assertEquals(List.of(42), list.toList());
        }

        @Test
        void insertAtTail_multipleItems_preservesOrder() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            assertEquals(List.of(1, 2, 3), list.toList());
        }

        @Test
        void insertAt_middleIndex_insertsCorrectly() {
            list.insertAtTail(1);
            list.insertAtTail(3);
            list.insertAt(1, 2);
            assertEquals(List.of(1, 2, 3), list.toList());
        }

        @Test
        void insertAt_indexZero_behavesLikeInsertAtHead() {
            list.insertAtTail(2);
            list.insertAt(0, 1);
            assertEquals(List.of(1, 2), list.toList());
        }

        @Test
        void insertAt_indexEqualToSize_behavesLikeInsertAtTail() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAt(2, 3);
            assertEquals(List.of(1, 2, 3), list.toList());
        }

        @Test
        void insertAt_negativeIndex_throwsIndexOutOfBoundsException() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.insertAt(-1, 99));
        }

        @Test
        void insertAt_indexBeyondSize_throwsIndexOutOfBoundsException() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.insertAt(1, 99));
        }
    }

    @Nested
    class DeletionTests {

        @Test
        void deleteAtHead_singleElement_listBecomesEmpty() {
            list.insertAtTail(1);
            int removed = list.deleteAtHead();
            assertEquals(1, removed);
            assertTrue(list.isEmpty());
        }

        @Test
        void deleteAtHead_multipleElements_removesFirst() {
            list.insertAtTail(10);
            list.insertAtTail(20);
            list.insertAtTail(30);
            assertEquals(10, list.deleteAtHead());
            assertEquals(List.of(20, 30), list.toList());
        }

        @Test
        void deleteAtHead_emptyList_throwsNoSuchElementException() {
            assertThrows(NoSuchElementException.class, () -> list.deleteAtHead());
        }

        @Test
        void deleteAtTail_singleElement_listBecomesEmpty() {
            list.insertAtTail(7);
            assertEquals(7, list.deleteAtTail());
            assertTrue(list.isEmpty());
        }

        @Test
        void deleteAtTail_multipleElements_removesLast() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            assertEquals(3, list.deleteAtTail());
            assertEquals(List.of(1, 2), list.toList());
        }

        @Test
        void deleteAtTail_emptyList_throwsNoSuchElementException() {
            assertThrows(NoSuchElementException.class, () -> list.deleteAtTail());
        }

        @Test
        void deleteByValue_existingValue_returnsTrueAndRemovesNode() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            assertTrue(list.delete(2));
            assertEquals(List.of(1, 3), list.toList());
        }

        @Test
        void deleteByValue_headValue_updatesHead() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            assertTrue(list.delete(1));
            assertEquals(List.of(2), list.toList());
        }

        @Test
        void deleteByValue_tailValue_updatesTail() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            assertTrue(list.delete(2));
            assertEquals(List.of(1), list.toList());
            // Verify tail pointer is correct by appending after delete
            list.insertAtTail(3);
            assertEquals(List.of(1, 3), list.toList());
        }

        @Test
        void deleteByValue_missingValue_returnsFalse() {
            list.insertAtTail(1);
            assertFalse(list.delete(99));
            assertEquals(1, list.size());
        }

        @Test
        void deleteByValue_duplicateValues_removesFirstOccurrenceOnly() {
            list.insertAtTail(5);
            list.insertAtTail(5);
            list.insertAtTail(5);
            assertTrue(list.delete(5));
            assertEquals(2, list.size());
        }
    }

    @Nested
    class QueryTests {

        @Test
        void contains_presentValue_returnsTrue() {
            list.insertAtTail(42);
            assertTrue(list.contains(42));
        }

        @Test
        void contains_absentValue_returnsFalse() {
            list.insertAtTail(1);
            assertFalse(list.contains(999));
        }

        @Test
        void isEmpty_newList_isTrue() {
            assertTrue(list.isEmpty());
        }

        @Test
        void isEmpty_afterInsertion_isFalse() {
            list.insertAtHead(1);
            assertFalse(list.isEmpty());
        }

        @Test
        void size_tracksInsertionsAndDeletions() {
            assertEquals(0, list.size());
            list.insertAtTail(1);
            list.insertAtTail(2);
            assertEquals(2, list.size());
            list.deleteAtHead();
            assertEquals(1, list.size());
        }
    }

    @Nested
    class ReversalTests {

        @Test
        void reverse_oddLengthList_correctOrder() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            list.reverse();
            assertEquals(List.of(3, 2, 1), list.toList());
        }

        @Test
        void reverse_evenLengthList_correctOrder() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            list.insertAtTail(4);
            list.reverse();
            assertEquals(List.of(4, 3, 2, 1), list.toList());
        }

        @Test
        void reverse_singleElement_unchanged() {
            list.insertAtTail(7);
            list.reverse();
            assertEquals(List.of(7), list.toList());
        }

        @Test
        void reverse_emptyList_noException() {
            assertDoesNotThrow(() -> list.reverse());
        }

        @Test
        void reverse_twice_restoresOriginalOrder() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            list.reverse();
            list.reverse();
            assertEquals(List.of(1, 2, 3), list.toList());
        }

        @Test
        void reverse_tailPointerIsCorrectAfterReverse() {
            list.insertAtTail(1);
            list.insertAtTail(2);
            list.insertAtTail(3);
            list.reverse();
            // After reverse the new tail should be 1; inserting appends after it
            list.insertAtTail(99);
            assertEquals(List.of(3, 2, 1, 99), list.toList());
        }
    }

}
