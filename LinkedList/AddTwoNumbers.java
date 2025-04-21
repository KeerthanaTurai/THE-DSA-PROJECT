/**
 * File: AddTwoNumbers.java
 *
 * Problem 2. Add Two Numbers
 *
 * Intuition:
 *   We simulate digit‐by‐digit addition as you would by hand, using a carry
 *   variable. Since the lists store digits in reverse order, we can process
 *   from head to tail directly.
 *
 * Approach:
 *   1. Create a dummy head to simplify result list construction.
 *   2. Maintain a `tail` pointer into the result, and an integer `carry = 0`.
 *   3. Loop while either input list has nodes or carry ≠ 0:
 *        - Let x = (l1 != null) ? l1.val : 0
 *        - Let y = (l2 != null) ? l2.val : 0
 *        - sum = x + y + carry
 *        - carry = sum / 10
 *        - Append new node with value (sum % 10) to tail
 *        - Advance l1, l2 if non‑null
 *   4. Return dummy.next as the head of the summed list.
 *
 * Time Complexity: O(max(n, m)), where n and m are the lengths of the two lists.
 * Space Complexity: O(max(n, m) + 1) for the result list (plus the dummy head).
 */
public class AddTwoNumbers {

    // Definition for singly‑linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }

    // Helper to build a linked list from an array of digits (least‐significant digit first)
    private static ListNode createList(int[] digits) {
        ListNode dummy = new ListNode(0), curr = dummy;
        for (int d : digits) {
            curr.next = new ListNode(d);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper to print a linked list as [d0, d1, d2, ...]
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
        AddTwoNumbers sol = new AddTwoNumbers();

        // Test cases: each pair represents (l1 digits, l2 digits) and expected result
        int[][][] tests = {
            {{2,4,3},    {5,6,4}},    // 342 + 465 = 807 -> [7,0,8]
            {{0},        {0}},        // 0 + 0 = 0 -> [0]
            {{9,9,9,9},  {1}},        // 9999 + 1 = 10000 -> [0,0,0,0,1]
            {{2,4,9},    {5,6,4,9}},  // 942 + 9465 = 10407 -> [7,0,4,0,1]
            {{1,8},      {0}},        // 81 + 0 = 81 -> [1,8]
        };

        for (int i = 0; i < tests.length; i++) {
            int[] a1 = tests[i][0];
            int[] a2 = tests[i][1];
            System.out.println("=== Test " + (i+1) + " ===");
            ListNode l1 = createList(a1);
            ListNode l2 = createList(a2);
            System.out.print("l1: "); printList(l1);
            System.out.print("l2: "); printList(l2);

            ListNode sum = sol.addTwoNumbers(l1, l2);
            System.out.print("sum: "); printList(sum);
            System.out.println();
        }
    }
}
