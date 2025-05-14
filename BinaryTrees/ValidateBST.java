import java.util.*;

/**
 * Problem 98. Validate Binary Search Tree
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *   1. The left subtree of a node contains only nodes with keys less than the node's key.
 *   2. The right subtree of a node contains only nodes with keys greater than the node's key.
 *   3. Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *   Input: root = [2,1,3]
 *   Output: true
 *
 * Example 2:
 *   Input: root = [5,1,4,null,null,3,6]
 *   Output: false
 *   Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * Intuition:
 *   Each node must lie within an exclusive (low, high) range. As we traverse
 *   the tree, we tighten these bounds: going left enforces a new upper bound,
 *   going right enforces a new lower bound.
 *
 * Approach:
 *   - Recursively validate each subtree with helper validate(node, low, high).
 *   - If node is null, it's valid.
 *   - If node.val ≤ low or node.val ≥ high, invalid.
 *   - Recurse left with (low, node.val) and right with (node.val, high).
 *
 * Time Complexity: O(n) — each node is visited once.
 * Space Complexity: O(h) — recursion stack where h is tree height.
 */
public class ValidateBST {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /** Returns true if the tree is a valid BST. */
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    /**
     * @param node the current subtree root
     * @param low  all nodes in this subtree must be > low (exclusive), or null for no bound
     * @param high all nodes in this subtree must be < high (exclusive), or null for no bound
     */
    private boolean validate(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;
        if (low  != null && node.val <= low)  return false;
        if (high != null && node.val >= high) return false;
        return validate(node.left,  low, node.val)
            && validate(node.right, node.val, high);
    }

    /** Builds a binary tree from level-order array (nulls for missing nodes). */
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

    public static void main(String[] args) {
        ValidateBST sol = new ValidateBST();

        // Test 1: Empty tree
        System.out.println("Test 1 (empty): " +
            sol.isValidBST(null)); // true

        // Test 2: Single node
        TreeNode t2 = buildTree(new Integer[]{1});
        System.out.println("Test 2 (single): " +
            sol.isValidBST(t2)); // true

        // Test 3: Valid BST [2,1,3]
        TreeNode t3 = buildTree(new Integer[]{2,1,3});
        System.out.println("Test 3 (valid): " +
            sol.isValidBST(t3)); // true

        // Test 4: Invalid BST [5,1,4,null,null,3,6]
        TreeNode t4 = buildTree(new Integer[]{5,1,4,null,null,3,6});
        System.out.println("Test 4 (invalid root violation): " +
            sol.isValidBST(t4)); // false

        // Test 5: Subtree violation [10,5,15,null,null,6,20]
        TreeNode t5 = buildTree(new Integer[]{10,5,15,null,null,6,20});
        System.out.println("Test 5 (subtree violation): " +
            sol.isValidBST(t5)); // false

        // Test 6: Larger valid BST
        Integer[] arr6 = {8,3,10,1,6,null,14,null,null,4,7,13};
        TreeNode t6 = buildTree(arr6);
        System.out.println("Test 6 (larger valid): " +
            sol.isValidBST(t6)); // true
    }
}