import java.util.*;

/**
 * Problem 547. Number of Provinces
 *
 * There are n cities. Some of them are connected directly, and some are not.
 * If city a is directly connected with city b, and city b is directly connected
 * with city c, then city a is indirectly connected with city c.
 *
 * A province is a group of directly or indirectly connected cities, and no other
 * cities outside the group. Given an n x n matrix isConnected where
 * isConnected[i][j] = 1 if the ith and jth cities are directly connected
 * (and 0 otherwise), return the total number of provinces.
 *
 * Intuition:
 *   The cities and their direct connections form an undirected graph. Each
 *   province corresponds to a connected component in this graph.
 *
 * Approach:
 *   - Maintain a boolean[] visited of length n.
 *   - Iterate each city i; if not yet visited, it's the start of a new province:
 *       1) Increment the province count.
 *       2) BFS (or DFS) from i, marking all reachable cities visited.
 *   - Return the count.
 *
 * Time Complexity: O(n^2), since for each city we scan its entire adjacency row.
 * Space Complexity: O(n), for the visited array and BFS queue.
 */
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Start BFS for a new province
                count++;
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int city = queue.poll();
                    // Check all possible neighbours
                    for (int j = 0; j < n; j++) {
                        if (isConnected[city][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
        return count;
    }

    // ---------- Main method with tests ----------
    public static void main(String[] args) {
        NumberOfProvinces solver = new NumberOfProvinces();

        // Helper to run a single test
        class Test {
            int[][] isConnected;
            int expected;
            Test(int[][] m, int e) { isConnected = m; expected = e; }
        }

        List<Test> tests = Arrays.asList(
            // Example 1
            new Test(new int[][] {
                {1,1,0},
                {1,1,0},
                {0,0,1}
            }, 2),
            // Example 2
            new Test(new int[][] {
                {1,0,0},
                {0,1,0},
                {0,0,1}
            }, 3),
            // Single city
            new Test(new int[][] {
                {1}
            }, 1),
            // Fully connected
            new Test(new int[][] {
                {1,1,1},
                {1,1,1},
                {1,1,1}
            }, 1),
            // Two provinces of size 2
            new Test(new int[][] {
                {1,1,0,0},
                {1,1,0,0},
                {0,0,1,1},
                {0,0,1,1}
            }, 2),
            // Chain connection
            new Test(new int[][] {
                {1,1,0,0,0},
                {1,1,1,0,0},
                {0,1,1,1,0},
                {0,0,1,1,1},
                {0,0,0,1,1}
            }, 1)
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = solver.findCircleNum(t.isConnected);
            System.out.printf(
                "Test %d: expected = %d, got = %d%n",
                i+1, t.expected, result
            );
        }
    }
}