import java.util.Arrays;

/**
 * Problem 48. Rotate Image
 *
 * You are given an n x n 2D matrix representing an image, rotate the image
 * by 90 degrees (clockwise) in-place. You must modify the input matrix directly
 * without allocating another 2D matrix.
 *
 * Example 1:
 *   Input:
 *     [ [1,2,3],
 *       [4,5,6],
 *       [7,8,9] ]
 *   Output:
 *     [ [7,4,1],
 *       [8,5,2],
 *       [9,6,3] ]
 *
 * Example 2:
 *   Input:
 *     [ [ 5, 1, 9,11],
 *       [ 2, 4, 8,10],
 *       [13, 3, 6, 7],
 *       [15,14,12,16] ]
 *   Output:
 *     [ [15,13, 2, 5],
 *       [14, 3, 4, 1],
 *       [12, 6, 8, 9],
 *       [16, 7,10,11] ]
 *
 * Intuition:
 *   Rotating 90° clockwise can be achieved by two steps:
 *     1. Transpose the matrix (swap matrix[i][j] with matrix[j][i]).
 *     2. Reverse each row (swap elements symmetrically around the center column).
 *
 * Approach:
 *   1. Let n = matrix.length.
 *   2. For i in [0..n-1], for j in [i..n-1], swap matrix[i][j] and matrix[j][i].
 *   3. For each row i in [0..n-1], reverse it: for j in [0..n/2-1], swap
 *      matrix[i][j] and matrix[i][n-1-j].
 *
 * Time Complexity: O(n^2), we touch each element a constant number of times.
 * Space Complexity: O(1), in-place swaps only.
 */
public class RotateImage {

    /**
     * Rotates the given n x n matrix 90 degrees clockwise in-place.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 1) Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 2) Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }

    /**
     * Prints the matrix in a readable form.
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Main with comprehensive tests, including edge cases.
     */
    public static void main(String[] args) {
        RotateImage solver = new RotateImage();

        int[][][] tests = {
            {},  // empty matrix
            {{1}},  // 1x1
            {{1, 2}, {3, 4}},  // 2x2
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},  // 3x3
            {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}},  // 4x4
            {{ 5, 1, 9,11},{ 2, 4, 8,10},{13, 3, 6, 7},{15,14,12,16}}  // example
        };

        for (int t = 0; t < tests.length; t++) {
            int[][] matrix = tests[t];
            System.out.printf("Test %d - Original:%n", t + 1);
            if (matrix.length == 0) {
                System.out.println("[]");
            } else {
                printMatrix(matrix);
            }
            solver.rotate(matrix);
            System.out.printf("Test %d - Rotated:%n", t + 1);
            if (matrix.length == 0) {
                System.out.println("[]");
            } else {
                printMatrix(matrix);
            }
            System.out.println("---------");
        }
    }
}