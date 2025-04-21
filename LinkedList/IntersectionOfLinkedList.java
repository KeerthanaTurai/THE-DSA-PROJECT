/**
 * File: IntersectionOfLinkedLists.java
 *
 * Problem 160. Intersection of Two Linked Lists
 *
 * Intuition:
 *   Use two pointers, pA and pB, starting at the heads of the two lists.
 *   Advance each pointer one step at a time. When a pointer reaches the end
 *   of its list, redirect it to the head of the other list. If the lists
 *   intersect, the pointers will meet at the intersection node after at most
 *   (lenA + lenB) steps; otherwise, they will both reach null.
 *
 * Approach:
 *   1. Handle null‐head edge cases immediately.
 *   2. Initialize pA = headA, pB = headB.
 *   3. Loop until pA == pB:
 *        - pA = (pA == null) ? headB : pA.next;
 *        - pB = (pB == null) ? headA : pB.next;
 *   4. Return pA (which is either the intersection node or null).
 *
 * Time Complexity: O(m + n), where m and n are the lengths of the two lists.
 * Space Complexity: O(1), only two pointers are used.
 */
public class IntersectionOfLinkedList {

    // Definition for singly‐linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * Returns the intersection node of two singly linked lists,
     * or null if they do not intersect.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }

    // Helper: build a linked list from an array, return its head
    private static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]), cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    // Helper: return the tail node of a list
    private static ListNode getTail(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    // Helper: print a list from given node
    private static void printList(ListNode head) {
        System.out.print("[");
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        IntersectionOfLinkedList sol = new IntersectionOfLinkedList();

        // --- Test 1: lists intersect at node with value 8 ---
        int[] a1 = {4, 1};
        int[] b1 = {5, 6, 1};
        int[] common = {8, 4, 5};

        ListNode headA1 = createList(a1);
        ListNode headB1 = createList(b1);
        ListNode commonPart = createList(common);

        getTail(headA1).next = commonPart;
        getTail(headB1).next = commonPart;

        System.out.print("List A1: "); printList(headA1);
        System.out.print("List B1: "); printList(headB1);
        ListNode inter1 = sol.getIntersectionNode(headA1, headB1);
        System.out.println("Intersection at: " + 
            (inter1 != null ? inter1.val : "null"));
        System.out.println();

        // --- Test 2: no intersection, both non‐empty ---
        ListNode headA2 = createList(new int[]{2, 6, 4});
        ListNode headB2 = createList(new int[]{1, 5});
        System.out.print("List A2: "); printList(headA2);
        System.out.print("List B2: "); printList(headB2);
        ListNode inter2 = sol.getIntersectionNode(headA2, headB2);
        System.out.println("Intersection at: " + 
            (inter2 != null ? inter2.val : "null"));
        System.out.println();

        // --- Test 3: one list empty ---
        ListNode headA3 = null;
        ListNode headB3 = createList(new int[]{1, 2, 3});
        System.out.print("List A3: "); printList(headA3);
        System.out.print("List B3: "); printList(headB3);
        ListNode inter3 = sol.getIntersectionNode(headA3, headB3);
        System.out.println("Intersection at: " + 
            (inter3 != null ? inter3.val : "null"));
        System.out.println();

        // --- Test 4: both lists empty ---
        ListNode headA4 = null;
        ListNode headB4 = null;
        System.out.print("List A4: "); printList(headA4);
        System.out.print("List B4: "); printList(headB4);
        ListNode inter4 = sol.getIntersectionNode(headA4, headB4);
        System.out.println("Intersection at: " + 
            (inter4 != null ? inter4.val : "null"));
    }
}

