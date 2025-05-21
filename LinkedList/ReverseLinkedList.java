import java.util.*;

/**
 * File: ReverseLinkedList.java
 *
 * Problem 206. Reverse Linked List
 *
 * Intuition:
 *   Reversing a singly linked list means flipping the direction of each next pointer.
 *   Iteratively, we walk through the list and adjust pointers one by one.
 *   Recursively, we reverse the tail and then fix the current node’s next pointer.
 *
 * Approach (Iterative):
 *   1. Maintain two pointers: prev (initially null) and curr (initially head).
 *   2. While curr != null:
 *        - Save curr.next in a temporary variable next.
 *        - Point curr.next to prev.
 *        - Move prev to curr, and curr to next.
 *   3. At the end, prev is the new head.
 *
 * Approach (Recursive):
 *   1. Base case: if head is null or head.next is null, return head.
 *   2. Recursively reverse the sublist from head.next onward, receiving newHead.
 *   3. head.next.next = head to flip the link.
 *   4. head.next = null to terminate the old link.
 *   5. Return newHead.
 *
 * Time Complexity: O(n) for both approaches, where n is the number of nodes.
 * Space Complexity:
 *   - Iterative: O(1) extra space.
 *   - Recursive: O(n) call stack in the worst case.
 */
public class ReverseLinkedList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * Iterative method to reverse a linked list.
     * @param head the head of the original list
     * @return the head of the reversed list
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode front = temp.next;  // save next
            temp.next = prev;            // reverse pointer
            prev = temp;                 // move prev forward
            temp = front;                // move temp forward
        }
        return prev;
    }

    /**
     * Recursive method to reverse a linked list.
     * @param head the head of the original list
     * @return the head of the reversed list
     */
    public ListNode reverseListRecursive(ListNode head) {
        // base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        // reverse the rest
        ListNode newHead = reverseListRecursive(head.next);
        // fix the pointers
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // Helper to build a linked list from an array
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : arr) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper to print the list
    public static void printList(ListNode head) {
        List<String> vals = new ArrayList<>();
        while (head != null) {
            vals.add(String.valueOf(head.val));
            head = head.next;
        }
        System.out.println(vals);
    }

    // Test various cases for both methods
    public static void main(String[] args) {
        ReverseLinkedList sol = new ReverseLinkedList();

        int[][] testCases = {
            {1, 2, 3, 4, 5},  // odd length
            {1, 2},           // even length
            {},               // empty list
            {42},             // single node
            {1, 2, 3},        // three nodes
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i];
            ListNode head = buildList(arr);

            System.out.printf("Test %d -- original: ", i + 1);
            printList(head);

            // Iterative
            ListNode revIter = sol.reverseList(buildList(arr));
            System.out.print("           iterative reversed: ");
            printList(revIter);

            // Recursive
            ListNode revRec = sol.reverseListRecursive(buildList(arr));
            System.out.print("           recursive reversed: ");
            printList(revRec);

            System.out.println("--------------------------------------------------");
        }
    }
}