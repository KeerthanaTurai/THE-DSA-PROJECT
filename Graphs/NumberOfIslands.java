/**
 * Problem 200. Number of Islands
 *
 * Given an m x n 2D binary grid grid which represents a map of '1's (land)
 * and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 *
 * Example 1:
 *   Input:
 *     [ ['1','1','1','1','0'],
 *       ['1','1','0','1','0'],
 *       ['1','1','0','0','0'],
 *       ['0','0','0','0','0'] ]
 *   Output: 1
 *
 * Example 2:
 *   Input:
 *     [ ['1','1','0','0','0'],
 *       ['1','1','0','0','0'],
 *       ['0','0','1','0','0'],
 *       ['0','0','0','1','1'] ]
 *   Output: 3
 *
 * Intuition:
 *   The grid defines an undirected graph where each '1' cell is a vertex
 *   connected to its up/down/left/right neighbors that are also '1'. Each
 *   island is one connected component.
 *
 * Approach:
 *   - Use DFS/BFS to count connected components of '1's.
 *   - Maintain a visited mark by flipping '1'→'0' when we visit it.
 *   - Iterate over every cell; when we find a '1', increment count and
 *     perform DFS from that cell to mark the entire island as '0'.
 *
 * Time Complexity: O(m * n), since each cell is visited at most once.
 * Space Complexity: O(m * n) in the worst case recursion depth (all land).
 */
public class NumberOfIslands {
    private int m, n;
    private int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

    /**
     * Returns the number of islands in the grid.
     */
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * Depth-first search that marks all reachable land ('1') from (r,c) to '0'.
     */
    private void dfs(char[][] grid, int r, int c) {
        grid[r][c] = '0';  // mark visited
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == '1') {
                dfs(grid, nr, nc);
            }
        }
    }

    /** Deep-copies a 2D char array. */
    private static char[][] deepCopy(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        char[][] copy = new char[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    /** Main with comprehensive tests. */
    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        char[][][] tests = {
            // Example 1
            {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
            },
            // Example 2
            {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
            },
            // Single row
            {
                {'1','0','1','1','0','1','1'}
            },
            // All water
            {
                {'0','0','0'},
                {'0','0','0'},
                {'0','0','0'}
            },
            // Single cell land
            {
                {'1'}
            },
            // Single cell water
            {
                {'0'}
            }
        };
        int[] expected = {1, 3, 3, 0, 1, 0};

        for (int i = 0; i < tests.length; i++) {
            char[][] gridCopy = deepCopy(tests[i]);
            int result = solver.numIslands(gridCopy);
            System.out.printf("Test %d: expected = %d, got = %d%n",
                i + 1, expected[i], result);
        }
    }
}