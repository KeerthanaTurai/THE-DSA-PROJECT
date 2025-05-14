import java.util.*;

/**
 * Problem 104. Maximum Depth of Binary Tree
 *
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: 3
 *
 * Example 2:
 *   Input: root = [1,null,2]
 *   Output: 2
 *
 * Example 3:
 *   Input: root = []
 *   Output: 0
 *
 * Intuition:
 *   The maximum depth of a tree is one plus the greater of the maximum depths
 *   of its left and right subtrees. An empty tree has depth 0.
 *
 * Approach:
 *   1. If root is null, return 0.
 *   2. Recursively compute leftDepth = maxDepth(root.left).
 *   3. Recursively compute rightDepth = maxDepth(root.right).
 *   4. Return max(leftDepth, rightDepth) + 1.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class MaxDepthBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /** Returns the maximum depth of the binary tree. */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /** Helper to build a tree from a level-order array (null for missing nodes). */
    private static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode node = queue.poll();
            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        MaxDepthBinaryTree sol = new MaxDepthBinaryTree();

        // Test 1: Empty tree
        TreeNode t1 = buildTree(new Integer[]{});
        System.out.println("Test 1 (empty): " + sol.maxDepth(t1)); // 0

        // Test 2: Single node
        TreeNode t2 = buildTree(new Integer[]{42});
        System.out.println("Test 2 (single): " + sol.maxDepth(t2)); // 1

        // Test 3: Left-skewed tree [1,2,null,3]
        TreeNode t3 = buildTree(new Integer[]{1,2,null,3});
        System.out.println("Test 3 (left-skew): " + sol.maxDepth(t3)); // 3

        // Test 4: Right-skewed tree [1,null,2,null,3]
        TreeNode t4 = buildTree(new Integer[]{1,null,2,null,3});
        System.out.println("Test 4 (right-skew): " + sol.maxDepth(t4)); // 3

        // Test 5: Balanced tree [3,9,20,null,null,15,7]
        TreeNode t5 = buildTree(new Integer[]{3,9,20,null,null,15,7});
        System.out.println("Test 5 (balanced): " + sol.maxDepth(t5)); // 3

        // Test 6: More complex tree [1,2,3,4,5,null,6,null,null,7]
        TreeNode t6 = buildTree(new Integer[]{
            1,2,3,4,5,null,6,null,null,7
        });
        // Structure:
        //        1
        //      /   \
        //     2     3
        //    / \     \
        //   4   5     6
        //      /
        //     7
        System.out.println("Test 6 (complex): " + sol.maxDepth(t6)); // 4
    }
}
