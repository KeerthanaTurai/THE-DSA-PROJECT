import java.util.*;

/**
 * Problem 79. Word Search
 *
 * Intuition:
 *   We need to determine if a given word can be formed by a path of adjacent
 *   cells (up/down/left/right) in the grid, without reusing any cell. This
 *   suggests a Depth-First Search (DFS) from each starting cell that matches
 *   the first character, with backtracking to mark visited cells.
 *
 * Approach:
 *   1. Loop over every cell (i, j) in the board.
 *   2. If board[i][j] == word.charAt(0), start a backtracking DFS from (i, j),
 *      with a counter of how many letters we've matched so far.
 *   3. In backtracking:
 *        - If count == word.length(), we've matched all letters → return true.
 *        - If out of bounds or board[i][j] != word.charAt(count), return false.
 *        - Mark board[i][j] as visited (e.g., store in temp and set to a sentinel).
 *        - Recurse in four directions with count+1.
 *        - Restore board[i][j] from temp.
 *        - Return true if any direction succeeds.
 *   4. If any starting DFS returns true, exist(...) returns true; otherwise false.
 *
 * Time Complexity:
 *   O(m * n * 4^L), where m×n is the board size and L = word.length().
 *   In the worst case, from each cell we explore up to 4 directions L deep.
 *
 * Space Complexity:
 *   O(L) for the recursion stack in the worst case.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtracking(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtracking(char[][] board, String word, int i, int j, int count) {
        if (count == word.length()) return true;
        if (i < 0 || j < 0 ||
            i >= board.length || j >= board[0].length ||
            board[i][j] != word.charAt(count)) {
            return false;
        }

        // mark visited
        char temp = board[i][j];
        board[i][j] = '\0';

        // explore neighbors
        boolean found = backtracking(board, word, i - 1, j, count + 1)
                     || backtracking(board, word, i + 1, j, count + 1)
                     || backtracking(board, word, i, j - 1, count + 1)
                     || backtracking(board, word, i, j + 1, count + 1);

        // restore
        board[i][j] = temp;
        return found;
    }

    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        WordSearch solver = new WordSearch();

        class Test {
            char[][] board;
            String word;
            boolean expected;
            Test(char[][] b, String w, boolean e) {
                board = b; word = w; expected = e;
            }
        }

        List<Test> tests = Arrays.asList(
            // Example 1
            new Test(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
            }, "ABCCED", true),

            // Example 2
            new Test(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
            }, "SEE", true),

            // Example 3
            new Test(new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
            }, "ABCB", false),

            // Single cell match
            new Test(new char[][] { {'X'} }, "X", true),

            // Single cell no match
            new Test(new char[][] { {'X'} }, "Y", false),

            // Word longer than total cells
            new Test(new char[][] {
                {'A','B'},
                {'C','D'}
            }, "ABCDX", false),

            // Backtracking required
            new Test(new char[][] {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
            }, "AAB", true),

            // All same letters, repeated word
            new Test(new char[][] {
                {'A','A'},
                {'A','A'}
            }, "AAAAA", false)
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            // Deep copy board so tests are independent
            char[][] boardCopy = new char[t.board.length][t.board[0].length];
            for (int r = 0; r < t.board.length; r++) {
                System.arraycopy(t.board[r], 0, boardCopy[r], 0, t.board[0].length);
            }

            boolean result = solver.exist(boardCopy, t.word);
            System.out.printf(
                "Test %2d: word=\"%s\" → result=%b (expected=%b)%n",
                i + 1, t.word, result, t.expected
            );
        }
    }
}