/**
 * File: DeepCopy.java
 *
 * Problem 138. Copy List with Random Pointer
 *
 * Intuition:
 *   We interleave copied nodes with the original list to easily set both
 *   next and random pointers without extra space for a hash map.
 *
 * Approach:
 *   1. If head is null, return null.
 *   2. **Interleave copies**: For each original node `cur`, create `copy = new Node(cur.val)`,
 *      insert it after `cur` by `copy.next = cur.next; cur.next = copy;`, then advance `cur = copy.next`.
 *   3. **Assign randoms**: Reset `cur = head`. For each original node `cur`,
 *      its copy is `cur.next`. Set `cur.next.random = cur.random != null ? cur.random.next : null`.
 *      Advance `cur = cur.next.next`.
 *   4. **Restore original & extract copy**: Traverse again with `cur = head`, let `copyHead = head.next`.
 *      For each pair, `orig = cur; copy = cur.next; orig.next = copy.next; copy.next = copy.next != null ? copy.next.next : null;`
 *      Advance `cur = orig.next`.
 *   5. Return `copyHead`.
 *
 * Time Complexity: O(n) — we traverse the list a constant number of times.
 * Space Complexity: O(1) extra — we only use pointers and weave nodes in place.
 */
public class DeepCopy {

    static class Node {
        int val;
        Node next;
        Node random;
        Node(int x) { val = x; }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 1. Interleave copies
        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }

        // 2. Assign random pointers
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 3. Restore original list & extract copy
        cur = head;
        Node copyHead = head.next;
        while (cur != null) {
            Node copy = cur.next;
            cur.next = copy.next;
            copy.next = (copy.next != null) ? copy.next.next : null;
            cur = cur.next;
        }

        return copyHead;
    }

    // Helper: Create a list from values and random indices
    // randomIdx[i] is the index of the node that node i's random pointer refers to (or null)
    private static Node createList(int[] vals, Integer[] randomIdx) {
        if (vals.length == 0) return null;
        Node[] nodes = new Node[vals.length];
        for (int i = 0; i < vals.length; i++) {
            nodes[i] = new Node(vals[i]);
        }
        for (int i = 0; i < vals.length; i++) {
            if (i + 1 < vals.length) {
                nodes[i].next = nodes[i + 1];
            }
            if (randomIdx[i] != null) {
                nodes[i].random = nodes[randomIdx[i]];
            }
        }
        return nodes[0];
    }

    // Helper: Print list as [[val, randomIndex], ...]
    private static void printList(Node head) {
        // Gather nodes in array to determine indices
        java.util.List<Node> list = new java.util.ArrayList<>();
        for (Node cur = head; cur != null; cur = cur.next) {
            list.add(cur);
        }
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            Integer randIdx = null;
            if (node.random != null) {
                randIdx = list.indexOf(node.random);
            }
            System.out.print("[" + node.val + "," + (randIdx != null ? randIdx : "null") + "]");
            if (i < list.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        DeepCopy sol = new DeepCopy();

        // Test 1: Example [[7,null],[13,0],[11,4],[10,2],[1,0]]
        int[] vals1 = {7, 13, 11, 10, 1};
        Integer[] rand1 = {null, 0, 4, 2, 0};
        Node head1 = createList(vals1, rand1);
        System.out.print("Original 1: ");
        printList(head1);
        Node copy1 = sol.copyRandomList(head1);
        System.out.print("Copy 1:     ");
        printList(copy1);
        System.out.println();

        // Test 2: Example [[1,1],[2,1]]
        int[] vals2 = {1, 2};
        Integer[] rand2 = {1, 1};
        Node head2 = createList(vals2, rand2);
        System.out.print("Original 2: ");
        printList(head2);
        Node copy2 = sol.copyRandomList(head2);
        System.out.print("Copy 2:     ");
        printList(copy2);
        System.out.println();

        // Test 3: Empty list
        Node head3 = createList(new int[]{}, new Integer[]{});
        System.out.print("Original 3: ");
        printList(head3);
        Node copy3 = sol.copyRandomList(head3);
        System.out.print("Copy 3:     ");
        printList(copy3);
        System.out.println();

        // Test 4: [[3,null],[3,0],[3,null]]
        int[] vals4 = {3, 3, 3};
        Integer[] rand4 = {null, 0, null};
        Node head4 = createList(vals4, rand4);
        System.out.print("Original 4: ");
        printList(head4);
        Node copy4 = sol.copyRandomList(head4);
        System.out.print("Copy 4:     ");
        printList(copy4);
    }
}

