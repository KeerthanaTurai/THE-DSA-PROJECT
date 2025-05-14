import java.util.*;

/**
 * Problem 236. Lowest Common Ancestor of a Binary Tree
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given
 * nodes p and q in the tree.
 *
 * According to the definition of LCA: “The lowest common ancestor is defined
 * between two nodes p and q as the lowest node in T that has both p and q as
 * descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *   Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *   Output: 3
 *
 * Example 2:
 *   Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *   Output: 5
 *
 * Example 3:
 *   Input: root = [1,2], p = 1, q = 2
 *   Output: 1
 *
 * Intuition:
 *   Recursively search for p and q in the tree. If the current root matches
 *   p or q, return it up the call stack. Otherwise, combine results from left
 *   and right subtrees: if both sides return non-null, root is LCA; if only
 *   one side returns non-null, propagate that up.
 *
 * Approach:
 *   1. If root is null, return null.
 *   2. If root == p or root == q, return root.
 *   3. Recurse left = LCA(root.left, p, q).
 *   4. Recurse right = LCA(root.right, p, q).
 *   5. If left != null && right != null, return root.
 *   6. Else return left != null ? left : right.
 *
 * Time Complexity: O(n), each node is visited once.
 * Space Complexity: O(h), recursion stack with h = tree height.
 */
public class LowestCommonAncestor {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /** Returns the lowest common ancestor of p and q in the tree rooted at root. */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left  = lowestCommonAncestor(root.left,  p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    /** Builds a binary tree from level-order array (null for missing nodes). */
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

    /** Finds and returns the TreeNode with the given value in the tree. */
    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }

    public static void main(String[] args) {
        LowestCommonAncestor sol = new LowestCommonAncestor();

        // Test 1: Example 1
        TreeNode t1 = buildTree(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode p1 = findNode(t1, 5), q1 = findNode(t1, 1);
        System.out.printf("Test 1 LCA of %d and %d = %d%n",
            p1.val, q1.val, sol.lowestCommonAncestor(t1, p1, q1).val);

        // Test 2: Example 2
        TreeNode t2 = buildTree(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode p2 = findNode(t2, 5), q2 = findNode(t2, 4);
        System.out.printf("Test 2 LCA of %d and %d = %d%n",
            p2.val, q2.val, sol.lowestCommonAncestor(t2, p2, q2).val);

        // Test 3: Example 3
        TreeNode t3 = buildTree(new Integer[]{1,2});
        TreeNode p3 = findNode(t3, 1), q3 = findNode(t3, 2);
        System.out.printf("Test 3 LCA of %d and %d = %d%n",
            p3.val, q3.val, sol.lowestCommonAncestor(t3, p3, q3).val);

        // Test 4: p is ancestor of q
        TreeNode t4 = buildTree(new Integer[]{1,2,3,4,5});
        TreeNode p4 = findNode(t4, 2), q4 = findNode(t4, 5);
        System.out.printf("Test 4 LCA of %d and %d = %d%n",
            p4.val, q4.val, sol.lowestCommonAncestor(t4, p4, q4).val);

        // Test 5: skewed tree
        TreeNode t5 = buildTree(new Integer[]{1,null,2,null,3,null,4});
        TreeNode p5 = findNode(t5, 3), q5 = findNode(t5, 4);
        System.out.printf("Test 5 LCA of %d and %d = %d%n",
            p5.val, q5.val, sol.lowestCommonAncestor(t5, p5, q5).val);

        // Test 6: nodes not present
        TreeNode t6 = buildTree(new Integer[]{1,2,3});
        TreeNode p6 = new TreeNode(99), q6 = findNode(t6, 3);
        TreeNode res6 = sol.lowestCommonAncestor(t6, p6, q6);
        System.out.printf("Test 6 LCA of %d and %d = %s%n",
            99, q6.val, res6 == null ? "null" : String.valueOf(res6.val));
    }
}
