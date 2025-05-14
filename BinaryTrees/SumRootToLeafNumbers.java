import java.util.*;

/**
 * Problem 129. Sum Root to Leaf Numbers
 *
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 *
 * A leaf is a node with no children.
 *
 * Example 1:
 *   Input: root = [1,2,3]
 *   Output: 25
 *   Explanation: The root-to-leaf paths are 1->2 = 12 and 1->3 = 13, so 12 + 13 = 25.
 *
 * Example 2:
 *   Input: root = [4,9,0,5,1]
 *   Output: 1026
 *   Explanation: The paths are 4->9->5 = 495, 4->9->1 = 491, and 4->0 = 40, summing to 1026.
 *
 * Intuition:
 *   Accumulate the numerical value along each path by carrying a running sum
 *   down the recursion. At each node, multiply the running sum by 10 and add
 *   the node’s digit. When a leaf is reached, that running sum is one full
 *   root-to-leaf number.
 *
 * Approach:
 *   - Public method calls helper with initial sum 0.
 *   - Helper sumNumbers(node, curSum):
 *       1. If node is null, return 0.
 *       2. Compute newSum = curSum * 10 + node.val.
 *       3. If node is a leaf, return newSum.
 *       4. Otherwise, return sumNumbers(node.left, newSum)
 *                         + sumNumbers(node.right, newSum).
 *
 * Time Complexity: O(n), where n is the number of nodes (each visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class SumRootToLeafNumbers {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // Public API
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    // Helper that returns the total sum of all root-to-leaf numbers
    private int sumNumbers(TreeNode node, int curSum) {
        if (node == null) return 0;
        int newSum = curSum * 10 + node.val;
        // If leaf, return the number formed
        if (node.left == null && node.right == null) {
            return newSum;
        }
        // Otherwise sum from left and right subtrees
        return sumNumbers(node.left, newSum)
             + sumNumbers(node.right, newSum);
    }

    // Builds a binary tree from level-order array (null for missing nodes)
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

    // Main with comprehensive edge-case tests
    public static void main(String[] args) {
        SumRootToLeafNumbers solver = new SumRootToLeafNumbers();

        class Test { Integer[] tree; int target; Test(Integer[] t){ tree = t; } }

        List<Test> tests = List.of(
            new Test(new Integer[]{            }),      // empty
            new Test(new Integer[]{0          }),      // single zero
            new Test(new Integer[]{1          }),      // single non-zero
            new Test(new Integer[]{1,2,3      }),      // two leaves
            new Test(new Integer[]{4,9,0,5,1  }),      // example 2
            new Test(new Integer[]{1,null,2,null,3}),  // right-skewed
            new Test(new Integer[]{1,0,1      })       // two leaves 10 and 11
        );

        for (int i = 0; i < tests.size(); i++) {
            Integer[] treeArr = tests.get(i).tree;
            TreeNode root = buildTree(treeArr);
            int result = solver.sumNumbers(root);
            System.out.printf("Test %d: tree=%s → sum = %d%n",
                i+1, Arrays.toString(treeArr), result);
        }
    }
}
