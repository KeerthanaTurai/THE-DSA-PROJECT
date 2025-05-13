/**
 * Problem 116. Populating Next Right Pointers in Each Node
 *
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The tree node is defined as:
 *
 *   struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *next;
 *   }
 *
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL. Initially, all
 * next pointers are set to NULL.
 *
 * Intuition:
 *   Since the tree is perfect, every level is completely filled. We can link
 *   level L+1 using only pointers on level L without extra data structures.
 *   For each node on level L, its left.next = its right, and its right.next
 *   = node.next.left (if node.next exists).
 *
 * Approach:
 *   1. Start at the root; this is the leftmost node of level 0.
 *   2. While leftmost.left != null (i.e., there is another level below):
 *        a. Let current = leftmost.
 *        b. While current != null:
 *             - current.left.next = current.right
 *             - if current.next != null:
 *                   current.right.next = current.next.left
 *             - current = current.next
 *        c. Move down: leftmost = leftmost.left
 *   3. Return the root.
 *
 * Time Complexity: O(n), each node’s pointers are set exactly once.
 * Space Complexity: O(1), we only use existing pointers.
 */
public class PopulatingNextRightPointers {

    // Definition for a perfect binary tree node with next pointer.
    static class Node {
        int val;
        Node left, right, next;
        Node(int x) { val = x; }
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Node leftMost = root;

        // Traverse levels, stopping before the leaf level
        while (leftMost.left != null) {
            Node current = leftMost;
            // Link all nodes in the current level
            while (current != null) {
                // Link left child to right child
                current.left.next = current.right;
                // Link right child to next subtree's left child, if exists
                if (current.next != null) {
                    current.right.next = current.next.left;
                }
                current = current.next;
            }
            // Move down one level
            leftMost = leftMost.left;
        }

        return root;
    }

    /**
     * Helper to print each level's values and next pointers.
     * E.g., "1 -> NULL", "2 -> 3 -> NULL", etc.
     */
    private static void printTreeNext(Node root) {
        Node levelStart = root;
        while (levelStart != null) {
            Node curr = levelStart;
            // Print this level
            while (curr != null) {
                System.out.print(curr.val + " -> ");
                curr = curr.next;
            }
            System.out.println("NULL");
            // Next level
            levelStart = levelStart.left;
        }
    }

    public static void main(String[] args) {
        PopulatingNextRightPointers sol = new PopulatingNextRightPointers();

        // Test 1: empty tree
        System.out.println("Test 1 (empty):");
        System.out.println(sol.connect(null));  // should print "null"

        // Test 2: single node
        Node single = new Node(1);
        sol.connect(single);
        System.out.println("Test 2 (single):");
        printTreeNext(single);  // "1 -> NULL"

        // Test 3: two levels
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        sol.connect(root2);
        System.out.println("Test 3 (two levels):");
        printTreeNext(root2);
        // expects:
        // 1 -> NULL
        // 2 -> 3 -> NULL

        // Test 4: three levels (perfect)
        Node root3 = new Node(1);
        root3.left  = new Node(2);
        root3.right = new Node(3);
        root3.left.left   = new Node(4);
        root3.left.right  = new Node(5);
        root3.right.left  = new Node(6);
        root3.right.right = new Node(7);
        sol.connect(root3);
        System.out.println("Test 4 (three levels):");
        printTreeNext(root3);
        // expects:
        // 1 -> NULL
        // 2 -> 3 -> NULL
        // 4 -> 5 -> 6 -> 7 -> NULL
    }
}
