import java.util.*;

/**
 * Problem 297. Serialize and Deserialize Binary Tree
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work,
 * as long as a tree can be converted to a string and back to the identical
 * structure.
 *
 * Intuition:
 *   We perform a breadth-first traversal (level order), writing out node values
 *   and using a special marker for null children. During deserialization, we
 *   rebuild the tree in the same level-order fashion.
 *
 * Approach (BFS with null markers):
 *   - serialize(root):
 *       1. If root is null, return an empty string.
 *       2. Initialize a queue with root and a StringBuilder.
 *       3. While the queue is not empty:
 *            – Poll a node. If non-null, append its value and enqueue its
 *              left and right children (which may be null). If null, append
 *              the marker "n".
 *            – Separate tokens with a space.
 *       4. Return the built string.
 *
 *   - deserialize(data):
 *       1. If data is empty, return null.
 *       2. Split on spaces to get tokens. The first token is the root’s value.
 *       3. Initialize a queue with the root.
 *       4. Iterate through tokens in pairs (for left/right) while queue not empty:
 *            – Poll a parent node.
 *            – For the next token: if not "n", create left child, attach & enqueue.
 *            – For the following token: similarly handle the right child.
 *       5. Return the reconstructed root.
 *
 * Time Complexity: O(N), where N = number of tree nodes (we process each node once).
 * Space Complexity: O(N), for the queue and the output string.
 */
public class SerializeDeserializeBinaryTree {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                if (node == null) {
                    sb.append("n ");
                } else {
                    sb.append(node.val).append(' ');
                    q.offer(node.left);
                    q.offer(node.right);
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;
            String[] tokens = data.split(" ");
            TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            int i = 1;
            while (i < tokens.length) {
                TreeNode parent = q.poll();
                // Left child
                if (!tokens[i].equals("n")) {
                    TreeNode left = new TreeNode(Integer.parseInt(tokens[i]));
                    parent.left = left;
                    q.offer(left);
                }
                i++;
                // Right child
                if (!tokens[i].equals("n")) {
                    TreeNode right = new TreeNode(Integer.parseInt(tokens[i]));
                    parent.right = right;
                    q.offer(right);
                }
                i++;
            }
            return root;
        }
    }

    // Helper to build a tree from level-order array (null for missing nodes)
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

    // Prints tree in level order (for verification)
    private static List<String> printLevelOrder(TreeNode root) {
        List<String> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.add("n");
            } else {
                res.add(String.valueOf(node.val));
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return res;
    }

    // Test harness
    public static void main(String[] args) {
        Codec ser = new Codec(), deser = new Codec();

        // Test 1: [1,2,3,null,null,4,5]
        Integer[] arr1 = {1, 2, 3, null, null, 4, 5};
        TreeNode root1 = buildTree(arr1);
        String s1 = ser.serialize(root1);
        System.out.println("Serialized: " + s1);
        TreeNode r1 = deser.deserialize(s1);
        System.out.println("Reconstructed (level order): " + printLevelOrder(r1));

        // Test 2: empty tree
        TreeNode root2 = null;
        String s2 = ser.serialize(root2);
        System.out.println("Serialized empty: \"" + s2 + "\"");
        TreeNode r2 = deser.deserialize(s2);
        System.out.println("Reconstructed empty: " + (r2 == null ? "null" : "not null"));

        // Test 3: single node
        Integer[] arr3 = {42};
        TreeNode root3 = buildTree(arr3);
        String s3 = ser.serialize(root3);
        System.out.println("Serialized single: " + s3);
        TreeNode r3 = deser.deserialize(s3);
        System.out.println("Reconstructed single: " + printLevelOrder(r3));

        // Test 4: full tree of depth 3
        Integer[] arr4 = {1,2,3,4,5,6,7};
        TreeNode root4 = buildTree(arr4);
        String s4 = ser.serialize(root4);
        System.out.println("Serialized full: " + s4);
        TreeNode r4 = deser.deserialize(s4);
        System.out.println("Reconstructed full: " + printLevelOrder(r4));
    }
}