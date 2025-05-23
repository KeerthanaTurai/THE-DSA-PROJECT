/**
 * Problem 348. Design Tic-Tac-Toe
 *
 * We want to implement a Tic-Tac-Toe game on an n×n board supporting O(1)
 * time per move to check for a winner.
 *
 * Intuition:
 *   Instead of storing the entire board, we keep running sums for each row,
 *   each column, the main diagonal, and the anti-diagonal. When player 1
 *   moves, we add +1; when player 2 moves, we add –1. If any line’s absolute
 *   sum reaches n, that player wins.
 *
 * Approach:
 *   - rows[row]   tracks the net marks in that row.
 *   - cols[col]   tracks the net marks in that column.
 *   - dia         tracks the net marks on the main diagonal (row == col).
 *   - anti_dia    tracks the net marks on the anti-diagonal (row + col == n-1).
 *   - On move(row, col, player):
 *       1) Let delta = (player == 1 ? +1 : -1).
 *       2) rows[row]   += delta
 *          cols[col]   += delta
 *          if row==col      dia      += delta
 *          if row+col==n-1  anti_dia += delta
 *       3) If any of |rows[row]|, |cols[col]|, |dia|, |anti_dia| == n,
 *          return player; otherwise return 0.
 *
 * Time Complexity: O(1) per move.
 * Space Complexity: O(n) for the rows and cols arrays, plus O(1) for the two diagonals.
 */
public class TicTacToe {
    private final int n;
    private final int[] rows, cols;
    private int dia, anti_dia;

    /** Initialize the TicTacToe board of size n. */
    public TicTacToe(int n) {
        this.n = n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.dia = 0;
        this.anti_dia = 0;
    }
    
    /**
     * Player {player} makes a move at (row, col).
     * @return 0 if no one wins; 1 or 2 if that player wins with this move.
     */
    public int move(int row, int col, int player) {
        int delta = (player == 1 ? +1 : -1);
        
        rows[row] += delta;
        cols[col] += delta;
        if (row == col) {
            dia += delta;
        }
        if (row + col == n - 1) {
            anti_dia += delta;
        }
        
        if (Math.abs(rows[row]) == n
         || Math.abs(cols[col]) == n
         || Math.abs(dia)      == n
         || Math.abs(anti_dia) == n) {
            return player;
        }
        return 0;
    }
    
    // -------------------- Test Harness --------------------
    public static void main(String[] args) {
        // Helper to run a sequence of moves
        class Move {
            int row, col, player, expected;
            Move(int r, int c, int p, int e) {
                row = r; col = c; player = p; expected = e;
            }
        }
        
        // Test 1: Horizontal win for player 1 on row 1
        System.out.println("Test 1: Horizontal Win");
        TicTacToe game1 = new TicTacToe(3);
        Move[] moves1 = {
            new Move(1, 0, 1, 0),
            new Move(0, 0, 2, 0),
            new Move(1, 1, 1, 0),
            new Move(0, 1, 2, 0),
            new Move(1, 2, 1, 1)  // completes row 1
        };
        for (Move m : moves1) {
            int res = game1.move(m.row, m.col, m.player);
            System.out.printf("move(%d,%d,player%d) -> %d (expected %d)%n",
                m.row, m.col, m.player, res, m.expected);
        }
        System.out.println();

        // Test 2: Vertical win for player 2 on column 2
        System.out.println("Test 2: Vertical Win");
        TicTacToe game2 = new TicTacToe(3);
        Move[] moves2 = {
            new Move(0, 2, 2, 0),
            new Move(0, 0, 1, 0),
            new Move(1, 2, 2, 0),
            new Move(1, 0, 1, 0),
            new Move(2, 2, 2, 2)  // completes column 2
        };
        for (Move m : moves2) {
            int res = game2.move(m.row, m.col, m.player);
            System.out.printf("move(%d,%d,player%d) -> %d (expected %d)%n",
                m.row, m.col, m.player, res, m.expected);
        }
        System.out.println();

        // Test 3: Main diagonal win for player 1
        System.out.println("Test 3: Main Diagonal Win");
        TicTacToe game3 = new TicTacToe(3);
        Move[] moves3 = {
            new Move(0, 0, 1, 0),
            new Move(0, 1, 2, 0),
            new Move(1, 1, 1, 0),
            new Move(0, 2, 2, 0),
            new Move(2, 2, 1, 1)  // completes main diagonal
        };
        for (Move m : moves3) {
            int res = game3.move(m.row, m.col, m.player);
            System.out.printf("move(%d,%d,player%d) -> %d (expected %d)%n",
                m.row, m.col, m.player, res, m.expected);
        }
        System.out.println();

        // Test 4: Anti-diagonal win for player 2
        System.out.println("Test 4: Anti-Diagonal Win");
        TicTacToe game4 = new TicTacToe(4);
        Move[] moves4 = {
            new Move(0, 3, 2, 0),
            new Move(0, 0, 1, 0),
            new Move(1, 2, 2, 0),
            new Move(1, 1, 1, 0),
            new Move(2, 1, 2, 0),
            new Move(2, 2, 1, 0),
            new Move(3, 0, 2, 2)  // completes anti-diagonal on 4×4
        };
        for (Move m : moves4) {
            int res = game4.move(m.row, m.col, m.player);
            System.out.printf("move(%d,%d,player%d) -> %d (expected %d)%n",
                m.row, m.col, m.player, res, m.expected);
        }
        System.out.println();

        // Test 5: No win yet
        System.out.println("Test 5: No Winner Yet");
        TicTacToe game5 = new TicTacToe(3);
        Move[] moves5 = {
            new Move(0, 0, 1, 0),
            new Move(0, 1, 2, 0),
            new Move(1, 0, 1, 0),
            new Move(1, 2, 2, 0),
            new Move(2, 1, 1, 0)
        };
        for (Move m : moves5) {
            int res = game5.move(m.row, m.col, m.player);
            System.out.printf("move(%d,%d,player%d) -> %d (expected %d)%n",
                m.row, m.col, m.player, res, m.expected);
        }
    }
}