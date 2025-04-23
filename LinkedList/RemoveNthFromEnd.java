/**
 * File: RemoveNthFromEnd.java
 *
 * Problem 19. Remove Nth Node From End of List
 *
 * Intuition:
 *   By advancing a fast pointer n+1 steps ahead of a slow pointer (both starting
 *   from a dummy node), when fast reaches the end, slow will be just before
 *   the node we need to remove. This lets us unlink the target in one pass.
 *
 * Approach:
 *   1. Create a dummy node pointing at head to simplify edge cases.
 *   2. Initialize two pointers, fast and slow, to dummy.
 *   3. Advance fast by n+1 steps.
 *   4. Move both fast and slow together until fast hits null.
 *   5. slow.next is the node to remove; skip it by slow.next = slow.next.next.
 *   6. Return dummy.next.
 *
 * Time Complexity: O(L), where L is the length of the list (single pass).
 * Space Complexity: O(1), only a few pointers used.
 */
public class RemoveNthFromEnd {

    // Definition for singly‑linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) { val = x; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;
        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // Move both until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // Unlink the n-th node from end
        slow.next = slow.next.next;
        return dummy.next;
    }

    // Helper to build a list from an array
    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0), curr = dummy;
        for (int x : arr) {
            curr.next = new ListNode(x);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper to print a list in [a, b, c] format
    private static void printList(ListNode head) {
        System.out.print("[");
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        RemoveNthFromEnd solution = new RemoveNthFromEnd();

        // Test cases: pairs of (list array, n)
        int[][] lists = {
            {1, 2, 3, 4, 5},
            {1},
            {1, 2},
            {1, 2},
            {1, 2, 3}
        };
        int[] ns = {2, 1, 1, 2, 3};

        for (int i = 0; i < lists.length; i++) {
            int[] arr = lists[i];
            int n = ns[i];
            System.out.println("=== Test " + (i+1) + " ===");
            System.out.print("Input: ");
            printList(createList(arr));
            System.out.println("n = " + n);

            ListNode result = solution.removeNthFromEnd(createList(arr), n);
            System.out.print("Output: ");
            printList(result);
            System.out.println();
        }
    }
}
