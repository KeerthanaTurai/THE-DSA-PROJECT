/**
 * File: DetectCycleII.java
 *
 * Problem 142. Linked List Cycle II
 *
 * Intuition:
 *   Use Floyd’s Tortoise and Hare algorithm to detect a cycle. Once the
 *   slow and fast pointers meet inside the cycle, reset one pointer to the
 *   head and advance both one step at a time; they will meet at the cycle start.
 *
 * Approach:
 *   1. If head is null or has only one node, return null (no cycle).
 *   2. Use two pointers, slow = head and fast = head.
 *   3. Move slow by one and fast by two until they meet or fast reaches null.
 *   4. If no meeting (fast hit null), return null.
 *   5. Otherwise, reset slow to head. Move slow and fast by one each step;
 *      the node where they meet is the cycle’s entry point.
 *
 * Time Complexity: O(n) — each pointer traverses at most 2n steps.
 * Space Complexity: O(1) — only a fixed number of pointers are used.
 */
public class DetectCycleII {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        // 1) Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (slow != fast) return null;  // no cycle

        // 2) Find entry
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    private static ListNode createListWithCycle(int[] arr, int pos) {
        ListNode head = null, tail = null, cycleNode = null;
        for (int i = 0; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
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
        DetectCycleII solution = new DetectCycleII();

        int[][] testVals = {
            {3, 2, 0, -4},
            {1, 2},
            {1}
        };
        int[] pos = {1, 0, -1};

        for (int i = 0; i < testVals.length; i++) {
            int[] vals = testVals[i];
            System.out.print("List values: ");
            System.out.print(java.util.Arrays.toString(vals));
            System.out.println(", cycle at index: " + pos[i]);

            ListNode head = createListWithCycle(vals, pos[i]);
            ListNode start = solution.detectCycle(head);

            System.out.println("Cycle starts at node with value: " +
                (start != null ? start.val : "null"));
            System.out.println();
        }
    }
}
