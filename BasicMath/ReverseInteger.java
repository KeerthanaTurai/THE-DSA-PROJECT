/**
 * File: ReverseInteger.java
 *
 * Problem 7. Reverse Integer
 *
 * Intuition:
 *   We build the reversed number digit by digit by:
 *     1) Extracting the last digit of x via x % 10.
 *     2) Appending it to rev by rev = rev * 10 + rem.
 *     3) Removing that digit from x via x /= 10.
 *
 *   Before each multiplication by 10 (and addition of rem), we must ensure
 *   we won’t overflow the 32-bit signed integer range [–2³¹, 2³¹–1].
 *
 * Why divide by 10 in our checks?
 *   We compare rev against Integer.MAX_VALUE/10 (and Integer.MIN_VALUE/10)
 *   because when we do rev * 10, rev must be ≤ MAX_VALUE/10, otherwise
 *   rev * 10 alone would already exceed MAX_VALUE.
 *
 * Why compare rem with 7 and 8?
 *   Integer.MAX_VALUE =  2_147_483_647 → last digit is 7
 *   Integer.MIN_VALUE = –2_147_483_648 → last digit is –8
 *
 *   If rev == MAX_VALUE/10 (214_748_364), then rev * 10 == 2_147_483_640,
 *   so we can only add at most 7 to stay within 2_147_483_647.
 *   If rem > 7 in that case, we’d overflow.
 *
 *   Similarly, if rev == MIN_VALUE/10 (–214_748_364), then rev * 10 == –2_147_483_640,
 *   and we can only subtract up to 8 (i.e. rem < –8) to stay ≥ –2_147_483_648.
 *
 * Approach:
 *   1. Initialize rev = 0.
 *   2. While x != 0:
 *        - rem = x % 10;       // extract last digit
 *        - x  = x / 10;       // drop that digit
 *        - if rev >  MAX/10    // would overflow on rev*10
 *             || (rev == MAX/10 && rem > 7)
 *           return 0;
 *        - if rev <  MIN/10    // would underflow on rev*10
 *             || (rev == MIN/10 && rem < -8)
 *           return 0;
 *        - rev = rev * 10 + rem;
 *   3. Return rev.
 *
 * Time Complexity: O(log₁₀|x|) — one iteration per digit.
 * Space Complexity: O(1).
 */
public class ReverseInteger {

    public int reverse(int x) {
        int rev = 0;
        int MAX_DIV_10 = Integer.MAX_VALUE / 10;  // 214_748_364
        int MIN_DIV_10 = Integer.MIN_VALUE / 10;  // -214_748_364

        while (x != 0) {
            int rem = x % 10;
            x = x / 10;

            // Overflow check:
            if (rev > MAX_DIV_10 || (rev == MAX_DIV_10 && rem > 7)) {
                return 0;
            }
            // Underflow check:
            if (rev < MIN_DIV_10 || (rev == MIN_DIV_10 && rem < -8)) {
                return 0;
            }

            rev = rev * 10 + rem;
        }

        return rev;
    }

    public static void main(String[] args) {
        ReverseInteger solution = new ReverseInteger();

        int[] testValues = {
            123,          // 321
            -123,         // -321
            120,          // 21
            0,            // 0
            1534236469    // overflow → 0
        };

        for (int x : testValues) {
            int result = solution.reverse(x);
            System.out.printf("reverse(%d) = %d%n", x, result);
        }
    }
}
