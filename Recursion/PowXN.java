/**
 * Problem 50. Pow(x, n)
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xⁿ).
 *
 * Given:
 *   -100.0 < x < 100.0  
 *   -2³¹ ≤ n ≤ 2³¹−1  
 *   Either x is nonzero or n > 0.
 *
 * Examples:
 *   Input: x = 2.00000, n = 10    → Output: 1024.00000  
 *   Input: x = 2.10000, n = 3     → Output: 9.26100  
 *   Input: x = 2.00000, n = -2    → Output: 0.25000  
 *
 * Intuition:
 *   Use divide-and-conquer via recursion:  
 *   - If n is even, xⁿ = (x²)^(n/2).  
 *   - If n is odd, xⁿ = x * (x²)^(n/2).  
 *   For negative exponents, compute 1 / x^(−n). Handle Integer.MIN_VALUE
 *   specially since −Integer.MIN_VALUE overflows.
 *
 * Approach:
 *   1. Base case: n == 0 → return 1.  
 *   2. If n > 0:
 *        a. n == 1 → return x.  
 *        b. If n even → return myPow(x*x, n/2).  
 *        c. If n odd  → return x * myPow(x*x, n/2).  
 *   3. If n < 0:
 *        a. If n == MIN_VALUE → return (1/x) * myPow(x, n+1).  
 *        b. Else              → return 1 / myPow(x, -n).  
 *
 * Time Complexity: O(log |n|) — each recursive call halves |n|.  
 * Space Complexity: O(log |n|) — recursion stack depth.
 */
public class PowXN {

    public double myPow(double x, int n) {
        // Base case
        if (n == 0) {
            return 1.0;
        }
        // Positive exponent
        if (n > 0) {
            if (n == 1) {
                return x;
            }
            if (n % 2 == 0) {
                // xⁿ = (x²)^(n/2)
                return myPow(x * x, n / 2);
            } else {
                // xⁿ = x * (x²)^(n/2)
                return x * myPow(x * x, n / 2);
            }
        }
        // Negative exponent
        else {
            if (n == Integer.MIN_VALUE) {
                // Avoid overflow when negating MIN_VALUE
                return (1.0 / x) * myPow(x, n + 1);
            }
            // x⁻ⁿ = 1 / xⁿ
            return 1.0 / myPow(x, -n);
        }
    }

    public static void main(String[] args) {
        PowXN solver = new PowXN();

        // Test cases
        double[][] tests = {
            {2.00000, 10},
            {2.10000, 3},
            {2.00000, -2},
            {0.44528, 0},
            {2.00000, Integer.MIN_VALUE}
        };

        for (double[] t : tests) {
            double x = t[0];
            int n = (int) t[1];
            double result = solver.myPow(x, n);
            System.out.printf("myPow(%.5f, %d) = %.5f%n", x, n, result);
        }
    }
}
