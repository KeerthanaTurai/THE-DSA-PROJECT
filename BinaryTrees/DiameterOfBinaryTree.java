import java.util.*;

/**
 * Problem 543. Diameter of Binary Tree
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter is the length of the longest path between any two nodes,
 * measured in number of edges. The path may or may not pass through the root.
 *
 * Example 1:
 *   Input: root = [1,2,3,4,5]
 *   Output: 3
 *   Explanation: The longest path is [4,2,1,3] or [5,2,1,3], each of length 3 edges.
 *
 * Example 2:
 *   Input: root = []
 *   Output: 0
 *
 * Example 3:
 *   Input: root = [1]
 *   Output: 0
 *
 * Intuition:
 *   At each node, the longest path through that node uses the height of its
 *   left subtree plus the height of its right subtree (each height in edges).
 *   We can compute heights recursively and update a global maximum of left+right.
 *
 * Approach:
 *   - Define a helper `height(node)` that returns the height (max number
 *     of edges from node down to a leaf).
 *   - For each node, compute `left = height(node.left)` and `right = height(node.right)`,
 *     update `diameter = max(diameter, left + right)`.
 *   - Return `max(left, right) + 1` as this node’s height.
 *   - In `diameterOfBinaryTree`, initialize `diameter = 0`, call `height(root)`,
 *     then return `diameter`.
 *
 * Time Complexity: O(n), each node is visited once.
 * Space Complexity: O(h), recursion stack uses O(height of tree).
 */
public class DiameterOfBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    private int diameter;  // max number of edges on any path

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    // Returns height (in edges) of subtree rooted at node
    private int height(TreeNode node) {
        if (node == null) return 0;
        int leftH = height(node.left);
        int rightH = height(node.right);
        // longest path through this node is leftH + rightH
        diameter = Math.max(diameter, leftH + rightH);
        // height is max of subtrees plus 1 edge to child
        return Math.max(leftH, rightH) + 1;
    }

    // Helper to build a tree from a level-order array (null for missing)
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
        DiameterOfBinaryTree sol = new DiameterOfBinaryTree();

        // Test 1: Empty tree
        TreeNode t1 = buildTree(new Integer[]{});
        System.out.println("Test 1 (empty): " + sol.diameterOfBinaryTree(t1)); // 0

        // Test 2: Single node
        TreeNode t2 = buildTree(new Integer[]{1});
        System.out.println("Test 2 (single): " + sol.diameterOfBinaryTree(t2)); // 0

        // Test 3: Two nodes
        TreeNode t3 = buildTree(new Integer[]{1,2});
        System.out.println("Test 3 (two nodes): " + sol.diameterOfBinaryTree(t3)); // 1

        // Test 4: Example [1,2,3,4,5]
        TreeNode t4 = buildTree(new Integer[]{1,2,3,4,5});
        System.out.println("Test 4 (example): " + sol.diameterOfBinaryTree(t4)); // 3

        // Test 5: Unbalanced tree
        TreeNode t5 = buildTree(new Integer[]{1,2,null,3,null,4});
        // Structure: 1-2-3-4 (a chain of length 4 nodes, 3 edges)
        System.out.println("Test 5 (chain): " + sol.diameterOfBinaryTree(t5)); // 3

        // Test 6: Balanced full tree
        TreeNode t6 = buildTree(new Integer[]{1,2,3,4,5,6,7});
        // Height=2 (edges), diameter=4 via leaf 4→2→1→3→7
        System.out.println("Test 6 (perfect): " + sol.diameterOfBinaryTree(t6)); // 4
    }
}
