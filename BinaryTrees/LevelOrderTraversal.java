/**
 * Problem 102. Binary Tree Level Order Traversal
 *
 * Given the root of a binary tree, return the level order traversal of its
 * nodes' values. (i.e., from left to right, level by level).
 *
 * Example:
 *    Input: root = [3,9,20,null,null,15,7]
 *    Output: [[3],[9,20],[15,7]]
 *
 * Intuition:
 *   A breadth-first traversal (BFS) naturally visits nodes level by level.
 *   We can use a queue to hold the frontier of the BFS: enqueue the root,
 *   then repeatedly dequeue all nodes at the current depth, record their
 *   values, and enqueue their non-null children for the next depth.
 *
 * Approach:
 *   1. If root is null, return an empty list.
 *   2. Initialize a queue and enqueue the root.
 *   3. While the queue is not empty:
 *        a. Let levelSize = queue.size(), and create an empty list levelList.
 *        b. For i from 0 to levelSize-1:
 *             • Dequeue a node.
 *             • Add its value to levelList.
 *             • If it has a left child, enqueue it.
 *             • If it has a right child, enqueue it.
 *        c. Add levelList to the result.
 *   4. Return the result.
 *
 * Time Complexity: O(n), where n is the number of nodes, since each node is
 * visited exactly once.
 * Space Complexity: O(w), where w is the maximum width of the tree (the
 * maximum number of nodes at any level), since the queue holds at most that
 * many nodes.
 */
import java.util.*;

public class LevelOrderTraversal {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
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
            res.add(levelList);
        }
        return res;
    }

    // Example usage and simple test
    public static void main(String[] args) {
        LevelOrderTraversal sol = new LevelOrderTraversal();

        // Build the tree [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(7);
        root.right.left  = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = sol.levelOrder(root);
        System.out.println(result);  // should print [[3], [9, 20], [15, 7]]
    }
}
