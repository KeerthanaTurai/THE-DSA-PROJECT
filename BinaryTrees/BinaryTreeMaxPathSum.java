import java.util.*;

/**
 * Problem 124. Binary Tree Maximum Path Sum
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent
 * nodes in the sequence has an edge connecting them. A node can only appear
 * in the sequence at most once. Note that the path does not need to pass
 * through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any
 * non-empty path.
 *
 * Example 1:
 *   Input: root = [1,2,3]
 *   Output: 6
 *   Explanation: The optimal path is 2 -> 1 -> 3 with sum 2 + 1 + 3 = 6.
 *
 * Example 2:
 *   Input: root = [-10,9,20,null,null,15,7]
 *   Output: 42
 *   Explanation: The optimal path is 15 -> 20 -> 7 with sum 15 + 20 + 7 = 42.
 *
 * Intuition:
 *   At each node, the best path that passes through that node and possibly
 *   extends to its parent is the node's value plus the maximum of the best
 *   downward path sums from its left and right children (ignoring negatives).
 *   Meanwhile, the best overall path might go through both children plus the
 *   node itself.
 *
 * Approach:
 *   - Use a global variable `maxSum` to track the highest path sum found.
 *   - Recursively compute for each node the maximum downward path sum:
 *       1. Compute left = maxPath(node.left)
 *       2. Compute right = maxPath(node.right)
 *       3. Clamp negatives: left = max(0, left), right = max(0, right)
 *       4. Update maxSum = max(maxSum, node.val + left + right)
 *       5. Return node.val + max(left, right) to parent.
 *   - The result is in `maxSum` after traversing the tree.
 *
 * Time Complexity: O(n), each node is visited once.
 * Space Complexity: O(h), recursion stack where h is the tree height.
 */
public class BinaryTreeMaxPathSum {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    private int maxSum = Integer.MIN_VALUE;

    /** Returns the maximum path sum in the tree. */
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPath(root);
        return maxSum;
    }

    /** Helper that computes max downward path sum and updates maxSum. */
    private int maxPath(TreeNode node) {
        if (node == null) return 0;
        int left  = maxPath(node.left);
        int right = maxPath(node.right);
        // ignore negative contributions
        left  = Math.max(0, left);
        right = Math.max(0, right);
        // path through this node
        maxSum = Math.max(maxSum, node.val + left + right);
        // return max downward path
        return node.val + Math.max(left, right);
    }

    /** Builds a binary tree from level-order array (null for missing). */
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
        BinaryTreeMaxPathSum solver = new BinaryTreeMaxPathSum();

        // Helper for tests
        class Test { Integer[] tree; int expected; Test(Integer[] t, int e){ tree=t; expected=e;} }

        List<Test> tests = List.of(
            new Test(new Integer[]{1,2,3}, 6),
            new Test(new Integer[]{-10,9,20,null,null,15,7}, 42),
            new Test(new Integer[]{5}, 5),
            new Test(new Integer[]{-3}, -3),
            new Test(new Integer[]{2,-1}, 2),
            new Test(new Integer[]{-2,-1,-3}, -1),
            new Test(new Integer[]{1,-2,-3,1,3,-2,null, -1}, 3)  // path 1->3 or 1->(-2)->3
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            TreeNode root = buildTree(t.tree);
            int result = solver.maxPathSum(root);
            System.out.printf("Test %d: tree=%s → maxPathSum = %d (expected %d)%n",
                              i+1, Arrays.toString(t.tree), result, t.expected);
        }
    }
}
