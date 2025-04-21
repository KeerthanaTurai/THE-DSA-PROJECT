/**
 * File: OddEvenLinkedList.java
 *
 * Problem 328. Odd Even Linked List
 *
 * Intuition:
 *   We can partition the list into two sublists—one containing nodes at odd
 *   positions and the other at even positions—by relinking next pointers
 *   in a single pass. We keep track of the start of the even sublist so we
 *   can attach it after the odd sublist at the end.
 *
 * Approach:
 *   1. Handle edge cases: if the list is empty or has only one node, return it.
 *   2. Initialize `odd` to head, `even` to head.next, and remember `evenHead = even`.
 *   3. While `even` and `even.next` are not null:
 *        - Link `odd.next = odd.next.next` (skip over the even node).
 *        - Link `even.next = even.next.next` (skip over the odd node).
 *        - Advance `odd = odd.next` and `even = even.next`.
 *   4. After the loop, attach the even sublist after the odd sublist:
 *        `odd.next = evenHead`.
 *   5. Return head.
 *
 * Time Complexity: O(n) — we traverse the list once.
 * Space Complexity: O(1) — we only use a fixed number of pointers.
 */
public class OddEvenLinkedList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    // Helper to build a linked list from an array
    private static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    // Helper to print a linked list in [a, b, c] format
    private static void printList(ListNode head) {
        System.out.print("[");
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(", ");
            cur = cur.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        OddEvenLinkedList solution = new OddEvenLinkedList();

        // Test cases
        int[][] tests = {
            {1, 2, 3, 4, 5},        // odd-length list
            {2, 1, 3, 5, 6, 4, 7},  // mixed values
            {},                     // empty list
            {1},                    // single node
            {1, 2}                  // two nodes
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("=== Test " + (i + 1) + " ===");
            ListNode head = createList(tests[i]);
            System.out.print("Original: ");
            printList(head);

            ListNode result = solution.oddEvenList(head);
            System.out.print("Reordered: ");
            printList(result);
            System.out.println();
        }
    }
}

