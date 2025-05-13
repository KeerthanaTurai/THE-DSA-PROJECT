import java.util.*;

/**
 * Problem 101. Symmetric Tree
 *
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 *
 * A binary tree is symmetric if the left subtree is a mirror reflection of
 * the right subtree.
 *
 * Example 1:
 *   Input: root = [1,2,2,3,4,4,3]
 *   Output: true
 *
 * Example 2:
 *   Input: root = [1,2,2,null,3,null,3]
 *   Output: false
 *
 * Intuition:
 *   Two subtrees are mirrors if:
 *     1) Their root values are equal.
 *     2) The left child of one is a mirror of the right child of the other, and vice versa.
 *
 * Approach:
 *   - If the tree is empty, it’s symmetric.
 *   - Otherwise, recursively compare the left and right subtrees using `isMirror`.
 *   - `isMirror(n1, n2)` checks:
 *       • If both nodes are null, true.
 *       • If one is null, false.
 *       • If their values differ, false.
 *       • Recursively: `n1.left` vs `n2.right` AND `n1.right` vs `n2.left`.
 *
 * Time Complexity: O(n), each node is visited once in the recursion.
 * Space Complexity: O(h), where h is the tree height (recursion stack).
 */
public class SymmetricTree {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /** Returns true if the tree is symmetric around its center. */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    /** Helper that checks if two subtrees are mirror images. */
    private boolean isMirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        if (n1.val != n2.val) return false;
        return isMirror(n1.left,  n2.right)
            && isMirror(n1.right, n2.left);
    }

    /** Helper to build a tree from a level-order array (null marks missing). */
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

    /** Main method with comprehensive edge-case tests. */
    public static void main(String[] args) {
        SymmetricTree sol = new SymmetricTree();

        // Test 1: Empty tree
        System.out.println("Test 1 (empty): " + sol.isSymmetric(null));  // true

        // Test 2: Single node
        TreeNode single = new TreeNode(42);
        System.out.println("Test 2 (single): " + sol.isSymmetric(single));  // true

        // Test 3: Perfect symmetric tree [1,2,2,3,4,4,3]
        TreeNode perfect = buildTree(new Integer[]{1,2,2,3,4,4,3});
        System.out.println("Test 3 (perfect): " + sol.isSymmetric(perfect));  // true

        // Test 4: Asymmetric tree [1,2,2,null,3,null,3]
        TreeNode asym = buildTree(new Integer[]{1,2,2,null,3,null,3});
        System.out.println("Test 4 (asymmetric): " + sol.isSymmetric(asym));  // false

        // Test 5: Another asymmetric shape [1,2,2,2,null,2]
        TreeNode asym2 = buildTree(new Integer[]{1,2,2,2,null,2});
        System.out.println("Test 5 (asymmetric 2): " + sol.isSymmetric(asym2));  // false

        // Test 6: Larger symmetric tree
        Integer[] arr = { 5,3,3,1,4,4,1 };
        TreeNode largeSym = buildTree(arr);
        System.out.println("Test 6 (larger sym): " + sol.isSymmetric(largeSym));  // true
    }
}
