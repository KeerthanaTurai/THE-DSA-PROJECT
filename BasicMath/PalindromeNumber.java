/**
 * File: PalindromeNumber.java
 *
 * Problem 9. Palindrome Number
 *
 * Intuition:
 *   A number is a palindrome if it reads the same forwards and backwards.
 *   Instead of converting to a string, we can reverse half of the digits
 *   numerically and compare to the remaining half.
 *
 * Key Observations:
 *   - Negative numbers are never palindromes.
 *   - Any number ending in 0 (except 0 itself) cannot be a palindrome.
 *   - We only need to reverse half of the digits. Once the reversed half
 *     is ≥ the remaining half, we can stop.
 *
 * Approach:
 *   1. If x < 0 or (x % 10 == 0 && x != 0), return false.
 *   2. Initialize rev = 0.
 *   3. While x > rev:
 *        - rev = rev * 10 + (x % 10);
 *        - x /= 10;
 *   4. At this point:
 *        - If the original length is even, x == rev
 *        - If odd, the middle digit is in rev, so x == rev / 10
 *   5. Return x == rev || x == rev / 10.
 *
 * Time Complexity: O(log₁₀ x) — we process half the digits.
 * Space Complexity: O(1).
 */
public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        // Quick checks
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0;
        // Build up reversed half
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        // Compare halves (drop middle digit for odd-length)
        return x == rev || x == rev / 10;
    }

    public static void main(String[] args) {
        PalindromeNumber solution = new PalindromeNumber();

        int[] tests = {
            121,     // true
            -121,    // false (negative)
            10,      // false (ends with 0)
            12321,   // true (odd length)
            1221,    // true (even length)
            0,       // true
            1001,    // true
            123,     // false
            1        // true
        };

        for (int x : tests) {
            boolean result = solution.isPalindrome(x);
            System.out.printf("isPalindrome(%d) = %b%n", x, result);
        }
    }
}

