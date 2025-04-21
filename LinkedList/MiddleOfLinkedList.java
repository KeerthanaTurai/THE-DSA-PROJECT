/* File: MiddleOfLinkedList.java
 *
 * Problem 876. Middle of the Linked List
 *
 * Intuition:
 *   Use two pointers, tortoise (slow) and hare (fast). The hare moves twice for
 *   every one step of the tortoise. When the hare reaches the end of the list,
 *   the tortoise will be at the middle. Returning tortoise yields the middle
 *   node; when the list has an even number of nodes, this naturally returns
 *   the second middle node as required.
 *
 * Approach:
 *   1. Handle the trivial case where head is null.
 *   2. Initialize tortoise = head, hare = head.
 *   3. While hare != null and hare.next != null:
 *        - tortoise = tortoise.next;
 *        - hare = hare.next.next;
 *   4. When the loop exits, tortoise points at the middle node; return it.
 *
 * Time Complexity: O(n), where n is the number of nodes in the list.
 * Space Complexity: O(1), only a fixed number of pointers are used.
 */
public class MiddleOfLinkedList {

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        ListNode tort = head;
        ListNode hare = head;
        while (hare != null && hare.next != null) {
            tort = tort.next;
            hare = hare.next.next;
        }
        return tort;
    }

    // Helper to build a linked list from an array
    public static ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    // Helper to print a linked list from a given node
    public static void printList(ListNode node) {
        System.out.print("[");
        ListNode curr = node;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(", ");
            curr = curr.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MiddleOfLinkedList solution = new MiddleOfLinkedList();

        int[][] testCases = {
            {1, 2, 3, 4, 5},       // odd length
            {1, 2, 3, 4, 5, 6},    // even length
            {42},                  // single node
            {1, 2}                 // two nodes (should return second)
        };

        for (int[] tc : testCases) {
            ListNode head = createList(tc);
            System.out.print("Input: ");
            printList(head);

            ListNode mid = solution.middleNode(head);
            System.out.print("Middle node onwards: ");
            printList(mid);
            System.out.println();
        }
    }
}