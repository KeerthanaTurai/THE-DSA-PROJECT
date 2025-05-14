import java.util.*;

/**
 * Problem 108. Convert Sorted Array to Binary Search Tree
 *
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 *
 * Example 1:
 *   Input: nums = [-10,-3,0,5,9]
 *   Output: [0,-3,9,-10,null,5]
 *
 * Example 2:
 *   Input: nums = [1,3]
 *   Output: [3,1]
 *
 * Intuition:
 *   A height-balanced BST is one where the depths of the two subtrees of
 *   every node never differ by more than 1. By always choosing the middle
 *   element of the (sub)array as the root, we ensure the left and right
 *   subtrees are as evenly sized as possible.
 *
 * Approach:
 *   - Recurse on the subarray [start..end]:
 *     1. If start > end, return null.
 *     2. Let mid = (start + end) / 2.
 *     3. Create a node with value nums[mid].
 *     4. node.left  = recurse(start, mid - 1).
 *     5. node.right = recurse(mid + 1, end).
 *     6. Return node.
 *   - The initial call is recurse(0, nums.length - 1).
 *
 * Time Complexity: O(n), each element is visited once.
 * Space Complexity: O(log n), recursion stack height for a balanced tree.
 */
public class ConvertSortedArrayToBST {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /** Public API: converts the entire sorted array to a BST. */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    /** Helper that builds BST from nums[start..end]. */
    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left  = sortedArrayToBST(nums, start, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, end);
        return node;
    }

    /** Helper: returns level-order traversal as list (nulls omitted at end). */
    private static List<Integer> toLevelOrderList(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        if (root == null) return out;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                out.add(node.val);
                q.offer(node.left);
                q.offer(node.right);
            } else {
                out.add(null);
            }
        }
        // Trim trailing nulls
        int i = out.size() - 1;
        while (i >= 0 && out.get(i) == null) {
            out.remove(i--);
        }
        return out;
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBST sol = new ConvertSortedArrayToBST();

        // Test 1: Empty array
        int[] a1 = {};
        TreeNode t1 = sol.sortedArrayToBST(a1);
        System.out.println("Test 1 (empty): " + toLevelOrderList(t1)); // []

        // Test 2: Single element
        int[] a2 = {42};
        TreeNode t2 = sol.sortedArrayToBST(a2);
        System.out.println("Test 2 (single): " + toLevelOrderList(t2)); // [42]

        // Test 3: Two elements
        int[] a3 = {1, 3};
        TreeNode t3 = sol.sortedArrayToBST(a3);
        System.out.println("Test 3 (two): " + toLevelOrderList(t3)); // [1 or 3 root]

        // Test 4: Odd number of elements
        int[] a4 = {-10, -3, 0, 5, 9};
        TreeNode t4 = sol.sortedArrayToBST(a4);
        System.out.println("Test 4 (odd count): " + toLevelOrderList(t4));
        // Expect root=0 at center

        // Test 5: Even number of elements
        int[] a5 = {0,1,2,3,4,5};
        TreeNode t5 = sol.sortedArrayToBST(a5);
        System.out.println("Test 5 (even count): " + toLevelOrderList(t5));

        // Test 6: Larger sorted array
        int[] a6 = new int[15];
        for (int i = 0; i < 15; i++) a6[i] = i + 1;
        TreeNode t6 = sol.sortedArrayToBST(a6);
        System.out.println("Test 6 (1..15): " + toLevelOrderList(t6));
        // Should produce a complete tree of height 4
    }
}
