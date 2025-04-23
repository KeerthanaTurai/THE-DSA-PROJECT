/**
 * File: LinkedListCycle.java
 *
 * Problem 141. Linked List Cycle
 *
 * Intuition:
 *   Use Floyd’s Tortoise and Hare algorithm to detect a cycle. Move one pointer
 *   (slow) by one step and another (fast) by two steps. If there’s a cycle,
 *   they will eventually meet; otherwise, fast will reach the end.
 *
 * Approach:
 *   1. If head is null, return false.
 *   2. Initialize slow = head and fast = head.
 *   3. While fast != null and fast.next != null:
 *        - slow = slow.next
 *        - fast = fast.next.next
 *        - if slow == fast, return true.
 *   4. If the loop exits, there’s no cycle; return false.
 *
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1), only two pointers are used.
 */
public class LinkedListCycle {

    // Definition for singly‑linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * Builds a linked list from values and introduces a cycle at position pos.
     * @param vals array of node values
     * @param pos  index at which tail.next points to (−1 for no cycle)
     * @return head of the potentially cycled list
     */
    private static ListNode createListWithCycle(int[] vals, int pos) {
        ListNode head = null, tail = null, cycleNode = null;
        for (int i = 0; i < vals.length; i++) {
            ListNode node = new ListNode(vals[i]);
            if (head == null) head = node;
            else tail.next = node;
            tail = node;
            if (i == pos) cycleNode = node;
        }
        if (tail != null && pos >= 0) {
            tail.next = cycleNode;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test cases: pairs of (values array, cycle position)
        int[][]    testVals = {
            {3, 2, 0, -4},
            {1, 2},
            {1},
            {}
        };
        int[] pos = {1, 0, -1, -1};  // -1 means no cycle

        for (int i = 0; i < testVals.length; i++) {
            System.out.println("=== Test " + (i + 1) + " ===");
            System.out.print("List values: ");
            System.out.print(java.util.Arrays.toString(testVals[i]));
            System.out.println(", cycle at pos: " + pos[i]);

            ListNode head = createListWithCycle(testVals[i], pos[i]);
            boolean result = solution.hasCycle(head);
            System.out.println("Has cycle? " + result);
            System.out.println();
        }
    }
}

