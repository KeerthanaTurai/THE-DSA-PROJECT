/**
 * Problem 993. Cousins in Binary Tree
 *
 * Given the root of a binary tree with unique values and the values of two different
 * nodes of the tree x and y, return true if the nodes corresponding to the values
 * x and y in the tree are cousins, or false otherwise.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Note that in a binary tree, the root node is at the depth 0, and children of
 * each depth k node are at depth k + 1.
 *
 * Example 1:
 *   Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 *   Output: true
 *
 * Example 2:
 *   Input: root = [1,2,3,4], x = 4, y = 3
 *   Output: false
 *
 * Example 3:
 *   Input: root = [1,2,3,null,4], x = 2, y = 3
 *   Output: false
 *
 * Intuition:
 *   We need to check that x and y are at the same depth but have different parents.
 *
 * Approach:
 *   1. Use find(root, val) to locate the TreeNode references xnode and ynode.
 *   2. Use level(root, xnode, 0) to compute the depth of xnode, and similarly for ynode.
 *   3. Use isSibiling(root, xnode, ynode) to verify they do not share the same parent.
 *   4. Return true if depths are equal and they’re not siblings.
 *
 * Time Complexity: O(n) — each helper (find, level, isSibiling) does an O(n) traversal.
 * Space Complexity: O(n) — recursion stack in worst-case skewed tree.
 */
public class CousinsInBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode xnode = find(root, x);
        TreeNode ynode = find(root, y);
        return (level(root, xnode, 0) == level(root, ynode, 0))
            && !isSibiling(root, xnode, ynode);
    }

    // Find the node with value x in the tree.
    public TreeNode find(TreeNode node, int x) {
        if (node == null) return null;
        if (node.val == x) return node;
        TreeNode left = find(node.left, x);
        if (left != null) return left;
        return find(node.right, x);
    }

    // Compute the depth of node2 starting from node1 at depth l.
    public int level(TreeNode node1, TreeNode node2, int l) {
        if (node1 == node2) return l;
        if (node1 == null) return 0;
        int left = level(node1.left, node2, l + 1);
        if (left != 0) return left;
        return level(node1.right, node2, l + 1);
    }

    // Check if xnode and ynode share the same parent.
    public boolean isSibiling(TreeNode node, TreeNode xnode, TreeNode ynode) {
        if (node == null) return false;
        if ((node.left == xnode && node.right == ynode)
         || (node.left == ynode && node.right == xnode)) {
            return true;
        }
        return isSibiling(node.left, xnode, ynode)
            || isSibiling(node.right, xnode, ynode);
    }

    // Helper to build a binary tree from level-order array (null for missing).
    private static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
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
        CousinsInBinaryTree sol = new CousinsInBinaryTree();

        // Test 1: empty tree
        System.out.println("Test 1 (empty): " +
            sol.isCousins(null, 1, 2)); // false

        // Test 2: single node
        TreeNode single = buildTree(new Integer[]{1});
        System.out.println("Test 2 (single): " +
            sol.isCousins(single, 1, 2)); // false

        // Test 3: siblings (should be false)
        //     1
        //    / \
        //   2   3
        TreeNode sibs = buildTree(new Integer[]{1,2,3});
        System.out.println("Test 3 (siblings): " +
            sol.isCousins(sibs, 2, 3)); // false

        // Test 4: cousins (should be true)
        //      1
        //     / \
        //    2   3
        //     \   \
        //      4   5
        TreeNode cousins = buildTree(
            new Integer[]{1,2,3,null,4,null,5}
        );
        System.out.println("Test 4 (cousins): " +
            sol.isCousins(cousins, 5, 4)); // true

        // Test 5: nodes on different depths (should be false)
        //      1
        //     / \
        //    2   3
        //   /
        //  4
        TreeNode diffDepth = buildTree(
            new Integer[]{1,2,3,4}
        );
        System.out.println("Test 5 (diff depth): " +
            sol.isCousins(diffDepth, 4, 3)); // false

        // Test 6: more complex tree
        Integer[] arr = {1,2,3,4,null,null,5,6,null,null,7};
        /*
         *        1
         *       / \
         *      2   3
         *     /     \
         *    4       5
         *   /         \
         *  6           7
         */
        TreeNode complex = buildTree(arr);
        System.out.println("Test 6 (complex): " +
            sol.isCousins(complex, 6, 7)); // true
    }
}
