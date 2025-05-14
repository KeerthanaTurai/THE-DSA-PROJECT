import java.util.*;

/**
 * Problem 230. Kth Smallest Element in a BST
 *
 * Given the root of a binary search tree, and an integer k, return the kth
 * smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 *   Input: root = [3,1,4,null,2], k = 1
 *   Output: 1
 *
 * Example 2:
 *   Input: root = [5,3,6,2,4,null,null,1], k = 3
 *   Output: 3
 *
 * Intuition:
 *   In-order traversal of a BST visits nodes in ascending order. By counting
 *   nodes as we traverse, we can stop when we've seen k nodes.
 *
 * Approach:
 *   - Use an instance variable `count` to track how many nodes we've visited.
 *   - Recursively traverse:
 *       1. Traverse left subtree; if it returns a valid value, propagate it.
 *       2. Increment `count`. If `count == k`, return current node's value.
 *       3. Traverse right subtree; propagate any valid result.
 *   - Use -1 as a sentinel for "not found".
 *
 * Time Complexity: O(h + k) on average, where h is tree height; worst-case O(n).
 * Space Complexity: O(h) for recursion stack.
 */
public class KthSmallestInBST {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    private int count = 0;

    /** Returns the kth smallest value in the BST, or -1 if not found. */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        // Search left subtree
        int left = kthSmallest(root.left, k);
        if (left != -1) return left;
        // Visit current
        count++;
        if (count == k) return root.val;
        // Search right subtree
        return kthSmallest(root.right, k);
    }

    /** Builds a binary tree from level-order array (null for missing nodes). */
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
        // Helper to run a test
        class Test {
            Integer[] arr;
            int k;
            Test(Integer[] arr, int k) { this.arr = arr; this.k = k; }
        }
        List<Test> tests = Arrays.asList(
            new Test(new Integer[]{3,1,4,null,2}, 1),     // expect 1
            new Test(new Integer[]{5,3,6,2,4,null,null,1}, 3), // expect 3
            new Test(new Integer[]{}, 1),                 // empty tree, expect -1
            new Test(new Integer[]{42}, 1),               // single node, expect 42
            new Test(new Integer[]{2,1,3}, 3),            // k equals size, expect 3
            new Test(new Integer[]{2,1,3}, 4),            // k > size, expect -1
            new Test(new Integer[]{1,null,2,null,3}, 2)   // right-skewed, expect 2
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            TreeNode root = buildTree(t.arr);
            KthSmallestInBST solver = new KthSmallestInBST();
            int result = solver.kthSmallest(root, t.k);
            System.out.printf("Test %d: tree=%s, k=%d → %d%n",
                              i+1, Arrays.toString(t.arr), t.k, result);
        }
    }
}
