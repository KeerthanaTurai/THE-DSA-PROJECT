/**
 * File: ArmstrongNumber.java
 *
 * Problem: Determine whether a given integer n is an Armstrong (narcissistic) number.
 *
 * Intuition:
 *   An Armstrong number of order k is an integer such that the sum of its own
 *   digits each raised to the power k equals the number itself. For three-digit
 *   numbers, k = 3 and we check whether n == d₁³ + d₂³ + d₃³.
 *
 * Approach:
 *   1. Store the original number in a temporary variable.
 *   2. Repeatedly extract the last digit d = t % 10, add d³ to an accumulator.
 *   3. Remove the last digit via t /= 10, and repeat until t == 0.
 *   4. Compare the accumulated sum to the original number.
 *
 * Time Complexity: O(d), where d is the number of digits (since we process each digit once).
 * Space Complexity: O(1) — only a few integer variables are used.
 */
public class ArmstrongNumber {

    /**
     * Returns true if n is an Armstrong number (for three-digit numbers checks sum of cubes).
     */
    public static boolean armstrongNumber(int n) {
        if(n<=99 || n>=1000) return false;
        int t = n;
        int sumCubes = 0;
        while (t != 0) {
            int d = t % 10;
            sumCubes += d * d * d;
            t /= 10;
        }
        return sumCubes == n;
    }

    public static void main(String[] args) {
        int[] testValues = {
            0,    // false (0^3 = 0 but typically Armstrong defined for positive)
            1,    // false (1^3 = 1 but single-digit Armstrongs usually defined with k=1)
            153,  // true (1³ + 5³ + 3³ = 153)
            370,  // true
            371,  // true
            407,  // true
            123,  // false
            9474  // false (this is a 4-digit Armstrong, but our check uses cubes)
        };

        for (int n : testValues) {
            boolean isArmstrong = armstrongNumber(n);
            System.out.printf("%d is%s an Armstrong number.%n",
                n, (isArmstrong ? "" : " not"));
        }
    }
}
