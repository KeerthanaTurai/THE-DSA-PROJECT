/**
 *
 * Problem: Compute the factorial of a non-negative integer n.
 *
 * Intuition:
 *   The factorial function satisfies:
 *     0! = 1
 *     n! = n × (n−1)!  for n ≥ 1
 *   We can implement this directly with recursion.
 *
 * Approach:
 *   1. Base case: if n == 0 or n == 1, return 1.
 *   2. Recursive step: return n × factorial(n−1).
 *
 * Time Complexity: O(n) — one multiplication per integer down to 1.
 * Space Complexity: O(n) — recursion depth proportional to n.
 */
public class Factorial {

    /**
     * Returns n! (n factorial) for n ≥ 0.
     * Assumes result fits within a 32-bit signed integer when n is small.
     */
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int[] testNs = {0, 1, 5, 10, 12};

        for (int n : testNs) {
            int result = factorial(n);
            System.out.printf("%d! = %d%n", n, result);
        }
    }
}

