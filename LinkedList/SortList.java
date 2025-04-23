/**
 * File: SortList.java
 *
 * Problem 148. Sort List
 *
 * Intuition:
 *   We can sort a singly linked list in O(n log n) time by applying merge sort:
 *   repeatedly split the list into halves, sort each half, then merge the sorted halves.
 *   Finding the midpoint with two pointers lets us divide in O(n), and merging two
 *   sorted lists takes O(n). The recursion depth is O(log n).
 *
 * Approach:
 *   1. Base case: if head is null or head.next is null, it’s already sorted.
 *   2. Split:
 *      - Use slow/fast pointers to locate the middle.
 *      - Cut the list into two halves by setting prev.next = null.
 *   3. Recursively sort the left and right halves.
 *   4. Merge the two sorted halves with a helper that splices nodes in order.
 *   5. Return the merged head.
 *
 * Time Complexity: O(n log n) — each level processes all n nodes merging,
 *                   with log n levels of recursion.
 * Space Complexity: O(log n) for the recursion stack (no extra lists allocated).
 */
public class SortList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // Merge two sorted lists in one pass.
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Find and split at the middle; return head of the right half.
    private ListNode middleOfList(ListNode head) {
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // cut the list
        if (prev != null) prev.next = null;
        return slow;
    }

    // Main sortList using merge sort.
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = middleOfList(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergeTwoLists(left, right);
    }

    // Helper: build a linked list from an array
    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0), curr = dummy;
        for (int x : arr) {
            curr.next = new ListNode(x);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: print a linked list in [a, b, c] format
    private static void printList(ListNode head) {
        System.out.print("[");
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        SortList solution = new SortList();

        // Test cases
        int[][] tests = {
            {4, 2, 1, 3},
            {-1, 5, 3, 4, 0},
            {},
            {1},
            {2, 1}
        };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("=== Test " + (i + 1) + " ===");
            int[] arr = tests[i];
            System.out.print("Input:  ");
            ListNode head = createList(arr);
            printList(head);

            ListNode sorted = solution.sortList(head);
            System.out.print("Sorted: ");
            printList(sorted);
            System.out.println();
        }
    }
}
