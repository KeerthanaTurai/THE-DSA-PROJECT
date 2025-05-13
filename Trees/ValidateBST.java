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
 *
 * Intuition:
 *   Every node must lie within an exclusive range (low, high).  
 *   Initially, the whole tree has no bounds. As we go left, we tighten the high bound
 *   to the current node's value; as we go right, we tighten the low bound.
 *
 * Approach:
 *   - Recursively traverse the tree with a helper `validate(node, low, high)`.
 *   - If node is null, it's valid.
 *   - If node.val ≤ low or node.val ≥ high, return false.
 *   - Recurse left with bounds (low, node.val).
 *   - Recurse right with bounds (node.val, high).
 *   - Return true only if both subtrees are valid.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node visited once).  
 * Space Complexity: O(h), where h is the tree height (recursion stack).
 */
public class ValidateBST {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

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
        int val = node.val;
        if (low  != null && val <= low)  return false;
        if (high != null && val >= high) return false;
        // left subtree must be < val
        if (!validate(node.left,  low, val))  return false;
        // right subtree must be > val
        if (!validate(node.right, val, high)) return false;
        return true;
    }

    // Helper to build simple test trees and run isValidBST
    public static void main(String[] args) {
        ValidateBST solver = new ValidateBST();

        // Example 1: [2,1,3]
        TreeNode ex1 = new TreeNode(2);
        ex1.left  = new TreeNode(1);
        ex1.right = new TreeNode(3);
        System.out.println("Example 1 is valid BST? " + solver.isValidBST(ex1));  // true

        // Example 2: [5,1,4,null,null,3,6]
        TreeNode ex2 = new TreeNode(5);
        ex2.left  = new TreeNode(1);
        ex2.right = new TreeNode(4);
        ex2.right.left  = new TreeNode(3);
        ex2.right.right = new TreeNode(6);
        System.out.println("Example 2 is valid BST? " + solver.isValidBST(ex2));  // false

        // Additional test: [10,5,15,null,null,6,20]
        TreeNode ex3 = new TreeNode(10);
        ex3.left  = new TreeNode(5);
        ex3.right = new TreeNode(15);
        ex3.right.left  = new TreeNode(6);
        ex3.right.right = new TreeNode(20);
        System.out.println("Example 3 is valid BST? " + solver.isValidBST(ex3));  // false

        // Additional test: single node
        TreeNode ex4 = new TreeNode(1);
        System.out.println("Single node is valid BST? " + solver.isValidBST(ex4));  // true

        // Additional test: empty tree
        System.out.println("Empty tree is valid BST? " + solver.isValidBST(null));  // true
    }
}
