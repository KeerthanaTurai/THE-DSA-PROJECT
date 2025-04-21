/**
 * File: MergeKLists.java
 *
 * Problem 23. Merge k Sorted Lists
 *
 * Intuition:
 *   Instead of pushing all nodes into a heap, we can merge the k lists
 *   pairwise (divide and conquer). At each level we merge pairs of lists,
 *   halving the number of lists per pass. This still runs in O(N log k)
 *   time but has lower constant overhead compared to a priority queue.
 *
 * Approach:
 *   1. If the input array is null or empty, return null.
 *   2. While more than one list remains:
 *        - Merge lists in pairs: merge lists[i] with lists[i + interval].
 *        - Double the interval each pass.
 *   3. Return lists[0].
 *
 *   Helper `mergeTwoLists` does a standard O(n+m) merge of two sorted lists.
 *
 * Time Complexity: O(N log k), where N is the total number of nodes and k is the number of lists.
 * Space Complexity: O(1) extra space (in-place pointer manipulation).
 */
public class MergeKLists {

    // Definition for singly‑linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // Merge two sorted lists in O(n + m) time.
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
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

    // Divide‑and‑conquer merge k lists.
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int interval = 1;
        int n = lists.length;
        while (interval < n) {
            for (int i = 0; i + interval < n; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    // Helper: build a list from array
    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0), curr = dummy;
        for (int x : arr) {
            curr.next = new ListNode(x);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: print list as [a, b, c]
    private static void printList(ListNode head) {
        System.out.print("[");
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MergeKLists solution = new MergeKLists();

        // Test case 1: [[1,4,5], [1,3,4], [2,6]]
        ListNode[] lists1 = {
            createList(new int[]{1,4,5}),
            createList(new int[]{1,3,4}),
            createList(new int[]{2,6})
        };

        // Test case 2: [] (no lists)
        ListNode[] lists2 = {};

        // Test case 3: [ [] ] (one empty list)
        ListNode[] lists3 = { createList(new int[]{}) };

        // Test case 4: [[-1,5,11], [6,10], [2,3,7,12]]
        ListNode[] lists4 = {
            createList(new int[]{-1,5,11}),
            createList(new int[]{6,10}),
            createList(new int[]{2,3,7,12})
        };

        ListNode[][] allTests = { lists1, lists2, lists3, lists4 };

        for (int i = 0; i < allTests.length; i++) {
            System.out.println("=== Test " + (i+1) + " ===");
            ListNode[] lists = allTests[i];
            System.out.print("Input lists: ");
            if (lists.length == 0) {
                System.out.println("[]");
            } else {
                System.out.print("[ ");
                for (ListNode l : lists) {
                    printList(l);
                }
                System.out.println(" ]");
            }

            ListNode merged = solution.mergeKLists(lists);
            System.out.print("Merged: ");
            printList(merged);
            System.out.println();
        }
    }
}
