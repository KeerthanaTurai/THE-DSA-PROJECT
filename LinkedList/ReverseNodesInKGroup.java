import java.util.*;

/**
 * Problem 25. Reverse Nodes in k-Group
 *
 * Given the head of a singly linked list, reverse the nodes of the list k at a time,
 * and return the modified list. k is a positive integer and is less than or equal
 * to the length of the linked list. If the number of nodes is not a multiple of k,
 * the remaining nodes at the end remain as is.
 *
 * Intuition:
 *   We process the list in segments of length k. For each segment, we:
 *     1) Find the kth node from the segment start.
 *     2) Temporarily cut the segment out.
 *     3) Reverse it.
 *     4) Reattach it to the previous part of the list and keep track of the next segment.
 *
 * Approach:
 *   - Use pointers prev (tail of the last processed segment) and temp (start of the current segment).
 *   - While temp is not null:
 *       a) Find kthNode = findKthNode(temp, k).
 *       b) If kthNode is null, attach the rest (temp) to prev and break.
 *       c) nextNode = kthNode.next; cut the segment by setting kthNode.next = null.
 *       d) newHead = reverseLinkedList(temp).
 *       e) If we’re at the very beginning, update head = newHead; otherwise prev.next = newHead.
 *       f) prev = temp; temp = nextNode.
 *
 * Time Complexity: O(n^2 / k) in worst case, because for each segment of length k we
 *   do O(k) work to reverse plus O(k) to find the kth node, and there are ~n/k segments.
 *   Overall O(n^2/k + n) = O(n^2/k). For k constant this is O(n^2), but in practice k
 *   is often small. This can be improved to O(n) by reversing in-place without recursion
 *   and caching the tail of each segment, but that is beyond this implementation.
 *
 * Space Complexity: O(n/k) for the recursion stack in reverseLinkedList for each segment,
 *   which is O(k) per segment. Overall O(k) auxiliary space.
 */
public class ReverseNodesInKGroup {
    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * Reverses nodes of the list in groups of k and returns the new head.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode prev = null, temp = head, nextNode, kthNode;
        while (temp != null) {
            kthNode = findKthNode(temp, k);
            if (kthNode == null) {
                // fewer than k nodes remain; attach them as is
                if (prev != null) {
                    prev.next = temp;
                }
                break;
            } else {
                nextNode = kthNode.next;
                kthNode.next = null;               // cut out the segment
                ListNode newHead = reverseLinkedList(temp);
                if (temp == head) {
                    head = newHead;                // first segment
                } else {
                    prev.next = newHead;           // link from previous segment
                }
                prev = temp;                       // temp is now tail of reversed segment
                temp = nextNode;                   // move to next segment
            }
        }
        return head;
    }

    /**
     * Returns the k-th node starting from node (1-based). If fewer than k nodes,
     * returns null.
     */
    public ListNode findKthNode(ListNode node, int k) {
        if (node == null) return null;
        int c = 1;
        while (node != null && c != k) {
            c++;
            node = node.next;
        }
        return (c == k) ? node : null;
    }

    /**
     * Recursively reverses a linked list and returns the new head.
     */
    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = head;
        ListNode newHead = reverseLinkedList(temp.next);
        ListNode front = temp.next;
        front.next = temp;
        temp.next = null;
        return newHead;
    }

    // Helper to build a linked list from an array
    private static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0), curr = dummy;
        for (int v : arr) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper to print a linked list
    private static void printList(ListNode head) {
        List<String> vals = new ArrayList<>();
        while (head != null) {
            vals.add(String.valueOf(head.val));
            head = head.next;
        }
        System.out.println(vals);
    }

    // Main with comprehensive tests
    public static void main(String[] args) {
        ReverseNodesInKGroup solver = new ReverseNodesInKGroup();

        class Test {
            int[] arr;
            int k;
            Test(int[] a, int k) { arr = a; this.k = k; }
        }
        List<Test> tests = Arrays.asList(
            new Test(new int[]{1,2,3,4,5}, 2),   // expect [2,1,4,3,5]
            new Test(new int[]{1,2,3,4,5}, 3),   // expect [3,2,1,4,5]
            new Test(new int[]{1},           1), // expect [1]
            new Test(new int[]{1,2},         3), // k>n, expect [1,2]
            new Test(new int[]{1,2,3},       3), // exact k, expect [3,2,1]
            new Test(new int[]{1,2,3},       1), // k=1, expect [1,2,3]
            new Test(new int[]{},            2)  // empty list, expect []
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            ListNode head = buildList(t.arr);
            System.out.printf("Test %d original: ", i+1);
            printList(head);

            ListNode result = solver.reverseKGroup(head, t.k);
            System.out.printf("       k=%d reversed: ", t.k);
            printList(result);
            System.out.println("------------------------------------------------");
        }
    }
}