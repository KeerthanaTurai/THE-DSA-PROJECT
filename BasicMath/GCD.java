/**
 * File: GCDCalculator.java
 *
 * Problem: Compute the greatest common divisor (GCD) of two integers using
 *          the Euclidean algorithm.
 *
 * Intuition:
 *   The Euclidean algorithm relies on the fact that the GCD of two numbers
 *   also divides their difference. Concretely,
 *     gcd(a, b) = gcd(b, a mod b)
 *   and when one of them becomes 0, the other is the GCD.
 *
 * Approach:
 *   1. Handle negative inputs by taking absolute values.
 *   2. Base case: if one argument is 0, return the other.
 *   3. Otherwise, recursively compute gcd(b, a % b).
 *
 * Time Complexity: O(log(min(a, b))) — each step reduces the size of the pair.
 * Space Complexity: O(log(min(a, b))) — recursion depth proportional to steps.
 */
public class GCD {

    /**
     * Returns the greatest common divisor of a and b.
     * Assumes gcd(0, 0) == 0 by convention.
     */
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0) return b;
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        // Test cases: pairs of (a, b)
        int[][] tests = {
            {48, 18},      // gcd = 6
            {0, 5},        // gcd = 5
            {5, 0},        // gcd = 5
            {270, 192},    // gcd = 6
            {17, 13},      // gcd = 1 (co-prime)
            {100, 10},     // gcd = 10
            {-24, 36},     // gcd = 12 (negative handled)
            {0, 0}         // gcd = 0 by convention
        };

        for (int i = 0; i < tests.length; i++) {
            int a = tests[i][0];
            int b = tests[i][1];
            int result = gcd(a, b);
            System.out.printf("gcd(%d, %d) = %d%n", a, b, result);
        }
    }
}
