import java.util.*;

/**
 * Problem 103. Binary Tree Zigzag Level Order Traversal
 *
 * Given the root of a binary tree, return the zigzag level order traversal
 * of its nodes' values. (i.e., from left to right, then right to left for
 * the next level and alternate between).
 *
 * Example:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[3],[20,9],[15,7]]
 *
 * Intuition:
 *   A breadth-first traversal visits nodes level by level. To zigzag, we
 *   reverse the direction every other level. Using a deque lets us both
 *   poll from front/back and offer to front/back efficiently.
 *
 * Approach:
 *   1. If root is null, return an empty list.
 *   2. Initialize a Deque<TreeNode> queue and offer the root.
 *   3. Maintain a boolean zigzag = false.
 *   4. While queue is not empty:
 *        a. Let level = queue.size(), create a new List<Integer> levelList.
 *        b. For i in [0, level):
 *             - If zigzag:
 *                 • Poll from back, add its value to levelList.
 *                 • Offer its right child then left child to front.
 *             - Else:
 *                 • Poll from front, add its value to levelList.
 *                 • Offer its left child then right child to back.
 *        c. Add levelList to result and flip zigzag.
 *   5. Return result.
 *
 * Time Complexity: O(n), each node is visited once.
 * Space Complexity: O(n), for the deque and result lists.
 */
public class ZigzagLevelOrderTraversal {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean zigzag = false;

        while (!queue.isEmpty()) {
            int level = queue.size();
            List<Integer> levelList = new ArrayList<>(level);

            for (int i = 0; i < level; i++) {
                if (zigzag) {
                    TreeNode temp = queue.pollLast();
                    levelList.add(temp.val);
                    if (temp.right != null) queue.offerFirst(temp.right);
                    if (temp.left  != null) queue.offerFirst(temp.left);
                } else {
                    TreeNode temp = queue.poll();
                    levelList.add(temp.val);
                    if (temp.left  != null) queue.offer(temp.left);
                    if (temp.right != null) queue.offer(temp.right);
                }
            }

            res.add(levelList);
            zigzag = !zigzag;
        }

        return res;
    }

    // Helper to print a list of lists
    private static <T> void print2DList(List<List<T>> list) {
        System.out.println(list);
    }

    // Main with edge-case testing
    public static void main(String[] args) {
        ZigzagLevelOrderTraversal sol = new ZigzagLevelOrderTraversal();

        // Test 1: Empty tree
        System.out.print("Test 1 (empty): ");
        print2DList(sol.zigzagLevelOrder(null));  // []

        // Test 2: Single node
        TreeNode single = new TreeNode(1);
        System.out.print("Test 2 (single): ");
        print2DList(sol.zigzagLevelOrder(single));  // [[1]]

        // Test 3: Perfect binary tree
        TreeNode perfect = new TreeNode(1);
        perfect.left  = new TreeNode(2);
        perfect.right = new TreeNode(3);
        perfect.left.left  = new TreeNode(4);
        perfect.left.right = new TreeNode(5);
        perfect.right.left  = new TreeNode(6);
        perfect.right.right = new TreeNode(7);
        System.out.print("Test 3 (perfect): ");
        print2DList(sol.zigzagLevelOrder(perfect));  // [[1],[3,2],[4,5,6,7]]

        // Test 4: Left-skewed tree
        TreeNode leftSkew = new TreeNode(10);
        leftSkew.left = new TreeNode(20);
        leftSkew.left.left = new TreeNode(30);
        System.out.print("Test 4 (left-skew): ");
        print2DList(sol.zigzagLevelOrder(leftSkew));  // [[10],[20],[30]]

        // Test 5: Right-skewed tree
        TreeNode rightSkew = new TreeNode(10);
        rightSkew.right = new TreeNode(20);
        rightSkew.right.right = new TreeNode(30);
        System.out.print("Test 5 (right-skew): ");
        print2DList(sol.zigzagLevelOrder(rightSkew));  // [[10],[20],[30]]

        // Test 6: Mixed tree
        TreeNode mixed = new TreeNode(3);
        mixed.left  = new TreeNode(9);
        mixed.right = new TreeNode(20);
        mixed.right.left  = new TreeNode(15);
        mixed.right.right = new TreeNode(7);
        System.out.print("Test 6 (mixed): ");
        print2DList(sol.zigzagLevelOrder(mixed));  // [[3],[20,9],[15,7]]
    }
}
