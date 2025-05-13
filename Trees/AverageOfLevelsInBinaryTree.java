import java.util.*;

/**
 * Problem 637. Average of Levels in Binary Tree
 *
 * Given the root of a binary tree, return the average value of the nodes
 * on each level in the form of an array. Answers within 10⁻⁵ of the actual
 * answer will be accepted.
 *
 * Intuition:
 *   A breadth-first search (BFS) naturally visits nodes level by level.
 *   While traversing each level, we can accumulate the sum of node values
 *   and then divide by the number of nodes on that level to get the average.
 *
 * Approach:
 *   1. If root is null, return an empty list.
 *   2. Initialize a queue and enqueue the root.
 *   3. While the queue is not empty:
 *        a. Let level = queue.size(), and sum = 0.0.
 *        b. For i from 0 to level-1:
 *             - Dequeue a node, add its value to sum.
 *             - Enqueue its left and right children if non-null.
 *        c. Compute average = sum / level, append to result list.
 *   4. Return the list of averages.
 *
 * Time Complexity: O(n), where n is the number of nodes, since each node is visited once.
 * Space Complexity: O(w), where w is the maximum width of the tree (maximum queue size).
 */
public class AverageOfLevelsInBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int level = queue.size();
            double sum = 0;

            for (int i = 0; i < level; i++) {
                TreeNode temp = queue.poll();
                sum += temp.val;
                if (temp.left  != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }

            res.add(sum / level);
        }

        return res;
    }

    // Main with comprehensive edge-case testing
    public static void main(String[] args) {
        AverageOfLevelsInBinaryTree sol = new AverageOfLevelsInBinaryTree();

        // Test 1: Empty tree
        System.out.println("Test 1 (empty): " + sol.averageOfLevels(null));

        // Test 2: Single node
        TreeNode single = new TreeNode(42);
        System.out.println("Test 2 (single): " + sol.averageOfLevels(single));

        // Test 3: Left-skewed tree
        TreeNode leftSkew = new TreeNode(1);
        leftSkew.left = new TreeNode(2);
        leftSkew.left.left = new TreeNode(3);
        System.out.println("Test 3 (left-skew): " + sol.averageOfLevels(leftSkew));

        // Test 4: Right-skewed tree
        TreeNode rightSkew = new TreeNode(1);
        rightSkew.right = new TreeNode(2);
        rightSkew.right.right = new TreeNode(3);
        System.out.println("Test 4 (right-skew): " + sol.averageOfLevels(rightSkew));

        // Test 5: Perfect binary tree
        TreeNode perfect = new TreeNode(4);
        perfect.left = new TreeNode(2);
        perfect.right = new TreeNode(6);
        perfect.left.left = new TreeNode(1);
        perfect.left.right = new TreeNode(3);
        perfect.right.left = new TreeNode(5);
        perfect.right.right = new TreeNode(7);
        System.out.println("Test 5 (perfect): " + sol.averageOfLevels(perfect));

        // Test 6: Mixed tree
        TreeNode mixed = new TreeNode(3);
        mixed.left  = new TreeNode(9);
        mixed.right = new TreeNode(20);
        mixed.right.left  = new TreeNode(15);
        mixed.right.right = new TreeNode(7);
        System.out.println("Test 6 (mixed): " + sol.averageOfLevels(mixed));
    }
}
