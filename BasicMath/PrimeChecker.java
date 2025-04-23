/**
 *
 * Problem: Determine whether a given integer n is prime.
 *
 * Intuition:
 *   A prime number n (>1) has no divisors other than 1 and itself.  
 *   If n has a divisor d, then n = d * k, and one of d or k must be ≤ √n.
 *   Therefore, it suffices to check divisibility from 2 up to √n.
 *
 * Approach:
 *   1. For i from 2 to floor(√n):
 *        - If n % i == 0, n is composite → return false.
 *   2. If no divisor found, return true.
 *
 * Time Complexity: O(√n) — we test at most √n candidates.
 * Space Complexity: O(1) — only a few variables used.
 */
public class PrimeChecker {

    /**
     * Returns true if n is prime (greater than 1 with no divisors other than 1 and itself).
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Runs several test cases to demonstrate the isPrime method.
     */
    public static void main(String[] args) {
        int[] testValues = {
            0, 1,    // edge cases (not prime)
            2, 3,        // small primes
            4, 16, 17,   // composite vs. prime
            19, 20,      // further checks
            7919         // larger prime
        };

        for (int n : testValues) {
            System.out.printf("isPrime(%d) = %b%n", n, isPrime(n));
        }
    }
}
