import java.util.*;

/**
 * Problem 112. Path Sum
 *
 * Given the root of a binary tree and an integer targetSum, return true if the
 * tree has a root-to-leaf path such that adding up all the values along the path
 * equals targetSum. A leaf is a node with no children.
 *
 * Example 1:
 *   Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 *   Output: true
 *
 * Example 2:
 *   Input: root = [1,2,3], targetSum = 5
 *   Output: false
 *
 * Example 3:
 *   Input: root = [], targetSum = 0
 *   Output: false
 *
 * Intuition:
 *   We can subtract the current node's value from the target sum as we descend.
 *   When we reach a leaf, if the remaining targetSum equals the leaf's value, we
 *   have found a valid path.
 *
 * Approach:
 *   - Base case: if root is null, return false (no path).
 *   - If at a leaf (both children null), check if targetSum == root.val.
 *   - Recursively check left subtree with targetSum - root.val.
 *   - If left returns true, short-circuit and return true.
 *   - Otherwise check right subtree with targetSum - root.val.
 *
 * Time Complexity: O(n), each node is visited at most once.
 * Space Complexity: O(h), recursion stack where h is the tree height.
 */
public class PathSum {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        // If leaf, check remaining sum
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // Otherwise recurse down
        if (hasPathSum(root.left, targetSum - root.val)) return true;
        return hasPathSum(root.right, targetSum - root.val);
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
        PathSum sol = new PathSum();

        class Test { Integer[] tree; int target; }
        List<Test> tests = List.of(
            new Test() {{ tree = new Integer[]{};         target = 0;  }},  // empty
            new Test() {{ tree = new Integer[]{5};        target = 5;  }},  // single equals
            new Test() {{ tree = new Integer[]{5};        target = 1;  }},  // single not equals
            new Test() {{ tree = new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1}; target = 22; }},  // example1
            new Test() {{ tree = new Integer[]{1,2,3};    target = 5;  }},  // example2
            new Test() {{ tree = new Integer[]{1,-2,-3,1,3,-2,null,-1}; target = -1; }}  // negative path
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            TreeNode root = buildTree(t.tree);
            boolean result = sol.hasPathSum(root, t.target);
            System.out.printf("Test %d: tree=%s, target=%d → %b%n",
                i+1, Arrays.toString(t.tree), t.target, result);
        }
    }
}
