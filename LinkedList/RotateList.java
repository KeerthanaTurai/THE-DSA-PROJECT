/**
 * File: RotateList.java
 *
 * Problem 61. Rotate List
 *
 * Intuition:
 *   Rotating the list by k positions to the right is equivalent to
 *   cutting the list into two parts and swapping them: the last k nodes
 *   move to the front. We can compute the effective rotation k % len,
 *   locate the split point, then rewire pointers.
 *
 * Approach:
 *   1. Handle edge cases: if head is null, there’s only one node, or k is 0, return head.
 *   2. Traverse once to compute list length len.
 *   3. Compute k = k % len; if k == 0, the list remains unchanged.
 *   4. Compute offset = len - k. Advance a pointer `prev` to the node at position offset - 1.
 *   5. Let `newHead = prev.next`; then cut the list: `prev.next = null`.
 *   6. Traverse from newHead to its tail and link `tail.next = head`.
 *   7. Return newHead.
 *
 * Time Complexity: O(n) — one full pass to compute length, one partial pass to split, one to link.
 * Space Complexity: O(1) extra — only pointers are used.
 */
public class RotateList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // 1. Compute length
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // 2. Effective rotations
        k = k % len;
        if (k == 0) return head;

        // 3. Find split point
        int offset = len - k;
        ListNode prev = head;
        for (int i = 1; i < offset; i++) {
            prev = prev.next;
        }

        // 4. Rewire pointers
        ListNode newHead = prev.next;
        prev.next = null;
        tail.next = head;

        return newHead;
    }

    // Helper: build a list from an array of values
    private static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]), cur = head;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    // Helper: print a list in [a, b, c] format
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
        RotateList solution = new RotateList();

        int[][]    inputLists = {
            {1, 2, 3, 4, 5},
            {0, 1, 2},
            {},
            {1},
            {1, 2}
        };
        int[]      ks         = {2, 4, 5, 99, 1};

        for (int i = 0; i < inputLists.length; i++) {
            System.out.println("=== Test " + (i + 1) + " ===");
            ListNode head = createList(inputLists[i]);
            System.out.print("Original: ");
            printList(head);
            System.out.println("k = " + ks[i]);

            ListNode rotated = solution.rotateRight(head, ks[i]);
            System.out.print("Rotated : ");
            printList(rotated);
            System.out.println();
        }
    }
}

