import java.util.*;

/**
 * Problem 114. Flatten Binary Tree to Linked List
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *   - The "linked list" should use the same TreeNode class where the right
 *     child pointer points to the next node in the list and the left child
 *     pointer is always null.
 *   - The "linked list" should be in the same order as a pre-order traversal
 *     of the binary tree.
 *
 * Example 1:
 *   Input:  [1,2,5,3,4,null,6]
 *   Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 *   Input:  []
 *   Output: []
 *
 * Example 3:
 *   Input:  [0]
 *   Output: [0]
 *
 * Intuition:
 *   You can simulate the process of weaving the left subtree into the right
 *   spine of the tree in-place by finding the rightmost node of the left
 *   subtree (the predecessor) and reconnecting pointers.
 *
 * Approach:
 *   1. Start at current = root.
 *   2. While current != null:
 *        a. If current.left != null:
 *             - Let temp = current.left.
 *             - Find the rightmost node of temp by while(temp.right != null) temp = temp.right.
 *             - Connect temp.right = current.right.
 *             - Move the entire left subtree to the right: current.right = current.left.
 *             - Null out current.left.
 *        b. Move current = current.right.
 *   3. This visits each node a constant number of times and reassigns pointers
 *      in O(1) each.
 *
 * Time Complexity: O(n), each node is visited and its left subtree's rightmost
 *                  link is found once in total across the algorithm.
 * Space Complexity: O(1) extra space (in-place).
 */
public class FlattenBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /** Flattens the tree to a linked list in-place. */
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                // Find the predecessor (rightmost node of left subtree)
                TreeNode temp = current.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                // Rewire pointers
                temp.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    /** Helper to build a tree from level-order array (null for missing). */
    private static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode node = q.poll();
            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    /** Helper to collect the "linked list" in array form: visits right pointers. */
    private static List<Integer> toLinkedList(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            out.add(curr.val);
            if (curr.left != null) {
                throw new IllegalStateException("Left child should be null after flatten");
            }
            curr = curr.right;
        }
        return out;
    }

    public static void main(String[] args) {
        FlattenBinaryTree sol = new FlattenBinaryTree();

        // Test 1: Empty tree
        TreeNode t1 = buildTree(new Integer[]{});
        sol.flatten(t1);
        System.out.println("Test 1 (empty): " + toLinkedList(t1));  // []

        // Test 2: Single node
        TreeNode t2 = buildTree(new Integer[]{1});
        sol.flatten(t2);
        System.out.println("Test 2 (single): " + toLinkedList(t2)); // [1]

        // Test 3: Two-level tree
        TreeNode t3 = buildTree(new Integer[]{1,2,3});
        sol.flatten(t3);
        System.out.println("Test 3 (two-level): " + toLinkedList(t3)); // [1,2,3]

        // Test 4: Example [1,2,5,3,4,null,6]
        TreeNode t4 = buildTree(new Integer[]{1,2,5,3,4,null,6});
        sol.flatten(t4);
        System.out.println("Test 4 (example): " + toLinkedList(t4)); // [1,2,3,4,5,6]

        // Test 5: Left-skewed tree
        TreeNode t5 = buildTree(new Integer[]{1,2,null,3});
        sol.flatten(t5);
        System.out.println("Test 5 (left-skew): " + toLinkedList(t5)); // [1,2,3]

        // Test 6: Right-skewed tree
        TreeNode t6 = buildTree(new Integer[]{1,null,2,null,3});
        sol.flatten(t6);
        System.out.println("Test 6 (right-skew): " + toLinkedList(t6)); // [1,2,3]
    }
}
