/**
 * File: MergeTwoSortedLists.java
 *
 * Problem 21. Merge Two Sorted Lists
 *
 * Intuition:
 *   We maintain a pointer (tail) into the merged list. At each step we compare
 *   the current nodes of list1 and list2, attach the smaller one to tail, and
 *   advance that list. When one list runs out, attach the rest of the other.
 *
 * Approach:
 *   1. Handle null‐list edge cases up front.
 *   2. Initialize head = the smaller of list1/list2, advance that list.
 *   3. Keep a tail pointer pointing to the last node in the merged list.
 *   4. While both lists are non‐null:
 *        - Compare list1.val and list2.val.
 *        - tail.next = smaller node; advance that list and tail.
 *   5. At the end, one list may still have nodes—simply do tail.next = remaining.
 *   6. Return head.
 *
 * Time Complexity: O(n + m), where n and m are the lengths of the two lists.
 * Space Complexity: O(1) extra space (we only rearrange pointers).
 */
public class MergeTwoSortedLinkedLists {

    // Definition for singly‑linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // If either list is empty, return the other
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Choose starting head
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }

        // tail tracks end of merged list
        ListNode tail = head;

        // Merge until one runs out
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // Attach the remainder
        tail.next = (list1 != null) ? list1 : list2;

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

    // Helper to print a linked list from a given node
    private static void printList(ListNode node) {
        System.out.print("[");
        for (ListNode cur = node; cur != null; cur = cur.next) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MergeTwoSortedLinkedLists sol = new MergeTwoSortedLinkedLists();

        // test cases
        int[][] a1 = {{1,2,4}, {}, {1,2,3}, {}};
        int[][] a2 = {{1,3,4}, {}, {4,5,6}, {0,1}};

        for (int i = 0; i < a1.length; i++) {
            ListNode l1 = createList(a1[i]);
            ListNode l2 = createList(a2[i]);
            System.out.print("List1: ");
            printList(l1);
            System.out.print("List2: ");
            printList(l2);

            ListNode merged = sol.mergeTwoLists(l1, l2);
            System.out.print("Merged: ");
            printList(merged);
            System.out.println();
        }
    }
}
