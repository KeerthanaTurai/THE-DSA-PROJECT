import java.util.*;

/**
 * Problem 987. Vertical Order Traversal of a Binary Tree
 *
 * Given the root of a binary tree, return its vertical order traversal.
 * For each node at position (row, col):
 *   - left child is at (row+1, col-1)
 *   - right child is at (row+1, col+1)
 * The vertical traversal is a list of columns from leftmost to rightmost;
 * within each column, nodes are ordered first by row, then by value.
 *
 * Intuition:
 *   We need to collect nodes grouped by their column index, then sort
 *   each group by row (ascending) and value (ascending). A DFS or BFS
 *   can record (row, col, value) for every node.
 *
 * Approach:
 *   - Use a Map<Integer, PriorityQueue<int[]>> nodeEntries keyed by column.
 *   - DFS from root at (row=0, col=0):
 *       • For each node, add [row, value] into nodeEntries.get(col).
 *       • Recurse left with (row+1, col-1) and right with (row+1, col+1).
 *   - After traversal, find min and max column keys.
 *   - For each col in [minCol..maxCol]:
 *       • Poll the priority queue to extract values in correct order.
 *       • Append the list of values to the result.
 *
 * Time Complexity: O(N log N) in the worst case (N nodes, each inserted into
 *                  a priority queue keyed by column, and each queue poll is log N).
 * Space Complexity: O(N), for the map and all entries.
 */
public class VerticalOrderTraversal {
    // Map from column -> min-heap of [row, value], ordered by row, then value
    private Map<Integer, PriorityQueue<int[]>> nodeEntries = new HashMap<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // 1) Populate nodeEntries via DFS
        dfs(root, 0, 0);

        // 2) Determine column range
        int minCol = Collections.min(nodeEntries.keySet());
        int maxCol = Collections.max(nodeEntries.keySet());

        // 3) Build result from leftmost to rightmost column
        List<List<Integer>> output = new ArrayList<>();
        for (int col = minCol; col <= maxCol; col++) {
            PriorityQueue<int[]> pq = nodeEntries.getOrDefault(col, new PriorityQueue<>());
            List<Integer> colList = new ArrayList<>();
            while (!pq.isEmpty()) {
                colList.add(pq.poll()[1]);
            }
            output.add(colList);
        }
        return output;
    }

    private void dfs(TreeNode node, int col, int row) {
        if (node == null) return;

        // Initialize priority queue for this column if needed
        nodeEntries.putIfAbsent(col, new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
        ));
        // Add this node's (row, value)
        nodeEntries.get(col).add(new int[]{row, node.val});

        // Recurse left and right
        dfs(node.left, col - 1, row + 1);
        dfs(node.right, col + 1, row + 1);
    }

    // ------------------ Helper Code for Testing ------------------

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // Builds a binary tree from a level-order array (null for missing nodes).
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

    // Prints the vertical traversal for display
    private static void printResult(List<List<Integer>> res) {
        System.out.println(res);
    }

    // Main method with test cases from the prompt
    public static void main(String[] args) {
        VerticalOrderTraversal solver = new VerticalOrderTraversal();

        // Example 1
        TreeNode ex1 = buildTree(new Integer[]{3,9,20,null,null,15,7});
        printResult(solver.verticalTraversal(ex1));  // [[9], [3,15], [20], [7]]

        // Example 2
        TreeNode ex2 = buildTree(new Integer[]{1,2,3,4,5,6,7});
        printResult(solver.verticalTraversal(ex2));  // [[4], [2], [1,5,6], [3], [7]]

        // Example 3
        TreeNode ex3 = buildTree(new Integer[]{1,2,3,4,6,5,7});
        printResult(solver.verticalTraversal(ex3));  // [[4], [2], [1,5,6], [3], [7]]
    }
}