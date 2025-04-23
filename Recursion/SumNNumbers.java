/**
 * File: SumNNumbers.java
 *
 * Problem: Sum of the first N natural numbers using recursion.
 *
 * Intuition:
 *   To compute 1 + 2 + … + N, note that this sum S(N) = N + S(N-1).  
 *   Recursively reduce the problem until the base case where N = 1.
 *
 * Approach:
 *   1. Base case: if N ≤ 1, return N (since sum of first 1 numbers is 1, and for N=0 it would be 0).
 *   2. Recursive step: return N + NnumbersSum(N - 1).
 *
 * Time Complexity: O(N) — you make one recursive call per integer down to 1.
 * Space Complexity: O(N) — due to recursion stack depth of N.
 */
public class SumNNumbers {

    /**
     * Returns the sum 1 + 2 + ... + N using recursion.
     */
    public static int NnumbersSum(int N) {
        if (N <= 1) return N;
        return N + NnumbersSum(N - 1);
    }

    public static void main(String[] args) {
        int[] testNs = {1, 2, 5, 10, 20};

        for (int N : testNs) {
            System.out.println("Sum of first " + N + " numbers = " + NnumbersSum(N));
        }
    }
}
