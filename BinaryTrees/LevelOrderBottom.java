import java.util.*;

/**
 * Problem 107. Binary Tree Level Order Traversal II
 *
 * Given the root of a binary tree, return the bottom-up level order traversal
 * of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 *
 * Example:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[15,7],[9,20],[3]]
 *
 * Intuition:
 *   A standard level order traversal (BFS) visits nodes from top to bottom.
 *   By inserting each level's list at the front of the result, we reverse
 *   the order to get bottom-up traversal without an extra pass.
 *
 * Approach:
 *   1. If root is null, return an empty list.
 *   2. Initialize a queue and enqueue the root.
 *   3. While the queue is not empty:
 *        a. Let levelSize = queue.size(), create a new list for this level.
 *        b. For i from 0 to levelSize-1:
 *             - Dequeue a node, add its value to the level list.
 *             - Enqueue its non-null left and right children.
 *        c. Insert the level list at index 0 of the result.
 *   4. Return the result list.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is enqueued and dequeued once).
 * Space Complexity: O(n), for the queue and the result storage.
 */
public class LevelOrderBottom {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelList = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // Insert each level at the front for bottom-up order
            res.add(0, levelList);
        }

        return res;
    }

    // Helper to print a 2D list
    private static <T> void print2DList(List<List<T>> list) {
        System.out.println(list);
    }

    // Construct various test trees and run edge-case tests
    public static void main(String[] args) {
        LevelOrderBottom solver = new LevelOrderBottom();

        // Test 1: Empty tree
        System.out.print("Test 1 (empty): ");
        print2DList(solver.levelOrderBottom(null));  // []

        // Test 2: Single node
        TreeNode single = new TreeNode(42);
        System.out.print("Test 2 (single): ");
        print2DList(solver.levelOrderBottom(single));  // [[42]]

        // Test 3: Left-skewed tree
        TreeNode leftSkew = new TreeNode(1);
        leftSkew.left = new TreeNode(2);
        leftSkew.left.left = new TreeNode(3);
        System.out.print("Test 3 (left-skew): ");
        print2DList(solver.levelOrderBottom(leftSkew));  // [[3],[2],[1]]

        // Test 4: Right-skewed tree
        TreeNode rightSkew = new TreeNode(1);
        rightSkew.right = new TreeNode(2);
        rightSkew.right.right = new TreeNode(3);
        System.out.print("Test 4 (right-skew): ");
        print2DList(solver.levelOrderBottom(rightSkew));  // [[3],[2],[1]]

        // Test 5: Perfect binary tree
        TreeNode perfect = new TreeNode(4);
        perfect.left  = new TreeNode(2);
        perfect.right = new TreeNode(6);
        perfect.left.left  = new TreeNode(1);
        perfect.left.right = new TreeNode(3);
        perfect.right.left  = new TreeNode(5);
        perfect.right.right = new TreeNode(7);
        System.out.print("Test 5 (perfect): ");
        print2DList(solver.levelOrderBottom(perfect));  // [[1,3,5,7],[2,6],[4]]

        // Test 6: Mixed tree
        TreeNode mixed = new TreeNode(3);
        mixed.left  = new TreeNode(9);
        mixed.right = new TreeNode(20);
        mixed.right.left  = new TreeNode(15);
        mixed.right.right = new TreeNode(7);
        System.out.print("Test 6 (mixed): ");
        print2DList(solver.levelOrderBottom(mixed));  // [[15,7],[9,20],[3]]
    }
}
