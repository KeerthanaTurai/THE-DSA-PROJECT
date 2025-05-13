import java.util.*;

/**
 * Problem 199. Binary Tree Right Side View
 *
 * Given the root of a binary tree, imagine yourself standing on the right side
 * of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example 1:
 *   Input: root = [1,2,3,null,5,null,4]
 *   Output: [1,3,4]
 *
 * Example 2:
 *   Input: root = []
 *   Output: []
 *
 * Example 3:
 *   Input: root = [1]
 *   Output: [1]
 *
 * Intuition:
 *   A right-side view shows the last node at each level when traversed from
 *   left to right. A breadth-first (level-order) traversal with a queue lets
 *   us process each level in order and pick the rightmost node.
 *
 * Approach:
 *   1. If root is null, return an empty list.
 *   2. Use a deque `queue` and enqueue the root.
 *   3. While `queue` is not empty:
 *        a. Let `level = queue.size()`.
 *        b. The rightmost node of this level is `queue.peekLast()`: add its
 *           value to `res`.
 *        c. For i in [0 .. level-1]:
 *             - `temp = queue.poll()`
 *             - If `temp.left != null`, `queue.offer(temp.left)`
 *             - If `temp.right != null`, `queue.offer(temp.right)`
 *   4. Return `res`.
 *
 * Time Complexity: O(n), each node is enqueued and dequeued exactly once.
 * Space Complexity: O(w), where w is the maximum width of the tree (size of the queue).
 */
public class RightSideView {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int level = queue.size();
            // The rightmost node is at the back of the deque
            res.add(queue.peekLast().val);
            for (int i = 0; i < level; i++) {
                TreeNode temp = queue.poll();
                if (temp.left  != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
        }

        return res;
    }

    // Helper to build a tree from an array (using nulls to indicate missing children)
    private static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int idx = 1;
        while (idx < arr.length) {
            TreeNode node = q.poll();
            if (arr[idx] != null) {
                node.left = new TreeNode(arr[idx]);
                q.offer(node.left);
            }
            idx++;
            if (idx < arr.length && arr[idx] != null) {
                node.right = new TreeNode(arr[idx]);
                q.offer(node.right);
            }
            idx++;
        }
        return root;
    }

    public static void main(String[] args) {
        RightSideView sol = new RightSideView();

        // Test 1: Empty tree
        TreeNode t1 = buildTree(new Integer[]{});
        System.out.println("Test 1 (empty): " + sol.rightSideView(t1));  // []

        // Test 2: Single node
        TreeNode t2 = buildTree(new Integer[]{1});
        System.out.println("Test 2 (single): " + sol.rightSideView(t2)); // [1]

        // Test 3: Right-skewed tree
        TreeNode t3 = buildTree(new Integer[]{1, null, 2, null, 3});
        System.out.println("Test 3 (right-skew): " + sol.rightSideView(t3)); // [1,2,3]

        // Test 4: Left-skewed tree
        TreeNode t4 = buildTree(new Integer[]{1, 2, null, 3});
        System.out.println("Test 4 (left-skew): " + sol.rightSideView(t4)); // [1,2,3]

        // Test 5: Balanced tree
        TreeNode t5 = buildTree(new Integer[]{1,2,3,4,5,6,7});
        System.out.println("Test 5 (balanced): " + sol.rightSideView(t5)); // [1,3,7]

        // Test 6: Mixed sparse tree
        TreeNode t6 = buildTree(new Integer[]{1,2,3,null,5,null,4});
        System.out.println("Test 6 (mixed): " + sol.rightSideView(t6)); // [1,3,4]
    }
}
